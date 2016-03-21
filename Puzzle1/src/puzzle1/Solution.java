/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle1;


/**
 *
 * @author Keshine O'Young
 * 
 */

public class Solution {
    private int month;
    private int day;
    private int year;
    private final String AMBIGRETURN = "Ambiguous";
    //put in 0 month cuz i'm lazy to put in -1 on indices
    public final int[] daysinmonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public Solution(){
        month=0;
        day=0;
        year=0;
    }
    public String answer(int x,int y, int z){
        String returndate;
        int numpotmonth = potmonth(x)+potmonth(y)+potmonth(z);
        Boolean xfill=false;
        Boolean yfill=false;
        Boolean zfill=false;
        
        //checks if more than one potential month exists
        if(numpotmonth>1){
            //one way that date is non ambiguous is if month and day are the same
            //and year is not a potential month or day thus must be greater than #of days in month
            
            //more laziness, since code will either assign all three or exit function
            xfill=true;
            yfill=true;
            zfill=true;
            if(x<=12 && x==y && z>daysinmonth[x]){
                month = x;
                day= x;
                year=z;
            }
            else if(y<=12 && y==z && x>daysinmonth[y]){
                month = y;
                day= y;
                year=x;
                
            }
            else if(z==12 && x==z && y>daysinmonth[x]){
                month = z;
                day= z;
                year=y;
            }
            //last way of non ambiguity is  if all three numbers are the same
            else if(x==y && y==z){
                month = x;
                day= x;
                year=x;
            }
            else
                return AMBIGRETURN;
        }
        //potmonth should = 1 if not >1 so just check which number it is and assign it
        //constraints said that there should always be a possible date, thus number of potential months can not be 0
        else if(potmonth(x)==1){
            month=x;
            xfill=true;
        }
        else if(potmonth(y)==1){
            month=y;
            yfill=true;
        } 
        else if(potmonth(z)==1){
            month=z;
            zfill=true;
        }
        
        int numpotday = potday(x,month)+potday(y,month)+potday(z,month);
        //if numpotday ==0, then it should have been assigned during the month check and should pass over this
        if(numpotday>1){
            //more bad code
            xfill=true;
            yfill=true;
            zfill=true;
            if(x==y && zfill){
                day= x;
                year=x;
            }
            else if(y==z && xfill){
                day= y;
                year=z;
            }
            else if(x==z && yfill){
                day= x;
                year=x;
            }
            else
                return AMBIGRETURN;
        }
        //only checks if any of these =1
        else if(potday(x,month)==1){
            day=x;
            xfill=true;
        }
        else if(potday(y,month)==1){
            day=y;
            yfill=true;
        } 
        else if(potday(z,month)==1){
            day=z;
            zfill=true;
        }
        
        if(!xfill){
            year=x;
            xfill=true;
        }
        else if(!yfill){
            year=y;
            yfill=true;
        }
        else if (!zfill){
            year=z;
            zfill=true;
        }
        //tired and no longer know what i'm doing
        String monstr;
        String daystr;
        String yrstr;
        //making format have two digits for any one digit numbers
        if(month<10)
            monstr="0"+month;
        else
            monstr=""+month;
        if(day<10)
            daystr="0"+day;
        else
            daystr=""+day;
        if(year<10)
            yrstr="0"+year;
        else
            yrstr=""+year;
        
        if(xfill && yfill && zfill ){
            returndate = monstr+"/"+daystr+"/"+yrstr;
        }
        else
            //i think it should never hit this,but why not
            returndate=AMBIGRETURN;
        return returndate;
    }
    //potential month check
    private int potmonth(int m){
        if(m<=12)
            return 1;
        else
            return 0;
    }
    //potential day check excluding pot months
    private int potday(int d,int m){
        if(d<=daysinmonth[m] && d>12)
            return 1;
        else
            return 0;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution sol = new Solution();
        //System.out.println(sol.answer(12,12,12));
        int index=0;
        //test loop
        for(int x = 1; x < 100;x++){
            for(int y = 1; y < 100;y++){
                for(int z = 1; z < 100;z++){
                    //skip over a majority of the impossible dates, takes 31 day month, so few impossible dates will show up.
                    if((sol.potmonth(x)+sol.potmonth(y)+sol.potmonth(z)==0) || (sol.potday(x,1)+sol.potday(y,1)+sol.potday(z,1)==0)){
                        break;
                    }
                    else{
                    System.out.print(""+x+" "+y+" "+z+" ");
                    System.out.println(sol.answer(x,y,z));
                    index++;
                    }
                }
            }
        }
        System.out.println(index);
        
    }
    
}
