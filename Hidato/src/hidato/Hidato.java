/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidato;

import MatrizForma2.MatrizForma2;
import java.util.Random;
import manejoArchivos.Archivo;
import java.util.concurrent.ThreadLocalRandom;

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
        
//        int numero;
//        for (int i = 0; i < 10; i++) {
//            numero = ThreadLocalRandom.current().nextInt(1, 10 + 1);
//            System.out.println(numero);

//              //generador de numeros aleatorios
//            Random generadorAleatorios = new Random();
//
//            //genera un numero entre 1 y 5 y lo guarda en la variable numeroAleatorio
//            int numeroAleatorio = 1+generadorAleatorios.nextInt(5);
//            //imprimo el numero en consola
//            System.out.println(numeroAleatorio);
//        }
         
         
         
       
   
        
        
        
//        MatrizForma2 triz =new MatrizForma2(5,5);
////        
//        triz.insertar(1, 1, 2);
//        triz.insertar(1, 2, 4);
//        triz.insertar(2, 1, 6);
//        triz.insertar(3, 1, 1);
//        triz.insertar(2, 3, 7);
////        
////        
//        Archivo ar = new Archivo();
//        ar.crearArchivo();
//        triz.guardar(ar); 
//        
////        ar.leerArchivo(triz);
//
//         MatrizForma2 triz2 =new MatrizForma2(5,5);
////        
//        triz2.insertar(1, 1, 3);
//        triz2.insertar(1, 2, 3);
//        triz2.insertar(2, 1, 3);
//        triz2.insertar(3, 1, 3);
//        triz2.insertar(2, 3, 3);
//        
//        Archivo ar2 = new Archivo();
//        ar2.crearArchivo();
//        triz2.guardar(ar2); 

        //con esta parte del codigo genero el tablero de hidato
          MatrizForma2 trix =new MatrizForma2(6,6);
//          GeneradorHidato hida=new GeneradorHidato();
//          hida.generar(trix, 3);
//          System.out.println("aqui va la matriz");
//          trix.muestraMatriz();
//          System.out.println("la otra");
//          trix.mostrarMatrizNormal();
        
        //modifique el constructor le paso el parametro a la matriz forma dos
      creaHidato prueba = new creaHidato(trix);
      prueba.gHidato(0, 0, 0);
      prueba.mostrar();
      prueba.generarH(trix, 3);
      trix.mostrarMatrizNormal();
            
            
          
          

    }
   
    
}

 
    




