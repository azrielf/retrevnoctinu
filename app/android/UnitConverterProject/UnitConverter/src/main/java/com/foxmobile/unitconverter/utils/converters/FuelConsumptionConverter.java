package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

/**
 * Created by Azriel on 1/13/14.
 */
public class FuelConsumptionConverter extends ConverterBase {

    static public enum Units implements eUnits {
        MPG_US,
        MPG_IMPERIAL,
        KM_LITER,
        LITER_100KM;
    }

    protected FuelConsumptionConverter() {
        mBaseUnit = Units.KM_LITER;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.MPG_US, "Mile/Gal(US)"));
        mUnits.add(new Pair<eUnits, String>(Units.MPG_IMPERIAL,  "Mile/Gal(UK)"));
        mUnits.add(new Pair<eUnits, String>(Units.KM_LITER,  "Km/Liter"));
        mUnits.add(new Pair<eUnits, String>(Units.LITER_100KM,  "Liter/100Km"));
    }

    @Override
    public String getConverterType() {
        return "Fuel Consumption";
    }

    @Override
    protected int getBackground() {
        return android.R.color.holo_green_dark;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.MPG_US, Units.KM_LITER,
                new IConversion() {public double convert(double arg) {return arg * 0.42514371;}},
                new IConversion() {public double convert(double arg) {return arg / 0.42514371;}});

        addRelation(Units.MPG_IMPERIAL, Units.KM_LITER,
                new IConversion() {public double convert(double arg) {return arg * 0.35400619;}},
                new IConversion() {public double convert(double arg) {return arg / 0.35400619;}});

        addRelation(Units.LITER_100KM, Units.KM_LITER,
                new IConversion() {public double convert(double arg) {return 100. / arg;}},
                new IConversion() {public double convert(double arg) {return 100. / arg;}});
    }
}
