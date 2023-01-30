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
import uk.gov.hmrc.test.ui.pages.StripeIDPLoginPage.url

object CommonClass extends BasePage {

  def loadPage: this.type = {
    driver.navigate().to(url)
    //onPage(vatReturnPeriod)
    this
  }

  def onPage(webText: String): this.type = {
    if (driver.findElement(By.xpath("//h1 [contains(text(), '" + webText + "')]")).isDisplayed)
      println("User is re-directed to valid page")
    else
      println("The page is not valid")
    this
  }

  def clickBackLink: this.type = {

    driver.findElement(By.xpath("//a[contains(text(), 'Back')]")).click()

    this
  }

  def clickContinueBtn: this.type = {

    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click()

    this
  }

}
