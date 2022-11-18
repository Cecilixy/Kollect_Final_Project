package com.example.kollect_final_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddFavoriteDialog extends AppCompatDialogFragment {
    private EditText editgroup;
    private Switch addwhat;
    private AddFavoriteDialogListener addFavoriteDialogListener;
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.favorite_group_dialog, null);

        builder.setView(view).setTitle("enter your favorite").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            String groupname = editgroup.getText().toString();
                        addFavoriteDialogListener.applyTexts(groupname,addwhat);
                    }
                });
        editgroup = view.findViewById(R.id.editgroup);
        addwhat = view.findViewById(R.id.switch1);
        return builder.create();

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            addFavoriteDialogListener = (AddFavoriteDialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+
                    "must implement AddGroupDialogListener");
        }
    }
    public interface AddFavoriteDialogListener{
        void applyTexts(String groupname, Switch what);
    }

}