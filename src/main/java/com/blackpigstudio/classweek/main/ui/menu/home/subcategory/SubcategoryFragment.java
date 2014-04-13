package com.blackpigstudio.classweek.main.ui.menu.home.subcategory;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.domain.Subcategory;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.homeui.AbstractHomeFragment;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.ClassSummaryInfoInventoryActivity;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class SubcategoryFragment extends AbstractHomeFragment implements ViewForSubcategoryFragment.OnSubCategoryChooseListener, HttpRequester.NetworkResponseListener{
    public static final String BUNDLE_PARM_OF_SPINNER_INDEX = "SPINNER_INDEX";
    public static final String BUNDLE_PARM_OF_URL = "URL";
    private ViewForSubcategoryFragment viewForSubcategoryFragment;
    private String urlToQuery;


    public SubcategoryFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        setSpinnerItemIndex(bundle.getInt(BUNDLE_PARM_OF_SPINNER_INDEX));
        urlToQuery = bundle.getString(BUNDLE_PARM_OF_URL);
        viewForSubcategoryFragment = new ViewForSubcategoryFragment(getActivity().getApplicationContext(),inflater,container,this);
        requestClassSummaryInfoFromServer(urlToQuery);
        return viewForSubcategoryFragment.getRoot();
    }





    private void requestClassSummaryInfoFromServer(String url)
    {
        HttpRequester.foo(this, url);
    }


    @Override
    public void onSubCategoryChoose(int index, String aTitle) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ClassSummaryInfoInventoryActivity.class);
        intent.putExtra(ClassSummaryInfoInventoryActivity.BUNDLE_PARM_OF_TITLE,aTitle);
        intent.putExtra(ClassSummaryInfoInventoryActivity.BUNDLE_PARM_OF_URL_KEY, urlToQuery+"/"+index);
        startActivity(intent);
    }

    /*
        temporal handler for dummy ClassSummaryInfos
     */
    Handler tmp = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            ArrayList<ViewForSubcategoryListViewItem.ISubcategory> subcategories = new ArrayList<ViewForSubcategoryListViewItem.ISubcategory>();
            for(int i = 1; i < 80; i++ )
                subcategories.add(new Subcategory());
            viewForSubcategoryFragment.addISubcategoryArrayList(subcategories);
        }
    };

    @Override
    public void onSuccess(JSONObject jsonObject) {
        tmp.sendEmptyMessage(0);
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {

    }
}
