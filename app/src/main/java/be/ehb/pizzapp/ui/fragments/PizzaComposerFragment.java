package be.ehb.pizzapp.ui.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.pizzapp.R;
import be.ehb.pizzapp.model.Pizza;
import be.ehb.pizzapp.model.PizzaViewModel;
import be.ehb.pizzapp.ui.dialogs.SizeDialogFragment;
import be.ehb.pizzapp.ui.dialogs.ToppingsDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PizzaComposerFragment extends Fragment {

    private static Pizza pizza;
    private FragmentActivity myContext;
    private SizeDialogFragment sizeDialogFragment;
    private ToppingsDialogFragment toppingsDialogFragment;
    private String pizzadescription;
    private View.OnClickListener openSizeDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sizeDialogFragment.show(getParentFragmentManager(), "sdf");
        }
    };
    private View.OnClickListener openToppingsDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            toppingsDialogFragment.show(getParentFragmentManager(), "tdf");
        }
    };
    private View.OnClickListener orderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(myContext.getApplicationContext(), pizzadescription, Toast.LENGTH_LONG).show();
        }
    };

    public PizzaComposerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        myContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pizza_composer, container, false);

        final TextView tvToppings = rootView.findViewById(R.id.tv_toppings);
        final TextView tvSize = rootView.findViewById(R.id.tv_size);
        Button btnOrder = rootView.findViewById(R.id.btn_order);
        Button btnPickSize = rootView.findViewById(R.id.btn_pick_size);
        Button btnPickToppings = rootView.findViewById(R.id.btn_pick_toppings);

        PizzaViewModel model = new ViewModelProvider(myContext).get(PizzaViewModel.class);
        model.getSharedPizza().observe(getViewLifecycleOwner(), new PizzaObserver(tvToppings, tvSize));

        sizeDialogFragment = new SizeDialogFragment();
        toppingsDialogFragment = new ToppingsDialogFragment();

        btnPickSize.setOnClickListener(openSizeDialog);
        btnPickToppings.setOnClickListener(openToppingsDialog);
        btnOrder.setOnClickListener(orderListener);

        return rootView;
    }

    private class PizzaObserver implements Observer<Pizza> {

        private final TextView tvToppings;
        private final TextView tvSize;

        public PizzaObserver(TextView tvToppings, TextView tvSize) {
            this.tvToppings = tvToppings;
            this.tvSize = tvSize;
        }

        @Override
        public void onChanged(Pizza pizza) {
            String[] toppings = pizza.getToppings();
            StringBuilder sBuilder = new StringBuilder();
            int listSize = toppings.length;
            for(int i = 0; i < listSize; i++ ){
                if(i != listSize - 1){
                    sBuilder.append(toppings[i]);
                    sBuilder.append("\n");
                }else{
                    sBuilder.append(toppings[i]);
                }
            }
            tvToppings.setText(sBuilder.toString());
            tvSize.setText(pizza.getSize());
            pizzadescription = pizza.toString();
        }
    }
}
