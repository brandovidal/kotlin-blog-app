# kotlin - Blog App

[Firebase](https://firebase.google.com/docs/android/setup?hl=es)

[Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)

[ViewModel and LiveData KTX](https://developer.android.google.cn/jetpack/androidx/releases/lifecycle?hl=es)

[Glide](https://github.com/bumptech/glide)

### Using in App Gradle

Source File: app/build.gradle (:app)

```shell script
  plugins {
    (...)
    id "androidx.navigation.safeargs.kotlin"
    id 'kotlin-kapt'
    id 'com.google.gms.google-services'
  }

  (...)
  // Firebase
  implementation 'com.google.firebase:firebase-firestore-ktx:23.0.3'
  implementation 'com.google.firebase:firebase-storage:19.2.1'

  // Navigation
  implementation "androidx.navigation:navigation-fragment-ktx:2.3.5"
  implementation "androidx.navigation:navigation-ui-ktx:2.3.5"

  // Glide - Utils
  implementation 'de.hdodenhof:circuleimageview:3.1.0'
  implementation 'com.github.bumptech.glide:glide:4.12.0'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

  // ViewModel and LiveData KTX
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

  // Play services coroutines
  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1'
```

### Using in Build Gradle

Source File: build.gradle (BloappApp)

```shell script
  (...)
  dependencies {
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30"
    classpath 'com.google.gms:google-services:4.3.10'
  }
```