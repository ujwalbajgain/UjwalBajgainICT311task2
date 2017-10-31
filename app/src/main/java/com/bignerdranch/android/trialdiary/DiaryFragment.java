package com.bignerdranch.android.trialdiary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by ujwalbajgain on 13/9/17.
 */

public class DiaryFragment extends Fragment {

    private static final String ARG_DIARY_ID = "diary_id";
    private Diary mDiary;
    private EditText mTitleField;
    private Button mDateButton;
    private EditText mCommentField;
    private EditText mPlaceField;
   ///private Spinner mSpinner;

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

    }
    @Override
    public void onPause(){
        super.onPause();

        DiaryLab.get(getActivity())
                .updateDiary(mDiary);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_diary, container, false);

        mTitleField = (EditText) v.findViewById(R.id.diary_title);
        mTitleField.setText(mDiary.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after){

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
        mPlaceField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after){

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiary.setPlace(s.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCommentField = (EditText) v.findViewById(R.id.diary_comment);
        mCommentField.setText(mDiary.getComment());
        mCommentField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(
                CharSequence s, int start, int count, int after){

        }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mDiary.setComment(s.toString());

        }

            @Override
            public void afterTextChanged(Editable editable) {

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
}
