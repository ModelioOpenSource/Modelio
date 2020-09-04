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

package org.modelio.core.ui.nattable.parts.data.element.single;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Data type converter for an Element editor.
 * <p>
 * Assumes that the data value is stored as a  {@link MObject} or {@link MRef}.
 * </p>
 */
@objid ("f388e6c6-3e1b-42f9-8254-bf56166694bd")
public class ElementDisplayConverter extends DisplayConverter {
    @objid ("c5b085fb-c5c7-49c6-b189-35ab207b065b")
    private final ILabelProvider labelProvider;

    @objid ("799215ce-1bd5-4229-ba35-c29bdee98dbd")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        if (canonicalValue instanceof MRef) {
            final MRef mRef = (MRef) canonicalValue;
            return mRef.name;
        } else if (canonicalValue instanceof MObject) {
            return this.labelProvider.getText(canonicalValue);
        } else {
            return "";
        }
    }

    @objid ("c399e14f-6955-40a6-ac07-79e151b599d3")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        return displayValue;
    }

    @objid ("64988d6b-48c7-4aac-afa7-ed4d05d911e3")
    public ElementDisplayConverter(ILabelProvider labelProvider) {
        this.labelProvider = Objects.requireNonNull(labelProvider);
    }

}
