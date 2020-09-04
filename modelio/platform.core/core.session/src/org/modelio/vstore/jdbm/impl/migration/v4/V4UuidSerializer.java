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

package org.modelio.vstore.jdbm.impl.migration.v4;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

/**
 * JDBM serializer for UUID.
 */
@objid ("60fb5a2c-fc8e-4833-bcb8-c0dc87067ef4")
final class V4UuidSerializer implements Serializer<UUID> {
    /**
     * The singleton instance.
     */
    @objid ("759a66b7-664a-43bc-945a-15e4acbbcdbb")
    public static final V4UuidSerializer instance = new V4UuidSerializer();

    @objid ("f8ef5453-ecf9-4e41-9f90-ce1ecbf7190c")
    @Override
    public void serialize(final SerializerOutput out, final UUID uuid) throws IOException {
        out.writeLong (uuid.getMostSignificantBits());
        out.writeLong (uuid.getLeastSignificantBits());
    }

    @objid ("8ebaa7df-cd0f-49c8-82d3-5c4bad285dc3")
    @Override
    public UUID deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        long most = in.readLong();
        long least = in.readLong();
        return new UUID(most, least);
    }

}
