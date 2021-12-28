import jdbcToFile.jdbcOracleToMapRFSConf

object Main extends  Application[jdbcOracleToMapRFSConf] {
  def main(args: Array[String]): Unit = {
    val conf: jdbcOracleToMapRFSConf = contextProvider.configuration
    println("configuraion parameters -------------------------")

    println(conf.source.driver)
    println(conf.source.url)
    println(conf.source.query)
    println(conf.target.outputPath)
    println(conf.target.format)
    println(conf.target.maxRecordsPerFile)
    println(conf.target.tableName)
    println(conf.target.transformationQuery)


    println("configuraion parameters -------------------------")
  }

}
