/*
keep the check-if-in-set cap at 50 for now

Julia set
	Z starts at p
	c is a predetermined constant
	next z = (previous z^2) + c

	if the absolute value of next z<=2, then p is in set
	FEEL FREE TO ADD MORE METHODS
*/
import java.util.*;
//import Complex.java;
//import Fractal?

public class Julia extends Fractal{
	
	//constructor
	public Julia(Complex low, Complex high, int nRows, int nCols, int maxIters, Complex c)
	{
		this.low = low;
		this.high = high;
		this.nrows = nRows;
		this.ncols = nCols;
		this.maxIters = maxIters;
		this.c = c;
		this.escapeVals = new int[nrows][ncols];
		this.escapeVals = escapes();
	}
	
	public int escapeCount(Complex p)
	{
		//implementing Fractal's method
		//Z starts at p
		//c is a predetermined constant
		return escapeCountHelper(0, p, p);
	}
	
	public int escapeCountHelper(int count, Complex z, Complex p)
	{
		//next z = (previous z^2) + c
		Complex newZ = Complex.add(Complex.mul(z, z), c);
		//do we stop?
		if((count==maxIters)||(Complex.abs(newZ)>2.0))
			return count;
		//recursion!
		return escapeCountHelper(count+1, newZ, p);
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
	
	public static void main(String[] args)
	{
		//ten arguments are accepted
		//doubles of dimensions lowR, highR, lowI, highI
		//ints nRows, nCols, maxIters
		//Real c and Imaginary c
		
		//create Julia object
		Complex c = new Complex(Double.parseDouble(args[7]), Double.parseDouble(args[8]));
		Complex low = new Complex(Double.parseDouble(args[0]), Double.parseDouble(args[2]));
		Complex high = new Complex(Double.parseDouble(args[1]), Double.parseDouble(args[3]));
		Julia jil = new Julia(low, high, Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), c);
		//write to the file
		jil.write(args[9]);
	}
}