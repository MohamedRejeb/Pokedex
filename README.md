# Pokedex

Pokedex is kotlin multiplatform project with 99% shared code, built with Compose multiplatform, Coroutines, Flow, Decompose, MVIKotlin, Koin, Ktor, SqlDelight, and Material 3 based on MVI architecture
<br>
<br>

![pokedex](https://user-images.githubusercontent.com/41842296/224551967-1c09e59d-25c2-4a7b-ace8-4676cfd26672.png)

## Open-source libraries
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin multiplatform / multi-format serialization.
- [Compose multiplatform](https://github.com/JetBrains/compose-multiplatform): a modern UI framework for Kotlin.
- [Decompose](https://github.com/arkivanov/Decompose): for navigation.
- [Ktor](https://github.com/ktorio/ktor): for making network requests.
- [SqlDelight](https://github.com/cashapp/sqldelight): for caching data.
- [Koin](https://github.com/InsertKoinIO/koin): a pragmatic lightweight dependency injection framework.
- Architecture
  - MVI Architecture ([MVIKotlin](https://github.com/arkivanov/MVIKotlin/))
  - Repository Pattern
- [Material 3](https://m3.material.io/components): Material 3 components.

## Screenshots
  ### Android
  
  <div style="display: flex; width: 100%">
  <img src="https://user-images.githubusercontent.com/41842296/224555659-f75bcddc-21a9-42f4-804d-198a5f06dcb1.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555672-03a6dcd5-f26f-4ecf-aa1e-3db66d278b8b.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555677-1fc807fc-57c6-46e0-bb27-afa085181a5c.png" width="31%"/>
  </div>
  
  ### IOS
  
  <div style="display: flex">
  <img src="https://user-images.githubusercontent.com/41842296/224555698-71d1fb5d-9359-483b-8d98-64f952a44a60.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555703-5a53cc2c-5375-4b07-bac1-aed03f34ca87.png" width="31%"/>
  <img src="https://user-images.githubusercontent.com/41842296/224555708-05edf0fa-7b74-4f2b-b4fd-f4d7d82e911b.png" width="31%"/>
  </div>
  
  ### Desktop
  <img width="1511" alt="Screenshot 2023-03-12 at 4 44 04 PM" src="https://user-images.githubusercontent.com/41842296/224555755-5d033ac5-061e-41d7-92b1-4e5c807dfb67.png">

## Installation
  ### Android
  Requires Android Studio and functioning Android device
  1. Download zip file of project
  2. Extract zip file
  3. Open extracted file in android studio using File > Open...
  4. In android studio, build the project using Build > Build Apk(s)
  5. Open and run the apk file on an android device
  6. Allow permissions for app to install when prompted
  7. Installation complete
     
  ### IOS
  Requires Xcode, iTunes, and funtioning IOS device
  1. Download project and open in Xcode
  2. Select device target as Generic IOS device or Any IOS device (arm64)
  3. Under the Product menu, click Clean, then Archive
  4. Selet the app and Export it, select Ad Hoc, Development, or Enterprise as method
  5. Select Distribution Certificate and Provisioning Profile to genere the ipa
  6. Export the ipa to desired location
  7. Open iTunes and connect IOS device to computer using a usb cable
  8. Transfer the ipa file to the connected device
  9. The ipa will install and installation is complete

  ### Desktop
  Requires Gradle
  1. Clone the repository to your local device
  2. Run "gradle init --dsl kotlin" in command line to initialize Gradle
  3. Build and invoke the project using "./gradlew build"
  4. Installation is complete and project is built

## PokeAPI

<div>
Pokedex uses [PokeAPI](https://pokeapi.co/) for fetching data related to Pokémon.

<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="right" width="21%"/>
</div>

## Authors

- [@MohamedRejeb](https://www.github.com/MohamedRejeb)


## Original design

Adapted from [Pokédex Apps design](https://dribbble.com/shots/17332968-Pok-dex-Apps-Design-Exploration/) by [Nur Asmara](https://dribbble.com/nurasmara/).

## 🔗 Social Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/MohamedRejeb/) <br>
[Follow me](https://github.com/MohamedRejeb) on GitHub


# License
```xml
Copyright 2023 M0Coding (Mohamed Ben Rejeb)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
