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
package org.modelio.vstore.jdbm.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.Serializer;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelRefresher;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.jdbm.index.MRefSerializer;
import org.modelio.vstore.jdbm.index.StringTable;

/**
 * JDBM {@link Serializer} optimized for {@link SmObjectData}.
 */
@objid ("f7530e95-89c0-40e4-bfe2-87dd942da578")
public class SmObjectDataSerializer {
    @objid ("19e1eaca-4337-46dd-8790-aa89993caf4a")
    private final byte rid;

    @objid ("c556c337-088e-4bbd-b251-c3e5f4e0d0d1")
    private static final Map<Class<?>, Object[]> enumContent = new HashMap<>();

    @objid ("a7c44e99-272c-4e12-ae66-6f90a53204d2")
    private final ILoadHelper loadHelper;

    @objid ("ae811d50-5d56-49a8-8d2b-96890d8a2f78")
    private final StringTable stringTable;

    @objid ("6d6984d5-1dd6-4555-b2dd-eeab6bb7ca25")
    public  SmObjectDataSerializer(byte rid, ILoadHelper loadHelper, StringTable depTable) {
        assert (rid >= 0);
        assert (depTable != null);
        assert (loadHelper != null);
        
        this.stringTable = depTable;
        this.rid = rid;
        this.loadHelper = loadHelper;
        
    }

    @objid ("6cb7b562-1f6f-4967-a4ae-88db5d9eb810")
    public void serialize(SerializerOutput out, SmObjectData obj) throws IOException {
        SmClass cls = obj.getClassOf();
        
        // Write metaclass name
        writeStringSymbol(out, cls.getQualifiedName());
        
        // Write attributes count
        List<SmAttribute> allAtts = cls.getAllAttDef();
        out.writePackedInt(allAtts.size());
        
        for (SmAttribute att : allAtts) {
            writeAttribute(out, obj, att);
        }
        
        // Write all persistent dependencies
        for (SmDependency  d: cls.getAllDepDef()) {
            if (Helper.isPersistent(d)) {
                writeMultipleDep(out, obj, d);
            }
        }
        
        // end of SmDependencies marker.
        out.writePackedLong(0);
        
    }

    /**
     * Read the SmObjectImpl content from the stream.
     * @param in the stream
     * @param obj the model object to populate
     * @param loader the model loader
     * @throws IOException on JDBM I/O failure
     * @throws DuplicateObjectException on duplicate identifier
     */
    @objid ("5ff47953-2f25-4c9a-830b-78adb504ee80")
    public void deserialize(SerializerInput in, SmObjectImpl obj, IModelLoader loader) throws IOException, DuplicateObjectException {
        final String clsName = readStringSymbol(in);
        final SmClass cls = this.loadHelper.getClass(clsName);
        
        int nbAtts = in.readPackedInt();
        for (int i=0; i<nbAtts; i++) {
            readAttribute(in, obj, cls, loader);
        }
        
        // Dirty optimization to avoid cleaning known to be empty dependencies.
        if (loader instanceof IModelRefresher) {
            // Read and record read dependencies
            Collection<SmDependency> readDeps = new HashSet<>();
            
            for (SmDependency d = readDep(in, obj, cls, loader);
                    d != null; 
                    d = readDep(in, obj, cls, loader)) 
            {
                readDeps.add(d);
            }
        
            // Clean not read dependencies.
            for (SmDependency  d: cls.getAllDepDef()) {
                if (Helper.isPersistent(d) && ! readDeps.contains(d)) {
                    loader.loadDependency(obj, d, Collections.emptyList());
                }
            }
        } else {
            // Read dependencies
            for (SmDependency d = readDep(in, obj, cls, loader);
                    d != null; 
                    d = readDep(in, obj, cls, loader)) {
                // empty
            }
        }
        
    }

