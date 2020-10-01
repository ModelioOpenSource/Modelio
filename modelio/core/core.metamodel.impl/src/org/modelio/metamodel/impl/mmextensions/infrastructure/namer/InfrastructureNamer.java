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

package org.modelio.metamodel.impl.mmextensions.infrastructure.namer;

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impl.mmextensions.infrastructure.namer.helpers.GetInfraNameVisitor;
import org.modelio.metamodel.impl.mmextensions.infrastructure.namer.helpers.GetInfraSiblingsVisitor;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8c6f81c5-811f-48b9-86cf-b2ff98e34948")
public class InfrastructureNamer implements IElementNamer {
/*
     * This implementation simply returns the metaclass name.
     */
    @objid ("91a7bf4b-7623-4af9-9e48-d04764707cbd")
    @Override
    public String getBaseName(MClass metaclass) {
        assert (metaclass != null);
        return metaclass.getName();
    }

    /**
     * This implementation uses an auxiliary visitor to generate a name for the object that can be smarter than just its metaclass
     * name.
     */
    @objid ("da7a1bff-7972-4906-98b6-041ea3386c85")
    @Override
    public String getBaseName(final MObject object) {
        assert (object != null);
        GetInfraNameVisitor visitor = new GetInfraNameVisitor();
        return visitor.getDefaultName(object);
    }

    /**
     * This implementation uses an auxiliary visitor to generate a name for the object that can be smarter than just its metaclass
     * name.
     */
    @objid ("e160c473-61c0-4c7e-8d54-e8e608bfd451")
    @Override
    public String getUniqueName(MObject object) {
        assert (object != null);
        return getUniqueName(getBaseName(object), object);
    }

    /**
     * This implementation returns basename unless this name is already used in which case it is completed with the smaller possible
     * value for X an integer such as basenameX does not exist.
     */
    @objid ("984788f5-7b46-4afc-b21d-0868b768fbc0")
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

    @objid ("62a4e7bb-8ddd-464c-b494-a5400d5a128c")
    private static Set<String> getSiblingIdentifiers(MObject object) {
        final GetInfraSiblingsVisitor v = new GetInfraSiblingsVisitor();
        final Set<String> results = v.getSiblings((Element) object);
        return results;
    }

}
