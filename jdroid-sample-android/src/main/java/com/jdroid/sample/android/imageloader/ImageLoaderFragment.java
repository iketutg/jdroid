package com.jdroid.sample.android.imageloader;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jdroid.android.fragment.AbstractFragment;
import com.jdroid.android.utils.ImageLoaderUtils;
import com.jdroid.sample.android.R;

public class ImageLoaderFragment extends AbstractFragment {
	
	@Override
	public Integer getContentFragmentLayout() {
		return R.layout.imageloader_fragment;
	}
	
	/**
	 * @see com.jdroid.android.fragment.AbstractFragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		ImageView imageView = findView(R.id.image);
		ImageLoaderUtils.displayImage("http://jdroidframework.com/images/mainImage.png", imageView,
			R.drawable.ic_launcher);
		
	}
}