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
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E241:
 * <ul>
 * <li>desc = A Manifestation cannot manifest itself.</li>
 * <li>what = A manifestation of the ''{1}'' artifact must not use itself.</li>
 * </ul>
 */
@objid ("00197378-e20e-1f69-b3fb-001ec947cd2a")
public class E241Checker implements IChecker {
    @objid ("006b0e54-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E241";

    /**
     * C++ reference: ManifestationChecker::checkSelfUtilized()
     */
    @objid ("00930cb0-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        Manifestation currentManifestation = (Manifestation) object;
        if (currentManifestation.equals(currentManifestation.getUtilizedElement())) {
            List<Object> objects = new ArrayList<>();
            objects.add(currentManifestation.getOwner());
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
        
    }

    @objid ("00930e7c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Manifestation, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Manifestation.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Manifestation, feature=UtilizedElement
        plan.registerChecker(this, smMetamodel.getMClass(Manifestation.class), TriggerType.Create, "UtilizedElement");
        
    }

}
