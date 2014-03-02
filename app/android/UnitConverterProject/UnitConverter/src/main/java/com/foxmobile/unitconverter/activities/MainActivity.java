package com.foxmobile.unitconverter.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.foxmobile.unitconverter.R;
import com.foxmobile.unitconverter.utils.adapters.ConvertersSpinnerAdapter;
import com.foxmobile.unitconverter.utils.adapters.UnitsSpinnerAdapter;
import com.foxmobile.unitconverter.utils.converters.ConverterBase;
import com.foxmobile.unitconverter.utils.converters.ConvertersManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


public class MainActivity extends ActionBarActivity implements android.app.ActionBar.OnNavigationListener {

    static private final String TAG = "UnitsConverter";
    static private final String PREF_SELECTED_UNIT_TYPE = "SelectedUnitType";

    ConvertersManager mConverters;
    View mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConverters = new ConvertersManager();

        // initialize the state of the converters manager
        int selectedConverterType = getPreferences(Activity.MODE_PRIVATE).getInt(PREF_SELECTED_UNIT_TYPE, 0);
        mConverters.setCurrConverter(selectedConverterType);
        mConverters.updateUnits(selectedConverterType);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(mConverters))
                    .commit();
        }

        // workaround for navigation theme. should be done by creating a layout for the spinner
        Context themeContext = new ContextThemeWrapper(this, android.R.style.Theme_Holo);
        final ConvertersSpinnerAdapter adapterConverters = new ConvertersSpinnerAdapter(themeContext, mConverters);

