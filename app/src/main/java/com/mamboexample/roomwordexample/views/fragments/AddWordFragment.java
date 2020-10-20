package com.mamboexample.roomwordexample.views.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mamboexample.roomwordexample.R;
import com.mamboexample.roomwordexample.databinding.FragmentAddWordBinding;
import com.mamboexample.roomwordexample.entities.Word;
import com.mamboexample.roomwordexample.viewmodel.WordViewModel;

public class AddWordFragment extends Fragment {

    private FragmentAddWordBinding binding;
    private NavController navController;
    private WordViewModel mWordViewModel;

    public AddWordFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        binding.buttonAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWord();
            }
        });

        binding.edtAddWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputWord.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.inputWord.setError(null);
            }
        });

    }

    private void saveWord() {
        if (TextUtils.isEmpty(binding.edtAddWord.getText().toString())) {
            binding.inputWord.setError("enter word");
            return;
        }

        mWordViewModel.insert(new Word(binding.edtAddWord.getText().toString()));

        navController.popBackStack();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWordViewModel = new ViewModelProvider(requireActivity()).get(WordViewModel.class);
    }
}