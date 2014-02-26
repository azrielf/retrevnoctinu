package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azriel on 1/13/14.
 */
public abstract class ConverterBase {

    protected eUnits mBaseUnit;
    protected ArrayList< Pair<eUnits, String> > mUnits;
    private Map<Pair<eUnits, eUnits>, IConversion> mMapConversions = new HashMap<Pair<eUnits, eUnits>, IConversion>();

    protected ConverterBase() {
        mUnits = new ArrayList< Pair<eUnits, String> >();

        initUnits();
        initConversions();
    }

    abstract protected String getConverterType();
    abstract protected int getBackground();
    abstract protected int getSymbolResId();
    abstract protected void initUnits();
    abstract protected void initConversions();

    public ArrayList< Pair<eUnits, String> > getUnits() {
        return mUnits;
    }

    public void addRelation(eUnits unitA, eUnits unitB, IConversion AtoB, IConversion BtoA) {
        mMapConversions.put(new Pair<eUnits, eUnits>(unitA, unitB), AtoB);
        mMapConversions.put(new Pair<eUnits, eUnits>(unitB, unitA), BtoA);
    }

    public double execute(eUnits from, eUnits to, double arg) {
        if (from.equals(to)) {
            return arg;
        }

        if (from.equals(mBaseUnit) || to.equals(mBaseUnit)) {
            return mMapConversions.get(new Pair<eUnits, eUnits>(from, to)).convert(arg);
        }

        double fromToBase = mMapConversions.get(new Pair<eUnits, eUnits>(from, mBaseUnit)).convert(arg);

        return mMapConversions.get(new Pair<eUnits, eUnits>(mBaseUnit, to)).convert(fromToBase);
    }


    // Helper classes
    //================================================

    protected interface IConversion {
        double convert(double arg);
    }

    public interface eUnits {
    }
}
