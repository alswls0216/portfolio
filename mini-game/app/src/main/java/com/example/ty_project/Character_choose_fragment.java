package com.example.ty_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Character_choose_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Character_choose_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Button back;

    Character_choose_model A = new Character_choose_model(1, "A", "a");
    Character_choose_model B = new Character_choose_model(2, "B", "a");
    Character_choose_model C = new Character_choose_model(3, "c", "a");
    Character_choose_model D = new Character_choose_model(4, "F", "a");
    Character_choose_model E = new Character_choose_model(5, "D", "a");
    Character_choose_model F = new Character_choose_model(6, "E", "a");

    public Character_choose_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_character_choose, container, false);

        back = view.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent _intent = new Intent( getActivity(), Main_activity2.class);
                startActivity(_intent);
            }
        });

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(
                        2,
                        StaggeredGridLayoutManager.VERTICAL);

        ArrayList<Character_choose_model> list = new ArrayList<>();

        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        list.add(E);
        list.add(F);

        RecyclerView rv = view.findViewById((R.id.rv_courses));
        rv.setLayoutManager(layoutManager);

        Character_choose_adapter adapter = new Character_choose_adapter(list);
        rv.setAdapter(adapter);

        return view;
    }


}