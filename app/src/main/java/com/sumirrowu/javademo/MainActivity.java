package com.sumirrowu.javademo;

import android.os.Bundle;
import android.widget.TextView;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewTarget(R.id.tv_helloword)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.tv_helloword)
    void tvClick() {
        textView.setText("Hello, world !!!!!!");
    }
}
