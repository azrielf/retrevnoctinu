package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

import com.foxmobile.unitconverter.R;

/**
 * Created by Azriel on 1/13/14.
 */
public class VolumeConverter extends ConverterBase {

    static public enum Units implements eUnits {
        US_GAL,
        US_QUART,
        US_PINT,
        US_CUP,
        US_OZ,
        US_TBSP,
        US_TSP,
        CUBIC_METER,
        LITER,
        MILLILITER,
        IMPERIAL_GAL,
        IMPERIAL_QUART,
        IMPERIAL_PINT,
        IMPERIAL_OZ,
        IMPERIAL_TBSP,
        IMPERIAL_TSP,
        CUBIC_FOOT,
        CUBIC_INCH;
    }

    protected VolumeConverter() {
        mBaseUnit = Units.MILLILITER;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.US_GAL, "US Gal"));
        mUnits.add(new Pair<eUnits, String>(Units.US_QUART,  "US Quart"));
        mUnits.add(new Pair<eUnits, String>(Units.US_PINT,  "US Pint"));
        mUnits.add(new Pair<eUnits, String>(Units.US_CUP,  "US Cup"));
        mUnits.add(new Pair<eUnits, String>(Units.US_OZ,  "US Oz"));
        mUnits.add(new Pair<eUnits, String>(Units.US_TBSP,  "US Tbsp"));
        mUnits.add(new Pair<eUnits, String>(Units.US_TSP,  "US Tsp"));
        mUnits.add(new Pair<eUnits, String>(Units.CUBIC_METER,  "Cubic Meter"));
        mUnits.add(new Pair<eUnits, String>(Units.LITER,  "Liter"));
        mUnits.add(new Pair<eUnits, String>(Units.MILLILITER,  "Milliliter"));
        mUnits.add(new Pair<eUnits, String>(Units.IMPERIAL_GAL,  "UK Gal"));
        mUnits.add(new Pair<eUnits, String>(Units.IMPERIAL_QUART,  "UK Quart"));
        mUnits.add(new Pair<eUnits, String>(Units.IMPERIAL_PINT,  "UK Pint"));
        mUnits.add(new Pair<eUnits, String>(Units.IMPERIAL_OZ,  "UK Oz"));
        mUnits.add(new Pair<eUnits, String>(Units.IMPERIAL_TBSP,  "UK Tbsp"));
        mUnits.add(new Pair<eUnits, String>(Units.IMPERIAL_TSP,  "UK Tsp"));
        mUnits.add(new Pair<eUnits, String>(Units.CUBIC_FOOT,  "Cubic Foot"));
        mUnits.add(new Pair<eUnits, String>(Units.CUBIC_INCH,  "Cubic Inch"));
    }

    @Override
    public String getConverterType() {
        return "Volume";
    }

    @Override
    protected int getBackground() {
        return R.color.bg_volume;
    }

    @Override
    protected int getSymbolResId() {
        return R.drawable.ic_launcher;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.US_GAL, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 3785.411784;}},
                new IConversion() {public double convert(double arg) {return arg / 3785.411784;}});

        addRelation(Units.US_QUART, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 946.352946;}},
                new IConversion() {public double convert(double arg) {return arg / 946.352946;}});

        addRelation(Units.US_PINT, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 473.176473;}},
                new IConversion() {public double convert(double arg) {return arg / 473.176473;}});

        addRelation(Units.US_CUP, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 236.5882365;}},
                new IConversion() {public double convert(double arg) {return arg / 236.5882365;}});

        addRelation(Units.US_OZ, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 29.5735295625;}},
                new IConversion() {public double convert(double arg) {return arg / 29.5735295625;}});

        addRelation(Units.US_TBSP, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 14.78676478125;}},
                new IConversion() {public double convert(double arg) {return arg / 14.78676478125;}});

        addRelation(Units.US_TSP, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 4.92892159375;}},
                new IConversion() {public double convert(double arg) {return arg / 4.92892159375;}});

        addRelation(Units.CUBIC_METER, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 1000000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000000.;}});

        addRelation(Units.LITER, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 1000.;}},
                new IConversion() {public double convert(double arg) {return arg / 1000.;}});

        addRelation(Units.IMPERIAL_GAL, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 4546.09;}},
                new IConversion() {public double convert(double arg) {return arg / 4546.09;}});

        addRelation(Units.IMPERIAL_QUART, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 1136.5225;}},
                new IConversion() {public double convert(double arg) {return arg / 1136.5225;}});

        addRelation(Units.IMPERIAL_PINT, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 568.26125;}},
                new IConversion() {public double convert(double arg) {return arg / 568.26125;}});

        addRelation(Units.IMPERIAL_OZ, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 28.4130625;}},
                new IConversion() {public double convert(double arg) {return arg / 28.4130625;}});

        addRelation(Units.IMPERIAL_TBSP, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 17.7581640625;}},
                new IConversion() {public double convert(double arg) {return arg / 17.7581640625;}});

        addRelation(Units.IMPERIAL_TSP, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 5.919388021;}},
                new IConversion() {public double convert(double arg) {return arg / 5.919388021;}});

        addRelation(Units.CUBIC_FOOT, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 28316.846592;}},
                new IConversion() {public double convert(double arg) {return arg / 28316.846592;}});

        addRelation(Units.CUBIC_INCH, Units.MILLILITER,
                new IConversion() {public double convert(double arg) {return arg * 16.387064;}},
                new IConversion() {public double convert(double arg) {return arg / 16.387064;}});
    }
}
