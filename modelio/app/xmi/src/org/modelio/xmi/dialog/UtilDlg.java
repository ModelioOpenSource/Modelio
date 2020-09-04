/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.xmi.dialog;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("4fc3cac7-3611-4f68-be41-0339b84e3fc6")
public class UtilDlg {
    @objid ("8fab95b1-0f57-40ab-a462-6ab63a82aeab")
    private static final String BUNDLE_NAME = "org.modelio.xmi.dialog.Util";

    @objid ("f579a771-802e-45ca-8bfb-4deb3a2c5dd3")
    private static final ResourceBundle RESOURCE_BUNDLE; // = ResourceBundle

    @objid ("ea812097-fde6-49f3-a76a-ecee335988dc")
    private UtilDlg() {
    }

    @objid ("171181b9-1025-4a15-903f-9b766d05b94a")
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    @objid ("70cbcb27-56a3-463e-8ee2-e22c4890135f")
    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }


static {
        String language;
        try {
            language = Locale.getDefault().getLanguage();
            if (!("fr".equalsIgnoreCase(language))) {
                language = "us";
            }
        } catch (Exception e) {
            // set default value
            language = "us"; 
        }

        RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(language));
    }
}
