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
import org.modelio.gproject.gproject.GProject;

/**
 * {@link FragmentsMigrator} factory interface.
 * @since 4.0
 */
@objid ("24101563-f6ee-44e1-9429-9c6c970d5904")
@FunctionalInterface
public interface IFragmentMigratorFactory {
    /**
     * Get a fragment migrator
     * @param eclipseContext the E4 context
     * @param project the project to work on.
     * @param withConfirmation whether to ask user for confirmation.
     * @return a fragment migrator. Never null.
     */
    @objid ("57c179ee-bd5c-4ada-8d6d-a480f38cf1f2")
    FragmentsMigrator getFragmentMigrator(final IEclipseContext eclipseContext, final GProject project, final boolean withConfirmation);

}
