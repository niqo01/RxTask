RxPlayServices
=========

RxJava wrappers for Android Play Services and corresponding Kotlin extensions.
This project uses the new [GoogleApi][googleapi] based API introduced in version 11.6.0.

RxTask
--------

Lightweight wrapper for the Google play services [Tasks][task] API.


```java
    PlaceDetectionClient client = Places.getPlaceDetectionClient(context, null);
    Single<PlaceLikelihoodBufferResponse> single = RxTask.single(client.getCurrentPlace(null));
```

```kotlin
    val client = Places.getPlaceDetectionClient(context, null)
    val single = client.getCurrentPlace(null).toSingle();
```


RxLocation
--------

TBD

RxPlaces
--------

TBD


RxFit
--------

TBD

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
