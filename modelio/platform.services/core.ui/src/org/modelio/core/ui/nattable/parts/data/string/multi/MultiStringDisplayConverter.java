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

package org.modelio.core.ui.nattable.parts.data.string.multi;

import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;

/**
 * Data type converter for a Multi-String editor.
 * <p>
 * Assumes that the data value is stored as a {@link List} of {@link Object},
 * and calls {@link Object#toString()} on each one of them.
 * </p>
 */
@objid ("65df3096-2d14-43f8-8a5b-19bec13d84fa")
public class MultiStringDisplayConverter extends DisplayConverter {
    @objid ("8bfede16-d8d8-43db-a4c4-ab80fefb50a5")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        final List<?> list = (List<?>) canonicalValue;
        if (list != null) {
            Iterator<?> it = list.iterator();
            if (! it.hasNext())
                return "";
        
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(it.next());
                if (it.hasNext())
                    sb.append(',').append(' ');
            } while (it.hasNext());
            return sb.toString();
        } else {
            return "";
        }
    }

    @objid ("be19cd41-0947-4d3e-824a-a663ef1a7296")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        return displayValue;
    }

}
