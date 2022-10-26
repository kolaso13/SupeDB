package Clases;

import java.util.Scanner;

public class Otros extends NoPerecedero{
    private String categoria;
    private double VALOR_IVA = 0.16;
    public Otros(){}

    public Otros(int codigo, String descr, double precio, int cantidad, double peso, String Categoria) {
        super(codigo, descr, precio, cantidad, peso);
        this.categoria = Categoria;
        setIva(VALOR_IVA);
    }

    public Otros(Scanner in) {
        super(in);
        System.out.println("Introduce la categoría:");
        while(true){
            try{
                categoria = String.valueOf(in.nextInt());
                break;
            }catch(Exception e){
                System.out.println("La categoria introducida es erronea");
                in.nextLine();
            }
        }
        setIva(VALOR_IVA);
    }

    public String volcar(){return super.volcar() + categoria + " Otros";}
    public void imprimir(){
        super.imprimir();
        System.out.println(", Categoría: "+ categoria);
    }
}
