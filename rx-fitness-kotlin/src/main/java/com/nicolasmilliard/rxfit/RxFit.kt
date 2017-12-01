package com.nicolasmilliard.rxfit

import android.app.PendingIntent
import android.support.annotation.RequiresPermission
import com.google.android.gms.fitness.*
import com.google.android.gms.fitness.data.*
import com.google.android.gms.fitness.request.*
import com.google.android.gms.fitness.result.DataReadResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


fun SensorsClient.readDataObs(request: SensorRequest): Observable<DataPoint>
        = RxSensorsClient(this).readData(request)

fun SensorsClient.findDataSourcesObs(request: DataSourcesRequest): Single<List<DataSource>>
        = RxSensorsClient(this).findDataSources(request)

fun HistoryClient.readDataObs(request: DataReadRequest): Single<DataReadResponse>
        = RxHistoryClient(this).readData(request)

fun HistoryClient.readDailyTotalObs(request: DataType): Single<DataSet>
        = RxHistoryClient(this).readDailyTotal(request)

fun HistoryClient.readDailyTotalFromLocalDeviceObs(request: DataType): Single<DataSet>
        = RxHistoryClient(this).readDailyTotalFromLocalDevice(request)

fun HistoryClient.registerDataUpdateListenerObs(request: DataUpdateListenerRegistrationRequest): Completable
        = RxHistoryClient(this).registerDataUpdateListener(request)

fun HistoryClient.unregisterDataUpdateListenerObs(request: PendingIntent): Completable
        = RxHistoryClient(this).unregisterDataUpdateListener(request)

fun HistoryClient.insertDataObs(dataSet: DataSet): Completable
        = RxHistoryClient(this).insertData(dataSet)

fun HistoryClient.updateDataObs(request: DataUpdateRequest): Completable
        = RxHistoryClient(this).updateData(request)

fun HistoryClient.deleteDataObs(request: DataDeleteRequest): Completable
        = RxHistoryClient(this).deleteData(request)

fun BleClient.claimBleDeviceObs(deviceAddress: String): Completable
        = RxBleClient(this).claimBleDevice(deviceAddress)

fun BleClient.claimBleDeviceObs(bleDevice: BleDevice): Completable
        = RxBleClient(this).claimBleDevice(bleDevice)

fun BleClient.listClaimedBleDevicesObs(): Single<List<BleDevice>>
        = RxBleClient(this).listClaimedBleDevices()

@RequiresPermission("android.permission.BLUETOOTH_ADMIN")
fun BleClient.startBleScanObs(dataTypes: List<DataType>, timeoutSecs: Int): Observable<BleDevice>
        = RxBleClient(this).startBleScan(dataTypes, timeoutSecs)

fun BleClient.unclaimBleDeviceObs(deviceAddress: String): Completable
        = RxBleClient(this).unclaimBleDevice(deviceAddress)

fun BleClient.unclaimBleDeviceObs(bleDevice: BleDevice): Completable
        = RxBleClient(this).unclaimBleDevice(bleDevice)

fun ConfigClient.createCustomDataTypeObs(request: DataTypeCreateRequest): Single<DataType>
        = RxConfigClient(this).createCustomDataType(request)

fun ConfigClient.disableFitObs(): Completable
        = RxConfigClient(this).disableFit()

fun ConfigClient.readDataTypeObs(dataTypeName: String): Single<DataType>
        = RxConfigClient(this).readDataType(dataTypeName)

fun GoalsClient.readCurrentGoalsObs(request: GoalsReadRequest): Single<List<Goal>>
        = RxGoalsClient(this).readCurrentGoals(request)