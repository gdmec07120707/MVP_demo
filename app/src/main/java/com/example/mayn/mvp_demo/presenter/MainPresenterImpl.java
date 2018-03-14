package com.example.mayn.mvp_demo.presenter;

import com.example.mayn.mvp_demo.model.User;
import com.example.mayn.mvp_demo.view.BaseView;

/**
 * Created by FANGDINGJIE
 * 2018/3/14.
 */

public class MainPresenterImpl implements MainPresenter {
    private BaseView mBaseView;

    @Override
    public void attachView(BaseView v) {
        this.mBaseView = v;
    }

    @Override
    public void detachView() {
        this.mBaseView = null;
    }

    @Override
    public void login(User user) {
        if(user!=null){
            if(user.getUsername().equals("fff")&&user.getPassword().equals("111111")){
                mBaseView.loginSuccess();
            }else{
                mBaseView.loginFailue();
            }
        }

    }
}
