/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;

/**
 * OutputPin v0.0.9054
 * 
 * 
 * An output pin is a pin that holds output values produced by an action.
 * 
 * An action cannot terminate itself if an output pin has fewer values than the lower multiplicity. An action may not put more values in an output pin in a single execution than the upper multiplicity of the pin.
 */
@objid ("003aad54-c4bf-1fd8-97fe-001ec947cd2a")
public interface OutputPin extends Pin {
    /**
     * The metaclass simple name.
     */
    @objid ("ed12c258-3cf0-4cf4-8064-b75d0f818517")
    public static final String MNAME = "OutputPin";

    /**
     * The metaclass qualified name.
     */
    @objid ("d4748f62-fba4-4663-a436-4d2afb60609c")
    public static final String MQNAME = "Standard.OutputPin";

    /**
     * Getter for relation 'OutputPin->Outputing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e8d0e737-6460-4eb0-abe1-ddd9b6deccd7")
    ActivityAction getOutputing();

    /**
     * Setter for relation 'OutputPin->Outputing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9f2521c4-bef7-4d02-905e-fcdd4e96ddc2")
    void setOutputing(ActivityAction value);

}
