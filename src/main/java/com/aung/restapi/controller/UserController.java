package com.aung.restapi.controller;

import com.aung.restapi.model.User;
import com.aung.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api") // To call this on Postman POST : http://localhost:8080/api/users
public class UserController {

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/users")
        public Flux<User> getAllPosts() {
            return userService.getAllPosts();
        }

        @GetMapping("/users/{id}")
        public Mono<User> getPostById(@PathVariable int id) {
            return userService.getPostById(id);
        }

        @PostMapping("/users")
        public Mono<User> createPost(@RequestBody User user) {
            return userService.createPost(user);
        }

        @PutMapping("/users/{id}")
        public Mono<User> updatePost(@PathVariable int id, @RequestBody User user) {
            return userService.updatePost(id, user);
        }

        @DeleteMapping("/users/{id}")
        public Mono<Void> deletePost(@PathVariable int id) {
            return userService.deletePost(id);
        }
}
