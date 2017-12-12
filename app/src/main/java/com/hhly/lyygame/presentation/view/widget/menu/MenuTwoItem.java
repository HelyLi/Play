package com.hhly.lyygame.presentation.view.widget.menu;

/**
 * Created by Simon on 2016/11/23.
 */

public class MenuTwoItem {

    private MenuItem fisrtItem;
    private MenuItem secondItem;

    public MenuTwoItem() {
    }

    public MenuTwoItem(MenuItem fisrtItem, MenuItem secondItem) {
        this.fisrtItem = fisrtItem;
        this.secondItem = secondItem;
    }

    public MenuItem getFisrtItem() {
        return fisrtItem;
    }

    public void setFisrtItem(MenuItem fisrtItem) {
        this.fisrtItem = fisrtItem;
    }

    public MenuItem getSecondItem() {
        return secondItem;
    }

    public void setSecondItem(MenuItem secondItem) {
        this.secondItem = secondItem;
    }
}
