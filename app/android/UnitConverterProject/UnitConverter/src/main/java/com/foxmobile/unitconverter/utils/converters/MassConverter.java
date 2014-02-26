package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

import com.foxmobile.unitconverter.R;


/**
 * Created by Azriel on 1/13/14.
 */
public class MassConverter extends ConverterBase {

    static public enum Units implements eUnits {
        METRIC_TON,
        KILOGRAM,
        GRAM,
        MILLIGRAM,
        MICROGRAM,
        LONG_TON,
        SHORT_TON,
        STONE,
        POUND,
        OUNCE;
    }

    protected MassConverter() {
        mBaseUnit = Units.MICROGRAM;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.METRIC_TON, "Metric Ton"));
        mUnits.add(new Pair<eUnits, String>(Units.KILOGRAM, "Kilogram"));
        mUnits.add(new Pair<eUnits, String>(Units.GRAM, "Gram"));
        mUnits.add(new Pair<eUnits, String>(Units.MILLIGRAM, "Milligram"));
        mUnits.add(new Pair<eUnits, String>(Units.MICROGRAM, "Microgram"));
        mUnits.add(new Pair<eUnits, String>(Units.LONG_TON, "Long Ton"));
        mUnits.add(new Pair<eUnits, String>(Units.SHORT_TON, "Short Ton"));
        mUnits.add(new Pair<eUnits, String>(Units.STONE, "Stone"));
        mUnits.add(new Pair<eUnits, String>(Units.POUND, "Pound"));
        mUnits.add(new Pair<eUnits, String>(Units.OUNCE, "Ounce"));
    }

    @Override
    public String getConverterType() {
        return "Mass";
    }

    @Override
    protected int getBackground() {
        return R.color.bg_mass;
    }

    @Override
    protected int getSymbolResId() {
        return R.drawable.symbol_mass;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.METRIC_TON, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 1000000000000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000000000000.;}});

        addRelation(Units.KILOGRAM, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 1000000000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000000000.;}});

        addRelation(Units.GRAM, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 1000000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000000.;}});

        addRelation(Units.MILLIGRAM, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 1000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000.;}});

        addRelation(Units.LONG_TON, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 1016046908800.;}},
                new IConversion() {public double convert(double arg) {return arg / 1016046908800.;}});

        addRelation(Units.SHORT_TON, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 907184740000.;}},
                new IConversion() {public double convert(double arg) {return arg / 907184740000.;}});

        addRelation(Units.STONE, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 6350293180.;}},
                new IConversion() {public double convert(double arg) {return arg / 6350293180.;}});

        addRelation(Units.POUND, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 453592370.;}},
                new IConversion() {public double convert(double arg) {return arg / 453592370.;}});

        addRelation(Units.OUNCE, Units.MICROGRAM,
                new IConversion() {public double convert(double arg) {return arg * 28349523.125;}},
                new IConversion() {public double convert(double arg) {return arg / 28349523.125;}});
    }
}
