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
package org.modelio.metamodel.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ActivityDiagram v0.0.9054
 * 
 * 
 * <p>Activity diagrams are used to represent business processes, or the dynamic part of a model (algorithm of an operation, for example).</p><p>They can be &quot;autonomous&quot; (inside a package) and represent a process, or the behavior of an operation (inside an operation).</p><p>Creation wizards, using the drag &amp; drop feature, allow to create object nodes (dragging classes), call operations (dragging operations), sub process calls (dragging activities).</p>
 */
@objid ("0067c92e-c4bf-1fd8-97fe-001ec947cd2a")
public interface ActivityDiagram extends BehaviorDiagram {
    /**
     * The metaclass simple name.
     */
    @objid ("fcfb980b-35e6-4e2e-879d-908ab3fc43de")
    public static final String MNAME = "ActivityDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("c1fcc007-aa46-4991-9aae-76802a86f033")
    public static final String MQNAME = "Standard.ActivityDiagram";

    /**
     * Getter for attribute 'ActivityDiagram.IsVertical'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("7a39a5c8-f15f-4d9b-a4fb-8d0db871e758")
    boolean isIsVertical();

    /**
     * Setter for attribute 'ActivityDiagram.IsVertical'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("d798af5a-ba0a-4b07-a266-e3eecfc4ed07")
    void setIsVertical(boolean value);

}
