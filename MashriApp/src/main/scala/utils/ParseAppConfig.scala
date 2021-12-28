package utils

import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

object ParseAppConfig {
  private val logger = LoggerFactory.getLogger(getClass.getName)

  def readAppConfigFile(): Config = {
    ConfigFactory.load("application.conf")
  }
  //    def fetchPGTargetTable(): String = {
  //      val pgTargetTable = readJsonFile().getString("body.pg_target_table")
  //      pgTargetTable
  //    }
  def returnConfigValue(key : String): String = {
    val value = readAppConfigFile().getString(key)
    value
  }

}
