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
package org.modelio.metamodel.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;

/**
 * Interface v0.0.9054
 * 
 * 
 * An Interface specifies a contract. Any Instance of a Classifier that realizes the Interface must fulfill this contract. The obligations that may be associated with an Interface take the form of various kinds of Constraints (such as pre-conditions and post-conditions) or protocol specifications, which may impose ordering restrictions on Interactions through the Interface. 
 * 
 * Since Interfaces are declarations, they are not instanciable. Instead, an Interface specification is implemented by an Instance of an instanciable Classifier, which means that the instanciable Classifier presents a public facade that conforms to the Interface specification. 
 * 
 * Note that a given Classifier may implement more than one Interface and that an Interface may be implemented by a number of different Classifiers.  
 * 
 * Constraints: The visibility of all Features owned by an Interface must be public.
 */
@objid ("000e151e-c4bf-1fd8-97fe-001ec947cd2a")
public interface Interface extends GeneralClass {
    /**
     * The metaclass simple name.
     */
    @objid ("6d07e361-3b6a-4795-ba53-bab4238568a3")
    public static final String MNAME = "Interface";

    /**
     * The metaclass qualified name.
     */
    @objid ("4ec5ae75-7cde-4b7d-82f1-479c3e1291b1")
    public static final String MQNAME = "Standard.Interface";

    /**
     * Getter for relation 'Interface->Requiring'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("83920783-200d-4e39-9a79-e2cbe23190ab")
    EList<RequiredInterface> getRequiring();

    /**
     * Filtered Getter for relation 'Interface->Requiring'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("41498e31-b5ce-4d46-aeb2-f01950dc2408")
    <T extends RequiredInterface> List<T> getRequiring(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Interface->ImplementedLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("92668ea6-2dfc-4e12-afd8-86250d390e20")
    EList<InterfaceRealization> getImplementedLink();

    /**
     * Filtered Getter for relation 'Interface->ImplementedLink'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ef65fd00-ed22-44f4-a230-153f97f0fa73")
    <T extends InterfaceRealization> List<T> getImplementedLink(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Interface->Providing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1a7f000a-478d-49a1-8857-ce916c55391a")
    EList<ProvidedInterface> getProviding();

    /**
     * Filtered Getter for relation 'Interface->Providing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e0c919c1-6e77-4521-8e56-2b98d231db30")
    <T extends ProvidedInterface> List<T> getProviding(java.lang.Class<T> filterClass);

}
