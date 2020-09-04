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

package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOException;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

@objid ("09ece16e-4ac1-11e2-91c9-001ec947ccaf")
final class UuidSerializer implements Serializer<UUID> {
    @objid ("691dceac-4b8b-11e2-91c9-001ec947ccaf")
    public static final UuidSerializer instance = new UuidSerializer();

    @objid ("09ece175-4ac1-11e2-91c9-001ec947ccaf")
    @Override
    public void serialize(final SerializerOutput out, final UUID uuid) throws IOException {
        /*
        out.writeLong (uuid.getMostSignificantBits());
        out.writeLong (uuid.getLeastSignificantBits());
        */
        out.writeUTF(uuid.toString());
    }

    @objid ("09ece176-4ac1-11e2-91c9-001ec947ccaf")
    @Override
    public UUID deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        return UUID.fromString(in.readUTF());
        /*
                                                                long most = in.readLong();
                                                                long least = in.readLong();
                                                                return new UUID(most, least);*/
    }

}
