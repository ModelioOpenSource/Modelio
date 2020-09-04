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

package org.modelio.gproject.ramc.core.packaging.filters;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This filter refuses Dependencies not having any exported stereotypes, or targeting non-exported elements.
 */
@objid ("61790891-c746-11e1-96e9-001ec947ccaf")
class DependencyFilter extends LinkTargetFilter {
    @objid ("6179091b-c746-11e1-96e9-001ec947ccaf")
    private Collection<Stereotype> exportedStereotypes = new HashSet<>();

    @objid ("6179079b-c746-11e1-96e9-001ec947ccaf")
    @Override
    public boolean accept(MObject obj) {
        ModelElement el = (ModelElement) obj;
        
        for (Stereotype ste : el.getExtension()) {
            if (this.exportedStereotypes.contains(ste)) {
                return super.accept(obj);
            }
        }
        return false;
    }

    @objid ("6179079c-c746-11e1-96e9-001ec947ccaf")
    public void addStereotype(Stereotype stereotype) {
        this.exportedStereotypes.add(stereotype);
    }

    @objid ("e63dd756-9a89-4fae-a768-d719d6232403")
    public DependencyFilter(IObjectFilter targetFilter, MExpert expert) {
        super(expert, targetFilter);
    }

}
