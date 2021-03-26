package com.dell.appliances.dto;

import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Wednesday 2/17/2021
 *
 */
public class ApplianceFilter {

    List<String> selectedModels;
    List<String> selectedUoms;
    List<String> selectedLocations;
    List<String> selectedGenerations;
    List<String> selectedPurposes;


    public List<String> getSelectedModels() {
        return selectedModels;
    }

    public void setSelectedModels(List<String> selectedModels) {
        this.selectedModels = selectedModels;
    }

    public List<String> getSelectedUoms() {
        return selectedUoms;
    }

    public void setSelectedUoms(List<String> selectedUoms) {
        this.selectedUoms = selectedUoms;
    }

    public List<String> getSelectedLocations() {
        return selectedLocations;
    }

    public void setSelectedLocations(List<String> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }

    public List<String> getSelectedGenerations() {
        return selectedGenerations;
    }

    public void setSelectedGenerations(List<String> selectedGenerations) {
        this.selectedGenerations = selectedGenerations;
    }

    public List<String> getSelectedPurposes() {
        return selectedPurposes;
    }

    public void setSelectedPurposes(List<String> selectedPurposes) {
        this.selectedPurposes = selectedPurposes;
    }

    @Override
    public String toString() {
        return "ApplianceFilter{" +
                "selectedModels=" + selectedModels +
                ", selectedUoms=" + selectedUoms +
                ", selectedLocations=" + selectedLocations +
                ", selectedGenerations=" + selectedGenerations +
                ", selectedPurposes=" + selectedPurposes +
                '}';
    }
}
