/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9ebb820a-4122-4cd0-8557-77355fd681d3")
public class StringConverter {
    @objid ("f7ffa02e-4140-4a49-a3f0-15668fa5b3c3")
    private static boolean isFilterEnabled = false;

    @objid ("4d9f60fe-3db2-45c8-82c7-a99bd8931f23")
    public static boolean isFilterEnabled() {
        return isFilterEnabled;
    }

    @objid ("dd143858-88a0-455e-b742-73ac2f4e6011")
    public static void setFilterEnabled(boolean activateFilter) {
        isFilterEnabled = activateFilter;
    }

    @objid ("d5431ecc-6b65-4f01-b59d-5b150202e96f")
    public static String replaceAllSpaces(String inputString) {
        return inputString.replaceAll(" ", "");
    }

    @objid ("d1361a28-3e66-4365-b1d0-f64eee05728e")
    public static Boolean getBoolean(String value) {
        try {
            if (isFilterEnabled)
                value = replaceAllSpaces(value);
            return Boolean.valueOf(value);
        } catch (Exception e) {
            return null;
        }
        
    }

    @objid ("9344b4e1-bc16-4cdf-980f-06abe4d13f14")
    public static Integer getInteger(String value) {
        try {
            if (isFilterEnabled)
                value = replaceAllSpaces(value);
            return Integer.valueOf(value);
        } catch (Exception e) {
            return null;
        }
        
    }

    @objid ("3c171e41-23a4-4b75-8b9a-3147913569d7")
    public static Long getLong(String value) {
        try {
            if (isFilterEnabled)
                value = replaceAllSpaces(value);
            return Long.valueOf(value);
        } catch (Exception e) {
            return null;
        }
        
    }

    @objid ("a5b970d9-0faf-43c7-9558-f1e0d9d91352")
    public static String getString(long value) {
        try {
            return String.valueOf(value);
        } catch (Exception e) {
            return null;
        }
        
    }

}
