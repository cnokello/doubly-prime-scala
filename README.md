## DOUBLY PRIME NUMBERS README

**Background**: 

This program generates N doubly prime numbers. A doubly prime number is defined as 
a prime number that also has  it's constituting digits  being prime.


*Example*:
  
	> 5 is prime and it's made up of the number 5 which is prime. Therefore, it's doubly prime
	
	> 13 is prime and it's made up of the numbers 1 which is prime and 3 which is also prime. Therefore, it's doubly prime.

*Counter example*: 

	> 19 is prime.  1 is prime but 9 isn't since it's divisible by 3. Therefore, 19 is not doubly prime. 


*What is the smallest prime number?*

Original definitions of prime numbers included 1. However, recent definitions states that prime numbers begin at 2. 
A look at [Wikipedia definition of prime numbers] (http://en.wikipedia.org/wiki/Prime_number) attests to this. Also, 
[a paper published in the Journal of Integer Sequences] (https://cs.uwaterloo.ca/journals/JIS/VOL15/Caldwell1/cald5.pdf) 
tries to explain why the smallest prime number is not 1. 

However, instructions for this assignment adheres to the original definition of prime numbers which includes 1.
 Therefore, the program treats 1 as a valid prime number.
 
 
 *Prime number generation algorithm*
 
 The algorithm I've used for generating a list of N prime numbers is the [Sieve of Eratosthenes](http://rosettacode.org/wiki/Sieve_of_Eratosthenes).
 
 
 **Limitations of my Implementation**
 
 * My implementation currently only uses a single core. Further work to enable it make use of several cores is one possible way to improve it's performance and total number of doubly prime numbers that it can generate
 * There's a possibility that my implementation may not be able to generate infinite number of doubly prime numbers. It took 12 seconds to generate 10,000 doubly prime numbers on a 2.90GHz core. 
 	I've not worked out the complexity of my implementation of the algorithm. Vanilla sieve of eratosthenes algorithm has a time complexity of the order  O(n (log n) (log log n)) according to this [Wikipedia entry](http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes).    
 		
 
**Some observations**
 
 * Changing the first prime number from 1 to 2 has a huge effect on the total number of doubly prime numbers this implementation can generate and the time it takes to generate them.
 * To generate 100 doubly prime numbers, it took about 40 milliseconds on 2.90GHz core. This is when other applications are also running.
 
 
**How to run**
 1. Ensure you have git installed. Refer to [this] (http://git-scm.com/book/en/v2/Getting-Started-Installing-Git) for git installation instructions. 
 2. Clone this repo by running the following command from within the directory into which you want the clone created:
 
 	> git clone https://github.com/cnokello/doubly-primed.git
 	
3. If not yet installed, install Scala by following the instructions [here](http://scala-lang.org/download/install.html). Ensure you set the environment variables.
4. This app uses [sbt](http://www.scala-sbt.org/) for source code compilation, packaging, test runs, and dependency management. 
	Please refer to [this guide](http://www.scala-sbt.org/0.13/tutorial/Setup.html) for installation on the various platforms.
5. To compile and test run the application, run the following command from within the base directory of the project you cloned in 2 above:

	> sbt clean compile run  // to generate 100 double prime numbers
	
	To generate doubly prime numbers other than 100, run: 
	
	> sbt clean compile "run N"  // e.g., sbt clean compile "run 80"
	
	This will compile and run the application
	
	Sample output for N = 100:
		
	> List(1, 2, 3, 5, 7, 11, 13, 17, 23, 31, 37, 53, 71, 73, 113, 127, 131, 137, 151, 157, 173, 211, 223, 227, 233, 251, 257, 271, 277, 311, 313, 317, 331, 337, 353, 373, 521, 523, 551, 557, 571, 577, 727, 733, 751, 757, 773, 1117, 1121, 1123, 1151, 1153, 1171, 1213, 1217, 1223, 1231, 1237, 1273, 1277, 1321, 1327, 1373, 1511, 1523, 1531, 1537, 1553, 1571, 1577, 1711, 1721, 1723, 1733, 1753, 1777, 2111, 2113, 2117, 2131, 2137, 2153, 2173, 2213, 2221, 2237, 2251, 2273, 2311, 2333, 2351, 2357, 2371, 2377, 2521, 2531, 2537, 2551, 2557, 2711)
	 
		

 	