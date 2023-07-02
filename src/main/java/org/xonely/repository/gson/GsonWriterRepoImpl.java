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
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return Stream.of(gson.fromJson(reader, Writer[].class)).distinct().collect(Collectors.toList());
        } catch (NullPointerException | IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Writer save(Writer writer) {
        writerList = getAll();

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
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
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
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
