# AnimatedTabLayout
Yet another tab layout.
<img src="https://raw.githubusercontent.com/iammert/AnimatedTabLayout/master/art/cover.png"/>

# GIF
<img src="https://raw.githubusercontent.com/iammert/AnimatedTabLayout/master/art/gf.gif"/>


# Motivation
Thanks to [Igor](https://dribbble.com/motionigor) for sharing this awesome animation. I motivated myself to create android library.

[Check out the concept](https://www.uplabs.com/posts/phone-app-animation-asus-zenui-6-0-concept)

# Setup
* Create your tabs file under res/xml/ folder.
```html
<?xml version="1.0" encoding="utf-8"?>
<tabs size="@dimen/size"
    space="@dimen/space">

    <tab
        inactiveColor="@color/colorGray"
        activeColor="@color/colorOrange"
        drawable="@drawable/ic_star_white_24dp"/>

    <tab
        inactiveColor="@color/colorGray"
        activeColor="@color/colorBlue"
        drawable="@drawable/ic_dialpad_white_24dp"/>

    <tab
        inactiveColor="@color/colorGray"
        activeColor="@color/colorRed"
        drawable="@drawable/ic_access_time_white_24dp"/>

    <tab
        inactiveColor="@color/colorGray"
        activeColor="@color/colorPurple"
        drawable="@drawable/ic_account_circle_white_24dp"/>
</tabs>
```

* Add tabs
```xml
<com.iammert.library.AnimatedTabLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:atl_tabs="@xml/tabs"/>
```
* Setup with viewpager or listen change on tab click.
```kotlin
atl.setupViewPager(viewpager)
atl.setTabChangeListener(object : AnimatedTabLayout.OnChangeListener {
    override fun onChanged(position: Int) {
    }
})
```

# Dependency
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
      implementation 'com.github.iammert:AnimatedTabLayout:0.1'
}
```

# TODO
- [ ] Animated Vector Drawable on tab expand.
- [ ] tabMode support(scrolling, fixed)

License
--------


    Copyright 2018 Mert Şimşek.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.







