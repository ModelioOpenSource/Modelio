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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E255:
 * <ul>
 * <li>desc = An AcceptChangeEventAction cannot have InputPins.</li>
 * <li>what = The element ''{0}'' cannot have input pins.</li>
 * </ul>
 */
@objid ("0043d492-e20e-1f69-b3fb-001ec947cd2a")
public class E255Checker implements IChecker {
    @objid ("008604c0-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E255";

    /**
     * C++ reference: ActivityModelChecker::checkHasNoInputPins()
     */
    @objid ("0095b4f6-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        ActivityAction activityAction = (ActivityAction)object;
        
        if (activityAction.getInput().size() > 0) {
            report.addEntry(new ModelError(ERRORID, object, Collections.emptyList()));
        }
    }

    @objid ("0095b6c2-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=AcceptChangeEventAction, feature=Input
        plan.registerChecker(this, smMetamodel.getMClass(AcceptChangeEventAction.class), TriggerType.AnyTrigger, "Input");
    }

}
