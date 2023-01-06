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
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object EoriServiceMgmt extends BasePage {
  val url: String     = TestConfiguration.url("example-frontend") + "/customs-update-eori-admin-frontend"
  val vatReturnPeriod = "EEORI number management service"

  //val ReplaceRadioButton = "update-eori"
  // val CancelRadioButton  = "cancel-eori"

  def loadPage: this.type = {
    driver.navigate().to(url)
    onPage(vatReturnPeriod)
    this
  }

  def eorimgmtserice(period: String): this.type = {
    period match {
      //case "Replace" => driver.findElement(By.id(ReplaceRadioButton)).click()
      case "Replace" => driver.findElement(By.xpath("//input[@id='update-eori']")).click()
      //case "Cancel"  => driver.findElement(By.id(CancelRadioButton)).click()
      case "Cancel"  => driver.findElement(By.xpath("//input[@id='cancel-eori']")).click()
    }
    ContinuePage()
    this
  }
}
