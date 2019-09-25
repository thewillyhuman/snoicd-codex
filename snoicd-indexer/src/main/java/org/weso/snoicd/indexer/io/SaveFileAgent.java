package org.weso.snoicd.indexer.io;

import io.thewilly.bigtable.BigTable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFileAgent extends Thread {

    private String filename;
    private BigTable indexToSave;

    public SaveFileAgent(String filename, BigTable indexToSave) {
        this.filename = filename;
        this.indexToSave = indexToSave;
    }

    @Override
    public void run() {
        try {
            FileOutputStream fos = new FileOutputStream( filename );
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.reset();
            oos.writeObject( indexToSave.getMemoryMap() );
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
