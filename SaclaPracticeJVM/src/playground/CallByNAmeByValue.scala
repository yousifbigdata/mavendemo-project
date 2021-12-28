package playground

object CallByNAmeByValue  extends App {
  def callByValue(x:Long):Unit ={
    println("Call By Value: " + x)
    println("Call By Value: " + x)
  }

  def callByName(x: =>Long):Unit={
    println("Call By Name: " + x)
    println("Call By Name: " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

}
