package org.xonely.repository.gson;

import com.google.gson.*;
import org.xonely.model.Writer;
import org.xonely.repository.WriterRepo;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static org.xonely.model.Status.DELETED;


public class GsonWriterRepoImpl implements WriterRepo {
    List<Writer> writerList;
    private final String path = "writers.json";
    private final Gson gson = new Gson();

    @Override
    public List<Writer> getAll() {
        try (InputStream is = new FileInputStream(path); Reader reader = new InputStreamReader(is)) {
            return Stream.of(gson.fromJson(reader, Writer[].class)).distinct().collect(Collectors.toList());
        } catch (NullPointerException | IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Writer save(Writer writer) {
        writerList = getAll();

        try (OutputStream os = new FileOutputStream(path, false); java.io.Writer wr = new OutputStreamWriter(os)) {
            writerList.add(writer);
            String json = gson.toJson(writerList);
            wr.write(json);
            return writer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Writer update(Writer writer) {
        writerList = getAll();
        try (OutputStream os = new FileOutputStream(path, false); java.io.Writer wr = new OutputStreamWriter(os)) {
            writerList.addAll(getAll());
            int index = writerList.indexOf(writer);
            writerList.set(index, writer);
            String json = gson.toJson(writerList);
            wr.write(json);
            return writer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Writer getById(Integer id) {
        return getAll().get(id);
    }

    @Override
    public void deleteById(Integer id) {
        Writer writer = getById(id);
        writer.setStatus(DELETED);
        update(writer);

    }
}
