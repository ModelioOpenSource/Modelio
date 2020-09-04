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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;

/**
 * PartDecomposition v0.0.9054
 * 
 * 
 * A Lifeline has a class associated as the type of the Instance that the Lifeline represents. That class may have an internal structure and the PartDecomposition is an Interaction that describes the behavior of that internal structure relative to the Interaction where the decomposition is referenced.
 * 
 * A PartDecomposition is a specialization of InteractionUse. It associates with the Instance that it decomposes.
 */
@objid ("004ae91c-c4bf-1fd8-97fe-001ec947cd2a")
public interface PartDecomposition extends InteractionUse {
    /**
     * The metaclass simple name.
     */
    @objid ("3eb736d6-f374-4627-a1cc-cf870231dfa3")
    public static final String MNAME = "PartDecomposition";

    /**
     * The metaclass qualified name.
     */
    @objid ("7613cf04-e931-4254-9f7e-795892223812")
    public static final String MQNAME = "Standard.PartDecomposition";

    /**
     * Getter for relation 'PartDecomposition->Decomposed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5e6275a0-a518-4787-8d3c-d7ffce6eb4f8")
    Lifeline getDecomposed();

    /**
     * Setter for relation 'PartDecomposition->Decomposed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("76182f0a-bd6e-4a22-8054-71f5b0ffb4a0")
    void setDecomposed(Lifeline value);

}
