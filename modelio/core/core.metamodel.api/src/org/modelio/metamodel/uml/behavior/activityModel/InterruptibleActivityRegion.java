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

package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;

/**
 * InterruptibleActivityRegion v0.0.9054
 * 
 * 
 * An interruptible region contains activity nodes. When a token leaves an interruptible region via edges designated by the region as interrupting edges, all tokens and behaviors in the region are terminated.
 * 
 * 
 */
@objid ("00366bf4-c4bf-1fd8-97fe-001ec947cd2a")
public interface InterruptibleActivityRegion extends ActivityGroup {
    /**
     * The metaclass simple name.
     */
    @objid ("8c456599-08c9-4c31-a27c-f5f998bbeb18")
    public static final String MNAME = "InterruptibleActivityRegion";

    /**
     * The metaclass qualified name.
     */
    @objid ("834af03d-a275-4834-ba4d-4f37aa4a3a82")
    public static final String MQNAME = "Standard.InterruptibleActivityRegion";

    /**
     * Getter for relation 'InterruptibleActivityRegion->InterruptingEdge'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b62f098a-fb5e-48b3-a2fc-00138fa6219c")
    EList<ActivityEdge> getInterruptingEdge();

    /**
     * Filtered Getter for relation 'InterruptibleActivityRegion->InterruptingEdge'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("050b8209-d5b6-45dd-b258-bad3dfd1763c")
    <T extends ActivityEdge> List<T> getInterruptingEdge(java.lang.Class<T> filterClass);
}

