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
package org.modelio.diagram.persistence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface is to be implemented if a {@link IPersistent} goes through a change important enough to require a major version change (for example: its inheritance tree is modified). In such a case, a class implementing this interface is to be provided
 * <strong>in the same package as the IPersistent and the name of the implementing class must be <em>NameOfTheIPersistent</em>Migrator, with <em>NameOfTheIPersistent</em> being the name of the IPersistent</strong>.
 */
@objid ("cb7049c3-186f-11e2-92d2-001ec947c8cc")
public interface IPersistentMigrator {
    /**
     * Instantiate a version of the {@link IPersistent} as it was when its major version was the given parameter. The returned instance can then be used to read the serialisation string corresponding to the version without risk.
     * @param majorVersionToInstantiate the major version of the instance requested.
     * @return an instance of IPersistent at the requested version. Might be <code>null</code>.
     */
    @objid ("cb7049c5-186f-11e2-92d2-001ec947c8cc")
    IPersistent createInstanceOfMajorVersion(final int majorVersionToInstantiate);

    /**
     * Returns an instance of IPersistent with the most recent major version, using as much information from the given IPersistent as possible.
     * @param instanceToMigrate an instance of a previous major version to be used as source of information.
     * @return an instance of IPersistent with the most recent major version based on the given instance. Might be <code>null</code>.
     */
    @objid ("cb7049ca-186f-11e2-92d2-001ec947c8cc")
    IPersistent migrate(final IPersistent instanceToMigrate);
}

