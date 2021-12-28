package com.yousif.scala
import org.scalatest.FunSuite

class MainTest extends FunSuite{
  test("test wordCount with input"){
    assert(First.wordCount("test with arg") == 3)
  }
  test("with no ars"){
    assert(First.wordCount(str = null) == 0)
  }


}
