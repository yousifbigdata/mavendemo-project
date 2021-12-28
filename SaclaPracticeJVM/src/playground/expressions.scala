package playground

object expressions extends App {
  val x:Int = 1 + 3 //Expression
  println(x)
  println(1 + 2 * 2)   //Expression
  println(1  == 2)
  println(!(1 == 2))
  var aVar = 2
  aVar += 4
  println(aVar) /// side effects

//  Instruction (tell pc to do something) vs Expression(something has a value)

//  IF Expression
  val aCondition: Boolean = true
  val aConditionValue = if (aCondition) 3 else 5
  println(aConditionValue)
  println(if (aCondition) 3 else 5)

  val aWiered = (aVar = 3)

  var i = 0
  val aWr= while (i <= 10){
    println(i)
    i+=1
  }

  val aCoodBlock = {
    val x  = 3
    val y = x + 2
    if(y > 4) "Good" else "Bye"
  }

}
