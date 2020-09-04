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

package org.modelio.vstore.exml.plugin;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * core.utils plugin class.
 */
@objid ("b6608515-463d-4e21-ad27-cdcf69674050")
public class VStoreExml {
    /**
     * Plugin ID.
     */
    @objid ("c83b7df8-f707-47a0-9d93-b687b0b5779e")
    public static final String PLUGIN_ID = "org.modelio.core.utils";

    /**
     * Translated messages bundle.
     */
    @objid ("dfb81334-92ea-4bdb-8ae2-8170faa76b14")
    public static final ResourceBundle I18N = ResourceBundle.getBundle("vstore_exml");

    /**
     * Get the translated formatted message.
     * @param key the message key
     * @param args arguments
     * @return the formatted message
     */
    @objid ("acfefc84-00a8-42e3-a348-66d9d9fea319")
    public static String getMessage(String key, Object... args) {
        String pattern;
        try {
            pattern = I18N.getString(key);
        } catch (MissingResourceException e) {
            Log.warning("No I18n message for '%s'", key);
            pattern = "!" + key + "!";
        }
        return MessageFormat.format(pattern, args);
    }

}
