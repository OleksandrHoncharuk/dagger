package com.example.daggerpractice.di

import androidx.work.ListenableWorker
import dagger.MapKey
import java.lang.annotation.Documented
import kotlin.reflect.KClass


@MapKey
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)