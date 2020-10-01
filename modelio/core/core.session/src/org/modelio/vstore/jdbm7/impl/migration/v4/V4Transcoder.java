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

package org.modelio.vstore.jdbm7.impl.migration.v4;

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
import org.modelio.vstore.jdbm7.impl.Helper;
import org.modelio.vstore.jdbm7.index.JdbmIndex;

/**
 * JDBM Transcoder from V4 to V5.
 */
@objid ("64454899-7501-45d6-807a-7579d4956226")
class V4Transcoder {
    @objid ("5a042f6e-8232-457e-b42b-4f1353617463")
    private static Map<Class<?>,Object[]> enumContent = new HashMap<>();

    @objid ("724168ee-edc3-46b6-9083-75d434683c83")
    private SmMetamodel mm;

    @objid ("7440404c-623c-41c8-b90e-49467b85fa52")
    private JdbmIndex index;

    @objid ("f7e88815-8cbd-4d7f-b52e-120ac8f1fb78")
    private MRef currentRef;

    /**
     * @param mm the metamodel
     * @param index the new base index to be populated
     */
    @objid ("e82043ee-26d3-4634-85b4-05d382b7c1b8")
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
    @objid ("2c55abda-ef63-4a05-91f4-067e13e16a47")
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

    @objid ("ad322958-d65f-4d99-aa0a-8706062265a2")
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

    @objid ("c9b824f7-2f37-4e90-9d2d-082a48f44ece")
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

    @objid ("d29c4b76-ea40-4fc0-bdf1-47c8bdfcf32a")
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

    @objid ("3f35cbb9-616d-4327-adab-6edbf6457842")
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
    @objid ("fae0b2b4-201d-4c09-aa8b-86e01e9a9332")
    private static Object[] getEnums(final Class<?> type) {
        Object[] ret = enumContent.get(type);
        if (ret == null) {
            ret = type.getEnumConstants();
            enumContent.put(type, ret);
        }
        return ret;
    }

    @objid ("7757b5d4-6465-4c95-80f1-09e661957baa")
    private static String readUuid(DataInput in) throws IOException {
        long most = in.readLong();
        long least = in.readLong();
        
        UUID uuid = new UUID(most, least);
        return uuid.toString();
    }

}
