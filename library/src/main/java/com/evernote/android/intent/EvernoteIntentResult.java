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
