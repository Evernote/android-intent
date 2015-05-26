package com.evernote.android.intent.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.evernote.android.intent.CreateNewNoteIntentBuilder;
import com.evernote.android.intent.EvernoteIntent;
import com.evernote.android.intent.EvernoteIntentResult;

/**
 * @author rwondratschek
 */
public class MainActivity extends Activity {

    private static final String KEY_NOTE_GUID = "KEY_NOTE_GUID";

    private static final int REQ_PICK_NOTE = 200;

    private String mNoteGuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mNoteGuid = savedInstanceState.getString(KEY_NOTE_GUID);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!TextUtils.isEmpty(mNoteGuid)) {
            outState.putString(KEY_NOTE_GUID, mNoteGuid);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_PICK_NOTE:
                if (resultCode != RESULT_OK || data == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    mNoteGuid = EvernoteIntentResult.getNoteGuid(data);
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button_share_plain_text:
                sharePlainTextNote();
                break;

            case R.id.button_share_html:
                shareHtml();
                break;

            case R.id.button_search:
                search();
                break;

            case R.id.button_pick_note:
                pickNote();
                break;

            case R.id.button_view_note:
                viewNote();
                break;

            case R.id.button_new_snapshot:
                newSnapshot();
                break;

            case R.id.button_new_voice_note:
                newVoiceNote();
                break;

            case R.id.button_new_search:
                newSearch();
                break;

            case R.id.button_is_installed:
                checkIsInstalled();
                break;

            default:
                throw new IllegalStateException("not implemented");
        }
    }

    private void sharePlainTextNote() {
        Intent intent = EvernoteIntent.createNewNote()
                .setTitle("Intent Demo Title")
                .setAuthor("Intent demo app")
                .addTags("Intent Demo Tag")
                .setTextPlain("This note is created by the Evernote intent demo application. https://github.com/evernote/android-intent")
                .setSourceApp("Intent demo app")
                .setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility.QUICK_SEND)
                .create();

        startActivity(intent);
    }

    private void shareHtml() {
        String html = "<h1>Share HTML - Intent Demo</h1>\n"
                + "<p>Many HTML features are supported, e.g.</p>"
                + "<ul>\n"
                + "<li><b>Styled</b> <span style=\"color:red\">text</span></li>\n"
                + "<li>Full HTML pages or HTML snippets</li>\n"
                + "<li>Relative URLs</li>\n"
                + "<li>Referenced images</li>\n"
                + "<li>Referenced web resources</li>\n"
                + "<li>...</li>\n"
                + "</ul>\n"
                + "<br/>"
                + "<p>The image below is embedded into a link, so feel free to click on it.</p> ";


        html += "<a href=\"https://evernote.com\" title=\"Evernote\">\n"
                + "\t<img alt=\"Evernote\" src=\"/wp-content/uploads/2014/11/Screen-Shot-2014-11-05-at-4.11.09-PM-232x232.png\" />";

        Intent intent = EvernoteIntent.createNewNote()
                .setTitle("Share HTML")
                .addTags("Intent Demo Tag")
                .setTextHtml(html, "https://blogassets.evernote.com")
                .setSourceApp("Intent demo app")
                .setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility.QUICK_SEND)
                .create();

        startActivity(intent);
    }

    private void search() {
        Intent intent = EvernoteIntent.searchNotes()
                .setQuery("Intent Demo")
                .create();

        startActivity(intent);
    }

    private void pickNote() {
        startActivityForResult(EvernoteIntent.pickNote().create(), REQ_PICK_NOTE);
    }

    private void viewNote() {
        if (TextUtils.isEmpty(mNoteGuid)) {
            Toast.makeText(this, "Pick a note first", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = EvernoteIntent.viewNote()
                .setNoteGuid(mNoteGuid)
                .setFullScreen(true)
                .create();

        startActivity(intent);
    }

    private void newSnapshot() {
        startActivity(EvernoteIntent.newSnapshot().create());
    }

    private void newVoiceNote() {
        startActivity(EvernoteIntent.newVoiceNote().create());
    }

    private void newSearch() {
        startActivity(EvernoteIntent.newSearch().create());
    }

    private void checkIsInstalled() {
        Toast.makeText(this, EvernoteIntent.isEvernoteInstalled(this) ? "Evernote is installed" : "Evernote is not installed", Toast.LENGTH_SHORT).show();
    }
}
