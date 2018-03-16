package com.webber.myframeapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.widget.RadioGroup;

import com.webber.myframeapp.R;
import com.webber.myframeapp.base.BaseActivity;
import com.webber.myframeapp.contact.MainContact;
import com.webber.myframeapp.presenter.MainPresenter;
import com.webber.myframeapp.ui.fragment.CardFragment;
import com.webber.myframeapp.ui.fragment.DiscountFragment;
import com.webber.myframeapp.ui.fragment.HomeFragment;
import com.webber.myframeapp.ui.fragment.MyFragment;
import com.webber.myframeapp.widget.CustomRadioGroup;
import com.webber.myframeapp.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContact.View, CustomRadioGroup.ItemSelectChangeListener {


    @BindView(R.id.vp_main)
    MyViewPager vpMain;
    @BindView(R.id.main_radio)
    CustomRadioGroup mainRadio;

    private List<Fragment> pageList;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainRadio.setItemSelectChangeListener(this);
        initData();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        pageList = new ArrayList<Fragment>();
        pageList.add(new HomeFragment());
        pageList.add(new DiscountFragment());
        pageList.add(new CardFragment());
        pageList.add(new MyFragment());
        mAdapter = new MyAdapter(getSupportFragmentManager());
        vpMain.setAdapter(mAdapter);
        setSelect(0);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    public void showProgress(String apkUrl, String destinationFilePath) {

    }

    @Override
    public void showChooseDialog(String newVersionName, String versionInfo, String isForceUpadate, String updateUrl) {

    }

    @Override
    public void showToast(String msg) {
    }


    /**
     * 选中之后的操作
     *
     * @param i
     */
    private void setSelect(int i) {
        //切换内容区域
        vpMain.setCurrentItem(i);
    }

    @Override
    public void onSelectChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_home:
                setSelect(0);
                break;
            case R.id.radio_discount:
                setSelect(1);
                break;
            case R.id.radio_card:
                setSelect(2);
                break;
            case R.id.radio_my:
                setSelect(3);
                break;
        }

    }


    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pageList.get(position);
        }

        @Override
        public int getCount() {
            return pageList.size();
        }
    }


    /***
     *
     *         JSONObject jsonObject = new JSONObject();
     try {
     jsonObject.put("telecode", "18511883771");
     jsonObject.put("password", "pppppp0");
     } catch (JSONException e) {
     e.printStackTrace();
     }
     String paras = jsonObject.toString();
     paras = Base64.encodeToString(paras.getBytes(), Base64.DEFAULT);
     Map<String, String> map = new HashMap<String, String>();
     map.put("param_json",paras);
     RetrofitManager.getApiService().login(map).subscribeOn(Schedulers.io())
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe(new DefaultObserver<BaseResponse>(this) {
    @Override public void onSuccess(BaseResponse response) {
    LogUtil.i("login:BaseResponse-----------------");
    }
    });
     *
     ***/


}
