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
package org.modelio.vcore.utils.jdbm;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

/**
 * JDBM serializer for UUID.
 */
@objid ("33124e56-8e7c-4bbe-9e62-1f5416d3cc82")
public final class UuidSerializer implements Serializer<UUID> {
    /**
     * The singleton instance.
     */
    @objid ("00e7f652-19d9-4dda-bbaa-79bcb32eec14")
    public static final UuidSerializer instance = new UuidSerializer();

    @objid ("4a7c044a-e4b8-4bc4-82e0-fdc5670f5416")
    @Override
    public void serialize(final SerializerOutput out, final UUID uuid) throws IOException {
        out.writeLong (uuid.getMostSignificantBits());
        out.writeLong (uuid.getLeastSignificantBits());
        
        //out.writeUTF(uuid.toString());
        
    }

    @objid ("c79445f6-59a8-4ded-ade7-d80cd73e0e64")
    @Override
    public UUID deserialize(final SerializerInput in) throws IOException, ClassNotFoundException {
        //return UUID.fromString(in.readUTF());
        
        long most = in.readLong();
        long least = in.readLong();
        return new UUID(most, least);
    }

}
