package com.avijekrl.proald.utils

import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.avijekrl.proald.R

object Constants {
    const val TAG = "UniQuiz"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val APPSFLYER_API_KEY = "7RrB8MAJVYsXNjVLsQtHLQ"
    const val ONESIGNAL_API_KEY = "f3f60ba2-0cd8-4ef4-9a43-5cd9a9ca1016"
    const val KOCHAVA_API_KEY = "kocamper-21-avia-qtz5kv"
}