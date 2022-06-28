package com.badlogic.amnesia.Services.FileManagment.error;

import com.badlogic.gdx.utils.GdxRuntimeException;

public class CopyFileException extends GdxRuntimeException {

    public CopyFileException (String originFilePath, String destinationFilePath, String errorMessage) {
        super("Cannot copy content from file " + originFilePath + " to " + destinationFilePath + ": " + errorMessage);
    }
    
}
