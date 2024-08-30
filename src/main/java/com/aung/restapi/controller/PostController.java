package com.aung.restapi.controller;

import com.aung.restapi.model.Post;
import com.aung.restapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PostController {

        private final PostService postService;

        @Autowired
        public PostController(PostService postService) {
            this.postService = postService;
        }

        @GetMapping("/posts")
        public Flux<Post> getAllPosts() {
            return postService.getAllPosts();
        }

        @GetMapping("/posts/{id}")
        public Mono<Post> getPostById(@PathVariable int id) {
            return postService.getPostById(id);
        }

        @PostMapping("/posts")
        public Mono<Post> createPost(@RequestBody Post user) {
            return postService.createPost(user);
        }

        @PutMapping("/posts/{id}")
        public Mono<Post> updatePost(@PathVariable int id, @RequestBody Post user) {
            return postService.updatePost(id, user);
        }

        @DeleteMapping("/posts/{id}")
        public Mono<Void> deletePost(@PathVariable int id) {
            return postService.deletePost(id);
        }
}
