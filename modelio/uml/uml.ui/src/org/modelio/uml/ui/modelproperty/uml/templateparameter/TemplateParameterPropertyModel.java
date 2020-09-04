/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.uml.ui.modelproperty.uml.templateparameter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.metamodel.uml.statik.TemplateParameter;

/**
 * <i>TemplateParameter</i> data model.
 * <p>
 * This class provides the list of properties for the <i>TemplateParameter</i>
 * metaclass.
 * <p>
 * This data model has been manually moved and updated.
 * <p>
 * It delegates each call to one of the otheEditedElement property model class
 * dependeing on the template paraemter kind.
 */
@objid ("a28bb711-93c5-4452-ba3d-29b24309f671")
public class TemplateParameterPropertyModel extends AbstractPropertyModel<TemplateParameter> {
    /**
     * Specialized property model to which each call is delegated.
     */
    @objid ("a6872096-cf39-4930-9390-922e04ee8591")
    private IPropertyModel<TemplateParameter> delegate;

    /**
     * Create a new <i>TemplateParameter</i> data model from an
     * <i>TemplateParameter</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("ea7d69ae-3b0e-43f5-b392-ce0a593390d8")
    public TemplateParameterPropertyModel(TemplateParameter theEditedElement) {
        super(theEditedElement);
        this.initDelegate();
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("5429d367-3903-4695-9f39-aac28fa44cee")
    @Override
    public int getColumnNumber() {
        return this.delegate.getColumnNumber();
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("a273fa94-d9eb-4b92-b332-db497f0c243e")
    @Override
    public int getRowsNumber() {
        return this.delegate.getRowsNumber();
    }

    /**
     * Return the type of the element displayed at the specified row and column.
     * <p>
     * This type will be used to choose an editor and a renderer for each cell
     * of the properties table.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("2fc95dd0-768e-4897-a08c-a85a159fbbf5")
    @Override
    public INatValue getValueAt(int row, int col) {
        return row < this.delegate.getRowsNumber() ? this.delegate.getValueAt(row, col) : null;
    }

    /**
     * Initialize the delegate PropertyModel.
     * @param theEditedElement
     */
    @objid ("8780fa59-5ef1-4e91-abb5-337c2269c170")
    private void initDelegate() {
        if (this.theEditedElement.getOwnedParameterElement() != null) {
            this.delegate = new ElementTemplateParameterPropertyModel(this.theEditedElement);
        } else if (this.theEditedElement.isIsValueParameter()) {
            this.delegate = new ValueTemplateParameterPropertyModel(this.theEditedElement);
        } else {
            this.delegate = new TypeTemplateParameterPropertyModel(this.theEditedElement);
        }
    }

    @objid ("db00b0d8-4c99-4813-bed8-9dfe21fba27b")
    @Override
    public boolean isEditable(int row, int col) {
        return this.delegate.isEditable(row, col);
    }

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("8533671d-33a9-4afb-bd7e-b7b818d3b75a")
    @Override
    public void setValueAt(int row, int col, Object value) {
        this.delegate.setValueAt(row, col, value);
        initDelegate();
    }

}
