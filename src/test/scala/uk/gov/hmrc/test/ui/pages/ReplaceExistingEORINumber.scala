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

object ReplaceExistingEORINumber extends BasePage {

  val ele_currentEORINumber = "existing-eori"
  val ele_day               = "txtDateOfEstablishmentDay"
  val elem_month             = "txtDateOfEstablishmentMonth"
  val elem_year              = "txtDateOfEstablishmentYear"
  val ele_newEORInumber     = "new-eori"

  def replaceEORI(currentEORI:String,day:String,month:String,year:String,newEORI:String): this.type = {
    driver.findElement(By.id(ele_currentEORINumber)).clear()
    driver.findElement(By.id(ele_currentEORINumber)).sendKeys(currentEORI)
    driver.findElement(By.id(ele_day)).clear()
    driver.findElement(By.id(ele_day)).sendKeys(day)
    driver.findElement(By.id(elem_month)).clear()
    driver.findElement(By.id(elem_month)).sendKeys(month)
    driver.findElement(By.id(elem_year)).clear()
    driver.findElement(By.id(elem_year)).sendKeys(year)
    driver.findElement(By.id(ele_newEORInumber)).clear()
    driver.findElement(By.id(ele_newEORInumber)).sendKeys(newEORI)

    this
  }
  def cancelEORI(currentEORI:String,day:String,month:String,year:String): this.type = {
    driver.findElement(By.id(ele_currentEORINumber)).clear()
    driver.findElement(By.id(ele_currentEORINumber)).sendKeys(currentEORI)
    driver.findElement(By.id(ele_day)).clear()
    driver.findElement(By.id(ele_day)).sendKeys(day)
    driver.findElement(By.id(elem_month)).clear()
    driver.findElement(By.id(elem_month)).sendKeys(month)
    driver.findElement(By.id(elem_year)).clear()
    driver.findElement(By.id(elem_year)).sendKeys(year)

    this
  }
}
