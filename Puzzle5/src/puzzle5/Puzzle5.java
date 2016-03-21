/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle5;

/**
 *
 * @author Speck
 */
public class Puzzle5 {

    /**
     *
     */
    public Puzzle5(){
        
    }
    public static int answer(int n) { 
        int numsq=0;
        double dn=n;
        while(dn>0){            
            for(int i=100;i>0;i--){
                if(dn-Math.pow(i, 2)>=0){
                    dn=dn-Math.pow(i, 2);
                    numsq++;
                    break;
                }
            } 
        }
        return numsq;
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(answer(24));
        System.out.println(answer(160));
    }

}
