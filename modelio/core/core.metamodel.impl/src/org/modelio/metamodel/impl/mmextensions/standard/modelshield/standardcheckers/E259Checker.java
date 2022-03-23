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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E259:
 * <ul>
 * <li>desc = An ActivityNode cannot belong to more than one Activity, StructureNode, ActivityGroup or ConditionalClause.</li>
 * <li>what = The ''{0}'' ActivityNode cannot belong to more than one Activity, StructureNode, ActivityGroup or ConditionalClause.</li>
 * </ul>
 */
@objid ("007756e6-e20e-1f69-b3fb-001ec947cd2a")
public class E259Checker implements IChecker {
    @objid ("0013eb38-6456-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E259";

    /**
     * C++ reference: ActivityModelChecker::checkE259()
     */
    @objid ("000086f6-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        ActivityNode an = (ActivityNode)object;
        if (an != null) {
            List<Object> objects = new ArrayList<>();
        
            StructuredActivityNode ownerNode = an.getOwnerNode();
            if (ownerNode != null) {
                objects.add(ownerNode);
            }
        
            Clause ownerClause = an.getOwnerClause();
            if (ownerClause != null) {
                objects.add(ownerClause);
            }
        
            ActivityPartition ownerPartition= an.getOwnerPartition();
            if (ownerPartition != null) {
                objects.add(ownerPartition);
            }
        
            Activity owner= an.getOwner();
            if (owner != null) {
                objects.add(owner);
            }
        
            if (objects.size() > 1) {
                report.addEntry(new ModelError(ERRORID, object, objects));
            }
        }
        
    }

    @objid ("000088cc-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=ActivityNode, feature=OwnerNode
        plan.registerChecker(this, smMetamodel.getMClass(ActivityNode.class), TriggerType.AnyTrigger, "OwnerNode");
        
        // trigger=*, metaclass=ActivityNode, feature=Owner
        plan.registerChecker(this, smMetamodel.getMClass(ActivityNode.class), TriggerType.AnyTrigger, "Owner");
        
        // trigger=*, metaclass=ActivityNode, feature=OwnerClause
        plan.registerChecker(this, smMetamodel.getMClass(ActivityNode.class), TriggerType.AnyTrigger, "OwnerClause");
        
        // trigger=*, metaclass=ActivityNode, feature=OwnerPartition
        plan.registerChecker(this, smMetamodel.getMClass(ActivityNode.class), TriggerType.AnyTrigger, "OwnerPartition");
        
    }

}
