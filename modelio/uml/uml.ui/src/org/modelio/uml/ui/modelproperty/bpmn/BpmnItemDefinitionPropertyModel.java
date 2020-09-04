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

package org.modelio.uml.ui.modelproperty.bpmn;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.objects.BpmnItemKind;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>BpmnItemDefinition</i> data model.
 * <p>
 * This class provides the list of properties for the <i>BpmnItemDefinition</i> metaclass.
 */
@objid ("97e230bd-b904-4561-9ab2-750d255bd4fc")
public class BpmnItemDefinitionPropertyModel extends AbstractPropertyModel<BpmnItemDefinition> {
    /**
     * Properties to display for <i>BpmnItemDefinition</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("b4464c39-9d57-4807-a344-c07a3e42a767")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "ItemKind", "Collection", "Reference" };

    @objid ("1e6b3592-9da4-4837-b275-377d9541fb98")
    private IMdaExpert mdaExpert;

    /**
     * Create a new <i>BpmnItemDefinition</i> data model from an <i>BpmnItemDefinition</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("474d915c-8baf-49a3-8aba-4760187c4dc8")
    public BpmnItemDefinitionPropertyModel(BpmnItemDefinition theEditedElement, IMdaExpert mdaExpert) {
        super(theEditedElement);
        this.mdaExpert = mdaExpert;
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("73fd2395-d991-4599-a5e8-d8a5d45aad56")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("52cb4006-c30a-4c61-8bc5-ec82922a75a9")
    @Override
    public int getRowsNumber() {
        return BpmnItemDefinitionPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("e390854d-8263-4c85-b7e2-fc205724a2de")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(BpmnItemDefinitionPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getItemKind();
            case 3:
                return this.theEditedElement.isIsCollection() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
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
    @objid ("5612bddb-1c40-477f-b859-d847a3422d03")
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
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), BpmnItemKind.class);
            case 3:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 4:
                List<Class<? extends MObject>> allowedTargets = this.mdaExpert.getPossibleTargetMetaclasses(Reference.MdaTypes.STEREOTYPE_ELT, this.theEditedElement.getMClass()).stream()
                        .map(mc -> mc.getJavaInterface())
                        .collect(Collectors.toList());
                DefaultElementNatValue elementNatValue = new DefaultElementNatValue((MObject) getValue(row, col), true, allowedTargets);
        
                MClass linkMetaclass = this.theEditedElement.getMClass().getMetamodel().getMClass(MethodologicalLink.class);
                elementNatValue.setElementFilter(new IMObjectFilter() {
                    @Override
                    public boolean accept(MObject element) {
                        return BpmnItemDefinitionPropertyModel.this.mdaExpert.canLink(Reference.MdaTypes.STEREOTYPE_ELT, linkMetaclass, BpmnItemDefinitionPropertyModel.this.theEditedElement, element);
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
    @objid ("c5835866-5637-4373-88e7-bcaadda6c153")
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
                this.theEditedElement.setItemKind((BpmnItemKind) value);
                break;
            case 3:
                this.theEditedElement.setIsCollection((Boolean) value);
                break;
            case 4:
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
