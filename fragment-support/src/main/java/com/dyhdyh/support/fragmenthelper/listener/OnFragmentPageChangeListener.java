package com.dyhdyh.support.fragmenthelper.listener;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dyhdyh.support.fragmenthelper.FragmentLifecycle;

/**
 * @author dengyuhan
 *         created 2018/7/13 15:46
 */
public class OnFragmentPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
    private FragmentPagerAdapter mAdapter;
    private int mPosition = -1;

    public OnFragmentPageChangeListener(FragmentPagerAdapter adapter, int currentItem) {
        this.mAdapter = adapter;
        this.mPosition = currentItem;
    }

    @Override
    public void onPageSelected(int position) {
        try {
            if (this.mPosition >= 0) {
                Fragment item = mAdapter.getItem(this.mPosition);
                if (item instanceof FragmentLifecycle) {
                    ((FragmentLifecycle) item).onPauseShow();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        this.mPosition = position;

        try {
            Fragment item = mAdapter.getItem(this.mPosition);
            if (item instanceof FragmentLifecycle) {
                ((FragmentLifecycle) item).onResumeShow();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
