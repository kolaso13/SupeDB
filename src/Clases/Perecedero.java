package Clases;

import Interfaces.Enviable;

import java.util.Scanner;

public abstract class Perecedero extends Producto implements Enviable {
    private String fechaad;
    private final boolean Fragil = false;
    private final double TARIFA_ENVIO = 1.71;
    public Perecedero(){}
    public Perecedero(int codigo, String descr, double precio, int cantidad, double peso, String fecha) {
        super(codigo, descr, precio, cantidad, peso);
        this.fechaad = fecha;
    }

    public Perecedero (Scanner in){
        super(in);
        System.out.println("Introduce la fecha de caducidad:");
        while(true){
            try{
                Scanner fecha = new Scanner(System.in);
                fechaad = fecha.nextLine();
                break;
            }catch(Exception e){
                System.out.println("La fecha introducida es erronea, introduce una valida");
                in.nextInt();
            }
        }
    }

    @Override
    public double tarifaEnvio() {return TARIFA_ENVIO;}

    public void imprimir(){
        super.imprimir();
        System.out.print(" (Producto perecedero: "+ fechaad+")");
    }
    public String volcar(){return super.volcar() + fechaad;}

    public void imprimirEnvio(){
        super.imprimirEnvio();
    }

    @Override
    public boolean envioFragil() {
        return Fragil;
    }
    public double getPeso(){
        return super.getPeso();
    }
}
