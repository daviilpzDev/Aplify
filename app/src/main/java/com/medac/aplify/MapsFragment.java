package com.medac.aplify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng medac = new LatLng(37.40652098410911, -5.933073360805121);
            googleMap.addMarker(new MarkerOptions().position(medac).title("Medac Sevilla"));

            LatLng micasa = new LatLng(37.40154165137732, -5.920958435660851);
            googleMap.addMarker(new MarkerOptions().position(micasa).title("Mi casa"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(micasa));

            LatLng miotracasa = new LatLng(37.22068932013324, -5.807636074306178);
            googleMap.addMarker(new MarkerOptions().position(miotracasa).title("Mi segunda casa"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(miotracasa));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}