package com.ayosec.procfs;

import java.io.FileReader;
import java.io.IOException;

public class FileHelper {

  public static String read(String path) throws IOException {
    int read;
    char[] buffer = new char[100];

    String fileContent = "";
    FileReader statFile = new FileReader(path);

    while((read = statFile.read(buffer, 0, 100)) > 0)
      fileContent += new String(buffer, 0, read);

    statFile.close();

    return fileContent;
  }

}
