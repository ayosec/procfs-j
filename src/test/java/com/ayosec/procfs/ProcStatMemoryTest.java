package com.ayosec.procfs;

import java.math.BigInteger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProcStatMemoryTest extends TestCase {

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() { return new TestSuite( ProcStatMemoryTest.class ); }

  /**
   * Parse a basic stat line
   */
  public void testStatLine() throws Exception {

    long ps = LibC.Handle.module.getpagesize();
    String sample = "1395 146 123 12 0 79 0";

    ProcStatMemory stat = new ProcStatMemory(sample);

    assertEquals(1395 * ps, stat.getTotalSize());
    assertEquals(146 * ps, stat.getResident());
    assertEquals(123 * ps, stat.getShare());
    assertEquals(12 * ps, stat.getCodeSize());
    assertEquals(79 * ps, stat.getDataSize());
  }

}
