package com.andikscript.simpleuser.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/test")
public class TestController {

    @GetMapping(value = "/all")
    public String allAccess() {
        return "Public Contenttt";
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER')")
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
