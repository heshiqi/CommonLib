package com.android.common.widget;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.android.common.R;
import com.android.common.core.data.Photo;
import com.android.common.helper.PhotoSelectionHelper;

import uk.co.senab.photoview.PhotoView;


public class PhotoBrowseItemLayout extends FrameLayout
        implements  View.OnClickListener{

    static final String LOG_TAG = "PhotoBrowseItemLayout";

    private final PhotoView mImageView;
    private final CheckableImageView mButton;

    private int mPosition;
    private final Photo mPhoto;
    private final PhotoSelectionHelper mPhotoSelectionHelper;

    public PhotoBrowseItemLayout(Context context,Photo photo) {
        super(context);

        mPhotoSelectionHelper = PhotoSelectionHelper.getInstance();
        LayoutInflater.from(context).inflate(R.layout.item_viewpager_photo_internal, this);
        mImageView = (PhotoView) findViewById(R.id.iv_photo);
        mButton = (CheckableImageView) findViewById(R.id.civ_button);
        mButton.setOnClickListener(this);
        if (null != photo) {
            mButton.setChecked(mPhotoSelectionHelper.isSelected(photo));
        }
        mPhoto = photo;
    }

    public PhotoView getImageView() {
        return mImageView;
    }

    public Photo getPhotoSelection() {
        return mPhoto;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.civ_button:
            	if(!mButton.isChecked()&&mPhotoSelectionHelper.isCompleteSelection())//�Ѿ�ѡ����ָ��������
    				return;
                mButton.toggle();
                updateController();
                break;
        }
    }


    void updateController() {
        if (mButton.isChecked()) {
            mPhotoSelectionHelper.addSelection(mPhoto);
        } else {
            mPhotoSelectionHelper.removeSelection(mPhoto);
        }
    }
}
