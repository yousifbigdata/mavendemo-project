package com.yousif.scala

import com.yousif.scala.JsonExtractor.getJsonObj
import com.yousif.scala.TestMetaData.{Car, Person}
import org.apache.parquet.ShouldNeverHappenException
import org.scalatest.{FunSuite, Matchers}


class JsonExtractorsTest extends FunSuite with Matchers{
  test ("test Person Object"){
    val samplJsonStr = """{"name":"Omer","age":23}"""
    val expectedJson:Person = Person("Omer",23)
    val actualJson = getJsonObj[Person](samplJsonStr)
    actualJson shouldEqual expectedJson

  }
  test ("test Car Object"){
    val samplJsonStr = """{"name":"BMW","model":2010}"""
    val expectedJson:Car = Car("BMW",2010)
    val actualJson = getJsonObj[Car](samplJsonStr)
    actualJson shouldEqual expectedJson

  }

  test("reading and parsing String from resource file"){

  }

}
