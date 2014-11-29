package intervw

case class Cross(next: Int, incr: Int)

/**
 * @author nelson.okello
 *
 * Generates doubly prime numbers.
 * It uses the  Sieve of Erastothenes algorithm to generate a sequence of N prime numbers
 *
 */
class DoublyPrimeNumbersGenerator(N: Int) {
  import scala.collection.mutable.ListBuffer

  /**
   * Returns N doubly prime numbers
   */
  def get: List[Int] = {

    /**
     * Eratosthenes algorithm for N prime numbers generation
     * I've adjusted this algorithm a little bit to take care of the doubly prime number condition
     * of ensuring that every constituent digit is a prime number as well
     */
    def sieve(s: Stream[Int], crosses: List[Cross]): Stream[Int] = {
      val current #:: rest = s

      if (notPrime(crosses, current)) sieve(rest, adjustCrosses(crosses, current))
      else current #:: sieve(rest, Cross(current * current, current) :: crosses)
    }

    def adjustCrosses(crosses: List[Cross], current: Int) = {
      crosses map {
        case cross @ Cross(`current`, incr) => cross copy (next = current + incr)
        case unchangedCross => unchangedCross
      }
    }

    /**
     * Modification of the Eratosthenes algorithm to take care of the double prime number
     * condition is implemented here
     */
    def notPrime(crosses: List[Cross], current: Int): Boolean = {
      val prime = crosses exists (_.next == current)

      current.toString.map(_.asDigit).toList.foreach { n =>
        if (!isPrime(n)) return true
      }

      return prime
    }

    /**
     * Returns TRUE if n is a prime number. FALSE otherwise.
     */
    def isPrime(n: Int): Boolean = {
      if (n < 1) false
      else if (n == 1 || n == 2) true
      else !(2 to (n - 1)).exists(x => n % x == 0)
    }

    val primes = sieve(Stream.from(1), Nil)
    primes.take(N).toList
  }

}

/**
 * Entry point into the application
 */
object RunApp extends App {
  override def main(args: Array[String]) = {
    val start = System.nanoTime()
    var N = 100

    if (args.size > 0)
      try N = args(0).toInt catch {
        case e: Exception => println("Invalid argument provided!")
      }

    val doublyPrimeNumbers = new DoublyPrimeNumbersGenerator(N).get

    println("Time taken to compute: " + (System.nanoTime() - start) / 1000000 + " milliseconds")
    println("Total doubly prime numbers generated: " + doublyPrimeNumbers.size)
    println(doublyPrimeNumbers)
  }
}