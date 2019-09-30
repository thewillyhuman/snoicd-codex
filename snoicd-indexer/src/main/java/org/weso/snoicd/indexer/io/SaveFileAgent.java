package org.weso.snoicd.indexer.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thewilly.bigtable.BigTable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Agent for saving / writing files. It creates a thread where the given index is saved.
 */
public class SaveFileAgent extends Thread {

    private String filename;
    private BigTable indexToSave;

    /**
     * @param filename    is the path were we want to store the index file.
     *                    Usually this will be the same place where the search
     *                    module will look for the indexes.
     * @param indexToSave is the data structure that we want to persist in the given location.
     */
    public SaveFileAgent(String filename, BigTable indexToSave) {
        this.filename = filename;
        this.indexToSave = indexToSave;
    }

    @Override
    public void run() {
        /*try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.reset();
            oos.writeObject(indexToSave.getMemoryMap());
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(this.filename), indexToSave.getMemoryMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
