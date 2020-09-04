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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.model.ObjId;

@objid ("9c987119-669c-4353-a612-d47c1060e27c")
class ObjIdSerializer implements Serializer<ObjId> {
    @objid ("9f2f605c-dbde-465a-8df6-03e45ebb1126")
    private final SmMetamodel metamodel;

    @objid ("6528480e-e3af-4359-8d71-d1b80013449d")
    @Override
    public void serialize(final SerializerOutput out, final ObjId obj) throws IOException {
        assert (obj.id != null);
        assert (obj.classof != null);
        
        out.writeUTF(obj.id);
        out.writeUTF(obj.classof.getQualifiedName());
    }

    @objid ("022c109f-8b27-4c9f-b830-58a2bcc51d03")
    @Override
    public ObjId deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        String id = in.readUTF();
        String cname = in.readUTF();
        SmClass mc = this.metamodel.getMClass(cname);
        
        if (mc == null) {
            mc = this.metamodel.fakeClassBuilder().setQualifiedName(cname).build();
        }
        return new ObjId(mc, id);
    }

    @objid ("73a3b15f-e9f0-49a8-a974-b73cf28d6950")
    public ObjIdSerializer(SmMetamodel metamodel) {
        assert (metamodel != null);
        
        this.metamodel = metamodel;
    }

}
