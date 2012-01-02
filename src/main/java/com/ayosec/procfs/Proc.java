package com.ayosec.procfs;

import com.sun.jna.Native;

public class Proc {
  private int pid;
  private ProcStat lastProcStat;
  private CPUStat lastCPUStat;

  public Proc(int pid) {
    this.pid = pid;
    this.lastProcStat = null;
    this.lastCPUStat = null;
  }

  /**
   * Return the PID of the process related to this Proc instance.
   */
  public int getPID() {
    return pid;
  }

  /**
   * Memory stats for the process.
   *
   * @return a new instance of a ProcStat.
   */
  public ProcStat getStat() throws java.io.IOException, ProcStat.InvalidStatFormat {
    return new ProcStat(FileHelper.read("/proc/" + this.pid + "/stat"));
  }

  /**
   * Memory stats for the process.
   *
   * @return a new instance of a ProcStatMemory.
   */
  public ProcStatMemory getStatMemory() throws java.io.IOException {
    return new ProcStatMemory(FileHelper.read("/proc/" + this.pid + "/statm"));
  }

  /**
   * Computes the CPU usage of the process.
   *
   * The first time this method is called the result will be 0.0, since there is
   * no previous data. Next times the value will be the percentage of the tim
   * consumed in this process since the last call.
   *
   * A negative value will be returned when the value can not be computed.
   */
  public double getCPUUsage() {
    ProcStat procStat;
    CPUStat cpuStat;

    try {
      procStat = this.getStat();
      cpuStat = new SystemStat().getTotalCPU();
    } catch (ProcStat.InvalidStatFormat e) {
      // Ignore
      return -1;
    } catch (java.io.IOException e) {
      // Ignore
      return -2;
    }

    double cpuUsage = 0d;

    if(this.lastCPUStat != null && this.lastProcStat != null) {
      cpuUsage =
        ( 100d * (
           (procStat.getUtime() + procStat.getStime()) -
           (this.lastProcStat.getUtime() + this.lastProcStat.getStime())
        )) / (cpuStat.getTotalTime() - this.lastCPUStat.getTotalTime());
    }

    this.lastCPUStat = cpuStat;
    this.lastProcStat = procStat;

    return cpuUsage;
  }

  /**
   * Send a signal to the associated process.
   *
   * This method is a wrapper for
   * <a href="http://kernel.org/doc/man-pages/online/pages/man2/kill.2.html">kill(2)</a>
   *
   * @param signal Signal to send
   */
  public void sendSignal(int signal) throws ErrnoException {
    int ret = LibC.Handle.module.kill(pid, signal);

    if(ret != 0)
      throw new ErrnoException(Native.getLastError(), "Signal " + signal + " to process " + pid);
  }
}
