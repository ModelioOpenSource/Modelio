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

package org.modelio.vstore.jdbm7.index;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

/**
 * JDBM serializer for UUID.
 */
@objid ("49582dd8-83e3-4b88-81a5-a81a361a7121")
public final class UuidSerializer implements Serializer<UUID> {
    /**
     * The singleton instance.
     */
    @objid ("eb89a59e-66ad-4b6b-9839-d3a3696c92b5")
    public static final UuidSerializer instance = new UuidSerializer();

    @objid ("705c5db4-01e4-4952-9cb2-96b6001885b1")
    @Override
    public void serialize(final SerializerOutput out, final UUID uuid) throws IOException {
        out.writeLong (uuid.getMostSignificantBits());
        out.writeLong (uuid.getLeastSignificantBits());
        
        //out.writeUTF(uuid.toString());
    }

    @objid ("7b7b34e5-9011-4a08-9d59-5652eefb34f5")
    @Override
    public UUID deserialize(final SerializerInput in) throws IOException, ClassNotFoundException {
        //return UUID.fromString(in.readUTF());
        
        long most = in.readLong();
        long least = in.readLong();
        return new UUID(most, least);
    }

}
