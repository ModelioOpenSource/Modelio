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
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E253:
 * <ul>
 * <li>desc = A Collaboration can belong to a NameSpace or an Operation, not both.</li>
 * <li>what = The ''{0}'' collaboration cannot simultaneously belong to a namespace and an operation.</li>
 * </ul>
 */
@objid ("005d0e08-e20d-1f69-b3fb-001ec947cd2a")
public class E253Checker implements IChecker {
    @objid ("002a1de0-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E253";

    @objid ("008d3830-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        Collaboration currentCollaboration = (Collaboration) object;
        
        // Check that there is no communication link on a class
        if (currentCollaboration != null) {
            if (currentCollaboration.getOwner() != null && currentCollaboration.getORepresented() != null) {
                report.addEntry(new ModelError(ERRORID, object, Collections.emptyList()));
            }
        }
        
    }

    @objid ("008d3a06-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=Collaboration, feature=Owner
        plan.registerChecker(this, smMetamodel.getMClass(Collaboration.class), TriggerType.AnyTrigger, "Owner");
        
        // trigger=*, metaclass=Collaboration, feature=ORepresented
        plan.registerChecker(this, smMetamodel.getMClass(Collaboration.class), TriggerType.AnyTrigger, "ORepresented");
        
        // trigger=create, metaclass=Collaboration, feature=ORepresented
        plan.registerChecker(this, smMetamodel.getMClass(Collaboration.class), TriggerType.Create, null);
        
    }

}
