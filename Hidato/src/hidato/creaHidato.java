/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidato;

/**
 *
 * @author jfwc1
 */
public class creaHidato {
    int[][] mov=new int[5][3];
    int matriz[][];
    int n;
    boolean termina= false;

    public creaHidato() {
        n=8;
        matriz =new int[n][n];
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
    
  
      public void gHidato(int i, int x1,int y1){
          
         
          int k, x2,y2,p,q;
          k=0;
          do {
              k=k+1;
              x2=x1+mov[k][1];
              y2=y1+mov[k][2];
              if (esValido(x2,y2)) {
                  matriz[x2][y2]=i;
                  if (i<(n)*(n)) {
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
      
      public boolean esValido(int x3,int y3){
          if ((x3>=0 && x3<n) && y3>=0 && y3<n && matriz[x3][y3]==0) {
              
              return true;
          }
          
          return false;
      }
      public void mostrar(){
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                  System.out.print("-"+matriz[i][j]+"-");
              }
              System.out.println("");
          }
      }
      
}
