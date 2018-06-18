import java.util.*;

//A "Complex" is simply a point in the fractal grid 
public class Complex{
	double r; //the real number, x value
	double i; //the imaginary number, y value
	
	public Complex (double r, double i)
	{
		this.r = r;
		this.i = i;
	}
	
	//given two points, add their values
	public static Complex add(Complex a, Complex b)
	{
		return new Complex(a.r+b.r, a.i+b.i);
	}
	
	//given two points, subtract their values
	public static Complex sub(Complex a, Complex b)
	{
		return new Complex(a.r-b.r, a.i-b.i);
	}
	
	//given two points, multiply their values
	public static Complex mul(Complex a, Complex b)
	{
		//the new complex is calculated acording to the formula 
			//(a+bi)(c+di)=(ac-bd)+(bc+ad)i
		double newA = ((a.r*b.r)-(a.i*b.i));
		double newB = ((a.i*b.r)+(a.r*b.i));

		return new Complex(newA, newB);
	}
	
	//given polar coordinates, calculate the abs value
	public static double abs(Complex c)
	{
		double sub = (c.r*c.r)+(c.i*c.i);
		sub = Math.sqrt(sub);
		return Math.abs(sub);
	}
	
	@Override public String toString()
	{
		String stub = "(" + Double.toString(this.r) + "," + Double.toString(this.i) + ")";
		return stub;
	}
	
	public Complex copy()
	{
		return new Complex(this.r, this.i);
	}
}