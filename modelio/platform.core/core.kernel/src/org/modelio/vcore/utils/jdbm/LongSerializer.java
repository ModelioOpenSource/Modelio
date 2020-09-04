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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;

/**
 * JDBM serializer for Long.
 */
@objid ("609a686d-aa84-4e07-9bf3-6831cbe8ce50")
public final class LongSerializer implements Serializer<Long> {
    /**
     * The singleton instance.
     */
    @objid ("1eb06446-8d45-4372-ae16-09dbd95b030a")
    public static final LongSerializer instance = new LongSerializer();

    @objid ("02f26f2c-03ff-45bc-9848-552d79c64e5d")
    @Override
    public void serialize(final SerializerOutput out, final Long number) throws IOException {
        out.writeLong (number);
    }

    @objid ("bdfb99c1-2bd5-433f-a425-4bde15db67fe")
    @Override
    public Long deserialize(final SerializerInput in) throws ClassNotFoundException, IOException {
        return in.readLong();
    }

}
