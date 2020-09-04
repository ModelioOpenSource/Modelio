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

package org.modelio.vcore.swap;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmLiveId;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmStatusFactory;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.SmSingleDependency;

/**
 * JDBM {@link Serializer} optimized for {@link JdbmSwap.CacheEntry}.
 */
@objid ("dcbe877f-493b-11e2-91c9-001ec947ccaf")
class CacheEntrySerializer implements Serializer<JdbmSwap.CacheEntry> {
    @objid ("7e405960-4a00-11e2-91c9-001ec947ccaf")
    private static Map<Class<?>,Object[]> enumContent = new HashMap<>();

    @objid ("c108316e-4137-424c-be29-76e07e7d46d5")
    private SmMetamodel metamodel;

    @objid ("dcbe8783-493b-11e2-91c9-001ec947ccaf")
    @Override
    public void serialize(SerializerOutput out, org.modelio.vcore.swap.JdbmSwap.CacheEntry entry) throws IOException {
        out.writeInt(entry.metaId);
        out.writeInt(entry.storeHandleId);
        
        SmObjectData obj = entry.data;
        out.writeLong(obj.getLiveId());
        writeUuid(out, obj.getUuid());
        
        out.writeLong(obj.getStatus());
        
        SmClass cls = obj.getClassOf();
        for (SmAttribute att : cls.getAllAttDef()) {
            writeAttribute(out, obj, att);
        }
        
        for (SmDependency  d: cls.getAllDepDef()) {
            if (d.isMultiple()) {
                writeMultipleDep(out, obj, d);
            } else {
                writeSimpleDep(out, obj, (SmSingleDependency)d);
            }
        }
    }

    @objid ("dcbe878b-493b-11e2-91c9-001ec947ccaf")
    @Override
    public org.modelio.vcore.swap.JdbmSwap.CacheEntry deserialize(SerializerInput in) throws ClassNotFoundException, IOException {
        JdbmSwap.CacheEntry ret = new JdbmSwap.CacheEntry();
        
        ret.metaId = in.readInt();
        ret.storeHandleId = in.readInt();
        
        final long liveId = in.readLong();
        final String uuid = readUuid(in);
        
        final short clsid = SmLiveId.getClassId(liveId);
        final SmClass cls = this.metamodel.getMClass(clsid);
        
        final SmObjectData data = (SmObjectData) cls.getObjectFactory().createData();
        data.init(uuid, liveId);
        ret.data = data;
        
        SmStatusFactory.deserializeStatuses(data, in.readLong());
        
        for (SmAttribute att : cls.getAllAttDef()) {
            readAttribute(in, data, att);
        }
        
        for (SmDependency  d: cls.getAllDepDef()) {
            if (d.isMultiple()) {
                readMultipleDep(in, data, d);
            } else {
                readSimpleDep(in, data, (SmSingleDependency)d);
            }
        }
        return ret;
    }

    @objid ("dcc0e9a0-493b-11e2-91c9-001ec947ccaf")
    private static void readAttribute(final DataInput in, final SmObjectData data, final SmAttribute att) throws IOException {
        final Class<?> type = att.getType();
        if (type == String.class){
            int len = in.readInt();
            StringBuilder s = new StringBuilder(len);
            for (int i=0; i < len; i++) {
                s.append(in.readChar());
            }
        
            att.setValue(data, s.toString());
        } else if (type == Integer.class) {
            att.setValue(data, in.readInt());
        } else if (type == Long.class) {
            att.setValue(data, in.readLong());
        } else if (type == Byte.class) {
            att.setValue(data, in.readByte());
        } else if (type == Boolean.class) {
            att.setValue(data, in.readBoolean());
        } else if (type == UUID.class) {
            att.setValue(data, new UUID(in.readLong(), in.readLong()));
        } else if (type == IRStatus.class || type == IPStatus.class) {
            // skip
            //att.setValue(data, new SmStatus(in.readLong()));
        } else if (type.isEnum()) {
            att.setValue(data, getEnums(type)[in.readInt()]);
        } else {
            throw new UnsupportedOperationException(type+" "+att+" attribute not supported.");
        }
    }

