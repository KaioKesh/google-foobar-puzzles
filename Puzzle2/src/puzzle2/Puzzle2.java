/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle2;

/**
 *
 * @author Speck
 */
public class Puzzle2 {
public static int[][] population1 = new int[][]{
    {1, 2, 3},
    {2, 3, 4},
    {3, 2, 1}
    };
public static int x1 = 0;
public static int y1 = 0;
public static int strength1 = 2;

public static int[][] answer1 =new int[][]{
    {-1, -1, 3}, 
    {-1, 3, 4}, 
    {3, 2, 1}
    };
public static int[][] population2 = new int[][]{
    {6, 7, 2, 7, 6},
    {6, 3, 1, 4, 7},
    {0, 2, 4, 1, 10},
    {8, 1, 1, 4, 9},
    {8, 7, 4, 9, 9}
    };
public static int x2 = 2;
public static int y2 = 1;
public static int strength2 = 5;

public static int[][] answer2 =new int[][]{
    {6, 7, -1, 7, 6},
    {6, -1, -1, -1, 7},
    {-1, -1, -1, -1, 10},
    {8, -1, -1, -1, 9},
    {8, 7, -1, 9, 9}
    };

    public void Puzzle2(){
        
    }
    public static int[][] answer(int[][]population, int x, int y, int strength){
        Boolean sweep=false;
        int[][]infectedpop;
        if(population[y][x]>strength){
            return population;
        }
        else{
            infectedpop=population.clone();
            infectedpop[y][x]=-1;
        }
        //boolean check if no cells were changed
        while(!sweep){
            //set sweep=true and if any cells changed set it back to false
            sweep=true;
            //top to bottom infection spread
            for(int i=0;i<infectedpop.length;i++){
                for(int j=0;j<infectedpop[i].length;j++){
                    if(infectedpop[i][j]==-1){
                        if(i>0){
                            if(infectedpop[i-1][j]<=strength && infectedpop[i-1][j]!=-1){
                                infectedpop[i-1][j]=-1;
                                sweep=false;
                            }
                        }
                        if(i<infectedpop.length-1){
                            if(infectedpop[i+1][j]<=strength && infectedpop[i+1][j]!=-1){
                                infectedpop[i+1][j]=-1;
                                sweep=false;
                            }
                        }
                        if(j>0){
                            if(infectedpop[i][j-1]<=strength && infectedpop[i][j-1]!=-1){
                                infectedpop[i][j-1]=-1;
                                sweep=false;
                            }
                        }
                        if(j<infectedpop[i].length-1){
                            if(infectedpop[i][j+1]<=strength && infectedpop[i][j+1]!=-1){
                                infectedpop[i][j+1]=-1;
                                sweep=false;
                            }
                        }
                    }
                }
            }
            //bottom to top infection spread to reduce loops to get cells where i<y
            for(int i=infectedpop.length-1;i>0;i--){
                for(int j=infectedpop[i].length-1;j>0;j--){
                    if(infectedpop[i][j]==-1){
                        if(i>0){
                            if(infectedpop[i-1][j]<=strength && infectedpop[i-1][j]!=-1){
                                infectedpop[i-1][j]=-1;
                                sweep=false;
                            }
                        }
                        if(i<infectedpop.length-1){
                            if(infectedpop[i+1][j]<=strength && infectedpop[i+1][j]!=-1){
                                infectedpop[i+1][j]=-1;
                                sweep=false;
                            }
                        }
                        if(j>0){
                            if(infectedpop[i][j-1]<=strength && infectedpop[i][j-1]!=-1){
                                infectedpop[i][j-1]=-1;
                                sweep=false;
                            }
                        }
                        if(j<infectedpop[i].length-1){
                            if(infectedpop[i][j+1]<=strength && infectedpop[i][j+1]!=-1){
                                infectedpop[i][j+1]=-1;
                                sweep=false;
                            }
                        }
                    }
                }
            }
        }
        return infectedpop;
    }
    
    public static void main(String[] args) {
        int[][] populationx=answer(population2,x2,y2,strength2);
        Boolean equal=true;
        for(int i=0;i<populationx.length;i++){
            for(int j=0;j<populationx[i].length;j++){
                if(populationx[i][j]!=answer2[i][j]){
                    equal=false;
                }
                System.out.print(populationx[i][j]+",");
            }
             System.out.println("");
        }
        if(equal){
            System.out.println("solved");
        }
        else{
            System.out.println("YOU FAIL");
        }
    }

}
