package com.monsupercloud.model;

public class CloudletModel {
    private String name;
    private int length;
    private int pesNumber;
    private VmModel vmParent; // Ajout du lien vers la VM

    public CloudletModel(String name, int length, int pesNumber, VmModel vmParent) {
        this.name = name;
        this.length = length;
        this.pesNumber = pesNumber;
        this.vmParent = vmParent;
    }

    public String getName() { return name; }
    public int getLength() { return length; }
    public int getPesNumber() { return pesNumber; }
    public VmModel getVmParent() { return vmParent; }
}