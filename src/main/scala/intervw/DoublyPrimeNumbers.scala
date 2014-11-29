package intervw

/**
 * @author nelson.okello
 *
 * Generates doubly prime numbers.
 * It uses the  Sieve of Erastothenes algorithm to generate a sequence of N prime numbers
 *
 * For the generated prime numbers,
 * it checks whether the constituent digits of each prime number is also a prime number. If any of the constituent digits
 * of any generated prime number is not a prime number, the corresponding prime number is removed from the list.
 * This process is applied recursively until all elements which are not doubly prime are removed.
 *
 *  If after removing non-doubly prime numbers, the remaining doubly prime number is less than the requested number, N,
 *  recursively call get primes
 *
 */
object DoublyPrimeNumbers {
  import scala.collection.mutable.ListBuffer

  var N: Int = 0
  var doublyPrimes: ListBuffer[Int] = ListBuffer[Int]()

  /**
   * Returns n doubly prime numbers
   */
  def get(n: Int): List[Int] = {

    /**
     * Generates prime numbers
     */
    def sieve(s: Stream[Int]): Stream[Int] = {
      s.head #:: sieve(s.tail.filter(_ % s.head != 0))
    }

    /**
     * Uses a custom algorithm to generate doubly prime numbers
     * Generates 100 doubly prime number in about  150ms on average using a single core of 2.90 GHz
     * The algorithm used to generate doubly prime numbers is a greedy algorithm
     * On my PC, it can comfortably generate 160 doubly prime numbers with no custom JVM configuration
     * To generate more than 160 doubly prime numbers requires additional work on the algorithm
     */
    def getPrimes(lower: Int, numPrimes: Int): List[Int] = {

      /**
       * Returns the largest integer number in the specified list
       */
      def max(xs: ListBuffer[Int]) = {
        if (xs.isEmpty) throw new NoSuchElementException
        xs.reduceLeft((x, y) => if (x > y) x else y)
      }

      /**
       * Returns TRUE if n is a prime number. FALSE otherwise.
       */
      def isPrime(n: Int): Boolean = {
        if (n < 1) false
        else if (n == 1 || n == 2) true
        else !(2 to (n - 1)).exists(x => n % x == 0)
      }

      if (N == 0) N = numPrimes
      var primes = sieve(Stream.from(lower))
      var _primes = primes.take(numPrimes).to[ListBuffer]
      _primes.foreach { prime =>
        prime.toString.map(_.asDigit).toList.foreach { n =>
          if (!isPrime(n)) {
            _primes = _primes diff List(prime)
          }
        }
      }

      doublyPrimes ++= _primes
      if (doublyPrimes.size >= N) doublyPrimes.take(N).toList
      else if (N <= 7) getPrimes(max(_primes) + 1, (N - doublyPrimes.size) * N)
      else if (N <= 100) getPrimes(max(_primes) + 1, (N - doublyPrimes.size) * (N / 6))
      else getPrimes(max(_primes) + 1, (N - doublyPrimes.size) * (N / 5))
    }

    getPrimes(2, n)
  }
}

/**
 * Entry point into the application
 */
object RunApp extends App {
  override def main(args: Array[String]) = {
    val start = System.nanoTime()
    var doublyPrimes = List[Int]()

    if (args.size == 0) doublyPrimes = DoublyPrimeNumbers.get(100)
    else try doublyPrimes = DoublyPrimeNumbers.get(args(0).toInt) catch {
      case e: Exception => println("Invalid argument provided!")
    }

    println("Time taken to compute: " + (System.nanoTime() - start) / 1000000 + " milliseconds")
    println("Total doubly prime numbers generated: " + doublyPrimes.size)
    println(doublyPrimes)
  }
}