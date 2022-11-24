package com.tipifreequiz.bavari.utils

import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.tipifreequiz.bavari.R

object Constants {
    const val TAG = "UniQuiz"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val APPSFLYER_API_KEY = "UzwYoihHTvdx2YYxZQ7hBZ"
    const val ONESIGNAL_API_KEY = "0f42fef3-72f3-4ff0-b3dc-a706ebf1b4fc"
    const val KOCHAVA_API_KEY = "koapp-go-mcamp-4f926l71x"
}