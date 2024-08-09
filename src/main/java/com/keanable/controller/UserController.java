// // package com.keanable.controller;

// package com.keanable.controller;

// import com.keanable.service.*;

// import jakarta.annotation.security.PermitAll;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @PermitAll
// @RequestMapping("/api/users")
// public class UserController {
//     @Autowired
//     private UserService userService;

//     @PostMapping("/register/{username}")
//     public String registerUser(@PathVariable String username) {
//         return userService.registerUser(username);
//     }

//     @PutMapping("/update/{username}")
//     public void updateUser(@PathVariable String username, @RequestBody String newPassword) {
//         userService.updateUser(username, newPassword);
//     }

//     @DeleteMapping("/delete/{username}")
//     public void deleteUser(@PathVariable String username) {
//         userService.deleteUser(username);
//     }

//     @GetMapping("/retrieve")
//     public String retrieveUsernameByPassword(@RequestParam String password) {
//         return userService.retrieveUsernameByPassword(password);
//     }
// }









package com.keanable.controller;

import com.keanable.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/{username}")
    public ResponseEntity<String> registerUser(@PathVariable String username) {
        try {
            String password = userService.registerUser(username);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(username+" Registered successfully.and his Password: " + password);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(e.getMessage());
        }
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, 
                                              @RequestParam String newPassword) {
        try {
            userService.updateUser(username, newPassword);
            return ResponseEntity.ok(username+"User updated successfully.update "+ newPassword);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(e.getMessage());
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity<String> retrieveUsernameByPassword(@RequestParam String password) {
        try {
            String username = userService.retrieveUsernameByPassword(password);
            return ResponseEntity.ok("Username retrieved: " + username);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(e.getMessage());
        }
    }
}


//////////////////////////////////////////////////////////////////////APIEndPoint////////////////////

// API testing using Postman,

// Register User:

// Method: POST
// URL: http://localhost:8080/api/users/register/Shiva


// Update User:

// Method: PUT
// URL: http://localhost:8080/api/users/update/Shiva?newPassword=updatePassword


// Delete User:

// Method: DELETE
// URL: http://localhost:8080/api/users/delete/Shiv


// Retrieve Username by Password:

// Method: GET
// URL: http://localhost:8080/api/users/retrieve?password=genpassword
