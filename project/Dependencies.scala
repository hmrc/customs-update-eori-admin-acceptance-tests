import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.scalatestplus"             %% "selenium-4-12"  % "3.2.17.0",
    "uk.gov.hmrc"                   %% "ui-test-runner" % "0.30.0",
    "com.softwaremill.sttp.client4" %% "core"           % "4.0.0-M1"
  ).map(_ % Test)

}
