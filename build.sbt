val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "romme",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.google.inject" % "guice" % "4.2.3"),
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.0.2")
      .cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("org.scala-lang.modules" %% "scala-xml" % "2.0.1"),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % "0.14.1"),
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.9.2")
      .cross(CrossVersion.for3Use2_13),
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(
        JacocoReportFormats.ScalaHTML,
        JacocoReportFormats.XML
      ), // note XML formatter
      "utf-8"
    ),
    jacocoCoverallsServiceName := "github-actions",
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN"),
    jacocoExcludes := Seq("*aview.*")
  )
  .enablePlugins(JacocoCoverallsPlugin)
