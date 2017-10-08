package Modelo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jfwc1
 */
 public class GeneradorHidato{

      /**
       * este metodo genera el contenido de tablero hidato en la matriz
       * recibe una matriz forma 2 la dificulta 
       * y retorna la matriz con el tablero de hidato
       * @param hidato //matriz forma 2
       * @param dificulta
       * @return hidato
       */
      public MatrizForma2 generar(MatrizForma2 hidato, int dificulta){
          int dimension;
          int difil=0;
          int v1[]=null,v2[]=null,v3[]=null;
          
          if (hidato.getFila()<hidato.getColumna()) {
              dimension=hidato.getFila();
          } else {
              dimension=hidato.getColumna();
          }
          
          switch (dificulta) {
              case 1:
                  difil=(hidato.getFila()*hidato.getColumna())/2;
                  System.out.println(difil);
                  break;
              case 2:
                  difil=(hidato.getFila()*hidato.getColumna())/4;
                  System.out.println(difil);
                  break;
              case 3:
                  difil=(hidato.getFila()*hidato.getColumna())/8;
                  System.out.println(difil);
                  break;
              
          }
          
          v1=this.aleatorioPosicion(dimension, difil);
          v2=this.aleatorioPosicion(dimension, difil);
          
          v3=this.aleatoriodato(hidato.getFila(), hidato.getColumna(),difil);
          hidato.crearHidato(v1, v2, v3);

          return hidato;
      }
      
      
      /**
       * Este metodo genera los valores que se ingresaran como pistas
       * en el trablero hidato
       * @param x//numero de filas
       * @param y//numero de columnas
       * @param longitud//cantidad de datos que se generaran
       * @return retorna un vector con los valores con los que se generara el hidato
       */
      public int[] aleatoriodato(int x, int y,int longitud){
          
          
           int n=x*y;  //numeros aleatorios
           int k=n;  //auxiliar;
            int[] numeros=new int[n];
            int[] resultado=new int[longitud];
            Random rnd=new Random();
            int res;


            //se rellena una matriz ordenada del 1 al x*y(1..n)
            for(int i=0;i<n;i++){
                numeros[i]=i+1;
            }

            for(int i=0;i<longitud;i++){
                res=rnd.nextInt(k);            
                resultado[i]=numeros[res];
                numeros[res]=numeros[k-1];
                k--;

            }
            return resultado;
      }
      
      
      /**
       * Este metodo retorna las posiciones que se usaran para ingresar los datos 
       *  aleatorios en la matriz de hidato
       * @param m//numer de filas y/o columnas de la matriz
       * @param longitud//tamaño de la matriz
       * @return 
       */
      public int[] aleatorioPosicion(int m,int longitud){
       
           int numero[]=new int[longitud];
           
            for (int i = 0; i < longitud; i++) {
                numero[i] = ThreadLocalRandom.current().nextInt(1, m + 1);
            }
           
          return numero;
          
      }
      
    
      
      
      
  }