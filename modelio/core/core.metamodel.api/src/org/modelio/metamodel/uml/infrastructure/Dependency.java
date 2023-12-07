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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Dependency v0.0.9054
 * 
 * 
 * <p>In the metamodel, a Dependency is a directed relationship from a client to a supplier, stating that the client is dependent on the supplier (in other words, the client element requires the presence and knowledge of the supplier element).</p><p>Dependencies are widely used inside Modelio to manage traceability. Any ModelElement can be traced to another ModelElement using this association.</p><p>In Modelio, a Dependency can only have one client and one supplier.</p><p>In Modelio, in order to comply with UML 2.0, the roles are swapped and renamed. The correspondence from UML 2.0 is now as follows:</p>
 * 
 * <ul>
 * 	<li>Dependency.client = Dependency.Impacted;</li>
 * 	<li>Dependency.supplier = Dependency.DependsOn;</li>
 * 	<li>Element.supplierDependency = ModelElement.ImpactedDependency;</li>
 * 	<li>Element.clientDependency = ModelElement.DependsOnDependency.</li>
 * </ul>
 * 
 * <p>A Dependency now belongs to the impacted element (the client in UML 2.0).</p>
 * 
 * 
 * 
 */
@objid ("0085d9be-c4be-1fd8-97fe-001ec947cd2a")
public interface Dependency extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("23c6233d-5908-492b-92dd-13a493c8cf0e")
    public static final String MNAME = "Dependency";

    /**
     * The metaclass qualified name.
     */
    @objid ("a43ddec7-2b34-498c-8f2d-19caa93bc154")
    public static final String MQNAME = "Infrastructure.Dependency";

    /**
     * Getter for relation 'Dependency->Impacted'
     * 
     * Metamodel description:
     * <i>Client</i>
     * 
     */
    @objid ("09f21bf9-30d1-4a56-b7fa-2fa13848fd48")
    ModelElement getImpacted();

    /**
     * Setter for relation 'Dependency->Impacted'
     * 
     * Metamodel description:
     * <i>Client</i>
     * 
     */
    @objid ("22808ee8-6cd6-47a2-ab53-ffa3586f4c1c")
    void setImpacted(ModelElement value);

    /**
     * Getter for relation 'Dependency->DependsOn'
     * 
     * Metamodel description:
     * <i>The element independent of the client element, in the same respect and the same dependency relationship.</i>
     * 
     */
    @objid ("adbce520-42fb-456a-8604-fa20665f07fb")
    ModelElement getDependsOn();

    /**
     * Setter for relation 'Dependency->DependsOn'
     * 
     * Metamodel description:
     * <i>The element independent of the client element, in the same respect and the same dependency relationship.</i>
     * 
     */
    @objid ("bd52f755-94b6-4ea2-bc49-e2e62986fecf")
    void setDependsOn(ModelElement value);
}

