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

object CustomsUpdateEORISelection extends BasePage {

  val header = "//a[contains(text(),'EORI number management service')]"
  val questionText = "//h1[contains(text(),'Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?')]"
  val replaceBtnText = "//label[contains(text(),'Replace an existing EORI number')]/..//input"
  val cancelBtnText = "//label[contains(text(),'Cancel subscriptions to HMRC services')]/..//input"

  def eoriSelectRadioBtn(radiobtn: String): this.type = {

    driver.findElement(By.xpath("//label[contains(text(),'" + radiobtn + "')]/..//input")).click()

    this
  }

  def verifyEORISelectionPage: this.type = {

    if (driver.findElement(By.xpath(header)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")
    if (driver.findElement(By.xpath(questionText)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")

    if (driver.findElement(By.xpath(replaceBtnText)).isEnabled)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")
    if (driver.findElement(By.xpath(cancelBtnText)).isEnabled)
      println("The text is verified")
    else {
      println("The text is wrong and can't be verified")

    }

    this

  }

}
