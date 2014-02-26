package com.foxmobile.unitconverter.utils.converters;

import com.foxmobile.unitconverter.R;

import android.util.Pair;

/**
 * Created by Azriel on 1/13/14.
 */
public class DigitalStorageConverter extends ConverterBase {

    static public enum Units implements eUnits {
        BIT,
        BYTE,
        KILOBIT,
        KILOBYTE,
        MEGABIT,
        MEGABYTE,
        GIGABIT,
        GIGABYTE,
        TERABIT,
        TERABYTE,
        PETABIT,
        PETABYTE;
    }

    protected DigitalStorageConverter() {
        mBaseUnit = Units.MEGABYTE;
    }

    @Override
    protected void initUnits() {
        mUnits.add(new Pair<eUnits, String>(Units.BIT, "Bit"));
        mUnits.add(new Pair<eUnits, String>(Units.BYTE,  "Byte"));
        mUnits.add(new Pair<eUnits, String>(Units.KILOBIT,  "Kilobit"));
        mUnits.add(new Pair<eUnits, String>(Units.KILOBYTE, "KiloByte"));
        mUnits.add(new Pair<eUnits, String>(Units.MEGABIT,  "Megabit"));
        mUnits.add(new Pair<eUnits, String>(Units.MEGABYTE,  "MegaByte"));
        mUnits.add(new Pair<eUnits, String>(Units.GIGABIT, "Gigabit"));
        mUnits.add(new Pair<eUnits, String>(Units.GIGABYTE,  "GigaByte"));
        mUnits.add(new Pair<eUnits, String>(Units.TERABIT,  "Terabit"));
        mUnits.add(new Pair<eUnits, String>(Units.TERABYTE, "TeraByte"));
        mUnits.add(new Pair<eUnits, String>(Units.PETABIT,  "Petabit"));
        mUnits.add(new Pair<eUnits, String>(Units.PETABYTE,  "PetaByte"));
    }

    @Override
    public String getConverterType() {
        return "Digital Storage";
    }

    @Override
    protected int getBackground() {
        return R.color.bg_digital_storage;
    }

    @Override
    protected int getSymbolResId() {
        return R.drawable.ic_launcher;
    }

    @Override
    protected void initConversions() {
        addRelation(Units.BIT, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg / 8388608.;}},
                new IConversion() {public double convert(double arg) {return arg * 8388608.;}});

        addRelation(Units.BYTE, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg / 1048576.;}},
                new IConversion() {public double convert(double arg) {return arg * 1048576.;}});

        addRelation(Units.KILOBIT, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg / 8192.;}},
                new IConversion() {public double convert(double arg) {return arg * 8192.;}});

        addRelation(Units.KILOBYTE, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg / 1024.;}},
                new IConversion() {public double convert(double arg) {return arg * 1024.;}});

        addRelation(Units.MEGABIT, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg / 8.;}},
                new IConversion() {public double convert(double arg) {return arg * 8.;}});

        addRelation(Units.GIGABIT, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg * 128.;}},
                new IConversion() {public double convert(double arg) {return arg / 128.;}});

        addRelation(Units.GIGABYTE, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg * 1024.;}},
                new IConversion() {public double convert(double arg) {return arg / 1024.;}});

        addRelation(Units.TERABIT, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg * 131072.;}},
                new IConversion() {public double convert(double arg) {return arg / 131072.;}});

        addRelation(Units.TERABYTE, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg * 1048576.;}},
                new IConversion() {public double convert(double arg) {return arg / 1048576.;}});

        addRelation(Units.PETABIT, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg * 134217728.;}},
                new IConversion() {public double convert(double arg) {return arg / 134217728.;}});

        addRelation(Units.PETABYTE, Units.MEGABYTE,
                new IConversion() {public double convert(double arg) {return arg * 1073741824.;}},
                new IConversion() {public double convert(double arg) {return arg / 1073741824.;}});
    }
}
