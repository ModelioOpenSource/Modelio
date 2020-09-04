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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vstore.exml.common.model.ObjId;

@objid ("ce37730e-c9e5-4171-9297-9266ff674555")
class ObjectIndexValueSerializer implements Serializer<ObjectIndexValue> {
    @objid ("cd34939f-99cd-4dd9-b2cc-c0eec6800327")
    private final ObjIdSerializer idSerializer;

    @objid ("c81a6288-05c9-4d66-9d89-846568583e7d")
    ObjectIndexValueSerializer(ObjIdSerializer idSerializer) {
        this.idSerializer = idSerializer;
    }

    @objid ("fcad5926-5817-4936-8e15-26c781fc4570")
    @Override
    public void serialize(SerializerOutput out, ObjectIndexValue obj) throws IOException {
        out.writeUTF(obj.name);
        this.idSerializer.serialize(out, obj.cmsNodeId);
    }

    @objid ("329b9fa2-876b-4d6e-b1a7-74f0527d9636")
    @Override
    public ObjectIndexValue deserialize(SerializerInput in) throws ClassNotFoundException, IOException {
        String name = in.readUTF();
        ObjId parentId = this.idSerializer.deserialize(in);
        return new ObjectIndexValue(name, parentId);
    }

}
