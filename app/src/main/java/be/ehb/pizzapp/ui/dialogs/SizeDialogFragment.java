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

import be.ehb.pizzapp.R;
import be.ehb.pizzapp.model.PizzaViewModel;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class SizeDialogFragment extends DialogFragment {

    private FragmentActivity myContext;
    private int selected = -1;
    private final DialogInterface.OnClickListener selectionListener = (dialogInterface, i) -> selected = i;
    private final DialogInterface.OnClickListener confirmListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            String size = getResources().getStringArray(R.array.sizes)[selected];
            PizzaViewModel model = new ViewModelProvider(myContext).get(PizzaViewModel.class);
            model.addSize(size);
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

        builder.setTitle("Pick a size");
        builder.setSingleChoiceItems(R.array.sizes, selected, selectionListener);

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, confirmListener);

        return builder.create();
    }
}
