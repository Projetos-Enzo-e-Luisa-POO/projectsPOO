package com.badlogic.amnesia.GraphicInterface;

import com.badlogic.amnesia.Services.FileManagment.FileController;
import com.badlogic.gdx.files.FileHandle;

public class MenuBrain {

    public boolean saveExists() {
        FileHandle  fh1 = new FileHandle("SaveFile.csv"),
                    fh2 = new FileHandle("ResetSaveFile.csv");
        return fh1.file().compareTo(fh2.file()) != 0;
    }

    public void overwriteSaveFile() {
        FileController  fc1 = new FileController("SaveFile.csv"),
                        fc2 = new FileController("ResetSaveFile.csv");
        fc1.overwrite(fc2.toString());
    }

}
