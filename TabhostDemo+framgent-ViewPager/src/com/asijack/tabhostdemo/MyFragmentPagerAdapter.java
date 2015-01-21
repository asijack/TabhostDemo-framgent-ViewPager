package com.asijack.tabhostdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

/*
 * 
 * һ���ȸ�ٷ���Ϊ��ViewPagerӦ�ú�Fragmentһ��ʹ��ʱ����ʱViewPager����������FragmentPagerAdapter
 *    ����ʵ��һ��FragmentPagerAdapter,��������ٸ������·���:
	getCount()
	getItem()
        ���� ���ViewPagerû�к�Fragmentһ��ViewPager����������PagerAdapter��
               ���ǻ����ṩ�����������ҳ��ViewPager�ڲ�������ʵ��һ��PagerAdapter,��������ٸ������·���:
    instantiateItem(ViewGroup, int)
    destroyItem(ViewGroup, int, Object)
    getCount()
    isViewFromObject(View, Object)


 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> list;
	public MyFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
		super(fm);
		this.list=list;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}
	
}
