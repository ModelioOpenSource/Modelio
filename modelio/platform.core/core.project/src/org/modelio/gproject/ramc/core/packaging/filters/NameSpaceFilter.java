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
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This filter refuses all NameSpaces that are not Public nor Protected.
 */
@objid ("6179089b-c746-11e1-96e9-001ec947ccaf")
class NameSpaceFilter implements IObjectFilter {
    @objid ("61790867-c746-11e1-96e9-001ec947ccaf")
    @Override
    public boolean accept(MObject obj) {
        NameSpace n = (NameSpace) obj;
        VisibilityMode vis = n.getVisibility();
        return vis == VisibilityMode.PUBLIC || vis==VisibilityMode.PROTECTED;
    }

}
