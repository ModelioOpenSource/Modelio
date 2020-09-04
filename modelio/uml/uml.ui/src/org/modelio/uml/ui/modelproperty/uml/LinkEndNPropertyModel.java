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
import org.modelio.core.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>NaryLinkEnd</i> data model.
 * <p>
 * This class provides the list of properties for the <i>NaryLinkEnd</i>
 * metaclass.
 */
@objid ("82527441-9f27-4777-938f-ad2b3ff8c297")
public class LinkEndNPropertyModel extends AbstractPropertyModel<NaryLinkEnd> {
    /**
     * Properties to display for <i>NaryLinkEnd</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for other rows the values usually match the meta-attributes and roles
     * names of the metaclass
     * </ul>
     */
    @objid ("aa3638ea-41e7-4529-9413-397d466fbbe4")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "LinkName",
			"Link.Base", "Linked", "Name", "MultiplicityMin", "MultiplicityMax", "IsOrdered", "IsUnique" };

    @objid ("c1229fb7-9d95-4d28-a3f0-b0de90bb0a40")
    private List<NaryLinkEnd> displayedRoles;

    @objid ("83f43145-2ea6-4a4e-9023-9406e01c8a85")
    private final NaryLink theLink;

    /**
     * Create a new <i>NaryLinkEnd</i> data model from an <i>NaryLinkEnd</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("bb15fb04-9c66-451f-a3e4-f366a034e1a9")
    public LinkEndNPropertyModel(NaryLinkEnd theEditedElement) {
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
    @objid ("57905db3-9788-4f45-b221-6edc68d223bf")
    @Override
    public int getColumnNumber() {
        return this.displayedRoles.size() + 1;
    }

    @objid ("942181f0-7648-4c40-8d5d-2d1f8975e056")
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

    @objid ("76a69a9e-26f9-4d2a-8c8c-aa98a97385d8")
    private Object getPropertyValue(int row, NaryLinkEnd aLinkEnd) {
        switch (row) {
        case 0: // Title
        
            Instance type = aLinkEnd.getSource();
        
            if (type == null) {
                return "";
            }
        
            return MessageFormat.format(MetamodelLabels.getString("Title.from"), type.getName());
        
        case 1:
            // Link name
            if (this.theLink == null) {
                return MetamodelLabels.getString("NaryLinkEnd.NoLink");
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
            Instance relatedInstance = aLinkEnd.getSource();
            return relatedInstance;
        
        case 4:
            return aLinkEnd.getName();
        case 5:
            return aLinkEnd.getMultiplicityMin();
        case 6:
            return aLinkEnd.getMultiplicityMax();
        case 7:
            return aLinkEnd.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
        case 8:
            return aLinkEnd.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
        default:
            return null;
        }
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("f280625f-bd73-4b97-9056-80c31461b387")
    @Override
    public int getRowsNumber() {
        return LinkEndNPropertyModel.PROPERTIES.length;
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
    @objid ("8d444b0e-49fd-40e5-8285-47dc8a08b350")
    private Object getValue(int row, int col) {
        if (row == 1 || row == 2) {
            return getLinkPropertyValue(row, col);
        }
        
        // NaryLinkEnd rows
        switch (col) {
        
        case 0: // col 0 is the property name
            if (row == 0) {
                return MessageFormat.format(MetamodelLabels.getString("Title.NaryLinkEnd"),
                        this.theEditedElement.getName());
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
    @objid ("bfd4d4d5-b01d-499a-8c44-3ea5614d6cc8")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        
        case 0: // col 0 is the property name
            return new DefaultStringNatValue((String) getValue(row, col), false);
        
        default:
            switch (row) {
            case 0: // Title
                return new DefaultStringNatValue((String) getValue(row, col), false);
        
            case 1: // Link name
                return new DefaultStringNatValue((String) getValue(row, col), false);
        
            case 2: // Link base Association
                if (col == 1) {
                    return new DefaultElementNatValue((MObject) getValue(row, col), true,
                            Collections.singletonList(NaryAssociation.class));
                }
                // else
                return new DefaultStringNatValue((String) getValue(row, col), false);
        
            case 3: // NaryLinkEnd Type
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Instance.class));
            case 4: // NaryLinkEnd Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
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
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            case 8:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        }
    }

    @objid ("75c1d29b-66d7-4bfb-a846-0df35eabb727")
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

    @objid ("7aac6b2e-bac1-4a7b-9273-d04fa9f7e886")
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
            linkEnd.setSource((Instance) value);
            break;
        case 4:
            linkEnd.setName(String.valueOf(value));
            break;
        case 5:
            linkEnd.setMultiplicityMin(String.valueOf(value));
            break;
        case 6:
            linkEnd.setMultiplicityMax(String.valueOf(value));
            break;
        case 7:
            linkEnd.setIsOrdered(((Boolean) value).booleanValue());
            break;
        case 8:
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
    @objid ("10384ebb-9b94-40d6-b151-2d65e935db10")
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
