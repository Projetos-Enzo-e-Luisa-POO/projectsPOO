package com.badlogic.amnesia.services.FileManagment.error;

public class FileNotFoundException extends Exception {
    public FileNotFoundException (String filePath) {
        super("Cannot find file with path: " + filePath);
    }
}
