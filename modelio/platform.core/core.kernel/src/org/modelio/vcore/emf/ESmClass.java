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

package org.modelio.vcore.emf;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * EClassImpl linked to a {@link SmClass}.
 * @author cmarin
 */
@objid ("3679e8df-bba6-11e1-ac85-001ec947ccaf")
public class ESmClass extends EClassImpl {
    @objid ("bbcedda5-bc87-11e1-b576-001ec947ccaf")
    private SmClass smClass;

    @objid ("bbcedda6-bc87-11e1-b576-001ec947ccaf")
    public SmClass getSmClass() {
        return this.smClass;
    }

    @objid ("bbceddaa-bc87-11e1-b576-001ec947ccaf")
    void setSmClass(SmClass smClass) {
        this.smClass = smClass;
    }

    @objid ("bbceddad-bc87-11e1-b576-001ec947ccaf")
    public ESmClass() {
    }

}
