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

package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

@objid ("d01b270c-55b4-11e2-81b0-001ec947ccaf")
class ObjectIndexValueSerializer implements Serializer<ObjectIndexValue> {
    @objid ("e801890c-55ba-11e2-81b0-001ec947ccaf")
    @Override
    public void serialize(SerializerOutput out, ObjectIndexValue obj) throws IOException {
        out.writeUTF(obj.name);
        out.writeLong(obj.cmsNodeLid);
    }

    @objid ("e8018913-55ba-11e2-81b0-001ec947ccaf")
    @Override
    public ObjectIndexValue deserialize(SerializerInput in) throws ClassNotFoundException, IOException {
        String name = in.readUTF();
        long cmsNodeLid = in.readLong();
        return new ObjectIndexValue(name, cmsNodeLid);
    }

}
