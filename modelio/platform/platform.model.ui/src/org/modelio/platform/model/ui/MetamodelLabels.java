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

package org.modelio.platform.model.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Message class for the metamodel labels.
 * See the 'metamodel_labels.properties' resource file.
 */
@objid ("8d5694d0-c068-11e1-8c0a-002564c97630")
public class MetamodelLabels {
    @objid ("e258a9b8-cb6e-11e1-9165-002564c97630")
    protected static final ResourceBundle bundle = ResourceBundle.getBundle("metamodel_labels");

    /**
     * Gets an i18n string for the given key from the 'metamodel_labels' resource bundle
     * 
     * @param key the key to search in the resource bundle.
     * @return an 18n string.
     */
    @objid ("8d5694d1-c068-11e1-8c0a-002564c97630")
    public static String getString(String key) {
        String pattern;
        try {
            pattern = bundle.getString(key);
        } catch (final MissingResourceException e) {
            //CoreUi.LOG.warning("No metamodel label for '%s'", key);
            pattern = key;
        }
        return pattern;
    }

}
