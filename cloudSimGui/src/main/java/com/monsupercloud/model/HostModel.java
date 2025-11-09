package com.monsupercloud.model;
public class HostModel {
    private String name;
    private int ram;
    private int mips;
    private int storage;
    public HostModel(String name, int ram, int mips, int storage) {
        this.name = name; this.ram = ram; this.mips = mips; this.storage = storage;
    }
    public String getName() { return name; }
    public int getRam() { return ram; }
    public int getMips() { return mips; }
    public int getStorage() { return storage; }
}