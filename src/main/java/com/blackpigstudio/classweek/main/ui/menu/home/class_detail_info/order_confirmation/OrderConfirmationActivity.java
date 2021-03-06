package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.order_confirmation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.domain.Schedule;

public class OrderConfirmationActivity extends ActionBarActivity implements ViewForOrderConfirmationActivity.IController {

    public static final String BUNDLE_PARM_CLASS_SUMMARY_INFO = "CLASS_SUMMARY_INFO";
    public static final String BUNDLE_PARM_SCHEDULES = "SCHEDULES";
    private ClassSummaryInfo classSummaryInfo;
    private Schedule schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        classSummaryInfo = (ClassSummaryInfo)intent.getSerializableExtra(BUNDLE_PARM_CLASS_SUMMARY_INFO);
        schedule = (Schedule)intent.getSerializableExtra(BUNDLE_PARM_SCHEDULES);
        ViewForOrderConfirmationActivity view = new ViewForOrderConfirmationActivity(getApplicationContext(),this);
        setContentView(view.getRoot());
    }


    @Override
    public void onPaymentRequested() {
        Intent intent = new Intent();
        // TODO: deliver selected schedule and datetime String.
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
