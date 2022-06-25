package com.badlogic.amnesia.Services.FileManagment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.utils.Array;

public class FileController {

   private FileHandle fh;

   public static String PATH = "/core/src/com/badlogic/amnesia/ConfigFiles/";

   public FileController(String fileName){
      this.fh = Gdx.files.local(PATH + fileName);
   }

   public String toString() {
      return this.fh.readString();
   }

   public String[] getFileContent(){
      String content = fh.readString();
      String[] contentSplitted = content.split(",");
      return contentSplitted;
   }

   public void copyFileContent (FileHandle fh){
      fh.copyTo(this.fh);
   }

   public void overwrite(String content){
      this.fh.writeString(content, false);
   }

}