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
package org.modelio.metamodel.impl.mmextensions.standard.namer;

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impl.mmextensions.standard.namer.helpers.GetDefaultNameVisitor;
import org.modelio.metamodel.impl.mmextensions.standard.namer.helpers.GetSiblingsVisitor;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("9645f297-5a09-40f5-aad9-c8bc9728fa7a")
class BasicNamer implements IElementNamer {
    /*
         * This implementation simply returns the metaclass name.
         */
    @objid ("14ba5eb0-312a-43cf-a036-927b48ed2ad0")
    @Override
    public String getBaseName(MClass metaclass) {
        assert (metaclass != null);
        return metaclass.getName();
    }

    /**
     * This implementation uses an auxiliary visitor to generate a name for the object that can be smarter than just its metaclass
     * name.
     */
    @objid ("bda52cac-fb3d-4dad-bb41-dde2822f586a")
    @Override
    public String getBaseName(final MObject object) {
        assert (object != null);
        GetDefaultNameVisitor visitor = new GetDefaultNameVisitor();
        return visitor.getDefaultName(object);
    }

    /**
     * This implementation uses an auxiliary visitor to generate a name for the object that can be smarter than just its metaclass
     * name.
     */
    @objid ("568f6b21-de6b-45c9-a128-311aa314dc41")
    @Override
    public String getUniqueName(MObject object) {
        assert (object != null);
        return getUniqueName(getBaseName(object), object);
    }

    /**
     * This implementation returns basename unless this name is already used in which case it is completed with the smaller possible
     * value for X an integer such as basenameX does not exist.
     */
    @objid ("5e1411a1-e251-4947-9024-73c008633d7f")
    @Override
    public String getUniqueName(String basename, MObject object) {
        boolean diagramsuffix = (object instanceof AbstractDiagram);
        
        assert (basename != null);
        assert (object != null);
        
        final Set<String> siblingIdentifiers = getSiblingIdentifiers(object);
        
        String newName = basename;
        int i = 0;
        while (siblingIdentifiers.contains(newName)) {
            i = i + 1;
            if (diagramsuffix) {
                newName = basename + " (" + i + ")";
            } else {
                newName = basename + i;
            }
        }
        return newName;
    }

    @objid ("6193d157-42a9-4caf-bf1f-49325f80a70e")
    private static Set<String> getSiblingIdentifiers(MObject object) {
        final GetSiblingsVisitor v = new GetSiblingsVisitor();
        final Set<String> results = v.getSiblings((Element) object);
        return results;
    }

}
