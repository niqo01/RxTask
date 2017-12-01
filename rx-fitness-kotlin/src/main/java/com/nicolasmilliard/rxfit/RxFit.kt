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


fun SensorsClient.readDataObs(request: SensorRequest): Observable<DataPoint> = SensorObservable(this, request)
fun SensorsClient.findDataSourcesObs(request: DataSourcesRequest): Single<List<DataSource>> = FindDataSourcesSingle(this, request)

fun HistoryClient.readDataObs(request: DataReadRequest): Single<DataReadResponse> = ReadDataSingle(this, request)
fun HistoryClient.readDailyTotalObs(request: DataType): Single<DataSet> = ReadDailyTotalSingle(this, request)
fun HistoryClient.readDailyTotalFromLocalDeviceObs(request: DataType): Single<DataSet> = ReadDailyTotalFromLocalDeviceSingle(this, request)

fun HistoryClient.registerDataUpdateListenerObs(request: DataUpdateListenerRegistrationRequest): Completable = RegisterDataUpdateListenerCompletable(this, request)
fun HistoryClient.unregisterDataUpdateListenerObs(request: PendingIntent): Completable = UnRegisterDataUpdateListenerCompletable(this, request)

fun HistoryClient.insertDataObs(dataSet: DataSet): Completable = InsertDataCompletable(this, dataSet)
fun HistoryClient.updateDataObs(request: DataUpdateRequest): Completable = UpdateDataCompletable(this, request)
fun HistoryClient.deleteDataObs(request: DataDeleteRequest): Completable = DeleteDataCompletable(this, request)

fun BleClient.claimBleDeviceObs(deviceAddress: String): Completable = ClaimBleDeviceByAddressCompletable(this,  deviceAddress)
fun BleClient.claimBleDeviceObs(bleDevice: BleDevice): Completable = ClaimBleDeviceCompletable(this, bleDevice)
fun BleClient.listClaimedBleDevicesObs(): Single<List<BleDevice>> = ListClaimedBleDeviceSingle(this)
@RequiresPermission("android.permission.BLUETOOTH_ADMIN")
fun BleClient.startBleScanObs(dataTypes: List<DataType>, timeoutSecs: Int, callback: BleScanCallback): Observable<BleDevice> = StartBleScanObservable(this, dataTypes, timeoutSecs)

fun BleClient.unclaimBleDeviceObs(deviceAddress: String): Completable = UnClaimBleDeviceByAddressCompletable(this, deviceAddress)
fun BleClient.unclaimBleDeviceObs(bleDevice: BleDevice): Completable = UnClaimBleDeviceCompletable(this,  bleDevice)

fun ConfigClient.createCustomDataTypeObs(request: DataTypeCreateRequest): Single<DataType> = CreateCustomDataTypeSingle(this, request)
fun ConfigClient.disableFitObs(): Completable = DisableFitCompletable(this)
fun ConfigClient.readDataTypeObs(dataTypeName: String): Single<DataType> = ReadDataTypeSingle(this, dataTypeName)

fun GoalsClient.readCurrentGoalsObs(request: GoalsReadRequest): Single<List<Goal>> = ReadCurrentGoalsSingle(this, request)