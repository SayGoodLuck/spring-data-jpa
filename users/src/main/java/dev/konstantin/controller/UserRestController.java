package dev.konstantin.controller;

import dev.konstantin.entity.UserInfo;
import dev.konstantin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    //todo заменить UserInfo на другой тип, потому что поля gender и birthday == нулю
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> saveUser(@RequestBody @Valid UserInfo userInfo) {
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        userService.save(userInfo);
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> updateUser(UserInfo userInfo) {
        if (userInfo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.update(userInfo);
        return ResponseEntity.ok(userInfo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") String id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> getUser(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UserInfo foundUser = userService.findById(id);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
