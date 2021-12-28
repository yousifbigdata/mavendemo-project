import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.current_timestamp
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory
import utils.ParseAppConfig.returnConfigValue
object Gear extends App {
  private val logger = LoggerFactory.getLogger(getClass.getName)
  val myConfig:Config = ConfigFactory.load("application.conf")
  val spark = SparkSession.builder().appName("MAshri")
    .master("local[*]").getOrCreate()
  import spark.implicits._
  val DF = spark.read.option("delimiter",",")
    .option("header","true")
    .option("schemaInfer","true")
    .csv("D:/BigData/Results2.csv")
    .withColumn("CreatedAt",current_timestamp())
  println("Number Of records is ==================")
  DF.createOrReplaceTempView("raw")
  val Gears = spark.sql("select distinct Gear from raw where Gear is not null")
    .withColumn("created_at",current_timestamp())
    .withColumn("updated_at",current_timestamp())
  Gears.createOrReplaceTempView("Gears")
  val Gearsdf = spark.sql("select  Gear  ,created_at,updated_at from Gears")

  Gearsdf.write.format("jdbc").options(Map(
    "url" -> returnConfigValue("MySqlDB.dbUrl"),
    "driver" -> returnConfigValue("MySqlDB.dbDriver"),
    "user" -> returnConfigValue("MySqlDB.userName"),
    "password" -> returnConfigValue("MySqlDB.password"),
    "dbtable" -> returnConfigValue("MySqlDB.gearsTable")
  )).mode("append").save()

}
