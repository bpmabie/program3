/**This is my own work: Ben Mabie
 * This program reads from a file and generates sequences based on the information
 * inside the file.
 * September 19, 2014
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SequenceGenerator{
    
    public static void main(String[] args) throws FileNotFoundException {
        
        //create scanner to read from file 
        File file = new File("SEQ.in");                                        
        Scanner scan = new Scanner(file);                                       
        //create sequence variable to be reused for each sequence
        Sequence s = null;
        //while loop to scan the file and evaluate the data with switch statement  
        while(scan.hasNext()){
            switch (scan.next()) {
                case "A":
                    s = new ArithematicSequence(scan.nextDouble(),scan.nextDouble());
                    break;
                case "G":
                    s = new GeometricSequence(scan.nextDouble(),scan.nextDouble());
                    break;
                case "F":
                    s = new FibonacciSequence(scan.nextDouble(),scan.nextDouble());
                    break;
            }
            System.out.println("The first 10 terms in the sequence are:");
            System.out.println(s);
            System.out.print("sum of the first 5 terms = ");
            System.out.print(s.getNthSum(5));
            System.out.println("\n");
                   
        }
    }

}

abstract class Sequence {
    //super class
    private double firstTerm; 
    //constructor
    public Sequence(double firstTerm){                                          
        this.firstTerm = firstTerm;       
    }//copy constructor
    public Sequence(Sequence s){                                                
        this.firstTerm = s.firstTerm;
    }
    public Sequence(){                                                          
        this(1);
    }//setters and getters
    public void setFirstTerm(double n){                                         
        this.firstTerm = n;  
    }
    public double getFirstTerm(){
        return this.firstTerm;
    }
    //abstract getNthTerm 
    public abstract double getNthTerm(int n);
    //getNthSum method
    public double getNthSum(int o){                                             
        
        double sum = 0;
      
        for(int i = 1; i <= o; i++){
            sum += getNthTerm(i);
        }
        
        return sum;
    }
    @Override//tostring method
    public abstract String toString();
}

class ArithematicSequence extends Sequence{
   //first sequence extension
    private double commonDif;
     //no args constructor
    public ArithematicSequence(){                                              
        super();
        this.commonDif = 0;   
    }//constructor
    public ArithematicSequence(double firstTerm, double commonDif){                   
        super(firstTerm);
        this.commonDif = commonDif;
    }//copy constructor
    public ArithematicSequence(ArithematicSequence c){                         
        super(c);
        this.commonDif = c.commonDif;
    }
    public void setArithematicSequence(double firstTerm, double commonDif){       
        this.commonDif = commonDif;
        super.setFirstTerm(firstTerm);
    }
  
    @Override
    public double getNthTerm(int n){
        double term1 = super.getFirstTerm();
        double term2 = commonDif;
        
        for(int i = 1; i < n; i++){
            term1 += commonDif; 
        }       
        return term1;
    }
    
    @Override
    public String toString(){
        String str = "";
      
        double first = super.getFirstTerm();
        double combo = 0;
         
        combo = first + commonDif;
        str += first;
        
        for(int i = 1; i < 10; i++){
            str += "," + " " + combo;
            combo += commonDif;
        }
                
        return str;
    }
}
class GeometricSequence extends Sequence{
   //next sequence extension 
    private double ratio;
    //no args constructor
    public GeometricSequence(){                                         
        super();
        this.ratio = 1;
    }//constructor
    public GeometricSequence(double firstTerm, double ratio){                   
        super(firstTerm);
        this.ratio = ratio;
    }//copy constructor
    public GeometricSequence(GeometricSequence d){                       
        super(d);
        this.ratio = d.ratio;
    }
    public void setGeometricSequence(double firstTerm, double ratio){
        this.ratio = ratio;
        super.setFirstTerm(firstTerm);
       
    }
    @Override//tostring
    public String toString(){
        
        String str = "";
        
        double first = super.getFirstTerm();
        double combo = 0;
                
        combo = (first * ratio);
        str += first;
        
        for(int i = 1; i < 10; i++){
            str += "," + " " + combo;
            combo = (combo * ratio);
        }
        
        return str;
    }
    @Override//getNthTerm
    public double getNthTerm(int n){
  
        double term1 = super.getFirstTerm();
        double term2 = ratio;
        
        for(int i = 1; i < n; i++){
            term1 *= ratio; 
        }       
        return term1;
    }

}
class FibonacciSequence extends Sequence{
    //next sequence extension
    private double secondTerm;
    //no args constructor
    public FibonacciSequence(){                         
        super();
        this.secondTerm = 1;
    }//constructor
    public FibonacciSequence(double firstTerm, double secondTerm){        
        super(firstTerm);
        this.secondTerm = secondTerm;
    }//copy constructor
    public FibonacciSequence(FibonacciSequence e){                  
        super(e);
        this.secondTerm = e.secondTerm;
    }
    public void setFibonacciSequence(double firstTerm, double secondTerm){
        this.secondTerm = secondTerm;
        super.setFirstTerm(firstTerm);
    }
    @Override
    public double getNthTerm(int n){
        
        double term1 = super.getFirstTerm();
        double term2 = secondTerm;
        double temp = term1 + term2;
                    
        if(n == 1){
            return getFirstTerm();
        }
        else if(n == 2){
           return this.secondTerm;
       }
        else
            for(int i = 3; i <= n; i++){
                temp = term1 + term2;
                term1 = term2;
                term2 = temp;  
            }
        return temp;
  
    }
    @Override
    public String toString(){
        String str = "";
        
        double first = super.getFirstTerm();
        double second = secondTerm;
        double curr = 0;
              
        str += first + "," + " ";
        str += second;
        
        for(int i = 1; i < 9; i++){
            curr = (first + second);
            str += "," + " " + curr;
            first = second;
            second = curr;
        }
        
        return str;
    }

}