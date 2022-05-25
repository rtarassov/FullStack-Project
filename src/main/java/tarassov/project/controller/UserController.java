package tarassov.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarassov.project.dto.UserRequest;
import tarassov.project.model.User;
import tarassov.project.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<User> findAllUsers() {
        log.info("findAllUsers() called from [{}]", UserController.class);
        return userService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserRequest> saveUser(@RequestBody UserRequest userRequest) {
        log.info("Trying to save user: [{}]", userRequest);
        var id = userService.saveUserToDB(userRequest);
        return ResponseEntity.created(URI.create("/user/%d"
                .formatted(id)))
                .body(userRequest);
    }
}
