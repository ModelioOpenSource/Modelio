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
package org.modelio.vstore.exml.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * EXML repository directories creator.
 */
@objid ("c898981d-5f0b-11e1-a535-001ec947ccaf")
class ExmlRepositoryCreator {
    @objid ("0396e17b-6132-11e1-a535-001ec947ccaf")
    private final IExmlRepositoryGeometry geometry;

    @objid ("0e971ab1-3fd4-41b2-9cfd-26d516b0c69b")
    private final MMetamodel metamodel;

    @objid ("e8dbe611-cc5a-4ab4-82f2-4931bdda5a7e")
    private final Path repositoryPath;

    @objid ("03bd06db-6132-11e1-a535-001ec947ccaf")
    public  ExmlRepositoryCreator(Path repositoryPath, final IExmlRepositoryGeometry geometry, MMetamodel metamodel) {
        this.repositoryPath = Objects.requireNonNull(repositoryPath);
        this.geometry = Objects.requireNonNull(geometry);
        this.metamodel = Objects.requireNonNull(metamodel);
        
    }

    /**
     * Create the repository directory structure in the given directory
     * @throws IOException in case of error creating the repository structure.
     */
    @objid ("03fb0397-6132-11e1-a535-001ec947ccaf")
    public void createRepositoryStructure() throws IOException {
        // Create "model" directory
        for (String dir : this.geometry.getInitialDirectories(this.metamodel)) {
            Files.createDirectories(this.repositoryPath.resolve(dir));
        }
        
        // Create index directory.
        Files.createDirectories(this.repositoryPath.resolve(IExmlRepositoryGeometry.INDEX_DIRNAME));
        
    }

    /**
     * Delete the repository
     * @throws IOException in case of failure
     */
    @objid ("03ffc844-6132-11e1-a535-001ec947ccaf")
    public void delete() throws IOException {
        FileUtils.delete(this.repositoryPath);
    }

}
