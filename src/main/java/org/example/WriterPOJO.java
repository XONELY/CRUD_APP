package org.example;

import java.io.Serializable;
import java.util.*;


public class WriterPOJO implements Serializable {

    private int id = 0;


    private String firstName;
    private String lastName;
    private final List<PostPOJO> posts;


    public WriterPOJO(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        posts = new ArrayList<>();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PostPOJO> getPosts() {
        return posts;
    }


    public void addPost(PostPOJO post) {
        posts.add(post);
    }


    @Override
    public String toString() {
        return
                "id= " + id + "| " + lastName +
                        " " + firstName +
                        ", Посты: " + posts;
    }
}
