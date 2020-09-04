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

package org.modelio.core.ui.nattable.parts.data.number.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.eclipse.nebula.widgets.nattable.util.ObjectUtils;
import org.modelio.core.ui.plugin.CoreUi;

/**
 * Data type converter for a Date editor.
 * <p>
 * Assumes that the data value is stored as a {@link String} to parse with a date formatter.
 * </p>
 */
@objid ("3ff329d1-330b-4426-9a2b-43a96980b10d")
public class DateDisplayConverter extends DisplayConverter {
    @objid ("40c060df-ba06-4183-bb3a-73d2a03f82a8")
    private final DateFormat dateFormatter;

    /**
     * Convert {@link Date} to {@link String} using the default format from {@link SimpleDateFormat}
     */
    @objid ("392f3056-5983-438e-ae34-889132407d82")
    public DateDisplayConverter() {
        this.dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
    }

    /**
     * Transform a canonical object of type <i>Date</i> to a string.
     */
    @objid ("63c2f478-e4e8-4660-b89d-b8a150c83f81")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        try {
            if (ObjectUtils.isNotNull(canonicalValue)) {
                return this.dateFormatter.format(canonicalValue);
            }
        } catch (final Exception e) {
            CoreUi.LOG.debug(e);
        }
        return canonicalValue;
    }

    /**
     * Transform a String to a <i>Date</i> canonical object. This method is guaranteed to be able to parse String produced by
     * {@link DateDisplayConverter#canonicalToDisplayValue(Object)}
     */
    @objid ("ed429508-9b93-4c86-8008-46b9ffbd1983")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        try {
            return this.dateFormatter.parse(displayValue.toString());
        } catch (@SuppressWarnings("unused") final Exception e) {
            // Ignore failed conversion
            return new Date();
        }
    }

}
