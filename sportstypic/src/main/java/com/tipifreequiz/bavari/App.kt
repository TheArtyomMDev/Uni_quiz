package com.tipifreequiz.bavari

import android.app.Application
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

import com.kochava.tracker.Tracker
import com.my.tracker.MyTracker
import com.onesignal.OneSignal
import com.tipifreequiz.bavari.di.dataStore
import com.tipifreequiz.bavari.di.databaseModule
import com.tipifreequiz.bavari.di.networkModule
import com.tipifreequiz.bavari.di.viewModelsModule
import com.tipifreequiz.bavari.utils.Constants
import io.branch.referral.Branch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(databaseModule, networkModule, viewModelsModule))
        }

        // AppsFlyer
        val conversionDataListener  = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                //Toast.makeText(this@App, "on Data success", Toast.LENGTH_LONG).show()
                println("data is ")
                if(data == null) return

                CoroutineScope(Dispatchers.IO).launch {
                    dataStore.edit { preferences ->
                        for(elem in listOf(
                            Constants.ADVERTISING_ID,
                            Constants.APPSFLYER_ID,
                            Constants.CAMPAIGN_ID,
                            Constants.CAMPAIGN_NAME,
                            Constants.AF_CHANNEL
                        ))
                        {
                            println("Current elem is ${elem.name}, ${(elem.name in data.keys)}")
                            if(elem.name in data.keys)
                                preferences[elem] = data[elem.name].toString()
                        }

                    }
                }

                for(elem in data) println(elem)
            }
            override fun onConversionDataFail(error: String?) {
                Log.e(Constants.TAG, "error onAttributionFailure :  $error")
            }
            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                data?.map {
                    Log.d(Constants.TAG, "onAppOpen_attribute: ${it.key} = ${it.value}")
                }
            }
            override fun onAttributionFailure(error: String?) {
                Log.e(Constants.TAG, "error onAttributionFailure :  $error")
            }
        }
        AppsFlyerLib.getInstance().setDebugLog(true)
        AppsFlyerLib.getInstance().init(Constants.APPSFLYER_API_KEY, conversionDataListener, this)
        AppsFlyerLib.getInstance().start(this)

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(Constants.ONESIGNAL_API_KEY)
        OneSignal.promptForPushNotifications()

        // MyTracker
        MyTracker.initTracker(Constants.MYTRACKER_API_KEY, this)

        // Branch
        Branch.enableTestMode()
        Branch.getAutoInstance(this)

        // Kochava
        Tracker.getInstance().startWithAppGuid(this, Constants.KOCHAVA_API_KEY)


        // Remote config
        FirebaseApp.initializeApp(this)
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        remoteConfig.reset()
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
            fetchTimeoutInSeconds = 60
        }
        //remoteConfig.setDefaultsAsync(R.xml.config)
        remoteConfig.setConfigSettingsAsync(configSettings)


    }
}