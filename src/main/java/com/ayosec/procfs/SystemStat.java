package com.ayosec.procfs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SystemStat {

  private int bootTime;
  private int processes;
  private int contextSwitches;
  private CPUStat totalCPU;
  private ArrayList<CPUStat> cpus;

  public SystemStat(String content) {
    this.cpus = new ArrayList<CPUStat>();

    StringTokenizer lines = new StringTokenizer(content, "\n");

    while(lines.hasMoreTokens()) {
      String line = lines.nextToken();

      if(line.startsWith("cpu")) {
        CPUStat cpu = new CPUStat(line);
        if(line.startsWith("cpu "))
          this.totalCPU = cpu;
        else
          this.cpus.add(cpu);

        continue;
      }

      if(line.startsWith("ctxt ")) {
        String[] parts = line.split("\\s");
        this.contextSwitches = Integer.parseInt(parts[1]);
        continue;
      }

      if(line.startsWith("btime ")) {
        String[] parts = line.split("\\s");
        this.bootTime = Integer.parseInt(parts[1]);
        continue;
      }

      if(line.startsWith("processes ")) {
        String[] parts = line.split("\\s");
        this.processes = Integer.parseInt(parts[1]);
        continue;
      }
    }
  }

  public SystemStat() throws IOException {
    this(FileHelper.read("/proc/stat"));
  }

  public int getBootTime() {
    return bootTime;
  }

  public int getProcesses() {
    return processes;
  }

  public int getContextSwitches() {
    return contextSwitches;
  }

  public CPUStat getTotalCPU() {
    return totalCPU;
  }

  public ArrayList<CPUStat> getCPUs() {
    return cpus;
  }

}
