package com.evernote.android.intent;

import android.app.SearchManager;
import android.support.annotation.Nullable;

/**
 * @author rwondratschek
 */
public final class SearchNotesIntentBuilder extends IntentBuilder<SearchNotesIntentBuilder> {

    /*package*/ SearchNotesIntentBuilder() {
        super(EvernoteIntent.ACTION_SEARCH_NOTES);
    }

    /**
     * @param query The query for the notes. If {@code null} then the current value gets removed.
     * @return This Builder object to allow for chaining of calls to set methods.
     */
    public SearchNotesIntentBuilder setQuery(@Nullable String query) {
        return putString(SearchManager.QUERY, query);
    }


}
