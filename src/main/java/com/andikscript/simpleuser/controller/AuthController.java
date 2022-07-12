package com.andikscript.simpleuser.controller;

import com.andikscript.simpleuser.exception.TokenRefreshException;
import com.andikscript.simpleuser.model.RefreshToken;
import com.andikscript.simpleuser.model.User;
import com.andikscript.simpleuser.payload.JwtResponse;
import com.andikscript.simpleuser.payload.TokenRefreshRequest;
import com.andikscript.simpleuser.payload.TokenRefreshResponse;
import com.andikscript.simpleuser.payload.UsernamePasswordRequest;
import com.andikscript.simpleuser.repository.UserRepository;
import com.andikscript.simpleuser.security.jwt.JwtUtils;
import com.andikscript.simpleuser.security.jwt.RefreshTokenService;
import com.andikscript.simpleuser.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity addUser(@RequestBody User user) {
        if (user.getUsername().equals(null) || user.getPassword().equals(null) ||
                user.getUsername().equals("") || user.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/signin", consumes = "application/json")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody UsernamePasswordRequest usernamePasswordRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernamePasswordRequest.getUsername(),
                        usernamePasswordRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),
                userDetails.getUsername(),
                userDetails.getEmail(), roles));
    }

    @PostMapping(value = "/refreshtoken", consumes = "application/json")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(user -> {
                    User user1 = user.getIdUser();
                    String token = jwtUtils.generateTokenFromUsername(user1.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token not store on database"));
    }
}
