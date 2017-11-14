package com.bignerdranch.android.trialdiary;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by ujwalbajgain on 13/9/17.
 */

public class DiaryFragment extends Fragment {

    private static final String ARG_DIARY_ID = "diary_id";
    private static final int REQUEST_PHOTO = 2;
    private Diary mDiary;
    private EditText mTitleField;
    private Button mDateButton;
    private EditText mCommentField;
    private EditText mPlaceField;
    private Spinner mTypeField;
    private File mPhotoFile;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

    private ImageView mDeleteButton;
    private ImageView mSaveButton;
    private ImageView mCancelButton;


    public static DiaryFragment newInstance(UUID diaryId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DIARY_ID, diaryId);

        DiaryFragment fragment = new DiaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID diaryId = (UUID) getArguments().getSerializable(ARG_DIARY_ID);
        mDiary = DiaryLab.get(getActivity()).getDiary(diaryId);
        mPhotoFile = DiaryLab.get(getActivity()).getPhotoFile(mDiary);

    }
    @Override
    public void onPause(){
        super.onPause();

        DiaryLab.get(getActivity())
                .updateDiary(mDiary);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_diary, container, false);

        mTitleField = (EditText) v.findViewById(R.id.diary_title);
        mTitleField.setText(mDiary.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiary.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mPlaceField = (EditText) v.findViewById(R.id.diary_place);
        mPlaceField.setText(mDiary.getPlace());
        mPlaceField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiary.setPlace(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        PackageManager packageManager = getActivity().getPackageManager();


        mPhotoButton = (ImageButton) v.findViewById(R.id.diary_camera);
//        PackageManager packageManager = getActivity().getPackageManager();
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(),
                        "com.bignerdranch.android.trialdiary.FileProvider",
                        mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameraActivities = getActivity()
                        .getPackageManager().queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);


                for(ResolveInfo activity : cameraActivities){
                    getActivity().grantUriPermission(activity.activityInfo.packageName,
                            uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.diary_photo);
        updatePhotoView();




        mCommentField = (EditText) v.findViewById(R.id.diary_comments);
        mCommentField.setText(mDiary.getComments());
        mCommentField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(
                CharSequence s, int start, int count, int after){

        }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mDiary.setComments(s.toString());

        }

            @Override
            public void afterTextChanged(Editable editable) {

        }
        });
        mTypeField = (Spinner) v.findViewById(R.id.diary_type);
        if (mDiary.getType() != null)
            mTypeField.setSelection(Integer.parseInt(mDiary.getType()));

        mTypeField.setOnItemSelectedListener(new DiaryTypeOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                mDiary.setType(Integer.toString(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mCancelButton= (ImageView) v.findViewById(R.id.diary_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryLab.get(getActivity())
                        .deleteDiary(mDiary);
                getActivity().onBackPressed();
            }
        });

        mSaveButton = (ImageView) v.findViewById(R.id.diary_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryLab.get(getActivity())
                        .updateDiary(mDiary);
                getActivity().onBackPressed();
            }
        });



        /*Spinner mSpinner = (Spinner) findViewById(R.id.activity_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);
*/
        mDateButton = (Button) v.findViewById(R.id.diary_date);
        mDateButton.setText(mDiary.getDate().toString());
        mDateButton.setEnabled(false);

        return v;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    "com.bignerdranch.android.trialdiary.fileprovider",
                    mPhotoFile);

        getActivity().revokeUriPermission(uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        updatePhotoView();
        }
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}
