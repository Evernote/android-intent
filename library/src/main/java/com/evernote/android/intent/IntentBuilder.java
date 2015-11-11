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
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * @author rwondratschek
 */
public abstract class IntentBuilder<T extends IntentBuilder<T>> {

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

    protected T putString(@NonNull String key, @Nullable String value) {
        if (TextUtils.isEmpty(value)) {
            mArgs.remove(key);
        } else {
            mArgs.putString(key, value);
        }
        return self();
    }

    protected T putStringArrayList(@NonNull String key, @Nullable ArrayList<String> value) {
        if (value == null || value.isEmpty()) {
            mArgs.remove(key);
        } else {
            mArgs.putStringArrayList(key, value);
        }
        return self();
    }

    protected T putBoolean(@NonNull String key, boolean value) {
        mArgs.putBoolean(key, value);
        return self();
    }

    protected T putParcelable(@NonNull String key, @Nullable Parcelable value) {
        if (value == null) {
            mArgs.remove(key);
        } else {
            mArgs.putParcelable(key, value);
        }
        return self();
    }

    protected T putParcelableArrayList(@NonNull String key, @Nullable ArrayList<? extends Parcelable> value) {
        if (value == null) {
            mArgs.remove(key);
        } else {
            mArgs.putParcelableArrayList(key, value);
        }
        return self();
    }

    protected T setUri(@Nullable Uri uri, @Nullable String mimeType) {
        if (uri == null || TextUtils.isEmpty(mimeType)) {
            return setUris(null, null);
        } else {
            mIntent.setType(mimeType);
            return putParcelable(Intent.EXTRA_STREAM, uri);
        }
    }

    protected T setUris(@Nullable ArrayList<Uri> uris, @Nullable String mimeType) {
        if (uris == null || uris.isEmpty() || TextUtils.isEmpty(mimeType)) {
            mIntent.setType(null);
            mArgs.remove(Intent.EXTRA_STREAM);
            return self();

        } else {
            mIntent.setType(mimeType);
            return putParcelableArrayList(Intent.EXTRA_STREAM, uris);
        }
    }

    protected abstract T self();
}
