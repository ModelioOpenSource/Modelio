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
package org.modelio.vcore.session.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.storage.memory.MemoryRepository;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;

/**
 * Builder/Descriptor to create a {@link CoreSession}.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("77ad8320-9b68-4086-a98f-e19b22b55cde")
public class CoreSessionBuilder {
    @objid ("3aabb870-a605-4497-8aa5-84306485a0b5")
    private SmMetamodel metamodel;

    @objid ("1e69d54d-ec7f-4ad3-9cad-2ba334229d0d")
    private File swapDir;

    @objid ("de1ad218-f6be-4403-8f8a-36cfa9607bd5")
    private IRepository shellRepository;

    @objid ("3438abc9-8905-474e-b6ef-b82120a16649")
    public  CoreSessionBuilder() {
        this.metamodel = new SmMetamodel();
    }

    /**
     * Set the metamodel to use.
     * @param metamodel_ a metamodel
     * @return this instance.
     */
    @objid ("eadb444d-f25e-448b-baa4-d5a45ff427ba")
    public CoreSessionBuilder withMetamodel(SmMetamodel metamodel_) {
        this.metamodel = metamodel_;
        return this;
    }

    /**
     * Setup the builder for a metamodel migration.
     * <p>
     * A coreSession for migration has:<ul>
     * <li>a {@link MofMetamodel MOF metamodel}
     * <li>the shell repository is read write to allow transmutations
     * </ul>
     * If the metamodel is already set it is copied as a MOF metamodel
     * to be freely modifiable.
     * @return this instance.
     */
    @objid ("db6f1318-37c6-40d0-8cb5-f31ef0e34d6e")
    public CoreSessionBuilder forMetamodelMigration() {
        MofMetamodel mofMetamodel = new MofMetamodel();
        if (this.metamodel != null) {
            mofMetamodel.copy(this.metamodel);
        }
        
        this.metamodel = mofMetamodel;
        
        // For migration we need a writeable repository to transmute
        // shell objects.
        this.shellRepository = new MemoryRepository();
        return this;
    }

    /**
     * Create and empties the swap directory.
     * @throws java.io.IOError
     * in case of failure
     * @return the swap directory path
     */
    @objid ("064f3c11-5685-46ae-914a-23d66f195833")
    public CoreSessionBuilder createSwapSpace() throws IOException {
        Path swapPath = Files.createTempDirectory("modelio.swap");
        this.swapDir = swapPath.toFile();
        this.swapDir.deleteOnExit();
        return this;
    }

    @objid ("d8dbc617-33c0-40c8-be93-0922b6dbd6ea")
    public CoreSessionBuilder withSwapDirectory(File swapPath) {
        this.swapDir = swapPath;
        return this;
    }

    /**
     * Create the modeling session.
     * @return the created session
     * @throws IOException on I/O failure.
     */
    @objid ("7d4358b3-6653-4e1a-b39c-e7c9598bd635")
    public CoreSession build() throws IOException {
        if (this.swapDir == null) {
            createSwapSpace();
        }
        return new CoreSession(this);
    }

    @objid ("428c9460-7549-4906-b057-c5f6894faec1")
    public File getSwapDirectory() {
        return this.swapDir;
    }

    @objid ("5efd325d-d72a-4b5a-bad1-9e04c36e3954")
    public SmMetamodel getMetamodel() {
        return this.metamodel;
    }

    @objid ("7a9b7fa4-b4dd-45a3-a66f-dd4c6a3d447e")
    public IRepository getShellRepository() {
        if (this.shellRepository == null) {
            this.shellRepository = new ShellObjectsRepository();
        }
        return this.shellRepository;
    }

}
