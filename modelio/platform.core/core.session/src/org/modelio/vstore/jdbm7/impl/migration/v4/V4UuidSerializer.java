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

package org.modelio.vstore.jdbm7.impl.migration.v4;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

/**
 * JDBM serializer for UUID.
 */
@objid ("59df07eb-452d-4550-9d06-8ff0ed33cb8f")
final class V4UuidSerializer implements Serializer<UUID> {
    /**
     * The singleton instance.
     */
    @objid ("f447432e-7586-4bf9-875f-ad03edca6c32")
    public static final V4UuidSerializer instance = new V4UuidSerializer();

    @objid ("6189c26a-df80-4cec-8243-9ba75b4543da")
    @Override
    public void serialize(final SerializerOutput out, final UUID uuid) throws IOException {
        out.writeLong (uuid.getMostSignificantBits());
        out.writeLong (uuid.getLeastSignificantBits());
    }

    @objid ("f8816777-e73c-42b1-a61c-14a63bd93bee")
    @Override
    public UUID deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        long most = in.readLong();
        long least = in.readLong();
        return new UUID(most, least);
    }

}
