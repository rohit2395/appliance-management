package com.dell.appliances.dto;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Tuesday 1/12/2021
 *
 */
public class CountByGeneration {

    String gen;
    int count;

    public CountByGeneration(){}

    public CountByGeneration(String gen, int count) {
        this.gen = gen;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}
