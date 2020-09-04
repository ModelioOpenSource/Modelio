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

package org.modelio.app.core.activation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.core.IModelioService;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("fa5e5483-3966-11e2-a430-001ec947c8cc")
public interface IActivationService extends IModelioService {
    @objid ("59aa5cde-3a22-11e2-a430-001ec947c8cc")
    void activateMObject(MObject objectToActivate);

    @objid ("762705df-cf40-4461-8d5d-7c4ad715fdc0")
    void editProperties(MObject objectToActivate);

}
