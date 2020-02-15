package com.tejas.weatherforecast.view.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;

import com.tejas.weatherforecast.R;

public class CustomProgressDialog extends ProgressDialog {

    private static final String TAG = CustomProgressDialog.class.getSimpleName();
    private static final long TIMER_LENGTH = 1800;

    private boolean isCancelable;

    public CustomProgressDialog(Context context, boolean isCancelable) {
        super(context);
        this.isCancelable = isCancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress_dialog);
        if (null != getWindow()) {
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setCancelable(isCancelable);
        setCanceledOnTouchOutside(false);
        CustomProgressBarView progressBar = (CustomProgressBarView) findViewById(R.id.circularProgressbar);
        progressBar.start(TIMER_LENGTH);
    }

    @Override
    public void onBackPressed() {
        if(isCancelable) {
            this.dismiss();
        } else {
            super.onBackPressed();
        }
    }
}
