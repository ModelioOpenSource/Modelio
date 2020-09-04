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

package org.modelio.metamodel.impl.mmextensions.infrastructure.namer.helpers;

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a5411c14-fa8e-4b90-bd32-066af4eec269")
public class GetInfraSiblingsVisitor extends DefaultInfrastructureVisitor {
    @objid ("9d8ba894-2ae2-439f-bb57-f50ae3133047")
    private Set<String> results;

    @objid ("04f08806-a884-440e-9d0d-fa238e76c65e")
    public GetInfraSiblingsVisitor() {
        super();
        this.results = null;
    }

    @objid ("d173549a-d50b-47c6-b706-89675702a24d")
    public Set<String> getSiblings(Element element) {
        this.results = new HashSet<>(16);
        element.accept(this);
        this.results.remove(element.getName());
        return this.results;
    }

    @objid ("dd38eb3f-bad1-47ec-b0a6-7884356d886f")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram theAbstractDiagram) {
        ModelElement owner = theAbstractDiagram.getOrigin();
        if (owner != null) {
            for (Element e : owner.getProduct()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("10c7ba8f-ba1d-4ac6-8aa8-a4b1535cfe9d")
    @Override
    public Object visitElement(Element obj) {
        MObject owner = obj.getCompositionOwner();
        if (owner != null) {
            for (MObject child : owner.getCompositionChildren()) {
                this.results.add(child.getName());
            }
        }
        return null;
    }

}
