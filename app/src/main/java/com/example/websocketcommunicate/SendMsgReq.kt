package com.example.websocketcommunicate

data class SendMsgReq(
    val detail: String,
    val deviceKey: String,
    val photoPath: String,
    val subType: String,
    val timestamp: String,
    val type: String
)