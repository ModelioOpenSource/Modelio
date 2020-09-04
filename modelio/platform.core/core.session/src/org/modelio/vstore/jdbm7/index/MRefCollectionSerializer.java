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

package org.modelio.vstore.jdbm7.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("11364054-d94d-4138-9cad-ec85598dd359")
class MRefCollectionSerializer implements Serializer<Collection<MRef>> {
    @objid ("18c60865-f580-4469-b952-2f2e07866b5b")
    public static final Serializer<Collection<MRef>> instance = new MRefCollectionSerializer();

    @objid ("b665a1f0-eb8f-4961-b276-a2156cb584b7")
    @Override
    public void serialize(final SerializerOutput out, final Collection<MRef> obj) throws IOException {
        out.writeInt(obj.size());
        for (MRef o : obj) {
            MRefSerializer.instance.serialize(out, o);
        }
    }

    @objid ("9c83cb47-1050-489f-9fa6-f3d8b8824bb4")
    @Override
    public Collection<MRef> deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        int nb = in.readInt();
        Collection<MRef> ret = new ArrayList<>(nb);
        
        for (int i=0; i<nb; i++){
            MRef id = MRefSerializer.instance.deserialize(in);
            ret.add(id);
        }
        return ret;
    }

    /**
     * Don't instantiate directly, use singleton.
     */
    @objid ("ef4bbda9-4d9d-4dcb-aee2-715f632f72bf")
    private MRefCollectionSerializer() {
    }

}
