package com.ayosec.procfs;

import java.math.BigInteger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.concurrent.CountDownLatch;

public class ProcTest extends TestCase {

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() { return new TestSuite( ProcTest.class ); }

  public void testStat() throws Exception {
    Proc self = new Proc(LibC.Handle.module.getpid());
    ProcStat stat = self.getStat();

    // Some basic checks
    assertEquals(LibC.Handle.module.getpid(), stat.getPID());
    assertTrue(stat.getUtime() > 0);
  }

  public void testStatMemory() throws Exception {
    Proc self = new Proc(LibC.Handle.module.getpid());
    ProcStatMemory statm = self.getStatMemory();

    // Some basic checks
    assertTrue(statm.getTotalSize() > 0);
    assertTrue(statm.getResident() > 0);
    assertTrue(statm.getShare() > 0);
    assertTrue(statm.getCodeSize() > 0);
    assertTrue(statm.getDataSize() > 0);

  }

  public void testCPUUsage() throws Exception {
    Proc self = new Proc(LibC.Handle.module.getpid());

    int availableProcs = Runtime.getRuntime().availableProcessors();
    final CountDownLatch latch = new CountDownLatch(availableProcs);

    // First call will return 0, since there is no previous data
    assertEquals(0d, self.getCPUUsage());

    // Burn the CPU 200 milliseconds
    final long start = System.currentTimeMillis();
    Runnable oven = new Runnable() {
      public void run() {
        while((System.currentTimeMillis() - start) < 200)
          /* nop */ ;

        latch.countDown();
      }
    };

    for(int i = 0; i < availableProcs; i++)
      new Thread(oven).start();

    latch.await();
    assertTrue(self.getCPUUsage() > 90);
  }

}
