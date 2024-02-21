package com.echelon.upickup.network.apimodel

import java.time.LocalDateTime

data class Event(
    val event_title: String,
    val event_date: LocalDateTime
)
