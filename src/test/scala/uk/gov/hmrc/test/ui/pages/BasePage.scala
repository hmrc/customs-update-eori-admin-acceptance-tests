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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.driver.BrowserDriver

import java.util.Properties

trait BasePage extends BrowserDriver with Matchers {
  val continueButton = "//button[@class='govuk-button']"
  var filepath       = "/Users/lathamageshwarimurugan/Document/EORI/customs-update-eori-admin-acceptance-tests/project/Config.properties"
  var url            = "http: //localhost:9000/customs-update-eori-admin-frontend"
  var pid            = "1212"
  var givenName      = "Test"
  var surname        = "User"
  var email          = "latha.murugan@digital.hmrc.gov.uk"
  var roles          = "update-enrolment-eori"
  var currentEori    = "GB023002091028"
  var ele_date       = "21"
  var ele_month      = "09"
  var ele_year       = "2001"
  var newEori        = "GB023002091029"

  def submitPage(): Unit =
    driver.findElement(By.className(continueButton)).click()

  def configReader(key: String): String = {
    //   val reader = new BufferedReader(new FileReader(fileppath))

    var properties = new Properties
    properties = new Properties
    // properties.load(reader)
    val value      = properties.getProperty(key)
    return value
  }

}

case class PageNotFoundException(s: String) extends Exception(s)
