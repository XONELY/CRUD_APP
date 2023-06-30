package org.xonely.repository;

import org.xonely.model.Writer;

import java.util.List;

public interface WriterRepo extends GenericRepo<Writer, Integer> {

    List<Writer> getAll();
    Writer getById(Integer integer);
    Writer save(Writer writer);
    Writer update(Writer writer);
    void deleteById(Integer id);
}
