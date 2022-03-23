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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.DefaultMultiElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>InformationFlow</i> data model.
 * <p>
 * This class provides the list of properties for the <i>InformationFlow</i>
 * metaclass.
 */
@objid ("10217f6c-2a98-47fb-96e5-9abf4058df21")
public class InformationFlowPropertyModel extends AbstractPropertyModel<InformationFlow> {
    /**
     * Properties to display for <i>InformationFlow</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("d4400923-2ffc-4821-81e4-2c55478496bd")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID,
                "Name",
                "Conveyed",
    			"InformationSource",
    			"InformationTarget",
                "Owner",
    			"Realizing" };

    /**
     * Create a new <i>InformationFlow</i> data model from an
     * <i>InformationFlow</i>.
     * @param theEditedElement the model to edit.
     */
    @objid ("1c409e1b-273c-4ab0-bc1f-a40dfc21c529")
    public  InformationFlowPropertyModel(InformationFlow theEditedElement) {
        super(theEditedElement);
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("bca31842-c97f-4f80-a4a8-55cd04e3964d")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * Returns the element realizing by the given InformationFlow node.
     * @return the realizing element
     */
    @objid ("b53e1e78-0bdb-4518-94ae-298f67ab87f9")
    private UmlModelElement getRealizing() {
        UmlModelElement ret = null;
        
        EList<StructuralFeature> featureList = this.theEditedElement.getRealizingFeature();
        if (featureList.size() > 0) {
            ret = featureList.get(0);
            if (ret != null) {
                return ret;
            }
        }
        
        EList<LinkEnd> linkList = this.theEditedElement.getRealizingLink();
        if (linkList.size() > 0) {
            ret = linkList.get(0);
            if (ret != null) {
                return ret;
            }
        }
        
        EList<ActivityEdge> edgeList = this.theEditedElement.getRealizingActivityEdge();
        if (edgeList.size() > 0) {
            ret = edgeList.get(0);
            if (ret != null) {
                return ret;
            }
        }
        
        EList<Message> messageList = this.theEditedElement.getRealizingMessage();
        if (messageList.size() > 0) {
            ret = messageList.get(0);
            if (ret != null) {
                return ret;
            }
        }
        
        EList<CommunicationMessage> communicationMessageList = this.theEditedElement.getRealizingCommunicationMessage();
        if (communicationMessageList.size() > 0) {
            ret = communicationMessageList.get(0);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("a2f782c8-7faa-4fb2-98d6-7fe9b243f3c9")
    @Override
    public int getRowsNumber() {
        return InformationFlowPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("8411eb42-a89d-45b4-a3fd-a2d3c4dd8359")
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
                return this.theEditedElement.getConveyed();
            case 3: {
                EList<UmlModelElement> sources = this.theEditedElement.getInformationSource();
                if (sources != null && sources.size() > 0) {
                    return sources.get(0);
                }
                return null;
            }
            case 4: {
                EList<UmlModelElement> targets = this.theEditedElement.getInformationTarget();
                if (targets != null && targets.size() > 0) {
                    return targets.get(0);
                }
                return null;
            }
            case 5: {
                return this.theEditedElement.getOwner();
            }
            case 6:
                return getRealizing();
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
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("dfb7f5a2-e9bc-4601-949c-96989c499be6")
    @SuppressWarnings("unchecked")
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
                return new DefaultMultiElementNatValue((Collection<MObject>) getValue(row, col), true,
                        Collections.singletonList(Classifier.class));
            case 3:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(UmlModelElement.class));
            case 4:
                return new DefaultElementNatValue((MObject) getValue(row, col), false,
                        Collections.singletonList(UmlModelElement.class));
            case 5:
                return new DefaultElementNatValue((MObject) getValue(row, col), true,
                        Collections.singletonList(NameSpace.class));
            case 6:
                List<java.lang.Class<? extends MObject>> realizingTypes = Arrays.asList(
                        ActivityEdge.class,
                        CommunicationMessage.class,
                        LinkEnd.class,
                        Message.class,
                        StructuralFeature.class);
                return new DefaultElementNatValue((MObject) getValue(row, col), true, realizingTypes);
            default:
                return null;
            }
        default:
            return null;
        }
        
    }

