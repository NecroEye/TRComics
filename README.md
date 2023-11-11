# 🪐 TRComics

#### What is TRComics?

> TRComics [TR-Comics] (noun):
>  A comic & Light novel reading application

## Screenshots

<p align="center">
  <img src="./.github/assets/home.jpg?raw=true" alt="Home screen" width="30%" />
  <img src="./.github/assets/details.jpg?raw=true" alt="Viewer screen" width="30%" />
  <img src="./.github/assets/favorite.jpg?raw=true" alt="More info on viewer screen" width="30%" />

  <img src="./.github/assets/login.jpg?raw=true" alt="Search screen" width="30%" />
  <img src="./.github/assets/register.jpg?raw=true" alt="Favorites screen" width="30%" />
  <img src="./.github/assets/profile.jpg?raw=true" alt="Manage accounts screen" width="30%" />
</p>

> _Note: the screenshots don't indicate the final product._

## Download

**🚧 PROJECT IS UNDER DEVELOPMENT (~90% finished) 🚧**

The signed APK is currently not yet available, please [build](#building) it yourself to test.

## Tech Stacks

This project is trying to use the latest Android tech stacks.

- Language: [Java](https://docs.oracle.com/javase/8/docs/technotes/guides/language/index.html)
- Architecture: [Android App Architecture](https://developer.android.com/topic/architecture)
- User Interface: [Android Jetpack](https://developer.android.com/jetpack?hl=tr)
- Concurrency: [WorkManager](https://developer.android.com/jetpack/androidx/releases/work?hl=tr)
- Dependency Injection: [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=tr)
- Local Database: [Room](https://developer.android.com/training/data-storage/room)
- Networking: [Firebase](https://firebase.google.com)
- Image Loading: [Glide](https://github.com/bumptech/glide) & [Picasso](https://github.com/square/picasso) 
- Others:
    - [Truth](https://github.com/google/truth) (Testing)
    - [Navigation Compose](https://github.com/uragiristereo/safer-navigation-compose) (Navigation)
    - [Shimmer](https://github.com/facebookarchive/shimmer-android) (Shimmer Effect)

## Building

To build this project, you need the latest stable
of [Android Studio](https://developer.android.com/studio).

1. Clone the project and open in Android Studio.
2. Sync project with Gradle then Run 'app'.

**Note:**

The `playRelease` build variant will bundle Firebase Crashlytics to the app. By default it
will use the `ossDebug` build variant. The rest of the build variants won't bundle Firebase at all.

To use the `playRelease` build variant, first you need to create a Firebase Android app with
`com.muratcangzm.trcomics` as the package name then store the `google-services.json` file in the
root of `app` module.

## License

    Copyright 2023 Muratcan Gözüm

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.