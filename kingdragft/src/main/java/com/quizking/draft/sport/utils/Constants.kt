package com.quizking.draft.sport.utils

import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.quizking.draft.sport.R

object Constants {
    const val TAG = "UniQuiz"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val APPSFLYER_API_KEY = "VEJruSwt5zy6VBQZYbzGGg"
    const val ONESIGNAL_API_KEY = "b111c2f9-375d-4b29-b830-d10e48a4c8d8"
    const val KOCHAVA_API_KEY = "kocamper-20-dk-cfbu"
}