    @objid ("73db7707-71b7-4b3d-b80c-662ac38d1eb0")
    private void readAttribute(final SerializerInput in, final SmObjectImpl obj, final SmClass cls, IModelLoader loader) throws IOException {
        String attName = readStringSymbol(in);
        SmAttribute att = cls.getAttributeDef(attName);
        if (att != null) {
            final Class<?> type = att.getType();
            Object val;
            if (type == String.class){
                int len = in.readPackedInt();
                StringBuilder s = new StringBuilder(len);
                for (int i=0; i < len; i++) {
                    s.append(in.readChar());
                }
        
                val = s.toString();
            } else if (type == Integer.class) {
                val = in.readPackedInt();
            } else if (type == Long.class) {
                val = in.readPackedLong();
            } else if (type == Byte.class) {
                val = in.readByte();
            } else if (type == Boolean.class) {
                val = in.readBoolean();
                //        } else if (type == IRStatus.class || type == IPStatus.class) {
                //            val = SmStatus.getPersistentBits(in.readLong());
            } else if (type.isEnum()) {
                val= getEnums(type)[in.readPackedInt()];
            } else {
                throw new UnsupportedOperationException(type+" "+att+" attribute not supported.");
            }
        
            if (loader != null) {
                loader.loadAttribute(obj, att, val);
            }
        }
        
    }

    @objid ("2d6f8054-b226-44b3-8853-a88b28b824a9")
    private void writeAttribute(final SerializerOutput out, final SmObjectData obj, final SmAttribute att) throws IOException {
        writeStringSymbol(out, att.getName());
        
        final Object val = att.getValue(obj);
        final Class<?> type = att.getType();
        if (type == String.class){
            final String s = (String) val;
            out.writePackedInt(s.length());
            out.writeChars(s);
        } else if (type == Integer.class) {
            out.writePackedInt((int) val);
        } else if (type == Long.class) {
            out.writePackedLong((long) val);
        } else if (type == Byte.class) {
            out.writeByte((int) val);
        } else if (type == Boolean.class) {
            out.writeBoolean((boolean) val);
        } else if (type == IRStatus.class || type == IPStatus.class) {
            // skip
            //out.writeLong(((SmStatus) val).toLong());
        } else if (type.isEnum()) {
            Enum<?> en = (Enum<?>)val;
            out.writePackedInt(en.ordinal());
        } else {
            throw new UnsupportedOperationException(type+" "+val+" "+att+" attribute not supported.");
        }
        
    }

    @objid ("1d8b4d73-b9c1-4258-9f98-b9a5e11166cc")
    private void writeMultipleDep(SerializerOutput out, SmObjectData obj, SmDependency md) throws IOException {
        Collection<SmObjectImpl> content = md.getValueAsCollection(obj);
        
        if (! content.isEmpty()) {
            writeStringSymbol(out, md.getName());
            out.writePackedInt(content.size());
        
            for (SmObjectImpl val : content) {
                writeRef(out, val);
            }
        }
        
    }

    @objid ("3c978ea8-de8f-4528-9ac6-447b1e65baaa")
    private SmDependency readDep(SerializerInput in, SmObjectImpl obj, SmClass cls, IModelLoader loader) throws IOException, DuplicateObjectException {
        long depLid = in.readPackedLong();
        if (depLid == 0) {
            return null;
        } else {
            final int size = in.readPackedInt();
            String depName = this.stringTable.getString(depLid);
            SmDependency d = cls.getDependencyDef(depName);
            
            if (d == null) {
                // eat references and ignore them
                for (int i=0; i<size; i++) {
                    readRef(in, null);
                }
            } else {
                List<SmObjectImpl> vals = new ArrayList<>(size);
        
                for (int i=0; i<size; i++) {
                    final SmObjectImpl depVal = readRef(in, loader);
                    if (depVal != null) {
                        vals.add(depVal);
                    }
                }
        
                loader.loadDependency(obj, d, vals);
            }
            
            return d;
        }
        
    }

    @objid ("b7c5f0bd-fa9e-4e1c-bcd9-595076a80f00")
    private SmObjectImpl readRef(SerializerInput in, IModelLoader loader) throws IOException, DuplicateObjectException {
        boolean isLocal = in.readBoolean();
        
        MRef ref = MRefSerializer.instance.deserialize(in);
        if (loader != null) {
            SmObjectImpl ret = decodeRef(loader, isLocal, ref);
            return ret;
        } else {
            return null;
        }
        
    }

