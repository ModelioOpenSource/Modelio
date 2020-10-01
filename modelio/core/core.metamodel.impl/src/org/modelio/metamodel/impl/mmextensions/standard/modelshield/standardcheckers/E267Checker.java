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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E267:
 * <ul>
 * <li>desc = ExecutionSpecification.Start.LineNumber must be less than ExecutionSpecification.Finish.LineNumber.</li>
 * <li>what = The ''{0}'' execution specification must have Start.LineNumber < Finish.LineNumber.</li>
 * </ul>
 */
@objid ("0051c0b6-e20e-1f69-b3fb-001ec947cd2a")
public class E267Checker implements IChecker {
    @objid ("00904e44-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E267";

    /**
     * C++ reference: InteractionModelChecker::checkE267()
     */
    @objid ("0096a1f4-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        ExecutionSpecification es = (ExecutionSpecification) object; 
        ExecutionOccurenceSpecification start  = es.getStart();
        ExecutionOccurenceSpecification finish = es.getFinish();
        
        if (start == null || finish == null) {
            return;
        }
        
        if ((start.getLineNumber() >= finish.getLineNumber())) {
            List<Object> objects = new ArrayList<>();
            objects.add(start);
            objects.add(finish);
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("0096a3c0-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=ExecutionSpecification, feature=Start
        plan.registerChecker(this, smMetamodel.getMClass(ExecutionSpecification.class), TriggerType.AnyTrigger, "Start");
        
        // trigger=*, metaclass=ExecutionSpecification, feature=Finish
        plan.registerChecker(this, smMetamodel.getMClass(ExecutionSpecification.class), TriggerType.AnyTrigger, "Finish");
    }

}
