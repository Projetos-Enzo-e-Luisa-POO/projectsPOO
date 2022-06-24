package com.badlogic.amnesia.graphicInterface;

import com.badlogic.amnesia.services.FileManagment.FileController;
import com.badlogic.gdx.files.FileHandle;

public class MenuBrain {

    public boolean saveExists() {
        FileHandle  fh1 = new FileHandle("SaveFile.csv"),
                    fh2 = new FileHandle("ResetSavefile.csv");
        return fh1.file().compareTo(fh2.file()) != 0;
    }

    public void OverwriteSaveFile() {
        FileController  fc1 = new FileController("SaveFile.csv"),
                        fc2 = new FileController("ResetSavefile.csv");
        fc1.Overwrite(fc2.toString());
    }

    public void setLoading(String fileName, GameControll gc){
        //GameControll.setLoadingString(fileName); < Implementar
    }

}
