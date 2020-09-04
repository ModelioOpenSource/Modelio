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

package org.modelio.diagram.editor.activity.elements.partition.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Utility class that computes partition symbol.
 */
@objid ("2b166379-55b6-11e2-877f-002564c97630")
public class PartitionSymbolProvider {
    /**
     * This class is not instanciable.
     */
    @objid ("2b168a8a-55b6-11e2-877f-002564c97630")
    private PartitionSymbolProvider() {
    }

    /**
     * Get the partition label at the following format: "name : type"
     * 
     * @param c the partition
     * @return the computed label
     */
    @objid ("2b168a8d-55b6-11e2-877f-002564c97630")
    public static String computeSimpleLabel(final ActivityPartition c) {
        StringBuilder s = new StringBuilder();
        final String name = c.getName();
        
        s.append(name);
        
        computeType(c, s);
        return s.toString();
    }

    @objid ("2b16d8aa-55b6-11e2-877f-002564c97630")
    private static void computeType(final ActivityPartition c, final StringBuilder s) {
        final ModelElement type = c.getRepresented();
        s.append(" : ");
        if (type != null) {
            s.append(type.getName());
        }
    }

}
