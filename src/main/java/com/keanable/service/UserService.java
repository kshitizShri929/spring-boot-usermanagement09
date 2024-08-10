// // 
// // package com.keanable.service;

// // import org.mindrot.jbcrypt.BCrypt;
// // import org.springframework.stereotype.Service;

// // import java.util.HashMap;
// // import java.util.Map;

// // @Service
// // public class UserService {
// //     private Map<String, String> users = new HashMap<>();

// //     public String registerUser(String username) {
// //         if (users.containsKey(username)) {
// //             throw new RuntimeException("Username already exists.");
// //         }
// //         String password = generatePassword();
// //         String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
// //         users.put(username, hashedPassword);
// //         return password;  // Return generated password
// //     }

// //     public void updateUser(String username, String newPassword) {
// //         if (!users.containsKey(username)) {
// //             throw new RuntimeException("User not found.");
// //         }
// //         String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
// //         users.put(username, hashedPassword);
// //     }

// //     public void deleteUser(String username) {
// //         if (!users.containsKey(username)) {
// //             throw new RuntimeException("User not found.");
// //         }
// //         users.remove(username);
// //     }

// //     public String retrieveUsernameByPassword(String password) {
// //         for (Map.Entry<String, String> entry : users.entrySet()) {
// //             if (BCrypt.checkpw(password, entry.getValue())) {
// //                 return entry.getKey();
// //             }
// //         }
// //         throw new RuntimeException("Invalid password.");
// //     }

// //     private String generatePassword() {
// //         // Simple password generation logic
// //         return String.valueOf((int) (Math.random() * 1000000000));
// //     }
// // }


// //0908

// package com.keanable.service;

// import org.mindrot.jbcrypt.BCrypt;
// import org.springframework.stereotype.Service;

// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Set;

// @Service
// public class UserService {
//     private Map<String, String> users = new HashMap<>();
//     private Set<String> deletedUsers = new HashSet<>(); // Set to track deleted usernames

//     public String registerUser(String username) {
//         if (users.containsKey(username) || deletedUsers.contains(username)) {
//             throw new RuntimeException("Username already exists or has been deleted.");
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
//         deletedUsers.add(username); // Add deleted username to the set
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






// package com.keanable.service;

// import org.mindrot.jbcrypt.BCrypt;
// import org.springframework.stereotype.Service;

// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Set;

// @Service
// public class UserService {
//     private Map<String, String> users = new HashMap<>();
//     private Set<String> deletedUsers = new HashSet<>(); // Set to track deleted usernames

//     public String registerUser(String username) {
//         if (users.containsKey(username) || deletedUsers.contains(username)) {
//             throw new RuntimeException("Username already exists or has been deleted.");
//         }
//         String password = generatePassword(username);
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
//         deletedUsers.add(username); // Add deleted username to the set
//     }

//     public String retrieveUsernameByPassword(String password) {
//         for (Map.Entry<String, String> entry : users.entrySet()) {
//             if (BCrypt.checkpw(password, entry.getValue())) {
//                 return entry.getKey();
//             }
//         }
//         throw new RuntimeException("Invalid password.");
//     }

//     private String generatePassword(String username) {
//         StringBuilder passwordBuilder = new StringBuilder();
//         int sum = 0;

//         // Iterate over each character in the username
//         for (char ch : username.toCharArray()) {
//             // Calculate the alphabetical position (A=1, B=2, ..., Z=26)
//             int position = Character.toUpperCase(ch) - 'A' + 1;
//             passwordBuilder.append(position);
//             sum += position; // Add position to sum
//         }

//         // Add the sum of the positions at the end of the password
//         passwordBuilder.append(sum);

//         // Ensure password length is exactly 10
//         return adjustPasswordLength(passwordBuilder.toString(), 10);
//     }

//     private String adjustPasswordLength(String password, int targetLength) {
//         // If the password is shorter than the target length, concatenate '12334...' until it's long enough
//         while (password.length() < targetLength) {
//             password += "12334"; // You can change this pattern if needed
//         }

//         // If the password is longer than the target length, trim it
//         if (password.length() > targetLength) {
//             password = password.substring(0, targetLength);
//         }

//         return password;
//     }
// }


// package com.keanable.service;

// import org.mindrot.jbcrypt.BCrypt;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.keanable.model.User;
// import com.keanable.repository.UserRepository;
// import org.springframework.transaction.annotation.Transactional;


