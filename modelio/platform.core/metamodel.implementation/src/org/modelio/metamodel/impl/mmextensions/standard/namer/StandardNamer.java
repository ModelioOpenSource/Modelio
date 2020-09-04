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

package org.modelio.metamodel.impl.mmextensions.standard.namer;

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.namer.helpers.GetSiblingsVisitor;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IElementNamer}.
 * 
 * This implementation build names for new elements using the smarter available strategy (depending on the metaclass).
 * 
 * 
 * @author phv
 */
@objid ("830535cf-bb22-479b-9071-e4a5136dc30d")
public class StandardNamer implements IElementNamer {
    @objid ("018b1048-f7d5-4645-990e-29d19b6680ea")
    @Override
    public String getBaseName(final MClass metaclass) {
        assert (metaclass != null);
        return metaclass.getName();
    }

    /**
     * This implementation uses an auxiliary visitor to generate a name for the object that can be smarter than just its metaclass
     * name.
     */
    @objid ("83f6e160-643d-4680-b1c0-80dffc4b0267")
    @Override
    public String getBaseName(final MObject object) {
        assert (object != null);
        
        IElementNamer effectiveNamer = new BasicNamer();
        return effectiveNamer.getBaseName(object);
    }

    /**
     * This implementation is similar to calling: getUniqueName(getBaseName(object), object)
     */
    @objid ("eb4b29f4-cf90-47dd-abaf-9a3a2e0a26ce")
    @Override
    public final String getUniqueName(final MObject object) {
        assert (object != null);
        return getUniqueName(getBaseName(object), object);
    }

    /**
     * This implementation returns basename unless this name is already used in which case it is completed with the smaller possible
     * value for X an integer such as basenameX does not exist.
     */
    @objid ("2df5b5eb-54fb-4736-af51-6bea604a3c4a")
    @Override
    public String getUniqueName(final String basename, final MObject object) {
        assert (basename != null);
        assert (object != null);
        
        IElementNamer effectiveNamer = new BasicNamer();
        return effectiveNamer.getUniqueName(basename, object);
    }

    @objid ("473d2aea-fef9-43ea-b5da-8cb7bf087dff")
    private static Set<String> getSiblingIdentifiers(MObject object) {
        final GetSiblingsVisitor v = new GetSiblingsVisitor();
        final Set<String> results = v.getSiblings((Element) object);
        return results;
    }

}
