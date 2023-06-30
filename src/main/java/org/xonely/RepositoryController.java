package org.xonely;

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class RepositoryController {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveToJson(String path) {
        try (OutputStream os = new FileOutputStream(path, false); Writer writer = new OutputStreamWriter(os)) {
            String json;
            if (path.equals(WRITERS_FILE)) {
                json = gson.toJson(WriterController.getWriters());
                writer.write(json);
            } else if (path.equals(POSTS_FILE)) {
                json = gson.toJson(WriterController.getPostList());
                writer.write(json);
            } else {
                json = gson.toJson(WriterController.getLabels());
                writer.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static List<WriterPOJO> loadWriterJson() {

        try (InputStream is = new FileInputStream(WRITERS_FILE); Reader reader = new InputStreamReader(is)) {
            List<WriterPOJO> wp;


            wp = Arrays.stream(gson.fromJson(reader, WriterPOJO[].class)).toList();

            return wp;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}




