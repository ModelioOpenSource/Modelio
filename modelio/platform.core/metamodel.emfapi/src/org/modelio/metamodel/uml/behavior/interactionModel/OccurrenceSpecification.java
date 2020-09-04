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
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;

/**
 * OccurrenceSpecification v0.0.9054
 * 
 * 
 * An OccurrenceSpecification is the basic semantic unit of Interactions. The sequences of occurrences specified by them are the meanings of Interactions.
 * 
 * OccurrenceSpecifications are ordered along a Lifeline.
 */
@objid ("004a586c-c4bf-1fd8-97fe-001ec947cd2a")
public interface OccurrenceSpecification extends InteractionFragment {
    /**
     * The metaclass simple name.
     */
    @objid ("fbf83820-19a3-4171-b7e6-dfce6e018dfb")
    public static final String MNAME = "OccurrenceSpecification";

    /**
     * The metaclass qualified name.
     */
    @objid ("8c003bf2-a3f0-409a-86be-656852c7cb49")
    public static final String MQNAME = "Standard.OccurrenceSpecification";

    /**
     * Getter for relation 'OccurrenceSpecification->ToAfter'
     * 
     * Metamodel description:
     * <i>NOT TO DOCUMENT : References the GeneralOrderings that specify EventOcurrences that must occur after this OccurrenceSpecification.</i>
     */
    @objid ("335d4ddb-df5e-40b4-92ec-07ef7334f022")
    EList<GeneralOrdering> getToAfter();

    /**
     * Filtered Getter for relation 'OccurrenceSpecification->ToAfter'
     * 
     * Metamodel description:
     * <i>NOT TO DOCUMENT : References the GeneralOrderings that specify EventOcurrences that must occur after this OccurrenceSpecification.</i>
     */
    @objid ("8335bbf4-7a22-4fa9-a622-bef2adffa6fe")
    <T extends GeneralOrdering> List<T> getToAfter(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'OccurrenceSpecification->ToBefore'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1fd093eb-4bcc-4f5d-b7f4-abbd322c1d14")
    EList<GeneralOrdering> getToBefore();

    /**
     * Filtered Getter for relation 'OccurrenceSpecification->ToBefore'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("09b66cfb-a0d2-4096-86b2-895924aadfc4")
    <T extends GeneralOrdering> List<T> getToBefore(java.lang.Class<T> filterClass);

}
