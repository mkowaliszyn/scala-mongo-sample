name := "scala-mongo-sample"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.mongodb" %% "casbah" % "2.7.3",
  "com.github.jmkgreen.morphia" % "morphia" % "1.2.3"
)
