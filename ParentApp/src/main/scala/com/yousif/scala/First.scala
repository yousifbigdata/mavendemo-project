package com.yousif.scala

object First extends App {

  def wordCount(str: String): Int = {
    str match {
      case emp if isEmpty(emp) => 0
      case x => x.split("\\W+").length
    }
  }

  def isEmpty(str: String) = str == null || str.trim.isEmpty
  println("Hello from my App")

}
