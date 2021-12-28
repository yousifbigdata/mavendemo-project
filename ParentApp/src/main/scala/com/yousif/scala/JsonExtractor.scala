package com.yousif.scala

import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods.parse

import scala.util.{Failure, Success, Try}

object JsonExtractor {
  private def parseJsonObj[T](jsonString:String)(implicit m: Manifest[T]):Try[T]={
    implicit val formats : DefaultFormats.type  = DefaultFormats
    Try{
      parse(jsonString).extract[T]
    }
  }
  def  getJsonObj[T](jsonString:String)(implicit m: Manifest[T]):T= {
    parseJsonObj(jsonString) match {
      case Success(parsedObj) => parsedObj
      case Failure(exc) => throw new IllegalArgumentException(exc)
    }
  }

}
