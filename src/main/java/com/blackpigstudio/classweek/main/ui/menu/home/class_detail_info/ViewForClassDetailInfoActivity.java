package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassDetailInfo;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.domain.class_info.FacilityInfo;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.AbstractViewForActivity;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class ViewForClassDetailInfoActivity extends AbstractViewForActivity {
    private Button bt_inquiry;
    private Button bt_booking;
    private OnInquiryChooseListener onInquiryChooseListener;
    private OnBookingChooseListener onBookingChooseListener;
    private TextView tv_title;
    private TextView tv_start_time_1;
    private TextView tv_start_time_2;
    private TextView tv_start_time_3;
    private TextView tv_one_day_price;
    private TextView tv_one_month_price;
    private TextView tv_location;
    private TextView tv_description;
    private TextView tv_prerequisite;
    private TextView tv_refund_info;

    private ImageView iv_toilet;
    private ImageView iv_fitting_room;
    private ImageView iv_shower_stall;
    private ImageView iv_locker;
    private ImageView iv_parking_lot;
    private ImageView iv_practice_room;
    private ImageView iv_instrument_rental;







    public ViewForClassDetailInfoActivity(Context context, OnInquiryChooseListener anOnInquiryChooseListener, OnBookingChooseListener anOnBookingChooseListener) {
        super(context);
        this.onInquiryChooseListener = anOnInquiryChooseListener;
        this.onBookingChooseListener = anOnBookingChooseListener;
    }

    @Override
    protected View inflate() {
        return LayoutInflater.from(getContext()).inflate(R.layout.activity_class_detail_info,null);
    }

    @Override
    protected void initViews() {
        bt_inquiry = (Button) findViewById(R.id.bt_detail_info_inquiry);
        bt_booking = (Button) findViewById(R.id.bt_detail_info_booking);
        tv_title = (TextView)findViewById(R.id.tv_class_detail_title);
        tv_start_time_1 = (TextView)findViewById(R.id.tv_class_detail_start_time_1);
        tv_start_time_2 = (TextView)findViewById(R.id.tv_class_detail_start_time_2);;
        tv_start_time_3 = (TextView)findViewById(R.id.tv_class_detail_start_time_3);;
        tv_one_day_price = (TextView)findViewById(R.id.tv_class_detail_one_day_price);
        tv_one_month_price = (TextView)findViewById(R.id.tv_class_detail_month_day_price);
        tv_location = (TextView)findViewById(R.id.tv_class_detail_location);
        tv_description = (TextView)findViewById(R.id.tv_class_detail_description);
        tv_prerequisite = (TextView)findViewById(R.id.tv_class_detail_prerequisite);
        tv_refund_info = (TextView)findViewById(R.id.tv_class_detail_refund_info);

        iv_toilet = (ImageView)findViewById(R.id.tv_class_detail_facility_toilet);
        iv_fitting_room = (ImageView)findViewById(R.id.iv_class_detail_facility_fitting_room);
        iv_shower_stall = (ImageView)findViewById(R.id.iv_class_detail_facility_shower_stall);
        iv_locker = (ImageView)findViewById(R.id.iv_class_detail_facility_locker);
        iv_parking_lot = (ImageView)findViewById(R.id.iv_class_detail_facility_parking_lot);
        iv_practice_room = (ImageView)findViewById(R.id.iv_class_detail_facility_practice_room);
        iv_instrument_rental = (ImageView)findViewById(R.id.iv_class_detail_facility_instrument_rental);
        
    }

    @Override
    protected void setEvent() {
        bt_inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInquiryChooseListener.onInquiryChoose();
            }
        });

        bt_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBookingChooseListener.onBookingChoose();
            }
        });


    }

    public void setData(ClassDetailInfo aClassDetailInfo, ClassSummaryInfo aClassSummaryInfo, FacilityInfo aFacilityInfo)
    {
        tv_title.setText(aClassSummaryInfo.getTitle());
        tv_one_day_price.setText(aClassSummaryInfo.getOneDayPrice()+"원");
        tv_one_month_price.setText(aClassSummaryInfo.getOneMonthPrice()+"원");
        tv_location.setText(aClassDetailInfo.getAddress());
        tv_description.setText(aClassDetailInfo.getDescriptions());
        tv_prerequisite.setText(aClassDetailInfo.getPrerequisite());
        tv_refund_info.setText(aClassDetailInfo.getRefundInfo());
        ArrayList<String> times = new ArrayList<String>();
        times.addAll(aClassSummaryInfo.getTimes());
        setTimeTextVies(times);

    }

    private void setTimeTextVies(ArrayList<String> times) {
        tv_start_time_1.setText(times.get(0));
        if(times.size() >  1)
        {
            tv_start_time_2.setText(times.get(1));
            if(times.size() > 2)
            {
                tv_start_time_3.setText(times.get(2));
            }
            else
            {
                tv_start_time_3.setVisibility(View.GONE);
            }
        }
        else
        {
            tv_start_time_2.setVisibility(View.GONE);
            tv_start_time_3.setVisibility(View.GONE);
        }
    }

    public static interface OnInquiryChooseListener
    {
        public void onInquiryChoose();
    }

    public static interface OnBookingChooseListener
    {
        public void onBookingChoose();
    }

}
