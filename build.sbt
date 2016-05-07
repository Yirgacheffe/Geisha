//
// This is the main build file of Geisha project
//

// --------------------------------------------------------------------------------------
name    := "Geisha"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

val slick = "com.typesafe.slick" %% "slick" % "3.1.1"
val slf4j = "org.slf4j" % "slf4j-nop" % "1.6.4"
val mysql = "mysql" % "mysql-connector-java" % "5.1.34"
val h2    = "com.h2database" % "h2" % "1.4.190"

val play_slick = "com.typesafe.play" %% "play-slick" % "1.1.0"
val play_evolu = "com.typesafe.play" %% "play-slick-evolutions" % "1.1.0"


libraryDependencies ++= Seq(
  play_slick,
  play_evolu,
  h2,
  mysql,
  slick,
  specs2 % Test
)

// --------------------------------------------------------------------------------------