    @objid ("dcc0e9a7-493b-11e2-91c9-001ec947ccaf")
    private static void writeAttribute(final DataOutput out, final SmObjectData obj, final SmAttribute att) throws IOException {
        final Object val = att.getValue(obj);
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
        } else if (type == IRStatus.class || type == IPStatus.class) {
            // skip
            //out.writeLong(((SmStatus) val).toLong());
        } else if (type.isEnum()) {
            Enum<?> en = (Enum<?>)val;
            out.writeInt(en.ordinal());
        } else {
            throw new UnsupportedOperationException(type+" "+val+" "+att+" attribute not supported.");
        }
    }

    @objid ("dcc0e9ae-493b-11e2-91c9-001ec947ccaf")
    private static void writeSimpleDep(final DataOutput out, final SmObjectData obj, final SmSingleDependency d) throws IOException {
        final SmObjectImpl content = d.getValue(obj);
        if (content == null) {
            out.writeByte(0);
        } else {
            out.writeByte(1);
            writeRef(out, content);
        }
    }

    @objid ("dcc0e9b5-493b-11e2-91c9-001ec947ccaf")
    private void readSimpleDep(DataInput in, SmObjectData data, SmSingleDependency d) throws IOException {
        byte flag = in.readByte();
        if (flag == 1) {
            d.setValue(data, readRef(in));
        } else {
            assert (flag == 0) : (d.getName()+" flag is " + flag);
        }
    }

    @objid ("dcc0e9bc-493b-11e2-91c9-001ec947ccaf")
    private static void writeMultipleDep(DataOutput out, SmObjectData obj, SmDependency md) throws IOException {
        Collection<SmObjectImpl> content = md.getValueAsCollection(obj);
        
        out.writeInt(content.size());
        
        for (SmObjectImpl val : content) {
            writeRef(out, val);
        }
    }

    @objid ("dcc0e9c3-493b-11e2-91c9-001ec947ccaf")
    private void readMultipleDep(DataInput in, ISmObjectData obj, SmDependency d) throws IOException {
        final int size = in.readInt();
        
        for (int i=0; i<size; i++) {
            final SmObjectImpl depVal = readRef(in);
            d.add(obj, depVal);
        }
    }

    @objid ("dcc0e9ca-493b-11e2-91c9-001ec947ccaf")
    private SmObjectImpl readRef(DataInput in) throws IOException {
        long liveId = in.readLong();
        String uuid = readUuid(in);
        
        short clsid = SmLiveId.getClassId(liveId);
        SmClass cls = this.metamodel.getMClass(clsid);
        
        SmObjectImpl obj = cls.getObjectFactory().createImpl();
        obj.init(uuid, liveId);
        return obj;
    }

    @objid ("dcc0e9d1-493b-11e2-91c9-001ec947ccaf")
    private static void writeRef(DataOutput out, SmObjectImpl val) throws IOException {
        out.writeLong(val.getLiveId());
        
        final String uuid = val.getUuid();
        writeUuid(out, uuid);
    }

    /**
     * Get all possible enumeration values of an enumerate type.
     * 
     * @param type a enumerate class.
     * @return all possible values, ordered.
     */
    @objid ("7e405967-4a00-11e2-91c9-001ec947ccaf")
    private static Object[] getEnums(final Class<?> type) {
        Object[] ret = enumContent.get(type);
        if (ret == null) {
            ret = type.getEnumConstants();
            enumContent.put(type, ret);
        }
        return ret;
    }

    @objid ("7e42bbbb-4a00-11e2-91c9-001ec947ccaf")
    private static String readUuid(DataInput in) throws IOException {
        //        long most = in.readLong();
        //        long least = in.readLong();
        //        
        //        UUID uuid = new UUID(most, least);
        return in.readUTF();
    }

    @objid ("7e42bbc3-4a00-11e2-91c9-001ec947ccaf")
    private static void writeUuid(DataOutput out, final String uuid) throws IOException {
        out.writeUTF(uuid);
        //        out.writeLong(uuid.getMostSignificantBits());
        //        out.writeLong(uuid.getLeastSignificantBits());
    }

    @objid ("7e212a92-3f65-41ef-b379-68dd4d8670f8")
    public CacheEntrySerializer(SmMetamodel metamodel) {
        this.metamodel = metamodel;
    }

}
