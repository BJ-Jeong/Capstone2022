package com.example.capstone2022;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    ArrayList<ContactModel> arrayList = new ArrayList<>();
    MainAdapter adapter;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);

        checkPermission();

        return v;
    }

    private void checkPermission() {
        // 사용자에게 권한을 허가받았는지 확인하는 함수.
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //연락처 접근 권한이 설정되어있지 않을 경우
            //자동으로 연락처 접근 권한을 요청함.
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.READ_CONTACTS}, 100);
        } else {
            // 권한이 이미 설정되어있을 경우
            // 메서드 호출.
            getContactList();
        }
    }

    private void getContactList() {
        // uri 정의
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        // ASC로 정렬
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        // 커서 정의
        Cursor cursor = getActivity().getContentResolver().query(
                uri, null, null, null, sort
        );
        //상태 체크
        if (cursor.getCount() > 0) {
            //카운트가 0보다 클때 while 반복문 실행
            while (cursor.moveToNext()) {
                //커서를 다음으로 움직이고
                //연락처 ID를 받아온다
                int id1 = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                String id = cursor.getString(id1);
                //연락처 이름을 받아온다
                int name1 = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(name1);
                //기기 uri 정의
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                //셀렉션 정의
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
                //phoneCursor 정의
                Cursor phoneCursor = getActivity().getContentResolver().query
                        (uriPhone, null, selection, new String[]{id}, null);
                //상태 체크
                if (phoneCursor.moveToNext()) {
                    //폰커서가 다음으로 움직였을 때
                    int number1 = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = phoneCursor.getString(number1);
                    //콘택트 모델 정의
                    ContactModel model = new ContactModel();
                    //이름, 번호를 받아온다
                    model.setName(name);
                    model.setNumber(number);
                    //모델을 리스트에 추가한다
                    arrayList.add(model);
                    //폰 커서 닫기.
                    phoneCursor.close();
                }

            }
            //커서 닫기.
            cursor.close();
        }
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lim);

        adapter = new MainAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //상태 체크
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {
            //권한 설정을 받았을 때 메서드 호출
            getContactList();
        } else {
            //권한 설정이 거부되었을때 Toast 표시
            Toast.makeText(getContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
            //권한 체크 메서드 호출
            checkPermission();
        }
    }
}
