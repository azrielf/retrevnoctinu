package com.foxmobile.unitconverter.utils.converters;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Azriel on 1/13/14.
 */
public class ConvertersManager {
    public interface ConvertersManagerListener {
        void onCurrentUnitsUpdated();
    }

    public static class ConverterTypes {
        static final Integer TEMPERATURE = 0;
        static final Integer LENGTH = 1;
        static final Integer MASS = 2;
        static final Integer SPEED = 3;
        static final Integer VOLUME = 4;
        static final Integer AREA = 5;
        static final Integer FUEL_CONSUMPTION = 6;
        static final Integer TIME = 7;
        static final Integer DIGITAL_STORAGE = 8;
    }

    Map<Integer, ConverterBase> mConverters = new HashMap<Integer, ConverterBase>();
    ConverterBase mCurrConverter = null;
    ArrayList< Pair<ConverterBase.eUnits, String> > mCurrConverterUnits = new ArrayList<Pair<ConverterBase.eUnits, String>>();
    ConvertersManagerListener m_listener = null;


    public ConvertersManager() {
        mConverters.put(ConverterTypes.TEMPERATURE, new TemperatureConverter());
        mConverters.put(ConverterTypes.LENGTH, new LengthConverter());
        mConverters.put(ConverterTypes.MASS, new MassConverter());
        mConverters.put(ConverterTypes.SPEED, new SpeedConverter());
        mConverters.put(ConverterTypes.VOLUME, new VolumeConverter());
        mConverters.put(ConverterTypes.AREA, new AreaConverter());
        mConverters.put(ConverterTypes.FUEL_CONSUMPTION, new FuelConsumptionConverter());
        mConverters.put(ConverterTypes.TIME, new TimeConverter());
        mConverters.put(ConverterTypes.DIGITAL_STORAGE, new DigitalStorageConverter());

        // set the units of the initial converter
        mCurrConverter = mConverters.entrySet().iterator().next().getValue();
        mCurrConverterUnits.addAll(mCurrConverter.getUnits());
    }

    public void setListener(ConvertersManagerListener listener) {
        m_listener = listener;
    }

    public void setCurrConverter(Integer currConverterType) {
        mCurrConverter = mConverters.get(currConverterType);
    }

    public int getCurrConverterBackground() {
        return mCurrConverter.getBackground();
    }

    public void updateUnits(Integer currConverterType) {
        mCurrConverterUnits.clear();
        mCurrConverterUnits.addAll(mConverters.get(currConverterType).getUnits());

        if (m_listener != null) {
            m_listener.onCurrentUnitsUpdated();
        }
    }

    public ArrayList< Pair<ConverterBase.eUnits, String> > getCurrConverterUnits() {
        return mCurrConverterUnits;
    }

    public ArrayList<Pair<Integer, String>> getConvertersList() {
        ArrayList<Pair<Integer, String>> converters = new ArrayList<Pair<Integer, String>>();

        for (Map.Entry<Integer, ConverterBase> entry : mConverters.entrySet()) {
            converters.add(new Pair<Integer, String>(entry.getKey(), entry.getValue().getConverterType()));
        }

//        Iterator iterator = mConverters.entrySet().iterator();
//        while (iterator.hasNext()) {
//            iterator.next().
//            converters.add(new Pair<Integer, String>(mConverters.get(i).first, mConverters.get(i).second.getConverterType()));
//        }
//        for (int i = 0; i < mConverters.size(); ++i) {
//            converters.add(new Pair<Integer, String>(mConverters.get(i).first, mConverters.get(i).second.getConverterType()));
//        }

//        Pair<Integer, String> converterType = new Pair<Integer, String>(1, "Mass");

//        ArrayList<String> converters = new ArrayList<String>();
//        converters.add("Length");
//        converters.add("Converter 2");
//        converters.add("Converter 3");
//        converters.add(converterType.second);

        return converters;
    }

    public double convert(ConverterBase.eUnits from, ConverterBase.eUnits to, double arg) {
        return mCurrConverter.execute(from, to, arg);
    }
}
