package org.xonely.repository;

import org.xonely.model.Post;

import java.util.List;

public interface PostRepo extends GenericRepo<Post,Integer>{
    List<Post> getAll();
    Post getById(Integer integer);
    Post save(Post postPOJO);
    Post update(Post postPOJO);
    void deleteById(Integer integer);
}
