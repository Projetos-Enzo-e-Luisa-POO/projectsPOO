package com.badlogic.amnesia.services.FileController.error;

public class FileNotFoundException extends Exception {
    public FileNotFoundException (String filePath) {
        super("Cannot find file with path: " + filePath);
    }
}
