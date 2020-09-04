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

package org.modelio.uml.ui.modelproperty.uml;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.core.ui.MetamodelLabels;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.core.ui.nattable.parts.data.javaenum.DefaultJavaEnumNatValue;
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * N-ary association property model.
 */
@objid ("b5203968-02c9-4fc5-b60b-9edc7671997d")
public class AssociationEndNPropertyModel extends AbstractPropertyModel<NaryAssociationEnd> {
    @objid ("abab16e5-93a5-4d33-8ef4-d1523fdfe921")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "AssociationName",
			"Class", "Role", "Visibility", "MultiplicityMin", "MultiplicityMax", "AccessMode", "IsAbstract", "IsClass",
			"IsOrdered", "IsUnique" };

    @objid ("d4df533e-f3c8-4916-936f-2c4c5df73b56")
    private List<NaryAssociationEnd> displayedRoles;

    @objid ("1cbee4de-0acb-4939-834f-46c9c16d90ae")
    private NaryAssociation theNaryAssociation;

    /**
     * Create a new <i>NaryAssociationEnd</i> data model from an
     * <i>NaryAssociationEnd</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("0862cce7-d0fc-4d21-9d8e-ffe3bce0f0c1")
    public AssociationEndNPropertyModel(NaryAssociationEnd theEditedElement) {
        super(theEditedElement);
        
        // Order the displayed roles as following:
        // - this role first for n-ary associations
        this.theNaryAssociation = theEditedElement.getNaryAssociation();
        if (this.theNaryAssociation != null) {
            final EList<NaryAssociationEnd> roles = this.theNaryAssociation.getNaryEnd();
            this.displayedRoles = new ArrayList<>(roles.size());
            this.displayedRoles.add(this.theEditedElement);
            for (NaryAssociationEnd r : roles) {
                if (!r.equals(this.theEditedElement)) {
                    this.displayedRoles.add(r);
                }
            }
        }
    }

    @objid ("d3d71681-ae4e-42e3-adf2-0afd743c9e38")
    @Override
    public int getColumnNumber() {
        return this.displayedRoles.size() + 1;
    }

    @objid ("7f390b5a-c0f5-489c-b5a3-08869b736d33")
    private Object getPropertyValue(int row, NaryAssociationEnd NaryAssociationEnd) {
        switch (row) {
        case 0: // Title
            Classifier type = NaryAssociationEnd.getOwner();
            if (type == null) {
                return "";
            } else {
                return MessageFormat.format(MetamodelLabels.getString("Title.from"), type.getName());
            }
        case 1:
            return this.theNaryAssociation.getName();
        case 2:
            // Type
            return NaryAssociationEnd.getOwner();
        case 3:
            // Role name
            return NaryAssociationEnd.getName();
        case 4:
            return NaryAssociationEnd.getVisibility();
        case 5:
            return NaryAssociationEnd.getMultiplicityMin();
        case 6:
            return NaryAssociationEnd.getMultiplicityMax();
        case 7:
            return NaryAssociationEnd.getChangeable();
        case 8:
            return NaryAssociationEnd.isIsAbstract() ? Boolean.TRUE : Boolean.FALSE;
        case 9:
            return NaryAssociationEnd.isIsClass() ? Boolean.TRUE : Boolean.FALSE;
        case 10:
            return NaryAssociationEnd.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
        case 11:
            return NaryAssociationEnd.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
        default:
            return null;
        }
    }

    @objid ("3cb01e37-f79f-4e5e-a892-af4cad0d09c8")
    @Override
    public int getRowsNumber() {
        return AssociationEndNPropertyModel.PROPERTIES.length;
    }

    @objid ("6072108a-e2fb-4081-9ac7-7839f920282f")
    private Object getValue(int row, int col) {
        if (row == 1) {
            // NaryAssociation name row
            if (col == 0) {
                return getPropertyI18n(PROPERTIES[row]);
            } else if (col == 1) {
                return this.theNaryAssociation.getName();
            } else {
                return "";
            }
        }
        
        switch (col) {
        case 0: // col 0 is the property name
            return getPropertyI18n(PROPERTIES[row]);
        default:
            return getPropertyValue(row, this.displayedRoles.get(col - 1));
        }
    }

    @objid ("ae3059de-c4b9-4093-a55a-400d072bb7b2")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        
        case 0: // col 0 is the property name
            return new DefaultStringNatValue((String) getValue(row, col), false);
        
        default:
            switch (row) {
            case 0: // Title
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1: // NaryAssociation name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2: // Role owner
                DefaultElementNatValue classifierType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Classifier.class));
                classifierType.setElementFilter(new ClassifierTypeFilter());
                return classifierType;
            case 3: // role name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), VisibilityMode.class);
            case 5:
                List<String> cardinalityMinValues = new ArrayList<>();
                cardinalityMinValues.add("0");
                cardinalityMinValues.add("1");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMinValues, true);
            case 6:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 7:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), KindOfAccess.class);
            case 8:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 9:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 10:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 11:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        }
    }

    @objid ("c7ece5e2-801f-4e9c-912c-beaa8ed8547f")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            // Labels
            return false;
        } else if (row == 1) {
            // NaryAssociation name line
            if (col == 1) {
                return this.theNaryAssociation.isModifiable();
            }
            // else
            return false;
        } else {
            // Other cells
            NaryAssociationEnd relatedEnd = this.displayedRoles.get(col - 1);
            if (!relatedEnd.isModifiable()) {
                return false;
            }
        }
        return true;
    }

    @objid ("b1506489-8788-4c8b-a8e0-de940ae00d5d")
    private void setPropertyValue(int row, NaryAssociationEnd NaryAssociationEnd, Object value) {
        switch (row) {
        case 0:
            return;
        case 1:
            if (this.theNaryAssociation != null) {
                this.theNaryAssociation.setName((String) value);
            }
            break;
        case 2:
            NaryAssociationEnd.setOwner((Classifier) value);
            break;
        case 3:
            NaryAssociationEnd.setName(String.valueOf(value));
            break;
        case 4:
            NaryAssociationEnd.setVisibility((VisibilityMode) value);
            break;
        case 5:
            NaryAssociationEnd.setMultiplicityMin(String.valueOf(value));
            break;
        case 6:
            NaryAssociationEnd.setMultiplicityMax(String.valueOf(value));
            break;
        case 7:
            NaryAssociationEnd.setChangeable((KindOfAccess) value);
            break;
        case 8:
            NaryAssociationEnd.setIsAbstract(((Boolean) value).booleanValue());
            break;
        case 9:
            NaryAssociationEnd.setIsClass(((Boolean) value).booleanValue());
            break;
        case 10:
            NaryAssociationEnd.setIsOrdered(((Boolean) value).booleanValue());
            break;
        case 11:
            NaryAssociationEnd.setIsUnique(((Boolean) value).booleanValue());
            break;
        default:
            return;
        }
    }

    @objid ("40419e03-2f73-41cb-8d66-a80f4435bbdf")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        
        case 0:
            return;
        default:
            setPropertyValue(row, this.displayedRoles.get(col - 1), value);
            return;
        }
    }

    @objid ("15d28cf9-0b4f-4093-8f4f-72653fe512f6")
    protected static class ClassifierTypeFilter implements IMObjectFilter {
        @objid ("5fa09ad7-1c0e-4e47-a5c7-c566e20af3de")
        @Override
        public boolean accept(MObject el) {
            Classifier type = (Classifier) el;
            
            if (type.getName().equals(PredefinedTypes.UNDEFINED_NAME)) {
                return false;
            } else if (type instanceof ModuleComponent) {
                return false;
            } else {
                return true;
            }
        }

    }

}
