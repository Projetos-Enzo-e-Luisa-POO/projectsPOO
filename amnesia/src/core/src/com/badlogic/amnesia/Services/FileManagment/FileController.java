package com.badlogic.amnesia.Services.FileManagment;

import com.badlogic.amnesia.Services.FileManagment.error.CopyFileException;
import com.badlogic.amnesia.Services.FileManagment.error.ReadingFileException;
import com.badlogic.amnesia.Services.FileManagment.error.WritingFileException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class FileController {

   private FileHandle fh;

   public static String PATH = "/core/src/com/badlogic/amnesia/ConfigFiles/";

   public FileController(String fileName){
      this.fh = Gdx.files.local(fileName);
   }

   public String toString() {
      try {
         return this.fh.readString();
      } catch (GdxRuntimeException e) {
         throw new ReadingFileException(fh.path(), e.getMessage());
      }
   }

   public String[] getFileContent(){
      String content;
      try{
         content = fh.readString();
      } catch (GdxRuntimeException e) {
         throw new ReadingFileException(this.fh.path(), e.getMessage());
      }
      String[] contentSplitted = content.split(",");
      return contentSplitted;
   }

   public void copyFileContent (FileHandle fh){
      try {
         fh.copyTo(this.fh);
      } catch (GdxRuntimeException e) {
         throw new CopyFileException(this.fh.path(), fh.path(), e.getMessage());
      }
   }

   public void overwrite(String content){
      try {
         this.fh.writeString(content, false);
      } catch (GdxRuntimeException e) {
         throw new WritingFileException(this.fh.path(), e.getMessage());
      }
   }

}