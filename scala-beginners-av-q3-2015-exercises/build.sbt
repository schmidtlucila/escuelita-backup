name := "scala-beginners-excercise"

version := "0.1"

organization := "com.despegar.university.scala"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "joda-time"                       %  "joda-time"                    % "2.8",
  "org.joda"                        %  "joda-convert"                 % "1.7",
  "org.specs2" %% "specs2-core" % "3.6.5" % "test"
)
