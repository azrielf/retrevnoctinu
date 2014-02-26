package com.foxmobile.unitconverter.utils.converters;

import com.foxmobile.unitconverter.R;

import android.util.Pair;

/**
 * Created by Azriel on 1/13/14.
 */
public class AreaConverter extends ConverterBase {

    static public enum Units implements eUnits {
        SQUARE_KM,
        HECTARE,
        SQUARE_METER,
        SQUARE_CENTIMETER,
        SQUARE_MILE,
        ACRE,
        SQUARE_YARD,
        SQUARE_FOOT,
        SQUARE_INCH;
    }

    protected AreaConverter() {
        mBaseUnit = Units.SQUARE_CENTIMETER;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.SQUARE_KM, "Sq. Km"));
        mUnits.add(new Pair<eUnits, String>(Units.HECTARE,  "Hectare"));
        mUnits.add(new Pair<eUnits, String>(Units.SQUARE_METER,  "Sq. Meter"));
        mUnits.add(new Pair<eUnits, String>(Units.SQUARE_CENTIMETER, "Sq. cm"));
        mUnits.add(new Pair<eUnits, String>(Units.SQUARE_MILE, "Sq. Mile"));
        mUnits.add(new Pair<eUnits, String>(Units.ACRE, "Acre"));
        mUnits.add(new Pair<eUnits, String>(Units.SQUARE_YARD, "Sq. Yard"));
        mUnits.add(new Pair<eUnits, String>(Units.SQUARE_FOOT, "Sq. Foot"));
        mUnits.add(new Pair<eUnits, String>(Units.SQUARE_INCH, "Sq. Inch"));
    }

    @Override
    public String getConverterType() {
        return "Area";
    }

    @Override
    protected int getBackground() {
        return R.color.bg_area;
    }

    @Override
    protected int getSymbolResId() {
        return R.drawable.ic_launcher;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.SQUARE_KM, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 10000000000.;}},
                new IConversion() {public double convert(double arg) {return arg / 10000000000.;}});

        addRelation(Units.HECTARE, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 100000000.;}},
                new IConversion() {public double convert(double arg) {return arg / 100000000.;}});

        addRelation(Units.SQUARE_METER, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 10000.;}},
                new IConversion() {public double convert(double arg) {return arg / 10000.;}});

        addRelation(Units.SQUARE_MILE, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 25899881103.36;}},
                new IConversion() {public double convert(double arg) {return arg / 25899881103.36;}});

        addRelation(Units.ACRE, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 40468564.224;}},
                new IConversion() {public double convert(double arg) {return arg / 40468564.224;}});

        addRelation(Units.SQUARE_YARD, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 8361.2736;}},
                new IConversion() {public double convert(double arg) {return arg / 8361.2736;}});

        addRelation(Units.SQUARE_FOOT, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 929.0304;}},
                new IConversion() {public double convert(double arg) {return arg / 929.0304;}});

        addRelation(Units.SQUARE_INCH, Units.SQUARE_CENTIMETER,
                new IConversion() {public double convert(double arg) {return arg * 6.4516;}},
                new IConversion() {public double convert(double arg) {return arg / 6.4516;}});
    }
}
