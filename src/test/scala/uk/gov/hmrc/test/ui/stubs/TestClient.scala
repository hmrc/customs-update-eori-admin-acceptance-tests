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

package uk.gov.hmrc.test.ui.stubs

import sttp.client4.quick._
import sttp.model.Header

import scala.concurrent.duration.Duration

trait TestClient {
  val CONTENT_TYPE = "Content-Type"
  val JSON = "application/json"

  def postRequest(url: String, payload: String) = {
    quickRequest.body(payload)
      .withHeaders(List(Header(CONTENT_TYPE, JSON)))
      .post(uri"$url")
      .send()
  }

  def deleteRequest(url: String) = {
    quickRequest.delete(uri"$url")
      .readTimeout(Duration(5000, "ms"))
      .send()
  }
}
