/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroshoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricar
 */
public class LeerDatos extends Datos {
    protected String[] datosLineas;
    
    public LeerDatos() {
        super("zapatos.txt");
    }
    
    /**
     * Cantidad de caracteres que tiene el archivo de datos actualmente
     * 
     * @return int cantidad de caracteres
     */
    protected int cantidadDeCaracteresDelArchivo() {
        int valor;
        int cantidadChar = 0;
        try {
            FileReader buscar = new FileReader(archivo);
            valor = buscar.read();
            while(valor != -1) {
                cantidadChar++;
                valor = buscar.read();
            }
        buscar.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidadChar;
    }
    
    /**
     * Devuelve el String de la linea pedida
     * 
     * @param numLinea numero de linea [1,infinito)
     * @return String linea
     */
    protected String obtenerLinea(int numLinea) {
        int cantidadChar = cantidadDeCaracteresDelArchivo();
        char datos[] = new char[cantidadChar];
        int valor;
        try {
            FileReader buscar = new FileReader(archivo);
            valor = buscar.read();
            int posicion = 0;
            while(valor != -1) {
                datos[posicion] = (char)valor;
                posicion++;
                valor = buscar.read();
            }
            buscar.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < cantidadChar; i++) {
           resultado.append( datos[i] );
        }
        String datosString = resultado.toString();
        datosLineas = datosString.split("\r\n");
        return datosLineas[numLinea-1];
    }
    
    public boolean existeArchivo() {
        File archivo = new File(this.archivo);
        return archivo.exists();
    }
}
