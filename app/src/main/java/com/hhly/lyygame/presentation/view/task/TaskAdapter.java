package com.hhly.lyygame.presentation.view.task;

import android.content.res.Resources;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.lyygame.R;

import java.util.ArrayList;

/**
 * Created by Simon on 2016/11/29.
 */

public class TaskAdapter extends BaseQuickAdapter<Task, BaseViewHolder> {

    public TaskAdapter() {
        super(R.layout.fragment_task_item_layout, new ArrayList<Task>());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Task task) {
        baseViewHolder.addOnClickListener(R.id.task_action_btn);
        baseViewHolder.setText(R.id.task_name_tv, task.getName());
        baseViewHolder.setText(R.id.task_description_tv, task.getDescription());
        baseViewHolder.setText(R.id.task_score_tv, String.valueOf(task.getScore()));
        if (task.isFinished()) {
            baseViewHolder.setVisible(R.id.task_action_btn, false);
            baseViewHolder.setVisible(R.id.task_finish_iv, true);
        } else {
            baseViewHolder.setVisible(R.id.task_finish_iv, false);
            baseViewHolder.setVisible(R.id.task_action_btn, true);
            Resources resources = baseViewHolder.getConvertView().getResources();
            switch (task.getType()) {
                case Task.TYPE_TASK:
                    baseViewHolder.setText(R.id.task_action_btn, R.string.lyy_task_action_task);
                    baseViewHolder.setTextColor(R.id.task_action_btn, resources.getColor(R.color.color_e7384a));
                    baseViewHolder.setBackgroundRes(R.id.task_action_btn, R.drawable.button_task_item_sign_selector);
                    break;
                case Task.TYPE_SIGN_IN:
                    baseViewHolder.setText(R.id.task_action_btn, R.string.lyy_task_action_sign_in);
                    baseViewHolder.setTextColor(R.id.task_action_btn, resources.getColor(R.color.color_e7384a));
                    baseViewHolder.setBackgroundRes(R.id.task_action_btn, R.drawable.button_task_item_sign_selector);
                    break;
                case Task.TYPE_SCORE:
                    baseViewHolder.setText(R.id.task_action_btn, R.string.lyy_task_action_score);
                    baseViewHolder.setTextColor(R.id.task_action_btn, resources.getColor(R.color.color_fff));
                    baseViewHolder.setBackgroundRes(R.id.task_action_btn, R.drawable.button_task_item_score_selector);
                    break;
            }
        }
    }
}
