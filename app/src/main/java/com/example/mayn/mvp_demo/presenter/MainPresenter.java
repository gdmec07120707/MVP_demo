package com.example.mayn.mvp_demo.presenter;

import com.example.mayn.mvp_demo.model.User;
import com.example.mayn.mvp_demo.view.BaseView;

/**
 * Created by FANGDINGJIE
 * 2018/3/14.
 */

public interface MainPresenter {

    void attachView(BaseView v);

    void detachView();

    void login(User user);


}
