[![Release](https://jitpack.io/v/Qase/AndroidDateTimePicker.svg)](https://jitpack.io/#Qase/AndroidDateTimePicker)
[![Build Status](https://travis-ci.org/Qase/AndroidDateTimePicker.svg?branch=master)](https://travis-ci.org/Qase/AndroidDateTimePicker)
[![codebeat badge](https://codebeat.co/badges/21bd2793-337f-43eb-b774-240aaa202819)](https://codebeat.co/projects/github-com-qase-androiddatetimepicker-master)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maintainer: havlisimo](https://img.shields.io/badge/Maintainer-havlisimo-blue.svg)](mailto:tomas.havlicek@quanti.cz)
[![Qase: AndroidDateTimePicker](https://img.shields.io/badge/Qase-AndroidDateTimePicker-ff69b4.svg)](https://github.com/Qase/AndroidDateTimePicker)

# AndroidDateTimePicker

Easy to use android date and time picker. 

## Features
* Very easy to use
* Use both date and time or choose just one
* Written in kotlin 
* Sample [app](github/sampleApp.png) is ready to build


## Installation

Click [HERE](https://jitpack.io/#Qase/AndroidDateTimePicker).

## Code example
```Kotlin
val sdf = SimpleDateFormat("dd.MM.yyyy - HH:mm", Locale.getDefault())
val dateText : String? = null

val callback: (date: Date) -> Unit = { newDate ->
    dateText = sdf.format(newDate)
}

DateTimePickerDialog.show(
    supportFragmentManager,
    "fragment_datepicker",              //tag for fragment manager
    callback,                           //calback with selected date
    Date(),                             //current date
    DateTimePickerDialog.TIME_DATE      //choose one - DATE_TIME, TIME_ONLY, DATE_ONLY, TIME_DATE
)
```

<img src="github/datetimepicker.gif" width="400">

## License
[MIT](https://github.com/nishanths/license/blob/master/LICENSE)
