package org.xonely.controller;

import org.xonely.model.*;
import org.xonely.repository.gson.GsonWriterRepoImpl;

import java.util.*;

public class WriterController {
    private final GsonWriterRepoImpl writerRepo = new GsonWriterRepoImpl();

    public Writer add(Writer writer) {
        writerRepo.save(writer);
        return writer;
    }

    public List<Writer> getAll() {
        return writerRepo.getAll();
    }

    public Writer get(Integer id) {
        return getAll().get(id);
    }

    public void updateWriter(Writer writer) {///////////
        writerRepo.update(writer);
    }

    public void statusUpdate(int choice, Writer writer) {
        if (choice == 1) {
            writer.setStatus(Status.ACTIVE);
        } else if (choice == 2) {
            writer.setStatus(Status.DELETED);
        } else {
            writer.setStatus(Status.UNDER_REVIEW);
        }
        writerRepo.update(writer);
    }

    public void deletebyId(int id) {
        writerRepo.deleteById(id);
    }


}
