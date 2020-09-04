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

package org.modelio.vcore.smkernel.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.collections.TopologicalSorter;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;

/**
 * Sorts metamodel fragments by dependencies.
 * @author cmarin
 * @since 3.6
 */
@objid ("42d2bcf9-acf3-480d-8add-8c2720ab6cf8")
public final class MMFragmentTopologicalSorter<T extends MMetamodelFragment> extends TopologicalSorter<T> {
    @objid ("1fa147e0-3e28-4aa7-bded-e1db715bcb4c")
    private Collection<T> frags;

    @objid ("fdb7fc2c-6224-4ec5-bb61-428839c0b228")
    public MMFragmentTopologicalSorter(Collection<T> frags) {
        this.frags = frags;
    }

    @objid ("cfa946b5-902a-4a92-9173-6f4b0984232e")
    @Override
    public Collection<T> getNodes() {
        return this.frags;
    }

    @objid ("e186e402-0492-4a6f-a01c-7a14d21904e6")
    @Override
    public Collection<T> getAdjacent(MMetamodelFragment node) {
        Collection<VersionedItem<MMetamodelFragment>> neededFragments = node.getNeededFragments();
        if (neededFragments.isEmpty()) {
            return Collections.emptyList();
        } else {
            Collection<T> ret = new ArrayList<>(neededFragments.size());
            for (VersionedItem<MMetamodelFragment> needed : neededFragments) {
                for (T f : this.frags) {
                    if (f.getName().equals(needed.getName())) {
                        // skip version checking
                        ret.add(f);
                    }
                }
            }
        
            return ret;
        }
    }

}
