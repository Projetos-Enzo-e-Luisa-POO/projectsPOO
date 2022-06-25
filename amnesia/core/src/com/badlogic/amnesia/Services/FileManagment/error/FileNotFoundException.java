package com.badlogic.amnesia.Services.FileManagment.error;

public class FileNotFoundException extends Exception {
    public FileNotFoundException (String filePath) {
        super("Cannot find file with path: " + filePath);
    }
}
