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
package org.modelio.vcore.session.impl.handles;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Interface for the metaobject helper handling the storage.
 * <p>
 * Should ensures that :
 * <ul>
 * <li>all the composition tree belongs to the same repository.
 * <li>a loading session is started
 * </ul>
 */
@objid ("9164f465-3a25-11e2-bf6c-001ec947ccaf")
public interface IStorageHandle {
    /**
     * @param dep a metamodel relation
     * @return whether the given dependency is physically written in storages.
     */
    @objid ("1f1a5467-3a2d-11e2-bf6c-001ec947ccaf")
    boolean isStored(SmDependency dep);

    /**
     * Load an attribute value
     * @param obj a model object
     * @param att the model attribute to load
     * @param data the model object data
     */
    @objid ("1f1a546a-3a2d-11e2-bf6c-001ec947ccaf")
    void loadAtt(SmObjectImpl obj, SmAttribute att, ISmObjectData data);

    /**
     * Ensure the given dependency is loaded for the given object.
     * @param obj An object
     * @param data The object data, to avoid calling {@link SmObjectImpl#getData()}.
     * @param dep the dependency to load.
     */
    @objid ("1f1a546f-3a2d-11e2-bf6c-001ec947ccaf")
    void loadDep(final SmObjectImpl obj, final ISmObjectData data, final SmDependency dep);

    /**
     * Force loading a model dependency
     * @param obj a model object
     * @param dep a model dependency
     * @param data the model object data to fill
     */
    @objid ("1f1a5477-3a2d-11e2-bf6c-001ec947ccaf")
    void forceLoadDep(SmObjectImpl obj, SmDependency dep, ISmObjectData data);

    /**
     * Called when an object relation is modified
     * @param data The object data, to avoid calling {@link SmObjectImpl#getData()}.
     * @param obj An object
     * @param dep the modified metamodel relation
     * @param dep_val the added object
     */
    @objid ("1f1a547b-3a2d-11e2-bf6c-001ec947ccaf")
    void appendObjDepVal(ISmObjectData data, SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val);

    /**
     * Called when an object relation modification is rollbacked.
     * @param obj An object
     * @param dep the modified metamodel relation
     * @param dep_val the object to remove
     * @param oldValStore the repository where the removed object must belong after this operation.
     */
    @objid ("1f1a5480-3a2d-11e2-bf6c-001ec947ccaf")
    void undoAppendDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, IRepositoryObject oldValStore);

    /**
     * @param obj a model object
     * @param dep a model dependency
     * @param dep_val the searched model object
     * @param data the model object data (for perf optimization)
     * @return the searched object index if found or -1
     */
    @objid ("1f1a5485-3a2d-11e2-bf6c-001ec947ccaf")
    int loadDepIndexOf(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, ISmObjectData data);
}

