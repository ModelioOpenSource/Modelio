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
package org.modelio.gproject.mtools.merge;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Used to fix the diagram data after having merged a set of elements
 * so that the diagram content represents the target instead of the original.
 */
@objid ("868739bd-7617-42f8-ba99-c77b889cd9e7")
class DiagramsMerger {
    /**
     * Fix the diagram copies data to represent the elements copies.
     * <p>
     * The given map contains pairs of elements: the original element and its
     * copy.
     * @param localDiagram the diagram to modify
     * @param sources source elements to replace by the target
     * @param target the target element in which sources are merged
     */
    @objid ("c4978cfd-e62c-4f46-865f-843313ef2a83")
    public void fixDiagram(AbstractDiagram localDiagram, Collection<MObject> sources, MObject target) {
        String content = localDiagram.getUiData();
        if (content.startsWith("<?xml")) {
            // old uncompressed format
        } else {
            content = org.modelio.vcore.utils.UUBase64Compressor.decompress(content);
        }
        
        String targetUUid = target.getUuid().toString();
        
        // If the diagram already displays the target element, do nothing.
        // Reloading the diagram will automatically update it.
        if (content.contains(targetUUid))
            return;
        
        // Replace all reference of sources by references of target.
        for (MObject source : sources) {
            content = content.replace(source.getUuid().toString(), targetUUid);
        }
        
        localDiagram.setUiData(org.modelio.vcore.utils.UUBase64Compressor.compress(content));
        
    }

}
