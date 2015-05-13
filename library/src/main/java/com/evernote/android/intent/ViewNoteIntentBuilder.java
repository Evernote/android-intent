package com.evernote.android.intent;

/**
 * @author rwondratschek
 */
public class ViewNoteIntentBuilder extends IntentBuilder {

    /*package*/ ViewNoteIntentBuilder() {
        super(EvernoteIntent.ACTION_VIEW_NOTE);
    }

    /**
     * @param noteGuid The desired GUID of the note which should be opened. An error message will open,
     *                 if the note can't be found or isn't associated with the signed in account.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public ViewNoteIntentBuilder setNoteGuid(String noteGuid) {
        putString(EvernoteIntent.EXTRA_NOTE_GUID, noteGuid);
        return this;
    }

    /**
     * @param fullScreen If {@code true} note is viewed in full screen mode.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public ViewNoteIntentBuilder setFullScreen(boolean fullScreen) {
        mArgs.putBoolean(EvernoteIntent.EXTRA_FULL_SCREEN, fullScreen);
        return this;
    }

}
