package com.social.socialapi.posts;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.social.socialapi.user.User;
import com.social.socialapi.user.UserRepository;
import com.social.socialapi.utils.authUtils;

import io.jsonwebtoken.security.SecurityException;
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

    public void createPost(String body) {
        Post post = new Post(body);
        String userEmail = authUtils.getAuthenticatedUser().getUsername();
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        post.setUser(user);

        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Integer id, String body) {
        Post post = postRepository.findById(id).orElseThrow();

        UserDetails user = authUtils.getAuthenticatedUser();

        if (!post.getUser().getEmail().equals(user.getUsername())) {
            throw new SecurityException("You can't update this post");
        }

        if (body != null) {
            post.setBody(body);
        }

        postRepository.save(post);
    }

    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow();

        UserDetails user = authUtils.getAuthenticatedUser();

        if (!post.getUser().getEmail().equals(user.getUsername())) {
            throw new SecurityException("You can't update this post");
        }

        postRepository.deleteById(id);
    }
}