    /**
     * Set the InstanceNode realizing elements. This method set the right
     * dependency and clears the otheEditedElement.
     * @param theEditedElement the instance node
     * @param value the new represented element
     */
    @objid ("695d235d-f7a6-4b75-a91d-657c25081775")
    private void setRealizing(InformationFlow theEditedElement, Object value) {
        // Erase old value or exit if old value is new value
        EList<StructuralFeature> featureList = theEditedElement.getRealizingFeature();
        if (featureList.size() > 0) {
            StructuralFeature old1 = featureList.get(0);
            if (old1.equals(value)) {
                return;
            }
            theEditedElement.getRealizingFeature().remove(old1);
        }
        
        EList<LinkEnd> linkList = theEditedElement.getRealizingLink();
        if (linkList.size() > 0) {
            LinkEnd old1 = linkList.get(0);
            if (old1.equals(value)) {
                return;
            }
            theEditedElement.getRealizingLink().remove(old1);
        }
        
        EList<ActivityEdge> edgeList = theEditedElement.getRealizingActivityEdge();
        if (edgeList.size() > 0) {
            ActivityEdge old3 = edgeList.get(0);
            if (old3.equals(value)) {
                return;
            }
            theEditedElement.getRealizingActivityEdge().remove(old3);
        }
        
        EList<Message> messageList = theEditedElement.getRealizingMessage();
        if (messageList.size() > 0) {
            Message old4 = messageList.get(0);
            if (old4.equals(value)) {
                return;
            }
            theEditedElement.getRealizingMessage().remove(old4);
        }
        
        EList<CommunicationMessage> communicationMessageList = theEditedElement.getRealizingCommunicationMessage();
        if (communicationMessageList.size() > 0) {
            CommunicationMessage old5 = communicationMessageList.get(0);
            if (old5.equals(value)) {
                return;
            }
            theEditedElement.getRealizingCommunicationMessage().remove(old5);
        }
        
        if (value != null) {
            // Set new value
            if (LinkEnd.class.isAssignableFrom(value.getClass())) {
                theEditedElement.getRealizingLink().add((LinkEnd) value);
            }
            if (StructuralFeature.class.isAssignableFrom(value.getClass())) {
                theEditedElement.getRealizingFeature().add((StructuralFeature) value);
            } else if (ActivityEdge.class.isAssignableFrom(value.getClass())) {
                theEditedElement.getRealizingActivityEdge().add((ActivityEdge) value);
            } else if (Message.class.isAssignableFrom(value.getClass())) {
                theEditedElement.getRealizingMessage().add((Message) value);
            } else if (CommunicationMessage.class.isAssignableFrom(value.getClass())) {
                theEditedElement.getRealizingCommunicationMessage().add((CommunicationMessage) value);
            }
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
    @objid ("cdee799b-ab34-4d60-affb-55bce35bd87f")
    @Override
    @SuppressWarnings("unchecked")
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
                List<Classifier> newcontent = (List<Classifier>) value;
                this.theEditedElement.getConveyed().clear();
                this.theEditedElement.getConveyed().addAll(newcontent);
                break;
            case 3:
                this.theEditedElement.getInformationSource().clear();
                this.theEditedElement.getInformationSource().add((UmlModelElement) value);
                break;
            case 4:
                this.theEditedElement.getInformationTarget().clear();
                this.theEditedElement.getInformationTarget().add((UmlModelElement) value);
                break;
            case 5:
                this.theEditedElement.setOwner((NameSpace) value);
                break;
            case 6:
                setRealizing(this.theEditedElement, value);
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
