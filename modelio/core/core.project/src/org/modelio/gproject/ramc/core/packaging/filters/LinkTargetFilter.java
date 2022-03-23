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
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Ignore links having not accepted targets.
 */
@objid ("d9ae3f3c-b7de-4437-98ec-d79cdbbe40d7")
class LinkTargetFilter implements IObjectFilter {
    @objid ("10f22126-bb00-4647-80e3-4d1ae188606e")
    private IObjectFilter targetFilter;

    @objid ("803b7536-09f5-47c0-8b33-9c5b3a7ec86e")
    protected final MExpert expert;

    @objid ("7158c7a8-b7bd-4d2f-88a3-3cb5610bbe9b")
    public  LinkTargetFilter(MExpert expert, IObjectFilter targetFilter) {
        this.expert = expert;
        this.targetFilter = targetFilter;
        
    }

    @objid ("ba5cdd20-a3e3-42b1-906a-eddf36c8f474")
    @Override
    public boolean accept(MObject obj) {
        MObject target = this.expert.getTarget(obj);
        return isValidTarget(target);
    }

    @objid ("19d62dca-5415-48ba-bfd2-6ea0744c8fbe")
    protected boolean isValidTarget(MObject target) {
        return target == null || this.targetFilter.accept(target);
    }

    @objid ("faae330c-694e-4fd9-806e-47831f15140b")
    public IObjectFilter getTargetFilter() {
        return this.targetFilter;
    }

    @objid ("5d42947e-f4d6-4dbc-83c8-4e8d897b15fa")
    public void setTargetFilter(IObjectFilter targetFilter) {
        this.targetFilter = targetFilter;
    }

}
