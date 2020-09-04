/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.sun.star.lib.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This class provides functionality for reading string values from the
 * Windows Registry. It requires no native library .
 * <p>
 * It uses private methods from {@link java.util.prefs.Preferences} by reflection.
 * @author <a href="http://stackoverflow.com/questions/62289/read-write-to-windows-registry-using-java">stackoverflow.com: read/write to Windows Registry using Java</a>
 */
@objid ("e9ad02a7-b9cd-4b93-b912-b9fc577796d9")
public class WinRegistry {
    @objid ("164b16b4-c65e-46f6-969b-9d84406e7aa7")
    public static final int HKEY_CURRENT_USER = 0x80000001;

    @objid ("a3fdc4c5-dda7-4365-aad5-8eb3f123e34c")
    public static final int HKEY_LOCAL_MACHINE = 0x80000002;

    @objid ("9c2ff143-3320-412f-93a8-e780265d092d")
    public static final int REG_SUCCESS = 0;

    @objid ("38494ad2-ce98-4a85-9368-febe0ee82112")
    public static final int REG_NOTFOUND = 2;

    @objid ("2ea3cd61-62c3-45f4-a916-eb84a79dbeb8")
    public static final int REG_ACCESSDENIED = 5;

    @objid ("d978e4cb-69f7-4d52-b244-ab44c4be9a51")
    private static final int KEY_ALL_ACCESS = 0xf003f;

    @objid ("32fb073c-ada9-4f37-a9d6-a29aa0d904b0")
    private static final int KEY_READ = 0x20019;

    @objid ("43d83eee-23d3-415a-bf03-c25a350105e6")
    private static Preferences userRoot = Preferences.userRoot();

    @objid ("ba530fac-f886-4629-8769-8486d1ec9e18")
    private static Preferences systemRoot = Preferences.systemRoot();

    @objid ("b0b36504-daf5-4090-a25d-a781473a8112")
    private static Method regOpenKey = null;

    @objid ("3eb8209b-f720-464b-8dbb-dbb44f3e5433")
    private static Method regCloseKey = null;

    @objid ("4b102860-53dd-4ff0-a0ec-1e6f9792e8d7")
    private static Method regQueryValueEx = null;

    @objid ("465e630f-9d47-4985-8e97-a679b629056b")
    private static Method regEnumValue = null;

    @objid ("66fe0612-cf1b-4a27-9a09-1254cabea445")
    private static Method regQueryInfoKey = null;

    @objid ("4e86acf5-dd4f-416f-87a5-36308aa6a0a2")
    private static Method regEnumKeyEx = null;

    @objid ("71974c02-9e45-4049-9008-cb86be64a1e7")
    private static Method regCreateKeyEx = null;

    @objid ("b8c892c6-201c-484e-a09b-5c449becd33e")
    private static Method regSetValueEx = null;

    @objid ("443fcf0a-bc02-4b65-a991-0b068ea86ad6")
    private static Method regDeleteKey = null;

    @objid ("d69a583d-3f5b-43bd-af0e-c7cf4a5add6a")
    private static Method regDeleteValue = null;

    @objid ("fbdc7643-baac-4cab-850c-03e3f2d550c8")
    private static Class<? extends Preferences> userClass = userRoot.getClass();

    @objid ("10ba0134-4b7e-462c-ab73-7908f55dfc2f")
    private WinRegistry() {
    }

