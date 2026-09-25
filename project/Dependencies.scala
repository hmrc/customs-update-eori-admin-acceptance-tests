import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.scalatestplus"             %% "selenium-4-21"  % "3.2.19.0",
    "uk.gov.hmrc"                   %% "ui-test-runner" % "0.56.0",
    "com.softwaremill.sttp.client4" %% "core"           % "4.0.26"
  ).map(_ % Test)

}
