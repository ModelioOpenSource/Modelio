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
package org.modelio.gproject.model.impl.importer.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0077be06-d3aa-108f-8d81-001ec947cd2a")
public class BrokenDepReport implements IBrokenDepReport {
    @objid ("0077e278-d3aa-108f-8d81-001ec947cd2a")
    private final SmObjectImpl localObject;

    @objid ("00781522-d3aa-108f-8d81-001ec947cd2a")
    private final SmObjectImpl missingRefObject;

    @objid ("0077c61c-d3aa-108f-8d81-001ec947cd2a")
    private final SmObjectImpl refObject;

    @objid ("0077fba0-d3aa-108f-8d81-001ec947cd2a")
    private final SmDependency smDep;

    @objid ("00782daa-d3aa-108f-8d81-001ec947cd2a")
    public  BrokenDepReport(final SmObjectImpl refObject, final SmObjectImpl localObject, SmDependency smDep, SmObjectImpl missingRefObject) {
        this.refObject = refObject;
        this.localObject = localObject;
        this.smDep = smDep;
        this.missingRefObject = missingRefObject;
        
    }

    @objid ("f9bfcdda-446a-11e2-b513-002564c97630")
    @Override
    public SmObjectImpl getLocalObject() {
        // Automatically generated method. Please do not modify this code.
        return this.localObject;
    }

    @objid ("f9c22f3d-446a-11e2-b513-002564c97630")
    @Override
    public SmObjectImpl getMissingRefObject() {
        // Automatically generated method. Please do not modify this code.
        return this.missingRefObject;
    }

    @objid ("f9bfcdd5-446a-11e2-b513-002564c97630")
    @Override
    public SmObjectImpl getRefObject() {
        // Automatically generated method. Please do not modify this code.
        return this.refObject;
    }

    @objid ("f9bfcddf-446a-11e2-b513-002564c97630")
    @Override
    public SmDependency getSmDep() {
        // Automatically generated method. Please do not modify this code.
        return this.smDep;
    }

}
