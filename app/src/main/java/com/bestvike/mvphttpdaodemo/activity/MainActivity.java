package com.bestvike.mvphttpdaodemo.activity;

import android.os.Bundle;
import android.widget.Button;

import com.bestvike.mvphttpdaodemo.R;
import com.bestvike.mvphttpdaodemo.base.BaseActivity;
import com.bestvike.mvphttpdaodemo.iview.GetDictView;
import com.bestvike.mvphttpdaodemo.presenter.GetDictPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements GetDictView {

    @InjectView(R.id.button)
    Button button;

    private GetDictPresenter getDictPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
        getHeaderView();
        setTitle("首页");
        getDictPresenter = new GetDictPresenter(this);

    }

    @Override
    protected void destroyPresenter() {
        getDictPresenter.onDettached();
    }


    @OnClick(R.id.button)
    public void onViewClicked() {
        showProgress(true);
        getDictPresenter.get();
    }

    /**接口查询成功调用*/
    @Override
    public void getCict() {
        showProgress(false);
    }
}
