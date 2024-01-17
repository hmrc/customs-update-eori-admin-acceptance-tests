import sbt._

object Dependencies {

  val test = Seq(
    "com.typesafe"                   % "config"            % "1.4.2"    % Test,
    "com.vladsch.flexmark"           % "flexmark-all"      % "0.64.0"   % Test,
    "org.scalatest"                 %% "scalatest"         % "3.2.17"   % Test,
    "org.scalatestplus"             %% "selenium-4-12"     % "3.2.17.0" % Test,
    "uk.gov.hmrc"                   %% "webdriver-factory" % "0.46.0"   % Test,
    "com.typesafe"                   % "config"            % "1.4.2"    % Test,
    "com.softwaremill.sttp.client4" %% "core"              % "4.0.0-M1" % Test
  )

}
