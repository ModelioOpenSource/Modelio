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
package org.modelio.vstore.exml.common.index.jdbm15;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * Object index entry value.
 * Contains the name of the object and its parent identifier.
 */
@objid ("a5f35355-e3f1-4b3e-8960-2410ff00cc76")
class ObjectIndexValue {
    @objid ("db99e4b0-c948-4e76-8a82-8b759be433a7")
    final String name;

    @objid ("0e8be74c-04a5-4408-806e-d2b57bda50ab")
    final ObjId cmsNodeId;

    @objid ("e517714c-dc79-4825-bd87-3d48e450262f")
    public  ObjectIndexValue(String name, ObjId parentId) {
        this.name = name;
        this.cmsNodeId = parentId;
        
    }

    @objid ("93dac75a-2ca6-4390-a428-5827711dfd57")
    @Override
    public String toString() {
        return "[name='" + this.name + "', cmsNodeId=" + this.cmsNodeId + "]";
    }

}
