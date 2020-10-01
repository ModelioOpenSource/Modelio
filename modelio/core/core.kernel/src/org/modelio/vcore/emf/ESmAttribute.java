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
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;

@objid ("3e181fab-bba6-11e1-ac85-001ec947ccaf")
public class ESmAttribute extends EAttributeImpl {
    @objid ("bbcc7b41-bc87-11e1-b576-001ec947ccaf")
    private SmAttribute att;

    @objid ("bbcedd9d-bc87-11e1-b576-001ec947ccaf")
    protected ESmAttribute(SmAttribute att) {
        this.att = att;
    }

    @objid ("bbcedda0-bc87-11e1-b576-001ec947ccaf")
    public SmAttribute getSmAtt() {
        return this.att;
    }

}
