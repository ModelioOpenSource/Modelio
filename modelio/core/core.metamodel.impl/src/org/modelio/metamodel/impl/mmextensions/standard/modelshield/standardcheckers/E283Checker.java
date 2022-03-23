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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E283:
 * <ul>
 * <li>desc = The Cluster, Owner, InternalOwner and OwnerTemplateParameter relationships are exclusive in an Instance.</li>
 * <li>what = The ''{0}'' instance has several owners. It should only have one.</li>
 * </ul>
 */
@objid ("00618f50-e20d-1f69-b3fb-001ec947cd2a")
public class E283Checker implements IChecker {
    @objid ("000e57cc-44b0-1f6b-b3fb-001ec947cd2a")
    private static final String ERRORID = "E283";

    @objid ("008d884e-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        // E283: Owner and OwnerTemplateParameter relationships are exclusive in Instance
        Instance me = (Instance)object;
        
        NameSpace namespaceOwner = me.getOwner();
        TemplateParameter templateOwner = me.getOwnerTemplateParameter();
        
        if (namespaceOwner != null && templateOwner != null) {
            List<Object> objects = new ArrayList<>();
            objects.add(namespaceOwner);
            objects.add(templateOwner);
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
        
    }

    @objid ("008d8a10-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Instance, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Instance.class), TriggerType.Create, null);
        
        // trigger=move, metaclass=Instance, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Instance.class), TriggerType.Move, null);
        
    }

}
