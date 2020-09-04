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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * PropertyDefinition model for {@link Stereotype}.
 */
@objid ("565401e7-93be-47ae-9d78-cc5bbe3f49c0")
public class StereotypePropertyModel extends AbstractPropertyModel<Stereotype> {
    @objid ("3c453377-fd1c-4190-9000-59ed853b76dc")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "Name", "Label",
	        "BaseClass", "IsHidden", "IsAbstract", "ParentStereotype", "Icon", "DiagramImage" };

    /**
     * Instantiate the stereotype properties view. element finder structure.
     * 
     * @param theEditedElement the current stereotype.
     */
    @objid ("5d692088-108a-4d04-aa45-5fd64f86e6b4")
    public StereotypePropertyModel(Stereotype theEditedElement) {
        super(theEditedElement);
    }

    /**
     * (non-Javadoc)
     * @see AbstractPropertyModel#getColumnNumber()
     */
    @objid ("7cb8192c-14a2-42ce-a3b1-711c3cb61a80")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * (non-Javadoc)
     * @see AbstractPropertyModel#getRowsNumber()
     */
    @objid ("6e55d915-85da-4799-93df-b626d8306dda")
    @Override
    public int getRowsNumber() {
        return StereotypePropertyModel.PROPERTIES.length;
    }

    /**
     * (non-Javadoc)
     * @see AbstractPropertyModel#getValueAt(int, int)
     */
    @objid ("dab6ddbb-0ada-4f52-b6a3-e998bf31f3f1")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(StereotypePropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return this.theEditedElement.getName();
            case 2:
                return this.theEditedElement.getLabelKey();
            case 3:
                return this.theEditedElement.getBaseClassName();
            case 4:
                return Boolean.valueOf(this.theEditedElement.isIsHidden());
            case 5:
                return Boolean.valueOf(this.theEditedElement.isIsAbstract());
            case 6:
                return this.theEditedElement.getParent();
            case 7:
                return this.theEditedElement.getIcon();
            case 8:
                return this.theEditedElement.getImage();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see AbstractPropertyModel#getValueAt(int, int)
     */
    @objid ("2458b0bf-5e64-4151-a7cb-b46b9c3d03bb")
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
                List<String> metaclasses = new ArrayList<>();
        
                // Get all metaclasses inheriting ModelElement
                for (MClass metaclass : this.theEditedElement.getMClass().getMetamodel().getMClass(ModelElement.class)
                        .getSub(true)) {
                    metaclasses.add(metaclass.getQualifiedName());
                }
        
                // Remove a few specific metaclass we do not want to appear
                metaclasses.remove(ModuleComponent.MQNAME);
                metaclasses.remove(Profile.MQNAME);
                metaclasses.remove(Project.MQNAME);
                metaclasses.remove(TagType.MQNAME);
                metaclasses.remove(NoteType.MQNAME);
                metaclasses.remove(Stereotype.MQNAME);
                metaclasses.remove(ModuleParameter.MQNAME);
        
                Collections.sort(metaclasses);
        
                return new DefaultStringChoiceNatValue((String) getValue(row, col), false, metaclasses, false);
            case 4:
            case 5:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 6:
                return new DefaultElementNatValue((MObject) getValue(row, col), true, Collections.singletonList(Stereotype.class));
            case 7:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 8:
                return new DefaultStringNatValue((String) getValue(row, col), false);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * (non-Javadoc)
     * @see AbstractPropertyModel#setValueAt(int, int, java.lang.Object)
     */
    @objid ("0d126d2b-5b28-41dc-a81e-1c5c76bdbb8c")
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
                this.theEditedElement.setBaseClassName((String) value);
                return;
            case 4:
                this.theEditedElement.setIsHidden(((Boolean) value).booleanValue());
                return;
            case 5:
                this.theEditedElement.setIsAbstract(((Boolean) value).booleanValue());
                return;
            case 6:
                this.theEditedElement.setParent((Stereotype) value);
                return;
            case 7:
                this.theEditedElement.setIcon((String) value);
                return;
            case 8:
                this.theEditedElement.setImage((String) value);
                return;
            default:
                return;
            }
        default:
            return;
        }
    }

}
