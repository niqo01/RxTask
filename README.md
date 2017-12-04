RxPlayServices
=========

RxJava 2 binding API for Google Play Services [Task][task] API.  

RxTask
--------
RxTask is an easy to use lightweight binding for the Google Play Services [Task][task] APIs.  
It can be use standalone or bundled with one of the GoogleAPIs helper project provided. 

##### Download
```groovy
implementation 'com.github.niqo01.rxplayservices:rx-play-services-task:0.3.0'
```

##### Example
```java
    GoogleSignInClient client = GoogleSignIn.getClient(context, options);
    Single<GoogleSignInAccount> silentSignIn = SingleTask.create(() -> client.silentSignIn());
    ...
    Completable revokeAccess = CompletableTask.create(() -> client.revokeAccess());
```

### GoogleAPIs

The following projects leverage RxTask and wrap the new [GoogleApi][googleapi] introduced 
in version 11.6.0.


| API | Dependency |
| --- | --- |
| Auth | com.github.niqo01.rxplayservices:rx-play-services-auth:0.3.0 |
| Location | com.github.niqo01.rxplayservices:rx-play-services-location:0.3.0 |
| Places | com.github.niqo01.rxplayservices:rx-play-services-places:0.3.0 |
| Fitness | com.github.niqo01.rxplayservices:rx-play-services-fitness:0.3.0 |
| Wallet | com.github.niqo01.rxplayservices:rx-play-services-wallet:0.3.0 |


##### Location Example
```java
    RxFusedLocationProviderClient rxClient = new RxFusedLocationProviderClient(context);
    Observable<LocationResult> result = rxClient
            .requestLocationRequestUpdates(LocationRequest.create());
```


Snapshots
-------
Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

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