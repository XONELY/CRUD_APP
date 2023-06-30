package org.xonely.repository;

import org.xonely.model.Label;

import java.util.List;

public interface LabelRepo extends GenericRepo<Label, Integer> {
    List<Label> getAll();

    Label getById(Integer id);

    Label save(Label label);

    Label update(Label label);

    void deleteById(Integer id);
}
