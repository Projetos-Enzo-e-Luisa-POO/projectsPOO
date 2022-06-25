package com.badlogic.amnesia.Services.FileManagment;

import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.utils.Array;

public class FileController {

   private FileHandle fh;

   public static String PATH = System.getProperty("user.dir") +
   "/core/src/com/badlogic/amnesia/ConfigFiles/";

   public FileController(String fileName){
      this.fh = new FileHandle(fileName);
      System.out.println("current directory: " + PATH);
   }

   public String[] getFileContent(){
      String content = fh.readString();
      String[] contentSplitted = content.split(",");
      /* while(sSplit[1].length() > 0){
         aux.add(sSplit[0]);
         sSplit = sSplit[1].split(",");
      }
      return aux.toArray(); */
      return contentSplitted;
   }

   public void copyFileContent (FileHandle fh){
      fh.copyTo(this.fh);
   }

   public void Overwrite(String content){
      this.fh.writeString(content, false);
   }

}