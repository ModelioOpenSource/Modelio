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

package org.modelio.vstore.jdbm.impl.migration.v4;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("cadff061-df79-4c09-a211-e36a3bee5ee4")
class V4MRefSerializer implements Serializer<MRef> {
    @objid ("64190490-bef2-4f3d-8bd5-2e81a77e0da1")
    public static final Serializer<MRef> instance = new V4MRefSerializer();

    @objid ("bae7966b-c280-438c-8dba-ca9723dde430")
    @Override
    public void serialize(final SerializerOutput out, final MRef obj) throws IOException {
        assert (obj.name != null);
        assert (obj.uuid != null);
        assert (obj.mc != null);
        
        out.writeUTF(obj.uuid);
        V4UuidSerializer.instance.serialize(out, UUID.fromString(obj.uuid));
        out.writeUTF(obj.name); 
        out.writeUTF(obj.mc);
    }

    @objid ("dfa34efe-8b59-4977-a802-03e35a9c345f")
    @Override
    public MRef deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        String uuid = V4UuidSerializer.instance.deserialize(in).toString();
        String name = in.readUTF(); 
        String cname = in.readUTF();
        return new MRef(cname, uuid, name);
    }

}
