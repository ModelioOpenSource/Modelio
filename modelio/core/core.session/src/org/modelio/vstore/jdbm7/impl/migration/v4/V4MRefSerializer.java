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

package org.modelio.vstore.jdbm7.impl.migration.v4;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("10d244ec-e3be-4aa0-b249-e33a46d6bb3c")
class V4MRefSerializer implements Serializer<MRef> {
    @objid ("9cfc27d9-a918-4a61-a6c7-395d984a3a2a")
    public static final Serializer<MRef> instance = new V4MRefSerializer();

    @objid ("aaa6c5e1-895b-4e38-89a3-51fbe5951b5a")
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

    @objid ("2a21bd88-0d50-415e-8ebb-55c14dbc84f1")
    @Override
    public MRef deserialize(final SerializerInput in) throws IOException, ClassNotFoundException {
        String uuid = V4UuidSerializer.instance.deserialize(in).toString();
        String name = in.readUTF(); 
        String cname = in.readUTF();
        return new MRef(cname, uuid, name);
    }

}
