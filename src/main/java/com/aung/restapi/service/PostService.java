package com.aung.restapi.service;

import com.aung.restapi.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {
        private final WebClient webClient;

        @Autowired
        public PostService(WebClient.Builder webClientBuilder, @Value("${vault.baseUrl}") String baseUrl) {
            this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        }

        public Flux<Post> getAllPosts() {
            return this.webClient.get()
                    .uri("/posts")
                    .retrieve()
                    .bodyToFlux(Post.class);
        }

        public Mono<Post> getPostById(int id) {
            return this.webClient.get()
                    .uri("/posts/{id}", id)
                    .retrieve()
                    .bodyToMono(Post.class);
        }

        public Mono<Post> createPost(Post user) {
            return this.webClient.post()
                    .uri("/posts")
                    .body(Mono.just(user), Post.class)
                    .retrieve()
                    .bodyToMono(Post.class);
        }

        public Mono<Post> updatePost(int id, Post user) {
            return this.webClient.put()
                    .uri("/posts/{id}", id)
                    .body(Mono.just(user), Post.class)
                    .retrieve()
                    .bodyToMono(Post.class);
        }

        public Mono<Void> deletePost(int id) {
            return this.webClient.delete()
                    .uri("/posts/{id}", id)
                    .retrieve()
                    .bodyToMono(Void.class);
        }
}
