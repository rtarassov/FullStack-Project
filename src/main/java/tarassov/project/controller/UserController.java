package tarassov.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarassov.project.dto.UserDTO;
import tarassov.project.dto.UserStorageDTO;
import tarassov.project.model.User;
import tarassov.project.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping({"id"})
    public ResponseEntity<User> findStorageById(@PathVariable("id") Long id) {
        var user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        var id = userService.saveUserToDB(userDTO);
        return ResponseEntity.created(URI.create("/user/%d"
                .formatted(id)))
                .body(userDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long id,
                                            @RequestBody UserDTO userDTO) {
        var userId = userService.updateUserById(id, userDTO);

        return ResponseEntity
                .created(URI.create("/user/update/%d"
                .formatted(userId)))
                .body(userDTO);
    }

    @PutMapping("/storage-to-user")
    public ResponseEntity<Void> addStorageToUser(@RequestBody UserStorageDTO userStorageDTO) {
        userService.assignStorageToUser(userStorageDTO);
        return ResponseEntity.ok().build();
    }
}
