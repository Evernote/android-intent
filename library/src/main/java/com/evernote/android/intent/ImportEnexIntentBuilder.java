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

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * @author rwondratschek
 */
@SuppressWarnings("unused")
public final class ImportEnexIntentBuilder extends IntentBuilder {

    /*package*/ ImportEnexIntentBuilder() {
        super(EvernoteIntent.ACTION_CREATE_NEW_NOTE);
    }

    /**
     * @param notebookGuid The notebook in which the imported notes should be stored. The notebook
     *                     already must exist. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public ImportEnexIntentBuilder setNotebookGuid(@Nullable String notebookGuid) {
        putString(EvernoteIntent.EXTRA_NOTEBOOK_GUID, notebookGuid);
        return this;
    }

    /**
     * @param uri The URI pointing to an existing ENEX file. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_STREAM
     */
    public ImportEnexIntentBuilder setEnexFile(@Nullable Uri uri) {
        if (uri == null) {
            return setEnexFiles(null);
        } else {
            mIntent.setType(EvernoteIntent.MIME_TYPE_ENEX);
            mArgs.putParcelable(Intent.EXTRA_STREAM, uri);
            return this;
        }
    }

    /**
     * @param uris Multiple URIs pointing to existing ENEX files. If {@code null} then the current
     *             value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_STREAM
     */
    public ImportEnexIntentBuilder setEnexFiles(@Nullable ArrayList<Uri> uris) {
        if (uris == null || uris.isEmpty()) {
            mIntent.setType(null);
            mArgs.remove(Intent.EXTRA_STREAM);

        } else {
            mIntent.setType(EvernoteIntent.MIME_TYPE_ENEX);
            mArgs.putParcelableArrayList(Intent.EXTRA_STREAM, uris);
        }
        return this;
    }

    /**
     * The default value is {@link CreateNewNoteIntentBuilder.AppVisibility#OPEN_COMPOSER}.
     *
     * @param visibility Decides whether and with which UI the Evernote app is opened.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public ImportEnexIntentBuilder setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility visibility) {
        CreateNewNoteIntentBuilder.setAppVisibility(mArgs, visibility);
        return this;
    }
}
