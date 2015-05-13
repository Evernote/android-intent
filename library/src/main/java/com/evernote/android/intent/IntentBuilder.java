package com.evernote.android.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * @author rwondratschek
 */
public abstract class IntentBuilder {

    protected final Intent mIntent;
    protected final Bundle mArgs;

    /*package*/ IntentBuilder(String action) {
        mIntent = new Intent(action);
        mArgs = new Bundle();
    }

    public Intent create() {
        mIntent.putExtras(mArgs);
        return mIntent;
    }

    protected void putString(@NonNull String key, @Nullable String value) {
        if (TextUtils.isEmpty(value)) {
            mArgs.remove(key);
        } else {
            mArgs.putString(key, value);
        }
    }

    protected void putStringArrayList(@NonNull String key, @Nullable ArrayList<String> value) {
        if (value == null || value.isEmpty()) {
            mArgs.remove(key);
        } else {
            mArgs.putStringArrayList(key, value);
        }
    }
}
