package Clases;

import Interfaces.Enviable;

import java.util.Scanner;

public class Herramienta extends NoPerecedero implements Enviable {
    private double VALOR_IVA= 0.4;
    private final double TARIFA_ENVIO = 3.71;
    private final boolean Fragil = false;
    public Herramienta(){}

    public Herramienta(int codigo, String descr, double precio, int cantidad, double peso) {
        super(codigo, descr, precio, cantidad, peso);
        setIva(VALOR_IVA);
    }

    public Herramienta(Scanner in) {
        super(in);
        setIva(VALOR_IVA);
    }

    @Override
    public boolean envioFragil() {
        return Fragil;
    }
    @Override
    public double tarifaEnvio() {
        return TARIFA_ENVIO;
    }
    public String volcar(){return super.volcar() + "Herramienta";}
    public void imprimir(){
        super.imprimir();
        System.out.println(", Herramienta");
    }
    public void imprimirEnvio(){super.imprimirEnvio();}
    public double getPeso(){
        return super.getPeso();
    }
}
