package com.badlogic.amnesia.services.FileController.error;

public class ReadingFileException extends Exception {
    
    public ReadingFileException (String filePath, String errorMessage) {
        super("Cannot read content of file " + filePath + ": " + errorMessage);
    }
    
}
