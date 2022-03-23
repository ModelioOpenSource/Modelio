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
package org.modelio.uml.statikdiagram.editor.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Utility class that computes instance symbol.
 * 
 * @author cmarin
 */
@objid ("3544c89f-55b7-11e2-877f-002564c97630")
public class InstanceSymbolProvider {
    /**
     * This class is not instantiable.
     */
    @objid ("3544c8a1-55b7-11e2-877f-002564c97630")
    private  InstanceSymbolProvider() {
        
    }

    /**
     * Get the instance label at the following format: "name : type [min..max]"
     * @param c the instance
     * @return the computed label
     */
    @objid ("3544c8a4-55b7-11e2-877f-002564c97630")
    public static String computeSimpleLabel(Instance c) {
        final StringBuilder s = new StringBuilder(60);
        
        final String name = c.getName();
        
        s.append(name);
        
        computeType(c, s);
        computeCard(c, s);
        return s.toString();
    }

    /**
     * Depending on min and max multiplicities:
     * <ul>
     * <li>unspecified : returns ""
     * <li>0..1 : returns "[0..1]"
     * <li>1..1 : returns ""
     * <li>0..* : returns "[*]"
     * <li>1..* : returns "[1..*]"
     * <li>all other a..b : returns "[a..b]"
     * </ul>
     * @param c The instance.
     * @param s Where the computed cardinality is appended.
     */
    @objid ("3544c8ac-55b7-11e2-877f-002564c97630")
    private static void computeCard(final Instance c, StringBuilder s) {
        final String min = c.getMultiplicityMin();
        final String max = c.getMultiplicityMax();
        
        if (min.isEmpty() && max.isEmpty()) {
            return;
        } else if (min.equals("1") && max.equals("1")) {
            return;
        } else if (min.equals("0") && max.equals("*")) {
            s.append("[*]");
        } else {
            s.append("[");
            s.append(min);
            s.append("..");
            s.append(max);
            s.append("]");
        }
        
    }

    /**
     * Return the absolute path of the instance with its type and cardinality.
     * @param c An instance
     * @return its fully qualified symbol.
     */
    @objid ("3544c8b4-55b7-11e2-877f-002564c97630")
    public static String computeFullQualifiedLabel(Instance c) {
        final StringBuilder s = new StringBuilder(100);
        
        MObject parent = c.getCompositionOwner();
        while (parent != null && !isRoot(parent)) {
            s.insert(0, '.');
            s.insert(0, parent.getName());
        
            parent = parent.getCompositionOwner();
        }
        
        s.append(c.getName());
        
        computeType(c, s);
        computeCard(c, s);
        return s.toString();
    }

    /**
     * Return the path of the instance relative to its namespace with its type and cardinality.
     * @param c An instance
     * @return its symbol relative to its namespace.
     */
    @objid ("3544c8bc-55b7-11e2-877f-002564c97630")
    public static String computeQualifiedLabel(Instance c) {
        final StringBuilder s = new StringBuilder(100);
        
        MObject parent = c.getCompositionOwner();
        while (parent != null && parent instanceof Instance) {
            s.insert(0, '.');
            s.insert(0, parent.getName());
        
            parent = parent.getCompositionOwner();
        }
        
        s.append(c.getName());
        
        computeType(c, s);
        computeCard(c, s);
        return s.toString();
    }

    /**
     * Tells whether the given element is the root package a the project.
     * @param el the element to test
     * @return true if the given element is the root package a the project, else false.
     */
    @objid ("3544c8c4-55b7-11e2-877f-002564c97630")
    private static boolean isRoot(MObject el) {
        // Project is a root
        if (el instanceof Project)
            return true;
        
        // Root package is a root
        final MObject parent = el.getCompositionOwner();
        if (parent == null || parent instanceof Project)
            return true;
        return false;
    }

    @objid ("3544c8cc-55b7-11e2-877f-002564c97630")
    private static void computeType(final Instance c, final StringBuilder s) {
        final NameSpace type = c.getBase();
        s.append(" : ");
        if (type != null) {
            s.append(type.getName());
        }
        
    }

}
