package com.ayosec.procfs;

import java.util.StringTokenizer;

public class ProcStatMemory {

  private long totalSize;
  private long resident;
  private long share;
  private long codeSize;
  private long dataSize;

  public ProcStatMemory(String statLine) {
    long ps = LibC.Handle.module.getpagesize();
    StringTokenizer tokenizer = new StringTokenizer(statLine, " ");

    totalSize = Long.parseLong(tokenizer.nextToken()) * ps;
    resident  = Long.parseLong(tokenizer.nextToken()) * ps;
    share     = Long.parseLong(tokenizer.nextToken()) * ps;
    codeSize  = Long.parseLong(tokenizer.nextToken()) * ps;

    tokenizer.nextToken(); // ignore (unused in Linux 2.6)

    dataSize  = Long.parseLong(tokenizer.nextToken()) * ps;
  }

  public long getTotalSize() {
    return totalSize;
  }

  public long getResident() {
    return resident;
  }

  public long getShare() {
    return share;
  }

  public long getCodeSize() {
    return codeSize;
  }

  public long getDataSize() {
    return dataSize;
  }


}
