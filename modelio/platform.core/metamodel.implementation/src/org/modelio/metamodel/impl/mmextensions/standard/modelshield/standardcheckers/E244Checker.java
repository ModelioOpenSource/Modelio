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
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E244:
 * <ul>
 * <li>desc = The visibility of a PackageImport must be public or private.</li>
 * <li>what = The package import between ''{0}'' (%1) and '2' (%3) must not be %4.</li>
 * </ul>
 */
@objid ("001cfbb0-e20e-1f69-b3fb-001ec947cd2a")
public class E244Checker implements IChecker {
    @objid ("006d21bc-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E244";

    @objid ("00934176-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        PackageImport currentElementImport = (PackageImport) object;
        
        if (currentElementImport.getVisibility() != VisibilityMode.PUBLIC && currentElementImport.getVisibility() != VisibilityMode.PRIVATE) {
        
            List<Object> objects = new ArrayList<>();
            objects.add(currentElementImport.getImportedPackage());
        
            NameSpace nsOrig = currentElementImport.getImportingNameSpace();
            if (nsOrig != null) {
                objects.add(nsOrig);
            }
        
            Operation opOrig = currentElementImport.getImportingOperation();
            if (opOrig != null) {
                objects.add(opOrig);
            }
        
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("0093434c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=PackageImport, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(PackageImport.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=PackageImport, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(PackageImport.class), TriggerType.Create, null);
    }

}
