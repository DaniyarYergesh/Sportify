package com.example.sportify.entity

import java.io.Serializable

data class SportEvent(
    val eventId: String = "",
    val eventName: String = "",
    val level: String = "",
    val location: String = "",
    val date: String = "",
    val time: String = "",
    val sportCategory: String = "",
    val participantsNumber: String = "",
    val maxParticipants: String = "",
    val duration: String = "",
    val status: String = SportEventStatus.OPEN.name,
) : Serializable

enum class SportEventStatus {
    OPEN, CLOSED
}