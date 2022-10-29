package com.dominikwannemaker.sportquiz.freo.utils

import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dominikwannemaker.sportquiz.freo.R

object Constants {
    const val TAG = "UniQuiz"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign_name")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val API_URL = "https://gfepeiq.ru"
    // const val MAIN_URL = "https://comappm.com/dolkox"

    // const val APPSFLYER_API_KEY = "HUsihS35Sq7zUP7kdqYiNY"
    const val ONESIGNAL_API_KEY = "5b68c176-4a7b-41ad-888c-badf862fd4a6"
    // const val KOCHAVA_API_KEY = "kocom-appcano-ziomn-u0ctevjn3"
    // const val MYTRACKER_API_KEY = "25510916382313489237"
}