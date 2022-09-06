package com.example.todoproject

import android.app.Application
import android.content.Context
import com.example.todoproject.di.AppComponent
import com.example.todoproject.di.DaggerAppComponent
import com.example.todoproject.domain.StoreImpl
import javax.inject.Inject

class ToDoProjectApplication : Application() {

    lateinit var applicationComponent: AppComponent
    @Inject
    lateinit var store: StoreImpl

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.create()
        applicationComponent.inject(this)
    }
}

val Context.applicationComponent: AppComponent
    get() = when (this) {
        is ToDoProjectApplication -> applicationComponent
        else -> this.applicationContext.applicationComponent
    }