//        getActionBar().setDisplayShowTitleEnabled(false);
//        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(adapterConverters, this);

        // for the 9 patch background to work properly
        mContainer = findViewById(R.id.container);
        mContainer.setPadding(0, 0, 0, 0);

        // restore the last unit type selection of the user
        getActionBar().setSelectedNavigationItem(getPreferences(Activity.MODE_PRIVATE).getInt(PREF_SELECTED_UNIT_TYPE, 0));
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = getPreferences(Activity.MODE_PRIVATE).edit();
        editor.putInt(PREF_SELECTED_UNIT_TYPE, getActionBar().getSelectedNavigationIndex());
        editor.commit();

        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Integer selectedConverterType = (int)itemId;

        mConverters.setCurrConverter(selectedConverterType);
        mConverters.updateUnits(selectedConverterType);

        mContainer.setBackgroundResource(mConverters.getCurrConverterBackground());

        return false;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {Toast.makeText(this, "onOptionsItemSelected", Toast.LENGTH_SHORT).show();
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
            implements ConvertersManager.ConvertersManagerListener,
                       View.OnFocusChangeListener,
                       AdapterView.OnItemSelectedListener,
                       TextWatcher {

        static private final String PREF_SELECTED_UNIT_1_TYPE_X = "SelectedUnit1_Type%d";
        static private final String PREF_SELECTED_UNIT_2_TYPE_X = "SelectedUnit2_Type%d";
        static private final String PREF_VALUE_UNIT_1 = "ValueUnit1";

        static private final String NUMBER_EDIT_FORMAT = "0.####"; //"%.3f";
        static private final String NUMBER_DISPLAY_FORMAT = "#,###.####"; //"%1$,.3f";

        EditText mTextUnit1;
        EditText mTextUnit2;
        Spinner mSpinnerUnits1;
        Spinner mSpinnerUnits2;
        ImageView mImageSymbol;
        ConvertersManager mConverters;
        UnitsSpinnerAdapter mAdapterUnits;
        boolean mWatchTextUnit = true;
        int mLastSelectedUnit1;
        int mLastSelectedUnit2;

        public PlaceholderFragment(ConvertersManager converters) {
            mConverters = converters;
            mConverters.setListener(this);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mAdapterUnits = new UnitsSpinnerAdapter(getActivity(), mConverters);


            mTextUnit1 = (EditText)rootView.findViewById(R.id.textUnit1);
            mTextUnit1.setRawInputType(Configuration.KEYBOARD_12KEY);
            mTextUnit1.setOnFocusChangeListener(this);
            mTextUnit1.addTextChangedListener(this);

            mTextUnit2 = (EditText)rootView.findViewById(R.id.textUnit2);
            mTextUnit2.setRawInputType(Configuration.KEYBOARD_12KEY);
            mTextUnit2.setOnFocusChangeListener(this);
            mTextUnit2.addTextChangedListener(this);

            mSpinnerUnits1 = (Spinner)rootView.findViewById(R.id.spinnerUnits1);
            mSpinnerUnits1.setAdapter(mAdapterUnits);
            mSpinnerUnits1.setOnItemSelectedListener(this);

            mSpinnerUnits2 = (Spinner)rootView.findViewById(R.id.spinnerUnits2);
            mSpinnerUnits2.setAdapter(mAdapterUnits);
            mSpinnerUnits2.setOnItemSelectedListener(this);

            mImageSymbol = (ImageView)rootView.findViewById(R.id.imageUnitSymbol);

            // set the initially selected units by the selection in the last session
            int selectedUnitsType = getActivity().getActionBar().getSelectedNavigationIndex();
            SharedPreferences pref = getActivity().getPreferences(Activity.MODE_PRIVATE);
            mSpinnerUnits1.setSelection(pref.getInt(String.format(PREF_SELECTED_UNIT_1_TYPE_X, selectedUnitsType), 0));
            mSpinnerUnits2.setSelection(pref.getInt(String.format(PREF_SELECTED_UNIT_2_TYPE_X, selectedUnitsType), 1));

            // restore the last values the user used (the second value is calculated by the first one)
            mTextUnit1.setText(pref.getString(PREF_VALUE_UNIT_1, "0"));
            mTextUnit1.setSelection(mTextUnit1.getText().length());

            return rootView;
        }

        @Override
        public void onDestroyView() {
            SharedPreferences.Editor editor = getActivity().getPreferences(Activity.MODE_PRIVATE).edit();
            editor.putString(PREF_VALUE_UNIT_1, mTextUnit1.getText().toString());
            editor.commit();

            super.onDestroyView();
        }

        @Override
        public void onCurrentUnitsUpdated() {
            mImageSymbol.setImageResource(mConverters.getCurrConverterSymbolResId());

            // restore the last units selections the user used for this type of unit
            int selectedUnitsType = getActivity().getActionBar().getSelectedNavigationIndex();
            SharedPreferences pref = getActivity().getPreferences(Activity.MODE_PRIVATE);
            mSpinnerUnits1.setSelection(pref.getInt(String.format(PREF_SELECTED_UNIT_1_TYPE_X, selectedUnitsType), 0));
            mSpinnerUnits2.setSelection(pref.getInt(String.format(PREF_SELECTED_UNIT_2_TYPE_X, selectedUnitsType), 1));


            mAdapterUnits.notifyDataSetChanged();
            convert(true);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.spinnerUnits1:
                    // never have both spinners show the same unit
                    if (position == mSpinnerUnits2.getSelectedItemPosition()) {
                        mSpinnerUnits2.setSelection(mLastSelectedUnit1);
                    }
                    mLastSelectedUnit1 = position;

//                    convert(false);
                    convert(true);
                    break;
                case R.id.spinnerUnits2:
                    // never have both spinners show the same unit
                    if (position == mSpinnerUnits1.getSelectedItemPosition()) {
                        mSpinnerUnits1.setSelection(mLastSelectedUnit2);
                    }
                    mLastSelectedUnit2 = position;

                    convert(true);
                    break;
            }

            // save selected units for each unit type
            int selectedUnitsType = getActivity().getActionBar().getSelectedNavigationIndex();
            SharedPreferences.Editor editor = getActivity().getPreferences(Activity.MODE_PRIVATE).edit();
            editor.putInt(String.format(PREF_SELECTED_UNIT_1_TYPE_X, selectedUnitsType), mSpinnerUnits1.getSelectedItemPosition());
            editor.putInt(String.format(PREF_SELECTED_UNIT_2_TYPE_X, selectedUnitsType), mSpinnerUnits2.getSelectedItemPosition());
            editor.commit();
        }

        // TextWatcher
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // TextWatcher
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mWatchTextUnit && getActivity().getCurrentFocus() != null) {
                convert(getActivity().getCurrentFocus().getId() == R.id.textUnit1);
            }
        }

        // TextWatcher
        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

        // View.OnFocusChangeListener
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (v instanceof EditText) {
                EditText tv;
                switch (v.getId()) {
                    case R.id.textUnit1:
                        tv = mTextUnit1;
                        break;
                    case R.id.textUnit2:
                        tv = mTextUnit2;
                        break;
                    default:
                        return;
                }

                mWatchTextUnit = false;
                tv.setText(getStringValue(!hasFocus, getDoubleValue(tv.getText().toString())));
                mWatchTextUnit = true;
                tv.setSelection(tv.getText().length());
            }
        }

        private double getDoubleValue(String text) {
            NumberFormat format = NumberFormat.getNumberInstance();
            try {
                Number number = format.parse(text);
                return number.doubleValue();
            } catch (ParseException e) {
                return 0.;
            }
        }

        private String getStringValue(boolean display, double val) {
            DecimalFormat decimalFormat = new DecimalFormat(display ? NUMBER_DISPLAY_FORMAT : NUMBER_EDIT_FORMAT);
            return decimalFormat.format(val);
        }

        private void convert(boolean firstToSecond) {
            EditText textFrom, textTo;
            Spinner spinnerFrom, spinnerTo;
            if (firstToSecond) {
                textFrom = mTextUnit1;
                textTo = mTextUnit2;
                spinnerFrom = mSpinnerUnits1;
                spinnerTo = mSpinnerUnits2;
            } else {
                textFrom = mTextUnit2;
                textTo = mTextUnit1;
                spinnerFrom = mSpinnerUnits2;
                spinnerTo = mSpinnerUnits1;
            }

            String strFrom = textFrom.getText().toString();
//            if (strFrom.isEmpty() || strFrom.equals("-") || strFrom.equals(".") || strFrom.equals("-.")) {
//                String strTo = textTo.getText().toString();
//                if (!strTo.isEmpty()) {
//                    textTo.setText("");
//                }
//                return;
//            }
//
//            double input = Double.parseDouble(strFrom);
            double input = getDoubleValue(strFrom);

            ConverterBase.eUnits unitFrom = ((Pair <ConverterBase.eUnits, String>)spinnerFrom.getSelectedItem()).first;
            ConverterBase.eUnits unitTo = ((Pair <ConverterBase.eUnits, String>)spinnerTo.getSelectedItem()).first;

            mWatchTextUnit = false;
            textTo.setText(getStringValue(true, mConverters.convert(unitFrom, unitTo, input)));
            mWatchTextUnit = true;
        }

        private void msg(String text) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }

}
