package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

/**
 * Created by Azriel on 1/13/14.
 */
public class SpeedConverter extends ConverterBase {

    static public enum Units implements eUnits {
        MILES_HOUR,
        FEET_SEC,
        METERS_SEC,
        KM_HOUR,
        KNOT,
        SOUND,
        LIGHT
    }

    protected SpeedConverter() {
        mBaseUnit = Units.METERS_SEC;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.MILES_HOUR, "Miles/hour"));
        mUnits.add(new Pair<eUnits, String>(Units.FEET_SEC,  "Feet/sec"));
        mUnits.add(new Pair<eUnits, String>(Units.METERS_SEC,  "Meters/sec"));
        mUnits.add(new Pair<eUnits, String>(Units.KM_HOUR,  "Km/hour"));
        mUnits.add(new Pair<eUnits, String>(Units.KNOT,  "Knot"));
        mUnits.add(new Pair<eUnits, String>(Units.SOUND,  "Sound (20°C)"));
        mUnits.add(new Pair<eUnits, String>(Units.LIGHT,  "Light"));
    }

    @Override
    public String getConverterType() {
        return "Speed";
    }

    @Override
    protected int getBackground() {
        return android.R.color.holo_green_dark;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.MILES_HOUR, Units.METERS_SEC,
                new IConversion() {public double convert(double arg) {return arg * 0.44704;}},
                new IConversion() {public double convert(double arg) {return arg / 0.44704;}});

        addRelation(Units.FEET_SEC, Units.METERS_SEC,
                new IConversion() {public double convert(double arg) {return arg * 0.3048;}},
                new IConversion() {public double convert(double arg) {return arg / 0.3048;}});

        addRelation(Units.KM_HOUR, Units.METERS_SEC,
                new IConversion() {public double convert(double arg) {return arg / 3.6;}},
                new IConversion() {public double convert(double arg) {return arg * 3.6;}});

        addRelation(Units.KNOT, Units.METERS_SEC,
                new IConversion() {public double convert(double arg) {return arg * 463. / 900.;}},
                new IConversion() {public double convert(double arg) {return arg * 900. / 463.;}});

        addRelation(Units.SOUND, Units.METERS_SEC,
                new IConversion() {public double convert(double arg) {return arg * 343.2;}},
                new IConversion() {public double convert(double arg) {return arg / 343.2;}});

        addRelation(Units.LIGHT, Units.METERS_SEC,
                new IConversion() {public double convert(double arg) {return arg * 299792458.;}},
                new IConversion() {public double convert(double arg) {return arg / 299792458.;}});
    }
}
