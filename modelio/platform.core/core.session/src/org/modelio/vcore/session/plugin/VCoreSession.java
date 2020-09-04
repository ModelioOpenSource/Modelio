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

package org.modelio.vcore.session.plugin;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * Represents the core.session plugin.
 * <p>
 * Cannot respect the <a href="http://forge.minotaure.softeam.com/projects/modelio-phoenix/wiki/Plugin_singleton">
 * typical plugin singleton pattern</a> because core plugins must not use OSGI.
 */
@objid ("ab43279b-6602-4f6b-9cd0-d3fa2b553644")
public class VCoreSession {
    /**
     * The plugin ID.
     */
    @objid ("eb554403-2612-4c95-b5d8-468f3157cf78")
    public static final String PLUGIN_ID = "org.modelio.core.session";

    /**
     * The resource bundle.
     * <p>
     * Use {@link #getMessage(String, Object...)} instead.
     */
    @objid ("395e5243-8ab6-4e38-8d16-8b2f7481506e")
    public static final ResourceBundle I18N = ResourceBundle.getBundle("coresession");

    /**
     * Get the translated formatted message.
     * @param key the message key
     * @param args arguments
     * @return the formatted message
     */
    @objid ("73fa79c2-ad18-4742-92b4-fa0d550bc233")
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
