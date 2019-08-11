package com.peathree.nationalraildata.incident.xml

import java.time.Instant
import java.time.ZonedDateTime
import javax.xml.bind.annotation.adapters.XmlAdapter

class InstantXmlAdapter : XmlAdapter<String, Instant>() {

    override fun marshal(v: Instant?) = v.toString()

    override fun unmarshal(v: String?) = ZonedDateTime.parse(v).toInstant()

}
