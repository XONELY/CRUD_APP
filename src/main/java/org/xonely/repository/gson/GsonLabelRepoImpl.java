package org.xonely.repository.gson;

import com.google.gson.*;
import org.xonely.model.Label;
import org.xonely.repository.LabelRepo;

import java.io.*;

import java.util.*;
import java.util.stream.*;

import static org.xonely.model.Status.DELETED;

public class GsonLabelRepoImpl implements LabelRepo {
    private List<Label> labelList;
    private final String path = "labels.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<Label> getAll() {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return Stream.of(gson.fromJson(reader, org.xonely.model.Label[].class)).distinct().collect(Collectors.toList());
        } catch (NullPointerException | IOException e) {
            return new ArrayList<>();
        }
    }


    @Override
    public Label save(Label label) {
        labelList = getAll();

        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
            labelList.add(label);
            String json = gson.toJson(labelList);
            wr.write(json);
            return label;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label update(Label label) {
        labelList = getAll();
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
            int index = labelList.indexOf(label);
            labelList.set(index, label);
            String json = gson.toJson(labelList);
            wr.write(json);
            return label;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label getById(Integer id) {
        return getAll().get(id);
    }

    @Override
    public void deleteById(Integer id) {
        Label label = getById(id);
        label.setStatus(DELETED);
        update(label);

    }
}
