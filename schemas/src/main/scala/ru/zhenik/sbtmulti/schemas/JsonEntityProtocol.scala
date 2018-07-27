package ru.zhenik.sbtmulti.schemas

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class Entity(id: String, value: String, metadata: Option[Map[String, String]] = Option.empty)

trait JsonEntityProtocol extends SprayJsonSupport with DefaultJsonProtocol {
//  implicit def entityFormat: RootJsonFormat[Entity] = jsonFormat3(Entity)
  implicit def entityFormat: RootJsonFormat[Entity] = jsonFormat(Entity, "id", "value", "metadata")
}


