package org.alexv.vet_manager_api.security.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.vet_manager_api.security.domain.dto.AuthRequestDTO;
import org.alexv.vet_manager_api.security.domain.dto.AuthResponseDTO;
import org.alexv.vet_manager_api.security.domain.dto.UserAddResponseDTO;
import org.alexv.vet_manager_api.security.domain.dto.UserInfoDTO;
import org.alexv.vet_manager_api.security.service.JwtService;
import org.alexv.vet_manager_api.security.service.UserInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserInfoService userInfoService;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @GetMapping("welcome")
    public String welcome() {
        return "Welcome this endpoint is not secured";
    }

    @PostMapping("/addNewUser")
    public UserAddResponseDTO addNewUser(@RequestBody UserInfoDTO userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public AuthResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return new AuthResponseDTO(jwtService.generateToken(authRequest.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }

}