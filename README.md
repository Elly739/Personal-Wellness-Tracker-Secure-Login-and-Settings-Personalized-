# Personal Wellness Tracker – Secure Login and Settings (Personalized)

## Developer
- **Name**: Okello Elly
- **Course**: Mobile Application Development (Android / Kotlin)

## App Overview
The **Okello Elly Wellness Tracker** is a secure Android application built with Android Views/Compose, Room Database, BCrypt password hashing, and the AndroidX Preference Library. It provides user account management, secure authentication, localization (English, Spanish, Swahili), landscape layouts, and customizable settings.

## Features & Deliverables
1. **Personalization**:
   - App Name: `Okello Elly Wellness Tracker`
   - Welcome Message: Welcomes user by name.
   - Settings: Pre-filled `"Username"` preference with `"Okello Elly"`.
2. **Authentication (Room + BCrypt)**:
   - Room database with `User` entity (`id`, `username`, `passwordHash`).
   - DAO with suspend functions (`insertUser`, `getUserByUsername`).
   - Secure password hashing using BCrypt (never stored in plain text).
   - Generic login error handling (`"Invalid username or password"`).
   - Off-main-thread database operations (`Dispatchers.IO`).
3. **User Interface & Settings**:
   - `AuthActivity` for Login & Sign Up.
   - `HomeActivity` showing logged-in username and live preference status.
   - `SettingsActivity` hosting `PreferenceFragmentCompat` (`preferences.xml`) with Notifications, Appearance (Dark Mode & Language), and Reminders categories.
4. **Resource Management & Localization**:
   - All user-facing text in `strings.xml`.
   - All colors in `colors.xml`.
   - All margins/paddings/text sizes in `dimens.xml`.
   - Localization support for Swahili (`values-sw`) and Spanish (`values-es`).
5. **Landscape Layouts**:
   - Dedicated landscape layout (`layout-land/activity_auth.xml`).

## Repository
[GitHub Repository](https://github.com/Elly739/Personal-Wellness-Tracker-Secure-Login-and-Settings-Personalized-)
