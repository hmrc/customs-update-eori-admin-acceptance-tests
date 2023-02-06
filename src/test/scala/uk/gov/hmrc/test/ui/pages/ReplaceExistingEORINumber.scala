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

  val currentEORINumber = "existing-eori"
  val day               = "txtDateOfEstablishmentDay"
  val month             = "txtDateOfEstablishmentMonth"
  val year              = "txtDateOfEstablishmentYear"
  val newEORInumber     = "new-eori"

  def replaceEORI: this.type = {

    driver.findElement(By.id(currentEORINumber)).sendKeys("GB0000020230155")
    driver.findElement(By.id(day)).sendKeys("20")
    driver.findElement(By.id(month)).sendKeys("04")
    driver.findElement(By.id(year)).sendKeys("1992")
    driver.findElement(By.id(newEORInumber)).sendKeys("GB0000020230156")

    this
  }

}
