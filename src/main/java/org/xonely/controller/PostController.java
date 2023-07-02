package org.xonely.controller;

import org.xonely.model.*;
import org.xonely.repository.gson.GsonPostRepoImpl;

import java.util.List;

public class PostController {
    GsonPostRepoImpl postRepo = new GsonPostRepoImpl();

    public Post add(Post post) {
        postRepo.save(post);
        return post;
    }

    public List<Post> getAll() {
        return postRepo.getAll();
    }

    public Post get(Integer id) {
        return getAll().get(id);
    }

    public void updatePost(Post post, List<String> content, String updateDate) {//////
        post.updateContent(content);
        post.updateDate(updateDate);
        postRepo.update(post);
    }

    public void updatePost(Post post) {//////
        postRepo.update(post);
    }

    public void statusUpdate(int choice, Post post) {
        if (choice == 1) {
            post.setStatus(Status.ACTIVE);
        } else if (choice == 2) {
            post.setStatus(Status.DELETED);
        } else {
            post.setStatus(Status.UNDER_REVIEW);
        }
        postRepo.update(post);
    }

    public void deletebyId(int id) {
        postRepo.deleteById(id);
    }
}
