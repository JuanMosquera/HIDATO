/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidato;

import MatrizForma2.MatrizForma2;
import manejoArchivos.Archivo;

/**
 *
 * @author jfwc1
 */
public class Hidato {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MatrizForma2 triz = null; //=new MatrizForma2(5,5);
//        
//        triz.insertar(1, 1, 2);
//        triz.insertar(1, 2, 4);
//        triz.insertar(2, 1, 6);
//        triz.insertar(3, 1, 1);
//        triz.insertar(2, 3, 7);
//        
//        
        Archivo ar = new Archivo();
//        ar.crearArchivo();
//        triz.guardar(ar); 
        ar.leerArchivo(triz);
    }
   
    
}

 
    
