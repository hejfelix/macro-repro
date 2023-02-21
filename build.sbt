val scala3Version = "3.2.0"
lazy val root = project
  .in(file("."))
  .settings(
    name := "macro-repro",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
  )
