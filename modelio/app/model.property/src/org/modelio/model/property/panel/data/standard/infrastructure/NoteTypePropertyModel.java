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

package org.modelio.model.property.panel.data.standard.infrastructure;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.metamodel.uml.infrastructure.NoteType;

/**
 * <i>Note</i> data model.
 * <p>
 * This class provides the list of properties for the <i>Note</i> metaclass.
 */
@objid ("f1e61e6f-71cf-457c-957f-5cd40dcd830b")
public class NoteTypePropertyModel extends AbstractPropertyModel<NoteType> {
    @objid ("2cd8f78f-26c5-459d-86a8-c69f1b4dd4fc")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Label",
	        "IsHidden", "MimeType" };

    /**
     * Instantiate the note type properties view.
     * 
     * @param theEditedElement the current note type.
     */
    @objid ("265e2693-abcc-4d5d-ba7a-31663dcc3eac")
    public NoteTypePropertyModel(NoteType theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getColumnNumber()
     */
    @objid ("95150ae2-bccb-4590-aaaa-5422a50c7578")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getRowsNumber()
     */
    @objid ("265c2ff8-b50d-4dcd-967a-74deebdf66a3")
    @Override
    public int getRowsNumber() {
        return NoteTypePropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("aa0d1532-364c-49dc-bb46-1bb4ed84a929")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(NoteTypePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getLabelKey();
            case 3:
                return this.theEditedElement.isIsHidden() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return this.theEditedElement.getMimeType();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#getValueAt(int,
     * int)
     */
    @objid ("aa58e323-7f5e-4b04-bbed-f11e08c12b80")
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
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 4:
                return new DefaultStringChoiceNatValue((String) getValue(row, col), false,
                        Arrays.asList("text/plain", "text/html"), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see IPropertyModel#setValueAt(int,
     * int, java.lang.Object)
     */
    @objid ("c3584881-bcc9-4cdb-9d1e-229a8bb3e909")
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
                this.theEditedElement.setName((String) value);
                return;
            case 2:
                this.theEditedElement.setLabelKey((String) value);
                return;
            case 3:
                this.theEditedElement.setIsHidden(((Boolean) value).booleanValue());
                return;
            case 4:
                this.theEditedElement.setMimeType((String) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}
