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

package org.modelio.xmi.dialog;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d7fbf169-d08f-4133-992a-091ad724bbdc")
public class ResourceDlg {
    @objid ("f839a84e-c644-4948-8bbe-e8cdf210696b")
    private static final String BUNDLE_NAME = "org.modelio.xmi.dialog.Resource";

    @objid ("6f13f643-f3e5-4cec-924d-222b701ef9c4")
    private static final ResourceBundle RESOURCE_BUNDLE;

    @objid ("5d969ae2-6bfa-4a8b-ad2f-191a02f284ab")
    private ResourceDlg() {
    }

    @objid ("dcbd1f39-0cf8-4ee3-86cd-2916e2e7f7a0")
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    @objid ("366cb819-6bc4-48a8-b1f1-e461394739aa")
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
