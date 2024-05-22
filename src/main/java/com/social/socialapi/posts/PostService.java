package com.social.socialapi.posts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.social.socialapi.user.User;
import com.social.socialapi.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    public final PostRepository postRepository;
    public final UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow();

        return post;
    }

    public void createPost(Integer user_id, String body) {
        Post post = new Post(body);
        User user = userRepository.findById(user_id).orElseThrow();
        post.setUser(user);

        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Integer id, String body) {
        Post post = postRepository.findById(id).orElseThrow();

        if (body != null) {
            post.setBody(body);
        }

        postRepository.save(post);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}
