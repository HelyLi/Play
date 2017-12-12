package com.hhly.lyygame.presentation.view.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hhly.lyygame.App;
import com.hhly.lyygame.R;
import com.hhly.lyygame.data.db.entry.UserInfo;
import com.hhly.lyygame.data.db.manager.AccountManager;
import com.hhly.lyygame.presentation.utils.NetworkUtil;
import com.hhly.lyygame.presentation.view.BaseFragment;
import com.hhly.lyygame.presentation.view.DialogFactory;
import com.hhly.lyygame.presentation.view.immersive.IImmersiveApply;
import com.hhly.lyygame.presentation.view.info.InfoActivity;
import com.hhly.lyygame.presentation.view.score.ScoreRecordActivity;
import com.hhly.lyygame.presentation.view.share.ShareContent;
import com.hhly.lyygame.presentation.view.share.ShareDialog;
import com.hhly.lyygame.presentation.view.signin.SignInActivity;
import com.hhly.lyygame.presentation.view.widget.RecyclerViewListDivide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import com.orhanobut.logger.Logger;

/**
 * Created by Simon on 2016/11/29.
 */

public class TaskFragment extends BaseFragment implements IImmersiveApply, SwipeRefreshLayout.OnRefreshListener, TaskContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.refreshLayout)
    //    SwipeRefreshLayout mRefreshLayout;

    TextView mTvTeskCount;

    private TaskAdapter mTaskAdapter;
    private TaskContract.Presenter mPresenter;
    private Unbinder mBinder;

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskAdapter = new TaskAdapter();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getActivity(), R.color.color_f5f5f5, RecyclerViewListDivide.VERTICAL_LIST, 1));

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_task_header_layout, null, false);
        mTvTeskCount = (TextView) headerView.findViewById(R.id.task_header_score_tv);
        mTvTeskCount.setText(String.format(getString(R.string.lyy_game_task_count), 0));
        mTaskAdapter.addHeaderView(headerView);

        mRecyclerView.setAdapter(mTaskAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Task task = (Task) baseQuickAdapter.getItem(i);
                if (task.isFinished())
                    return;
                switch (view.getId()) {
                    case R.id.task_action_btn:
                        callToTaskAction(i);
                        break;
                }
            }
        });

        postDelay(new Runnable() {
            @Override
            public void run() {
                fetchData(false);
            }
        }, 500);

        try {
            mTaskList.add(new Task(Task.TYPE_TASK, true, "注册奖励", "完成注册即可获得", AccountManager.getInstance().getUserInfo().getRegType() == 4 ? 100 : 200, 0));
            mTaskList.add(new Task(Task.TYPE_SCORE, false, "邀请好友注册", "邀请好友完成手机号注册即可", 1200, 3));
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    private void callToTaskAction(int index) {
        if (mRefreshLayout.isRefreshing()) {
            return;
        }
        switch (index) {
            case 1://每日签到
                ActivityCompat.startActivityForResult(getActivity(), SignInActivity.getCallingIntent(getActivity()), TaskActivity.TASK_REQUESTCODE, null);
                break;
            case 2://完善个人资料
                ActivityCompat.startActivityForResult(getActivity(), InfoActivity.getCallingIntent(getActivity()), TaskActivity.TASK_REQUESTCODE, null);
                break;
            case 3://邀请好友注册，分享

                ShareContent shareContent = new ShareContent();
                shareContent.setAppName(getString(R.string.app_name));
                shareContent.setImage("http://public.13322.com/23c192a0.png");
                shareContent.setLink("http://m.game.13322.com");
                shareContent.setInviteCode(mInviteCode);
                shareContent.setContent(getString(R.string.lyy_third_invite, mInviteCode));
                shareContent.setDescription(getString(R.string.lyy_third_invite, mInviteCode));
                shareContent.setTitle(getString(R.string.lyy_wyx));

                DialogFactory.createShare(getActivity(), ShareDialog.TYPE_INVITE, shareContent).show();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.task_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.task_score_detail_menu) {
            ActivityCompat.startActivity(getActivity(), ScoreRecordActivity.getCallingIntent(getActivity()), null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean enableSetActionBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task_layout;
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1.0f;
    }

    @Override
    public void onRefresh() {
        if (!NetworkUtil.isAvailable(App.getContext())) {
            mRefreshLayout.setRefreshing(false);
            return;
        }
        mTaskList.clear();
        try {
            mTaskList.add(new Task(Task.TYPE_TASK, true, "注册奖励", "完成注册即可获得", AccountManager.getInstance().getUserInfo().getRegType() == 4 ? 100 : 200, 0));
            mTaskList.add(new Task(Task.TYPE_SCORE, false, "邀请好友注册", "邀请好友完成手机号注册即可", 1200, 3));
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean loadMore) {
        mPresenter.getUserInfo();
        mPresenter.queryMonthSign();
        mPresenter.getInviteCode();
    }

    private List<Task> mTaskList = new ArrayList<>();

    @Override
    public void showUserInfo(UserInfo userInfo) {
        mRefreshLayout.setRefreshing(false);
        //(userInfo.getSex() == null || userInfo.getSex() != 1 || userInfo.getSex() != 2 || userInfo.getSex() != 3)
        //finish == true 表示资料未完善
        boolean finish = TextUtils.isEmpty(userInfo.getQq())
                || TextUtils.isEmpty(userInfo.getNickname())
                || TextUtils.isEmpty(userInfo.getRealName())
                || TextUtils.isEmpty(userInfo.getLocation())
                || TextUtils.isEmpty(userInfo.getAddress());

        if (mTaskList.size() == 2) {
            mTaskList.add(1, new Task(Task.TYPE_SCORE, !finish, "完善个人资料", "个人资料信息填写完成保存即可", 200, 2));
        } else if (mTaskList.size() == 3) {
            mTaskList.add(2, new Task(Task.TYPE_SCORE, !finish, "完善个人资料", "个人资料信息填写完成保存即可", 200, 2));
        }
        mTaskAdapter.setNewData(mTaskList);
        mTvTeskCount.setText(String.format(getString(R.string.lyy_game_task_count), Long.parseLong(userInfo.getJf())));
    }

    @Override
    public void showMonthSign(boolean sign) {
        mRefreshLayout.setRefreshing(false);
        Task task = new Task(Task.TYPE_SIGN_IN, sign, "每日签到", "点击签到即可，连续签到更多惊喜！", 50, 1);
        if (mTaskList.size() >= 2 && !mTaskList.get(1).getName().equalsIgnoreCase(task.getName())) {
            mTaskList.add(1, task);
        }
        mTaskAdapter.setNewData(mTaskList);
    }

    private String mInviteCode;

    @Override
    public void showInviteCode(String inviteCode) {
        mInviteCode = inviteCode;
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(true);
        }
        onRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }
}
