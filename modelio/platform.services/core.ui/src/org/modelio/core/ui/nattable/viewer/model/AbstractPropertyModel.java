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

package org.modelio.core.ui.nattable.viewer.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.MetamodelLabels;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Generic abstract data model for the property view.
 * <p>
 * This class provides a field for the edited element, as well as a default implementation for {@link IPropertyModel#isEditable(int, int)}.
 * @param <T> the edited element's type.
 */
@objid ("8ed6e3d5-3339-495b-8fb0-b6947443520a")
public abstract class AbstractPropertyModel<T extends MObject> implements IPropertyModel<T> {
    /**
     * The <i>Element</i> that corresponds to this data model.
     */
    @objid ("5e0aff07-8492-4f0d-9e5b-c50025bda307")
    protected final T theEditedElement;

    @objid ("f6571b61-4931-4d9a-b1eb-9ba4b90c7652")
    protected static final String PROPERTY_ID = "Property";

    @objid ("e6e18080-7aa6-4a73-965c-9cf86841ace0")
    protected static final String VALUE_ID = "Value";

    @objid ("0ab7cdfa-429f-4f2e-97dd-bda7caddf02b")
    protected AbstractPropertyModel(T editedElement) {
        this.theEditedElement = editedElement;
    }

    @objid ("c55fb4c2-9fc3-4e9b-8ae9-c74cad069aaa")
    @Override
    public final T getEditedElement() {
        return this.theEditedElement;
    }

    /**
     * Basic implementation of isEditable.
     * @param row the row index from the table.
     * @param col the column index from the table.
     * @return <code>false</code> when col = 0 or the edited element is not modifiable.
     */
    @objid ("819a6853-8961-440c-be11-69ce043e92a7")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            return false;
        }
        return this.theEditedElement.isModifiable();
    }

    @objid ("63d68077-be7f-4d20-a434-c14d5366fa5d")
    protected String getPropertyI18n(String key) {
        return MetamodelLabels.getString(key);
    }

}
