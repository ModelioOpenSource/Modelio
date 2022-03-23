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
package org.modelio.core.modelshield.engine.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

@objid ("3e6d4cfb-42a4-4cd7-b920-a4f206cbea80")
public class CompositePlan implements IModelShieldPlan {
    @objid ("e5a6573c-2210-44cb-a765-9eb3c6dee43a")
    Map<MMetamodelFragment, IModelShieldPlan> plans = new HashMap<>();

    @objid ("7315423e-29b1-442e-86a1-f004335a0758")
    public void addPlan(MMetamodelFragment frag, IModelShieldPlan plan) {
        this.plans.put(frag, plan);
    }

    @objid ("7c700934-427d-450c-a2c3-011452d77e06")
    public void removeFragment(MMetamodelFragment frag) {
        this.plans.remove(frag);
    }

    @objid ("93de42a5-42d3-413f-8b55-9c6decc7275f")
    @Override
    public Collection<IChecker> getApplicableCheckers(TriggerType trigger, MObject obj, String feature) {
        Collection<IChecker> ret = new ArrayList<>();
        
        for (IModelShieldPlan plan : this.plans.values()) {
            ret.addAll(plan.getApplicableCheckers(trigger, obj, feature));
        }
        return ret;
    }

}
