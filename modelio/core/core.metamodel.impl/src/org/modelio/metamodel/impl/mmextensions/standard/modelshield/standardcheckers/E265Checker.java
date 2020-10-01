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
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E265:
 * <ul>
 * <li>desc = ConditionalNodes must not directly own ActivityNodes (contrary to other StructuredActivityNodes such as LoopNodes).</li>
 * <li>what = The ''{0}'' conditional node cannot contain activity nodes.</li>
 * </ul>
 */
@objid ("004d1458-e20e-1f69-b3fb-001ec947cd2a")
public class E265Checker implements IChecker {
    @objid ("008cbdb0-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E265";

    /**
     * C++ reference: ActivityModelChecker::checkE265()
     */
    @objid ("00965334-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        ConditionalNode cn = (ConditionalNode)object;
        EList<ActivityNode> body = cn.getBody();
        if (body.size() > 0) {
            List<Object> objects = new ArrayList<>();
            objects.addAll(body);
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("00965500-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=ConditionalNode, feature=Body
        plan.registerChecker(this, smMetamodel.getMClass(ConditionalNode.class), TriggerType.AnyTrigger, "Body");
    }

}
