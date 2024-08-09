// 
// package com.keanable.service;

// import org.mindrot.jbcrypt.BCrypt;
// import org.springframework.stereotype.Service;

// import java.util.HashMap;
// import java.util.Map;

// @Service
// public class UserService {
//     private Map<String, String> users = new HashMap<>();

//     public String registerUser(String username) {
//         if (users.containsKey(username)) {
//             throw new RuntimeException("Username already exists.");
//         }
//         String password = generatePassword();
//         String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
//         users.put(username, hashedPassword);
//         return password;  // Return generated password
//     }

//     public void updateUser(String username, String newPassword) {
//         if (!users.containsKey(username)) {
//             throw new RuntimeException("User not found.");
//         }
//         String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
//         users.put(username, hashedPassword);
//     }

//     public void deleteUser(String username) {
//         if (!users.containsKey(username)) {
//             throw new RuntimeException("User not found.");
//         }
//         users.remove(username);
//     }

//     public String retrieveUsernameByPassword(String password) {
//         for (Map.Entry<String, String> entry : users.entrySet()) {
//             if (BCrypt.checkpw(password, entry.getValue())) {
//                 return entry.getKey();
//             }
//         }
//         throw new RuntimeException("Invalid password.");
//     }

//     private String generatePassword() {
//         // Simple password generation logic
//         return String.valueOf((int) (Math.random() * 1000000000));
//     }
// }


//0908

package com.keanable.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    private Map<String, String> users = new HashMap<>();
    private Set<String> deletedUsers = new HashSet<>(); // Set to track deleted usernames

    public String registerUser(String username) {
        if (users.containsKey(username) || deletedUsers.contains(username)) {
            throw new RuntimeException("Username already exists or has been deleted.");
        }
        String password = generatePassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        users.put(username, hashedPassword);
        return password;  // Return generated password
    }

    public void updateUser(String username, String newPassword) {
        if (!users.containsKey(username)) {
            throw new RuntimeException("User not found.");
        }
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        users.put(username, hashedPassword);
    }

    public void deleteUser(String username) {
        if (!users.containsKey(username)) {
            throw new RuntimeException("User not found.");
        }
        users.remove(username);
        deletedUsers.add(username); // Add deleted username to the set
    }

    public String retrieveUsernameByPassword(String password) {
        for (Map.Entry<String, String> entry : users.entrySet()) {
            if (BCrypt.checkpw(password, entry.getValue())) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Invalid password.");
    }

    private String generatePassword() {
        // Simple password generation logic
        return String.valueOf((int) (Math.random() * 1000000000));
    }
}

