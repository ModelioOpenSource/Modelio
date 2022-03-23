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
package org.modelio.vcore.emf;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("3dfde5f9-bba6-11e1-ac85-001ec947ccaf")
public class ESmDependency extends EReferenceImpl {
    @objid ("bbceddaf-bc87-11e1-b576-001ec947ccaf")
    private SmDependency dep;

    @objid ("bbceddb0-bc87-11e1-b576-001ec947ccaf")
    protected  ESmDependency(SmDependency dep) {
        this.dep = dep;
    }

    @objid ("bbceddb3-bc87-11e1-b576-001ec947ccaf")
    public SmDependency getSmDep() {
        return this.dep;
    }

}
