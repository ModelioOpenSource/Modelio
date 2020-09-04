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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * ActivityGroup v0.0.9054
 * 
 * 
 * Nodes and edges can belong to more than one group. They have no inherent semantics and can be used for various purposes. Subclasses of ActivityGroup may add semantics.
 * 
 * ActivityGroups belong to an activity or (exclusively) to another ActivityGroup.
 */
@objid ("002813ba-c4bf-1fd8-97fe-001ec947cd2a")
public interface ActivityGroup extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("662bf2a8-c45f-4c86-9035-8f976ac484cf")
    public static final String MNAME = "ActivityGroup";

    /**
     * The metaclass qualified name.
     */
    @objid ("45e29e1d-a821-44a5-83d7-cbbe9f55b9f7")
    public static final String MQNAME = "Standard.ActivityGroup";

    /**
     * Getter for relation 'ActivityGroup->InActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("11d33b42-9430-4e8e-bba6-f2797081a54e")
    Activity getInActivity();

    /**
     * Setter for relation 'ActivityGroup->InActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fa0ac401-998f-400e-b787-3afd3f472190")
    void setInActivity(Activity value);

}
