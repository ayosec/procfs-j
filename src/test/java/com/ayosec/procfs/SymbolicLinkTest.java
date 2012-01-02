package com.ayosec.procfs;

import java.io.File;
import java.util.Locale;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SymbolicLinkTest extends TestCase
{

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() { return new TestSuite( SymbolicLinkTest.class ); }

  /**
   * Create a symbolic link and check if the content is the expected
   */
  public void testLink() throws Exception
  {
    File newLink = File.createTempFile("symlink", "d");
    newLink.delete();

    // Create a symbolic link and read its value
    LibC.symlink("/home", newLink.getAbsolutePath());
    String linkValue = LibC.readlink(newLink.getAbsolutePath());

    assertEquals("/home", linkValue);
  }

  /**
   * Check if the error is correctly set with readlink
   */
  public void testErrnoWithReadLink() {
    boolean exceptionBlockWasSeen = false;

    LibC.Handle.module.setlocale(6, "C");

    try {
      LibC.readlink("/");
    } catch (ErrnoException error) {
      assertEquals(ErrnoException.Errors.EINVAL, error.getError());
      assertEquals("EINVAL", error.getErrorName());
      assertEquals("/", error.getArgument());
      assertEquals("Invalid argument", error.getMessage());

      exceptionBlockWasSeen = true;
    }

    assertTrue(exceptionBlockWasSeen);
  }

  /**
   * Check if the error is correctly set with symlink
   */
  public void testErrnoWithSymLink() {
    int exceptions = 0;

    char[] charsName = new char[10000];
    java.util.Arrays.fill(charsName, 'a');
    String longName = new String(charsName);

    LibC.Handle.module.setlocale(6, "C");

    try {
      LibC.symlink("/", longName);
    } catch (ErrnoException error) {
      assertEquals(ErrnoException.Errors.ENAMETOOLONG, error.getError());
      assertEquals("ENAMETOOLONG", error.getErrorName());
      assertEquals("/:" + longName, error.getArgument());
      assertEquals("File name too long", error.getMessage());
      exceptions++;
    }

    try {
      LibC.symlink("/tmp", "/tmp");
    } catch (ErrnoException error) {
      assertEquals(ErrnoException.Errors.EEXIST, error.getError());
      assertEquals("EEXIST", error.getErrorName());
      assertEquals("/tmp:/tmp", error.getArgument());
      assertEquals("File exists", error.getMessage());
      exceptions++;
    }

    assertEquals(2, exceptions);
  }
}
