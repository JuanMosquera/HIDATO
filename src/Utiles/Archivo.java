/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;


import Modelo.MatrizForma2;
import java.io.*;

/**
 *
 * @author jfwc1
 */
public class Archivo {
    private File archivo;
    private BufferedWriter bw;
    
    /**
     * crea el archivo de txt donde se guardara la session del juego
     */
    public void crearArchivo(){//podria crear un nuevo archivo para guardar si paso un string como parametro
        try {
            archivo=new File(".\\archivosTextos","partida.txt");
            
            if (archivo.createNewFile())
                System.out.println("El fichero se ha creado correctamente");
              else
                System.out.println("No ha podido ser creado el fichero");
            
        } catch (Exception e) {
            System.out.println("El archivo no se creo");
        }
    }
    
    /**
     * escribe en el archivo la sesion del juego
     * osea la matriz que representa el tablero de hidato
     * 
     * @param s 
     */
    public void escribirArchivo(String[] s){
        
        try {
            if(archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                for (int i = 0; i < s.length; i++) {
                    bw.write(s[i]);
                    bw.newLine();
                }
                
                
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                for (int i = 0; i < s.length; i++) {
                    bw.write(s[i]);
                    bw.newLine();
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("no pudo escribir");
        }

    }
    
    
    
   /**
    * Lee un archivo txt a partir del cual llena
    * una matriz forma 2 la cual se le pasa como parametro
    * @param matrix 
    */
    public void leerArchivo(MatrizForma2 matrix){
        try {
            FileReader entrada= new FileReader("./archivosTextos/partida.txt");
            BufferedReader bw=new BufferedReader(entrada);
            String fila,resul="";
            int m=0,n=0;
            MatrizForma2 formaDos;
            while ((fila=bw.readLine())!=null) {
                //formaDos
                m=fila.length();
                resul=resul+fila;
                resul=resul+" ";
                ++n;
                
            }
            
            formaDos=new MatrizForma2(m,n);
            System.out.println(resul); 
            String part[]=resul.split(" ");
            
            matrix = new MatrizForma2(m,n);
            matrix.crear(part);
//            matrix.muestraMatriz();
            
            entrada.close();
            bw.close();
        } catch (Exception ex) {
            System.out.println("No se encontro archivo");
        }
      
    }
    
}
