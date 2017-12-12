package com.hhly.lyygame.presentation.permission;

import java.util.List;

/**
 * Created by ${HELY} on 16/12/28.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface PermissionListener {

    void granted();

    void onDenied(List<String> deniedPermission);
}
