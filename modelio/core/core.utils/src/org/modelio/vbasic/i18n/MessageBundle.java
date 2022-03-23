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
package org.modelio.vbasic.i18n;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

/**
 * Message internationalization service.
 */
@objid ("cb5e7bf7-0fcd-4f7d-8637-084be0445433")
public class MessageBundle {
    @objid ("a9d5b4ed-e047-4c60-b71f-a8f60fd1ba2c")
    private final ResourceBundle resBundle;

    @objid ("3f12070c-7a49-416f-863d-b4d3df19e3d5")
    public  MessageBundle(final ResourceBundle resBundle) {
        this.resBundle = resBundle;
    }

    /**
     * Get the translated message formatted with {@link MessageFormat}.
     * <p>
     * If the message is missing return the message key and log the failure as a warning.
     * </p>
     * @see MessageFormat
     * @param key the message key
     * @param args message parameters
     * @return the translated message
     */
    @objid ("253f7b09-918f-4059-ae56-d95c7bfaedb5")
    public String getMessage(final String key, final Object... args) {
        try {
            final String pattern = this.resBundle.getString(key);
            return MessageFormat.format(pattern, args);
        } catch (final MissingResourceException e) {
            logMissingMessage("Missing '" + key + "' message in " + this.resBundle);
            return "!" + key + "(" + Arrays.toString(args) + ")!";
        }
        
    }

    /**
     * Get a translated string.
     * <p>
     * If the message is missing return the message key and log the failure as a warning.
     * </p>
     * @param key the string key
     * @return the translated string.
     */
    @objid ("7e117658-c2b0-409d-9cc8-2da3bc1a9c56")
    public String getString(final String key) {
        try {
            return this.resBundle.getString(key);
        } catch (final MissingResourceException e) {
            logMissingMessage("Missing '" + key + "' message in " + this.resBundle);
            return "!" + key + "!";
        }
        
    }

    @objid ("9a2abdf0-e968-4ddf-a5b5-13a0a1ee8c08")
    protected void logMissingMessage(final String message) {
        Log.warning(message);
    }

    @objid ("3d1a9070-c67d-44ce-aaf8-438c9fbd037c")
    public boolean containsKey(final String key) {
        return this.resBundle.containsKey(key);
    }

}
