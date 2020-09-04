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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E276:
 * <ul>
 * <li>desc = An activity diagram can only be defined on an activity.</li>
 * <li>what = The ''{0}'' activity diagram is defined on %1, which is a(n) %2.</li>
 * </ul>
 */
@objid ("0064c45e-e20e-1f69-b3fb-001ec947cd2a")
public class E276Checker implements IChecker {
    @objid ("00060702-6456-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E276";

    @objid ("0097de16-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null)
            return;
        
        ActivityDiagram diagram = (ActivityDiagram) object;
        if (diagram.getOrigin() == null || (diagram.getOrigin() instanceof Activity))
            return;
        
        report.addEntry(new ModelError(ERRORID, object, Arrays.asList((Object) diagram.getOrigin())));
    }

    @objid ("0097dfe2-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=ModelElement, feature=product
        plan.registerChecker(this, smMetamodel.getMClass(ActivityDiagram.class), TriggerType.Update, "origin");
    }

}
