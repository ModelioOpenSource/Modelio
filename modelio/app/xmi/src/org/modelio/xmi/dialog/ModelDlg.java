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

@objid ("ac3aff23-fafd-482e-a596-19db90d0c753")
public class ModelDlg {
    @objid ("dbada06f-11a9-4a9a-a003-63fc1d4c92b7")
    private static final String BUNDLE_NAME = "org.modelio.xmi.dialog.Model";

    @objid ("1f8765c5-8110-4e02-858f-497eb1ca1e38")
    private static final ResourceBundle RESOURCE_BUNDLE; // = ResourceBundle

    @objid ("711932ab-7c42-4e74-9112-b1efad374faa")
    private ModelDlg() {
    }

    @objid ("da5bc434-98d6-4c25-b391-522e5d0a2b28")
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    @objid ("20c368bd-f0ab-4d03-857d-5de425a479bf")
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
