val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "romme",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "org.scalactic" %% "scalactic" % "3.2.10",
      "org.scalatest" %% "scalatest" % "3.2.10" % "test"
    )
  )
  .enablePlugins(JacocoCoverallsPlugin)
