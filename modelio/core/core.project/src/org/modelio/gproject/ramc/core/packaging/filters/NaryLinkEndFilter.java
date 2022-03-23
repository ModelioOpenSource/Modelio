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
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This filter refuses all NaryLinks:
 * <ul>
 * <li>having at least one not Public nor Protected end.</li>
 * <li>having at least one end's owner not exported.</li>
 * </ul>
 */
@objid ("85529d95-3bc3-41bb-afd5-f711da8b7cf5")
class NaryLinkEndFilter extends LinkTargetFilter {
    @objid ("d441b913-8e1c-41ef-ba81-9bdfefadbade")
    public  NaryLinkEndFilter(IObjectFilter targetFilter, MExpert expert) {
        super(expert, targetFilter);
    }

    @objid ("de10b6ed-41dd-4ee9-b0c6-aa9f912ce97a")
    @Override
    public boolean accept(MObject obj) {
        NaryLink link = ((NaryLinkEnd) obj).getNaryLink();
        for (NaryLinkEnd end : link.getNaryLinkEnd()) {
            if (isValidTarget(end.getSource()) == false) {
                return false;
            }
        }
        return true;
    }

}
