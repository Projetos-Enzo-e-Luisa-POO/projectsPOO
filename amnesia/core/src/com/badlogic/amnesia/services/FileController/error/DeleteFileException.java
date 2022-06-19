package com.badlogic.amnesia.services.FileController.error;

public class DeleteFileException extends Exception {
    public DeleteFileException (String filePath, String errorMessage) {
        super("Cannot delete file " + filePath + ": " + errorMessage);
    }
}
