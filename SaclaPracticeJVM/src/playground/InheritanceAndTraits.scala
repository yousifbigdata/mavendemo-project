package playground

object InheritanceAndTraits extends App {

 class Animal {
   def eat = println("Eating........")
 }

  class Cat extends Animal

  val cat = new Cat
  cat.eat

}
