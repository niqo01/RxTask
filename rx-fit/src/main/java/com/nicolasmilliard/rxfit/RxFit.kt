package com.nicolasmilliard.rxfit

import android.app.PendingIntent
import com.google.android.gms.fitness.HistoryClient
import com.google.android.gms.fitness.SensorsClient
import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.*
import com.google.android.gms.fitness.result.DataReadResponse
import com.nicolasmilliard.rxtask.toCompletable
import com.nicolasmilliard.rxtask.toSingle
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


fun SensorsClient.readDataObs(request: SensorRequest): Observable<DataPoint> = SensorObservable(this, request)
fun SensorsClient.findDataSourcesObs(request: DataSourcesRequest): Single<List<DataSource>> = this.findDataSources(request).toSingle()

fun HistoryClient.readDataObs(request: DataReadRequest): Single<DataReadResponse> = this.readData(request).toSingle()
fun HistoryClient.readDailyTotalObs(request: DataType): Single<DataSet> = this.readDailyTotal(request).toSingle()
fun HistoryClient.readDailyTotalFromLocalDeviceObs(request: DataType): Single<DataSet> = this.readDailyTotalFromLocalDevice(request).toSingle()

fun HistoryClient.registerDataUpdateListenerObs(request: DataUpdateListenerRegistrationRequest): Completable = this.registerDataUpdateListener(request).toCompletable()
fun HistoryClient.unregisterDataUpdateListenerObs(request: PendingIntent): Completable = this.unregisterDataUpdateListener(request).toCompletable()

fun HistoryClient.insertDataObs(dataSet: DataSet): Completable = this.insertData(dataSet).toCompletable()
fun HistoryClient.updateDataObs(request: DataUpdateRequest): Completable = this.updateData(request).toCompletable()
fun HistoryClient.insertDataObs(request: DataDeleteRequest): Completable = this.deleteData(request).toCompletable()


