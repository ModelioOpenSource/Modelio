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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISwap;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

@objid ("00461d38-702c-1f21-85a5-001ec947cd2a")
public class FileSwap implements ISwap {
    @objid ("3edcfc75-7e46-11e1-bee3-001ec947ccaf")
    private String swapPath;

    @objid ("3f1af8f6-7e46-11e1-bee3-001ec947ccaf")
    private Index<IRepositoryObject> storeIndex = new Index<>();

    @objid ("3f1af8f9-7e46-11e1-bee3-001ec947ccaf")
    private Index<IMetaOf> metaObjectIndex = new Index<>();

    @objid ("912b2bf1-552a-4a68-ac54-c1dc0b2b9683")
    private SmMetamodel metamodel;

    /**
     * @param metamodel the metamodel
     * @param swapPath the swap directory
     */
    @objid ("3f1af8fc-7e46-11e1-bee3-001ec947ccaf")
    public FileSwap(SmMetamodel metamodel, final File swapPath) {
        this.metamodel = metamodel;
        this.swapPath = swapPath.getAbsolutePath();
        //        Log.trace("Swap initialized to:"+swapPath);
    }

    @objid ("17df7294-84b6-11e1-b644-001ec947ccaf")
    @Override
    public void close() {
        try {
            FileUtils.delete(this.swapPath);
        } catch (IOException e) {
            Log.warning("Failed to clean the swap space on "+this.swapPath+":");
            Log.warning(e);
        }
    }

    @objid ("004681ba-702c-1f21-85a5-001ec947cd2a")
    @Override
    public SmObjectData restore(final String uuid) throws IOError {
        File fileName = getFileName(uuid);
        if (! fileName.isFile()) {
            return null;
        }
        
        try (ObjectInputStream s = new ObjectInputStream(new FileInputStream(fileName))){
        
            SmObjectData data = (SmObjectData) s.readObject();
        
        //            Log.trace("Swap restoring: "+data.getClassOf().getName()+ " "+data.getUuid());
        
            data.setRepositoryObject(this.storeIndex.getObject(s.readInt()));
            data.setMetaOf(this.metaObjectIndex.getObject(s.readInt()));
        
            return data;
        } catch (FileNotFoundException e) {
            throw new IOError(e);
        } catch (IOException e) {
            throw new IOError(e);
        } catch (ClassNotFoundException e) {
            throw new IOError(e);
        } finally {
            //new File(getFileName(id)).delete();
        }
    }

    @objid ("004649d4-702c-1f21-85a5-001ec947cd2a")
    @Override
    public void swap(final SmObjectData data) throws IOError {
        //        Log.trace("Swap "+data.getClassOf().getName()+ " "+data.getUuid());
                try (ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream(getFileName(data.getUuid())))){
                    s.writeObject(data);
                    s.writeInt(this.storeIndex.getId(data.getRepositoryObject()));
                    s.writeInt(this.metaObjectIndex.getId(data.getMetaOf()));
                } catch (IOException e) {
                    Log.error(e);
                    throw new IOError(e);
                }
    }

    @objid ("00465cee-702c-1f21-85a5-001ec947cd2a")
    private File getFileName(final String id) {
        return new File(this.swapPath,id.toString());
    }

    /**
     * Index that assigns an unique integer to an object and can retrieve one from the other.
     * <p>
     * The objects are all stored by weak reference.
     * 
     * @param <T> the type of the elements to index.
     */
    @objid ("3edcfc76-7e46-11e1-bee3-001ec947ccaf")
    private static final class Index<T> {
        @objid ("3f189693-7e46-11e1-bee3-001ec947ccaf")
        private int indexCount = 0;

        @objid ("3f2946da-7e46-11e1-bee3-001ec947ccaf")
        private final Map<T, Integer> objectToIdMap = new WeakHashMap<>();

        @objid ("3f1af8e3-7e46-11e1-bee3-001ec947ccaf")
        private final Map<Integer, WeakReference<T>> idToObjectMap = new HashMap<>();

        @objid ("3f1af8ea-7e46-11e1-bee3-001ec947ccaf")
        public synchronized int getId(final T repositoryObject) {
            Integer i = this.objectToIdMap.get(repositoryObject);
            if (i == null) {
                i = Integer.valueOf(this.indexCount);
                this.objectToIdMap.put(repositoryObject, i);
                this.idToObjectMap.put(i, new WeakReference<>(repositoryObject));
                this.indexCount++;
            }
            return i.intValue();
        }

        @objid ("3f1af8f0-7e46-11e1-bee3-001ec947ccaf")
        public synchronized T getObject(final int index) {
            WeakReference<T> ref = this.idToObjectMap.get(Integer.valueOf(index));
            
            if (ref == null) {
                throw new Error(index+" repository object not in map.");
            }
            if (ref.get() == null) {
                throw new Error(index+" repository object no more in memory.");
            }
            return ref.get();
        }

        @objid ("dd913d00-cb55-11e1-87f1-001ec947ccaf")
        public Index() {
        }

    }

}
