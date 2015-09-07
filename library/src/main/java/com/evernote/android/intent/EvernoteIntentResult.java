/*
 * Copyright 2007-present Evernote Corporation.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.evernote.android.intent;

import android.app.Activity;
import android.content.Intent;

/**
 * This class helps to parse returned values from the Evernote app in {@link Activity#onActivityResult(int, int, Intent)}.
 *
 * @author rwondratschek
 */
@SuppressWarnings("unused")
public final class EvernoteIntentResult {

    private EvernoteIntentResult() {
        // no op
    }

    /**
     * @param data The returned data from {@link Activity#onActivityResult(int, int, Intent)}.
     * @return The note GUID if it was set or {@code null}.
     */
    public static String getNoteGuid(Intent data) {
        return data == null ? null : data.getStringExtra(EvernoteIntent.EXTRA_NOTE_GUID);
    }

    /**
     * @param data The returned data from {@link Activity#onActivityResult(int, int, Intent)}.
     * @return The notebook GUID if it was set or {@code null}.
     */
    public static String getNotebookGuid(Intent data) {
        return data == null ? null : data.getStringExtra(EvernoteIntent.EXTRA_NOTEBOOK_GUID);
    }
}
