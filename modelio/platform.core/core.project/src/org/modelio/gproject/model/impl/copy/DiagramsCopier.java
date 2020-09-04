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

package org.modelio.gproject.model.impl.copy;

import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Used to fix the diagram data after having copied a set of elements
 * so that the diagram content represents the copy instead if the original.
 */
@objid ("78eec33a-5b93-418a-9e7f-eb3964e523a4")
class DiagramsCopier {
    /**
     * Fix the diagram copies data to represent the elements copies.
     * <p>
     * The given map contains pairs of elements: the original element and its
     * copy.
     * @param localDiagram the diagram to modify
     * @param pairs pairs of {source, dest} elements.
     */
    @objid ("ebe253bd-c5ea-42f5-a514-7c8fb223bc3f")
    public void fixDiagram(AbstractDiagram localDiagram, Map<SmObjectImpl, SmObjectImpl> pairs) {
        String content = localDiagram.getUiData();
        if (content.startsWith("<?xml")) {
            // old uncompressed format
        } else {
            content = org.modelio.vcore.utils.UUBase64Compressor.decompress(content);
        }
        
        for (Entry<SmObjectImpl, SmObjectImpl> pair : pairs.entrySet()) {
            SmObjectImpl refElt = pair.getKey();
            SmObjectImpl localElt = pair.getValue();
            content = content.replace(refElt.getUuid().toString(), localElt.getUuid().toString());
        }
        
        localDiagram.setUiData(org.modelio.vcore.utils.UUBase64Compressor.compress(content));
    }

}
