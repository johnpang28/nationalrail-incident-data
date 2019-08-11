package com.peathree.nationalraildata.incident.jms

import com.peathree.nationalraildata.incident.model.Incident
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import java.util.concurrent.CopyOnWriteArrayList

class IncidentReceiver {

    private val incidents: MutableList<Incident> = CopyOnWriteArrayList()

    @JmsListener(destination = "kb.incidents")
    fun receive(incident: Incident) {
        logger.info("Received incident: $incident")
        incidents.add(incident)
    }

    fun incidents() = incidents.toList()

    companion object {
        private val logger = LoggerFactory.getLogger(IncidentReceiver::class.java)
    }
}
