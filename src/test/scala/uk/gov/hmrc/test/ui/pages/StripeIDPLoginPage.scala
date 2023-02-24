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

object StripeIDPLoginPage extends BasePage {
  //val url: String     = TestConfiguration.url("example-frontend")

  val ele_pid           = "pid"
  val ele_givenName     = "usersGivenName"
  val ele_surname       = "usersSurname"
  val ele_email         = "emailAddress"
  val ele_auth_radiobtn = "//label[contains(text(),'Success')]/..//input"
  val ele_saml_radiobtn = "//label[contains(text(),'Valid')]/..//input"
  val ele_roles         = "roles"

  def loginStub(pid:String,givenName:String,surName:String,email:String,roles:String): this.type = {
    driver.findElement(By.id(ele_pid)).clear()
    driver.findElement(By.id(ele_pid)).sendKeys(pid)
    driver.findElement(By.id(ele_givenName)).clear()
    driver.findElement(By.id(ele_givenName)).sendKeys(givenName)
    driver.findElement(By.id(ele_surname)).clear()
    driver.findElement(By.id(ele_surname)).sendKeys(surname)
    driver.findElement(By.id(ele_email)).clear()
    driver.findElement(By.id(ele_email)).sendKeys(email)
    if (driver.findElement(By.xpath(ele_auth_radiobtn)).isSelected)
      println("Success radio button is already selected")
    else
      driver.findElement(By.xpath(ele_auth_radiobtn)).click()
    if (driver.findElement(By.xpath(ele_saml_radiobtn)).isSelected)
      println("Valid radio button is already selected")
    else
      driver.findElement(By.xpath(ele_saml_radiobtn)).click()

    driver.findElement(By.id(ele_roles)).clear()
    driver.findElement(By.id(ele_roles)).sendKeys(roles)
    this
  }
}
