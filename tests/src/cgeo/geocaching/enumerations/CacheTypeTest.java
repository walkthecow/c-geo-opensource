package cgeo.geocaching.enumerations;

import android.test.AndroidTestCase;

public class CacheTypeTest extends AndroidTestCase {

    public static void testGetById() {
        assertEquals(CacheType.UNKNOWN, CacheType.getById(""));
        assertEquals(CacheType.UNKNOWN, CacheType.getById(null));
        assertEquals(CacheType.UNKNOWN, CacheType.getById("random garbage"));
        assertEquals(CacheType.WHERIGO, CacheType.getById("wherigo"));
    }

    public static void testGetByPattern() {
        assertEquals(CacheType.UNKNOWN, CacheType.getByPattern(""));
        assertEquals(CacheType.UNKNOWN, CacheType.getByPattern(null));
        assertEquals(CacheType.UNKNOWN, CacheType.getByPattern("random garbage"));
        assertEquals(CacheType.CITO, CacheType.getByPattern("cache in trash out event"));
    }

    public static void testGetByIdComplete() {
        for (CacheType type : CacheType.values()) {
            assertEquals(type, CacheType.getById(type.id));
            assertEquals(type, CacheType.getById(type.id.toLowerCase()));
            assertEquals(type, CacheType.getById(type.id.toUpperCase()));
        }
    }

    public static void testGetByPatternComplete() {
        for (CacheType type : CacheType.values()) {
            assertEquals(type, CacheType.getByPattern(type.pattern));
            assertEquals(type, CacheType.getByPattern(type.pattern.toLowerCase()));
            assertEquals(type, CacheType.getByPattern(type.pattern.toUpperCase()));
        }
    }
}
