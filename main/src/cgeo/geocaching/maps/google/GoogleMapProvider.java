package cgeo.geocaching.maps.google;

import cgeo.geocaching.R;
import cgeo.geocaching.cgeoapplication;
import cgeo.geocaching.maps.AbstractMapProvider;
import cgeo.geocaching.maps.AbstractMapSource;
import cgeo.geocaching.maps.interfaces.MapItemFactory;
import cgeo.geocaching.maps.interfaces.MapProvider;
import cgeo.geocaching.maps.interfaces.MapSource;

import com.google.android.maps.MapActivity;

import android.content.res.Resources;

public final class GoogleMapProvider extends AbstractMapProvider {

    public static final String GOOGLE_MAP_ID = "GOOGLE_MAP";
    public static final String GOOGLE_SATELLITE_ID = "GOOGLE_SATELLITE";
    private static GoogleMapProvider instance;

    private final MapItemFactory mapItemFactory;

    private GoogleMapProvider() {
        final Resources resources = cgeoapplication.getInstance().getResources();

        registerMapSource(new GoogleMapSource(this, resources.getString(R.string.map_source_google_map)));
        registerMapSource(new GoogleSatelliteSource(this, resources.getString(R.string.map_source_google_satellite)));

        mapItemFactory = new GoogleMapItemFactory();
    }

    public static GoogleMapProvider getInstance() {
        if (instance == null) {
            instance = new GoogleMapProvider();
        }
        return instance;
    }

    public static boolean isSatelliteSource(final MapSource mapSource) {
        return mapSource != null && mapSource instanceof GoogleSatelliteSource;
    }

    @Override
    public Class<? extends MapActivity> getMapClass() {
        return GoogleMapActivity.class;
    }

    @Override
    public int getMapViewId() {
        return R.id.map;
    }

    @Override
    public int getMapLayoutId() {
        return R.layout.map_google;
    }

    @Override
    public MapItemFactory getMapItemFactory() {
        return mapItemFactory;
    }

    @Override
    public boolean isSameActivity(final MapSource source1, final MapSource source2) {
        return true;
    }

    private static abstract class AbstractGoogleMapSource extends AbstractMapSource {

        public AbstractGoogleMapSource(final String id, final MapProvider mapProvider, final String name) {
            super(id, mapProvider, name);
        }

    }

    private static final class GoogleMapSource extends AbstractGoogleMapSource {

        public GoogleMapSource(final MapProvider mapProvider, final String name) {
            super(GOOGLE_MAP_ID, mapProvider, name);
        }

    }

    private static final class GoogleSatelliteSource extends AbstractGoogleMapSource {

        public GoogleSatelliteSource(MapProvider mapProvider, String name) {
            super(GOOGLE_SATELLITE_ID, mapProvider, name);
        }

    }

}
