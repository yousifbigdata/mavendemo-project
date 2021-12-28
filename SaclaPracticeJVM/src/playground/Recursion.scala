package playground

object Recursion extends App {
  def aFactorialFun(n:Int):BigInt={
    if(n <= 1) n
    else n * aFactorialFun(n-1)
  }

//  println(aFactorialFun(5000))

  def anotherFactorial(n:Int):BigInt ={
    def factHelper(x:Int, accumulator: BigInt) :BigInt={
    if(x <= 1) accumulator
    else
      factHelper(x-1,x * accumulator)
    }
    factHelper(n,1)
  }
//  println(anotherFactorial(5000))
def isPrime(n:Int):Boolean={
  def isPrimeUntil(x:Int):Boolean={
    if(x <=1) true
    else n % x !=0 && isPrimeUntil(x -1)
  }
  isPrimeUntil(n / 2)
}
//  isPrimeUntil(n / 2)
//  isPrimeUntil(n / 3)
//  isPrimeUntil(n / 4)

}
