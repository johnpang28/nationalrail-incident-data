package com.peathree.nationalraildata.incident

import com.peathree.nationalraildata.incident.jms.IncidentReceiver
import com.peathree.nationalraildata.incident.model.Incident
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.support.converter.MarshallingMessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
class IncidentConfiguration {

    @Bean
    fun messageConverter(): MessageConverter {
        val jaxb2Marshaller = Jaxb2Marshaller().apply { setClassesToBeBound(Incident::class.java) }
        return MarshallingMessageConverter(jaxb2Marshaller)
    }

    @Bean
    fun receiver() = IncidentReceiver()

}
