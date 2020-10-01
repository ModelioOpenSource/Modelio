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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.platform.model.ui.MetamodelLabels;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.DefaultStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>LinkEnd</i> data model.
 * <p>
 * This class provides the list of properties for the <i>LinkEnd</i> metaclass.
 */
@objid ("64f792b6-1819-47d6-8e05-fd8ebd2b77c6")
public class LinkEnd2PropertyModel extends AbstractPropertyModel<LinkEnd> {
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
    @objid ("f637da7a-03fa-4f50-8ccf-cfa39fc0a22f")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID, "LinkName",
			"Link.Base", "Linked", "Name", "MultiplicityMin", "MultiplicityMax", "IsNavigable", "IsOrdered",
			"IsUnique" };

    /**
     * Create a new <i>LinkEnd</i> data model from an <i>LinkEnd</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("ca2c16a9-9e80-4d47-b400-743d8ae9b9b0")
    public LinkEnd2PropertyModel(LinkEnd theEditedElement) {
        super(theEditedElement);
    }

    @objid ("a6d3e2bc-f455-40f8-ab74-ce1a6f9b9dc8")
    private INatValue getBaseAssociationType(LinkEnd editedEnd) {
        return LinkUtils.getBaseAssociationType(editedEnd);
    }

    @objid ("7f858b36-6a7c-469d-91fd-1fbe8b59aa45")
    @Override
    public int getColumnNumber() {
        return 3;
    }

    @objid ("4809c67d-b323-48da-b17d-b9e5a4bc711e")
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
        }
        return "";
    }

    @objid ("d8fdb836-49ba-4eb5-b66d-063fc98dc399")
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
            return aLinkEnd.getLink().getName();
        case 2:
            return aLinkEnd.getModel();
        case 3:
            if (aLinkEnd != null) {
                Instance relatedInstance = aLinkEnd.getTarget();
                return relatedInstance;
            }
            // else
            return null;
        case 4:
            return aLinkEnd.getName();
        case 5:
            return aLinkEnd.getMultiplicityMin();
        case 6:
            return aLinkEnd.getMultiplicityMax();
        case 7:
            return aLinkEnd.isNavigable();
        case 8:
            return aLinkEnd.isIsOrdered() ? Boolean.TRUE : Boolean.FALSE;
        case 9:
            return aLinkEnd.isIsUnique() ? Boolean.TRUE : Boolean.FALSE;
        default:
            return null;
        }
    }

    @objid ("b1a68ce6-8d27-4501-a964-613250cf3b16")
    @Override
    public int getRowsNumber() {
        return LinkEnd2PropertyModel.PROPERTIES.length;
    }

    @objid ("d65a9363-84cb-4aa2-b1ab-c1162c94a0f0")
    private Object getValue(int row, int col) {
        if (row == 1) {
            return getLinkPropertyValue(row, col);
        }
        
        // LinkEnd rows
        switch (col) {
        
        case 0: // col 0 is the property name
            if (row == 0) {
                return MessageFormat.format(MetamodelLabels.getString("Title.LinkEnd"),
                        this.theEditedElement.getName());
            }
            // else
            return getPropertyI18n(PROPERTIES[row]);
        
        case 1:
            LinkEnd relatedEnd = this.theEditedElement.getOpposite();
            if (relatedEnd != null) {
                return getPropertyValue(row, relatedEnd);
            }
            // else
            return null;
        case 2:
            if (row == 1) {
                return ""; // Link name display only in the second column
            }
            return getPropertyValue(row, this.theEditedElement);
        default:
            return null;
        }
    }

    @objid ("1bfa24e5-1ad3-4919-bce3-dbe940ef3e8a")
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
            case 1: // Link Name
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 2: // Link base Association
                return getBaseAssociationType(this.theEditedElement);
            case 3: // LinkEnd Type
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(Instance.class));
            case 4: // LinkEnd Name
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
            case 9:
                return new DefaultBooleanNatValue((Boolean) getValue(row, col));
            default:
                return null;
            }
        default:
            return null;
        }
    }

    @objid ("fafb4ddf-fb47-4cdc-b2ae-59c676286cc3")
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
            if (row == 1) {
                return false; // Link name is only editable in the second column
            } else if (isApplicableCell(row, this.theEditedElement)) {
                return this.theEditedElement.isModifiable();
            }
        }
        return false;
    }

    @objid ("b9cfd057-51d3-41ef-aa21-64cedebe5c18")
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
            linkEnd.setMultiplicityMin(String.valueOf(value));
            break;
        case 6:
            linkEnd.setMultiplicityMax(String.valueOf(value));
            break;
        case 7:
            linkEnd.setNavigable((Boolean) value);
            break;
        case 8:
            linkEnd.setIsOrdered(((Boolean) value).booleanValue());
            break;
        case 9:
            linkEnd.setIsUnique(((Boolean) value).booleanValue());
            break;
        default:
            return;
        }
    }

    @objid ("a1eba032-9c49-4f29-a22b-256a90394596")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0:
            return;
        case 1:
            LinkEnd relatedEnd = this.theEditedElement.getOpposite();
            if (relatedEnd != null) {
                setPropertyValue(row, relatedEnd, value);
            }
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
    @objid ("87c92c8d-e600-4788-978a-6ce75521c0d1")
    private boolean isApplicableCell(int row, LinkEnd linkEnd) {
        return row <= 7 || linkEnd.isNavigable();
    }

}
