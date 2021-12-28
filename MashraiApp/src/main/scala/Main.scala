import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.current_timestamp


object Main {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("MAshrai")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val DF = spark.read.option("delimiter",",")
      .option("header","true")
      .option("schemaInfer","true")
      .csv("D:/BigData/Results2.csv")
      .withColumn("CreatedAt",current_timestamp())
    DF.show()



  }
}
