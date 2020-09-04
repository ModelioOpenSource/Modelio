/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vcore.smkernel.mapi;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * MMetamodel represent the so-called 'runtime metamodel' used by Modelio kernel at run time.<br/>
 * <p>
 * Note the Modelio kernel does not know anything about how to load the metamodel.
 * Practically, MMetamodel is only a repository for known MClass instances.
 * 
 * @since Modelio 3.4
 */
@objid ("9e22e0af-bc13-4be9-8c46-2faafb5b7265")
public interface MMetamodel {
    /**
     * Get a meta class by its java interface
     * <p>
     * The meta class must be passed as an Java class defining the meta class structure.
     * @param interf a metaclass java interface.
     * @return the meta class corresponding to the 'interf' Java interface class,
     * <i>null</i> if it cannot be found
     */
    @objid ("33404c85-a093-48f9-921a-d4feb31fd8d3")
    MClass getMClass(Class<? extends MObject> interf);

    /**
     * Get a meta class by its name or its {@link MClass#getQualifiedName() qualified name} .
     * @param name the metaclass name.
     * @return the meta class named 'name', <i>null</i> if it does not exist.
     */
    @objid ("e39b0870-0056-4917-b56f-3c4478dcab90")
    MClass getMClass(final String name);

    /**
     * Get all the metamodel classes.
     * @return the metamodel classes.
     */
    @objid ("7220c1db-55bf-4459-a011-845c6b46d6ab")
    Collection<? extends MClass> getRegisteredMClasses();

    /**
     * Get the metamodel expert, relying on experts provided by each metamodel fragment.
     * @return the metamodel expert.
     */
    @objid ("58f98916-a197-4498-8abb-e50a2f0e142e")
    MExpert getMExpert();

    /**
     * Get the registered metamodel fragments.
     * <p>
     * The returned list is not modifiable.
     * </p>
     * @return the metamodel fragments.
     */
    @objid ("40b722b2-b948-4830-8704-0dd3d6f3e9e4")
    Collection<? extends MMetamodelFragment> getFragments();

    /**
     * Get the registered metamodel fragments with and fake fragments if asked.
     * <p>
     * The returned list is not modifiable.
     * @param withFakes if true the returned collection also contains fake metamodel fragments.
     * @return the requested metamodel fragments.
     */
    @objid ("cf4f4c31-975b-4477-842a-969ac80d1af5")
    Collection<ISmMetamodelFragment> getFragments(boolean withFakes);

    /**
     * Get the registered fragments sorted by dependencies.
     * <p>
     * The first fragment needs nobody.
     * @return the sorted metamodel fragments.
     * @throws java.lang.IllegalStateException if there is a cycle in the metamodel fragment dependencies
     * @since 3.6
     */
    @objid ("3b433f37-279b-4726-b211-2a05def7236b")
    List<? extends MMetamodelFragment> getSortedFragments() throws IllegalStateException;

}
