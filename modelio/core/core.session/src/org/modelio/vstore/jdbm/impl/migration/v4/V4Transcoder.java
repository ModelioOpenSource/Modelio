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

package org.modelio.vstore.jdbm.impl.migration.v4;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOError;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.SerializerInput;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.jdbm.impl.Helper;
import org.modelio.vstore.jdbm.index.JdbmIndex;

/**
 * JDBM Transcoder from V4 to V5.
 */
@objid ("42f3fe91-81b1-4db0-93ca-5438f553ae31")
class V4Transcoder {
    @objid ("45fee2c9-fd68-4712-b4c7-9aed6e0be26f")
    private static Map<Class<?>,Object[]> enumContent = new HashMap<>();

    @objid ("9460163c-0ae9-4da9-bc96-6c888b1910b9")
    private SmMetamodel mm;

    @objid ("3e2d6d8e-85ad-476e-b368-f64439083c03")
    private JdbmIndex index;

    @objid ("d95a962b-5476-4ea5-b88d-de38360c4eca")
    private MRef currentRef;

    /**
     * @param mm the metamodel
     * @param index the new base index to be populated
     */
    @objid ("36fc4c77-d94e-42dd-bcc1-5e3faecacc1d")
    public V4Transcoder(SmMetamodel mm, JdbmIndex index) {
        this.mm = mm;
        this.index = index;
    }

    /**
     * Transcode in to out and update indexes.
     * 
     * @param in input data with old format
     * @param out same data with new format
     * @param uuid the UUID of the object to transcode.
     * @throws java.io.IOException on failure
     */
    @objid ("e4e80565-9960-46bc-9561-1389a6f2f186")
    public void transcode(SerializerInput in, final DataOutput out, UUID uuid) throws IOException {
        try {
            final String clsName = in.readUTF();
            final SmClass cls = this.mm.getMClass(clsName);
        
            if (cls == null) {
                throw new IOException(MessageFormat.format("''{0}'' metaclass is missing.", clsName, uuid));
            }
        
            String strUuid = uuid.toString();
            this.currentRef = new MRef (clsName, strUuid);
        
            out.writeUTF(cls.getQualifiedName());
        
            for (MAttribute att : cls.getAttributes(true)) {
                Object v = readAttribute(in, out, att);
                writeAttribute(out, v, att);
            }
        
            for (SmDependency  d: cls.getAllDepDef()) {
                if (Helper.isPersistent(d)) {
                    transcodeDep(in, out, d);
                }
            }
            
            this.index.addRefToMain(cls, strUuid);
            
        } catch (IOError e) {
            // JDBM throws IOErrors
            throw new IOException(e);
        }
    }

    @objid ("1f3d48c1-4efa-4c0e-8e2c-93b07807b5ac")
    private Object readAttribute(final DataInput in, final DataOutput out, final MAttribute att) throws IOException {
        final Class<?> type = att.getType();
        Object val;
        if (type == String.class){
            int len = in.readInt();
            StringBuilder s = new StringBuilder(len);
            for (int i=0; i < len; i++) {
                s.append(in.readChar());
            }
        
            val = s.toString();
        } else if (type == Integer.class) {
            val = in.readInt();
        } else if (type == Long.class) {
            val = in.readLong();
        } else if (type == Byte.class) {
            val = in.readByte();
        } else if (type == Boolean.class) {
            val = in.readBoolean();
        //        } else if (type == UUID.class) {
        //            val = readUuid(in);
        //        } else if (type == IRStatus.class || type == IPStatus.class) {
        //            val = SmStatus.getPersistentBits(in.readLong());
        } else if (type.isEnum()) {
            val= getEnums(type)[in.readInt()];
        } else {
            throw new UnsupportedOperationException(type+" "+att+" attribute not supported.");
        }
        return val;
    }

    @objid ("7e1f2890-b00c-479d-a8ff-756c30e3ad6b")
    private static void writeAttribute(final DataOutput out, Object val, final MAttribute att) throws IOException {
        final Class<?> type = att.getType();
        if (type == String.class){
            final String s = (String) val;
            out.writeInt(s.length());
            out.writeChars(s);
        } else if (type == Integer.class) {
            out.writeInt((int) val);
        } else if (type == Long.class) {
            out.writeLong((long) val);
        } else if (type == Byte.class) {
            out.writeByte((int) val);
        } else if (type == Boolean.class) {
            out.writeBoolean((boolean) val);
        //        } else if (type == UUID.class) {
        //            final String uuid = (String) val;
        //            out.writeUTF(uuid);
        //        } else if (type == IRStatus.class || type == IPStatus.class) {
        //            // skip
        //            //out.writeLong(((SmStatus) val).toLong());
        } else if (type.isEnum()) {
            Enum<?> en = (Enum<?>)val;
            out.writeInt(en.ordinal());
        } else {
            throw new UnsupportedOperationException(type+" "+val+" "+att+" attribute not supported.");
        }
    }

    @objid ("903922f0-d822-4920-88aa-8ee56fece6ea")
    private void transcodeDep(DataInput in, final DataOutput out, SmDependency d) throws IOException {
        final int size = in.readInt();
        
        assert (d.getMaxCardinality() == -1 || size <= d.getMaxCardinality()) ;
        
        Collection<MRef> values = new ArrayList<>(size);
        
        out.writeInt(size);
        
        for (int i=0; i<size; i++) {
            values.add(transcodeRef(in, out));
        }
        
        this.index.addCrossRef(this.currentRef, d, values );
    }

    @objid ("179dbc6c-a7d9-4b20-8060-b11b4bcd66db")
    private MRef transcodeRef(DataInput in, final DataOutput out) throws IOException {
        // Read
        boolean isLocal = in.readBoolean();
        
        String clsid = in.readUTF();
        String uuid = readUuid(in);
        
        // Write 
        out.writeBoolean( isLocal);
        out.writeUTF(clsid);
        out.writeUTF(uuid);
        return new MRef(clsid, uuid);
    }

    /**
     * Get all possible enumeration values of an enumerate type.
     * 
     * @param type a enumerate class.
     * @return all possible values, ordered.
     */
    @objid ("46408f5f-3de2-47bf-bcff-eedfd8fe6e11")
    private static Object[] getEnums(final Class<?> type) {
        Object[] ret = enumContent.get(type);
        if (ret == null) {
            ret = type.getEnumConstants();
            enumContent.put(type, ret);
        }
        return ret;
    }

    @objid ("f77b1ec4-02ee-463c-86bb-c8f86e708f12")
    private static String readUuid(DataInput in) throws IOException {
        long most = in.readLong();
        long least = in.readLong();
        
        UUID uuid = new UUID(most, least);
        return uuid.toString();
    }

}
