import scalariform.formatter.preferences._

// Project setup
val rootPackage = "ru.zhenik"
val subRootPackage = s"$rootPackage.sbtmulti"
val projectV = "0.0.1-SNAPSHOT"
val scalaV = "2.12.6"

// https://www.scala-sbt.org/release/docs/Basic-Def-Examples.html
lazy val settings = Seq(

  organization := s"$subRootPackage",
  version := projectV,
  scalaVersion := scalaV,

  test in assembly := {},

  // set the main Scala source directory to be <base>/src
  scalaSource in Compile := baseDirectory.value / "src/main/scala",

  // set the Scala test source directory to be <base>/test
  scalaSource in Test := baseDirectory.value / "src/test/scala",

  // append several options to the list of options passed to the Java compiler
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),

  // set the initial commands when entering 'console' or 'consoleQuick', but not 'consoleProject'
  initialCommands in console := "import ru.zhenik.sbtmulti._",

  // only use a single thread for building
  parallelExecution := false,

)

                                    /** projects */
lazy val rootProject = project
  .in(file("."))
  .settings(
    name := "sbtmulti",
    organization := rootPackage,
    version := projectV
  )
  .aggregate(
    schemas,
    microservice
  )

lazy val schemas = project
  .settings(
    name := "schemas",
    settings,
    libraryDependencies ++= Seq(Dependency.akkaHttpSpray)
  )

lazy val microservice = project
  .settings(
    name := "microservice",
    settings,
    libraryDependencies ++= commonDependencies ++ testDependencies,
    mainClass in assembly := Some(s"$subRootPackage.microservice.Boot"),
    assemblyJarName in assembly := "microservice-fat-jar.jar"
  )
  .dependsOn(schemas)

lazy val commonDependencies = Seq(
  Dependency.akkaHttp,
  Dependency.akkaStreams,
  Dependency.akkaHttpCore
)

lazy val testDependencies = Seq(
  Dependency.scalaTest
)

// code formatter, executed on goal:compile by default
scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)