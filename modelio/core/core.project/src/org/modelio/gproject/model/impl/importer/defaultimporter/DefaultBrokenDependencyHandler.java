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

package org.modelio.gproject.model.impl.importer.defaultimporter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.importer.core.IBrokenDepReport;
import org.modelio.gproject.model.impl.importer.core.IBrokenDependencyHandler;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.model.GetAbsoluteSymbol;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * {@link IBrokenDependencyHandler} implementation that logs the errors as warnings.
 */
@objid ("007a8e06-d3aa-108f-8d81-001ec947cd2a")
public class DefaultBrokenDependencyHandler implements IBrokenDependencyHandler {
    @objid ("007a9efa-d3aa-108f-8d81-001ec947cd2a")
    @Override
    public void handleBrokenDep(IBrokenDepReport brokenDepReport) {
        SmObjectImpl missingrRefObject = brokenDepReport.getMissingRefObject();
        SmObjectImpl brokenRefObject = brokenDepReport.getRefObject();
        SmDependency smDep = brokenDepReport.getSmDep();
        
        // FIXME log error
        Log.warning(" '%s' %s missing, needed by '%s' %s on '%s' dependency", GetAbsoluteSymbol.get(missingrRefObject), missingrRefObject
                .getMClass().getName(), GetAbsoluteSymbol.get(brokenRefObject), missingrRefObject.getMClass().getName(), smDep.getName());
    }

    @objid ("007ae09a-d3aa-108f-8d81-001ec947cd2a")
    @Override
    public void postProcess() {
        // Nothing to do in the default implementation
    }

}
