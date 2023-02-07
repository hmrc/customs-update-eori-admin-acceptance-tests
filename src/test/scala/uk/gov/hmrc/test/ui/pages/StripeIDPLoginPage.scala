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


  val ele_pid = "pid"
  val ele_givenName = "usersGivenName"
  val ele_surname = "usersSurname"
  val ele_email = "emailAddress"
  val ele_auth_radiobtn = "success"
  val ele_saml_radiobtn = "valid"
  val ele_roles = "roles"
  val ele_submitButton = "continue-button"



//  def loginStub: this.type = {
//    driver.findElement(By.id(ele_pid)).sendKeys(configReader("pid"))
//
//
//    driver.findElement(By.id(ele_givenName)).sendKeys(configReader("givenName"))
//    driver.findElement(By.id(ele_surname)).sendKeys(configReader("surname"))
//    driver.findElement(By.id(ele_email)).sendKeys(configReader("email"))
//    if (driver.findElement(By.id(ele_auth_radiobtn)).isSelected)
//      println("Success radio button is already selected")
//    else
//      driver.findElement(By.id(ele_auth_radiobtn)).click()
//    if (driver.findElement(By.id(ele_saml_radiobtn)).isSelected)
//      println("Valid radio button is already selected")
//    else
//      driver.findElement(By.id(ele_saml_radiobtn)).click()
//    driver.findElement(By.id(ele_roles)).sendKeys(configReader("roles"))
//    driver.findElement(By.id(ele_submitButton)).click()
//    this
//  }
  def loginStub(pid:String,Givenname:String,Surname:String,Email:String,Roles:String): Unit ={
    driver.findElement(By.id(ele_pid)).sendKeys(pid)


    driver.findElement(By.id(ele_givenName)).sendKeys(Givenname)
    driver.findElement(By.id(ele_surname)).sendKeys(Surname)
    driver.findElement(By.id(ele_email)).sendKeys(Email)
    if (driver.findElement(By.id(ele_auth_radiobtn)).isSelected)
      println("Success radio button is already selected")
    else
      driver.findElement(By.id(ele_auth_radiobtn)).click()
    if (driver.findElement(By.id(ele_saml_radiobtn)).isSelected)
      println("Valid radio button is already selected")
    else
      driver.findElement(By.id(ele_saml_radiobtn)).click()
    driver.findElement(By.id(ele_roles)).sendKeys(Roles)
    driver.findElement(By.id(ele_submitButton)).click()
  }
}
