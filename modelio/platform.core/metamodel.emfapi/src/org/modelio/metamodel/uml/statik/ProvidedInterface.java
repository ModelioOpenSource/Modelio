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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Port;

/**
 * ProvidedInterface v0.0.9054
 */
@objid ("00198dea-c4bf-1fd8-97fe-001ec947cd2a")
public interface ProvidedInterface extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("74e4991d-5555-4dd6-ade3-0cf5e9164495")
    public static final String MNAME = "ProvidedInterface";

    /**
     * The metaclass qualified name.
     */
    @objid ("a068d65f-0225-42d3-8053-379bebdfa08d")
    public static final String MQNAME = "Standard.ProvidedInterface";

    /**
     * Getter for relation 'ProvidedInterface->ProvidedElement'
     * 
     * Metamodel description:
     * <i>Interface provided by the Port.</i>
     */
    @objid ("116dd96e-ba72-48a6-a449-fc1f3a562af0")
    EList<Interface> getProvidedElement();

    /**
     * Filtered Getter for relation 'ProvidedInterface->ProvidedElement'
     * 
     * Metamodel description:
     * <i>Interface provided by the Port.</i>
     */
    @objid ("0d8c0603-fbb1-490a-a084-b44031cdccb7")
    <T extends Interface> List<T> getProvidedElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ProvidedInterface->Providing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4e345139-0138-4820-81ce-a96960305d8c")
    Port getProviding();

    /**
     * Setter for relation 'ProvidedInterface->Providing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("46bae6fb-f5df-4307-8ba4-9bf58b9d811b")
    void setProviding(Port value);

    /**
     * Getter for relation 'ProvidedInterface->Consumer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b2559747-82fd-4cfc-8715-86a10f5c2310")
    EList<LinkEnd> getConsumer();

    /**
     * Filtered Getter for relation 'ProvidedInterface->Consumer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4a1a0d66-0079-48e2-a8f0-394061249868")
    <T extends LinkEnd> List<T> getConsumer(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ProvidedInterface->NaryConsumer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8b3449a9-d69d-4a77-b8f2-ae878ef3bf82")
    EList<NaryLinkEnd> getNaryConsumer();

    /**
     * Filtered Getter for relation 'ProvidedInterface->NaryConsumer'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f5f6aaf3-6867-46a5-8aae-669f63a6fb5d")
    <T extends NaryLinkEnd> List<T> getNaryConsumer(java.lang.Class<T> filterClass);

}
