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
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object StripeIDPLoginPage extends BasePage {
  val url: String     = TestConfiguration.url("example-frontend")

  val pid = "//input[@id = 'pid']"
  val givenName = "//input[@id='usersGivenName']"
  val surname = "//input[@id='usersSurname']"
  val email = "//input[@id='emailAddress']"
  val auth_radiobtn = "//label[contains(text(),'Success')]/..//input"
  val saml_radiobtn = "//label[contains(text(),'Valid')]/..//input"
  val roles = "//textarea[@id='roles']"
  val submitButton = "//button[@id='continue-button']"



  def loginStub: this.type = {
    driver.findElement(By.xpath(pid)).sendKeys(configReader("pid"))


    driver.findElement(By.xpath(givenName)).sendKeys(configReader("givenName"))
    driver.findElement(By.xpath(surname)).sendKeys(configReader("surname"))
    driver.findElement(By.xpath(email)).sendKeys(configReader("email"))
    if (driver.findElement(By.xpath(auth_radiobtn)).isSelected)
      println("Success radio button is already selected")
    else
      driver.findElement(By.xpath(auth_radiobtn)).click()
    if (driver.findElement(By.xpath(saml_radiobtn)).isSelected)
      println("Valid radio button is already selected")
    else
      driver.findElement(By.xpath(saml_radiobtn)).click()
    driver.findElement(By.xpath(roles)).sendKeys(configReader("roles"))
    driver.findElement(By.xpath(submitButton)).click()
    this
  }
}
