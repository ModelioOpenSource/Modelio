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

package org.modelio.uml.activitydiagram.editor.elements.partition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * An enumeration listing the different kinds of creation tools using the ActivityPartition metaclass.
 * 
 * @author fpoyer
 */
@objid ("2b1b4579-55b6-11e2-877f-002564c97630")
public enum PartitionToolKind {
    /**
     * A tool of this kind requests the creation of an ActivityPartition that is a "sibling" (ie on the same nesting level) of the designated ActivityPartition.
     */
    SIBLING,
    /**
     * A tool of this kind requests the creation of an ActivityPartition that is nested in (ie a sub partition of) the designated ActivityPartition.
     */
    INNER,
    /**
     * A tool of this kind requests the creation of an ActivityPartition container on the diagram background, with orientation being horizontal (contained partitions will be on top of each other).
     */
    HORIZONTAL_CONTAINER,
    /**
     * A tool of this kind requests the creation of an ActivityPartition container on the diagram background, with orientation being vertical (contained partitions will side by side).
     */
    VERTICAL_CONTAINER;
}
