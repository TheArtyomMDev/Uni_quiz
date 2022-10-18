package com.amigoquiz.sports.ger.utils

import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.amigoquiz.sports.ger.R

object Constants {
    const val TAG = "UniQuiz"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign_name")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val API_URL = "https://forogas.com"
    const val MAIN_URL = "https://forogas.com/dcmasv"

    const val APPSFLYER_API_KEY = "vo4ZjdgfK4isNF27cz2XWS"
    const val ONESIGNAL_API_KEY = "281541a0-efbe-41b2-b48b-19686f55989d"
    const val KOCHAVA_API_KEY = "kofgolp-vbpolf-laofo-1yq960"
    const val MYTRACKER_API_KEY = "48613360286629569785"
}