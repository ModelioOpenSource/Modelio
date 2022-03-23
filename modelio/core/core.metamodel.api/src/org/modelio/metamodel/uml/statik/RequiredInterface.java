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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * RequiredInterface v0.0.9054
 */
@objid ("001b5224-c4bf-1fd8-97fe-001ec947cd2a")
public interface RequiredInterface extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("20ff874a-3faa-439d-8dca-9ea263eefd87")
    public static final String MNAME = "RequiredInterface";

    /**
     * The metaclass qualified name.
     */
    @objid ("f3381daf-cc3d-4eef-bed4-ab546ea36321")
    public static final String MQNAME = "Standard.RequiredInterface";

    /**
     * Getter for relation 'RequiredInterface->RequiredElement'
     * 
     * Metamodel description:
     * <i>Interface required by the Port.</i>
     */
    @objid ("ce04a8bc-f91e-456c-890b-d8ffc5af6d28")
    EList<Interface> getRequiredElement();

    /**
     * Filtered Getter for relation 'RequiredInterface->RequiredElement'
     * 
     * Metamodel description:
     * <i>Interface required by the Port.</i>
     */
    @objid ("6534bc41-8989-4c47-8088-fc081eeaa768")
    <T extends Interface> List<T> getRequiredElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'RequiredInterface->Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cff53395-dfc4-4b64-a560-5c8038d6b6b7")
    EList<LinkEnd> getProvider();

    /**
     * Filtered Getter for relation 'RequiredInterface->Provider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e77cfc9b-74dd-42f8-ac98-05d8a6bf558b")
    <T extends LinkEnd> List<T> getProvider(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'RequiredInterface->Requiring'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("72c0f80c-b9f8-4954-8879-4dcdb7fd092c")
    Port getRequiring();

    /**
     * Setter for relation 'RequiredInterface->Requiring'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c0e84285-cf40-4441-abc0-7365ed511fbf")
    void setRequiring(Port value);

    /**
     * Getter for relation 'RequiredInterface->NaryProvider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("83622fb1-fcaf-4053-9fad-fbc00a69e916")
    EList<NaryLinkEnd> getNaryProvider();

    /**
     * Filtered Getter for relation 'RequiredInterface->NaryProvider'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c8ead03e-f4c3-43ea-a793-6262a14505fc")
    <T extends NaryLinkEnd> List<T> getNaryProvider(java.lang.Class<T> filterClass);

}
