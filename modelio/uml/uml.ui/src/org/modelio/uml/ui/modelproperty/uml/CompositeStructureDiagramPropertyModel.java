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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>CompositeStructureDiagram</i> data model.
 * <p>
 * This class provides the list of properties for the
 * <i>CompositeStructureDiagram</i> metaclass.
 */
@objid ("e8f57864-047c-4290-9c4c-509caaf56756")
public class CompositeStructureDiagramPropertyModel extends AbstractPropertyModel<CompositeStructureDiagram> {
    /**
     * Properties to display for <i>CompositeStructureDiagram</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("4b9fe8ac-624b-41b3-82f6-8348ac2dc55e")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Context" };

    /**
     * Create a new <i>CompositeStructureDiagram</i> data model from an
     * <i>CompositeStructureDiagram</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("717c0aa2-31e1-4068-924e-2c61f0f51c48")
    public CompositeStructureDiagramPropertyModel(final CompositeStructureDiagram theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("9e2ad9af-d85e-4fc3-866f-dbad1ce54a8e")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("70f864e3-cc2e-4433-b7d0-881ee1c1c00a")
    @Override
    public int getRowsNumber() {
        return CompositeStructureDiagramPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("407fdf8d-1450-40e0-83f6-53183835ad0f")
    private Object getValue(final int row, final int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getOrigin();
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
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("db02dcfe-4da2-4313-9fdc-ed3c4fc4caac")
    @Override
    public INatValue getValueAt(final int row, final int col) {
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
                List<java.lang.Class<? extends MObject>> allowedMetaclasses = new ArrayList<>();
                allowedMetaclasses.add(Artifact.class);
                allowedMetaclasses.add(BindableInstance.class);
                allowedMetaclasses.add(Class.class);
                allowedMetaclasses.add(Collaboration.class);
                allowedMetaclasses.add(Component.class);
                allowedMetaclasses.add(Instance.class);
                allowedMetaclasses.add(Node.class);
                allowedMetaclasses.add(Package.class);
        
                return new DefaultElementNatValue((MObject) getValue(row, col), true, allowedMetaclasses);
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
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("b9a7f2ff-a29d-4ca2-8b27-1863b5074be6")
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
                break;
            case 2:
                this.theEditedElement.setOrigin((ModelElement) value);
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
