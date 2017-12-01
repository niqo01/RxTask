RxPlayServices
=========

RxJava binding API for Google Play Services and corresponding Kotlin extensions.  
This project uses the new [GoogleApi][googleapi]introduced in version 11.6.0.

Download
--------

'play-services-location' library bindings:
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-location:0.1.0'
```

'play-services-places' library bindings:
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-places:0.1.0'
```

'rx-play-services-fitness' library bindings:
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-fitness:0.1.0'
```

Kotlin extension methods for all of the above libraries are available by appending `-kotlin` to the
'artifactId' of the dependency. For example, `rx-play-services-task` becomes
`rx-play-services-task-kotlin`.

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].


RxLocation
--------

Java sample:
```java
    FusedLocationProviderClient client = LocationServices
            .getFusedLocationProviderClient(context);
    RxFusedLocationProviderClient rxClient = new
            RxFusedLocationProviderClient(client);
    Observable<LocationResult> result = rxClient
            .requestLocationRequestUpdates(LocationRequest.create());
```

Kotlin sample:
```kotlin
    val client = LocationServices
            .getFusedLocationProviderClient(context)
    val result: Observable<LocationResult> = client
            .requestLocationRequestUpdatesObs(LocationRequest.create())
```

RxPlaces
--------

Java sample:
```java
    PlaceDetectionClient client = Places.getPlaceDetectionClient(context, null);
    RxPlaceDetectionClient rxClient = new RxPlaceDetectionClient(client);
    Single<PlaceLikelihoodBufferResponse> currentPlace = rxClient
            .getCurrentPlace(null);
```

Kotlin sample:
```kotlin
val client = Places.getPlaceDetectionClient(context, null)
val currentPlace: Single<PlaceLikelihoodBufferResponse> = client
            .getCurrentPlaceObs(null)
```

RxFitness
--------

Java sample:
```java
    SensorsClient client = Fitness.getSensorsClient(context, signInAccount);
    RxSensorsClient rxClient = new RxSensorsClient(client);
    SensorRequest request = new SensorRequest.Builder()
            .setDataType(DataType
            .TYPE_CALORIES_EXPENDED)
            .build();
    Observable<DataPoint> dataPointObservable = rxClient.readData(request);
```

Kotlin sample:
```kotlin
    val client = Fitness.getSensorsClient(context, signInAccount)
    val request = SensorRequest.Builder()
            .setDataType(DataType
                    .TYPE_CALORIES_EXPENDED)
            .build()
    val dataPointObservable: Observable<DataPoint> = client.readDataObs(request)
```

License
-------

    Copyright (C) 2016 Nicolas Milliard

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 [googleapi]: https://android-developers.googleblog.com/2017/11/moving-past-googleapiclient_21.html
 [task]: https://developers.google.com/android/guides/tasks
 [rxtask]: https://github.com/niqo01/RxPlayServices/blob/master/rx-task/src/main/java/com/nicolasmilliard/rxtask/RxTask.kt
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/