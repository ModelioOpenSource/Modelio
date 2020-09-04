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

package org.modelio.gproject.plugin;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * Represents the core.project plugin.
 * <p>
 * Cannot respect the <a href="http://forge.minotaure.softeam.com/projects/modelio-phoenix/wiki/Plugin_singleton">
 * typical plugin singleton pattern</a> because core plugins must not use osgi.
 */
@objid ("d1ccad8b-c9dd-11e1-96e9-001ec947ccaf")
public class CoreProject {
    @objid ("d4817b07-cb72-11e1-87f1-001ec947ccaf")
    public static final String PLUGIN_ID = "org.modelio.core.project";

    @objid ("d3a8861c-cb72-11e1-87f1-001ec947ccaf")
    public static final ResourceBundle I18N = ResourceBundle.getBundle("coreproject");

    /**
     * Get the translated formatted message.
     * @param key the message key
     * @param args arguments
     * @return the formatted message
     */
    @objid ("74101c36-cc3e-11e1-87f1-001ec947ccaf")
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
