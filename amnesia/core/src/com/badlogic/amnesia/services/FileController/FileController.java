package com.badlogic.amnesia.services.FileController;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class FileController {

   private FileHandle fh;

   public static String PATH = System.getProperty("user.dir") +
   "/core/src/com/badlogic/amnesia/configs/";

   public FileController(String fileName){
      this.fh = new FileHandle(fileName);
      System.out.println("current directory: " + PATH);
   }

   public String[] getFileContent(){
      Array<String> aux = new Array<String>();
      String s = fh.readString();
      String[] sSplit = s.split(",");
      while(sSplit[1].length() > 0){
         aux.add(sSplit[0]);
         sSplit = sSplit[1].split(",");
      }
      return aux.toArray();
   }

   public void copyFileContent (FileHandle fh){
      fh.copyTo(this.fh);
   }

   public void Overwrite(String content){
      this.fh.writeString(content, false);
   }

}