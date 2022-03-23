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
package org.modelio.vcore.session.impl.storage;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.IPStatus;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * API that must be used by {@link org.modelio.vcore.session.api.repository.IRepository IRepository} implementation to create objects and load them.
 * <p>
 * Each repository has its own loader.
 */
@objid ("006468c4-5447-1f29-adbc-001ec947cd2a")
public interface IModelLoader extends AutoCloseable {
    /**
     * Create a model object that is being loaded.
     * @param classof The metaclass
     * @param id The identifier
     * @return the object ready to be to loaded
     * @throws DuplicateObjectException if another object with the same identifier already exist in another repository.
     */
    @objid ("002c6636-5454-1f29-adbc-001ec947cd2a")
    SmObjectImpl createLoadedObject(final SmClass classof, final String id) throws DuplicateObjectException;

    /**
     * Find or create a model object coming from a different repository.
     * <p>
     * If the model object is found in a repository, return it. In the other case create
     * a shell model object and return it.
     * @param classof The metaclass
     * @param id The identifier
     * @param name The shell object name.
     * @return the found or created object.
     */
    @objid ("bd7ed618-92d7-11e1-81e9-001ec947ccaf")
    SmObjectImpl loadForeignObject(final SmClass classof, final String id, final String name);

    /**
     * Load a dependency content.
     * @param obj The object to load
     * @param dep the dependency to load
     * @param newContent the new dependency content.
     */
    @objid ("525ad08f-064d-11e2-9eb7-001ec947ccaf")
    void loadDependency(SmObjectImpl obj, SmDependency dep, List<SmObjectImpl> newContent);

    /**
     * Load an attribute value.
     * @param obj The object to load
     * @param att the attribute to load.
     * @param newValue the attribute value.
     */
    @objid ("525ad096-064d-11e2-9eb7-001ec947ccaf")
    void loadAttribute(SmObjectImpl obj, SmAttribute att, Object newValue);

    /**
     * @return the metaobject each loaded object should have.
     */
    @objid ("7dbf9e3c-1c43-11e2-8eb9-001ec947ccaf")
    IMetaOf getMetaOf();

    /**
     * Create a loaded model object from the given object data.
     * @param cls the object class
     * @param uuid the object String
     * @param d the object data
     * @return the loaded ready object.
     * @throws DuplicateObjectException if another object with the same identifier already exist in another repository.
     */
    @objid ("f69a23d2-3948-11e2-920a-001ec947ccaf")
    SmObjectImpl createLoadedObject(SmClass cls, String uuid, SmObjectData d) throws DuplicateObjectException;

    /**
     * Create, initialize and set a new model object data for the given model object.
     * <p>
     * To be called when recovering a garbage collected model object data .
     * @param obj the model object to recover.
     * @return the new model object data.
     */
    @objid ("dbc589f2-4868-11e2-91c9-001ec947ccaf")
    ISmObjectData createObjectData(SmObjectImpl obj);

    /**
     * Initialize the state of the given runtime flags.
     * <p>
     * The other flags remain untouched.
     * <p>
     * Use the constants defined in {@link IRStatus}.
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * @param obj the object to initialize
     * @param trueFlags a combination of flags to set.
     * @param falseFlags a combination of flags to unset.
     * @param undefFlags a combination of flags to undefine.
     */
    @objid ("e5a3f357-4e4a-45fd-aa19-196c9dbeec09")
    void setRStatus(SmObjectImpl obj, long trueFlags, long falseFlags, long undefFlags);

    /**
     * Initialize the state of the given persistent flags.
     * <p>
     * The other flags remain untouched.
     * <p>
     * Use the constants defined in {@link IPStatus}.
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * @param obj the object to initialize
     * @param trueFlags a combination of flags to set.
     * @param falseFlags a combination of flags to unset.
     * @param undefFlags a combination of flags to undefine.
     */
    @objid ("1b63cdb7-1655-4c2d-890f-e122df8a3dff")
    void setPStatus(SmObjectImpl obj, long trueFlags, long falseFlags, long undefFlags);

    @objid ("064307ee-d7c9-4808-8db9-fc5690a470e1")
    @Override
    void close();

    /**
     * Begin a loading session.
     */
    @objid ("bbdb3793-a7c1-4d56-af35-ad1f5a4ab2fa")
    void begin();

}
