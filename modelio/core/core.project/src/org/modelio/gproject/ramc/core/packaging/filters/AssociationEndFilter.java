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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This filter refuses all Associations:
 * <ul>
 * <li>having at least one not Public nor Protected end.</li>
 * <li>having at least one end's owner not exported.</li>
 * </ul>
 */
@objid ("d0c46f18-ab12-46e0-a5d4-b41ac46ba594")
class AssociationEndFilter extends LinkTargetFilter {
    @objid ("f1cdd5c2-9856-4d6b-96e0-503d201e445f")
    public  AssociationEndFilter(IObjectFilter targetFilter, MExpert expert) {
        super(expert, targetFilter);
    }

    @objid ("be75ff02-7402-498b-a1af-31275ceed42d")
    @Override
    public boolean accept(MObject obj) {
        AssociationEnd e = (AssociationEnd) obj;
        
        VisibilityMode vis = e.getVisibility();
        if (vis != VisibilityMode.PUBLIC && vis != VisibilityMode.PROTECTED) {
            return false;
        }
        
        // Check the target is to be exported
        MObject target = this.expert.getTarget(obj);
        if (!isValidTarget(target)) {
            return false;
        }
        
        // Check the source is to be exported
        MObject source = this.expert.getSource(obj);
        if (!isValidTarget(source)) {
            return false;
        }
        return true;
    }

}
