package com.lll.lvlinlin20181203;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.lvlinlin20181203.sql.Dao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FlowLayout flow_layout;
    private EditText edit;
    private Dao dao;
    private long insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        flow_layout = findViewById(R.id.flow_layout);
        Button btn_search = findViewById(R.id.btn_search);
        Button btn_clear = findViewById(R.id.btn_clear);
        edit = findViewById(R.id.edit);
        btn_search.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        //创建Dao层
        dao = new Dao(this);
        Cursor query = dao.query("zk", null, null, null, null, null, null);
        if (query.moveToFirst()) {
            do {
                String name = query.getString(query.getColumnIndex("name"));
                flow_layout.addTextView(name);
            } while (query.moveToNext());
        }
        Button text01 = findViewById(R.id.text01);
        Button text02 = findViewById(R.id.text02);
        Button text03 = findViewById(R.id.text03);
        Button text04 = findViewById(R.id.text04);
        Button text05 = findViewById(R.id.text05);
        text01.setOnClickListener(this);
        text02.setOnClickListener(this);
        text03.setOnClickListener(this);
        text04.setOnClickListener(this);
        text05.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                String keys = edit.getText().toString().trim();
                flow_layout.addTextView(keys);
                ContentValues values = new ContentValues();
                values.put("name", keys);
                insert = dao.insert("zk", null, values);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clear:
                long delete = dao.delete("zk", null, null);
                Toast.makeText(this, "删除条数" + delete, Toast.LENGTH_SHORT).show();
                flow_layout.removeAllViews();
                break;
            case R.id.text01:
                Toast.makeText(this, "考拉三周年人气热销榜", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text02:
                Toast.makeText(this, "榴莲", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text03:
                Toast.makeText(this, "电动牙刷", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text04:
                Toast.makeText(this, "沐浴露", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text05:
                Toast.makeText(this, "豆豆鞋", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
