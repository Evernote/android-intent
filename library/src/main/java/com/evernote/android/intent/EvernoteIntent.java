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
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * This class serves as entry point to create Intents targeting the Evernote app.
 *
 * @author rwondratschek
 */
public final class EvernoteIntent {

    public static final String ACTION_CREATE_NEW_NOTE = "com.evernote.action.CREATE_NEW_NOTE";
    public static final String ACTION_SEARCH_NOTES = "com.evernote.action.SEARCH_NOTES";
    public static final String ACTION_VIEW_NOTE = "com.evernote.action.VIEW_NOTE";
    public static final String ACTION_NEW_SNAPSHOT = "com.evernote.action.NEW_SNAPSHOT";
    public static final String ACTION_NEW_VOICE_NOTE = "com.evernote.action.NEW_VOICE_NOTE";
    public static final String ACTION_SEARCH = "com.evernote.action.SEARCH";
    public static final String ACTION_NOTE_PICKER = "com.evernote.action.NOTE_PICKER";

    public static final String EXTRA_NOTE_GUID = "NOTE_GUID";
    public static final String EXTRA_NOTEBOOK_GUID = "NOTEBOOK_GUID";
    public static final String EXTRA_TAG_NAME_LIST = "TAG_NAME_LIST";
    public static final String EXTRA_SOURCE_URL = "SOURCE_URL";
    public static final String EXTRA_SOURCE_APP = "SOURCE_APP";
    public static final String EXTRA_BASE_URL = "BASE_URL";

    public static final String EXTRA_FORCE_NO_UI = "FORCE_NO_UI";
    public static final String EXTRA_QUICK_SEND = "QUICK_SEND";
    public static final String EXTRA_FULL_SCREEN  = "FULL_SCREEN";

    public static final String PACKAGE_NAME = "com.evernote";

    public static final String MIME_TYPE_ENEX = "application/enex";

    private EvernoteIntent() {
        // no op
    }

    /**
     * @return A new builder object holding additional parameters for creating a new note.
     */
    public static CreateNewNoteIntentBuilder createNewNote() {
        return new CreateNewNoteIntentBuilder();
    }

    public static ImportEnexIntentBuilder importEnexFile() {
        return new ImportEnexIntentBuilder();
    }

    /**
     * @return A new builder object holding additional parameters for searching notes.
     */
    public static SearchNotesIntentBuilder searchNotes() {
        return new SearchNotesIntentBuilder();
    }

    /**
     * @return A new builder object holding additional parameters to view a note.
     */
    public static ViewNoteIntentBuilder viewNote() {
        return new ViewNoteIntentBuilder();
    }

    /**
     * The returned builder object doesn't accept additional parameters. This may change in the future.
     *
     * @return An empty builder object, which doesn't accept additional parameters.
     */
    public static IntentBuilder newSnapshot() {
        return new NoArgsIntentBuilder(ACTION_NEW_SNAPSHOT);
    }

    /**
     * The returned builder object doesn't accept additional parameters. This may change in the future.
     *
     * @return An empty builder object, which doesn't accept additional parameters.
     */
    public static IntentBuilder newVoiceNote() {
        return new NoArgsIntentBuilder(ACTION_NEW_VOICE_NOTE);
    }

    /**
     * The returned builder object doesn't accept additional parameters. This may change in the future.
     *
     * @return An empty builder object, which doesn't accept additional parameters.
     */
    public static IntentBuilder newSearch() {
        return new NoArgsIntentBuilder(ACTION_SEARCH);
    }

    /**
     * The returned builder object doesn't accept additional parameters. This may change in the future.
     *
     * <br>
     * <br>
     *
     * Call {@link Activity#startActivityForResult(Intent, int)} to fire this intent and override
     * {@link Activity#onActivityResult(int, int, Intent)} to get the returned note. The returned data
     * usually contains the note and notebook GUID.
     *
     * @return An empty builder object, which doesn't accept additional parameters.
     * @see EvernoteIntentResult#getNoteGuid(Intent)
     * @see EvernoteIntentResult#getNotebookGuid(Intent)
     */
    public static IntentBuilder pickNote() {
        return new NoArgsIntentBuilder(ACTION_NOTE_PICKER);
    }

    /**
     * @return {@code true} if Evernote is installed and can be launched.
     */
    public static boolean isEvernoteInstalled(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(PACKAGE_NAME, PackageManager.GET_ACTIVITIES);
            return true;

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
