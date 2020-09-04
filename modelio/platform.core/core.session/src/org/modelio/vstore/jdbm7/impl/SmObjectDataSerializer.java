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

package org.modelio.vstore.jdbm7.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * JDBM {@link Serializer} optimized for {@link SmObjectData}.
 */
@objid ("9395c5ed-ed72-403b-8759-a2231754805c")
public class SmObjectDataSerializer {
    @objid ("ee4f79b4-e5d2-4159-921e-d6e7362cccc9")
    private byte rid;

    @objid ("daa863bb-38d0-4bee-9c74-226db7205888")
    private static Map<Class<?>,Object[]> enumContent = new HashMap<>();

    @objid ("ffc47319-d4f2-480e-8b6a-016ae36c7d03")
    private ILoadHelper loadHelper;

    @objid ("a448c5f7-d654-4dd4-9ea9-25f844b98bdf")
    public SmObjectDataSerializer(byte rid, ILoadHelper loadHelper) {
        assert (rid >=0);
        
        this.rid = rid;
        this.loadHelper = loadHelper;
    }

    @objid ("c63f174f-bf24-47b3-8bf9-8f679ba511e5")
    public void serialize(SerializerOutput out, SmObjectData obj) throws IOException {
        SmClass cls = obj.getClassOf();
        
        out.writeUTF(cls.getQualifiedName());
        
        for (SmAttribute att : cls.getAllAttDef()) {
            writeAttribute(out, obj, att);
        }
        
        for (SmDependency  d: cls.getAllDepDef()) {
            if (Helper.isPersistent(d)) {
                writeMultipleDep(out, obj, d);
            }
        }
    }

    @objid ("6d1372bb-b94d-485b-b079-6028f1e764be")
    public void deserialize(SerializerInput in, SmObjectImpl obj, IModelLoader loader) throws DuplicateObjectException, IOException {
        final String clsName = in.readUTF();
        final SmClass cls = this.loadHelper.getClass(clsName);
        
        for (SmAttribute att : cls.getAllAttDef()) {
            readAttribute(in, obj, att, loader);
        }
        
        for (SmDependency  d: cls.getAllDepDef()) {
            if (Helper.isPersistent(d)) {
                readDep(in, obj, d, loader);
            }
        }
    }

    @objid ("55830c5b-96bf-4d33-845b-84ed853bdf2b")
    private void readAttribute(final DataInput in, final SmObjectImpl obj, final SmAttribute att, IModelLoader loader) throws IOException {
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
        //        } else if (type == IRStatus.class || type == IPStatus.class) {
        //            val = SmStatus.getPersistentBits(in.readLong());
        } else if (type.isEnum()) {
            val= getEnums(type)[in.readInt()];
        } else {
            throw new UnsupportedOperationException(type+" "+att+" attribute not supported.");
        }
        
        loader.loadAttribute(obj, att, val);
    }

    @objid ("cb08e2af-38cd-4fa2-bc26-0ca0c87641d2")
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

    @objid ("ed327888-8b6f-4eb2-922a-4fc306ad05bb")
    private void writeMultipleDep(DataOutput out, SmObjectData obj, SmDependency md) throws IOException {
        Collection<SmObjectImpl> content = md.getValueAsCollection(obj);
        
        //out.writeUTF(md.getName());
        out.writeInt(content.size());
        
        for (SmObjectImpl val : content) {
            writeRef(out, val);
        }
    }

    @objid ("08661f2f-a2f9-41e8-a597-9c6a06413e4e")
    private void readDep(DataInput in, SmObjectImpl obj, SmDependency d, IModelLoader loader) throws DuplicateObjectException, IOException {
        //String depName = in.readUTF();
        
        //assert (depName.equals(d.getName())) : depName + " !=" + d;
        
        final int size = in.readInt();
        List<SmObjectImpl> vals = new ArrayList<>(size);
        
        for (int i=0; i<size; i++) {
            final SmObjectImpl depVal = readRef(in, loader);
            if (depVal != null) {
                vals.add(depVal);
            }
        }
        
        loader.loadDependency(obj, d, vals);
    }

    @objid ("a051c1ee-5c23-4ffe-babb-4d0007ed542e")
    private SmObjectImpl readRef(DataInput in, IModelLoader loader) throws DuplicateObjectException, IOException {
        boolean isLocal = in.readBoolean();
        
        String clsid = in.readUTF();
        String uuid = in.readUTF();
        
        SmClass cls = this.loadHelper.getClass(clsid);
        
        if (cls == null) {
            return null;
        }
        
        SmObjectImpl ret = decodeRef(loader, isLocal, uuid, cls);
        return ret;
    }

    @objid ("4cd76089-c1be-4322-8d3e-445581ce2fb1")
    private SmObjectImpl decodeRef(IModelLoader loader, boolean isLocal, String uuid, SmClass cls) throws DuplicateObjectException {
        SmObjectImpl ret;
        if (isLocal) {
            ret = this.loadHelper.getLocalRef(cls, uuid, loader);
        } else {
            ret = loader.loadForeignObject(cls, uuid, "");
        }
        return ret;
    }

    @objid ("6d5989b3-8fb3-47cb-aedf-3328ed977939")
    private void writeRef(DataOutput out, SmObjectImpl val) throws IOException {
        // Write islocal
        out.writeBoolean( val.getRepositoryObject().getRepositoryId() == this.rid);
        
        out.writeUTF(val.getMClass().getQualifiedName());
        out.writeUTF(val.getUuid());
    }

    /**
     * Get all possible enumeration values of an enumerate type.
     * 
     * @param type a enumerate class.
     * @return all possible values, ordered.
     */
    @objid ("9e64a840-05f8-4ff7-9444-e1a2337844fa")
    private static Object[] getEnums(final Class<?> type) {
        Object[] ret = enumContent.get(type);
        if (ret == null) {
            ret = type.getEnumConstants();
            enumContent.put(type, ret);
        }
        return ret;
    }

}
