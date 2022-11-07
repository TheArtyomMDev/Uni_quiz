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

    const val API_URL = "https://comappm.com"
    const val MAIN_URL = "https://comappm.com/noopls"

    const val APPSFLYER_API_KEY = "6GcmqsTy5D55wdGzRyuUn5"
    const val ONESIGNAL_API_KEY = "9b867574-8b09-4d82-a24b-8ed9269f4b0f"
    const val KOCHAVA_API_KEY = "koapp-go-mcamp-4f926l71x"
    const val MYTRACKER_API_KEY = "62874364010790166820"
}