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
package org.modelio.metamodel.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Instance;

/**
 * Lifeline v0.0.9054
 * 
 * 
 * <p>A lifeline represents an individual participant in the Interaction. While Parts and StructuralFeatures may have multiplicity greater than 1, Lifelines represent only one interacting entity.</p><p>If the referenced Instance is multivalued (i.e, has a multiplicity &gt; 1), then the Lifeline may have an expression (the &quot;selector&quot;) that specifies which particular part is represented by this Lifeline. If the selector is omitted, this means that an arbitrary representative of the multivalued ConnectableElement is chosen.</p>
 */
@objid ("0048b5b6-c4bf-1fd8-97fe-001ec947cd2a")
public interface Lifeline extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("7370acd5-d0b0-4ae6-a6a7-3e4ceff68ac4")
    public static final String MNAME = "Lifeline";

    /**
     * The metaclass qualified name.
     */
    @objid ("69e94ef0-4c3a-45cb-88b3-01fdb1f744eb")
    public static final String MQNAME = "Standard.Lifeline";

    /**
     * Getter for attribute 'Lifeline.Selector'
     * 
     * Metamodel description:
     * <i>If the referenced ConnectableElement is multivalued, then this specifies the specific individual part within that set.</i>
     */
    @objid ("beedd7c8-d90e-46f9-88e2-679e9b9ab0f8")
    String getSelector();

    /**
     * Setter for attribute 'Lifeline.Selector'
     * 
     * Metamodel description:
     * <i>If the referenced ConnectableElement is multivalued, then this specifies the specific individual part within that set.</i>
     */
    @objid ("d4a05ddc-7fc8-402c-8397-783b623353c5")
    void setSelector(String value);

    /**
     * Getter for relation 'Lifeline->CoveredBy'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9872850e-636b-448e-8926-63ba87479134")
    EList<InteractionFragment> getCoveredBy();

    /**
     * Filtered Getter for relation 'Lifeline->CoveredBy'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("08851ea2-9ce6-4c85-b9bf-404daf53882d")
    <T extends InteractionFragment> List<T> getCoveredBy(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Lifeline->DecomposedAs'
     * 
     * Metamodel description:
     * <i>References the Interaction that represents the decomposition.</i>
     */
    @objid ("7788c93c-4243-451a-ad0c-84590a24e77d")
    PartDecomposition getDecomposedAs();

    /**
     * Setter for relation 'Lifeline->DecomposedAs'
     * 
     * Metamodel description:
     * <i>References the Interaction that represents the decomposition.</i>
     */
    @objid ("fff1c171-1e8f-46c7-9b21-9e7c83fe7482")
    void setDecomposedAs(PartDecomposition value);

    /**
     * Getter for relation 'Lifeline->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f4fd6419-0fb7-426f-81de-e1e57f024b87")
    Interaction getOwner();

    /**
     * Setter for relation 'Lifeline->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ecbd5ef9-8803-476c-ba36-5e6473605181")
    void setOwner(Interaction value);

    /**
     * Getter for relation 'Lifeline->Represented'
     * 
     * Metamodel description:
     * <i>References the Instance the Lifeline represents.</i>
     */
    @objid ("5bca8154-72a4-4584-a0bc-c4d33b58adc4")
    Instance getRepresented();

    /**
     * Setter for relation 'Lifeline->Represented'
     * 
     * Metamodel description:
     * <i>References the Instance the Lifeline represents.</i>
     */
    @objid ("c91e5871-9ff8-4f79-9150-624787c0b81d")
    void setRepresented(Instance value);

}
