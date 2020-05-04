name := "script-to-fetch-first-and-all-page"

version := "0.1"

scalaVersion := "2.12.6"

scapegoatVersion in ThisBuild := "1.3.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.11",
  "com.typesafe.akka" %% "akka-stream" % "2.6.1",
  "net.liftweb" %% "lift-json" % "3.4.1",
  "com.typesafe.akka" %% "akka-actor" % "2.5.13",
  "org.postgresql" % "postgresql" % "42.2.11",
  "org.scalikejdbc" %% "scalikejdbc" % "3.4.1",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "com.h2database" % "h2" % "1.4.196",
  "org.scalatest" %% "scalatest" % "3.1.0" % Test,
  "org.mockito" %% "mockito-scala" % "1.11.4" % Test,
  "org.apache.commons" % "commons-io" % "1.3.2",
  "org.apache.httpcomponents" % "httpclient" % "4.5.11"
)