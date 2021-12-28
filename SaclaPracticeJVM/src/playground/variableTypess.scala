package playground

object variableTypess extends App {
  val x:Int = 23
  println(x)
//  x = 32
//  VAL IS IMMUTABLE
  val aShort : Short = 12345
  val sInt : Int = 12345678
  val aChar : Char = 'a'
  val aString :String = "String"
  val aFloat:Float = 12.34f
  val aDouble:Double = 23.76
  val aLong:Long = 123456789000L
  val aBool :Boolean = true

  // variables as side effects
  var aInt :Int = 32
  aInt =  45 // side effects


}
