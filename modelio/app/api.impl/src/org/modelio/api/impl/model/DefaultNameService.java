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

package org.modelio.api.impl.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IDefaultNameService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementNamer;

@objid ("c6a85a4e-8e3f-4151-8ea3-32b8e29ce3d7")
public class DefaultNameService implements IDefaultNameService {
    @objid ("f127d0bf-dc35-4882-b3ab-69617dba177b")
    private IElementNamer elementNamer;

    @objid ("d40d2f95-35ff-491d-9e79-1e5a905c5c95")
    public DefaultNameService(IElementNamer elementNamer) {
        this.elementNamer = elementNamer;
    }

    @objid ("2d1bb6b0-d0f4-4ea0-ae36-3e2c747ecad9")
    @Override
    public void setDefaultName(ModelElement element) {
        element.setName(this.elementNamer.getUniqueName(element));
    }

    @objid ("29339318-a227-455b-9ab9-9fdfdf4c09bb")
    @Override
    public void setDefaultName(ModelElement element, String baseName) {
        element.setName(this.elementNamer.getUniqueName(baseName, element));
    }

}
