package intervw

object DoublyPrimeNumbers {
  import scala.collection.mutable.ListBuffer
  import util.control.Breaks._

  var N: Int = 0
  var doublyPrimes: ListBuffer[Int] = ListBuffer[Int]()

  def get(n: Int): List[Int] = {
    def sieve(s: Stream[Int]): Stream[Int] = {
      s.head #:: sieve(s.tail.filter(_ % s.head != 0))
    }

    def getPrimes(lower: Int, numPrimes: Int): List[Int] = {
      def max(xs: List[Int]) = {
        if (xs.isEmpty) throw new NoSuchElementException
        xs.reduceLeft((x, y) => if (x > y) x else y)
      }

      def isPrime(n: Int): Boolean = {
        if (n < 1) false
        else if (n == 1) true
        else if (n == 2) true
        else !(2 to (n - 1)).exists(x => n % x == 0)
      }

      if (N == 0) N = numPrimes
      var primes = sieve(Stream.from(lower))
      var _primes = primes.take(numPrimes).toList
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

object RunApp extends App {
  override def main(args: Array[String]) = {
    var i = 1
    DoublyPrimeNumbers.get(100).foreach { n => print(n + "\t "); i += 1 }
  }
}