package com.example.lock_in_app

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class InstagramBlockerService : AccessibilityService() {

    companion object {
        var isBlockingEnabled: Boolean = false
    }

    private var lastScrollTime = 0L
    private var lastScrollY = 0

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (!isBlockingEnabled) return

        if (event.packageName == "com.instagram.android" &&
            event.eventType == AccessibilityEvent.TYPE_VIEW_SCROLLED) {

            val currentTime = System.currentTimeMillis()
            val deltaY = event.scrollDeltaY

            val deltaTime = currentTime - lastScrollTime
            Log.d("InstagramBlocker","Delta time: $deltaTime ms, Delta Y: $deltaY")
            if (deltaTime < 500 && Math.abs(deltaY) > 50) {
                Log.d("InstagramBlocker","Instagram scrolling detected, blocking...")
                performGlobalAction(GLOBAL_ACTION_HOME) // Go to home screen
            }

            lastScrollTime = currentTime
        }
    }

    override fun onInterrupt() {}
}
