package com.hhly.lyygame.presentation.view.info;

import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.net.protocol.user.UpdateUserInfoReq;
import com.hhly.lyygame.presentation.view.BaseLoadingView;
import com.hhly.lyygame.presentation.view.BasePresenter;
import com.hhly.lyygame.presentation.view.BaseView;

/**
 * Created by ${HELY} on 17/2/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class InfoContract {

    interface Presenter extends BasePresenter {
        void updateUserInfo(int index, UpdateUserInfoReq.UpdateUserInfo updateUserInfo);

        void uploadImage(String imagePath);

        void keyWordFilter(int index, UpdateUserInfoReq.UpdateUserInfo updateUserInfo);
    }

    interface View extends BaseView<Presenter>, BaseLoadingView {
        void updateSuccess(int index, UserInfo userInfo);

        void updateFailure(String msg);

        void imageUrl(String imageUrl, String filePath);

        void imageUrlFailure(String filePath, String msg);

        void keyWordFilterSuccess(int index, UpdateUserInfoReq.UpdateUserInfo updateUserInfo);

        void keyWordFilterFailure(String msg);
    }

}
