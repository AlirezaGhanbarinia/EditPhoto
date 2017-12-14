package com.hoanganhtuan95ptit.editphoto.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.hoanganhtuan95ptit.permission.Permission;

/**
 * Created by Hoang Anh Tuan on 3/10/2017.
 */

abstract class BaseActivity extends FragmentActivity implements Permission.OnPermissionFeedbackListener {

    private Permission permission;
    public boolean runnable = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runnable = true;
        permission = Permission.Builder.with(this)
                .setOnPermissionFeedbackListener(this)
                .build();
    }

    @Override
    protected void onDestroy() {
        runnable = false;
        super.onDestroy();
    }

    /**
     * show notification
     *
     * @param message infor show
     */
    protected void showMessage(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_LONG).show();
    }

    /**
     * ask permission android
     *
     * @param requestCode code
     * @param permissions list permission
     */
    protected void askPermissions(int requestCode, String... permissions) {
        permission.askPermissions(requestCode, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permission.onRequestPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void accept(int requestCode) {
    }

    @Override
    public void reject(int requestCode) {
    }

}
