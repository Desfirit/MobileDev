package ru.mirea.sulokhin.mireaproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment {

    Button playButton;
    Button stopButton;

    public MusicFragment() {
        // Required empty public constructor
    }

    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        playButton = view.findViewById(R.id.playButton);
        stopButton = view.findViewById(R.id.stopButton);

        View.OnClickListener onPlayClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(
                        new Intent(getActivity(), PlayerService.class));
            }
        };

        View.OnClickListener onStopClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(
                        new Intent(getActivity(), PlayerService.class));
            }
        };

        playButton.setOnClickListener(onPlayClickListener);
        stopButton.setOnClickListener(onStopClickListener);
    }
}