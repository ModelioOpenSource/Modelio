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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E264:
 * <ul>
 * <li>desc = An ExecutionSpecification has to start and end on a single Lifeline.</li>
 * <li>what = The ''{0}'' ExecutionSpecification cannot start and end on different Lifelines.</li>
 * </ul>
 */
@objid ("007a4a5e-e20e-1f69-b3fb-001ec947cd2a")
public class E264Checker implements IChecker {
    @objid ("001631e0-6456-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E264";

    @objid ("0000bcde-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        ExecutionSpecification es = (ExecutionSpecification) object;
        ExecutionOccurenceSpecification start = es.getStart();
        ExecutionOccurenceSpecification finish = es.getFinish();
        if (start == null || finish == null) {
            return;
        }
        
        EList<Lifeline> start_life_line = start.getCovered();
        EList<Lifeline> finish_life_line = finish.getCovered();
        
        if (!start_life_line.equals(finish_life_line)) {
            List<Object> objects = new ArrayList<>();
            objects.addAll(start_life_line);
            objects.addAll(finish_life_line);
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("0000beaa-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=ExecutionSpecification, feature=Start
        plan.registerChecker(this, smMetamodel.getMClass(ExecutionSpecification.class), TriggerType.AnyTrigger, "Start");
        
        // trigger=*, metaclass=ExecutionSpecification, feature=Finish
        plan.registerChecker(this, smMetamodel.getMClass(ExecutionSpecification.class), TriggerType.AnyTrigger, "Finish");
    }

}
