import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.current_timestamp
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory
import utils.ParseAppConfig.returnConfigValue
object Products extends App {
  private val logger = LoggerFactory.getLogger(getClass.getName)
  val myConfig:Config = ConfigFactory.load("application.conf")
  val spark = SparkSession.builder().appName("MAshri")
    .master("local[*]")
//    .config("spark.unsafe.sorter.spill.read.ahead.enable","false")
    .config("spark.local.dir", "D:\\CDH")
    .getOrCreate()
  import spark.implicits._

  val DF = spark.read.option("delimiter",",")
    .option("header","true")
    .option("schemaInfer","true")
    .csv("D:/BigData/Results2.csv")
    .withColumn("deleted_at",current_timestamp())
  println("Number Of records is ==================")
  DF.createOrReplaceTempView("raw")
  val products = spark.sql("select description, 1 as user_id ,cast(price as double), " +
    "1 as shape_id ," +
    "source , make,Gear,city ,brand , cast(year as int ) , cast(Milage as int ), Date, " +
    "URL , Telephone , 1 as published ,  deleted_at" +
    "  from raw " +
    "where make is not null " +
    "AND " +
    "Gear is not null " +
    "AND " +
    "Gear is not null " +
    "AND " +
    "city is not null " +
    "AND " +
    "brand is not null")
    .withColumn("created_at",current_timestamp())
    .withColumn("updated_at",current_timestamp())
  products.createOrReplaceTempView("products")

//  products.show()

  spark.read.format("jdbc")
    .options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.brandTypesTable")
    )).load().createOrReplaceTempView("brand_types")
  spark.read.format("jdbc")
    .options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.citiesTable")
    )).load().createOrReplaceTempView("cities")
  spark.read.format("jdbc")
    .options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.gearsTable")
    )).load().createOrReplaceTempView("gears")
  spark.read.format("jdbc")
    .options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.sourcesTable")
    )).load().createOrReplaceTempView("sources")

  val joineddf = spark.sql("select description," +
    "user_id ," +
    "price, " +
    "1 as shape_id," +
    "cities.id as city_id," +
    "gears.id as gear_id," +
    "sources.id as source_id ," +
    "brand_types.id as brand_type_id," +
    "year as model_year," +
    "Milage as kilometer ," +
    "date ," +
    "url ," +
    "Telephone as contact ," +
    "published ," +
    "deleted_at ," +
    " products.created_at, " +
    "products.updated_at " +
    "from " +
    "products inner join cities on products.city = cities.city " +
    "inner join gears on products.gear = gears.gear " +
    "inner join sources on products.source = sources.title " +
    "inner join brand_types on products.make = brand_types.brand_types")

//  joineddf.show()
  joineddf.write.format("jdbc").options(Map(
    "url" -> returnConfigValue("MySqlDB.dbUrl"),
    "driver" -> returnConfigValue("MySqlDB.dbDriver"),
    "user" -> returnConfigValue("MySqlDB.userName"),
    "password" -> returnConfigValue("MySqlDB.password"),
    "dbtable" -> returnConfigValue("MySqlDB.productsTable")
  )).mode("append").save()

}
