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

@objid ("55b26c21-2723-42ba-8a0b-8a7b74a95ee3")
public class GuiDlg {
    @objid ("d201d478-ec4e-4be5-84b1-c129b2b46d35")
    private static final String BUNDLE_NAME = "org.modelio.xmi.dialog.Gui";

    @objid ("07bd551c-e94f-4c3d-84d7-4c554f994b2d")
    private static final ResourceBundle RESOURCE_BUNDLE; // = ResourceBundle

    @objid ("1ab7662f-bec2-4f04-83d0-40cd4ded27f7")
    private GuiDlg() {
    }

    @objid ("d544ca5d-f7d9-4132-a97b-87f129afa2a4")
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    @objid ("a64f92f8-5320-43ed-b777-bdd11d078b28")
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
