import MyFunctions.read_schema
import SparkCommon.createSparkSession
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.SparkSession

import java.lang.System.currentTimeMillis
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object InitialLoad {
  def main(args: Array[String]): Unit = {
    val spark = createSparkSession.get
    val sc = spark.sparkContext
    import spark.implicits._
    //Reading Landing Data using Config
    val myConfig:Config = ConfigFactory.load("application.conf")
    val myInputLocation = myConfig.getString("paths.inputLocation")
    val myOutputLocation = myConfig.getString("paths.outputLocation")
    val landingFileSchemaFromFile =myConfig.getString("schema.landingFileSchema")
    val queryTrans =myConfig.getString("target.transformationQuery")

    val landingFileSchema = read_schema(landingFileSchemaFromFile)
    val landingDF = spark.read
      .option("delimiter","|")
      .schema(landingFileSchema)
      .csv( myInputLocation )
      .selectExpr("*","cast(Sale_Date as String) as S_Date","year(Sale_Date) as Year",
        "month(Sale_Date) as Month","day(Sale_Date) as Day")

    landingDF.show()
//    val landingDFWithPartitions = landingDF.selectExpr(queryTrans)


    val ResDF = landingDF
      .write
      .mode("overwrite")
      .partitionBy("year","month","day")

      .csv(myOutputLocation+"Results")
  }
}
