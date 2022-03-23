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
package org.modelio.gproject.ramc.core.packaging.filters;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This filter refuses all TaggedValues which type is not exported.
 */
@objid ("61790926-c746-11e1-96e9-001ec947ccaf")
class TaggedValueFilter implements IObjectFilter {
    @objid ("6179090c-c746-11e1-96e9-001ec947ccaf")
    private Collection<TagType> acceptedTagTypes = new HashSet<>();

    @objid ("a0a2002c-ef78-4d93-9abe-59d6688e80db")
    private Artifact artifact;

    @objid ("6179085f-c746-11e1-96e9-001ec947ccaf")
    @Override
    public boolean accept(MObject obj) {
        TaggedValue t = (TaggedValue) obj;
        return t.getAnnoted() == this.artifact || this.acceptedTagTypes.contains(t.getDefinition());
    }

    @objid ("617908bb-c746-11e1-96e9-001ec947ccaf")
    public void addTagType(TagType tagType) {
        this.acceptedTagTypes.add(tagType);
    }

    @objid ("0cf24452-be57-4f76-be75-528b37dbb865")
    public  TaggedValueFilter(Artifact artifact) {
        this.artifact = artifact;
    }

}
