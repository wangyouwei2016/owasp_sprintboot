// UserController.java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String getUserInfo() {
        // Insecure direct object reference vulnerability
        // This endpoint should only be accessible to authenticated users with specific roles
        // However, it currently allows any user to access sensitive user information
        return "Sensitive user information";
    }
}
