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

package org.modelio.vbasic.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;

@objid ("cb5e7bf7-0fcd-4f7d-8637-084be0445433")
public class MessageBundle {
    @objid ("a9d5b4ed-e047-4c60-b71f-a8f60fd1ba2c")
    private final ResourceBundle resBundle;

    @objid ("3f12070c-7a49-416f-863d-b4d3df19e3d5")
    public MessageBundle(ResourceBundle resBundle) {
        this.resBundle = resBundle;
    }

    /**
     * Get the translated message formatted with {@link MessageFormat}.
     * <p>
     * If the message is missing return the message key and log the failure as a warning.
     * @param key the message key
     * @param args arguments
     * @return the formatted message
     */
    @objid ("253f7b09-918f-4059-ae56-d95c7bfaedb5")
    public String getMessage(String key, Object... args) {
        String pattern;
        try {
            pattern = this.resBundle.getString(key);
        } catch (@SuppressWarnings("unused") MissingResourceException e) {
            Log.warning("No I18n message for '%s' in '%s'", key, this.resBundle.getBaseBundleName());
            pattern = "!" + key + "!";
        }
        return MessageFormat.format(pattern, args);
    }

}
