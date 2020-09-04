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
package org.modelio.metamodel.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;

/**
 * Gate v0.0.9054
 * 
 * 
 * Gates are connected through Messages. A Gate is actually a representative of an OccurrenceSpecification that is not in the same scope as the Gate.
 * 
 * Gates play different roles: we have formal gates on Interactions, and actual gates on InteractionUses.
 */
@objid ("004545ca-c4bf-1fd8-97fe-001ec947cd2a")
public interface Gate extends MessageEnd {
    /**
     * The metaclass simple name.
     */
    @objid ("8d37ad29-82f7-4083-9549-30247bfdafd7")
    public static final String MNAME = "Gate";

    /**
     * The metaclass qualified name.
     */
    @objid ("cfe67cf0-f03f-42b0-b301-eff8ae19664e")
    public static final String MQNAME = "Standard.Gate";

    /**
     * Getter for relation 'Gate->OwnerUse'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("aa9013f4-815e-44b9-9df6-7a06354010b6")
    InteractionUse getOwnerUse();

    /**
     * Setter for relation 'Gate->OwnerUse'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d2d30bfc-e735-4e30-a1a8-e0cce1384e17")
    void setOwnerUse(InteractionUse value);

    /**
     * Getter for relation 'Gate->Actual'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b18592a8-383d-4d3f-a689-afa8994362a0")
    EList<Gate> getActual();

    /**
     * Filtered Getter for relation 'Gate->Actual'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c8060897-fc7f-48ab-98de-8452413d04b2")
    <T extends Gate> List<T> getActual(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Gate->OwnerInteraction'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c694328f-3d6a-42d2-941a-fd3c033e3ce9")
    Interaction getOwnerInteraction();

    /**
     * Setter for relation 'Gate->OwnerInteraction'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("951b43f7-e059-4675-896f-cf2f0fd73046")
    void setOwnerInteraction(Interaction value);

    /**
     * Getter for relation 'Gate->OwnerFragment'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("24582264-759d-4c63-80c5-81e8c7213e47")
    CombinedFragment getOwnerFragment();

    /**
     * Setter for relation 'Gate->OwnerFragment'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("edb34dac-948f-40bb-971f-a75fb0776ea0")
    void setOwnerFragment(CombinedFragment value);

    /**
     * Getter for relation 'Gate->Formal'
     * 
     * Metamodel description:
     * <i>If the gate belongs to an InteractionUse, then it is connected to a formal gate belonging to the referred interaction.</i>
     */
    @objid ("0a314064-bf37-41b7-967c-6ae7c50cfb72")
    Gate getFormal();

    /**
     * Setter for relation 'Gate->Formal'
     * 
     * Metamodel description:
     * <i>If the gate belongs to an InteractionUse, then it is connected to a formal gate belonging to the referred interaction.</i>
     */
    @objid ("b4398a4e-fec4-41b8-9e95-3f8cda97a053")
    void setFormal(Gate value);

}
