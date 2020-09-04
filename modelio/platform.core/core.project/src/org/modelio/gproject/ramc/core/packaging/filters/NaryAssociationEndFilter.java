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

package org.modelio.gproject.ramc.core.packaging.filters;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This filter refuses all NaryAssociations:
 * <ul>
 * <li>having at least one not Public nor Protected end.</li>
 * <li>having at least one end's owner not exported.</li>
 * </ul>
 */
@objid ("48ee9754-1335-498a-8ade-ced52c73a031")
class NaryAssociationEndFilter extends LinkTargetFilter {
    @objid ("ba31c406-b36c-4502-8d8b-81d6966af76b")
    public NaryAssociationEndFilter(IObjectFilter targetFilter, MExpert expert) {
        super(expert, targetFilter);
    }

    @objid ("dde68c0c-aad0-4015-a02b-def62eff74cb")
    @Override
    public boolean accept(MObject obj) {
        NaryAssociation association = ((NaryAssociationEnd) obj).getNaryAssociation();
        for (NaryAssociationEnd end : association.getNaryEnd()) {
            VisibilityMode vis = end.getVisibility();
            if (vis != VisibilityMode.PUBLIC && vis != VisibilityMode.PROTECTED) {
                return false;
            }
            if (isValidTarget(end.getOwner()) == false) {
                return false;
            }
        }
        return true;
    }

}
