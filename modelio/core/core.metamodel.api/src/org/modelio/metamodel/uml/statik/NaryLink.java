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

package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * NaryLink v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("00223b3e-c4bf-1fd8-97fe-001ec947cd2a")
public interface NaryLink extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e7231d52-910c-4e12-8338-1d47d205c3db")
    public static final String MNAME = "NaryLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("96a7333a-19c8-4302-9be2-2f22b1aaee12")
    public static final String MQNAME = "Standard.NaryLink";

    /**
     * Getter for relation 'NaryLink->NaryLinkEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a1935688-f1b4-49c7-a934-dee5cdef1cb4")
    EList<NaryLinkEnd> getNaryLinkEnd();

    /**
     * Filtered Getter for relation 'NaryLink->NaryLinkEnd'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f2dd34da-33f0-42f4-a6c4-617b0b759715")
    <T extends NaryLinkEnd> List<T> getNaryLinkEnd(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NaryLink->Model'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0b925807-3b03-401e-a5aa-ce5dd160ef2a")
    NaryAssociation getModel();

    /**
     * Setter for relation 'NaryLink->Model'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1769063d-3e7f-477e-8fc7-fe38bbfb2b0f")
    void setModel(NaryAssociation value);

    /**
     * Getter for relation 'NaryLink->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("90aae63d-9174-4d6c-b5d8-da71464c7718")
    EList<InformationFlow> getRealizedInformationFlow();

    /**
     * Filtered Getter for relation 'NaryLink->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6e1c4609-aa8b-45c3-a991-4c97995c8300")
    <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NaryLink->Sent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2fe2b7a5-c008-4cf4-a0a9-e36664e851e6")
    EList<CommunicationChannel> getSent();

    /**
     * Filtered Getter for relation 'NaryLink->Sent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8c912625-e0bb-4c1d-982b-2c2ba27a0a25")
    <T extends CommunicationChannel> List<T> getSent(java.lang.Class<T> filterClass);
}

