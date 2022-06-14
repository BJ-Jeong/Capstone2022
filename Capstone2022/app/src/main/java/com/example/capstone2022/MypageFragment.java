package com.example.capstone2022;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.capstone2022.api.ServerConnector;
import com.example.capstone2022.api.user.MemberData;
import com.example.capstone2022.service.UUIDService;
import com.example.capstone2022.util.LocalDateTimeUtil;

import org.jetbrains.annotations.Contract;

import java.io.File;
import java.util.Objects;

import lombok.val;

public class MypageFragment extends Fragment {

    TextView tv_vaccineCount, tv_finalVaccineDate, tv_quarantineReleaseDate, tv_confirmation, tv_hname_output, tv_hnumber_output;

    public MypageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, Bundle savedInstanceState) {
        Log.d(MypageFragment.class.getSimpleName(), "onCreateView called");
        Log.d(MypageFragment.class.getSimpleName(), "entry count: " + getActivity().getFragmentManager().getBackStackEntryCount());

        container.removeAllViews();

        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        tv_vaccineCount = view.findViewById(R.id.tv_vaccineCount);
        tv_finalVaccineDate = view.findViewById(R.id.tv_finalVaccineDate);
        tv_quarantineReleaseDate = view.findViewById(R.id.tv_quarantineReleaseDate);
        tv_confirmation = view.findViewById(R.id.tv_confirmation);
        tv_hname_output = view.findViewById(R.id.tv_hname_output);
        tv_hnumber_output = view.findViewById(R.id.tv_hnumber_output);

        updateMember(view);
        Log.d(MypageFragment.class.getSimpleName(), "view: " + view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(MypageFragment.class.getSimpleName(), "onResume called");

    }

    private void updateMember(@NonNull View view) {
        ServerConnector.GET("rest/member/" + new UUIDService(getContext()).getUUID(), (jsonObject -> {
            MemberData data = MemberData.parseMember(jsonObject);

            tv_confirmation.setText(data.getConfirmDate() != null ? "확진" : "미확진");

            if (data.getVaccineCount() != null)
                tv_vaccineCount.setText(String.valueOf(data.getVaccineCount()));
            if (data.getVaccineDate() != null)
                tv_finalVaccineDate.setText(data.getVaccineDate().toString());
            if (data.getQuarantineReleaseDate() != null)
                tv_quarantineReleaseDate.setText(data.getQuarantineReleaseDate().toString());
            if (data.getHospitalName() != null)
                tv_hname_output.setText(data.getHospitalName());
            if (data.getHospitalContact() != null)
                tv_hnumber_output.setText(data.getHospitalContact());
        }));

        view.invalidate();
        view.requestLayout();
    }

    @Override
    public void onDestroy() {
        destroy();

        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        destroy();

        super.onDestroyView();
    }

    private void destroy() {
        try {
            trimCache(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            Log.e(MypageFragment.class.getSimpleName(), "Error on trimCache", e);
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < Objects.requireNonNull(children).length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        assert dir != null;
        return dir.delete();
    }
}
