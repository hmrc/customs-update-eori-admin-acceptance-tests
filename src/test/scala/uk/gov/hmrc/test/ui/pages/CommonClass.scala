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

object CommonClass extends BasePage {

  def loadPage: this.type = {
    driver.navigate().to(url)
    this
  }

  def onPage(webText: String): this.type = {
    if (driver.findElement(By.xpath("//h1 [contains(text(), '" + webText + "')]")).isDisplayed)
      println("User is re-directed to valid page")
    else
      println("The page is not valid")
    this
  }
  def sucessMessageValidation(oldEORI: String,newEORI:String): this.type = {
    if (driver.findElement(By.xpath("//h2 [contains(text(), 'Success')]")).isDisplayed)
      println("User is re-directed to valid page")
    else
      println("The page is not valid")

    if (driver.findElement(By.xpath("//h3 [contains(text(), 'EORI number "+oldEORI+" has been replaced with "+newEORI+".')]")).isDisplayed)
      println("User is re-directed to valid page")
    else
      println("The page is not valid")
    this
  }
  def clickBackLink: this.type = {

    driver.findElement(By.xpath("//a[contains(text(), 'Back')]")).click()

    this
  }
  def selectRadioOption(radioOption:String): this.type = {

    radioOption match {
      case "Cancel" => driver.findElement(By.id("update-or-cancel-eori-2")).click()
      case _ => driver.findElement(By.id("update-or-cancel-eori")).click()
    }
    this
  }

  def clickContinueBtn: this.type = {

    driver.findElement(By.className("govuk-button")).click()
    this
  }
  def clickEORINumberMgntLink: this.type = {

    driver.findElement(By.className("hmrc-internal-header__link")).click()

    this
  }
}
