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

package org.modelio.uml.ui.modelproperty.uml;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>Element</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Element</i> metaclass.
 * </p>
 * It might be used as a fallback when no specific property model exists for a
 * metaclass.
 */
@objid ("502561e6-a24d-4255-8ba7-347bb1688755")
public class EmptyDataModel extends AbstractPropertyModel<Element> {
    /**
     * @param theEditedElement the model to edit.
     */
    @objid ("0212aa16-2fdc-416e-9e53-3cbfae05013b")
    public EmptyDataModel(Element theEditedElement) {
        super(theEditedElement);
    }

    @objid ("fe1191b6-65c3-4044-99c1-8986c0c262fc")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("614e9bf7-a4ff-4570-bb2c-ab5c60e5b6cc")
    @Override
    public int getRowsNumber() {
        return 1;
    }

    @objid ("2ec42cc5-c114-45ce-ab1d-7e146e6e4960")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            switch (row) {
            case 0: // Header
                if (this.theEditedElement != null) {
                    return this.theEditedElement.getMClass().getName();
                }
                return "Null";
            default:
                return null;
            }
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("207f43be-cf5d-4d08-85b1-7a261ef0f90f")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        default:
            return null;
        }
    }

    @objid ("f409fee4-79b6-462a-9a7b-9ee84dc6649e")
    @Override
    public boolean isEditable(int row, int col) {
        return false;
    }

    @objid ("2a2cc7f4-04bb-4ff1-bad8-0e7065873a13")
    @Override
    public void setValueAt(int row, int col, Object value) {
        // Nothing to do.
    }

}
