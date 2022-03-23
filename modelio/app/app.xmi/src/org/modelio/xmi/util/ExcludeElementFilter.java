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
package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("5e260539-b603-4799-8e76-6d2b0ef511a2")
public class ExcludeElementFilter implements IObjectFilter {
    @objid ("7d4b213b-eb84-4c3d-8dc3-17ce5a666591")
    private MObject excludedElt;

    @objid ("62087d2d-b33c-4cd1-b359-eeb815753d59")
    public  ExcludeElementFilter(MObject newExcludedElt) {
        this.excludedElt = newExcludedElt;
    }

    @objid ("3aa78576-04ce-48e4-83f6-53503401ba0b")
    public boolean select(MObject eltToBeFiltered) {
        return !this.excludedElt.equals(eltToBeFiltered);
    }

    @objid ("c887aa52-b401-4ace-b693-400e9e7d942f")
    public MObject getExcludedElt() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.excludedElt;
    }

    @objid ("cce6b060-8dcf-4235-b469-bdfc3ef742fa")
    @Override
    public boolean accept(MObject obj) {
        // TODO Auto-generated method stub
        return false;
    }

}
