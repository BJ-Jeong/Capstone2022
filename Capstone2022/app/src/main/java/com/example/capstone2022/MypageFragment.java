package com.example.capstone2022;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.capstone2022.api.ServerConnector;
import com.example.capstone2022.api.user.MemberData;
import com.example.capstone2022.util.LocalDateTimeUtil;

import org.jetbrains.annotations.Contract;

public class MypageFragment extends Fragment {

    private TextView finalVaccineDate;
    private TextView confirmation;
    private TextView vaccineInfo;
    private TextView quarantineReleaseDate;

    public MypageFragment() {

    }

    @NonNull
    @Contract(" -> new")
    public static MypageFragment newInstance() {
        return new MypageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        finalVaccineDate = view.findViewById(R.id.finalVaccineDate);
        confirmation = view.findViewById(R.id.confirmation);
        vaccineInfo = view.findViewById(R.id.vaccineInfo);
        quarantineReleaseDate = view.findViewById(R.id.quarantineReleaseDate);

        updateMember();
        view.invalidate();
        view.requestLayout();

        return view;
    }

    private void updateMember() {
        ServerConnector.GET("member", (jsonObject -> {
            MemberData data = MemberData.parseMember(jsonObject);

            finalVaccineDate.setText(LocalDateTimeUtil.getTimeString(data.getCoronaInfo().getFinalVaccineDate()));
            quarantineReleaseDate.setText(LocalDateTimeUtil.getTimeString(data.getCoronaInfo().getQuarantineReleaseDate()));
        }));
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
        this.finalVaccineDate = null;
        this.vaccineInfo = null;
        this.confirmation = null;
        this.quarantineReleaseDate = null;

        ServerConnector.cancelAll();
    }

}
