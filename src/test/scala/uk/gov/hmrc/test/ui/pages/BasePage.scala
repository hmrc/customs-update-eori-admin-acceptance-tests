/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import org.scalatest.Tag

trait BasePage extends BrowserDriver with Matchers {
  val continueButton = "//button[@class='govuk-button']"
  val url: String = TestConfiguration.url("customs-update-eori-admin-frontend")

  def submitPage(): Unit =
    driver.findElement(By.className(continueButton)).click()
}



object AccessibilityTag extends Tag("a11y")

case class PageNotFoundException(s: String) extends Exception(s)
