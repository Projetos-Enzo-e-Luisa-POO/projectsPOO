package com.badlogic.amnesia.Services.FileManagment.error;

import com.badlogic.gdx.utils.GdxRuntimeException;

public class WritingFileException extends GdxRuntimeException {

    public WritingFileException (String filePath, String errorMessage) {
        super("Cannot find or write in file " + filePath + ": " + errorMessage);
    }

}
