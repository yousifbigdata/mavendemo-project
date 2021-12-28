package lookups
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.current_timestamp
import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory
import utils.ParseAppConfig.returnConfigValue
object dic_brand extends App {
  private val logger = LoggerFactory.getLogger(getClass.getName)
  val myConfig:Config = ConfigFactory.load("application.conf")
  val spark = SparkSession.builder().appName("MAshri")
    .master("local[*]").getOrCreate()
  import spark.implicits._

  val brands = spark.read.format("jdbc")
    .options(Map(
      "url" -> returnConfigValue("MySqlDB.dbUrl"),
      "driver" -> returnConfigValue("MySqlDB.dbDriver"),
      "user" -> returnConfigValue("MySqlDB.userName"),
      "password" -> returnConfigValue("MySqlDB.password"),
      "dbtable" -> returnConfigValue("MySqlDB.brandsTable")
    )).load().createOrReplaceTempView("brands")
  spark.sql("select *, " +
    "case " +
    "when brand_name in ('تويوتا','Toyota')then 'Toyota' " +
    "when brand_name in ('اودي','Audi') then 'Audi' " +
    "when brand_name in ('بنتلي','Bentley') then 'Bentley' " +
    "when brand_name in ('بي ام دبليو','BMW') then 'BMW' " +
    "when brand_name in ('بي واي دي','BYD') then 'BYD' " +
    "when brand_name in ('كاديلاك','Cadillac','Caddilac') then 'Cadillac' " +
    "when brand_name in ('شانجان','Changan') then 'Changan' " +
    "when brand_name in ('شيفروليه','Chevrolet','شفروليه') then 'Chevrolet' " +
    "when brand_name in ('فاو','Faw','فاو Faw') then 'Faw' " +
    "when brand_name in ('فيراري','Ferrari') then 'Ferrari' " +
    "end brand_name_en " +
    "from brands").show(truncate = false)

//  df.write.format("jdbc").options(Map(
//    "url" -> returnConfigValue("MySqlDB.dbUrl"),
//    "driver" -> returnConfigValue("MySqlDB.dbDriver"),
//    "user" -> returnConfigValue("MySqlDB.userName"),
//    "password" -> returnConfigValue("MySqlDB.password"),
//    "dbtable" -> returnConfigValue("MySqlDB.brandTypesTable")
//  )).mode("append").save()

  //  branddf.write.format("jdbc").options(Map(
  //    "url" -> returnConfigValue("MySqlDB.dbUrl"),
  //    "driver" -> returnConfigValue("MySqlDB.dbDriver"),
  //    "user" -> returnConfigValue("MySqlDB.userName"),
  //    "password" -> returnConfigValue("MySqlDB.password"),
  //    "dbtable" -> returnConfigValue("MySqlDB.brandsTable")
  //  )).mode("append").save()


}
