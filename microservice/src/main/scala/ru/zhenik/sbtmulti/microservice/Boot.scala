package ru.zhenik.sbtmulti.microservice

import ru.zhenik.sbtmulti.schemas.{Entity, JsonEntityProtocol}

object Boot extends App with JsonEntityProtocol{
  import spray.json._

  val entity = Entity("uuid", "some value", Option(Map( ("key1","value1"), ("key2", "value2") )))

  println(entity.toJson)
}
