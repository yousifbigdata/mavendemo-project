import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.current_timestamp
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory
import utils.ParseAppConfig.returnConfigValue
object SparkMain extends App {
 private val logger = LoggerFactory.getLogger(getClass.getName)
 val myConfig:Config = ConfigFactory.load("application.conf")
 val spark = SparkSession.builder().appName("MAshri")
   .master("local[*]").getOrCreate()
  import spark.implicits._
 val sch = "Title STRING,Price " +
   "DOUBLE,Brand STRING,Brand_type STRING,Class STRING," +
   "model_year INT,Specs STRING,Gear STRING, Milage INT," +
   "City STRING,Telephone INT,Fuel STRING,Color STRING," +
   "Status STRING, description STRING,Date TIMESTAMP," +
   "URL STRING,Source STRING, Seller STRING,SellerLocation STRING ," +
   " Availability STRING, id STRING, CreatedAt TIMESTAMP"

 val DF = spark.read.option("delimiter",",")
    .option("header","true")
    .option("schemaInfer","true")
    .csv("D:/BigData/Results2.csv")
    .withColumn("CreatedAt",current_timestamp())
 println("Number Of records is ==================")
 DF.createOrReplaceTempView("raw")
 val brands = spark.sql("select distinct brand from raw where brand is not null")
   .withColumn("created_at",current_timestamp())
   .withColumn("updated_at",current_timestamp())
//   .filter("id is null")

// brands.show()


 brands.write.format("jdbc").options(Map(
    "url" -> returnConfigValue("MySqlDB.dbUrl"),
    "driver" -> returnConfigValue("MySqlDB.dbDriver"),
    "user" -> returnConfigValue("MySqlDB.userName"),
    "password" -> returnConfigValue("MySqlDB.password"),
    "dbtable" -> returnConfigValue("MySqlDB.brandsTable")
   )).mode("append").save()



}
