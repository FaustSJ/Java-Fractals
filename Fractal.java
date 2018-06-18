import java.util.*;
import java.io.*;
//import "Complex.java";

abstract class Fractal{
     Complex low, high; // lower-left and upper-right coordinates
     int nrows, ncols; // pixel counting – how many  pixels in each direction
     int maxIters;  // how many iterations to consider for set inclusion
     int[][] escapeVals; // cached answers for each point's iterations to escape
     Complex c;   // what is the c value of the iteration function? (boring for Mandelbrot)
     
     //given a p, how many iterations can be done up to maxIters, before it escapes?
     public abstract int escapeCount(Complex p);
     
     //calculate escape counts for each point
     //x values are Real, y values are Imaginary
     public int[][] escapes()
     {
          //iDiff is now the difference between each range point
          double iDiff = (high.i - low.i)/(nrows-1);
          
          //iDiff is now the difference between each domain point
          double rDiff = (high.r - low.r)/(ncols-1);
          
          //currentR and I start out at [0][0], the upper-left corner of the grid
          double currentR = low.r;
          double currentI = high.i;

          //counts will store the values to return
          int[][] counts = new int[nrows][ncols];
          
          for(int k = 0; k<nrows; k++)
          {
               for(int j = 0; j<ncols; j++)
               {      
                    counts[k][j] = escapeCount(new Complex(currentR, currentI));
                    currentR = currentR + rDiff; //going right
               }
               currentR = low.r; //reset the currentI to very left value
               currentI = currentI - iDiff; //going down
          }

          return counts;
     }
     
     public void write(String filename)
     {
          //create a file containing:
          //nRows, nCols, maxIters
          //lowRealVal, highRealVal, lowImaginatyVal, highImaginaryVal
          //realC imaginary (parts of constant c)
          //-blank line-
          //each row from escapeVals on their own line, values padded appart
          try{
               PrintWriter pw = new PrintWriter(new File(filename));
               String s = toString();
               pw.print(s);
               pw.close();
          }
          catch(FileNotFoundException e)
          {
               System.out.println("ERROR:: File Not Found");
          }
     }
     
     public void zoom(double factor)
     {
          //stay centered, scale it so that each dimension sees 1/factor of its previous range
          //ex: factor=2, we see half the distance of previous real range and previous imaginary range
          
          //find the range
          double rDiff = high.r - low.r;
          double iDiff = high.i - low.i;
          //find new range
          double rFac = rDiff/factor;
          double iFac = iDiff/factor;
          //find new low and high values, starting from the middle
          double rMid = high.r - (rDiff/2);
          double iMid = high.i - (iDiff/2);
          double highR = rMid + (rFac/2);
          double highI = iMid + (iFac/2);
          double lowR = rMid - (rFac/2);
          double lowI = iMid - (iFac/2);
          //Update the dimensions
          updateDimensions(new Complex(lowR, lowI), new Complex(highR, highI));
     }
     
     public void updateDimensions(Complex low, Complex high)
     {
          //reset low and high
          this.low = low;
          this.high = high;
          //recalculate escapeCounts and stuff
          escapeVals = escapes();
     }
     
     @Override public String toString()
     {
          String s = "";
          s = s + Integer.toString(nrows)+" "+Integer.toString(ncols)+" "+Integer.toString(maxIters)+"\n";
          s = s + Double.toString(low.r)+" "+Double.toString(high.r)+" "+Double.toString(low.i)+" "+Double.toString(high.i)+"\n";
          s = s + Double.toString(c.r)+" "+Double.toString(c.i)+"\n\n";
          for(int[] arr : escapeVals)
          {
               for(int num : arr)
               {
                    s = s + Integer.toString(num) + "\t";
               }
               s = s + "\n";
          }
          return s;
     }
}