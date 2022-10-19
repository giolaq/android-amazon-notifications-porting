/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amazon.A3L.messaging.A3LMessaging
import com.amazon.A3L.messaging.registration.InitCallbackResponse
import com.amazon.A3L.messaging.registration.OnInitCallback
import com.example.android.eggtimernotifications.ui.EggTimerFragment


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val onInitCallback: OnInitCallback = object : OnInitCallback() {
            override fun onReady(initCallbackResponse: InitCallbackResponse) {
                if (initCallbackResponse.isSuccessFul) {
                    Log.d(TAG, "Device Id: " + initCallbackResponse.token)
                } else {
                    Log.d(
                        TAG, "Registration failed with Error: " +
                                initCallbackResponse.errorMessage
                    )
                }
            }
        }
        A3LMessaging.init(applicationContext, onInitCallback)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EggTimerFragment.newInstance())
                .commitNow()
        }
    }
}