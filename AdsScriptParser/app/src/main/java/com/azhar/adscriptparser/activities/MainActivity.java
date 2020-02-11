package com.azhar.adscriptparser.activities;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.azhar.adscriptparser.R;
import com.azhar.adscriptparser.tools.AdsCodeParser;

/**
 * Created by Azhar Rivaldi on 11/02/2020.
 */

public class MainActivity extends AppCompatActivity {

    AppCompatButton mButtonParse;
    AppCompatEditText mEditCodeParser, mEditParsedCode;
    AdsCodeParser mParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        mParser = new AdsCodeParser();
        mEditCodeParser = findViewById(R.id.edit_code_to_parse);
        mButtonParse = findViewById(R.id.button_do_parse);
        mButtonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditCodeParser.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Masukan Kode Iklan Anda!", Toast.LENGTH_LONG).show();
                } else {
                    mParser.doParser(mEditCodeParser.getText().toString());
                    showDialogParsedCode();
                }
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showDialogParsedCode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Parsed Code");

        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.layout_parsed_code, null);
        mEditParsedCode = view.findViewById(R.id.edit_parsed_code);
        builder.setView(view);
        mEditParsedCode.setText(mParser.getParsedCode());

        builder.setPositiveButton("COPY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                setToClipboard(mEditParsedCode.getText().toString());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setToClipboard(String content) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Parsed Code", content);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_LONG).show();
    }

}
