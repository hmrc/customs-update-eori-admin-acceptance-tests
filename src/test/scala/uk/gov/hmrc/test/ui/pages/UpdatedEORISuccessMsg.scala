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
object UpdatedEORISuccessMsg extends BasePage {

  val successmsg   = "//h1[@class='govuk-fieldset__heading']"
  val replacedeori = "//h3[@class='govuk-notification-banner__heading']"

  def verifysuccessMsg: this.type = {

    driver.findElement(By.xpath(successmsg)).isDisplayed
    driver.findElement(By.xpath(replacedeori)).isDisplayed
    driver.findElement(By.xpath("//div[@class='govuk-notification-banner__header']")).isDisplayed
    this
  }
}
