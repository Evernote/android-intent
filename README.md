Evernote Android-Intent
=======================

This tiny library lets you easily send Android Intents to the main Evernote app.

Download
--------

Add the library as a dependency in your build.gradle file.

```groovy
dependencies {
    compile 'com.evernote:android-intent:1.0.0'
}
```

Usage
-----

`EvernoteIntent` serves as entry point and provides all possible Intent builders, e.g. you can create a new note, view an existing note or make a new snapshot.

Some returned builders provide additional parameters.

```java
private void sharePlainTextNote() {
    Intent intent = EvernoteIntent.createNewNote()
            .setTitle("Intent Demo Title")
            .setAuthor("Intent demo app")
            .addTags("Intent Demo Tag")
            .setTextPlain("This note is created by the Evernote intent demo application. https://github.com/evernote/android-intent")
            .setSourceApp("Intent demo app")
            .setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility.QUICK_SEND)
            .create();

    startActivity(intent);
}
```

License
-------
    Copyright (c) 2007-2015 by Evernote Corporation, All rights reserved.

    Use of the source code and binary libraries included in this package
    is permitted under the following terms:

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:

        1. Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.
        2. Redistributions in binary form must reproduce the above copyright
        notice, this list of conditions and the following disclaimer in the
        documentation and/or other materials provided with the distribution.

    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
