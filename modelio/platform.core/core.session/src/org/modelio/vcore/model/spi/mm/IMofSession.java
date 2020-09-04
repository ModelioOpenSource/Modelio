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

package org.modelio.vcore.model.spi.mm;

import java.util.Collection;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Interface provided to {@link IMofRepositoryMigrator} to implements the migration.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("4e1c015e-c28f-40df-b104-24da11b60830")
public interface IMofSession {
    /**
     * Create a new object
     * 
     * @param newMetaclass the new object metaclass
     * @return the created object.
     */
    @objid ("35fed9c8-c8fb-4c4e-bb7a-913a0901ff4a")
    MofSmObjectImpl createObject(MClass newMetaclass);

    /**
     * Create a new model object.
     * @throws MetaclassNotFoundException
     * 
     * @param metaclassName the metaclass name, qualified by preference.
     * @param name the new object name
     * @return the created object
     */
    @objid ("f0e482c9-5295-4c11-9a30-a0ed5014dc9e")
    MofSmObjectImpl createObject(String metaclassName, String name) throws MetaclassNotFoundException;

    /**
     * Find elements by metaclass and attribute value.
     * 
     * @param metaclass the metaclass
     * @param withSubClasses look into sub metaclass hierarchy
     * @param attName the attribute name
     * @param attValue the attribute value
     * @return found elements
     */
    @objid ("744023b2-b352-4905-abf5-8398af054074")
    Collection<MofSmObjectImpl> findByAtt(SmClass metaclass, boolean withSubClasses, String attName, Object attValue);

    /**
     * Get all objects of a metaclass.
     * <p>
     * This method may not return objects outside the migrated fragment that are references
     * by other objects inside the migrated fragment.
     * If you also need them use {@link #getAllForeignReferences(MClass, boolean)}.
     * @see #getAllForeignReferences(MClass, boolean)
     * 
     * @param metaclass a metaclass
     * @param withSubclasses look into sub-metaclasses hierarchy.
     * @return all objects of the metaclass.
     */
    @objid ("187443bd-c729-4e18-b233-0cdef080b71f")
    Collection<MofSmObjectImpl> findByClass(MClass metaclass, boolean withSubclasses);

    /**
     * Get all objects of a metaclass.
     * <p>
     * This method may not return objects outside the migrated fragment that are references
     * by other objects inside the migrated fragment.
     * If you also need them use {@link #getAllForeignReferences(MClass, boolean)}.
     * @see #getAllForeignReferences(MClass, boolean)
     * @throws MetaclassNotFoundException
     * 
     * @param clsName a metaclass name
     * @param withSubClasses look into sub-metaclasses hierarchy.
     * @return all objects of the metaclass.
     */
    @objid ("6067a91c-5cc9-4414-9813-ba43904fdd30")
    Collection<MofSmObjectImpl> findByClass(String clsName, boolean withSubClasses) throws MetaclassNotFoundException;

    /**
     * Find an element from a reference.
     * <p>
     * If you need this object to reference it from another one you should better use {@link #getObjectReference(MRef)}
     * that will return a usable shell object if the referenced object is not in the migrated fragment.
     * @see #getObjectReference(MRef)
     * 
     * @param ref an element reference
     * @return the found element or <i>null</i>.
     */
    @objid ("b31088d5-c0e3-4db5-9414-5452b737dfcb")
    MObject findByRef(MRef ref);

    /**
     * @return the modeling session
     */
    @objid ("282e819b-baf9-4601-9a81-d2edbaa1b799")
    ICoreSession getCoreSession();

    /**
     * @param clsName a metaclass name, preferably qualified
     * @return the found metaclass.
     * @throws org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException if the metaclass does not exist.
     */
    @objid ("f5669634-35a4-4387-93af-ca990a1f0fbf")
    SmClass getMetaclass(String clsName) throws MetaclassNotFoundException;

    /**
     * @return the MOF metamodel
     */
    @objid ("2b756003-d41a-4752-834b-b298007050ff")
    MofMetamodel getMetamodel();

    /**
     * Get a reference object for the given object reference.
     * <p>
     * If the object exists in the MOF session it is directly returned.
     * In the other case a shell object is instantiated and returned.
     * <p>
     * This method should be used instead of {@link #findByRef(MRef)} when you want to reference
     * an object stored in another model fragment.
     * e.g : you want to stereotype an object, the stereotype probably comes from a foreign module
     * that is not loaded in the migration session.
     * @throws MetaclassNotFoundException
     * 
     * @param ref an object reference
     * @return the found object or an unresolved reference shell object.
     */
    @objid ("c97170a4-d611-45f2-861b-f4c2eef4a5e5")
    MofSmObjectImpl getObjectReference(MRef ref) throws MetaclassNotFoundException;

    /**
     * Look for an object by scanning a dependency. Create the element if not found.
     * @throws MetaclassNotFoundException
     * 
     * @param from the source element to scan
     * @param depName the dependency name
     * @param clsName the target metaclass name, preferably qualified
     * @param name the element name
     * @return the found or created element.
     */
    @objid ("c3fd54b2-d407-478d-bb2d-35f462ef1654")
    MofSmObjectImpl getOrCreate(MofSmObjectImpl from, String depName, String clsName, String name) throws MetaclassNotFoundException;

    /**
     * @return the migration report builder
     */
    @objid ("b4526a13-dd27-4fef-adb1-0213205f180e")
    IMigrationReporter getReport();

    /**
     * @return the migrated repository
     */
    @objid ("40edb9d4-9dde-4cc4-9c2c-8cfa4b466088")
    IRepository getTargetRepository();

    /**
     * @param monitor a progress monitor supplier, that will be called only if re-identifications are scheduled.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on failure
     */
    @objid ("e220495a-c10e-4bc8-add6-1d65d57b61af")
    void processScheduledReidentifications(Supplier<SubProgress> monitor) throws MofMigrationException;

    /**
     * Replace the given object by another object of the given metaclass.
     * <p>
     * Warn: For technical reasons the new object has a temporary identifier that is different
     * from the original one.
     * The original object is deleted in the process.
     * The transmuted object will be re-identified at the end of the migration.
     * 
     * @param toTransmute the object to transmute.
     * @param newMetaclass the new metaclass
     * @return the new object.
     */
    @objid ("eafea517-b65c-4b6f-88e5-01e495ac7283")
    MofSmObjectImpl transmute(MofSmObjectImpl toTransmute, MofSmClass newMetaclass);

    /**
     * Create a new model object.
     * 
     * @param mc the metaclass .
     * @param name the new object name
     * @return the created object
     */
    @objid ("b6f6311e-cb3f-4ab3-b5e4-4dcaf24f2eab")
    MofSmObjectImpl createObject(MClass mc, String name);

}
