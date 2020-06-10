package com.android.data.features.general

import android.annotation.SuppressLint
import android.os.Build
import com.android.data.BuildConfig

class BuildInfoHelper {
    /* Build ====== */

    val isDebug: Boolean
        get() = BuildConfig.DEBUG

    val productName = "rtt_express_app"


    /* Apis Info ====== */
    val webApiUrl: String
        get() = BuildConfig.API_URL


    /* About ====== */


    val appVersionCode: Int
        get() = BuildConfig.VERSION_CODE

    val appVersionName: String
        get() = BuildConfig.VERSION_NAME

    val androidVersion: String
        get() = Build.VERSION.RELEASE ?: ""

    val deviceName: String
        @SuppressLint("DefaultLocale")
        get() = "${Build.MANUFACTURER?.capitalize() ?: ""} ${Build.MODEL ?: ""}"
}