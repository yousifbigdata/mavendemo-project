import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory
object SparkCommon {
  private val logger = LoggerFactory.getLogger(getClass.getName)
  def createSparkSession ( ): Option[SparkSession] ={
    try {

//      logger.info("Create SparkSession Method Started")
    val spark = SparkSession.builder()
        .appName("InitialLoad")
        .master("local[*]")
        .getOrCreate()

//      logger.info("SparkSession Created")
      Some(spark)
    }
    catch {
      case exception: Exception =>
//        logger.error("An error ocuured in Creating SparkSession" + exception.printStackTrace())
        System.exit(1)
        None
    }
  }


}
