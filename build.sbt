name := """intervw-challenge"""

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.2",
  "ch.qos.logback" % "logback-classic" % "1.0.7",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "junit" % "junit" % "4.11" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
