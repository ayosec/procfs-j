package com.ayosec.procfs;

import java.math.BigInteger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SystemStatTest extends TestCase {

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() { return new TestSuite( SystemStatTest.class ); }

  /**
   * Parse a file with data.
   */
  public void testData() {
    String sample =
      "cpu  118154 65 18125 3374274 19431 0 469 0 0 0\n" +
      "cpu0 46765 58 7528 813397 14547 0 336 0 0 0\n" +
      "cpu1 41897 0 6733 831505 2383 0 73 0 0 0\n" +
      "cpu2 18230 2 2572 860143 1641 0 50 0 0 0\n" +
      "cpu3 11260 4 1290 869228 858 0 10 0 0 0\n" +
      "intr 10802129 70 2\n" +
      "somethingtoignore 1 2 3\n" +
      "ctxt 13007159\n" +
      "btime 1324857962\n" +
      "processes 7297\n" +
      "procs_running 1\n" +
      "procs_blocked 0\n";

    SystemStat stat = new SystemStat(sample);

    // Sum of all CPUs
    assertEquals(3510618, stat.getTotalCPU().getTotalTime());
    assertEquals(118154, stat.getTotalCPU().getUserTime());
    assertEquals(65, stat.getTotalCPU().getNiceTime());
    assertEquals(18125, stat.getTotalCPU().getSystemTime());
    assertEquals(3374274, stat.getTotalCPU().getIdleTime());

    assertEquals(4, stat.getCPUs().size());

    // First CPU
    assertEquals(867748, stat.getCPUs().get(0).getTotalTime());
    assertEquals(46765, stat.getCPUs().get(0).getUserTime());
    assertEquals(58, stat.getCPUs().get(0).getNiceTime());
    assertEquals(7528, stat.getCPUs().get(0).getSystemTime());
    assertEquals(813397, stat.getCPUs().get(0).getIdleTime());

    // Last CPU
    assertEquals(881782, stat.getCPUs().get(3).getTotalTime());
    assertEquals(11260, stat.getCPUs().get(3).getUserTime());
    assertEquals(4, stat.getCPUs().get(3).getNiceTime());
    assertEquals(1290, stat.getCPUs().get(3).getSystemTime());
    assertEquals(869228, stat.getCPUs().get(3).getIdleTime());

    // Other data
    assertEquals(1324857962, stat.getBootTime());
    assertEquals(13007159, stat.getContextSwitches());
    assertEquals(7297, stat.getProcesses());
  }

  /**
   * Parse the real /proc/stat.
   * Since the data is variable and unpredictable we only test is
   * some data have been loaded.
   */
  public void testRealData() throws java.io.IOException {
    SystemStat stat = new SystemStat();

    assertTrue(stat.getTotalCPU().getTotalTime() > 0);
    assertTrue(stat.getBootTime() > 0);
    assertTrue(stat.getProcesses() > 0);
    assertTrue(stat.getContextSwitches() > 0);
  }

}
