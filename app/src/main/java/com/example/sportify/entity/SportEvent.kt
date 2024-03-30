package com.example.sportify.entity

import java.util.Date

data class SportEvent(
    val eventId: Int,
    val eventName: String,
    val location: String,
    val date: String,
    val time: String,
    val sportCategory: String,
    val maxParticipants: String
)