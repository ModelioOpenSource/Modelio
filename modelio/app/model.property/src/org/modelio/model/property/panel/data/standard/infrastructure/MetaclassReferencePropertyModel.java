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

package org.modelio.model.property.panel.data.standard.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * <i>MetaclassReference</i> data model.
 * <p>
 * This class provides the list of properties for the <i>MetaclassReference</i>
 * metaclass.
 */
@objid ("7b6647e3-ec6f-4d96-8d36-c3698d019155")
public class MetaclassReferencePropertyModel extends AbstractPropertyModel<MetaclassReference> {
    @objid ("1ef36769-913e-4b4d-8476-bb122cd01f82")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "ReferencedClass" };

    /**
     * Instantiate the profile properties view.
     * 
     * @param theEditedElement the current profile.
     */
    @objid ("4726e9e1-e1d5-41e9-b74a-0f28bedee2fc")
    public MetaclassReferencePropertyModel(MetaclassReference theEditedElement) {
        super(theEditedElement);
    }

    @objid ("d7dbe5bd-e7f6-4589-8dad-0ef4d2bc7e88")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("b90b25aa-c1eb-441d-95fd-e5e5d69c5382")
    @Override
    public int getRowsNumber() {
        return MetaclassReferencePropertyModel.PROPERTIES.length;
    }

    @objid ("4e6fb2cc-4651-45df-a8b2-7c0f5c91969d")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(MetaclassReferencePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getReferencedClassName();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("6720fe7f-5268-4d6d-b531-8088bb99129b")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
                List<String> metaclasses = new ArrayList<>();
        
                // Get all metaclasses inheriting Element
                for (MClass metaclass : this.theEditedElement.getMClass().getMetamodel().getMClass(Element.class).getSub(true)) {
                    metaclasses.add(metaclass.getQualifiedName());
                }
        
                Collections.sort(metaclasses);
        
                return new DefaultStringChoiceNatValue((String) getValue(row, col), false, metaclasses, false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("ebe8e341-aa0b-4030-b84f-933b53660599")
    @Override
    public boolean isEditable(int row, int col) {
        return super.isEditable(row, col);
    }

    @objid ("e3eeb687-3dba-4563-b10f-c25f99dbdeac")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                this.theEditedElement.setReferencedClassName((String) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}
