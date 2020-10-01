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

package org.modelio.platform.core.navigate;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.core.IModelioService;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("005d66aa-a738-10ac-8258-001ec947cd2a")
public interface IModelioNavigationService extends IModelioService {
    @objid ("005c6d86-a86b-10ac-8258-001ec947cd2a")
    void fireNavigate(MObject data);

    @objid ("005c79b6-a86b-10ac-8258-001ec947cd2a")
    void fireNavigate(List<MObject> data);

}
