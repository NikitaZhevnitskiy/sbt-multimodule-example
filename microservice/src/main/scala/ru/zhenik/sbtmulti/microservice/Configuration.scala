package ru.zhenik.sbtmulti.microservice

import com.typesafe.config.ConfigFactory

case class Configuration(http: HttpConfig)
case class HttpConfig(host: String, port: Int)

private[microservice] object Configuration {
  def get(): Configuration = {
    val configFromFile = ConfigFactory.load()
    Configuration(
      HttpConfig(
        configFromFile.getString("microservice.http.host"),
        configFromFile.getInt("microservice.http.port")
      )
    )
  }
}
