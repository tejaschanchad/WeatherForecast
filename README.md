# WeatherForecast

This is weather forecast android application. 5 day forecast is available for available cities in application. It includes weather data every 3 hours.

It is design in MVVM. It is required Internet for fetching weather forecast data from web-services. Please select city for weather forecast information from dropdown list.

Features:

1. 5 day weather forecast showing Date, Time, Min. temperature and Max. temperature.

How to Use:

1. Select city from spinner control
2. After selection of city, it will call api.openweathermap.org web-services for collecting data for 5 day forecast
3. After getting all data, list (recycleview with card) displaying Date, Time, Min. temperature and Max. temperature.

How to Build/Run iWeather Github Project:

1. Clone/Download project code from - https://github.com/tejaschanchad/WeatherForecast
2. Open project in Android Studio
3. Select Build (Menu in Android Studio) -> click on clean or Open Terminal in Android Studio and run ./gradlew clean.
4. After successful clean project - Select Build (Menu in Android Studio) -> click on Build Bundle/APK or After successful clean project  run ./gradlew assembleDebug. It will generating debug apk in app->build->output->apk->debug path.
5. Open terminal, connect physical device via USB or open Emulator from Android Studio and run adb command - adb install apk_path

Application Technical Details:

1. Application Name: WeatherForecast
2. compileSdkVersion: 28
3. applicationId: "com.tejas.weatherforecast"
4. minSdkVersion: 21
5. targetSdkVersion: 28
6. versionCode: 1
7. versionName: 1.1.1

Note: This application is for demo purpose only.
