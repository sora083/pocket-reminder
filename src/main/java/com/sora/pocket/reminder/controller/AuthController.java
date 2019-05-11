package com.sora.pocket.reminder.controller;

import com.sora.pocket.reminder.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/init")
    public String initialize() {
        return authenticationService.initialize();
    }
}
