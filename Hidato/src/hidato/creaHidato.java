/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidato;

import MatrizForma2.MatrizForma2;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jfwc1
 */
public class creaHidato {
    int[][] mov=new int[5][3];
    int matriz[][];
    int m,n;
    boolean termina= false;

    /**
     * constructor de la clase
     */
    public creaHidato(MatrizForma2 ma) {
        m=ma.getFila();
        n=ma.getColumna();
        matriz =new int[m][n];
        
        mov[1][1]= -1;
        mov[1][2]= 0;
        mov[2][1]= 0;
        mov[2][2]= +1;
        mov[3][1]= +1;
        mov[3][2]= 0;
        mov[4][1]= 0;
        mov[4][2]= -1;
        
//        mov[1][1]=-1;
//        mov[2][1]= -1;
//        mov[3][1]= 0;
//        mov[4][1]= +1;
//        mov[5][1]= +1;
//        mov[6][1]= +1;
//        mov[7][1]= 0;
//        mov[8][1]= -1;
//        mov[1][2]= 0;
//        mov[2][2]= +1;
//        mov[3][2]= +1;
//        mov[4][2]= +1;
//        mov[5][2]= 0;
//        mov[6][2]= -1;
//        mov[7][2]= -1;
//        mov[8][2]= -1;
        
    }
    
      /**
       * Este metodo me genera la matriz forma 2 que representa el hidato 
       * con un numero de pistas dependiento de la dificultad 
       * @param ma//matriz forma 2 donde se generara el hidato
       * @param dificulta 
       */
      public void generarH(MatrizForma2 ma, int dificulta){
        
        
          
           int difil=0,v[],tamaño,aux[],a;
          tamaño=ma.getFila()*ma.getColumna();
          switch (dificulta) {
              case 1:
                  difil=(ma.getFila()*ma.getColumna())/2;
                  System.out.println(difil);
                  break;
              case 2:
                  difil=(ma.getFila()*ma.getColumna())/4;
                  System.out.println(difil);
                  break;
              case 3:
                  difil=(ma.getFila()*ma.getColumna())/8;
                  System.out.println(difil);
                  break;    
          }
          
          aux=new int[tamaño];
          v=new int[difil];
          v[0]=1;
          v[1]=tamaño;
          
          for (int i = 0; i < tamaño; i++) {
              aux[i]=0;
          }
          aux[0]=1;
          aux[tamaño-1]=tamaño;
          for (int i = 2; i < v.length; i++) {
              a = ThreadLocalRandom.current().nextInt(1, tamaño + 1);
              if (aux[a-1]==0) {
                  aux[a-1]=a;
                  v[i]=a;
              } else {
                  i--;
              }
          }
          System.out.println("la matriz");
          for (int i = 0; i < v.length; i++) {
              System.out.print(" "+v[i]);
              
          }
          System.out.println("");
          for (int i = 0; i < v.length; i++) {
              
              this.buscarInsertar( ma, v[i]);
              
          }
          
          
      }
      
      /**
       * Este metodo busca en la matriz un dato especificado del cual se obtendra 
       * su fila y su columna para ingresarlo en la matriz forma 2
       * @param m//matriz forma 2
       * @param v //dato que se va a buscar en la matriz
       */
      public void buscarInsertar(MatrizForma2 m, int v){
          
          for (int i = 0; i < m.getFila(); i++) {
              for (int j = 0; j < m.getColumna(); j++) {
                  if (matriz[i][j]==v) {
                      System.out.println((i+1)+" "+(j+1)+" "+v);
                      m.insertar(i+1, j+1, v);
                      return;
                  }
              }
          }
      }
      
      
      /**
       * Este metodo genera un hidato dentro de una matriz ya creada,
       * a partir de una posicion indicada
       * @param i//se utiliza para identificar  el orden con el cual se visitan las casillas de la matriz
       * @param x1//posicion en fila
       * @param y1 //posicion en columna
       */
      public void gHidato(int i, int x1,int y1){
          
         
          int k, x2,y2,p,q;
          k=0;
          do {
              k=k+1;
              x2=x1+mov[k][1];
              y2=y1+mov[k][2];
              if (esValido(x2,y2)) {
                  matriz[x2][y2]=i;
                  if (i<(m)*(n)) {
                      gHidato(i+1,x2,y2);
                       if (!termina) {
                          matriz[x2][y2]=0;
                           
                      } 
                  }else{
                      termina=true;
                  }
              }
              
          } while (k<4 &&!termina);
          
       }
      
      /**
       * Metodo que recibe dos enteros que representan una posicion en una matriz
       * y determina si en dicha posicion es igual a cero (osea esta libre) o es
       * distinta de cero (osea ya estan uso ocupada con otro valor)
       * @param x3//posicion fila
       * @param y3//posicion columna
       * @return //me retorna true si en esa posicion hay un cero o false de lo contrario
       */
      public boolean esValido(int x3,int y3){
          if ((x3>=0 && x3<m) && y3>=0 && y3<n && matriz[x3][y3]==0) {
              
              return true;
          }
          
          return false;
      }
      /**
       * Metodo que muestra la matriz que contiene el hidato
       */
      public void mostrar(){
          for (int i = 0; i < m; i++) {
              for (int j = 0; j < n; j++) {
                  System.out.print("-"+matriz[i][j]+"-");
              }
              System.out.println("");
          }
      }
      
}
