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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

@objid ("42f61cdc-920d-410c-a0af-1fba8b80aebe")
class SmClassSerializer implements Serializer<SmClass> {
    @objid ("4cabe7a3-c764-481e-9b72-e5ebadf659da")
    private SmMetamodel metamodel;

    @objid ("8fac3708-d2b0-4e52-9474-bf0938613c96")
    @Override
    public void serialize(final SerializerOutput out, final SmClass obj) throws IOException {
        out.writeUTF(obj.getQualifiedName());
    }

    @objid ("41837a54-2328-4d5f-8ca2-09df400f7855")
    @Override
    public SmClass deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        String cname = in.readUTF();
        return this.metamodel.getMClass(cname);
    }

    @objid ("9c49320b-daf0-420a-8614-83cc6049aed3")
     SmClassSerializer(SmMetamodel metamodel) {
        this.metamodel = metamodel;
    }

}
