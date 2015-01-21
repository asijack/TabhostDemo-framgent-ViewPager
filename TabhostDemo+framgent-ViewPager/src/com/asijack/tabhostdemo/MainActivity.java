package com.asijack.tabhostdemo;
import java.util.ArrayList;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity{
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private ImageView image;
	private int currIndex;//��ǰҳ������
	private int bmpW;//����ͼƬ���
	private int offset;//ͼƬ�ƶ���ƫ����
	//��Ҫ�����ĵ�ҳ��
	private TextView view1,view2,view3,view4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ��TextView
		InitTextView();
		//��ʼ���α꣨ͼƬ��
		InitImage();
		//��ʼ��ViewPager
		InitViewPager();
	}
	public void InitTextView(){
		//ÿ��fragment(view) ��Ӧ�� textview
		view1=(TextView) findViewById(R.id.tv_guid1);
		view2=(TextView) findViewById(R.id.tv_guid2);
		view3=(TextView) findViewById(R.id.tv_guid3);
		view4=(TextView) findViewById(R.id.tv_guid4);
		//���ü���
		view1.setOnClickListener(new TxListener(0));
		view2.setOnClickListener(new TxListener(1));
		view3.setOnClickListener(new TxListener(2));
		view4.setOnClickListener(new TxListener(3));
		
	}
	public class TxListener implements View.OnClickListener{
		private int index=0;
		
		public TxListener (int i){
			index=i;
		}
		@Override
		public void onClick(View view) {
			//�޸�ViewPager����setCurrentItemʱ���������ٶ�
			mPager.setCurrentItem(index);
		}
		
	}
	//��ʼ��ͼƬ��λ������
	public 	void InitImage(){
		image=(ImageView) findViewById(R.id.cursor);
		//ֻ�м�����Bitmap�����ʱ����ܶ�ͼƬ���в鿴�޸�,decodeResource��ȡ��Bitmap����
		bmpW=BitmapFactory.decodeResource(getResources(), R.drawable.cours).getWidth();
//		Android ������Ϊ���Ŵ��ڴ�С�������ű�������������ˣ��ֻ����������Ա���Ǳ���֪���ֻ���Ļ�ı߽磬�Ա���������ɵĲ��ֱ������⡣
//		�ֻ��ķֱ�����Ϣ���ֻ���һ����Ҫ��Ϣ���ܺõ��ǣ�Android �Ѿ��ṩDisplayMetircs ����Ժܷ���Ļ�ȡ�ֱ��ʡ�
//		getWindowManager().getDefaultDisplay().getMetrics;
//		���캯��DisplayMetrics ����Ҫ�����κβ���������getWindowManager() ֮�󣬻�ȡ������Activity ��Handle 
//      ��ʱ��getDefaultDisplay() ������ȡ�õĿ��ά�ȴ����DisplayMetrics �����У���ȡ�õĿ��ά����������Ϊ��λ(Pixel) 
//      �����ء���ָ���ǡ��������ء����ǡ�������ء���
		DisplayMetrics dm=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//��ȡ�豸�Ŀ��
		int screenW=dm.widthPixels;
		offset=(screenW/4 -bmpW)/2;//4��item��ƫ����
//		offset = (screenW-2*bmpW)/4;//2 ��item ��ƫ����
		
		//imageView����ƽ�ƣ�ʹ�»���ƽ�Ƶ���ʼλ��(ƽ��һ��offset)
		Matrix matrix=new Matrix();
		//  Matrix�Ĳ������ܹ���Ϊtranslate��ƽ�ƣ���rotate����ת����scale�����ţ���skew����б������
		//ÿһ�ֱ任��Android��API�ﶼ�ṩ��set, post��pre���ֲ�����ʽ,����translate���������ֲ���������ָ�����ĵ㡣
		//  set��ֱ������Matrix��ֵ��ÿ��setһ�Σ�����Matrix�����鶼������
		//post�Ǻ�ˣ���ǰ�ľ�����Բ��������ľ��󡣿����������ʹ��post�����������������任��
		matrix.postTranslate(offset, 0);
		image.setImageMatrix(matrix);
	}
	//��ʼ��ViewPager
	public void InitViewPager(){
		mPager=(ViewPager) findViewById(R.id.viewpager);
		fragmentList=new ArrayList<Fragment>();
		Fragment firstFragment=FragmentFactory.newInstance("this is first fragment");
		Fragment secondFragment=FragmentFactory.newInstance("this is second fragment");
		Fragment thirdFragment=FragmentFactory.newInstance("this is third fragment");
		Fragment fourthFragment=FragmentFactory.newInstance("this is fourth fragment");
		fragmentList.add(firstFragment);
		fragmentList.add(secondFragment);
		fragmentList.add(thirdFragment);
		fragmentList.add(fourthFragment);
		//��ViewPager����������
		mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
		//���õ�ǰ��ʾ�ı�ǩΪ��һҳ
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChanceListener());
		
	}
	
	//viewҳ�滬���ļ���
	public class MyOnPageChanceListener implements OnPageChangeListener{
		
		//�˷�������״̬�ı��ʱ����ã�arg0���� ������״̬0,1,2 ==1ʱ��ʾ���ڻ�����==2ʱ��ʾ������ϣ�==0ʱ��ʾʲô��û��
		//��ҳ�濪ʼ������ʱ������״̬�ı仯˳��Ϊ��1��2��0��
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
		//��ҳ�滬����ʱ�����ô˷������ڻ���ֹ֮ͣǰ���˷�����һֱ�õ�����
		//arg0 ��ǰҳ�棬������������ҳ��  arg1 ��ǰҳ����˵İٷֱ�  arg2 ��ǰҳ��ƫ�Ƶ�����λ��
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		//�˷�����ҳ����ת��� �õ����� arg0 ���㵱ǰѡ�е�ҳ���position��λ�ñ�ţ�
		@Override
		public void onPageSelected(int arg0) {
			int one=offset*2+bmpW;//��������ҳ���ƫ���� 
			Animation animation=new TranslateAnimation(currIndex*one, arg0*one,0,0);//ƽ�ƶ���
			currIndex=arg0;
			animation.setFillAfter(true);//������ֹʱͣ�������һ֡����Ȼ��ص�û��ִ��ǰ��״̬
			animation.setDuration(200);//��������ʱ��0.2�� 
			image.startAnimation(animation);
			int i=currIndex +1;
			Toast.makeText(MainActivity.this, "��ѡ���˵�"+i+"��ҳ��", Toast.LENGTH_SHORT).show();
			
		}
	}
}


















