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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

@objid ("b04afbd7-1489-4d72-bfda-319b81c449fc")
final class UuidSerializer implements Serializer<UUID> {
    @objid ("37997f44-ca18-484b-825f-5832e4baf120")
    public static final UuidSerializer instance = new UuidSerializer();

    @objid ("d7add56d-f51d-4a52-9189-e4bb7d0eaae2")
    @Override
    public void serialize(final SerializerOutput out, final UUID uuid) throws IOException {
        /*
        out.writeLong (uuid.getMostSignificantBits());
        out.writeLong (uuid.getLeastSignificantBits());
        */
        out.writeUTF(uuid.toString());
    }

    @objid ("6abef75c-a0df-461b-a463-73f6f8242704")
    @Override
    public UUID deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        return UUID.fromString(in.readUTF());
        /*
                                                        long most = in.readLong();
                                                        long least = in.readLong();
                                                        return new UUID(most, least);*/
    }

}
