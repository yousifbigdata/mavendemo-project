package playground

object OOP extends App {
 val person = new Person("Yousif", 34)
  println(person)
  person.greet("Omer")
  person.greet

}
// Constructor
// class parameters are not FIELDS
class Person(name:String,age:Int){
  val x = 2
  println(1 + 4)

  def greet(name:String):Unit=println(s"${this.name} says hi $name")
  def greet():Unit=println(s"${this.name} says hi $name")

//  Multiple Constructors allawed by using this

}

