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

package org.modelio.metamodel.impl.expert.standard.links.impl.creation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation expert for {@link TemplateBinding}.
 * <p>
 * <ul>
 * <li>Template bindings must be between elements of the same metaclass.
 * <li>The target must have template parameters.
 * <li>A template cannot instantiate itself.
 * </ul>
 */
@objid ("7e9c040c-1eb2-11e2-8009-002564c97630")
public class TemplateBindingCreationExpert extends DefaultDelegatingLinkExpert {
    @objid ("7e9c0411-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canLink(MClass linkMetaclass, final MObject from, final MObject to) {
        if (from == null || !canSource(linkMetaclass, from.getMClass())) {
            // Bad from
            return false;
        } else if (to == null || from.equals(to)) {
            // - null not accepted
            // - Cannot instantiate itself
            return false;
        } else if (!from.getMClass().getName().equals(to.getMClass().getName())) {
            // Different metaclasses
            return false;
        } else if (to instanceof NameSpace) {
            // The target must have template parameters
            NameSpace ns = (NameSpace) to;
            return (ns.getTemplate().size() > 0);
        } else if (to instanceof Operation) {
            // The target must have template parameters
            Operation op = (Operation) to;
            return (op.getTemplate().size() > 0);
        } else {
            // Bad target
            return false;
        }
    }

    @objid ("3b3fc888-93f5-4293-a77c-ef16df3d6f2e")
    public TemplateBindingCreationExpert(ILinkExpert defaultExpert) {
        super(defaultExpert);
    }

}
