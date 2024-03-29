lazy val testSuite = (project in file("."))
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    name := "customs-update-eori-admin-acceptance-tests",
    version := "0.1.0",
    scalaVersion := "2.13.12",
    scalacOptions ++= Seq("-feature"),
    libraryDependencies ++= Dependencies.test
  )
