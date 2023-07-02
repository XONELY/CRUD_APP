package org.xonely.controller;

import org.xonely.model.*;
import org.xonely.repository.gson.GsonLabelRepoImpl;

import java.util.List;

public class LabelController {
    GsonLabelRepoImpl labelRepo = new GsonLabelRepoImpl();

    public Label add(Label label) {
        labelRepo.save(label);
        return label;
    }

    public List<Label> getAll() {
        return labelRepo.getAll();
    }

    public Label get(Integer id) {
        return getAll().get(id);
    }

    public Label statusUpdate(int choice, Label label) {
        if (choice == 1) {
            label.setStatus(Status.ACTIVE);

        } else if (choice == 2) {
            label.setStatus(Status.DELETED);

        } else {
            label.setStatus(Status.UNDER_REVIEW);

        }
        labelRepo.update(label);
        return label;
    }

    public void deletebyId(int id) {
        labelRepo.deleteById(id);

    }

}
