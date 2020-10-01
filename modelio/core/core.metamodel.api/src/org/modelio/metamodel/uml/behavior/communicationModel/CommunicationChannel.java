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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.communicationModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.NaryLink;

/**
 * CommunicationChannel v0.0.9054
 * 
 * 
 * A CommunicationChannel is a link between two CommunicationNodes.
 * 
 * A CommunicationChannel can represent a Link. It owns messages that follow the channel from its start to the end, and inverted messages that go from the end to the start.
 */
@objid ("5451d474-f72f-46a9-b8b5-35997413d584")
public interface CommunicationChannel extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("6e808325-1178-4f5a-9ca8-269f71745b8d")
    public static final String MNAME = "CommunicationChannel";

    /**
     * The metaclass qualified name.
     */
    @objid ("20f1621a-db2d-4fb3-8e34-8502c8d522e0")
    public static final String MQNAME = "Standard.CommunicationChannel";

    /**
     * Getter for relation 'CommunicationChannel->StartToEndMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d089f797-963a-472b-9102-e08e9087b6cd")
    EList<CommunicationMessage> getStartToEndMessage();

    /**
     * Filtered Getter for relation 'CommunicationChannel->StartToEndMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("066dba33-47f2-4d51-911f-d869593b015d")
    <T extends CommunicationMessage> List<T> getStartToEndMessage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'CommunicationChannel->Channel'
     * 
     * Metamodel description:
     * <i>References the Link the communication channel represents.</i>
     */
    @objid ("1bb2731c-131f-497d-9749-1f4f1e705acb")
    Link getChannel();

    /**
     * Setter for relation 'CommunicationChannel->Channel'
     * 
     * Metamodel description:
     * <i>References the Link the communication channel represents.</i>
     */
    @objid ("590a2bf3-2953-41dc-8b02-1f07ac23249c")
    void setChannel(Link value);

    /**
     * Getter for relation 'CommunicationChannel->Start'
     * 
     * Metamodel description:
     * <i>Node starting the channel.</i>
     */
    @objid ("afa7354b-88c4-40d5-b8dd-215055f8955c")
    CommunicationNode getStart();

    /**
     * Setter for relation 'CommunicationChannel->Start'
     * 
     * Metamodel description:
     * <i>Node starting the channel.</i>
     */
    @objid ("c3f1412d-ca73-479b-8bf7-561601b3f34c")
    void setStart(CommunicationNode value);

    /**
     * Getter for relation 'CommunicationChannel->NaryChannel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("42f8450d-1aca-4f83-a91e-9f7e7fc3c5c7")
    NaryLink getNaryChannel();

    /**
     * Setter for relation 'CommunicationChannel->NaryChannel'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("92b2c0f0-fd35-4625-a34a-228b33b2cc4d")
    void setNaryChannel(NaryLink value);

    /**
     * Getter for relation 'CommunicationChannel->EndToStartMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("20dbaa0c-b05b-4a47-a12e-306a021a47aa")
    EList<CommunicationMessage> getEndToStartMessage();

    /**
     * Filtered Getter for relation 'CommunicationChannel->EndToStartMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1842961e-730c-46db-8ef2-47e5d4f8ba30")
    <T extends CommunicationMessage> List<T> getEndToStartMessage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'CommunicationChannel->End'
     * 
     * Metamodel description:
     * <i>Node at the end of the channel.</i>
     */
    @objid ("a401b5aa-a324-4104-b9f3-8aa6e8adc133")
    CommunicationNode getEnd();

    /**
     * Setter for relation 'CommunicationChannel->End'
     * 
     * Metamodel description:
     * <i>Node at the end of the channel.</i>
     */
    @objid ("cfed1cf5-bd4d-45f7-9acf-65e9e11fac88")
    void setEnd(CommunicationNode value);

}
