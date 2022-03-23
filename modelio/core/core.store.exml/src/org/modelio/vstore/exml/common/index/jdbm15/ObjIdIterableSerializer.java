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

@objid ("d2fbb9f6-9f5a-422d-beec-41d908eb554a")
class ObjIdIterableSerializer implements Serializer<Iterable<ObjId>> {
    @objid ("34df128c-71a6-408b-b0b5-bd2f17565c5f")
    private ObjIdCollectionSerializer collSerializer;

    @objid ("1c5ada49-f68a-4507-9342-e7e18371eb1f")
    @Override
    public void serialize(final SerializerOutput out, final Iterable<ObjId> idList) throws IOException {
        Collection<ObjId> coll;
        
        if (idList instanceof Collection<?>) {
            coll = (Collection<ObjId>) idList;
        } else {
            coll = new ArrayList<>();
            for( ObjId id : idList)
                coll.add(id);
        }
        
        this.collSerializer.serialize(out, coll);
        
    }

    @objid ("2994cf22-84d6-4e87-86b7-15eb8bce3784")
    @Override
    public Iterable<ObjId> deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        return this.collSerializer.deserialize(in);
    }

    @objid ("d79b5b5e-53a2-4a08-8844-f382ad87e840")
     ObjIdIterableSerializer(ObjIdCollectionSerializer collSerializer) {
        this.collSerializer = collSerializer;
    }

}
