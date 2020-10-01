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

package org.modelio.platform.utils.i18n;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.utils.log.writers.PluginLogger;

/**
 * Message translation service to use in Modelio plugins in order to obtain
 * internationalized strings.
 * <p>
 * This class method never fail : if a message is not found a warning is logged
 * and a fallback message is returned.
 */
@objid ("006d94da-9c03-1fcc-9f44-001ec947cd2a")
public class BundledMessages {
    @objid ("0006280e-9df5-1fcc-9f44-001ec947cd2a")
    protected ResourceBundle bundle;

    @objid ("a6795756-1dce-11e2-82de-002564c97630")
    protected PluginLogger pluginLogger;

    /**
     * @param pluginLogger the plugin logger used to log warnings
     * @param bundle the bundle where messages are stored
     */
    @objid ("00063984-9df5-1fcc-9f44-001ec947cd2a")
    public BundledMessages(final PluginLogger pluginLogger, final ResourceBundle bundle) {
        this.pluginLogger = pluginLogger;
        this.bundle = bundle;
    }

    /**
     * Gets an internationalized message for the given key and arguments from this plugin resource bundle.
     * <p>
     * The internationalized string is expected to use {@link MessageFormat} pattern.
     * @see MessageFormat
     * 
     * @param key The message key
     * @param arguments arguments for the message pattern
     * @return the internationalized message
     */
    @objid ("00066a08-9df5-1fcc-9f44-001ec947cd2a")
    public String getMessage(final String key, final Object... arguments) {
        String pattern;
        
        try {
            pattern = this.bundle.getString(key);
        } catch (MissingResourceException e) {
            this.pluginLogger.warning("No I18n message for '%s'", key);
            pattern = "!" + key + "!";
        }
        
        try {
            return MessageFormat.format(pattern, arguments);
        } catch (IllegalArgumentException e) {
            this.pluginLogger.warning("Bad I18n message for '%s'(%s):", key, Arrays.toString(arguments));
            this.pluginLogger.warning(e);
            return "!" + key + "(" + Arrays.toString(arguments) + "):"+e.getMessage()+"!";
        }
    }

    /**
     * Gets an internationalized string for the given key from this plugin resource bundle.
     * 
     * @param key The string key
     * @return The internationalized string
     */
    @objid ("0006aac2-9df5-1fcc-9f44-001ec947cd2a")
    public String getString(final String key) {
        String pattern;
        
        try {
            pattern = this.bundle.getString(key);
        } catch (MissingResourceException e) {
            this.pluginLogger.warning("No I18n message for '%s'", key);
            pattern = "!" + key + "!";
        }
        return pattern;
    }

    /**
     * Return the locale currently being used by this BundledMessages
     * 
     * @return the locale
     */
    @objid ("edf4007f-b609-48f6-ad81-dbc55ddc7bbe")
    public Locale getLocale() {
        return this.bundle.getLocale();
    }

}
