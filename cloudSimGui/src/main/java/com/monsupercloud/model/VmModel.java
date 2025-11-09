package com.monsupercloud.model;
public class VmModel {
    private String name;
    private int ram, mips, storage;
    private HostModel hostParent; // <--- lien vers le host parent

    public VmModel(String name, int ram, int mips, int storage, HostModel hostParent) {
        this.name = name; this.ram = ram; this.mips = mips; this.storage = storage;
        this.hostParent = hostParent;
    }
    public String getName() { return name; }
    public int getRam() { return ram; }
    public int getMips() { return mips; }
    public int getStorage() { return storage; }
    public HostModel getHostParent() { return hostParent; }
}