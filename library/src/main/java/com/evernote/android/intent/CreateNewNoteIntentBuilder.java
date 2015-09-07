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
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author rwondratschek
 */
@SuppressWarnings("unused")
public final class CreateNewNoteIntentBuilder extends IntentBuilder {

    /*package*/ CreateNewNoteIntentBuilder() {
        super(EvernoteIntent.ACTION_CREATE_NEW_NOTE);
    }

    /**
     * @param title The title for the new note. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_TITLE
     */
    public CreateNewNoteIntentBuilder setTitle(@Nullable String title) {
        putString(Intent.EXTRA_TITLE, title);
        return this;
    }

    /**
     * @param plainText The text content for the new note. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_TEXT
     */
    public CreateNewNoteIntentBuilder setTextPlain(@Nullable String plainText) {
        putString(Intent.EXTRA_TEXT, plainText);
        return this;
    }

    /**
     * @param notebookGuid The notebook in which the new note should be stored. The notebook already must exist.
     *                     If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder setNotebookGuid(@Nullable String notebookGuid) {
        putString(EvernoteIntent.EXTRA_NOTEBOOK_GUID, notebookGuid);
        return this;
    }

    /**
     * Adds more tags. Any existing tags aren't overwritten.
     *
     * @param tags The tags which should be added to the existing ones.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder addTags(String... tags) {
        return addTags(new ArrayList<>(Arrays.asList(tags)));
    }

    /**
     * Adds more tags. Any existing tags aren't overwritten.
     *
     * @param tags The tags which should be added to the existing ones.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder addTags(@Nullable ArrayList<String> tags) {
        ArrayList<String> list = mArgs.getStringArrayList(EvernoteIntent.EXTRA_TAG_NAME_LIST);
        if (list == null) {
            list = tags;

        } else if (tags != null) {
            list.addAll(tags);
        }

        return setTags(list);
    }

    /**
     * Set the tags for the new note. <b>Caution: </b>Any existing tags are overwritten. Use
     * {@link #addTags(String...)} to keep the already set tags in this builder.
     *
     * @param tags The tags which should be added to the new note.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder setTags(String... tags) {
        return setTags(new ArrayList<>(Arrays.asList(tags)));
    }

    /**
     * Set the tags for the new note. <b>Caution: </b>Any existing tags are overwritten. Use
     * {@link #addTags(ArrayList)} to keep the already set tags in this builder.
     *
     * @param tags The tags which should be added to the new note. If {@code null} then the current
     *             value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder setTags(@Nullable ArrayList<String> tags) {
        putStringArrayList(EvernoteIntent.EXTRA_TAG_NAME_LIST, tags);
        return this;
    }

    /**
     * @param author The author of the new note. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder setAuthor(@Nullable String author) {
        putString(EvernoteIntent.EXTRA_AUTHOR, author);
        return this;
    }

    /**
     * @param sourceUrl The source url of the new note. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder setSourceUrl(@Nullable String sourceUrl) {
        putString(EvernoteIntent.EXTRA_SOURCE_URL, sourceUrl);
        return this;
    }

    /**
     * @param sourceApp The source app of the new note. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder setSourceApp(@Nullable String sourceApp) {
        putString(EvernoteIntent.EXTRA_SOURCE_APP, sourceApp);
        return this;
    }

    /**
     * @param uri The URI pointing to an existing resource. If {@code null} then the current value gets removed.
     * @param mimeType The mime type of the resource. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_STREAM
     */
    public CreateNewNoteIntentBuilder setUri(@Nullable Uri uri, @Nullable String mimeType) {
        if (uri == null || TextUtils.isEmpty(mimeType)) {
            return setUris(null, null);
        } else {
            mIntent.setType(mimeType);
            mArgs.putParcelable(Intent.EXTRA_STREAM, uri);
            return this;
        }
    }

