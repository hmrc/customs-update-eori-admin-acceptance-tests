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

object ReplaceExistingEORINumber extends BasePage {

  val currentEORINumber = "//input[@id = 'existing-eori']"
  val day               = "//input[@id = 'txtDateOfEstablishmentDay']"
  val month             = "//input[@id = 'txtDateOfEstablishmentMonth']"
  val year              = "//input[@id = 'txtDateOfEstablishmentYear']"
  val newEORInumber     = "//input[@id = 'new-eori']"

  def replaceEORI: this.type = {

    driver.findElement(By.xpath(currentEORINumber)).sendKeys("GB0000020230155")
    driver.findElement(By.xpath(day)).sendKeys("20")
    driver.findElement(By.xpath(month)).sendKeys("04")
    driver.findElement(By.xpath(year)).sendKeys("1992")
    driver.findElement(By.xpath(newEORInumber)).sendKeys("GB0000020230156")

    this
  }

}
