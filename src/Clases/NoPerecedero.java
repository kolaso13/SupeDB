package Clases;

import java.util.Scanner;

public abstract class NoPerecedero extends Producto{
    public NoPerecedero(){}
    public NoPerecedero(int codigo, String descr, double precio, int cantidad, double peso) {
        super(codigo, descr, precio, cantidad, peso);
    }

    public NoPerecedero(Scanner in) {
        super(in);
    }

    public void imprimir(){
        super.imprimir();
        System.out.print(" (Producto no perecedero)");
    }
}
