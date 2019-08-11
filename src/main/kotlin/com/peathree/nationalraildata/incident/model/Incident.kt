package com.peathree.nationalraildata.incident.model

import com.peathree.nationalraildata.incident.xml.InstantXmlAdapter
import java.time.Instant
import javax.xml.bind.annotation.XmlAccessType.FIELD
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlElementWrapper
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter

private const val COMMON_NAMESPACE = "http://nationalrail.co.uk/xml/common"
private const val INCIDENT_NAMESPACE = "http://nationalrail.co.uk/xml/incident"

@XmlRootElement(name = "uk.co.nationalrail.xml.incident.PtIncidentStructure")
@XmlAccessorType(FIELD)
data class Incident(
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "CreationTime") @field:XmlJavaTypeAdapter(value = InstantXmlAdapter::class) val creationTime: Instant? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "ChangeHistory") val changeHistory: ChangeHistory? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "ParticipantRef") val participantRef: String? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "IncidentNumber") val incidentNumber: String? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Version") val version: String? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Source") val source: Source? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "ValidityPeriod") val validityPeriod: ValidityPeriod? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Planned") val planned: Boolean? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Description") val description: String? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Summary") val summary: String? = null,
    @field:XmlElementWrapper(namespace = INCIDENT_NAMESPACE, name = "InfoLinks") @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "InfoLink") val infoLinks: List<InfoLink>? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Affects") val affects: Affects? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "ClearedIncident") val clearedIncident: Boolean? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "IncidentPriority") val incidentPriority: Int? = null
)

data class ChangeHistory(
    @field:XmlElement(namespace = COMMON_NAMESPACE, name = "ChangedBy") val changedBy: String? = null,
    @field:XmlElement(namespace = COMMON_NAMESPACE, name = "LastChangedDate") @field:XmlJavaTypeAdapter(value = InstantXmlAdapter::class) val lastChangedDate: Instant? = null
)

data class Source(
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "TwitterHashtag") val twitterHashTags: List<String>? = null
)

data class ValidityPeriod(
    @field:XmlElement(namespace = COMMON_NAMESPACE, name = "StartTime") @field:XmlJavaTypeAdapter(value = InstantXmlAdapter::class) val startTime: Instant? = null,
    @field:XmlElement(namespace = COMMON_NAMESPACE, name = "EndTime") @field:XmlJavaTypeAdapter(value = InstantXmlAdapter::class) val endTime: Instant? = null
)

data class InfoLink(
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Uri") val uri: String? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "Label") val label: String? = null
)

data class Affects(
    @field:XmlElementWrapper(namespace = INCIDENT_NAMESPACE, name = "Operators") @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "AffectedOperator") val operators: List<AffectedOperator>? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "RoutesAffected") val routesAffected: String? = null
)

data class AffectedOperator(
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "OperatorRef") val ref: String? = null,
    @field:XmlElement(namespace = INCIDENT_NAMESPACE, name = "OperatorName") val name: String? = null
)
