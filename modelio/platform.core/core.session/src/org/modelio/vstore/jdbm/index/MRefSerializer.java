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

package org.modelio.vstore.jdbm.index;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * JDBM serializer for {@link MRef}.
 */
@objid ("62aa1817-17d2-4485-82d1-9f16bb6d0ea7")
public class MRefSerializer implements Serializer<MRef> {
    /**
     * Singleton to use
     */
    @objid ("b9c00746-7aaf-45b9-9e78-ecb1e87c567a")
    public static final MRefSerializer instance = new MRefSerializer();

    @objid ("9bf0a13a-aca0-4902-9e43-f166e514ff91")
    @Override
    public void serialize(final SerializerOutput out, final MRef obj) throws IOException {
        assert (obj.name != null);
        assert (obj.uuid != null);
        assert (obj.mc != null);
        
        out.writeUTF(obj.uuid); 
        out.writeUTF(obj.name); 
        out.writeUTF(obj.mc);
    }

    @objid ("94e71b4c-a6f7-4f6c-be70-585f3503df78")
    @Override
    public MRef deserialize(final SerializerInput in) throws IOException {
        String uuid = in.readUTF();
        String name = in.readUTF(); 
        String cname = in.readUTF();
        return new MRef(cname, uuid, name);
    }

}
