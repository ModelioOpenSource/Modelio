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
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>ConnectorEnd</i> data model.
 * <p>
 * This class provides the list of properties for the <i>ConnectorEnd</i>
 * metaclass.
 */
@objid ("badb32b6-178c-49aa-8577-4a8daf57b9de")
public class ConnectorEndNPropertyModel extends AbstractPropertyModel<NaryConnectorEnd> {
    /**
     * Properties to display for <i>ConnectorEnd</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for other rows the values usually match the meta-attributes and roles
     * names of the metaclass
     * </ul>
     */
    @objid ("40757553-5813-4de1-bc3a-58b7c8843547")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "LinkName", "Base",
			"ConnectorRepresentedFeature", "Linked", "Name", "ConnectorEndRepresentedFeature", "MultiplicityMin",
			"MultiplicityMax", "IsOrdered", "IsUnique" };

    @objid ("ebdb2d4a-fa47-487d-af62-a78d1a465491")
    private List<NaryLinkEnd> displayedRoles;

    @objid ("da75bcf1-5964-46a5-90e0-2501bee80b5a")
    private final NaryLink theLink;

    /**
     * Create a new <i>ConnectorEnd</i> data model from an <i>ConnectorEnd</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("27961458-187b-4234-812a-1b8964b8ff3a")
    public ConnectorEndNPropertyModel(NaryConnectorEnd theEditedElement) {
        super(theEditedElement);
        
        this.theLink = theEditedElement.getNaryLink();
        
        // Order the displayed roles as following:
        // - this role first for n-ary associations
        // - other roles next
        if (this.theLink != null) {
            final EList<NaryLinkEnd> roles = this.theLink.getNaryLinkEnd();
            this.displayedRoles = new ArrayList<>(roles.size());
            this.displayedRoles.add(this.theEditedElement);
            for (NaryLinkEnd r : roles) {
                if (!r.equals(this.theEditedElement)) {
                    this.displayedRoles.add(r);
                }
            }
        }
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("a5c7293c-cc02-4d35-bafc-74265c47ca98")
    @Override
    public int getColumnNumber() {
        return this.displayedRoles.size() + 1;
    }

    @objid ("25db4adf-4846-4993-a7f5-bafb9b6c3438")
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
            return ""; // Link base Association
        }
    }

    @objid ("6158fb29-b2d5-4eeb-acf3-c4370172de57")
    private Object getPropertyValue(int row, NaryLinkEnd aConnectorEnd) {
        switch (row) {
        case 0: // Title
        
            Instance type = aConnectorEnd.getSource();
        
            if (type == null) {
                return "";
            }
        
            if (aConnectorEnd == this.theEditedElement) {
                return "To: " + type.getName();
            }
            // else
            return "From: " + type.getName();
        
        case 1:
            // Link name
            if (this.theLink == null) {
                return "<no link>";
            }
            // else
            return this.theLink.getName();
        
        case 2:
            // Link base association
            if (this.theLink == null) {
                return null;
            }
            // else
            return this.theLink.getModel();
        
        case 3:
            if (this.theLink instanceof NaryConnector) {
                return ((NaryConnector) this.theLink).getRepresentedFeature();
            }
            // else
            return null;
        
        case 4:
            Instance relatedInstance = aConnectorEnd.getSource();
            return relatedInstance;
        
        case 5:
            return aConnectorEnd.getName();
        case 6:
            if (aConnectorEnd instanceof NaryConnectorEnd) {
                return ((NaryConnector) ((NaryConnectorEnd) aConnectorEnd).getNaryLink()).getRepresentedFeature();
            }
            // else
            return null;
        case 7:
            return aConnectorEnd.getMultiplicityMin();
        case 8:
            return aConnectorEnd.getMultiplicityMax();
        case 9:
            return aConnectorEnd.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
        case 10:
            return aConnectorEnd.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
        default:
            return null;
        }
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("421a2ae6-312e-4404-879d-69b7ca301776")
    @Override
    public int getRowsNumber() {
        return PROPERTIES.length;
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
    @objid ("3b2e8dde-e30a-483d-9b16-c76fbcedaf80")
    private Object getValue(int row, int col) {
        if (row == 1 || row == 2 || row == 3) {
            return getLinkPropertyValue(row, col);
        }
        
        // ConnectorEnd rows
        switch (col) {
        
        case 0: // col 0 is the property name
            if (row == 0) {
                return this.theEditedElement.getName() + " link";
            }
            // else
            return getPropertyI18n(PROPERTIES[row]);
        
        default:
            return getPropertyValue(row, this.displayedRoles.get(col - 1));
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
    @objid ("30c7cd87-8cec-4bd8-80ca-4fb96446b5d0")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        
        case 0: // col 0 is the property name
            return new DefaultStringNatValue((String) getValue(row, col), false);
        
        default:
            switch (row) {
            case 0: // Title
                return new DefaultStringNatValue((String) getValue(row, col), false);
        
            case 1:
                // Link name
                return new DefaultStringNatValue((String) getValue(row, col), false);
        
            case 2: // Link base Association
                if (col == 1) {
                    return new DefaultElementNatValue((MObject) getValue(row, col), true,
                            Collections.singletonList(NaryAssociation.class));
                }
                // else
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 3:
                if (col == 1) {
                    List<java.lang.Class<? extends MObject>> connectorRepresentedFeatureValues = new ArrayList<>();
                    connectorRepresentedFeatureValues.add(Attribute.class);
                    connectorRepresentedFeatureValues.add(NaryAssociation.class);
                    connectorRepresentedFeatureValues.add(NaryLink.class);
                    return new DefaultElementNatValue((MObject) getValue(row, col), true, connectorRepresentedFeatureValues);
                }
                // else
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 4: // ConnectorEnd Type
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Instance.class));
            case 5: // ConnectorEnd Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 6:
                List<java.lang.Class<? extends MObject>> connectorEndRepresentedFeatureValues = new ArrayList<>();
                connectorEndRepresentedFeatureValues.add(Attribute.class);
                connectorEndRepresentedFeatureValues.add(AssociationEnd.class);
                connectorEndRepresentedFeatureValues.add(NaryConnectorEnd.class);
                return new DefaultElementNatValue((MObject) getValue(row, col), true, connectorEndRepresentedFeatureValues);
            case 7:
                List<String> cardinalityMinValues = new ArrayList<>();
                cardinalityMinValues.add("0");
                cardinalityMinValues.add("1");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMinValues, true);
            case 8:
                List<String> cardinalityMaxValues = new ArrayList<>();
                cardinalityMaxValues.add("1");
                cardinalityMaxValues.add("*");
                return new DefaultStringChoiceNatValue((String) getValue(row, col), true, cardinalityMaxValues, true);
            case 10:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 11:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        }
    }

    @objid ("2757ac03-c07a-4480-90e1-1b22016f3491")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            // Labels are not editable
            return false;
        } else if (row == 1 || row == 2) {
            // Link lines: only the first cell is editable
            if (col == 1) {
                if (this.theLink == null) {
                    return false;
                }
                if (!this.theLink.isModifiable()) {
                    return false;
                }
            } else {
                return false;
            }
        } else if (col == 1) {
            // Other cells
            NaryLinkEnd relatedEnd = this.displayedRoles.get(col - 1);
            if (!relatedEnd.isModifiable()) {
                return false;
            }
        }
        return true;
    }

    @objid ("be36a8d9-6488-48d0-a157-7acc08492eba")
    private void setPropertyValue(int row, NaryLinkEnd linkEnd, Object value) {
        switch (row) {
        case 0:
            return;
        case 1:
            if (this.theLink != null) {
                this.theLink.setName((String) value);
            }
            break;
        
        case 2:
            if (this.theLink != null) {
                this.theLink.setModel((NaryAssociation) value);
            }
            break;
        case 3:
            if (this.theLink instanceof NaryConnector) {
                ((NaryConnector) this.theLink).setRepresentedFeature((UmlModelElement) value);
            }
            break;
        case 4:
            linkEnd.setSource((Instance) value);
            break;
        case 5:
            linkEnd.setName(String.valueOf(value));
            break;
        case 6:
            if (linkEnd instanceof NaryConnectorEnd) {
                ((NaryConnector) ((NaryConnectorEnd) linkEnd).getNaryLink())
                        .setRepresentedFeature((UmlModelElement) value);
            }
            break;
        case 7:
            linkEnd.setMultiplicityMin(String.valueOf(value));
            break;
        case 8:
            linkEnd.setMultiplicityMax(String.valueOf(value));
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

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("33305053-b6ac-4253-abff-f17c6d8291a4")
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

}
