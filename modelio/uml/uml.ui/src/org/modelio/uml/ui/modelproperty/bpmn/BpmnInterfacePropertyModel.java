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
package org.modelio.uml.ui.modelproperty.bpmn;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnInterface</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnInterface</i> metaclass.
 */
@objid ("1abfc417-c550-4e55-a244-53b40431f79d")
public class BpmnInterfacePropertyModel extends AbstractPropertyModel<BpmnInterface> {
    /**
     * Properties to display for <i>BpmnInterface</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("9b7ca48e-b3db-47d2-bb6d-15b7405dd714")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Reference" };

    @objid ("1528cb39-6878-417c-a732-685b0f1cd336")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnInterface</i> data model from an <i>BpmnInterface</i> .
     * @param theEditedElement the model to edit.
     * @param mdaExpert the MDA expert to handle Methodological links.
     */
    @objid ("a4180102-ca2d-4483-aea1-c4344d54b2b6")
    public  BpmnInterfacePropertyModel(BpmnInterface theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("8ab1b375-bc3e-4ecd-ba56-5599ef9919d1")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("826ef195-0598-40b1-8b27-28142657d24f")
    @Override
    public int getRowsNumber() {
        return BpmnInterfacePropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("a7d2fff2-88d7-4d4a-8aab-8df0bca45e7b")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnInterfacePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return Reference.getTarget(this.theEditedElement);
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
     * This type will be used to choose an editor and a renderer for each cell of the properties table.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("11ab85d9-1aa0-4cb8-af2d-2876878be29f")
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
                List<Class<? extends MObject>> allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(Reference.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                MClass linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnInterfacePropertyModel.this.mdaExpert.canLink(Reference.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnInterfacePropertyModel.this.theEditedElement, element);
                    }
                });
                return elementNatValue;
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
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("768405a5-fe59-421e-b18c-5f01664fb560")
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
                Reference.setTarget(this.theEditedElement, (ModelElement) value);
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
