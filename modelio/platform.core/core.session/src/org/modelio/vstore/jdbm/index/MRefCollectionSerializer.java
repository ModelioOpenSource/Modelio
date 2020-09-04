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
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("ace74c1d-cd92-4125-99a4-c1f72b5d4dd8")
class MRefCollectionSerializer implements Serializer<Collection<MRef>> {
    @objid ("a6390819-2d4a-42fb-b073-be383e350353")
    public static final Serializer<Collection<MRef>> instance = new MRefCollectionSerializer();

    @objid ("954d1e08-6e9e-4fcb-b9f0-13331304caf8")
    @Override
    public void serialize(final SerializerOutput out, final Collection<MRef> obj) throws IOException {
        out.writeInt(obj.size());
        for (MRef o : obj) {
            MRefSerializer.instance.serialize(out, o);
        }
    }

    @objid ("0217293c-06b3-4bba-8ef6-2d887566b2cb")
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
    @objid ("6e0c9e4f-6eb2-4126-8df9-95275f16d2db")
    private MRefCollectionSerializer() {
    }

}
