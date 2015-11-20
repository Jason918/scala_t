name := "t"

version := "1.0"

scalaVersion := "2.11.7"

val akkaVersion = "2.4.0"
libraryDependencies ++= Seq (
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)
    