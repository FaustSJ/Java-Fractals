/*
keep the check-if-in-set cap at 50 for now

Mandelbrot
	Z starts at 0
	p is the point we are checking if in the set or not
	next z = (previous z^2) + p

	if next Z is not larger than 2, then p is in the set
	FEEL FREE TO ADD MORE METHODS
	*/
import java.util.*;
//import Complex.java;
//import Fractal?

public class Mandelbrot extends Fractal{
	
	//constructor
	public Mandelbrot(Complex low, Complex high, int nRows, int nCols, int maxIters)
	{
		this.low = low;
		this.high = high;
		this.nrows = nRows;
		this.ncols = nCols;
		this.maxIters = maxIters;
		this.c = new Complex(0.0, 0.0);
		this.escapeVals = new int[nrows][ncols];
		this.escapeVals = escapes();
	}
	
	public int escapeCount(Complex p)
	{
		//implementing Fractal's method
		//Z starts at 0
		
		//p is the point we are checking if in the set or not
		Complex z = new Complex(0.0, 0.0);
		return escapeCountHelper(0, z, p);
	}
	
	public int escapeCountHelper(int count, Complex z, Complex p)
	{
		//next z = (previous z^2) + p
		Complex newZ = Complex.add(Complex.mul(z, z), p);
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
		//eight arguments are accepted
		//doubles lowR, highR, lowI, highI
		//ints nRows, nCols, maxIters
		//the filename
		
		//create a Mandelbrot object
		Complex low = new Complex(Double.parseDouble(args[0]), Double.parseDouble(args[2]));
		Complex high = new Complex(Double.parseDouble(args[1]), Double.parseDouble(args[3]));
		Mandelbrot mad = new Mandelbrot(low, high, Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]));
		//write to the file
		mad.write(args[7]);
	}

}