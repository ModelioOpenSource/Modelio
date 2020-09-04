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
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Binary association property model.
 */
@objid ("c0b5e1ad-a02f-4e37-8e59-b6275e156bbd")
public class AssociationEnd2PropertyModel extends AbstractPropertyModel<AssociationEnd> {
    @objid ("c6fe8d74-2fae-4604-9aef-c43c8bb20118")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "AssociationName",
            "IsNavigable", "RoleName", "RoleTarget", "AssociationType", "MultiplicityMin", "MultiplicityMax",
            "Visibility", "IsModifiable", "AccessMode", "IsAbstract", "IsClass", "IsOrdered", "IsUnique" };

    /**
     * Create a new <i>AssociationEnd</i> data model from an <i>AssociationEnd</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("3e51f67b-e5b3-4d14-a73c-daeef56b4963")
    public AssociationEnd2PropertyModel(AssociationEnd theEditedElement) {
        super(theEditedElement);
    }

    @objid ("5b90001d-3d80-41c0-8b85-7211610724f2")
    @Override
    public int getColumnNumber() {
        return 3;
    }

    @objid ("91d13ef8-286b-4f58-9ad5-50bcffb8118b")
    private Object getPropertyValue(int row, AssociationEnd associationEnd) {
        // Broken model
        if (associationEnd == null) {
            if (row == 0) {
                return "<missing role>";
            } else {
                return null;
            }
        }
        
        // Default value for non editable cells
        if (!isApplicableCell(row, associationEnd)) {
            return "N/A";
        }
        
        switch (row) {
        case 0: // Title
            Classifier type = associationEnd.getTarget() != null ? associationEnd.getTarget()
                    : associationEnd.getOpposite() != null ? associationEnd.getOpposite().getSource()
                            : null;
        
            if (type != null) {
                if (associationEnd == this.theEditedElement) {
                    return MessageFormat.format(MetamodelLabels.getString("Title.to"), type.getName());
                } else {
                    return MessageFormat.format(MetamodelLabels.getString("Title.from"), type.getName());
                }
            } else {
                type = associationEnd.getSource();
                if (type != null) {
                    return MessageFormat.format("broken " + MetamodelLabels.getString("Title.from"), type.getName());
                }
        
            }
            return "";
        case 1:
            final Association association = associationEnd.getAssociation();
            if (association != null) {
                return association.getName();
            } else {
                return "<null>";
            }
        case 2:
            return associationEnd.isNavigable();
        case 3:
            return associationEnd.getName();
        case 4:
            return associationEnd.getTarget();
        case 5:
            return associationEnd.getAggregation();
        case 6:
            return associationEnd.getMultiplicityMin();
        case 7:
            return associationEnd.getMultiplicityMax();
        case 8:
            return associationEnd.getVisibility();
        case 9:
            return associationEnd.isIsChangeable() ? Boolean.TRUE : Boolean.FALSE;
        case 10:
            return associationEnd.getChangeable();
        case 11:
            return associationEnd.isIsAbstract() ? Boolean.TRUE : Boolean.FALSE;
        case 12:
            return associationEnd.isIsClass() ? Boolean.TRUE : Boolean.FALSE;
        case 13:
            return associationEnd.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
        case 14:
            return associationEnd.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
        default:
            return null;
        }
    }

    @objid ("57e62c17-43f2-4c20-abf2-b1e989ee65c7")
    @Override
    public int getRowsNumber() {
        return AssociationEnd2PropertyModel.PROPERTIES.length;
    }

    @objid ("8b259018-30b0-4ad6-beef-9450cc6e654d")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property name
        
            return getPropertyI18n(AssociationEnd2PropertyModel.PROPERTIES[row]);
        case 1:
            return getPropertyValue(row, this.theEditedElement.getOpposite());
        case 2:
            if (row == 1) {
                return ""; // Association name display only in the second column
            }
            return getPropertyValue(row, this.theEditedElement);
        default:
            return null;
        }
    }

    @objid ("3b5d50d5-abb3-4d07-80a7-091e7efc7871")
    @Override
    public INatValue getValueAt(int row, int col) {
        // Non editable case
        if ((col == 1 && !isApplicableCell(row, this.theEditedElement.getOpposite()))
                || (col == 2 && !isApplicableCell(row, this.theEditedElement))) {
            return new DefaultStringNatValue((String) getValue(row, col), false);
        }
        
        // Get the standard types
        switch (col) {
        case 0: // col 0 is the property name
            return new DefaultStringNatValue((String) getValue(row, col), false);
        
        case 1:
        case 2:
            switch (row) {
            case 0: // Title
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1: // Association Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2: // Navigability
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 3: // Role Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4: // Type
                DefaultElementNatValue classifierType = new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Classifier.class));
                classifierType.setElementFilter(new ClassifierTypeFilter());
                return classifierType;
            case 5: // Kind
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), AggregationKind.class);
            case 6:
                List<String> cardinalityMinValues = new ArrayList<>();
                cardinalityMinValues.add("0");
                cardinalityMinValues.add("1");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMinValues, true);
            case 7:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 8:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), VisibilityMode.class);
            case 9:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 10:
                return new DefaultJavaEnumNatValue((Enum<?>) getValue(row, col), KindOfAccess.class);
            case 11:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 12:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 13:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 14:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("a2a55158-c3c2-4c97-b8a0-265367c394b2")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            // Labels are not editable
            return false;
        } else if (col == 1) {
            AssociationEnd oppositeEnd = this.theEditedElement.getOpposite();
            if (isApplicableCell(row, oppositeEnd)) {
                return oppositeEnd.isModifiable();
            }
        } else if (col == 2) {
            if (row == 1) {
                return false; // Association name is only editable in the second
                              // column
            } else if (isApplicableCell(row, this.theEditedElement)) {
                return this.theEditedElement.isModifiable();
            }
        }
        return false;
    }

    @objid ("458d5912-7e22-4275-be2b-f4ce4ca57a2b")
    private void setPropertyValue(int row, AssociationEnd associationEnd, Object value) {
        switch (row) {
        case 0:
            return;
        case 1:
            associationEnd.getAssociation().setName(String.valueOf(value));
            break;
        case 2:
            associationEnd.setNavigable((Boolean) value);
            break;
        case 3:
            associationEnd.setName(String.valueOf(value));
            break;
        case 4:
            associationEnd.setTarget((Classifier) value, true);
            break;
        case 5:
            associationEnd.setAggregation((AggregationKind) value);
            break;
        case 6:
            associationEnd.setMultiplicityMin(String.valueOf(value));
            break;
        case 7:
            associationEnd.setMultiplicityMax(String.valueOf(value));
            break;
        case 8:
            associationEnd.setVisibility((VisibilityMode) value);
            break;
        case 9:
            associationEnd.setIsChangeable(((Boolean) value).booleanValue());
            break;
        case 10:
            associationEnd.setChangeable((KindOfAccess) value);
            break;
        case 11:
            associationEnd.setIsAbstract(((Boolean) value).booleanValue());
            break;
        case 12:
            associationEnd.setIsClass(((Boolean) value).booleanValue());
            break;
        case 13:
            associationEnd.setIsOrdered(((Boolean) value).booleanValue());
            break;
        case 14:
            associationEnd.setIsUnique(((Boolean) value).booleanValue());
            break;
        default:
            return;
        }
    }

    @objid ("e6d3573b-a759-47db-9045-48ee77d2f99f")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0:
            return;
        case 1:
            setPropertyValue(row, this.theEditedElement.getOpposite(), value);
            return;
        case 2:
            setPropertyValue(row, this.theEditedElement, value);
            return;
        default:
            return;
        }
    }

    /**
     * For a non navigable end, rows after 7 aren't applicable...
     */
    @objid ("4cd8d9e2-1279-4e00-b8bd-3c1f4bc09033")
    private boolean isApplicableCell(int row, AssociationEnd associationEnd) {
        return associationEnd != null && (row <= 7 || associationEnd.isNavigable());
    }

    @objid ("685b9062-0986-4f60-a05f-36ad4cf2b8c3")
    protected static class ClassifierTypeFilter implements IMObjectFilter {
        @objid ("acc03310-a382-4bb0-b610-289a12b92e64")
        @Override
        public boolean accept(MObject el) {
            Classifier type = (Classifier) el;
            
            if (type.getUuid().equals(PredefinedTypes.UNDEFINED_UID)) {
                return false;
            } else if (type instanceof ModuleComponent) {
                return false;
            } else {
                return true;
            }
        }

    }

}
