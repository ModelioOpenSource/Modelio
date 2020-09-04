/* 
 * Copyright 2013-2018 Modeliosoft
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
package org.modelio.metamodel.uml.behavior.usecaseModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.statik.GeneralClass;

/**
 * UseCase v0.0.9054
 * 
 * 
 * A UseCase is expressed by sequences of Messages exchanged by system units and one or more Actors of the system. 
 * 
 * The definition of a UseCase includes all of the Behavior that it entails. This Behavior can be expressed by sequence diagrams, activity diagrams, Object diagrams, and so on. 
 * 
 * UseCases are structured by Packages, and have cooperation links with Actors. 
 * 
 * UseCases belong to a NameSpace.
 */
@objid ("00588716-c4bf-1fd8-97fe-001ec947cd2a")
public interface UseCase extends GeneralClass {
    /**
     * The metaclass simple name.
     */
    @objid ("b336a5c3-0254-45cc-86ca-ae44f2fd4150")
    public static final String MNAME = "UseCase";

    /**
     * The metaclass qualified name.
     */
    @objid ("9692e2f5-6f89-4f41-9521-c9a7a5ab5e13")
    public static final String MQNAME = "Standard.UseCase";

    /**
     * Getter for relation 'UseCase->Used'
     * 
     * Metamodel description:
     * <i>In dependencies between UseCases, this defines the link to the UseCaseDependency association.</i>
     */
    @objid ("36ad0db2-503f-4a7b-b655-f49eecf61fbe")
    EList<UseCaseDependency> getUsed();

    /**
     * Filtered Getter for relation 'UseCase->Used'
     * 
     * Metamodel description:
     * <i>In dependencies between UseCases, this defines the link to the UseCaseDependency association.</i>
     */
    @objid ("f2cc379a-3c84-4056-8b1b-0686a8a194fb")
    <T extends UseCaseDependency> List<T> getUsed(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UseCase->OwnedExtension'
     * 
     * Metamodel description:
     * <i>References the ExtensionPoints owned by the use case.</i>
     */
    @objid ("d5e55c4c-a9f7-4b14-8b56-a3cdcc4e9f48")
    EList<ExtensionPoint> getOwnedExtension();

    /**
     * Filtered Getter for relation 'UseCase->OwnedExtension'
     * 
     * Metamodel description:
     * <i>References the ExtensionPoints owned by the use case.</i>
     */
    @objid ("3ff26b82-b7d6-4ad9-8c61-57cc766102fe")
    <T extends ExtensionPoint> List<T> getOwnedExtension(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'UseCase->User'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5a9d2594-3abb-4492-b48a-3756f087b1d7")
    EList<UseCaseDependency> getUser();

    /**
     * Filtered Getter for relation 'UseCase->User'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b2f44928-7688-4c2f-9641-12d791441dce")
    <T extends UseCaseDependency> List<T> getUser(java.lang.Class<T> filterClass);

}
