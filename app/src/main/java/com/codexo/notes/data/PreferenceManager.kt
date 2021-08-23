package com.codexo.notes.data

import android.content.Context
import com.codexo.notes.utils.SortBy

class PreferenceManager(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "JottyNotes"
        private const val SORT_BY = "SortBy"
        private const val FAVORITE_PINNED = "FavoritePinned"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setSortBy(sortBy: String) {
        preferences.edit()
            .putString(SORT_BY, sortBy)
            .apply()
    }

    fun sortBy(): String? {
        if (preferences.contains(SORT_BY)) {
            return preferences.getString(SORT_BY, SortBy.ID.colName)
        }
        return null
    }

    fun favoritePinned(pinned: Boolean) {
        preferences.edit()
            .putBoolean(FAVORITE_PINNED, pinned)
            .apply()
    }

    fun favoritePinnedStatus(): Boolean {
        if (preferences.contains(FAVORITE_PINNED)) {
            return preferences.getBoolean(FAVORITE_PINNED, false)
        }
        return false
    }
}