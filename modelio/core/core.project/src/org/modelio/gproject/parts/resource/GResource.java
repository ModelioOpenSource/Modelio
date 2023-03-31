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
package org.modelio.gproject.parts.resource;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.parts.AbstractGPart;
import org.modelio.vbasic.progress.IModelioProgress;

@objid ("e92912af-84b2-4a5f-9b45-5b17fe4d996b")
public class GResource extends AbstractGPart {
    @objid ("770af65b-207c-4e70-a257-36db70d776c4")
    public  GResource(GProjectPartDescriptor desc) {
        super(desc);
    }

    @objid ("bf1453d2-5e0e-46b1-a5ef-9b048594644f")
    @Override
    public void mount(IModelioProgress monitor) {
        // TODO Auto-generated method stub
    }

    @objid ("41d34065-0c0b-4580-b085-f6130a7d7085")
    @Override
    public void unmount(IModelioProgress monitor) {
        // TODO Auto-generated method stub
    }

}
