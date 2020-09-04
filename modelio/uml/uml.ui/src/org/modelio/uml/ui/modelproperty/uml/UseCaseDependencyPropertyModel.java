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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>UseCaseDependency</i> data model.
 * <p>
 * This class provides the list of properties for the <i>UseCaseDependency</i>
 * metaclass.
 */
@objid ("bb968f16-1e08-48e4-814c-986119d15416")
public class UseCaseDependencyPropertyModel extends AbstractPropertyModel<UseCaseDependency> {
    /**
     * Properties to display for <i>UseCaseDependency</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("3085eae0-76d0-4cd7-bed4-391cfca5798b")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Target",
			"ExtensionLocation" };

    /**
     * Create a new <i>UseCaseDependency</i> data model from an
     * <i>UseCaseDependency</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("0913b8f8-3c23-441f-a45b-e7c483036965")
    public UseCaseDependencyPropertyModel(UseCaseDependency theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     */
    @objid ("6f308368-dda0-4fe1-850f-eee24554db6c")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     */
    @objid ("99ff037a-c1cd-4fa6-ab5d-1db143c583a2")
    @Override
    public int getRowsNumber() {
        for (Stereotype stereo : this.theEditedElement.getExtension()) {
            if (stereo.getName().equals("extend")) {
                return 3;
            }
        }
        return 2;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     */
    @objid ("61cfda5f-2817-4387-9b0e-c5f4d0def829")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getTarget();
            case 2:
                return this.theEditedElement.getExtensionLocation();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * Return the type of the element displayed at the specified row and column.
     * <p>
     * This type will be used to choose an editor and a renderer for each cell
     * of the properties table.
     * <p>
     * The first column contains the properties names.
     */
    @objid ("6d844656-bd37-4cc3-b1c8-ef87e6c40ad3")
    @SuppressWarnings("unchecked")
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
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(UseCase.class));
            case 2:
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true,
                        Collections.singletonList(ExtensionPoint.class));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     */
    @objid ("148160b8-67ff-4458-97c8-b97ecdfbed37")
    @Override
    @SuppressWarnings("unchecked")
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                this.theEditedElement.setTarget((UseCase) value);
                break;
            case 2:
                for (ExtensionPoint e : new ArrayList<>(this.theEditedElement.getExtensionLocation())) {
                    this.theEditedElement.getExtensionLocation().remove(e);
                }
        
                List<ExtensionPoint> l = (List<ExtensionPoint>) value;
                for (ExtensionPoint e : l) {
                    this.theEditedElement.getExtensionLocation().add(e);
                }
                break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

}
