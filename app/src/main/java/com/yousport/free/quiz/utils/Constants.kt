package com.yousport.free.quiz.utils

import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yousport.free.quiz.R

object Constants {
    const val TAG = "UniQuiz"

    val POINTS = intPreferencesKey("points")
    val ADVERTISING_ID = stringPreferencesKey("advertising_id")
    val APPSFLYER_ID = stringPreferencesKey("appsflyer_id")
    val CAMPAIGN_ID = stringPreferencesKey("campaign_id")
    val CAMPAIGN_NAME = stringPreferencesKey("campaign")
    val AF_CHANNEL = stringPreferencesKey("af_channel")

    const val API_URL = "https://comappm.com"
    const val MAIN_URL = "https://comappm.com/pgved"

    const val APPSFLYER_API_KEY = "pSwf7g88UwAu3U9c44pFcX"
    const val ONESIGNAL_API_KEY = "77932b2a-0fc9-4ac0-afb9-2d633672424d"
    const val BRANCH_API_KEY = "key_test_kh34f9vMl7KexVpBSKsvudckzulU66z1"
    const val KOCHAVA_API_KEY = "koapp-golomin-fbrj2xv9e"
    const val MYTRACKER_API_KEY = "51508643737268533179"
}