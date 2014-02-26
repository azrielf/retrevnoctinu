package com.foxmobile.unitconverter.utils.converters;

import com.foxmobile.unitconverter.R;

import android.util.Pair;

/**
 * Created by Azriel on 1/13/14.
 */
public class TimeConverter extends ConverterBase {

    static public enum Units implements eUnits {
        NANOSECOND,
        MICROSECOND,
        MILLISECOND,
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK,
        MONTH,
        YEAR,
        DECADE,
        CENTURY;
    }

    protected TimeConverter() {
        mBaseUnit = Units.SECOND;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.NANOSECOND, "Nanosecond"));
        mUnits.add(new Pair<eUnits, String>(Units.MICROSECOND,  "Microsecond"));
        mUnits.add(new Pair<eUnits, String>(Units.MILLISECOND,  "Millisecond"));
        mUnits.add(new Pair<eUnits, String>(Units.SECOND,  "Second"));
        mUnits.add(new Pair<eUnits, String>(Units.MINUTE,  "Minute"));
        mUnits.add(new Pair<eUnits, String>(Units.HOUR,  "Hour"));
        mUnits.add(new Pair<eUnits, String>(Units.DAY, "Day"));
        mUnits.add(new Pair<eUnits, String>(Units.WEEK,  "Week"));
        mUnits.add(new Pair<eUnits, String>(Units.MONTH,  "Month"));
        mUnits.add(new Pair<eUnits, String>(Units.YEAR,  "Year"));
        mUnits.add(new Pair<eUnits, String>(Units.DECADE,  "Decade"));
        mUnits.add(new Pair<eUnits, String>(Units.CENTURY,  "Century"));
    }

    @Override
    public String getConverterType() {
        return "Time";
    }

    @Override
    protected int getBackground() {
        return R.color.bg_time;
    }

    @Override
    protected int getSymbolResId() {
        return R.drawable.symbol_time;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.NANOSECOND, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg / 1000000000.;}},
                new IConversion() {public double convert(double arg) {return arg * 1000000000.;}});

        addRelation(Units.MICROSECOND, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg / 1000000.;}},
                new IConversion() {public double convert(double arg) {return arg * 1000000.;}});

        addRelation(Units.MILLISECOND, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg / 1000.;}},
                new IConversion() {public double convert(double arg) {return arg * 1000.;}});

        addRelation(Units.MINUTE, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 60.;}},
                new IConversion() {public double convert(double arg) {return arg / 60.;}});

        addRelation(Units.HOUR, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 3600.;}},
                new IConversion() {public double convert(double arg) {return arg / 3600.;}});

        addRelation(Units.DAY, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 86400.;}},
                new IConversion() {public double convert(double arg) {return arg / 86400.;}});

        addRelation(Units.WEEK, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 604800.;}},
                new IConversion() {public double convert(double arg) {return arg / 604800.;}});

        addRelation(Units.MONTH, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 2628000.;}},
                new IConversion() {public double convert(double arg) {return arg / 2628000.;}});

        addRelation(Units.YEAR, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 31536000.;}},
                new IConversion() {public double convert(double arg) {return arg / 31536000.;}});

        addRelation(Units.DECADE, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 315360000.;}},
                new IConversion() {public double convert(double arg) {return arg / 315360000.;}});

        addRelation(Units.CENTURY, Units.SECOND,
                new IConversion() {public double convert(double arg) {return arg * 3153600000.;}},
                new IConversion() {public double convert(double arg) {return arg / 3153600000.;}});
    }
}
