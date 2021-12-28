package playground

object StringOps extends App {

  val str:String = "This is Scala Program"
  println(str.charAt(2))
  println(str.substring(4,7))
  println(str.split(" ").toList)
  println(str.startsWith("is"))
  println(str.endsWith("Program"))
  println(str.replace(" ","-"))
  println(str.length)
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.take(2))
//  Scala -Specific -- Str interpolator
//  S- interpolator
  val name:String = "Yousif"
  val age:Int = 35
  val greeting:String = s"Hello am $name and my age is $age"
  println(greeting)
  println(s"Am $name am around ${age + 2}")
//  raw-interpolator

  println(raw"my name /n Yousif")


}
