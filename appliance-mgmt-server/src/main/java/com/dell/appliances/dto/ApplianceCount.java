package com.dell.appliances.dto;

import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Monday 12/7/2020
 *
 */
public class ApplianceCount {

    private int totalAppliances;
    private int totalAvailableAppliances;
    private int totalReservedAppliaces;

    private int total4x00;
    private int totalAvailable4x00;
    private int totalReserved4x00;

    private int total4x00s;
    private int totalAvailable4x00s;
    private int totalReserved4x00s;

    private int total5x00;
    private int totalAvailable5x00;
    private int totalReserved5x00;

    private int total8x00;
    private int totalAvailable8x00;
    private int totalReserved8x00;

    private List<CountByLocation> countByLoc;
    private List<CountByGeneration> countByGen;

    public int getTotalAppliances() {
        return totalAppliances;
    }

    public void setTotalAppliances(int totalAppliances) {
        this.totalAppliances = totalAppliances;
    }

    public int getTotalAvailableAppliances() {
        return totalAvailableAppliances;
    }

    public void setTotalAvailableAppliances(int totalAvailableAppliances) {
        this.totalAvailableAppliances = totalAvailableAppliances;
    }

    public int getTotalReservedAppliaces() {
        return totalReservedAppliaces;
    }

    public void setTotalReservedAppliaces(int totalReservedAppliaces) {
        this.totalReservedAppliaces = totalReservedAppliaces;
    }

    public int getTotal4x00() {
        return total4x00;
    }

    public void setTotal4x00(int total4x00) {
        this.total4x00 = total4x00;
    }

    public int getTotalAvailable4x00() {
        return totalAvailable4x00;
    }

    public void setTotalAvailable4x00(int totalAvailable4x00) {
        this.totalAvailable4x00 = totalAvailable4x00;
    }

    public int getTotalReserved4x00() {
        return totalReserved4x00;
    }

    public void setTotalReserved4x00(int totalReserved4x00) {
        this.totalReserved4x00 = totalReserved4x00;
    }

    public int getTotal4x00s() {
        return total4x00s;
    }

    public void setTotal4x00s(int total4x00s) {
        this.total4x00s = total4x00s;
    }

    public int getTotalAvailable4x00s() {
        return totalAvailable4x00s;
    }

    public void setTotalAvailable4x00s(int totalAvailable4x00s) {
        this.totalAvailable4x00s = totalAvailable4x00s;
    }

    public int getTotalReserved4x00s() {
        return totalReserved4x00s;
    }

    public void setTotalReserved4x00s(int totalReserved4x00s) {
        this.totalReserved4x00s = totalReserved4x00s;
    }

    public int getTotal5x00() {
        return total5x00;
    }

    public void setTotal5x00(int total5x00) {
        this.total5x00 = total5x00;
    }

    public int getTotalAvailable5x00() {
        return totalAvailable5x00;
    }

    public void setTotalAvailable5x00(int totalAvailable5x00) {
        this.totalAvailable5x00 = totalAvailable5x00;
    }

    public int getTotalReserved5x00() {
        return totalReserved5x00;
    }

    public void setTotalReserved5x00(int totalReserved5x00) {
        this.totalReserved5x00 = totalReserved5x00;
    }

    public int getTotal8x00() {
        return total8x00;
    }

    public void setTotal8x00(int total8x00) {
        this.total8x00 = total8x00;
    }

    public int getTotalAvailable8x00() {
        return totalAvailable8x00;
    }

    public void setTotalAvailable8x00(int totalAvailable8x00) {
        this.totalAvailable8x00 = totalAvailable8x00;
    }

    public int getTotalReserved8x00() {
        return totalReserved8x00;
    }

    public void setTotalReserved8x00(int totalReserved8x00) {
        this.totalReserved8x00 = totalReserved8x00;
    }

    public List<CountByLocation> getCountByLoc() {
        return countByLoc;
    }

    public void setCountByLoc(List<CountByLocation> countByLoc) {
        this.countByLoc = countByLoc;
    }

    public List<CountByGeneration> getCountByGen() {
        return countByGen;
    }

    public void setCountByGen(List<CountByGeneration> countByGen) {
        this.countByGen = countByGen;
    }
}
