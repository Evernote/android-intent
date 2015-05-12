package com.evernote.android.intent;

/**
 * A helper class which might be exchanged in the future for additional parameters.
 *
 * @author rwondratschek
 */
public class NoArgsIntentBuilder extends IntentBuilder<NoArgsIntentBuilder> {

    /*package*/ NoArgsIntentBuilder(String action) {
        super(action);
    }
}
