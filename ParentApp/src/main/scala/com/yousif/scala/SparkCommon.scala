package com.yousif.scala

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object SparkCommon {
  private val logger = LoggerFactory.getLogger(getClass.getName)
  def createSparkSession(): Option[SparkSession]={
    try
      {
        logger.warn("Spark Create Session Started")
        val spark = SparkSession.builder()
          .appName("SparkIncLoad")
          .master("local[*]")
          .enableHiveSupport()
          .getOrCreate()
        logger.warn("Spark Create Session Started")
        Some(spark)

      }
    catch {
      case e:Exception =>
        logger.error("An error occured while creating Spark Session")
        System.exit(1)
        None
    }
  }

}
