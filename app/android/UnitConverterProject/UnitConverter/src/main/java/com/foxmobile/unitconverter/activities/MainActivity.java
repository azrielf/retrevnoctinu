package com.foxmobile.unitconverter.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foxmobile.unitconverter.R;
import com.foxmobile.unitconverter.utils.adapters.ConvertersSpinnerAdapter;
import com.foxmobile.unitconverter.utils.adapters.UnitsSpinnerAdapter;
import com.foxmobile.unitconverter.utils.converters.ConverterBase;
import com.foxmobile.unitconverter.utils.converters.ConvertersManager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class MainActivity extends ActionBarActivity implements android.app.ActionBar.OnNavigationListener {

    ConvertersManager mConverters;
    View mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConverters = new ConvertersManager();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(mConverters))
                    .commit();
        }

        // workaround for navigation theme. should be done by creating a layout for the spinner
        Context themeContext = new ContextThemeWrapper(this, android.R.style.Theme_Holo);
        final ConvertersSpinnerAdapter adapterConverters = new ConvertersSpinnerAdapter(themeContext, mConverters);

        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(adapterConverters, this);

        // for the 9 patch background to work properly
        mContainer = findViewById(R.id.container);
        mContainer.setPadding(0, 0, 0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Integer selectedConverterType = (int)itemId;

        mConverters.setCurrConverter(selectedConverterType);
        mConverters.updateUnits(selectedConverterType);


        mContainer.setBackgroundResource(mConverters.getCurrConverterBackground());

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {Toast.makeText(this, "onOptionsItemSelected", Toast.LENGTH_SHORT).show();
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
            implements ConvertersManager.ConvertersManagerListener,
                       View.OnFocusChangeListener,
                       AdapterView.OnItemSelectedListener,
                       TextWatcher{

        static final String NUMBER_EDIT_FORMAT = "0.####"; //"%.3f";
        static final String NUMBER_DISPLAY_FORMAT = "#,###.####"; //"%1$,.3f";

        EditText mTextUnit1;
        EditText mTextUnit2;
        Spinner mSpinnerUnits1;
        Spinner mSpinnerUnits2;
        ConvertersManager mConverters;
        UnitsSpinnerAdapter mAdapterUnits;
        boolean mWatchTextUnit = true;

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

            return rootView;
        }

        @Override
        public void onCurrentUnitsUpdated() {
            mSpinnerUnits1.setSelection(0);
            mSpinnerUnits2.setSelection(1);
            mAdapterUnits.notifyDataSetChanged();
            convert(true);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.spinnerUnits1:
//                    convert(false);
                    convert(true);
                    break;
                case R.id.spinnerUnits2:
                    convert(true);
                    break;
            }
        }

        // TextWatcher
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // TextWatcher
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mWatchTextUnit) {
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

//            msg("input = " + String.valueOf(input));
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
