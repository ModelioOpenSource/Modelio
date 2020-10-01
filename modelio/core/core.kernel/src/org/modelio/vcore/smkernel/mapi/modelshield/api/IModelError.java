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

package org.modelio.vcore.smkernel.mapi.modelshield.api;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("008b0af6-b76d-1f61-8473-001ec947cd2a")
public interface IModelError {
    @objid ("001e5a50-b76e-1f61-8473-001ec947cd2a")
    String getRuleId();

    @objid ("001e6554-b76e-1f61-8473-001ec947cd2a")
    MObject getElement();

    @objid ("001e6cfc-b76e-1f61-8473-001ec947cd2a")
    List<Object> getLinkedObjects();

}
