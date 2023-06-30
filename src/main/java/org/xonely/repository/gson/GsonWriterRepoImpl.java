package org.xonely.repository.gson;

import com.google.gson.*;
import org.xonely.model.Writer;
import org.xonely.repository.WriterRepo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

import static org.xonely.model.Status.DELETED;


public class GsonWriterRepoImpl implements WriterRepo {
    private List<Writer> writers = new ArrayList<>();
    private final String path = "writers.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public GsonWriterRepoImpl() {
        File file = new File(path);

    }

    @Override
    public List<Writer> getAll() {

        try (InputStream is = new FileInputStream(path); Reader reader = new InputStreamReader(is)) {
            writers = Stream.of(gson.fromJson(reader, Writer[].class)).toList();
                return writers;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (NullPointerException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Writer getById(Integer id) {
        return getAll().get(id);
    }

    @Override
    public Writer save(Writer writer) {
        try (OutputStream os = new FileOutputStream(path, false); java.io.Writer wr = new OutputStreamWriter(os)) {
            writers = getAll();
            if (writers.stream().noneMatch(streamW -> streamW.equals(writer))) {
                writers.add(writer);
                String json = gson.toJson(writers);
                wr.write(json);
            } else {
                System.err.println("Такой автор уже существует");
            }
            return writer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (OutputStream os = new FileOutputStream(path, false); java.io.Writer wr = new OutputStreamWriter(os)) {
            writers = getAll();
            int index = writers.indexOf(writer);
            writers.set(index, writer);
            String json = gson.toJson(writers);
            wr.write(json);
            return writer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        writers.get(id).setStatus(DELETED);
    }

}
