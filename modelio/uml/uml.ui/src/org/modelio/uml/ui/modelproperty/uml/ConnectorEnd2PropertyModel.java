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
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ConnectorEnd</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ConnectorEnd</i>
 * metaclass.
 */
@objid ("517e627c-79d4-45c5-8fbe-a94377f27adf")
public class ConnectorEnd2PropertyModel extends AbstractPropertyModel<ConnectorEnd> {
    /**
     * Properties to display for <i>LinkEnd</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for other rows the values usually match the meta-attributes and roles
     * names of the metaclass
     * </ul>
     */
    @objid ("34842d8b-d56c-4e85-811d-2291c343be17")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "LinkName", "Base",
			"Linked", "Name", "ConnectorEndRepresentedFeature", "MultiplicityMin", "MultiplicityMax", "IsNavigable",
			"IsOrdered", "IsUnique" };

    /**
     * Create a new <i>LinkEnd</i> data model from an <i>LinkEnd</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("2a85870d-8ebd-421b-8e73-f7204fbe5680")
    public ConnectorEnd2PropertyModel(ConnectorEnd theEditedElement) {
        super(theEditedElement);
    }

    @objid ("ec2edccf-fcb8-4030-8ded-7ee518a844c4")
    private INatValue getBaseAssociationType(LinkEnd editedEnd) {
        return LinkUtils.getBaseAssociationType(editedEnd);
    }

    @objid ("40706a8d-7990-4261-9889-7ac41783b33d")
    @Override
    public int getColumnNumber() {
        return 3;
    }

    @objid ("54704d05-d95c-4643-92da-b128250efa61")
    private Object getLinkPropertyValue(int row, int col) {
        // Link rows
        if (col == 0) {
            return getPropertyI18n(PROPERTIES[row]);
        } else if (col == 1) {
            return getPropertyValue(row, this.theEditedElement);
        } else {
            if (row == 1) {
                return ""; // Link name
            }
            // else
            return null; // Link base Association
        }
    }

    @objid ("5f5d159a-6f38-4619-893a-29542cd5a00e")
    private Object getPropertyValue(int row, LinkEnd aLinkEnd) {
        // Default value for non editable cells
        if (!isApplicableCell(row, aLinkEnd)) {
            return "N/A";
        }
        
        switch (row) {
        case 0: // Title
            Instance type = aLinkEnd.getTarget() != null ? aLinkEnd.getTarget() : aLinkEnd.getOpposite().getSource();
            if (type != null) {
                if (aLinkEnd == this.theEditedElement) {
                    return MessageFormat.format(MetamodelLabels.getString("Title.to"), type.getName());
                } else {
                    return MessageFormat.format(MetamodelLabels.getString("Title.from"), type.getName());
                }
            }
            return "";
        case 1:
            // Link name
            return aLinkEnd.getLink().getName();
        case 2:
            // Link base association
            return aLinkEnd.getModel();
        case 3:
            return aLinkEnd.getTarget();
        case 4:
            return aLinkEnd.getName();
        case 5:
            return ((ConnectorEnd) aLinkEnd).getRepresentedFeature();
        case 6:
            return aLinkEnd.getMultiplicityMin();
        case 7:
            return aLinkEnd.getMultiplicityMax();
        case 8:
            return aLinkEnd.isNavigable();
        case 9:
            return aLinkEnd.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
        case 10:
            return aLinkEnd.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
        default:
            return null;
        }
    }

    @objid ("ba2920fc-bb72-4a5c-98f9-777488a73237")
    @Override
    public int getRowsNumber() {
        return PROPERTIES.length;
    }

    @objid ("d59d5579-dd7d-4203-9dd1-1fbea2b8ea3e")
    private Object getValue(int row, int col) {
        if (row == 1 || row == 2) {
            return getLinkPropertyValue(row, col);
        }
        
        // LinkEnd rows
        switch (col) {
        
        case 0: // col 0 is the property name
            if (row == 0) {
                return this.theEditedElement.getName() + " association";
            }
            // else
            return getPropertyI18n(PROPERTIES[row]);
        
        case 1:
            return getPropertyValue(row, this.theEditedElement.getOpposite());
        
        case 2:
            return getPropertyValue(row, this.theEditedElement);
        
        default:
            return null;
        }
    }

    @objid ("c084db3b-b595-4c13-98b9-417b710f7735")
    @Override
    public INatValue getValueAt(int row, int col) {
        // Non editable case
        if ((col == 1 && !isApplicableCell(row, this.theEditedElement.getOpposite()))
                || (col == 2 && !isApplicableCell(row, this.theEditedElement))) {
            return new DefaultStringNatValue((String) getValue(row, col), false);
        }
        switch (col) {
        
        case 0: // col 0 is the property name
            return new DefaultStringNatValue((String) getValue(row, col), false);
        
        case 1:
            switch (row) {
            case 2: // Link base Association
                LinkEnd relatedEnd = this.theEditedElement.getOpposite();
                return getBaseAssociationType(relatedEnd);
            default:
            }
            //$FALL-THROUGH$
        case 2:
            switch (row) {
            case 0: // Title
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
                // Link name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2: // Link base Association
                return getBaseAssociationType(this.theEditedElement);
            case 3: // LinkEnd Type
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Instance.class));
            case 4: // LinkEnd Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 5:
                List<java.lang.Class<? extends MObject>> connectorEndRepresentedFeatureValues = new ArrayList<>();
                connectorEndRepresentedFeatureValues.add(Attribute.class);
                connectorEndRepresentedFeatureValues.add(AssociationEnd.class);
                connectorEndRepresentedFeatureValues.add(LinkEnd.class);
                return new DefaultElementNatValue((MObject) getValue(row, col), true, connectorEndRepresentedFeatureValues);
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
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 9:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 10:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("d0c19d1d-2d65-4faf-a861-d618b087a47c")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            // Labels are not editable
            return false;
        } else if (col == 1) {
            LinkEnd oppositeEnd = this.theEditedElement.getOpposite();
            if (isApplicableCell(row, oppositeEnd)) {
                return oppositeEnd.isModifiable();
            }
        } else if (col == 2) {
            if (row == 1 || row == 2) {
                return false;
            } else if (isApplicableCell(row, this.theEditedElement)) {
                return this.theEditedElement.isModifiable();
            }
        }
        return false;
    }

    @objid ("6c0d29d0-347c-45db-8e81-95b80b2a7692")
    private void setPropertyValue(int row, LinkEnd linkEnd, Object value) {
        switch (row) {
        case 0:
            return;
        case 1:
            linkEnd.getLink().setName(String.valueOf(value));
            break;
        case 2:
            if (value == null) {
                linkEnd.setModel(null);
                linkEnd.getOpposite().setModel(null);
                linkEnd.getLink().setModel(null);
            } else {
                final AssociationEnd model = (AssociationEnd) value;
                linkEnd.setModel(model);
                linkEnd.getOpposite().setModel(model.getOpposite());
                linkEnd.getLink().setModel(model.getAssociation());
            }
            break;
        case 3:
            linkEnd.setTarget((Instance) value, true);
            break;
        case 4:
            linkEnd.setName(String.valueOf(value));
            break;
        case 5:
            ((ConnectorEnd) linkEnd).setRepresentedFeature((UmlModelElement) value);
            break;
        case 6:
            linkEnd.setMultiplicityMin(String.valueOf(value));
            break;
        case 7:
            linkEnd.setMultiplicityMax(String.valueOf(value));
            break;
        case 8:
            linkEnd.setNavigable((Boolean) value);
            break;
        case 9:
            linkEnd.setIsOrdered(((Boolean) value).booleanValue());
            break;
        case 10:
            linkEnd.setIsUnique(((Boolean) value).booleanValue());
            break;
        default:
            return;
        }
    }

    @objid ("6be899dc-716b-4986-aded-ae400d28cb22")
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
     * For a non navigable end, rows after 8 aren't applicable...
     */
    @objid ("f21361ac-3c0e-47ac-b6ba-1b1778a967ad")
    private boolean isApplicableCell(int row, LinkEnd linkEnd) {
        return row <= 8 || linkEnd.isNavigable();
    }

}
