package Clases;

import Interfaces.Enviable;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.*;
import java.util.Scanner;

public class Inventario {
    private static ArrayList<Producto> ListaProductos ;
    private static String ficheroInventario;
    private static int ultimoCodigo;
    private static Path pathArchivo = Path.of("src/file/productos.txt");
    private static
            Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/adridb",
            "root", "admin"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;


    private static Inventario instance;
    private Inventario(){
        ListaProductos = new ArrayList<Producto>();
    }

    public static Inventario getInstance() {
        if (instance == null) {
            instance = new Inventario();
        }
        return instance;
    }

    public static void guardarProductos(){
        try (PreparedStatement statement = connection.prepareStatement("""
           CREATE TABLE IF NOT EXISTS PRODUCTOS (
               ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
               Nombre VARCHAR(64) NOT NULL ,
               Precio DOUBLE(20,2) NOT NULL ,
               Cantidad int NOT NULL,
               Peso DOUBLE(20,2),
               FechaCaducidad VARCHAR(64),
               Especificacion VARCHAR(64),
               Tipo VARCHAR(64)
           );
        """)) {
            statement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        try  {
            for (Producto pro: ListaProductos) {
                String[] array = pro.volcar().split(" ");
                switch (array[array.length-1]){
                    case "Herramienta":
                        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO PRODUCTOS (Nombre, Precio, Cantidad, Peso, Tipo) VALUES (?, ?, ?, ?, ?);
                            """)) {
                                statement.setString(1, array[1]);
                                statement.setDouble(2, Double.parseDouble(array[2]));
                                statement.setInt(3, Integer.parseInt(array[3]));
                                statement.setDouble(4, Double.parseDouble(array[4]));
                                statement.setString(5, array[5]);
                                statement.executeUpdate();
                        }catch (Exception e){
                            System.out.println(e);
                        }
                        break;
                    case "Otros":
                        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO PRODUCTOS (Nombre, Precio, Cantidad, Peso, Especificacion, Tipo) VALUES (?, ?, ?, ?, ?, ?);
                            """)) {
                            statement.setString(1, array[1]);
                            statement.setDouble(2, Double.parseDouble(array[2]));
                            statement.setInt(3, Integer.parseInt(array[3]));
                            statement.setDouble(4, Double.parseDouble(array[4]));
                            statement.setString(5, array[5]);
                            statement.setString(6, array[6]);
                            statement.executeUpdate();
                        }catch (Exception e){
                            System.out.println(e);
                        }
                        break;
                    default:
                        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO PRODUCTOS (Nombre, Precio, Cantidad, Peso, FechaCaducidad, Especificacion, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?);
                            """)) {
                            statement.setString(1, array[1]);
                            statement.setDouble(2, Double.parseDouble(array[2]));
                            statement.setInt(3, Integer.parseInt(array[3]));
                            statement.setDouble(4, Double.parseDouble(array[4]));
                            statement.setString(5, array[5]);
                            statement.setString(6, array[6]);
                            statement.setString(7, array[7]);
                            statement.executeUpdate();
                        }catch (Exception e){
                            System.out.println(e);
                        }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addNuevoProducto(Producto tipo){
        System.out.println(tipo.volcar());
        try  {
            String[] array = tipo.volcar().split(" ");
            switch (array[array.length-1]){
                case "Herramienta":
                    try (PreparedStatement statement = connection.prepareStatement("""
                        INSERT INTO PRODUCTOS (Nombre, Precio, Cantidad, Peso, Tipo) VALUES (?, ?, ?, ?, ?);
                        """)) {
                        statement.setString(1, array[1]);
                        statement.setDouble(2, Double.parseDouble(array[2]));
                        statement.setInt(3, Integer.parseInt(array[3]));
                        statement.setDouble(4, Double.parseDouble(array[4]));
                        statement.setString(5, array[5]);
                        statement.executeUpdate();
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case "Otros":
                    try (PreparedStatement statement = connection.prepareStatement("""
                        INSERT INTO PRODUCTOS (Nombre, Precio, Cantidad, Peso, Especificacion, Tipo) VALUES (?, ?, ?, ?, ?, ?);
                        """)) {
                        statement.setString(1, array[1]);
                        statement.setDouble(2, Double.parseDouble(array[2]));
                        statement.setInt(3, Integer.parseInt(array[3]));
                        statement.setDouble(4, Double.parseDouble(array[4]));
                        statement.setString(5, array[5]);
                        statement.setString(6, array[6]);
                        statement.executeUpdate();
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                default:
                    try (PreparedStatement statement = connection.prepareStatement("""
                        INSERT INTO PRODUCTOS (Nombre, Precio, Cantidad, Peso, FechaCaducidad, Especificacion, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?);
                        """)) {
                        statement.setString(1, array[1]);
                        statement.setDouble(2, Double.parseDouble(array[2]));
                        statement.setInt(3, Integer.parseInt(array[3]));
                        statement.setDouble(4, Double.parseDouble(array[4]));
                        statement.setString(5, array[5]);
                        statement.setString(6, array[6]);
                        statement.setString(7, array[7]);
                        statement.executeUpdate();
                    }catch (Exception e){
                        System.out.println(e);
                    }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void mostrarProductos(){
        try (PreparedStatement statement = connection.prepareStatement("""
                SELECT * FROM PRODUCTOS;
            """)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String Nombre = rs.getString(2);
                Double Precio = rs.getDouble(3);
                int Cantidad = rs.getInt(4);
                Double Peso = rs.getDouble(5);
                String FechaDeCad = rs.getString(6);
                String Especificacion = rs.getString(7);
                String Tipo = rs.getString(8);
                System.out.println("ID = " + ID+" Nombre: "+Nombre+" Precio: "+Precio+" Cantidad: "+Cantidad+" Peso: "+Peso+" FechaDeCad: "+FechaDeCad+" Especificacion: "+Especificacion+" Tipo: "+Tipo );
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void actualizarCantidad(int codigo, int cant){

        try (PreparedStatement statement = connection.prepareStatement("""
           UPDATE PRODUCTOS SET Cantidad = ? WHERE ID = ?;
        """)) {
            statement.setDouble(1, cant);
            statement.setInt(2, codigo);
            statement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static int tamaño(){
        getInstance(); return ListaProductos.size();
    }

    public static void mostrarProductosEnviables(){
        try (PreparedStatement statement = connection.prepareStatement("""
                SELECT * FROM PRODUCTOS WHERE TIPO='Herramienta' OR TIPO='Otros';
            """)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String Nombre = rs.getString(2);
                Double Precio = rs.getDouble(3);
                int Cantidad = rs.getInt(4);
                Double Peso = rs.getDouble(5);
                String FechaDeCad = rs.getString(6);
                String Especificacion = rs.getString(7);
                String Tipo = rs.getString(8);
                System.out.println("ID = " + ID+" Nombre: "+Nombre+" Precio: "+Precio+" Cantidad: "+Cantidad+" Peso: "+Peso+" FechaDeCad: "+FechaDeCad+" Especificacion: "+Especificacion+" Tipo: "+Tipo );
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void eliminarProductos(int id){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del producto:");
        String producto = sc.nextLine();
        try (PreparedStatement statement = connection.prepareStatement("""
               DELETE FROM PRODUCTOS WHERE Nombre = ?;
            """)) {
                statement.setString(1, producto);
                statement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
