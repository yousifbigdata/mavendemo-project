package lookups
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.current_timestamp
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory
import utils.ParseAppConfig.returnConfigValue
object brands extends App {
  private val logger = LoggerFactory.getLogger(getClass.getName)
  val myConfig:Config = ConfigFactory.load("application.conf")
  val spark = SparkSession.builder().appName("MAshri")
    .master("local[*]").getOrCreate()
  import spark.implicits._
  val sch = "brand STRING, brand_name STRING,make STRING,make_name STRING,source STRING"
  val DF = spark.read.option("delimiter",",")
    .option("header","true")
    .schema(sch)
    .csv("D:/AWS/Mashri/Mashri_cars.csv")
    .withColumn("CreatedAt",current_timestamp())
  println("Number Of records is ==================")
  DF.createOrReplaceTempView("raw")
//  DF.show()
//  --------------------Make
  spark.sql("select *, case source " +
    "when 'haraj' then 1 " +
    "when 'Motory'then 2 " +
    "when 'SaudiSale' then 5 " +
    "when 'Syarah' then 3 " +
    "when 'Expatriates' then 4 end source_id from raw ").createOrReplaceTempView("makes")

  val brands = spark.read.format("jdbc")
    .options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.brandsTable")
    )).load().createOrReplaceTempView("brands")
  spark.sql("select brand_id , make_name from brands inner join makes " +
    "on brands.source_id = makes.source_id " +
    "AND brands.brand_name = makes.brand_name").createOrReplaceTempView("pre")
  val df = spark.sql("select distinct * from pre")

    df.write.format("jdbc").options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.brandTypesTable")
    )).mode("append").save()
//  ----------------- Brands
// spark.sql("select  distinct brand_name from raw where source like 'haraj' ")
//   .createOrReplaceTempView("haraj_pre")
//spark.sql("select 1 as source_id , brand_name from haraj_pre")
//  .createOrReplaceTempView("haraj")
//
//  spark.sql("select  distinct brand_name from raw where source like 'Motory' ")
//    .createOrReplaceTempView("Motory_pre")
//  spark.sql("select 2 as source_id , brand_name from Motory_pre")
//    .createOrReplaceTempView("Motory")
//
//  spark.sql("select  distinct brand_name from raw where source like 'Syarah' ")
//    .createOrReplaceTempView("Syarah_pre")
//  spark.sql("select 3 as source_id , brand_name from Syarah_pre")
//    .createOrReplaceTempView("Syarah")
//
//  spark.sql("select  distinct brand_name from raw where source like 'Expatriates' ")
//    .createOrReplaceTempView("Expatriates_pre")
//  spark.sql("select 4 as source_id , brand_name from Expatriates_pre")
//    .createOrReplaceTempView("Expatriates")
//
//  spark.sql("select  distinct brand_name from raw where source like 'SaudiSale' ")
//    .createOrReplaceTempView("SaudiSale_pre")
//  spark.sql("select 5 as source_id , brand_name from SaudiSale_pre")
//    .createOrReplaceTempView("SaudiSale")
//  val branddf = spark.sql("select * from haraj " +
//    "union " +
//    "select * from Motory " +
//    "union " +
//    "select * from Syarah " +
//    "union "  +
//    "select * from Expatriates " +
//    "union " +
//    "select * from SaudiSale")
//  branddf.show()

//  branddf.write.format("jdbc").options(Map(
//    "url" -> returnConfigValue("MySqlDB.dbUrl"),
//    "driver" -> returnConfigValue("MySqlDB.dbDriver"),
//    "user" -> returnConfigValue("MySqlDB.userName"),
//    "password" -> returnConfigValue("MySqlDB.password"),
//    "dbtable" -> returnConfigValue("MySqlDB.brandsTable")
//  )).mode("append").save()


}
