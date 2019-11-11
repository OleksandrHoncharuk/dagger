package com.example.daggerpractice.managers

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.daggerpractice.managers.factory.ManagerFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class WorkManagerInit: ContentProvider() {

    @Inject
    lateinit var managerFactory: ManagerFactory

    override fun onCreate(): Boolean {
        AndroidInjection.inject(this)
        WorkManager.initialize(context!!,
                Configuration.Builder().setWorkerFactory(managerFactory).build())

        return true
    }

    override fun insert(uri: Uri, values: ContentValues?) = null
    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?) = null
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?) = 0
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = 0
    override fun getType(uri: Uri) = null
}