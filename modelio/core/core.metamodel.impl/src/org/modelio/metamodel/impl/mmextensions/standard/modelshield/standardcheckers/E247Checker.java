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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E247:
 * <ul>
 * <li>desc = A NameSpace cannot import itself.</li>
 * <li>what = The ''{1}'' {2} cannot have an element import link towards itself.</li>
 * </ul>
 */
@objid ("00247ca0-e20e-1f69-b3fb-001ec947cd2a")
public class E247Checker implements IChecker {
    @objid ("0072b816-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E247";

    /**
     * C++ reference: ElementImportChecker::checkSelfImport()
     */
    @objid ("0093c8da-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        ElementImport currentElementImport = (ElementImport) object;
        
        NameSpace ns = currentElementImport.getImportingNameSpace();
        NameSpace dest = currentElementImport.getImportedElement();
        
        if (ns != null && dest != null && ns.equals(dest)) {
            List<Object> objects = new ArrayList<>();
            objects.add(ns.getName());
            objects.add(ns.getMClass().getName());
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
        
    }

    @objid ("0093cab0-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=ElementImport, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(ElementImport.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=ElementImport, feature=ImportingNameSpace
        plan.registerChecker(this, smMetamodel.getMClass(ElementImport.class), TriggerType.Create, "ImportingNameSpace");
        
        // trigger=create, metaclass=ElementImport, feature=ImportedElement
        plan.registerChecker(this, smMetamodel.getMClass(ElementImport.class), TriggerType.Create, "ImportedElement");
        
    }

}