    @objid ("50e49ea1-6a0e-495c-af64-6ba04ab0a5f4")
    private SmObjectImpl decodeRef(IModelLoader loader, boolean isLocal, MRef ref) throws DuplicateObjectException {
        SmClass cls = this.loadHelper.getClass(ref.mc);
        SmObjectImpl ret;
        if (isLocal) {
            ret = this.loadHelper.getLocalRef(cls, ref.uuid, loader);
        } else {
            ret = loader.loadForeignObject(cls, ref.uuid, ref.name);
        }
        return ret;
    }

    @objid ("634d5c23-edaf-42e4-ae1e-32046a6fcad5")
    private void writeRef(SerializerOutput out, SmObjectImpl val) throws IOException {
        // Write islocal
        out.writeBoolean( val.getRepositoryObject().getRepositoryId() == this.rid);
        
        // Don't put the name in the MRef to save space and spare potential loading 
        MRef ref = new MRef(val.getMClass().getQualifiedName(), val.getUuid(), "");
        MRefSerializer.instance.serialize(out, ref);
        
    }

    /**
     * Get all possible enumeration values of an enumerate type.
     * @param type a enumerate class.
     * @return all possible values, ordered.
     */
    @objid ("cdcc6703-457f-4e72-a077-4f7125e37a4e")
    private static Object[] getEnums(final Class<?> type) {
        Object[] ret = enumContent.get(type);
        if (ret == null) {
            ret = type.getEnumConstants();
            enumContent.put(type, ret);
        }
        return ret;
    }

    @objid ("3b4bf1a4-b7fa-4997-8507-4d62dda9deac")
    private String readStringSymbol(SerializerInput in) throws IOException {
        return this.stringTable.getString(in.readPackedLong());
    }

    @objid ("bbbb76f5-c9e2-42b3-84f2-984ac3d02936")
    private void writeStringSymbol(SerializerOutput out, String symbol) throws IOException {
        long symbolLid = this.stringTable.getOrAddKey(symbol);
        out.writePackedLong(symbolLid);
        
    }

    /**
     * Parse the input stream and call the consumer of each found dependency reference.
     * @param in input stream
     * @param refConsumer reference consumer
     * @throws IOException on I/O failure
     */
    @objid ("7a32bf96-df88-48a7-9dba-2d7e5e662f3e")
    public void parseCrossRefs(final SerializerInput in, RefConsumer refConsumer) throws IOException {
        final String clsName = readStringSymbol(in);
        final SmClass cls = this.loadHelper.getClass(clsName);
        
        int nbAtts = in.readPackedInt();
        for (int i=0; i<nbAtts; i++) {
            readAttribute(in, null, cls, null);
        }
        
        // Read dependencies
        while (parseDep(in, refConsumer)) {
            // empty
        }
        
    }

    @objid ("a9e55ed4-73d7-4be2-8b82-0f794d1965ee")
    private boolean parseDep(SerializerInput in, RefConsumer refConsumer) throws IOException {
        long depLid = in.readPackedLong();
        if (depLid == 0) {
            return false;
        } else {
            final int size = in.readPackedInt();
            String depName = this.stringTable.getString(depLid);
        
            for (int i=0; i<size; i++) {
                @SuppressWarnings("unused")
                boolean isLocal = in.readBoolean();
                MRef ref = MRefSerializer.instance.deserialize(in);
        
                refConsumer.accept(depName, ref);
            }
        
            return true;
        }
        
    }

    /**
     * Recipient for {@link SmObjectDataSerializer#parseCrossRefs(SerializerInput, RefConsumer)}
     */
    @objid ("02f80497-2cca-4769-a98c-c867288c21fe")
    public interface RefConsumer {
        /**
         * @param depName the dependency name
         * @param ref one dependency target
         * @throws IOException on I/O failure
         */
        @objid ("c7bc2dcf-87fb-4a2a-a955-d233ce1614d6")
        void accept(String depName, MRef ref) throws IOException;

    }

}
