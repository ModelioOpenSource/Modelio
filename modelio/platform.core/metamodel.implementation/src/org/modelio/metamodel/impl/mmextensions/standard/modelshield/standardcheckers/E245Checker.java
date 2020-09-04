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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E245:
 * <ul>
 * <li>desc = A BindableInstance cannot belong simultaneously to a Classifier, a Collaboration and an Instance.</li>
 * <li>what = The ''{0}'' part can belong to only one of the following elements : ''{1}'', ''{2}'', ''{3}''.</li>
 * </ul>
 */
@objid ("001fe28a-e20e-1f69-b3fb-001ec947cd2a")
public class E245Checker implements IChecker {
    @objid ("006f9334-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E245";

    @objid ("00937682-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        BindableInstance currentBindableInstance = (BindableInstance) object;
        
        List<Object> objects = new ArrayList<>();
        
        Instance cluster = currentBindableInstance.getCluster();
        if (cluster != null) {
            objects.add(cluster);
        }
        
        Classifier internalOwner = currentBindableInstance.getInternalOwner();
        if (internalOwner != null) {
            objects.add(internalOwner);
        }
        
        NameSpace owner = currentBindableInstance.getOwner();
        if (owner != null) {
            objects.add(owner);
        }
        
        if (objects.size() > 1) {
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("00937844-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=BindableInstance, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(BindableInstance.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=BindableInstance, feature=Cluster
        plan.registerChecker(this, smMetamodel.getMClass(BindableInstance.class), TriggerType.Create, "Cluster");
        
        // trigger=create, metaclass=BindableInstance, feature=InternalOwner
        plan.registerChecker(this, smMetamodel.getMClass(BindableInstance.class), TriggerType.Create, "InternalOwner");
        
        // trigger=create, metaclass=BindableInstance, feature=Owner
        plan.registerChecker(this, smMetamodel.getMClass(BindableInstance.class), TriggerType.Create, "Owner");
    }

}
