package org.xonely.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.xonely.model.Label;
import org.xonely.repository.LabelRepo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.xonely.model.Status.DELETED;

public class GsonLabelRepoImpl implements LabelRepo {
    private List<Label> labels;
    private final String path = "labels.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public GsonLabelRepoImpl() {
        File file = new File(path);
    }
    @Override
    public List<Label> getAll() {

        try (InputStream is = new FileInputStream(path); Reader reader = new InputStreamReader(is)) {
            labels = Stream.of(gson.fromJson(reader, Label[].class)).toList();
            if (labels == null) {
                return new ArrayList<>();
            } else {
                return labels;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch(NullPointerException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Label getById(Integer id) {
        return getAll().get(id);
    }

    @Override
    public Label save(Label label) {
        try (OutputStream os = new FileOutputStream(path, false); Writer wr = new OutputStreamWriter(os)) {
            labels = getAll();
            if (labels.stream().noneMatch(streamW -> streamW.equals(label))) {
                labels.add(label);
                String json = gson.toJson(labels);
                wr.write(json);
            } else {
                System.err.println("Такой тэг уже существует");
            }
            return label;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label update(Label label) {
        try (OutputStream os = new FileOutputStream(path, false); Writer wr = new OutputStreamWriter(os)) {
            labels = getAll();
            int index = labels.indexOf(label);
            labels.set(index, label);
            String json = gson.toJson(labels);
            wr.write(json);
            return label;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Integer id) {
        labels.get(id).setStatus(DELETED);
    }
}
