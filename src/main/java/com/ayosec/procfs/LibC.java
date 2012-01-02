package com.ayosec.procfs;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class LibC {

  public interface Handle extends Library {
    Handle module = (Handle) Native.loadLibrary("c", Handle.class);

    long getpagesize();

    int geteuid();
    int getuid();

    int getpid();
    int getppid();
    int kill(int pid, int signal);

    int readlink(String path, byte[] buffer, int size);
    int symlink(String oldpath, String newpath);

    String setlocale(int category, String locale);
    String strerror(int errno);
  }

  /**
   * @return the value of a symbolic link.
   *
   * The main difference with
   * {@link java.nio.file.Files.html#readSymbolicLink(java.nio.file.Path) Files.readSymbolicLink}
   * is that this method does not check if the target is valid (for example, a
   * link in <tt>/proc/N/fd/N</tt> can be the string <tt>"socket[12345]"</tt>).
   *
   * @param path The symbolic link path
   */
  public static String readlink(String path) throws ErrnoException {
    byte[] buffer = new byte[300];
    int size = LibC.Handle.module.readlink(path, buffer, 300);
    if(size > 0)
      return new String(buffer, 0, size);
    else
      throw new ErrnoException(Native.getLastError(), path);
  }

  /**
   * Create a new symbolic link.
   *
   * @param oldpath The value of the new symbolic link.
   * @param newpath The path of the new symbolic link.
   */
  public static void symlink(String oldpath, String newpath) throws ErrnoException {
    int ret = LibC.Handle.module.symlink(oldpath, newpath);

    if(ret != 0)
      throw new ErrnoException(Native.getLastError(), oldpath + ":" + newpath);
  }
}
