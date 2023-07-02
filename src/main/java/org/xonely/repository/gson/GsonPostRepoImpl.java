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
        try (InputStream is = new FileInputStream(path); Reader reader = new InputStreamReader(is)) {
            return Stream.of(gson.fromJson(reader, org.xonely.model.Post[].class)).distinct().collect(Collectors.toList());
        } catch (NullPointerException | IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Post save(Post post) {
        postList = getAll();

        try (OutputStream os = new FileOutputStream(path, false); java.io.Writer wr = new OutputStreamWriter(os)) {
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
        try (OutputStream os = new FileOutputStream(path, false); java.io.Writer wr = new OutputStreamWriter(os)) {
            postList.addAll(getAll());
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
