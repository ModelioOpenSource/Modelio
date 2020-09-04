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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E266:
 * <ul>
 * <li>desc = A Partition cannot simultaneously have sub-Partitions and contain Nodes.</li>
 * <li>what = The ''{0}'' partition has both sub-Partitions and children.</li>
 * </ul>
 */
@objid ("004ed4c8-e20e-1f69-b3fb-001ec947cd2a")
public class E266Checker implements IChecker {
    @objid ("008e0e04-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E266";

    /**
     * C++ reference: ActivityModelChecker::checkE266()
     */
    @objid ("00966d6a-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        ActivityPartition ap = (ActivityPartition) object;
        
        EList<ActivityPartition> subPartition = ap.getSubPartition();
        EList<ActivityNode> containedNode = ap.getContainedNode();
        if ((subPartition.size() > 0) && (containedNode.size() > 0)) {
            List<Object> objects = new ArrayList<>();
            objects.addAll(subPartition);
            objects.addAll(containedNode);
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("00966f2c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=ActivityPartition, feature=SubPartition
        plan.registerChecker(this, smMetamodel.getMClass(ActivityPartition.class), TriggerType.AnyTrigger, "SubPartition");
        
        // trigger=*, metaclass=ActivityPartition, feature=ContainedNode
        plan.registerChecker(this, smMetamodel.getMClass(ActivityPartition.class), TriggerType.AnyTrigger, "ContainedNode");
    }

}
