package playground

object OOP3 extends App {
//  class level functionality , functionality that doesn't depend on the instance of a class
//  Scala doesn't have a class level functionality(STATIC)
  object Person{
  val N_EYES = 2
  def canFly:Boolean = false
//  scala object is a singleton instance
}

  println(Person.N_EYES)
  println(Person.canFly)


}