    /**
     * @param uris Multiple URIs pointing to existing resources. If {@code null} then the current
     *             value gets removed.
     * @param mimeType The mime type of the resources. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_STREAM
     */
    public CreateNewNoteIntentBuilder setUris(@Nullable ArrayList<Uri> uris, @Nullable String mimeType) {
        if (uris == null || uris.isEmpty() || TextUtils.isEmpty(mimeType)) {
            mIntent.setType(null);
            mArgs.remove(Intent.EXTRA_STREAM);

        } else {
            mIntent.setType(mimeType);
            mArgs.putParcelableArrayList(Intent.EXTRA_STREAM, uris);
        }
        return this;
    }

    /**
     * The Evernote app will convert the HTML content to ENML. It will fetch resources and apply
     * supported styles. You can either send an HTML snippet or a full HTML page.
     *
     * <br>
     * <br>
     *
     * You cannot send plain text content and HTML at the same time. This method uses the {@link Intent#EXTRA_TEXT}
     * field and sets the type of the Intent to {@code text/html}. Previous set content with {@link #setTextPlain(String)}
     * is overwritten.
     *
     * @param html The HTML content which is converted to ENML in the Evernote app. If {@code null}
     *             then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_TEXT
     * @see Intent#setType(String)
     */
    public CreateNewNoteIntentBuilder setTextHtml(@Nullable String html) {
        return setTextHtml(html, null);
    }

    /**
     * The Evernote app will convert the HTML content to ENML. It will fetch resources and apply
     * supported styles. You can either send an HTML snippet or a full HTML page.
     *
     * <br>
     * <br>
     *
     * You cannot send plain text content and HTML at the same time. This method uses the {@link Intent#EXTRA_TEXT}
     * field and sets the type of the Intent to {@code text/html}. Previous set content with {@link #setTextPlain(String)}
     * is overwritten.
     *
     * @param html The HTML content which is converted to ENML in the Evernote app. If {@code null}
     *             then the current value gets removed.
     * @param baseUrl The base URL for resources with relative URLs within the HTML. If {@code null}
     *                then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     * @see Intent#EXTRA_TEXT
     * @see Intent#setType(String)
     */
    public CreateNewNoteIntentBuilder setTextHtml(@Nullable String html, @Nullable String baseUrl) {
        if (TextUtils.isEmpty(html)) {
            mIntent.setType(null);
        } else {
            mIntent.setType("text/html");
        }

        putString(Intent.EXTRA_TEXT, html);
        putString(EvernoteIntent.EXTRA_BASE_URL, baseUrl);
        return this;
    }

    /**
     * The default value is {@link AppVisibility#OPEN_COMPOSER}.
     *
     * @param visibility Decides whether and with which UI the Evernote app is opened.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public CreateNewNoteIntentBuilder setAppVisibility(AppVisibility visibility) {
        setAppVisibility(mArgs, visibility);
        return this;
    }

    /*package*/ static void setAppVisibility(Bundle args, AppVisibility visibility) {
        if (visibility == null) {
            visibility = AppVisibility.OPEN_COMPOSER;
        }

        switch (visibility) {
            case OPEN_COMPOSER:
                args.remove(EvernoteIntent.EXTRA_QUICK_SEND);
                args.remove(EvernoteIntent.EXTRA_FORCE_NO_UI);
                break;

            case QUICK_SEND:
                args.remove(EvernoteIntent.EXTRA_FORCE_NO_UI);
                args.putBoolean(EvernoteIntent.EXTRA_QUICK_SEND, true);
                break;

            case NO_UI:
                args.remove(EvernoteIntent.EXTRA_QUICK_SEND);
                args.putBoolean(EvernoteIntent.EXTRA_FORCE_NO_UI, true);
                break;

            default:
                throw new IllegalStateException("not implemented");
        }
    }

    /**
     * Controls if the Evernote app gets opened.
     */
    public enum AppVisibility {
        /**
         * The Evernote app opens full screen and launches the composer with the new note.
         */
        OPEN_COMPOSER,

        /**
         * The Evernote app opens a small bubble where the user can add or remove tags and choose
         * the notebook for the new note.
         */
        QUICK_SEND,

        /**
         * The Evernote app won't open and the new note is created in the background.
         */
        NO_UI
    }
}
