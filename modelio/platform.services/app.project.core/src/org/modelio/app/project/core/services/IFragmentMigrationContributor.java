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

package org.modelio.app.project.core.services;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.migration.MigrationFailedException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vbasic.progress.IModelioProgress;
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
     * the project is accessible with IProjectFragment#get
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
     * to call done() on the given monitor. Accepts <i>null</i>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @param reporter Interface to report migration log and result.
     * @param gproject The project being migrated.
     * @param f the migrated project fragment.
     * @param fromVersion the initial metamodel version
     * @param eclipseContext The Eclipse context
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException to cancel the migration.
     */
    @objid ("b55f9044-6832-48c5-bb0b-d12f55af6e48")
    void contributeMigration(IModelioProgress monitor, IMigrationReporter reporter, GProject gproject, IProjectFragment f, MetamodelVersionDescriptor fromVersion, IEclipseContext eclipseContext) throws MigrationFailedException;

}
