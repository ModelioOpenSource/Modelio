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

package org.modelio.vstore.jdbm.impl.migration.v4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("d09d6c63-508d-4614-965e-308ae1bbfbcf")
class V4MRefCollectionSerializer implements Serializer<Collection<MRef>> {
    @objid ("38c9634b-caf5-468f-8f3c-0a6277bfd821")
    public static final Serializer<Collection<MRef>> instance = new V4MRefCollectionSerializer();

    @objid ("00c78c72-79b4-42f9-95db-3e4acc7a14d4")
    @Override
    public void serialize(final SerializerOutput out, final Collection<MRef> obj) throws IOException {
        out.writeInt(obj.size());
        for (MRef o : obj) {
            V4MRefSerializer.instance.serialize(out, o);
        }
    }

    @objid ("a8bb0a44-746f-42db-bae8-6c184d9fede7")
    @Override
    public Collection<MRef> deserialize(final SerializerInput in) throws IOException, ClassNotFoundException {
        int nb = in.readInt();
        Collection<MRef> ret = new ArrayList<>(nb);
        
        for (int i=0; i<nb; i++){
            MRef id = V4MRefSerializer.instance.deserialize(in);
            ret.add(id);
        }
        return ret;
    }

    /**
     * Don't instantiate directly, use singleton.
     */
    @objid ("7619e12f-fccd-4eaf-a34d-44837e66e40d")
    private V4MRefCollectionSerializer() {
    }

}
