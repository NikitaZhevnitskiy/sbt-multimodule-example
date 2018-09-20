import sbt._
/** dependencies */
object Dependency {
  val akkaHttpV     = "10.1.3"
  val akkaStreamsV  = "2.5.14"
  val scalaTestV    = "3.0.4"
  val slickV        = "3.2.3"

  val akkaHttp            = "com.typesafe.akka"   %% "akka-http"            % akkaHttpV
  val akkaStreams         = "com.typesafe.akka"   %% "akka-stream"          % akkaStreamsV
  val akkaHttpCore        = "com.typesafe.akka"   %% "akka-http-core"       % akkaHttpV
  val akkaHttpSpray       = "com.typesafe.akka"   %% "akka-http-spray-json" % akkaHttpV

  // test dependencies
  val scalaTest           = "org.scalatest"       %% "scalatest"            % scalaTestV % Test
}
