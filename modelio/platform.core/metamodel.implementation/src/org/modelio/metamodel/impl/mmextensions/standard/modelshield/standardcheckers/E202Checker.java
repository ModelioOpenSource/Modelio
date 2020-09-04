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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E202:
 * <ul>
 * <li>desc = In a Transition, the 'Processing' relationship and the 'Effect' Attribute must not be defined at the same time.</li>
 * <li>what = The ''{0}'' transition cannot simultaneously define the 'Processing' relationship with the ''{1}'' method, and the 'Effect' attribute with the ''{2}'' value.</li>
 * </ul>
 */
@objid ("006d4a70-e20d-1f69-b3fb-001ec947cd2a")
public class E202Checker implements IChecker {
    @objid ("0036cdec-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E202";

    @objid ("008e5ca6-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        Transition currentTransition = (Transition) object;
        
        Operation processed = currentTransition.getProcessed();
        String effect = currentTransition.getEffect();
        if (processed != null && effect.length() > 0) {
            List<Object> objects = new ArrayList<>();
            objects.add(processed);
            objects.add(effect);
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("008e5e72-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        plan.registerChecker(this, smMetamodel.getMClass(Transition.class), TriggerType.AnyTrigger, "Processed");
        plan.registerChecker(this, smMetamodel.getMClass(Transition.class), TriggerType.AnyTrigger, "Processed");
    }

}
