package playground

object OOPPRactice extends App {

}

class Writer(firstName:String ,surName:String ,val  year:Int){
  def fullName:String =firstName + " " + surName

}

class Novel(name:String,year:Int,author:Writer){
  def authorAge:Int = year - author.year
  def isWrittenBy(author:Writer):Boolean = author == this.author
  def copy (newYear: Int):Novel= new Novel(name,newYear,author)
}
