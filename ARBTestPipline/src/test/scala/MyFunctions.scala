import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructType, TimestampType}

object MyFunctions {
  def read_schema(schema_arg : String)={
    var sch : StructType = new StructType
    val split_values = schema_arg.split(",").toList

    val d_types =Map(
      "StringType" -> StringType,
      "DoubleType" -> DoubleType,
      "IntegerType" -> IntegerType,
      "TimestampType" -> TimestampType
    )
    for(i <- split_values){
      val colValue = i.split(" ").toList
      sch = sch.add(colValue(0),d_types(colValue(1)),true)
    }
    sch
  }

}
