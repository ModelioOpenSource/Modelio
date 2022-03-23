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
package org.modelio.vstore.exml.local.save;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Dependencies of a CMS node.
 */
@objid ("fd26ba04-5986-11e1-991a-001ec947ccaf")
class ElementDependencies {
    /**
     * reference to owned CMS nodes
     */
    @objid ("fd21f5b0-5986-11e1-991a-001ec947ccaf")
    final Set<MObject> compNodes = new TreeSet<>(MObjectComparator.instance);

    /**
     * references to CMS  nodes
     */
    @objid ("fd21f5d0-5986-11e1-991a-001ec947ccaf")
    final Set<MObject> refNodes = new TreeSet<>(MObjectComparator.instance);

    /**
     * references to non CMS nodes
     */
    @objid ("fd21f5d4-5986-11e1-991a-001ec947ccaf")
    final Set<MObject> refDeps = new TreeSet<>(MObjectComparator.instance);

    /**
     * External references.
     * <p>
     * References to elements outside the repository.
     */
    @objid ("f09fc3d5-92d7-11e1-be7e-001ec947ccaf")
    final Set<MObject> extDeps = new TreeSet<>(MObjectComparator.instance);

    @objid ("fd21f5da-5986-11e1-991a-001ec947ccaf")
    MObject parentNode;

    /**
     * MObject comparator that sorts object by name then by UUID.
     */
    @objid ("70bd385f-ee52-458a-b1ad-9e80f0c510f5")
    private static class MObjectComparator implements Comparator<MObject> {
        @objid ("94f883c7-2e1e-4c42-8a25-4f8a4b03ea49")
        public static final Comparator<MObject> instance = new MObjectComparator();

        @objid ("e293fd37-5218-4e40-a88c-0b8b5370ad52")
        @Override
        public int compare(MObject o1, MObject o2) {
            if (o1 == o2) { // NOPMD by cma on 2/6/17 4:10 PM
                return 0;
            }
            
            int comp = o1.getName().compareTo(o2.getName());
            if (comp != 0) {
                return comp;
            }
            return o1.getUuid().compareTo(o2.getUuid());
        }

    }

}
