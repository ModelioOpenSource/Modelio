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

package org.modelio.vstore.jdbm7.impl.migration.v4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("6febb606-0a91-4add-9738-3e00f4b13ad0")
class V4MRefCollectionSerializer implements Serializer<Collection<MRef>> {
    @objid ("e1c02098-0b3f-4e77-ae95-d6fcbfb529a1")
    public static final Serializer<Collection<MRef>> instance = new V4MRefCollectionSerializer();

    @objid ("6b70d630-cb44-4ac2-844a-051315ae4510")
    @Override
    public void serialize(final SerializerOutput out, final Collection<MRef> obj) throws IOException {
        out.writeInt(obj.size());
        for (MRef o : obj) {
            V4MRefSerializer.instance.serialize(out, o);
        }
    }

    @objid ("97e05177-ad55-48eb-8d57-e187c22a4848")
    @Override
    public Collection<MRef> deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
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
    @objid ("d595dbc3-1911-4154-b1ad-84a2c0adb619")
    private V4MRefCollectionSerializer() {
    }

}
