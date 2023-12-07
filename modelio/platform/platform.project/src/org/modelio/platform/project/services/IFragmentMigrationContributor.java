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
package org.modelio.platform.project.services;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.gproject.MigrationFailedException;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Contributor to metamodel migration of model fragments.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("8baa3447-1fad-4d1c-afc5-d4a905a64080")
public interface IFragmentMigrationContributor {
    /**
     * Called after model objects have been migrated.
     * <p>
     * The fragment is mount, and you need to open a transaction if you need to modify the model.
     * the project is accessible with IGModelFragment#get
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
     * to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @param reporter Interface to report migration log and result.
     * @param gproject The project being migrated.
     * @param f the migrated project fragment.
     * @param fromVersion the initial metamodel version
     * @param eclipseContext The Eclipse context
     * @throws MigrationFailedException to cancel the migration.
     */
    @objid ("b55f9044-6832-48c5-bb0b-d12f55af6e48")
    void contributeMigration(IModelioProgress monitor, IMigrationReporter reporter, IGProject gproject, IGModelFragment f, MetamodelVersionDescriptor fromVersion, IEclipseContext eclipseContext) throws MigrationFailedException;

    /**
     * Get the target Modelio version this migrator is made for.
     * <p>
     * It will be used as key to sort migration contributors.
     * @return the target Modelio version this migrator is made for.
     * @since 5.4.1 26/10/2023
     */
    @objid ("cd431ed9-c1d1-45c5-a43c-7dace27550cd")
    Version getTargetModelioVersion();
}

