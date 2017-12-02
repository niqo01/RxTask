RxPlayServices
=========

RxJava 2 binding API for Google Play Services.  
This project uses the new [GoogleApi][googleapi] introduced in version 11.6.0.

Download
--------

'play-services-location' library bindings:
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-location:0.2.0'
```

'play-services-places' library bindings:
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-places:0.2.0'
```

'rx-play-services-fitness' library bindings:
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-fitness:0.2.0'
```

'rx-play-services-wallet' library bindings:
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-wallet:0.2.0'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].


API Example
--------

### Location
```java
    RxFusedLocationProviderClient rxClient = new
            RxFusedLocationProviderClient(context);
    Observable<LocationResult> result = rxClient
            .requestLocationRequestUpdates(LocationRequest.create());
```

### Places

Sample:
```java
    RxPlaceDetectionClient rxClient = new RxPlaceDetectionClient(context, null);
    Single<PlaceLikelihoodBufferResponse> currentPlace = rxClient
            .getCurrentPlace(null);
```


### Fitness

Sample:
```java
    RxSensorsClient rxClient = new RxSensorsClient(context, signInAccount);
    SensorRequest request = new SensorRequest.Builder()
            .setDataType(DataType
            .TYPE_CALORIES_EXPENDED)
            .build();
    Observable<DataPoint> dataPointObservable = rxClient.readData(request);
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