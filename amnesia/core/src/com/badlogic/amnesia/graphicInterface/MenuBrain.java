package com.badlogic.amnesia.graphicInterface;

import com.badlogic.gdx.files.FileHandle;

public class MenuBrain {

    public boolean saveExists() {
        FileHandle  fh1 = new FileHandle("SaveFile.csv"),
                    fh2 = new FileHandle("ResetSavefile.csv");
                    System.out.println("Verificando..." + (fh1.file().compareTo(fh2.file()) != 0));
        return fh1.file().compareTo(fh2.file()) != 0;
    }
}
