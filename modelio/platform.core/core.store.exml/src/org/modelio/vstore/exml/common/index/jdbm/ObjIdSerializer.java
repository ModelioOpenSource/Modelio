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

package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.model.ObjId;

@objid ("ea207749-5c87-11e1-863f-001ec947ccaf")
@Deprecated
class ObjIdSerializer implements Serializer<ObjId> {
    @objid ("d0890115-bda2-4974-8016-f1af625ce20d")
    private final SmMetamodel metamodel;

    @objid ("82d690e7-5ca7-11e1-863f-001ec947ccaf")
    @Override
    public void serialize(final SerializerOutput out, final ObjId obj) throws IOException {
        assert (obj.id != null);
        assert (obj.classof != null);
        
        out.writeUTF(obj.id);
        out.writeUTF(obj.classof.getQualifiedName());
    }

    @objid ("82d690ee-5ca7-11e1-863f-001ec947ccaf")
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

    @objid ("2dbb8cb6-68be-4077-bb6f-b4281bf44fb2")
    public ObjIdSerializer(SmMetamodel metamodel) {
        assert (metamodel != null);
        this.metamodel = metamodel;
    }

}
