package com.nicolasmilliard.rxfit

import android.app.PendingIntent
import android.support.annotation.RequiresPermission
import com.google.android.gms.fitness.*
import com.google.android.gms.fitness.data.*
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
fun HistoryClient.deleteDataObs(request: DataDeleteRequest): Completable = this.deleteData(request).toCompletable()

fun BleClient.claimBleDeviceObs(deviceAddress: String): Completable = this.claimBleDevice(deviceAddress).toCompletable()
fun BleClient.claimBleDeviceObs(bleDevice: BleDevice): Completable = this.claimBleDevice(bleDevice).toCompletable()
fun BleClient.listClaimedBleDevicesObs(): Single<List<BleDevice>> = this.listClaimedBleDevices().toSingle()
@RequiresPermission("android.permission.BLUETOOTH_ADMIN")
fun BleClient.startBleScanObs(dataTypes: List<DataType>, timeoutSecs: Int, callback: BleScanCallback): Completable = this.startBleScan(dataTypes, timeoutSecs, callback).toCompletable()

fun BleClient.stopBleScanObs(callback: BleScanCallback): Single<Boolean> = this.stopBleScan(callback).toSingle()
fun BleClient.unclaimBleDeviceObs(deviceAddress: String): Completable = this.unclaimBleDevice(deviceAddress).toCompletable()
fun BleClient.unclaimBleDeviceObs(bleDevice: BleDevice): Completable = this.unclaimBleDevice(bleDevice).toCompletable()

fun ConfigClient.createCustomDataTypeObs(request: DataTypeCreateRequest): Single<DataType> = this.createCustomDataType(request).toSingle()
fun ConfigClient.disableFitObs(): Completable = this.disableFit().toCompletable()
fun ConfigClient.readDataTypeObs(dataTypeName: String): Single<DataType> = this.readDataType(dataTypeName).toSingle()

fun GoalsClient.readCurrentGoalsObs(request: GoalsReadRequest): Single<List<Goal>> = this.readCurrentGoals(request).toSingle()