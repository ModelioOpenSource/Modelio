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

package org.modelio.vstore.jdbm.index;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

@objid ("7bf26662-bccc-45dd-b040-5635a4c21f21")
class UTFSerializer implements Serializer<String> {
    @objid ("1f717abc-2288-4423-aee7-b77977680dd2")
    public static final UTFSerializer INSTANCE = new UTFSerializer();

    @objid ("3bad2667-9c8c-4ec3-a263-97399fdc3fac")
    @Override
    public void serialize(SerializerOutput out, String obj) throws IOException {
        out.writeUTF(obj);
    }

    @objid ("59e94a59-d300-446f-9cd5-47207bc0597d")
    @Override
    public String deserialize(SerializerInput in) throws ClassNotFoundException, IOException {
        return in.readUTF();
    }

}
