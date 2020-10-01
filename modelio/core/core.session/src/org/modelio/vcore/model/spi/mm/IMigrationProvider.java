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

package org.modelio.vcore.model.spi.mm;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Participant in a MOF migration.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("207f421f-3b15-49cf-9246-2a10b8e07c77")
public interface IMigrationProvider {
    /**
     * Tells how this migrator can transform the given metamodel.
     * <p>
     * The returned {@link IMofRepositoryMigrator#getTargetMetamodel()} should return a copy of the given {@link MetamodelVersionDescriptor}
     * with the modifications it can make, by replacing old versions by new ones, replacing
     * some by other ...
     * <p>
     * If no migrator is applicable it must return null.
     * 
     * @param fromMetamodel the given metamodel
     * @param targetMetamodel the target metamodel
     * @return the metamodel after this migrator would run.
     */
    @objid ("4db4859f-9830-4386-8f8e-ef3ce6060505")
    IMofRepositoryMigrator getMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel);

}
