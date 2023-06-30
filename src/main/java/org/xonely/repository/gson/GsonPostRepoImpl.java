package org.xonely.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.xonely.model.Post;
import org.xonely.repository.PostRepo;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.xonely.model.Status.DELETED;

public class GsonPostRepoImpl implements PostRepo {
    private List<Post> posts;
    private final String path = "posts.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public GsonPostRepoImpl() {
        File file = new File(path);

    }
    @Override
    public List<Post> getAll() {

        try (InputStream is = new FileInputStream(path); Reader reader = new InputStreamReader(is)) {
            posts = Stream.of(gson.fromJson(reader, Post[].class)).toList();
            if(posts ==null){
                return new ArrayList<>();
            }else{
            return posts;}
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch(NullPointerException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Post getById(Integer id) {
        return getAll().get(id);
    }

    @Override
    public Post save(Post post) {
        try (OutputStream os = new FileOutputStream(path, false); Writer wr = new OutputStreamWriter(os)) {
            posts = getAll();
            if (posts.stream().noneMatch(streamW -> streamW.equals(post))) {
                posts.add(post);
                String json = gson.toJson(posts);
                wr.write(json);
            } else {
                System.err.println("Такой пост уже существует");
            }
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Post update(Post post) {
        try (OutputStream os = new FileOutputStream(path, false); Writer wr = new OutputStreamWriter(os)) {
            posts = getAll();
            int index = posts.indexOf(post);
            posts.set(index, post);
            String json = gson.toJson(posts);
            wr.write(json);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        posts.get(id).setStatus(DELETED);
    }

}
