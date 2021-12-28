import org.apache.spark.sql.SparkSession

object SparkMain {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Test")
      .master("local[*]")
      .getOrCreate()
  }

}
