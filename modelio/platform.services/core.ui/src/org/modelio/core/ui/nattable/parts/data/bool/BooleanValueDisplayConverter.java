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

package org.modelio.core.ui.nattable.parts.data.bool;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultBooleanDisplayConverter;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;

/**
 * Data type converter for a Boolean editor.
 * <p>
 * Assumes that the data value is stored as a {@link IBooleanNatValue}.
 * </p>
 */
@objid ("25970944-2d6a-44e0-ac5e-0e2fdd217892")
public class BooleanValueDisplayConverter extends DefaultBooleanDisplayConverter {
    /**
     * Default c'tor.
     */
    @objid ("f003f3fe-9761-4a6d-b51f-1854035dbc96")
    public BooleanValueDisplayConverter() {
        super();
    }

    @objid ("950aa51a-ae50-4fa6-9801-a111d48c8ecb")
    @Override
    public Object canonicalToDisplayValue(ILayerCell cell, IConfigRegistry configRegistry, Object canonicalValue) {
        return canonicalToDisplayValue(canonicalValue);
    }

    /**
     * Transform a canonical object of type <i>INatValue</i> to a boolean.
     */
    @objid ("be5c7d1c-e6e6-4c4b-822a-2b3578e08c57")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        if (canonicalValue instanceof IBooleanNatValue) {
            return ((IBooleanNatValue) canonicalValue).getValue();
        } else {
            super.canonicalToDisplayValue(canonicalValue);
        }
        return canonicalValue;
    }

    @objid ("47b3f3a2-d9a9-4920-8b4b-e09644fb7ae6")
    @Override
    public Object displayToCanonicalValue(ILayerCell cell, IConfigRegistry configRegistry, Object displayValue) {
        return displayToCanonicalValue(displayValue);
    }

}
