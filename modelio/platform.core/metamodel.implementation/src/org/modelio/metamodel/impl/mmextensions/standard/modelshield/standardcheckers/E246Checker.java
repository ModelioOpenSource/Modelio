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
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E246:
 * <ul>
 * <li>desc = An Association can link an Actor to a Class, an Interface, an Actor, a UseCase or a Component.</li>
 * <li>what = The ''{0}'' role cannnot links a %1 to a %2.</li>
 * </ul>
 */
@objid ("0056cd2c-e20d-1f69-b3fb-001ec947cd2a")
public class E246Checker implements IChecker {
    @objid ("00259a36-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E246";

    /**
     * C++ reference: AssociationChecker::checkLinkedMetaclasses()
     */
    @objid ("008ccd0a-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        AssociationEnd currentRole = (AssociationEnd) object;
        boolean currentNavigability = currentRole.isNavigable();
        
        AssociationEnd otherRole = currentRole.getOpposite();
        if (otherRole == null) {
            // Invalid model, another rule will be triggered...
            return;
        }
        boolean otherNavigability = otherRole.isNavigable();
        
        Classifier csource, cdest;
        
        if (currentNavigability && !otherNavigability) { // THIS SIDE
            csource = currentRole.getSource();
            cdest = currentRole.getTarget();
        } else if (!currentNavigability && otherNavigability) { // OTHER SIDE 
            csource = otherRole.getSource();
            cdest = otherRole.getTarget();
        } else {
            // assume it's from a to b
            csource = currentRole.getSource();
            cdest = otherRole.getSource();
        }
        
        if (csource instanceof Actor) {
            if (!(cdest instanceof Actor) &&
                    !(cdest instanceof Class) &&
                    !(cdest instanceof Interface) &&
                    !(cdest instanceof UseCase)) {
                List<Object> objects = new ArrayList<>();
                objects.add(currentRole);
                objects.add(otherRole);
                report.addEntry(new ModelError(ERRORID, object, objects));
                return;
            }
        }
    }

    @objid ("008ccee0-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=update, metaclass=AssociationEnd, feature=Owner
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, "Source");
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, "Target");
        
        // trigger=update, metaclass=AssociationEnd, feature=Owner
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Create, "Source");
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Create, "Target");
    }

}
