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

package org.modelio.core.ui.nattable.parts.data.number.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.eclipse.nebula.widgets.nattable.util.ObjectUtils;
import org.modelio.core.ui.nattable.parts.data.number.date.DateDisplayConverter;
import org.modelio.core.ui.plugin.CoreUi;

/**
 * Data type converter for a Time editor.
 * <p>
 * Assumes that the data value is stored as a {@link String} to parse with a time formatter.
 * </p>
 */
@objid ("0ec10a4c-4552-447e-b776-cd2f4b4d7750")
public class TimeDisplayConverter extends DisplayConverter {
    @objid ("f3b2fa2d-12d1-420d-94ca-d827c7231a8f")
    private final DateFormat timeFormatter;

    /**
     * Convert {@link Date} to {@link String} using the default format from {@link SimpleDateFormat}
     */
    @objid ("b1f3cca1-d57c-4c01-85db-aafe06367f54")
    public TimeDisplayConverter() {
        this.timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.getDefault());
    }

    /**
     * Transform a canonical object of type <i>Date</i> to a string.
     */
    @objid ("3bfb028b-f244-4ae8-a9d6-91127148819e")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        try {
            if (ObjectUtils.isNotNull(canonicalValue)) {
                return this.timeFormatter.format(canonicalValue);
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
    @objid ("1f4f158b-a9f5-4b3d-8d7f-1beea79638a3")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        try {
            return this.timeFormatter.parse(displayValue.toString());
        } catch (@SuppressWarnings("unused") final Exception e) {
            // Ignore failed conversion
            return new Date();
        }
    }

}
