package de.chrisward.locationapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import de.chrisward.locationapp.MainActivity;
import de.chrisward.locationapp.R;

public class RequestPermissionFragment extends Fragment {
    TextView textView;
    Button btnGrantPermission;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_request_permission, container, false);

        textView = result.findViewById(R.id.permissionStatus);
        btnGrantPermission = result.findViewById(R.id.btnGrantPermission);

        btnGrantPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() == null) {
                    return;
                }

                ((MainActivity) getActivity()).getLocationPermission();
            }
        });

        return result;
    }
}
