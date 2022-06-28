package com.badlogic.amnesia.Services.FileManagment.error;

import com.badlogic.gdx.utils.GdxRuntimeException;

public class ReadingFileException extends GdxRuntimeException {
    
    public ReadingFileException (String filePath, String errorMessage) {
        super("Cannot find or read file " + filePath + ": " + errorMessage);
    }

}
