package com.example.instagramclone.FakeClasses

import android.content.SharedPreferences

class FakeSharedPreferences : SharedPreferences {

    private val data = mutableMapOf<String, Any>()

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return data[key] as? Boolean ?: defValue
    }

    override fun edit(): SharedPreferences.Editor {
        return FakeEditor()
    }

    inner class FakeEditor : SharedPreferences.Editor {
        override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
            if (key != null) data[key] = value
            return this
        }

        override fun apply() {}
        override fun commit(): Boolean = true

        // other methods can be empty
        override fun clear() = this
        override fun remove(key: String?) = this
        override fun putString(key: String?, value: String?) = this
        override fun putInt(key: String?, value: Int) = this
        override fun putLong(key: String?, value: Long) = this
        override fun putFloat(key: String?, value: Float) = this
        override fun putStringSet(key: String?, values: MutableSet<String>?) = this
    }

    // unused methods
    override fun getAll() = data
    override fun getInt(key: String?, defValue: Int) = defValue
    override fun getLong(key: String?, defValue: Long) = defValue
    override fun getFloat(key: String?, defValue: Float) = defValue
    override fun getString(key: String?, defValue: String?) = defValue
    override fun getStringSet(key: String?, defValue: MutableSet<String>?) = defValue
    override fun contains(key: String?) = data.containsKey(key)
    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}
    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}
}
