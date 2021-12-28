import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.current_timestamp
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory
import utils.ParseAppConfig.returnConfigValue
object BrandTypes extends App {

  private val logger = LoggerFactory.getLogger(getClass.getName)
  val myConfig:Config = ConfigFactory.load("application.conf")
  val spark = SparkSession.builder().appName("MAshri")
    .master("local[*]").getOrCreate()

  val brands = spark.read.format("jdbc")
    .options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.brandsTable")
    )).load().createOrReplaceTempView("brands")
//  spark.sql("select * from brands").show()

  val DF = spark.read.option("delimiter",",")
    .option("header","true")
    .option("schemaInfer","true")
    .csv("D:/BigData/Results2.csv")
    .withColumn("CreatedAt",current_timestamp())
  println("Number Of records is ==================")
  DF.createOrReplaceTempView("raw")
  val brandTypes = spark.sql("select distinct make as brand_types ,  brand " +
    "from raw where brand is not null and make is not null")
    .withColumn("created_at",current_timestamp())
    .withColumn("updated_at",current_timestamp())
  brandTypes.createOrReplaceTempView("brandTypes")

  val brandTypesDf = spark.sql("select distinct brand_types, brands.id as brand_id,  " +
    "brandTypes.created_at ,brandTypes.updated_at " +
    "from brands inner join brandTypes " +
    "on brands.brand = brandTypes.brand_types")

  brandTypesDf.write.format("jdbc").options(Map(
    "url" -> returnConfigValue("MySqlDB.dbUrl"),
    "driver" -> returnConfigValue("MySqlDB.dbDriver"),
    "user" -> returnConfigValue("MySqlDB.userName"),
    "password" -> returnConfigValue("MySqlDB.password"),
    "dbtable" -> returnConfigValue("MySqlDB.brandTypesTable")
  )).mode("append").save()


}
