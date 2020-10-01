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

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E272:
 * <ul>
 * <li>desc = An InformationFlow must have an InformationSource and an InformationTarget.</li>
 * <li>what = The ''{0}'' information flow has no information source or no information target.</li>
 * </ul>
 */
@objid ("006168e0-e20e-1f69-b3fb-001ec947cd2a")
public class E272Checker implements IChecker {
    @objid ("000394a4-6456-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E272";

    /**
     * C++ reference: InformationFlowChecker::checkEnds()
     */
    @objid ("0097a98c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null || !(object instanceof InformationFlow))
            return;
        
        // both Source and Target should exist
        InformationFlow flow = (InformationFlow) object;
        if ((flow.getInformationSource() == null) || (flow.getInformationTarget() == null)) {
            report.addEntry(new ModelError(ERRORID, object, Collections.emptyList()));
        }
    }

    @objid ("0097ab4e-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=InformationFlow, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(InformationFlow.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=InformationFlow, feature=InformationSource
        plan.registerChecker(this, smMetamodel.getMClass(InformationFlow.class), TriggerType.Update, "InformationSource");
        
        // trigger=create, metaclass=InformationFlow, feature=InformationTarget
        plan.registerChecker(this, smMetamodel.getMClass(InformationFlow.class), TriggerType.Update, "InformationTarget");
    }

}
