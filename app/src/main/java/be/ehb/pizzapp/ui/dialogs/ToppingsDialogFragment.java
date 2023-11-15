package be.ehb.pizzapp.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;

import be.ehb.pizzapp.R;
import be.ehb.pizzapp.model.PizzaViewModel;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class ToppingsDialogFragment extends DialogFragment {

    private FragmentActivity myContext;
    private String[] toppings;
    private boolean[] checkedIndexes;
    private ArrayList<String> selectedItems;

    private DialogInterface.OnClickListener confirmListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            PizzaViewModel model = new ViewModelProvider(myContext).get(PizzaViewModel.class);
            model.addToppings(selectedItems.toArray(new String[selectedItems.size()]));
        }
    };
    private DialogInterface.OnMultiChoiceClickListener selectedListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
            if(b){
                selectedItems.add(toppings[i]);
            }else{
                selectedItems.remove(toppings[i]);
            }
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        myContext = (FragmentActivity) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);

        toppings = getResources().getStringArray(R.array.toppings);
        checkedIndexes = new boolean[toppings.length];
        Arrays.fill(checkedIndexes, false);
        selectedItems = new ArrayList<>();

        builder.setTitle("Pick your toppings");
        builder.setMultiChoiceItems(toppings, checkedIndexes, selectedListener);

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, confirmListener);

        return builder.create();
    }


}
