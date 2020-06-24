package com.idata.filedemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.idata.filedemo.bean.Constant;
import com.idata.filedemo.R;
import com.idata.filedemo.utils.FileUtils;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";
    private Button mWriteFile;
    private Button mReadFile;
    private Button mDeleteFile;
    private EditText mEditText;
    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_layout);
        initView();
    }

    private void initView() {
        mWriteFile = findViewById(R.id.button_write);
        mEditText = findViewById(R.id.et_text);
        mTvShow = findViewById(R.id.tv_read);
        mReadFile = findViewById(R.id.button_read);
        mDeleteFile = findViewById(R.id.button_delete);
        mWriteFile.setOnClickListener(this);
        mDeleteFile.setOnClickListener(this);
        mReadFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_read:
                String text = FileUtils.ReadTxtFile(Constant.FilePath);
                if (!TextUtils.isEmpty(text)) {
                    mTvShow.setText(text);
                } else {
                    mTvShow.setText(null);
                }
                break;
            case R.id.button_write:
                if (mEditText.getText() != null && !TextUtils.isEmpty(mEditText.getText().toString())) {
                    String packName = mEditText.getText().toString();
                    initData(packName);
                    mEditText.setText("");
                }
                break;
            case R.id.button_delete:
                if (!TextUtils.isEmpty(Constant.FilePath)) {
                    FileUtils.delete(Constant.FilePath);
                    mTvShow.setText(null);
                }
                break;
            default:
                break;
        }
    }

    private void initData(String str) {
        String filePath = "/storage/sdcard0/test/";
        String fileName = "test.txt";
        FileUtils.writeTxtToFile(str, filePath, fileName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(Constant.FilePath)) {
            String text = FileUtils.ReadTxtFile(Constant.FilePath);
            if (!TextUtils.isEmpty(text)) {
                mTvShow.setText(text);
            } else {
                mTvShow.setText(null);
            }
        }
    }

}
