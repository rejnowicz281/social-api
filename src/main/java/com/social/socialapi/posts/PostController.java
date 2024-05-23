package com.social.socialapi.posts;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "{id}")
    public Post getPost(@PathVariable("id") Integer id) {
        return postService.getPost(id);
    }

    @PostMapping
    public void createPost(@RequestBody Post post) {
        postService.createPost(post.getBody());
    }

    @PutMapping(path = "{id}")
    public void updatePost(@PathVariable("id") Integer id, @RequestBody Post post) {
        postService.updatePost(id, post.getBody());
    }

    @DeleteMapping(path = "{id}")
    public void deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
    }
}
