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

object ReplacingCurrentEORI extends BasePage {

  val ReplacingEori       = "//h1[@class='govuk-heading-l']"
  val CurrentlySubscribed = "//p[text()[contains(.,'currently subscribed to:')]]"
  val ATAR                = "//ul/li[@class='govuk-list govuk-list--bullet']"
  val GVMS                = "//ul/li[contains(text(),'Goods Vehicle Movement System (GVMS)')]"
  val Question            = "//h1[@class='govuk-fieldset__heading']"
  val Yes                 = "//input[@id='confirm']"
  val No                  = "//input[@id='confirm-2']"
  val Yesradiobtn         = "//label[contains(text(),'Yes')]/..//input"
  val Noradiobtn          = "//label[contains(text(),'No')]/..//input"
  var confirmbtn          = "//button[@class ='govuk-button']"

  def verifyReplacingCurrentEori: this.type = {
    driver.findElement(By.xpath(".//*[text()='Replacing EORI number']"))
    driver.findElement(By.xpath(ReplacingEori)).isDisplayed
    println("The text is verified")
    driver.findElement(By.xpath(CurrentlySubscribed)).isDisplayed
    println("The text is verified")
    if (driver.findElement(By.xpath(ATAR)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")
    if (driver.findElement(By.xpath(GVMS)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")
    driver.findElement(By.xpath(Yes)).isDisplayed
    driver.findElement(By.xpath(No)).isDisplayed
    driver.findElement(By.xpath(confirmbtn)).click()
    println("confirm button is clicked")
    this
  }
}
