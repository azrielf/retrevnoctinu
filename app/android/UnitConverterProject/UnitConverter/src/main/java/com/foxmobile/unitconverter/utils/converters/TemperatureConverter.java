package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

import com.foxmobile.unitconverter.R;

/**
 * Created by Azriel on 1/13/14.
 */
public class TemperatureConverter extends ConverterBase {

    static public enum Units implements eUnits {
        CELSIUS,
        FAHRENHEIT,
        KELVIN
    }

    protected TemperatureConverter() {
        mBaseUnit = Units.FAHRENHEIT;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.CELSIUS, "Celsius"));
        mUnits.add(new Pair<eUnits, String>(Units.FAHRENHEIT,  "Fahrenheit"));
        mUnits.add(new Pair<eUnits, String>(Units.KELVIN,  "Kelvin"));
    }

    @Override
    public String getConverterType() {
        return "Temperature";
    }

    @Override
    protected int getBackground() {
        return R.color.bg_temperature;
    }

    @Override
    protected int getSymbolResId() {
        return R.drawable.symbol_temperature;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.CELSIUS, Units.FAHRENHEIT,
                new IConversion() {public double convert(double arg) {return arg * 9 / 5 + 32;}},
                new IConversion() {public double convert(double arg) {return (arg - 32) * 5 / 9;}});

//        addRelation(Units.CELSIUS, Units.KELVIN,
//                new IConversion() {public double convert(double arg) {return arg + 273.15;}},
//                new IConversion() {public double convert(double arg) {return arg - 273.15;}});

        addRelation(Units.KELVIN, Units.FAHRENHEIT,
                new IConversion() {public double convert(double arg) {return (arg - 273.15) * 9 / 5 + 32;}},
                new IConversion() {public double convert(double arg) {return (arg - 32) * 5 / 9 + 273.15;}});
    }
}
