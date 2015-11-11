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

/**
 * @author rwondratschek
 */
public class ViewNoteIntentBuilder extends IntentBuilder<ViewNoteIntentBuilder> {

    /*package*/ ViewNoteIntentBuilder() {
        super(EvernoteIntent.ACTION_VIEW_NOTE);
    }

    /**
     * @param noteGuid The desired GUID of the note which should be opened. An error message will open,
     *                 if the note can't be found or isn't associated with the signed in account.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public ViewNoteIntentBuilder setNoteGuid(String noteGuid) {
        return putString(EvernoteIntent.EXTRA_NOTE_GUID, noteGuid);
    }

    /**
     * @param fullScreen If {@code true} note is viewed in full screen mode.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public ViewNoteIntentBuilder setFullScreen(boolean fullScreen) {
        return putBoolean(EvernoteIntent.EXTRA_FULL_SCREEN, fullScreen);
    }

    @Override
    protected ViewNoteIntentBuilder self() {
        return this;
    }
}
