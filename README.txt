These files make up a program that can generate two fractal sets: the Julia set
and the Mandelbrot set.

After compiling, the user can either supply arguments to the Julia or Mandelbrot 
file, and a generated set will be stored into a txt file.

Note: In order to actually see the fractal, it's recommended that the user open
the output txt file in a program other than Notepad. The Notepad application 
ignores newline symbol (\n), so the fractal doesn't properly display.
--------------------
An example of implementing Julia:
>java Julia -2 2 -2 2 15 15 40 -.62772 .42193 out.txt
where~
the first -2 2 represent the low complex pair 
the second -2 2 represent the high complex pair
15 and 15 represent the number of rows and columns respectively
40 is the maximum number of iterations through the algorithm before determining that 
                                                                   a value is in the set. 
-.62772 .42193 represent the constant complex pair that is present in the Julia algorithm
out.txt is the file the generated Julia set will output to.
--------------------
An example of implementing Mandelbrot:
>java Mandelbrot -2 2 -2 2 15 15 40 tinyM.txt
where~
the first -2 2 represent the low complex pair 
the second -2 2 represent the high complex pair
15 and 15 represent the number of rows and columns respectively
40 is the maximum number of iterations through the algorithm before determining that 
                                                                   a value is in the set.
out.txt is the file the generated Julia set will output to.
