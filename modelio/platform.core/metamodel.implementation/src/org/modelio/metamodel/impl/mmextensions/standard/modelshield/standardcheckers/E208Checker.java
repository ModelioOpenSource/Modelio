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

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E208:
 * <ul>
 * <li>desc = A UseCaseDependency must be stereotyped <<include>> or <<extend>>.</li>
 * <li>what = The dependency between the ''{0}'' and ''{1}'' use cases is not stereotyped <<include>> or <<extend>>.</li>
 * </ul>
 */
@objid ("007e207a-e20d-1f69-b3fb-001ec947cd2a")
public class E208Checker implements IChecker {
    @objid ("0043556c-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E208";

    @objid ("008f80b8-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        UseCaseDependency currentUseCaseDependency = (UseCaseDependency) object;
        
        UseCase origin = currentUseCaseDependency.getOrigin();
        UseCase target = currentUseCaseDependency.getTarget();
        if (origin != null && target != null && !currentUseCaseDependency.isStereotyped("ModelerModule", "include") && !currentUseCaseDependency.isStereotyped("ModelerModule", "extend")) {
            report.addEntry(new ModelError(ERRORID, object, Collections.emptyList()));
        }
    }

    @objid ("008f8284-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        plan.registerChecker(this, smMetamodel.getMClass(UseCaseDependency.class), TriggerType.Update, "Extension");
        plan.registerChecker(this, smMetamodel.getMClass(UseCaseDependency.class), TriggerType.Create, null);
    }

}
