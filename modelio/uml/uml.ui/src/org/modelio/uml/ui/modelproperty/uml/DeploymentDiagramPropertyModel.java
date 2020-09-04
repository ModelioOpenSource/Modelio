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
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>DeploymentDiagram</i> data model.
 * <p>
 * This class provides the list of properties for the <i>DeploymentDiagram</i>
 * metaclass.
 */
@objid ("bb99e01e-3c70-43e9-8fc7-524a240642b5")
public class DeploymentDiagramPropertyModel extends AbstractPropertyModel<DeploymentDiagram> {
    /**
     * Properties to display for <i>DeploymentDiagram</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("3974484f-368a-44cf-94c1-2861476f0ad8")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Context" };

    /**
     * Create a new <i>DeploymentDiagram</i> data model from an
     * <i>DeploymentDiagram</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("2c7ef75e-4877-4e54-b863-d448347224a5")
    public DeploymentDiagramPropertyModel(final DeploymentDiagram theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("80788884-1dee-4f2e-a2e6-a50ebb528f07")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("83c93e50-5554-427e-bf9f-61245972d946")
    @Override
    public int getRowsNumber() {
        return DeploymentDiagramPropertyModel.PROPERTIES.length;
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
    @objid ("4b0a6c90-8b78-4753-8e36-3bf8718863af")
    private Object getValue(int row, int col) {
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
    @objid ("6158d75e-e6c3-43fb-869b-0b626c71e2ad")
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
                List<java.lang.Class<? extends MObject>> allowedMetaclasses = new ArrayList<>();
                allowedMetaclasses.add(Artifact.class);
                allowedMetaclasses.add(Package.class);
                allowedMetaclasses.add(Class.class);
                allowedMetaclasses.add(Component.class);
                allowedMetaclasses.add(Node.class);
        
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
    @objid ("5c8eb1ab-8367-4ee5-a1eb-c10f2700ab7f")
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
