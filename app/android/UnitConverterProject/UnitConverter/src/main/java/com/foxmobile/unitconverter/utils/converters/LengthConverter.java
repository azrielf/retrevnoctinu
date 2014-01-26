package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

import com.foxmobile.unitconverter.R;
import java.util.ArrayList;

/**
 * Created by Azriel on 1/13/14.
 */
public class LengthConverter extends ConverterBase {

    static public enum Units implements eUnits {
        KILOMETER,
        METER,
        CENTIMETER,
        MILLIMETER,
        MILE,
        YARD,
        FOOT,
        INCH;
    }

    protected LengthConverter() {
        mBaseUnit = Units.MILLIMETER;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.KILOMETER, "Kilometer"));
        mUnits.add(new Pair<eUnits, String>(Units.METER, "Meter"));
        mUnits.add(new Pair<eUnits, String>(Units.CENTIMETER, "Centimeter"));
        mUnits.add(new Pair<eUnits, String>(Units.MILLIMETER,  "Millimeter"));
        mUnits.add(new Pair<eUnits, String>(Units.MILE, "Mile"));
        mUnits.add(new Pair<eUnits, String>(Units.YARD, "Yard"));
        mUnits.add(new Pair<eUnits, String>(Units.FOOT, "Foot"));
        mUnits.add(new Pair<eUnits, String>(Units.INCH, "Inch"));
    }

    @Override
    public String getConverterType() {
        return "Length";
    }

    @Override
    protected int getBackground() {
        return R.drawable.bg_length;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.KILOMETER, Units.MILLIMETER,
                new IConversion() {public double convert(double arg) {return arg * 1000000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000000.;}});
        addRelation(Units.METER, Units.MILLIMETER,
                new IConversion() {public double convert(double arg) {return arg * 1000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000.;}});
        addRelation(Units.CENTIMETER, Units.MILLIMETER,
                new IConversion() {public double convert(double arg) {return arg * 10.;}},
                new IConversion() {public double convert(double arg) {return arg / 10.;}});
        addRelation(Units.MILE, Units.MILLIMETER,
                new IConversion() {public double convert(double arg) {return arg * 1609344.;}},
                new IConversion() {public double convert(double arg) {return arg / 1609344.;}});
        addRelation(Units.YARD, Units.MILLIMETER,
                new IConversion() {public double convert(double arg) {return arg * 914.4;}},
                new IConversion() {public double convert(double arg) {return arg / 914.4;}});
        addRelation(Units.FOOT, Units.MILLIMETER,
                new IConversion() {public double convert(double arg) {return arg * 304.8;}},
                new IConversion() {public double convert(double arg) {return arg / 304.8;}});
        addRelation(Units.INCH, Units.MILLIMETER,
                new IConversion() {public double convert(double arg) {return arg * 25.4;}},
                new IConversion() {public double convert(double arg) {return arg / 25.4;}});
    }
}
