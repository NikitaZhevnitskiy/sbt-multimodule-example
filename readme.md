# sbt multi module project
This repository is setup to provide a basic understanding of [SBT](https://github.com/sbt/sbt)

## Goals
* create multi module sbt project
* set inner module as a dependency to another inner module 
* produce fat-jar (uber jar) 
* docker plugin

### Multimodule project
Sbt gives a lot of freedom and flexibility in project build definition. 
I like the way how its defined in [official documentation](https://www.scala-sbt.org/release/docs/Basic-Def-Examples.html).
Root module with 2 sub modules have next view: 
```scala
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
    libraryDependencies ++= Seq(dependencies.akkaHttpSpray)
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
```


### Fat-jar
Plugin assembly defined in `/project/assembly.sbt`.

```scala
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.7")
``` 

Setup main class for each project in `build.sbt`. Optionally you can set name for `.jar`.  

```scala
lazy val microservice = project
  .settings(
    mainClass in assembly := Some(s"ru.zhenik.sbtmulti.microservice.Boot"),
    assemblyJarName in assembly := "microservice-fat-jar.jar"
  )
```

To produce fat-jar execute `sbt assembly`
