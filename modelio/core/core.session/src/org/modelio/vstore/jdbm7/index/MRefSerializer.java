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
package org.modelio.vstore.jdbm7.index;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("cf2dd74b-f7c2-4f4e-b204-443e871d0df6")
class MRefSerializer implements Serializer<MRef> {
    @objid ("ce09798c-39d5-49e5-b3da-e64d6b8d1723")
    public static final Serializer<MRef> instance = new MRefSerializer();

    @objid ("2e4b2ca7-b7bc-4188-b88c-ccfd822b0502")
    @Override
    public void serialize(final SerializerOutput out, final MRef obj) throws IOException {
        assert (obj.name != null);
        assert (obj.uuid != null);
        assert (obj.mc != null);
        
        out.writeUTF(obj.uuid); 
        out.writeUTF(obj.name); 
        out.writeUTF(obj.mc);
        
    }

    @objid ("8799a6bb-90ca-47e5-bcb6-12013d25e3f2")
    @Override
    public MRef deserialize(final SerializerInput in) throws IOException, ClassNotFoundException {
        String uuid = in.readUTF();
        String name = in.readUTF(); 
        String cname = in.readUTF();
        return new MRef(cname, uuid, name);
    }

}