    /**
     * Read a value from key and value name
     * @param key
     * @param valueName
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * 
     * @param hkey HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
     * @return the value
     */
    @objid ("a36117b8-8ce9-4c15-8aa6-83ae032fbca1")
    public static String readString(int hkey, String key, String valueName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (hkey == HKEY_LOCAL_MACHINE) {
            return readString(systemRoot, hkey, key, valueName);
        } else if (hkey == HKEY_CURRENT_USER) {
            return readString(userRoot, hkey, key, valueName);
        } else {
            throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

    /**
     * Read value(s) and value name(s) form given key
     * @param key
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * 
     * @param hkey HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
     * @return the value name(s) plus the value(s)
     */
    @objid ("b460b944-7563-4b8e-9b4c-3e29639bf9c8")
    public static Map<String, String> readStringValues(int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (hkey == HKEY_LOCAL_MACHINE) {
            return readStringValues(systemRoot, hkey, key);
        } else if (hkey == HKEY_CURRENT_USER) {
            return readStringValues(userRoot, hkey, key);
        } else {
            throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

    /**
     * Read the value name(s) from a given key
     * @param key
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * 
     * @param hkey HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
     * @return the value name(s)
     */
    @objid ("8f34e668-8097-41d8-afd6-21284882806e")
    public static List<String> readStringSubKeys(int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (hkey == HKEY_LOCAL_MACHINE) {
            return readStringSubKeys(systemRoot, hkey, key);
        } else if (hkey == HKEY_CURRENT_USER) {
            return readStringSubKeys(userRoot, hkey, key);
        } else {
            throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

    /**
     * Create a key
     * @param key
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * 
     * @param hkey HKEY_CURRENT_USER/HKEY_LOCAL_MACHINE
     */
    @objid ("e6a7e733-e5e1-4bad-a279-4332ef3b09f3")
    public static void createKey(int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int[] ret;
        if (hkey == HKEY_LOCAL_MACHINE) {
            ret = createKey(systemRoot, hkey, key);
            regCloseKey.invoke(systemRoot, new Object[] { new Integer(ret[0]) });
        } else if (hkey == HKEY_CURRENT_USER) {
            ret = createKey(userRoot, hkey, key);
            regCloseKey.invoke(userRoot, new Object[] { new Integer(ret[0]) });
        } else {
            throw new IllegalArgumentException("hkey=" + hkey);
        }
        if (ret[1] != REG_SUCCESS) {
            throw new IllegalArgumentException("rc=" + ret[1] + "  key=" + key);
        }
    }

    /**
     * Write a value in a given key/value name
     * @param hkey
     * @param key
     * @param valueName
     * @param value
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @objid ("3133f11b-b092-471a-bb4f-4585ac2fd364")
    public static void writeStringValue(int hkey, String key, String valueName, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (hkey == HKEY_LOCAL_MACHINE) {
            writeStringValue(systemRoot, hkey, key, valueName, value);
        } else if (hkey == HKEY_CURRENT_USER) {
            writeStringValue(userRoot, hkey, key, valueName, value);
        } else {
            throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

    /**
     * Delete a given key
     * @param hkey
     * @param key
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @objid ("54d377fe-fc04-4c9b-9557-cfa568da586e")
    public static void deleteKey(int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int rc = -1;
        if (hkey == HKEY_LOCAL_MACHINE) {
            rc = deleteKey(systemRoot, hkey, key);
        } else if (hkey == HKEY_CURRENT_USER) {
            rc = deleteKey(userRoot, hkey, key);
        }
        if (rc != REG_SUCCESS) {
            throw new IllegalArgumentException("rc=" + rc + "  key=" + key);
        }
    }

    /**
     * delete a value from a given key/value name
     * @param hkey
     * @param key
     * @param value
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @objid ("2b32fdfe-2c5d-4eb7-83bf-041a6cf69355")
    public static void deleteValue(int hkey, String key, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int rc = -1;
        if (hkey == HKEY_LOCAL_MACHINE) {
            rc = deleteValue(systemRoot, hkey, key, value);
        } else if (hkey == HKEY_CURRENT_USER) {
            rc = deleteValue(userRoot, hkey, key, value);
        }
        if (rc != REG_SUCCESS) {
            throw new IllegalArgumentException("rc=" + rc + "  key=" + key + "  value=" + value);
        }
    }

// =====================
    @objid ("7cfeff5b-9a5e-478a-a80b-7964829e473f")
    private static int deleteValue(Preferences root, int hkey, String key, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[] { new Integer(hkey), toCstr(key),
                new Integer(KEY_ALL_ACCESS) });
        if (handles[1] != REG_SUCCESS) {
            return handles[1]; // can be REG_NOTFOUND, REG_ACCESSDENIED
        }
        int rc = ((Integer) regDeleteValue.invoke(root,
                                                  new Object[] { new Integer(handles[0]), toCstr(value) })).intValue();
        regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
        return rc;
    }

    @objid ("f58f9a66-b6a0-4d74-b4e8-10918ea89cb9")
    private static int deleteKey(Preferences root, int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int rc = ((Integer) regDeleteKey.invoke(root, new Object[] { new Integer(hkey), toCstr(key) })).intValue();
        return rc; // can REG_NOTFOUND, REG_ACCESSDENIED, REG_SUCCESS
    }

    @objid ("ad928444-42ac-4b19-87f9-a4dc2923e70a")
    private static String readString(Preferences root, int hkey, String key, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[] { new Integer(hkey), toCstr(key),
                new Integer(KEY_READ) });
        if (handles[1] != REG_SUCCESS) {
            return null;
        }
        byte[] valb = (byte[]) regQueryValueEx.invoke(root, new Object[] { new Integer(handles[0]),
                toCstr(value) });
        regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
        return (valb != null ? new String(valb).trim() : null);
    }

    @objid ("74746a35-b973-4b0d-8634-881f359da527")
    private static Map<String, String> readStringValues(Preferences root, int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HashMap<String, String> results = new HashMap<>();
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[] { new Integer(hkey), toCstr(key),
                new Integer(KEY_READ) });
        if (handles[1] != REG_SUCCESS) {
            return null;
        }
        int[] info = (int[]) regQueryInfoKey.invoke(root, new Object[] { new Integer(handles[0]) });
        
        int count = info[2]; // count  
        int maxlen = info[3]; // value length max
        for (int index = 0; index < count; index++) {
            byte[] name = (byte[]) regEnumValue.invoke(root, new Object[] { new Integer(handles[0]),
                    new Integer(index), new Integer(maxlen + 1) });
            String value = readString(hkey, key, new String(name));
            results.put(new String(name).trim(), value);
        }
        regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
        return results;
    }

    @objid ("6e21c715-ce2c-4359-8545-f196b55eddd4")
    private static List<String> readStringSubKeys(Preferences root, int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String> results = new ArrayList<>();
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[] { new Integer(hkey), toCstr(key),
                new Integer(KEY_READ) });
        if (handles[1] != REG_SUCCESS) {
            return null;
        }
        int[] info = (int[]) regQueryInfoKey.invoke(root, new Object[] { new Integer(handles[0]) });
        
        int count = info[2]; // count  
        int maxlen = info[3]; // value length max
        for (int index = 0; index < count; index++) {
            byte[] name = (byte[]) regEnumKeyEx.invoke(root, new Object[] { new Integer(handles[0]),
                    new Integer(index), new Integer(maxlen + 1) });
            results.add(new String(name).trim());
        }
        regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
        return results;
    }

    @objid ("69a5ffbe-939e-468d-ae92-06f6c05d944f")
    private static int[] createKey(Preferences root, int hkey, String key) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return (int[]) regCreateKeyEx.invoke(root, new Object[] { new Integer(hkey), toCstr(key) });
    }

    @objid ("58eed7a1-6446-40be-bf16-15d93e5e8927")
    private static void writeStringValue(Preferences root, int hkey, String key, String valueName, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[] { new Integer(hkey), toCstr(key),
                new Integer(KEY_ALL_ACCESS) });
        
        regSetValueEx.invoke(root, new Object[] { new Integer(handles[0]), toCstr(valueName), toCstr(value) });
        regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
    }

// utility
    @objid ("fbb4f73e-cb07-4f9e-98e3-3a17a811463d")
    private static byte[] toCstr(String str) {
        byte[] result = new byte[str.length() + 1];
        
        for (int i = 0; i < str.length(); i++) {
            result[i] = (byte) str.charAt(i);
        }
        result[str.length()] = 0;
        return result;
    }


static {
        try {
            regOpenKey = userClass.getDeclaredMethod("WindowsRegOpenKey", new Class[] { int.class,
                    byte[].class, int.class });
            regOpenKey.setAccessible(true);
            regCloseKey = userClass.getDeclaredMethod("WindowsRegCloseKey", new Class[] { int.class });
            regCloseKey.setAccessible(true);
            regQueryValueEx = userClass.getDeclaredMethod("WindowsRegQueryValueEx", new Class[] { int.class,
                    byte[].class });
            regQueryValueEx.setAccessible(true);
            regEnumValue = userClass.getDeclaredMethod("WindowsRegEnumValue", new Class[] { int.class,
                    int.class, int.class });
            regEnumValue.setAccessible(true);
            regQueryInfoKey = userClass.getDeclaredMethod("WindowsRegQueryInfoKey1",
                                                          new Class[] { int.class });
            regQueryInfoKey.setAccessible(true);
            regEnumKeyEx = userClass.getDeclaredMethod("WindowsRegEnumKeyEx", new Class[] { int.class,
                    int.class, int.class });
            regEnumKeyEx.setAccessible(true);
            regCreateKeyEx = userClass.getDeclaredMethod("WindowsRegCreateKeyEx", new Class[] { int.class,
                    byte[].class });
            regCreateKeyEx.setAccessible(true);
            regSetValueEx = userClass.getDeclaredMethod("WindowsRegSetValueEx", new Class[] { int.class,
                    byte[].class, byte[].class });
            regSetValueEx.setAccessible(true);
            regDeleteValue = userClass.getDeclaredMethod("WindowsRegDeleteValue", new Class[] { int.class,
                    byte[].class });
            regDeleteValue.setAccessible(true);
            regDeleteKey = userClass.getDeclaredMethod("WindowsRegDeleteKey", new Class[] { int.class,
                    byte[].class });
            regDeleteKey.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
