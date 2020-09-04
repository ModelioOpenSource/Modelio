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

package org.modelio.xmi.dialog;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.xmi.plugin.Xmi;

@objid ("7222b301-4466-4b0e-a226-9d323aa03181")
public class MsgDlg {
    @objid ("6e4f0e37-b906-4a1c-845d-023960ddb802")
    private static final String BUNDLE_NAME = "org.modelio.xmi.dialog.Msg";

    @objid ("5d99ada1-3fab-4667-b0bf-010b7add512a")
    private static final ResourceBundle RESOURCE_BUNDLE;

    @objid ("6ebee680-9185-43df-b0c6-c910155cdef3")
    private MsgDlg() {
    }

    @objid ("41a357dc-8c19-44d6-95d2-d62c6f6d4670")
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, "bundle" + '!' + key + '!');
            return '!' + key + '!';
        }
    }

    @objid ("12dd5d52-98eb-4221-8ef0-3831d3265e99")
    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, "bundle" + '!' + key + '!');
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
