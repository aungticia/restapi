package com.aung.restapi.service;

import com.aung.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
        private final WebClient webClient;

        @Autowired
        public UserService(WebClient.Builder webClientBuilder) {
            this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
        }

        public Flux<User> getAllPosts() {
            return this.webClient.get()
                    .uri("/posts")
                    .retrieve()
                    .bodyToFlux(User.class);
        }

        public Mono<User> getPostById(int id) {
            return this.webClient.get()
                    .uri("/posts/{id}", id)
                    .retrieve()
                    .bodyToMono(User.class);
        }

        public Mono<User> createPost(User user) {
            return this.webClient.post()
                    .uri("/posts")
                    .body(Mono.just(user), User.class)
                    .retrieve()
                    .bodyToMono(User.class);
        }

        public Mono<User> updatePost(int id, User user) {
            return this.webClient.put()
                    .uri("/posts/{id}", id)
                    .body(Mono.just(user), User.class)
                    .retrieve()
                    .bodyToMono(User.class);
        }

        public Mono<Void> deletePost(int id) {
            return this.webClient.delete()
                    .uri("/posts/{id}", id)
                    .retrieve()
                    .bodyToMono(Void.class);
        }
}
