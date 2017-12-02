@file:Suppress(
        names = "NOTHING_TO_INLINE"
)
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


inline fun SensorsClient.readDataObs(request: SensorRequest): Observable<DataPoint>
        = RxSensorsClient(this).readData(request)

inline fun SensorsClient.findDataSourcesObs(request: DataSourcesRequest): Single<List<DataSource>>
        = RxSensorsClient(this).findDataSources(request)

inline fun HistoryClient.readDataObs(request: DataReadRequest): Single<DataReadResponse>
        = RxHistoryClient(this).readData(request)

inline fun HistoryClient.readDailyTotalObs(request: DataType): Single<DataSet>
        = RxHistoryClient(this).readDailyTotal(request)

inline fun HistoryClient.readDailyTotalFromLocalDeviceObs(request: DataType): Single<DataSet>
        = RxHistoryClient(this).readDailyTotalFromLocalDevice(request)

inline fun HistoryClient.registerDataUpdateListenerObs(request: DataUpdateListenerRegistrationRequest): Completable
        = RxHistoryClient(this).registerDataUpdateListener(request)

inline fun HistoryClient.unregisterDataUpdateListenerObs(request: PendingIntent): Completable
        = RxHistoryClient(this).unregisterDataUpdateListener(request)

inline fun HistoryClient.insertDataObs(dataSet: DataSet): Completable
        = RxHistoryClient(this).insertData(dataSet)

inline fun HistoryClient.updateDataObs(request: DataUpdateRequest): Completable
        = RxHistoryClient(this).updateData(request)

inline fun HistoryClient.deleteDataObs(request: DataDeleteRequest): Completable
        = RxHistoryClient(this).deleteData(request)

inline fun BleClient.claimBleDeviceObs(deviceAddress: String): Completable
        = RxBleClient(this).claimBleDevice(deviceAddress)

inline fun BleClient.claimBleDeviceObs(bleDevice: BleDevice): Completable
        = RxBleClient(this).claimBleDevice(bleDevice)

inline fun BleClient.listClaimedBleDevicesObs(): Single<List<BleDevice>>
        = RxBleClient(this).listClaimedBleDevices()

@RequiresPermission("android.permission.BLUETOOTH_ADMIN")
inline fun BleClient.startBleScanObs(dataTypes: List<DataType>, timeoutSecs: Int): Observable<BleDevice>
        = RxBleClient(this).startBleScan(dataTypes, timeoutSecs)

inline fun BleClient.unclaimBleDeviceObs(deviceAddress: String): Completable
        = RxBleClient(this).unclaimBleDevice(deviceAddress)

inline fun BleClient.unclaimBleDeviceObs(bleDevice: BleDevice): Completable
        = RxBleClient(this).unclaimBleDevice(bleDevice)

inline fun ConfigClient.createCustomDataTypeObs(request: DataTypeCreateRequest): Single<DataType>
        = RxConfigClient(this).createCustomDataType(request)

inline fun ConfigClient.disableFitObs(): Completable
        = RxConfigClient(this).disableFit()

inline fun ConfigClient.readDataTypeObs(dataTypeName: String): Single<DataType>
        = RxConfigClient(this).readDataType(dataTypeName)

inline fun GoalsClient.readCurrentGoalsObs(request: GoalsReadRequest): Single<List<Goal>>
        = RxGoalsClient(this).readCurrentGoals(request)