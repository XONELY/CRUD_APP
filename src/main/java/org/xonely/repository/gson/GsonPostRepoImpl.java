package org.xonely.repository.gson;

import com.google.gson.*;
import org.xonely.model.Post;
import org.xonely.repository.PostRepo;


import java.io.*;
import java.util.*;
import java.util.stream.*;

import static org.xonely.model.Status.DELETED;

public class GsonPostRepoImpl implements PostRepo {
    List<Post> postList;
    private final String path = "posts.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Post> getAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return Stream.of(gson.fromJson(reader, org.xonely.model.Post[].class)).distinct().collect(Collectors.toList());
        } catch (NullPointerException | IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Post save(Post post) {
        postList = getAll();

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
            postList.add(post);
            String json = gson.toJson(postList);
            wr.write(json);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Post update(Post post) {
        postList = getAll();
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
            int index = postList.indexOf(post);
            postList.set(index, post);
            String json = gson.toJson(postList);
            wr.write(json);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Post getById(Integer id) {
        return getAll().get(id);
    }

    @Override
    public void deleteById(Integer id) {
        Post post = getById(id);
        post.setStatus(DELETED);
        update(post);

    }
}
