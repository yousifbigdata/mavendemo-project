package playground

object Functions extends App {
  def aFunction(a:String , b:String):String ={
    a + " " + b
  }
//  println(aFunction("Hello","Ahmed"))

  def aParameterLessFun():Int = 23

  println(aParameterLessFun())
  println(aParameterLessFun)

  def aRepeatedFun(aString: String , n:Int):String ={
    if(n == 1) aString
    else aString + aRepeatedFun(aString , n-1)
  }

  println(aRepeatedFun("Hello",5))
  def aFunctionWithSideEffect(aString:String):Unit = println(aString)

  aFunctionWithSideEffect("Hi - Yousif")


//  We can define Autx function inside a function

  def aBigFunction(n:Int):Int ={
    def aSmallFunction(a:Int , b:Int):Int = a + b
    aSmallFunction(n, n-1)
  }

//  println(aBigFunction(6))


  def aGreeting(name:String,age:Int):String= "Hi, "+ name +"My age is " + age + "Years old"

//  println(aGreeting("Yousif",43))

  def aFactorial(n:Int):Int ={
    if(n <= 1) n
      else n * aFactorial(n-1)
  }
  println(aFactorial(5000))

  def aFibo(n:Int):Int={
    if (n <=2) n
    else aFibo(n-1) + aFibo(n-2)
  }
//  println(aFibo(2))

  def isPrime(n:Int):Boolean={
    def isPrimeUntil(x:Int):Boolean={
      if(x <=1) true
      else n % x !=0 && isPrimeUntil(x -1)
    }
    isPrimeUntil(n / 2)
  }

//  println(isPrime(7))


}
