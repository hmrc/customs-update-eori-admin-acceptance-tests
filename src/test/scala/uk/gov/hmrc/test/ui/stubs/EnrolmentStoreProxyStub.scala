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

import org.scalatest.matchers.should.Matchers
import sttp.model.StatusCode
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object EnrolmentStoreProxyStub extends TestClient with Matchers {
  val url: String = TestConfiguration.url("enrolment-store-stub")

  def createEnrolments(groupId: String, userId: String, enrolments: List[Enrolment]): Unit = {
    enrolments.foreach(insertKnownFacts)
    insertEnrolment(groupId, userId, enrolments)
  }
  private def insertKnownFacts(enrolment: Enrolment) {
    val req: String =
      s"""
         |{
         |    "service": "${enrolment.key}",
         |    "knownFacts": [
         |        {
         |            "key": "EORINumber",
         |            "value": "${enrolment.eoriNumber}",
         |            "kfType": "identifier"
         |        },
         |        {
         |            "key": "DateOfEstablishment",
         |            "value": "03/12/2000",
         |            "kfType": "verifier"
         |        }
         |    ]
         |}
         |""".stripMargin
    val result = postRequest(s"$url/known-facts", req)
    result.code shouldBe StatusCode.Created
  }
  private def insertEnrolment(groupId: String, userId: String, enrolments: List[Enrolment]) {
    val startKnownFacts: String =
      s"""
         |{
         |	"groupId": "$groupId",
         |	"affinityGroup": "Organisation",
         |	"users": [
         |		{
         |			"credId": "$userId",
         |			"name": "Default User",
         |			"email": "default@example.com",
         |			"credentialRole": "Admin",
         |			"description": "User Description"
         |		}
         |	],
         |	"enrolments": [
         |""".stripMargin

    val strEnrolments = enrolments.map { enrolment =>
      s"""
         |{
         |    "serviceName": "${enrolment.key}",
         |    "identifiers": [
         |        {
         |            "key": "EORINumber",
         |            "value": "${enrolment.eoriNumber}"
         |        }
         |    ],
         |    "enrolmentFriendlyName": "Customs Enrolment",
         |    "assignedUserCreds": [],
         |    "state": "Activated",
         |    "enrolmentType": "principal",
         |    "assignedToAll": true
         |},
         |""".stripMargin
    }.mkString("")

    val request = startKnownFacts + strEnrolments.substring(0, strEnrolments.length - 2) + "]}"
    println(request)
    val result = postRequest(s"$url/data", request)
    result.code shouldBe StatusCode.NoContent
  }

  def cleanStubData() = {
    deleteEnrolmentsData()
    deleteEnrolmentKnownFacts()
  }

  private def deleteEnrolmentsData() {
    val result = deleteRequest(s"$url/data")
    result.code shouldBe StatusCode.NoContent
  }

  private def deleteEnrolmentKnownFacts() {
    val result = deleteRequest(s"$url/known-facts")
    result.code shouldBe StatusCode.NoContent
  }
}
