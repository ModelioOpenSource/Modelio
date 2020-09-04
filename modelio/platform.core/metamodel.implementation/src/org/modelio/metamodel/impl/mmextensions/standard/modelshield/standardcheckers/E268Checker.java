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
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E268:
 * <ul>
 * <li>desc =  The LineNumber attribute on an InteractionFragment must always >= 1.</li>
 * <li>what = The ''{0}'' interaction fragment has a line number equal to %1 but it should be greater than or equal to 1.</li>
 * </ul>
 */
@objid ("00567fc0-e20e-1f69-b3fb-001ec947cd2a")
public class E268Checker implements IChecker {
    @objid ("009473e8-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E268";

    /**
     * C++ reference: InteractionModelChecker::checkE268()
     */
    @objid ("0096f0fa-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        InteractionFragment ifn = (InteractionFragment) object; 
        
        // Ignore part decompositions
        if (ifn instanceof PartDecomposition) {
            return;
        }
        
        if (ifn.getLineNumber() < 1) {
            if (ifn instanceof ExecutionSpecification) {
                ExecutionSpecification es = (ExecutionSpecification) ifn;
        
                ExecutionOccurenceSpecification startEos = es.getStart();
                if (startEos != null && (startEos.getLineNumber() < 1)) {
                    List<Object> objects = new ArrayList<>();
                    objects.add(startEos);
                    report.addEntry(new ModelError(ERRORID, object, objects));
                }
        
                ExecutionOccurenceSpecification finishEos = es.getFinish();
                if (finishEos != null && (finishEos.getLineNumber() < 1)) {
                    List<Object> objects = new ArrayList<>();
                    objects.add(finishEos);
                    report.addEntry(new ModelError(ERRORID, object, objects));
                }
            } else {
                report.addEntry(new ModelError(ERRORID, object, Collections.emptyList()));
            }
        }
    }

    @objid ("0096f2c6-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=InteractionFragment, feature=EnclosingInteraction
        plan.registerChecker(this, smMetamodel.getMClass(InteractionFragment.class), TriggerType.AnyTrigger, "EnclosingInteraction");
        
        // trigger=*, metaclass=InteractionFragment, feature=EnclosingOperand
        plan.registerChecker(this, smMetamodel.getMClass(InteractionFragment.class), TriggerType.AnyTrigger, "EnclosingOperand");
        
        // trigger=*, metaclass=InteractionFragment, feature=EnclosingOperand
        plan.registerChecker(this, smMetamodel.getMClass(InteractionFragment.class), TriggerType.AnyTrigger, null);
    }

}
