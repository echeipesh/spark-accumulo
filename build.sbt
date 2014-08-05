name := "Spark Accumulo"

organization := ""

version := "0.0.1"

scalaVersion := "2.10.4"

resolvers ++= Seq("cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0.M5b",
  "org.apache.spark" %% "spark-core" % "1.0.0",
  "org.apache.hadoop" % "hadoop-client" % "1.2.1",
  "org.apache.accumulo" % "accumulo-core" % "1.5.1"
)

initialCommands := "import .sparkaccumulo._"

