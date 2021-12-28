package playground

object DefaultArgs extends App {
  def defArgs(n:Int, acc:Int = 1):Int={
    if(n <= 1) acc
    else defArgs(n - 1, n*acc)
    defArgs(10,1)
  }

  def savePic(format:String = "bing" , width:Int , height :Int):Unit = println("Saving the Pic")
  savePic(width = 23 , height = 500)

}
