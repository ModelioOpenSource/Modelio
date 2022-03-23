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
package org.modelio.vstore.jdbm.index;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

/**
 * JDBM serializer for UUID.
 */
@objid ("cd6c18e4-4f92-4d65-8413-fdd50e935f62")
public final class UuidSerializer implements Serializer<UUID> {
    /**
     * The singleton instance.
     */
    @objid ("144e30ec-8572-4af5-a57a-437ee846eab5")
    public static final UuidSerializer instance = new UuidSerializer();

    @objid ("abc6ad3e-d3a0-4954-bfc2-7cd783ebe2ef")
    @Override
    public void serialize(final SerializerOutput out, final UUID uuid) throws IOException {
        out.writeLong (uuid.getMostSignificantBits());
        out.writeLong (uuid.getLeastSignificantBits());
        
        //out.writeUTF(uuid.toString());
        
    }

    @objid ("af243438-aeff-4e51-8bf6-a93783551a60")
    @Override
    public UUID deserialize(final SerializerInput in) throws IOException, ClassNotFoundException {
        //return UUID.fromString(in.readUTF());
        
        long most = in.readLong();
        long least = in.readLong();
        return new UUID(most, least);
    }

}
