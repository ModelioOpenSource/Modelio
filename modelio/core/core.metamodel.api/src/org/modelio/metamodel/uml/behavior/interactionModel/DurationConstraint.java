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
import org.modelio.metamodel.uml.infrastructure.Constraint;

/**
 * DurationConstraint v0.0.9054
 * 
 * 
 * NOTE : This class is not yet available in Modelio.
 * 
 * A DurationConstraint is a Constraint that must reference 2 MessageEnds.
 * It expresses that the time between 2 MessagesEnds must be comprised between DurationMin and DurationMax.
 * 
 * Modelio specific:
 * DurationConstraint has been greatly simplified to improve its useability : its bounds are expressed directly textually instead of using the IntervalConstraint and DurationIntervals OMG metaclasses.
 * 
 * 
 */
@objid ("0043d1ea-c4bf-1fd8-97fe-001ec947cd2a")
public interface DurationConstraint extends Constraint {
    /**
     * The metaclass simple name.
     */
    @objid ("6bc77f26-91d8-42ee-b71b-8269d35ab2e1")
    public static final String MNAME = "DurationConstraint";

    /**
     * The metaclass qualified name.
     */
    @objid ("15db99a6-6c80-41d8-ae42-7ab30945fa71")
    public static final String MQNAME = "Standard.DurationConstraint";

    /**
     * Getter for attribute 'DurationConstraint.DurationMin'
     * 
     * Metamodel description:
     * <i>The minimum duration between 2 MessageEnds.</i>
     * 
     */
    @objid ("d2d087e3-a93f-46c3-9b04-fdaf1d8b6e13")
    String getDurationMin();

    /**
     * Setter for attribute 'DurationConstraint.DurationMin'
     * 
     * Metamodel description:
     * <i>The minimum duration between 2 MessageEnds.</i>
     * 
     */
    @objid ("73911b6e-4ada-421d-b52d-a917e0462ec9")
    void setDurationMin(String value);

    /**
     * Getter for attribute 'DurationConstraint.DurationMax'
     * 
     * Metamodel description:
     * <i>The maximum duration between 2 MessageEnds.</i>
     * 
     */
    @objid ("a6fbb6f8-288a-4ce9-930b-f1be8fc75d15")
    String getDurationMax();

    /**
     * Setter for attribute 'DurationConstraint.DurationMax'
     * 
     * Metamodel description:
     * <i>The maximum duration between 2 MessageEnds.</i>
     * 
     */
    @objid ("7fc91f32-ead7-45e1-b6de-09f55c8e4d76")
    void setDurationMax(String value);
}

