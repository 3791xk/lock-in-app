package com.example.lock_in_app

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.yourapp.blocker"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
            when (call.method) {
                "startBlock" -> {
                    Log.d("InstagramBlocker","start blocerk")
                    InstagramBlockerService.isBlockingEnabled = true
                    result.success(null)
                }
                "stopBlock" -> {
                    Log.d("InstagramBlocker","stop blocerk")
                    InstagramBlockerService.isBlockingEnabled = false
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }
    }
}
