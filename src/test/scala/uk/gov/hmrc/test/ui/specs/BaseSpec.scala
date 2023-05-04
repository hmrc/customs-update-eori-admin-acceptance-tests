/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.specs

import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import uk.gov.hmrc.test.ui.pages.{CommonClass, StripeIDPLoginPage}
import uk.gov.hmrc.test.ui.stubs.EnrolmentStoreProxyStub
import uk.gov.hmrc.webdriver.SingletonDriver

import scala.util.Try

trait BaseSpec
    extends AnyFeatureSpec
    with GivenWhenThen
    with BeforeAndAfterAll
    with Matchers
    with WebBrowser
    with BrowserDriver
    with Eventually {

  //---------Auth Login Details----------//
  val pid = "12345"
  val givenName = "Automation"
  val surName = "Test"
  val email = "abcdef@hmrc.com"
  val roles = "update-enrolment-eori"

  sys.addShutdownHook {
    Try(SingletonDriver.closeInstance())
  }

  override def beforeAll() {
    CommonClass.loadPage
    StripeIDPLoginPage.loginStub(pid, givenName, surName, email, roles)
    CommonClass.clickContinueBtn
  }

  override def afterAll() {
    EnrolmentStoreProxyStub.cleanStubData()
    CommonClass.clearCookies()
  }

  override def withFixture(test: NoArgTest): Outcome = {
    val fixture = super.withFixture(test)
    if (!fixture.isSucceeded) {
      val screenshotName = test.name.replaceAll(" ", "_").replaceAll(":", "") + ".png"
      setCaptureDir("./target/test-reports/html-report/screenshots/")
      capture to screenshotName
      markup(s"<img src='screenshots/$screenshotName' />")
    }
    fixture
  }
}
