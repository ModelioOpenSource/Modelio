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
 * Provides a {@link NoopMofRepositoryMigrator} if the target metamodel is build compatible
 * with the source metamodel. returns null in all other cases.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("002764bc-dc8e-4fae-9b11-b240fde6eb8d")
public class BuildCompatibleMigrationProvider implements IMofRepositoryMigratorProvider {
    /**
     * Returns a {@link NoopMofRepositoryMigrator} if the target metamodel is build compatible
     * with the source metamodel. returns null in all other cases.
     * @param fromMetamodel the given metamodel
     * @param targetMetamodel the target metamodel
     * @return the metamodel after this migrator would run.
     */
    @objid ("50663349-a981-4fbe-940e-b4a52d4f580e")
    @Override
    public IMofRepositoryMigrator getMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        if (targetMetamodel.isCompatibleWith(fromMetamodel, true)) {
            return new NoopMofRepositoryMigrator(fromMetamodel, targetMetamodel);
        } else {
            return null;
        }
        
    }

}
