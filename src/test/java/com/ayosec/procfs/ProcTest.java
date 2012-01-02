package com.ayosec.procfs;

import java.math.BigInteger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.concurrent.CountDownLatch;
import java.io.File;

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

  public void testSendSignals() throws Exception {
    // This test depends on the netcat package (http://packages.debian.org/netcat)
    try {
      Runtime.getRuntime().exec("nc -l -p 65200");
    } catch(java.io.IOException e) {
      // if nc is not present, just skip this test
      return;
    }

    Proc nc = TCPInfo.processOnPort(65200);
    assertEquals("nc", nc.getStat().getCommand());

    // Send SIGKILL and wait for the process termination
    nc.sendSignal(9);
    Thread.sleep(50);

    assertTrue(TCPInfo.processOnPort(65200) == null);
  }

  public void testSignalsExceptions() throws Exception {
    LibC.Handle.module.setlocale(6, "C");

    int exceptions = 0;
    int pid = LibC.Handle.module.getpid();
    Proc self = new Proc(pid);

    // Invalid signal number
    try {
      self.sendSignal(10000);
    } catch (ErrnoException error) {
      assertEquals(ErrnoException.Errors.EINVAL, error.getError());
      assertEquals("EINVAL", error.getErrorName());
      assertEquals("Signal 10000 to process " + pid, error.getArgument());
      assertEquals("Invalid argument", error.getMessage());
      exceptions++;
    }

    // Invalid PID
    pid = 100;
    while(pid < 10000) {
      File possibleDir = new File("/proc/" + pid);
      if(!possibleDir.isDirectory())
        break;

      pid++;
    }

    Proc noProc = new Proc(pid);

    try {
      // SIGUSR1 to an non-existent process
      noProc.sendSignal(30);
    } catch (ErrnoException error) {
      assertEquals(ErrnoException.Errors.ESRCH, error.getError());
      assertEquals("ESRCH", error.getErrorName());
      assertEquals("Signal 30 to process " + pid, error.getArgument());
      assertEquals("No such process", error.getMessage());
      exceptions++;
    }


    assertEquals(2, exceptions);
  }

}
