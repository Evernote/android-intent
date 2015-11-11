package com.evernote.android.intent.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.evernote.android.intent.CreateNewNoteIntentBuilder;
import com.evernote.android.intent.EvernoteIntent;
import com.evernote.android.intent.EvernoteIntentResult;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
        if (!EvernoteIntent.isEvernoteInstalled(this)) {
            Toast.makeText(this, R.string.evernote_not_installed, Toast.LENGTH_LONG).show();
            return;
        }

        switch (view.getId()) {
            case R.id.button_share_plain_text:
                sharePlainTextNote();
                break;

            case R.id.button_share_html:
                shareHtml();
                break;

            case R.id.button_share_enex:
                shareEnex();
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
        ArrayList<Uri> images = new ArrayList<>();

        try {
            Uri uri = createLocalFile("test1.jpeg", new InputStreamCreator() {
                @Override
                public InputStream open(String fileName) throws IOException {
                    return getResources().openRawResource(R.raw.image);
                }
            });

            grantUriPermission(EvernoteIntent.PACKAGE_NAME, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            for (int i = 0; i < 3; i++) {
                images.add(uri);
            }

        } catch (IOException e) {
            Toast.makeText(this, "Could not attach image", Toast.LENGTH_SHORT).show();
            Log.e("Demo", "IOException", e);
        }

        Intent intent = EvernoteIntent.createNewNote()
                .setTitle("Intent Demo Title")
                .addTags("Intent Demo Tag")
                .setTextPlain("This note is created by the Evernote intent demo application. https://github.com/evernote/android-intent")
                .setSourceApp(getPackageName())
                .setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility.QUICK_SEND)
                .setUris(images, "image/*")
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
                .setSourceApp(getPackageName())
                .setAppVisibility(CreateNewNoteIntentBuilder.AppVisibility.QUICK_SEND)
                .create();

        startActivity(intent);
    }

    private void shareEnex() {
        Uri localEnexFile;
        try {
            localEnexFile = createLocalFile("notes.enex", new InputStreamCreator() {
                @Override
                public InputStream open(String fileName) throws IOException {
                    return getAssets().open(fileName);
                }
            });

            grantUriPermission(EvernoteIntent.PACKAGE_NAME, localEnexFile, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        } catch (IOException e) {
            Toast.makeText(this, "Could not create local file", Toast.LENGTH_SHORT).show();
            Log.e("Demo", "IOException", e);
            return;
        }

        Intent intent = EvernoteIntent.importEnexFile()
                .setEnexFile(localEnexFile)
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

    private Uri createLocalFile(String fileName, InputStreamCreator inputStreamCreator) throws IOException {
        File file = new File(new File(getCacheDir(), "share"), fileName);
        if (file.exists()) {
            return FileProvider.getUriForFile(this, "com.evernote.android.intent.demo.fileprovider", file);
        }

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException();
        }

        if (!file.exists() && !file.createNewFile()) {
            throw new IOException();
        }

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            inputStream = new BufferedInputStream(inputStreamCreator.open(fileName));

            byte[] buffer = new byte[2048];
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);
            }

            return createLocalFile(fileName, inputStreamCreator);

        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private interface InputStreamCreator {
        InputStream open(String fileName) throws IOException;
    }
}
