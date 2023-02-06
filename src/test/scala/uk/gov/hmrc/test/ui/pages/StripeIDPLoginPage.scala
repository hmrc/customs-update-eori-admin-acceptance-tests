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

  val pid = "pid"
  val givenName = "usersGivenName"
  val surname = "usersSurname"
  val email = "emailAddress"
  val auth_radiobtn = "success"
  val saml_radiobtn = "valid"
  val roles = "roles"
  val submitButton = "continue-button"



  def loginStub: this.type = {
    driver.findElement(By.id(pid)).sendKeys(configReader("pid"))


    driver.findElement(By.id(givenName)).sendKeys(configReader("givenName"))
    driver.findElement(By.id(surname)).sendKeys(configReader("surname"))
    driver.findElement(By.id(email)).sendKeys(configReader("email"))
    if (driver.findElement(By.id(auth_radiobtn)).isSelected)
      println("Success radio button is already selected")
    else
      driver.findElement(By.id(auth_radiobtn)).click()
    if (driver.findElement(By.id(saml_radiobtn)).isSelected)
      println("Valid radio button is already selected")
    else
      driver.findElement(By.id(saml_radiobtn)).click()
    driver.findElement(By.id(roles)).sendKeys(configReader("roles"))
    driver.findElement(By.id(submitButton)).click()
    this
  }
}
