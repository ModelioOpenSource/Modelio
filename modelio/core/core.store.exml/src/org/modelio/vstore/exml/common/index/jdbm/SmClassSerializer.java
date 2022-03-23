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
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

@objid ("abf66ee7-5c87-11e1-863f-001ec947ccaf")
class SmClassSerializer implements Serializer<SmClass> {
    @objid ("6fe1479b-237c-41c0-8ad7-cc19c264afb5")
    private final SmMetamodel metamodel;

    @objid ("82d69109-5ca7-11e1-863f-001ec947ccaf")
    @Override
    public void serialize(final SerializerOutput out, final SmClass obj) throws IOException {
        out.writeUTF(obj.getQualifiedName());
    }

    @objid ("82d69110-5ca7-11e1-863f-001ec947ccaf")
    @Override
    public SmClass deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        String cname = in.readUTF();
        return this.metamodel.getMClass(cname);
    }

    @objid ("521c4d54-a134-4a35-8a5e-795d09833a34")
     SmClassSerializer(SmMetamodel metamodel) {
        this.metamodel = metamodel;
    }

}
