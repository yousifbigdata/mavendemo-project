package playground

object OOP2 extends App {
  class Person(val name :String , favoriteMovie :String){
    def likes(movie :String):Boolean= movie ==favoriteMovie
    def +(person: Person):String = s"${this.name} is hangOutWith ${person.name} "
    def apply():String = s"my name is $name I like $favoriteMovie "
  }

  val mary = new Person("Mary","300")
  println(mary.likes("300"))
  println(mary likes "300") // This call infix notation == operator notation --works with mwthod has one parameters

//  "Operators in Scala
  val tom = new Person("Tom","fast")
  println( mary + tom)
  println( mary .+ (tom))

//  prefix notation
//  apply

  println(mary.apply())
  println(mary()) // equivalent


}
