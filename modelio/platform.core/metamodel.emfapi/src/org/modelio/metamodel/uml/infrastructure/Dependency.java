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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

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
 */
@objid ("0085d9be-c4be-1fd8-97fe-001ec947cd2a")
public interface Dependency extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("1a712206-9038-4056-8f1b-397f68d67a11")
    public static final String MNAME = "Dependency";

    /**
     * The metaclass qualified name.
     */
    @objid ("1619193c-253d-48ee-9df7-5de72f398e7a")
    public static final String MQNAME = "Infrastructure.Dependency";

    /**
     * Getter for relation 'Dependency->Impacted'
     * 
     * Metamodel description:
     * <i>Client</i>
     */
    @objid ("66bc6d9f-7e2a-443e-95f6-30cb60ac9a3f")
    ModelElement getImpacted();

    /**
     * Setter for relation 'Dependency->Impacted'
     * 
     * Metamodel description:
     * <i>Client</i>
     */
    @objid ("b8019e87-3218-457d-bb7a-68a4741f3270")
    void setImpacted(ModelElement value);

    /**
     * Getter for relation 'Dependency->DependsOn'
     * 
     * Metamodel description:
     * <i>The element independent of the client element, in the same respect and the same dependency relationship.</i>
     */
    @objid ("4f0ff90d-0746-4a54-8e73-d8417f5e4148")
    ModelElement getDependsOn();

    /**
     * Setter for relation 'Dependency->DependsOn'
     * 
     * Metamodel description:
     * <i>The element independent of the client element, in the same respect and the same dependency relationship.</i>
     */
    @objid ("fcd648bc-c61a-431a-843d-e88b3af84ff7")
    void setDependsOn(ModelElement value);

}
