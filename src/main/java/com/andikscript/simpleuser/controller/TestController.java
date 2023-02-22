package com.andikscript.simpleuser.controller;

import com.andikscript.simpleuser.webclient.WebClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/test")
public class TestController {

    private final WebClientService webClientService;

    public TestController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @GetMapping(value = "/all")
    public String allAccess() {
        return "Public Contenttt " + webClientService.callShow();
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER') or hasRole('ROOT')")
    public String userAccess() {
        return "User Content";
    }

    @GetMapping(value = "/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Content";
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Content";
    }

    @GetMapping(value = "/root")
    @PreAuthorize("hasRole('ROOT')")
    public String rootAccess() {
        return "Root Content";
    }
}