// @Service
// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     public String registerUser(String username) {
//         if (userRepository.findByUsername(username) != null) {
//             throw new RuntimeException("Username already exists.");
//         }

//         String password = generatePassword(username);
//         String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

//         User newUser = new User(username, hashedPassword);
//         userRepository.save(newUser);  // Save the new user to the database

//         return password;  // Return generated password
//     }

//     public void updateUser(String username, String newPassword) {
//         User user = userRepository.findByUsername(username);
//         if (user == null) {
//             throw new RuntimeException("User not found.");
//         }

//         String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
//         user.setPassword(hashedPassword);
//         userRepository.save(user);  // Update the user in the database
//     }

//     @Transactional
//     public void deleteUser(String username) {
//         User user = userRepository.findByUsername(username);
//         if (user == null) {
//             throw new RuntimeException("User not found.");
//         }

//         userRepository.delete(user); // Delete the user from the database
//     }

//     public String retrieveUsernameByPassword(String password) {
//         for (User user : userRepository.findAll()) {
//             if (BCrypt.checkpw(password, user.getPassword())) {
//                 return user.getUsername();
//             }
//         }
//         throw new RuntimeException("Invalid password.");
//     }

//     private String generatePassword(String username) {
//         StringBuilder passwordBuilder = new StringBuilder();
//         int sum = 0;

//         // Iterate over each character in the username
//         for (char ch : username.toCharArray()) {
//             // Calculate the alphabetical position (A=1, B=2, ..., Z=26)
//             int position = Character.toUpperCase(ch) - 'A' + 1;
//             passwordBuilder.append(position);
//             sum += position; // Add position to sum
//         }

//         // Add the sum of the positions at the end of the password
//         passwordBuilder.append(sum);

//         // Ensure password length is exactly 10
//         return adjustPasswordLength(passwordBuilder.toString(), 10);
//     }

//     private String adjustPasswordLength(String password, int targetLength) {
//         // If the password is shorter than the target length, concatenate '12334...' until it's long enough
//         while (password.length() < targetLength) {
//             password += "12334"; // You can change this pattern if needed
//         }

//         // If the password is longer than the target length, trim it
//         if (password.length() > targetLength) {
//             password = password.substring(0, targetLength);
//         }

//         return password;
//     }
// }
package com.keanable.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keanable.model.User;
import com.keanable.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(String username) {
        // Normalize the username to lowercase for uniqueness check
        String normalizedUsername = username.toLowerCase();
        
        if (userRepository.findByUsername(normalizedUsername) != null) {
            throw new RuntimeException("Username already exists.");
        }

        String password = generatePassword(normalizedUsername);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User(normalizedUsername, hashedPassword);
        userRepository.save(newUser);  // Save the new user to the database

        return password;  // Return generated password
    }

    public void updateUser(String username, String newPassword) {
        // Normalize the username to lowercase for the lookup
        String normalizedUsername = username.toLowerCase();
        
        User user = userRepository.findByUsername(normalizedUsername);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);  // Update the user in the database
    }

    @Transactional
    public void deleteUser(String username) {
        // Normalize the username to lowercase for the lookup
        String normalizedUsername = username.toLowerCase();
        
        User user = userRepository.findByUsername(normalizedUsername);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        userRepository.delete(user); // Delete the user from the database
    }

    public String retrieveUsernameByPassword(String password) {
        for (User user : userRepository.findAll()) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user.getUsername();
            }
        }
        throw new RuntimeException("Invalid password.");
    }

    private String generatePassword(String username) {
        StringBuilder passwordBuilder = new StringBuilder();
        int sum = 0;

        // Iterate over each character in the username
        for (char ch : username.toCharArray()) {
            // Calculate the alphabetical position (A=1, B=2, ..., Z=26)
            int position = Character.toUpperCase(ch) - 'A' + 1;
            passwordBuilder.append(position);
            sum += position; // Add position to sum
        }

        // Add the sum of the positions at the end of the password
        passwordBuilder.append(sum);

        // Ensure password length is exactly 10
        return adjustPasswordLength(passwordBuilder.toString(), 10);
    }

    private String adjustPasswordLength(String password, int targetLength) {
        // If the password is shorter than the target length, concatenate '12334...' until it's long enough
        while (password.length() < targetLength) {
            password += "12334"; // You can change this pattern if needed
        }

        // If the password is longer than the target length, trim it
        if (password.length() > targetLength) {
            password = password.substring(0, targetLength);
        }

        return password;
    }
}
