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
package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

@objid ("1f8dfd2c-54b7-4357-956a-c5dc97f0470a")
class UTFSerializer implements Serializer<String> {
    @objid ("c138acdc-f127-4677-9f89-a1935b699df9")
    public static final UTFSerializer INSTANCE = new UTFSerializer();

    @objid ("543a6d67-81c5-4850-bd2c-c3bb0aac42d3")
    @Override
    public void serialize(SerializerOutput out, String obj) throws IOException {
        out.writeUTF(obj);
    }

    @objid ("aa367974-f5a4-4b1c-ba33-3e944ae621cd")
    @Override
    public String deserialize(SerializerInput in) throws IOException, ClassNotFoundException {
        return in.readUTF();
    }

}
