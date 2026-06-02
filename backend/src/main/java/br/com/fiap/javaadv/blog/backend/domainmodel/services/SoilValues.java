package br.com.fiap.javaadv.blog.backend.domainmodel.services;

public class SoilValues {

    private double clay;
    private double sand;
    private double silt;

    public SoilValues(double clay, double sand, double silt) {
        this.clay = clay;
        this.sand = sand;
        this.silt = silt;
    }

    public double getClay() { return clay; }
    public double getSand() { return sand; }
    public double getSilt() { return silt; }
}