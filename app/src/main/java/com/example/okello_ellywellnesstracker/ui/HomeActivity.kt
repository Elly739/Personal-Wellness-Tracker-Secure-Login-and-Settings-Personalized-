package com.example.okello_ellywellnesstracker.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.okello_ellywellnesstracker.R
import com.example.okello_ellywellnesstracker.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("EXTRA_USERNAME") ?: "Okello Elly"
        binding.tvUserWelcome.text = getString(R.string.logged_in_as, username)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        updatePreferencesDisplay()

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        updatePreferencesDisplay()
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        updatePreferencesDisplay()
    }

    private fun updatePreferencesDisplay() {
        val notificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", true)
        val reminderHour = sharedPreferences.getInt("reminder_hour", 8)
        val notifStatus = if (notificationsEnabled) "ON" else "OFF"

        val summaryText = "${getString(R.string.pref_val_notifications)}: $notifStatus | ${getString(R.string.pref_val_reminder_hour)}: $reminderHour"
        binding.tvPrefSummary.text = summaryText
    }
}
