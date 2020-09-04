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

package org.modelio.diagram.editor.statik.elements.namespaceheader;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Symbol provider for namespaces.
 */
@objid ("359d48fb-55b7-11e2-877f-002564c97630")
public class NamespaceSymbolProvider {
    /**
     * Compute a fully qualified name.
     * @param c a namespace
     * @param withVisibility true to show visibility.
     * @return the computed symbol.
     */
    @objid ("359d48fd-55b7-11e2-877f-002564c97630")
    public static String computeFullQualifiedLabel(final NameSpace c, final boolean withVisibility) {
        final StringBuilder s = new StringBuilder(100);
        
        ModelTree parent = c.getOwner();
        while (parent != null && !isRoot(parent)) {
            s.insert(0, '.');
            s.insert(0, parent.getName());
        
            parent = parent.getOwner();
        }
        
        s.append(c.getName());
        s.insert(0, computeVisibility(c, withVisibility));
        return s.toString();
    }

    /**
     * Compute a qualified name.
     * @param c a namespace
     * @param withVisibility true to show visibility.
     * @return the computed symbol.
     */
    @objid ("359d4908-55b7-11e2-877f-002564c97630")
    public static String computeQualifiedLabel(final NameSpace c, final boolean withVisibility) {
        final ModelTree parent = c.getOwner();
        if (parent == null || isRoot(parent))
            return computeVisibility(c, withVisibility) + c.getName();
        else
            return computeVisibility(c, withVisibility) + parent.getName() + "." + c.getName();
    }

    /**
     * Compute a simple name.
     * @param c a namespace
     * @param withVisibility true to show visibility.
     * @return the computed symbol.
     */
    @objid ("359d4912-55b7-11e2-877f-002564c97630")
    public static String computeSimpleLabel(final NameSpace c, final boolean withVisibility) {
        return computeVisibility(c, withVisibility) + c.getName();
    }

    @objid ("359d491d-55b7-11e2-877f-002564c97630")
    private static String computeVisibility(final NameSpace el, final boolean withVisibility) {
        if (withVisibility) {
            switch (el.getVisibility()) {
                case PUBLIC:
                    return "+";
                case PROTECTED:
                    return "#";
                case PRIVATE:
                    return "-";
                case PACKAGEVISIBILITY:
                    return "~";
                default:
                    return " ";
            }
        } else {
            return "";
        }
    }

    /**
     * Tells whether the given element is the root package a the project.
     * @param el the element to test
     * @return true if the given element is the root package a the project, else false.
     */
    @objid ("359d4926-55b7-11e2-877f-002564c97630")
    private static boolean isRoot(ModelTree el) {
        // Project is a root
        if (el instanceof Project)
            return true;
        
        // Root package is a root
        final MObject parent = el.getCompositionOwner();
        if (parent == null || parent instanceof Project)
            return true;
        return false;
    }

}
