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

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.{By, WebDriver}
import uk.gov.hmrc.webdriver.SingletonDriver

object CommonClass extends BasePage {

  def loadPage: this.type = {
    val options = new ChromeOptions
    options.addArguments("--remote-allow-origins=*")
    implicit lazy val driver: WebDriver = SingletonDriver.getInstance(Some(options))
    driver.navigate().to(url)
    driver.manage().window().maximize()
    this
  }

  def onPage(webText: String): this.type = {
    if (driver.findElement(By.xpath("//h1 [contains(text(), '" + webText + "')]")).isDisplayed)
      println("User is re-directed to valid page")
    else
      println("The page is not valid")
    this
  }
  def onPageLabelValidation(webText:String): Unit ={
    if (driver.findElement(By.xpath("//label[contains(text(), '"+webText+"')]")).isDisplayed)
      println("Text displayed - "+webText)
  }
  def onPageObjectValidation(objectType:String,objectText:String): Unit ={
    if (driver.findElement(By.xpath("//"+objectType+"[contains(text(), '"+objectText+"')]")).isDisplayed)
      println(objectType+ " displayed - "+objectText)
  }
  def sucessMessageValidation(oldEORI: String,newEORI:String): this.type = {


    if (driver.findElement(By.xpath("//h1[contains(text(),'EORI number "+oldEORI+" has been replaced with "+newEORI+"')]")).isDisplayed) {
      println("User is re-directed to valid page")
      driver.findElement(By.xpath("//p[contains(text(), 'New EORI number "+newEORI+" is now subscribed to:')]")).isDisplayed
      driver.findElement(By.xpath("//p[contains(text(),'What happens next')]")).isDisplayed
      driver.findElement(By.xpath("//p[contains(text(), 'You will need to email the trader to confirm which subscriptions have been successfully updated with their new EORI number.')]")).isDisplayed
    driver.findElement(By.xpath("//p[contains(text(),'You can get help with your email in the guidance.')]")).isDisplayed
    driver.findElement(By.xpath("//button[contains(text(),'Start again')]")).isDisplayed
    } else
      println("The page is not valid")
    this
  }
  def cancelSucessMessageValidation(EORI: String): this.type = {


    if (driver.findElement(By.xpath("//h1[contains(text(),'Subscriptions cancelled for "+EORI+"')]")).isDisplayed) {
      println("User is re-directed to valid page")
      driver.findElement(By.xpath("//p[contains(text(), 'EORI number "+EORI+" subscriptions have been successfully cancelled for:')]")).isDisplayed
      driver.findElement(By.xpath("//p[contains(text(),'What happens next')]")).isDisplayed
      driver.findElement(By.xpath("//p[contains(text(), 'You will need to email the trader to confirm which EORI number "+EORI+" subscriptions have been successfully cancelled.')]")).isDisplayed
      driver.findElement(By.xpath("//p[contains(text(),'You can get help with your email in the guidance.')]")).isDisplayed
      driver.findElement(By.xpath("//button[contains(text(),'Start again')]")).isDisplayed
    } else
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
  def errorMessageValidation(errormsg: String): this.type = {


    if (driver.findElement(By.xpath("//a[contains(text(),'"+errormsg+"')]")).isDisplayed) {
      println("Error message displayed -- "+errormsg)
      } else
      println("The page is not valid")
    this
  }
}
