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
package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * RaisedException v0.0.9054
 */
@objid ("001a6e72-c4bf-1fd8-97fe-001ec947cd2a")
public interface RaisedException extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("47a6db55-c375-4a01-b7c9-dc1a001fdb1f")
    public static final String MNAME = "RaisedException";

    /**
     * The metaclass qualified name.
     */
    @objid ("5b00a705-faa4-4eff-a963-5bf1f32dd1c0")
    public static final String MQNAME = "Standard.RaisedException";

    /**
     * Getter for relation 'RaisedException->ThrownType'
     * 
     * Metamodel description:
     * <i>Raised exception type.</i>
     */
    @objid ("9b687292-a1c1-47f6-8a07-f93644ccf296")
    Classifier getThrownType();

    /**
     * Setter for relation 'RaisedException->ThrownType'
     * 
     * Metamodel description:
     * <i>Raised exception type.</i>
     */
    @objid ("09ba1d32-3cd2-4a58-89d3-6cff88c58966")
    void setThrownType(Classifier value);

    /**
     * Getter for relation 'RaisedException->Thrower'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1f6110e7-99c3-4f32-abdf-b4c020c9daa1")
    Operation getThrower();

    /**
     * Setter for relation 'RaisedException->Thrower'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b5f9600a-edeb-458f-bade-288cac96d82c")
    void setThrower(Operation value);

}
