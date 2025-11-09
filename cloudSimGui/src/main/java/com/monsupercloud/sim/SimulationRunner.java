package com.monsupercloud.sim;

import com.monsupercloud.model.*;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple; // ✅ IMPORT AJOUTÉ
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic;

import java.util.ArrayList;
import java.util.List;

public class SimulationRunner {
    public static List<Cloudlet> runSimulation(
            List<DatacenterModel> datacenterModels,
            List<HostModel> hostModels,
            List<VmModel> vmModels,
            List<CloudletModel> cloudletModels
    ) {
        CloudSim simulation = new CloudSim();

        // Créer le datacenter (avec hosts)
        DatacenterSimple datacenter = createDatacenter(simulation, hostModels);

        // Créer le broker
        DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);

        // Créer les VMs
        List<Vm> vms = new ArrayList<>();
        for (VmModel vmModel : vmModels) {
            // Par défaut 1 PE par VM, à adapter si besoin
            Vm vm = new VmSimple(
                    vmModel.getMips(),
                    1
            );
            vm.setRam(vmModel.getRam()).setBw(1000).setSize(vmModel.getStorage());
            vms.add(vm);
        }

        // Créer les cloudlets
        List<Cloudlet> cloudlets = new ArrayList<>();
        for (CloudletModel clModel : cloudletModels) {
            Cloudlet cloudlet = new CloudletSimple(
                    clModel.getLength(),
                    clModel.getPesNumber()
            );
            cloudlet.setUtilizationModelCpu(new UtilizationModelDynamic(0.7));
            cloudlet.setUtilizationModelRam(new UtilizationModelDynamic(0.3));
            cloudlet.setUtilizationModelBw(new UtilizationModelDynamic(0.3));
            cloudlets.add(cloudlet);
        }

        // Soumettre au broker
        broker.submitVmList(vms);
        broker.submitCloudletList(cloudlets);

        // Lancer la simulation
        simulation.start();

        // Retourner la liste des cloudlets terminés
        return broker.getCloudletFinishedList();
    }

    private static DatacenterSimple createDatacenter(CloudSim simulation, List<HostModel> hostModels) {
        List<Host> hosts = new ArrayList<>();
        for (HostModel model : hostModels) {
            // Nombre de PE par Host — à adapter selon ton besoin !
            int nbPes = 4;
            List<Pe> peList = new ArrayList<>();
            for (int i = 0; i < nbPes; i++) {
                // ✅ CORRECTION : Utiliser PeSimple au lieu de Pe
                peList.add(new PeSimple(model.getMips()));
            }
            Host host = new HostSimple(
                    model.getRam(), 10000, model.getStorage(), peList
            );
            hosts.add(host);
        }
        return new DatacenterSimple(simulation, hosts, new VmAllocationPolicySimple());
    }
}