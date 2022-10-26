package Clases;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Producto implements Serializable {
    private double iva;
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private double peso;


    public Producto (int codigo, String descr, double precio, int cantidad, double peso){
        this.codigo = codigo;
        this.nombre = descr;
        this.precio = precio;
        this.cantidad = cantidad;
        this.peso = peso;
    }

    public Producto(Scanner in){
        codigo = Inventario.tamaño()+1;

        //Scanner nombre
        System.out.println("Introduce el nombre:");
        while (true){
            try{

                nombre = in.nextLine();
                break;
            }catch (Exception e){
                System.out.println("El nombre introducido es erroneo, introduce uno correcto");
                in.nextLine();
            }
        }

        //Scanner precio
        System.out.println("Introduce el precio:");
        while (true){
            try{
                precio = in.nextDouble();
                break;
            }catch (Exception e){
                System.out.println("El precio introducido es erroneo, introduce uno correcto");
                in.nextLine();
            }
        }

        //Scanner cantidad
        System.out.println("Introduce la cantidad:");
        while (true){
            try{
                cantidad = in.nextInt();
                break;
            }catch (Exception e){
                System.out.println("La cantidad introducida es erronea, introduce una correcta");
                in.nextLine();
            }

        }

        //Scanner peso
        System.out.println("Introduce el peso:");
        while (true){
            try{
                peso = in.nextDouble();
                break;
            }catch (Exception e){
                System.out.println("El peso introducido es erroneo, introduce uno correcto");
                in.nextLine();
            }
        }
    }

    public Producto(){}

    public double getPeso() {return peso;}

    public double getIva() {return iva;}

    public int getCodigo() {return codigo;}

    public double getPrecio() {return precio;}

    public int getCantidad() {return cantidad;}

    public String getNombre() {return nombre;}

    public void setIva(double iva) {this.iva = iva;}

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setPrecio(double precio) {this.precio = precio;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public void setPeso(double peso) {this.peso = peso;}

    public double calcularPrecioIVA(){return (precio * getIva()) + precio;}

    public void imprimir(){
        System.out.print("Producto número: "+codigo+", Nombre del producto: "+nombre+", Precio del producto: "+precio+", Cantidad: "+cantidad+", Peso: "+peso);
    }

    public void imprimirEnvio(){
        System.out.println("Codigo: "+codigo+" Nombre: "+nombre+" Peso: "+peso+"IVA: "+calcularPrecioIVA());
    }

    public String volcar(){return codigo + " " + nombre + " " + precio + " " + cantidad + " " + peso + " ";}

}
