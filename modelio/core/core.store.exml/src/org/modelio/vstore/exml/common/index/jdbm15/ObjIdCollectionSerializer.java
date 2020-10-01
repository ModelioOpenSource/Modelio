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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vstore.exml.common.model.ObjId;

@objid ("a0a8f1ba-1cb9-41d9-bdd9-7d4639fadd69")
class ObjIdCollectionSerializer implements Serializer<Collection<ObjId>> {
    @objid ("96485e84-7683-4043-8749-7899b98ff4a0")
    private final ObjIdSerializer objIdSerializer;

    @objid ("16a34ab0-df3a-4f40-885c-df276a01049f")
    @Override
    public void serialize(final SerializerOutput out, final Collection<ObjId> obj) throws IOException {
        out.writeInt(obj.size());
        for (ObjId o : obj) {
            this.objIdSerializer.serialize(out, o);
        }
    }

    @objid ("1f5829b1-07f3-41b1-bff2-2e5af9f43525")
    @Override
    public Collection<ObjId> deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        int nb = in.readInt();
        Collection<ObjId> ret = new ArrayList<>(nb);
        
        for (int i=0; i<nb; i++){
            ObjId id = this.objIdSerializer.deserialize(in);
            ret.add(id);
        }
        return ret;
    }

    /**
     * @param objIdSerializer the object serializer
     */
    @objid ("07c0b6dd-224e-4ac1-a5f3-656e467dab97")
    public ObjIdCollectionSerializer(ObjIdSerializer objIdSerializer) {
        this.objIdSerializer = objIdSerializer;
    }

}
