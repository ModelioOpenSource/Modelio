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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E270:
 * <ul>
 * <li>desc = An ActivityEdge must have a source and a target.</li>
 * <li>what = The ''{0}'' activity edge does not have a source and a target.</li>
 * </ul>
 */
@objid ("005e6e1a-e20e-1f69-b3fb-001ec947cd2a")
public class E270Checker implements IChecker {
    @objid ("00015400-6456-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E270";

    @objid ("009774ee-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null || !(object instanceof ActivityEdge))
            return;
        
        ActivityEdge edge = (ActivityEdge) object;
        
        if (edge.getSource() == null || edge.getTarget() == null) {
            report.addEntry(new ModelError(ERRORID, object, Collections.emptyList()));
        }
    }

    @objid ("009776ba-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=ActivityEdge, feature=Source
        plan.registerChecker(this, smMetamodel.getMClass(ActivityEdge.class), TriggerType.Update, "Source");
        
        // trigger=*, metaclass=ActivityEdge, feature=Target
        plan.registerChecker(this, smMetamodel.getMClass(ActivityEdge.class), TriggerType.Update, "Target");
        
        // trigger=create, metaclass=ActivityEdge, feature=Target
        plan.registerChecker(this, smMetamodel.getMClass(ActivityEdge.class), TriggerType.Create, null);
    }

}
