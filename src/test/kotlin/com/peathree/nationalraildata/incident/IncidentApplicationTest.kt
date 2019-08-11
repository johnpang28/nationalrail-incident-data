package com.peathree.nationalraildata.incident

import com.peathree.nationalraildata.incident.jms.IncidentReceiver
import com.peathree.nationalraildata.incident.model.AffectedOperator
import com.peathree.nationalraildata.incident.model.InfoLink
import com.peathree.nationalraildata.incident.model.Source
import org.apache.activemq.command.ActiveMQTextMessage
import org.assertj.core.api.Assertions.assertThat
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import org.springframework.core.io.ClassPathResource
import org.springframework.jms.core.JmsTemplate
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.TimeUnit.SECONDS

@SpringBootTest(webEnvironment = NONE)
@ActiveProfiles("test")
class IncidentApplicationTest {

    @Autowired
    lateinit var jmsTemplate: JmsTemplate

    @Autowired
    lateinit var receiver: IncidentReceiver

    @Test
    fun `should receive incident`() {
        val rawMessage = ClassPathResource("incident-message.xml").file.readText()

        jmsTemplate.send("kb.incidents") { ActiveMQTextMessage().apply { text = rawMessage } }

        await().atMost(2, SECONDS).untilAsserted {
            assertThat(receiver.incidents()).hasSize(1)

            with(receiver.incidents()[0]) {
                assertThat(creationTime).isEqualTo("2019-08-02T22:34:00.000Z")
                assertThat(changeHistory!!.changedBy).isEqualTo("bives")
                assertThat(changeHistory!!.lastChangedDate).isEqualTo("2019-08-04T11:03:00.000Z")
                assertThat(participantRef).isEqualTo("96010")
                assertThat(incidentNumber).isEqualTo("467679F7607440969A8CF6929324BBE3")
                assertThat(version).isEqualTo("20190804120343")
                assertThat(source).isEqualTo(Source(listOf("#Fitzwilliam")))
                assertThat(validityPeriod!!.startTime).isEqualTo("2019-08-02T22:34:00.000Z")
                assertThat(planned).isFalse()
                assertThat(summary).isEqualTo("Disruption between Leeds and Doncaster / Sheffield expected until 13:00")
                assertThat(description!!.trim()).startsWith("<p>All lines have now reopened between Wakefield")
                assertThat(infoLinks).hasSize(2)
                assertThat(infoLinks!![0]).isEqualTo(InfoLink("https://www.nationalrail.co.uk/service_disruptions/227907.aspx", "nationalrail.co.uk"))
                assertThat(infoLinks!![1]).isEqualTo(InfoLink("https://www.nationalrail.co.uk/static/documents/maps/Fitzwilliam%20030819.pdf", "Additional Maps"))
                assertThat(affects!!.operators).hasSize(3)
                assertThat(affects!!.operators!![0]).isEqualTo(AffectedOperator("EM", "East Midlands Trains"))
                assertThat(affects!!.operators!![1]).isEqualTo(AffectedOperator("GR", "LNER"))
                assertThat(affects!!.operators!![2]).isEqualTo(AffectedOperator("NT", "Northern"))
                assertThat(affects!!.routesAffected).startsWith("<p>East Midlands Trains between Leeds and London")
                assertThat(clearedIncident).isFalse()
                assertThat(incidentPriority).isEqualTo(2)
            }
        }
    }
}
