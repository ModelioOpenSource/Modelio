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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * {@link IMofRepositoryMigrator} that does nothing.
 * <p>
 * Changes:<ul>
 * <li>>= 3.6.0 (12/09/2016): initial version
 * <li>3.7.1 : this class now inherits from {@link AbstractMofRepositoryMigrator} and is final.
 * </ul>
 * @author cma
 */
@objid ("e24b1145-b2fd-430b-b430-c025473fb185")
public final class NoopMofRepositoryMigrator extends AbstractMofRepositoryMigrator {
    /**
     * @param fromMetamodel the source metamodel
     * @param targetMetamodel the target metamodel
     */
    @objid ("37aeaa2f-f398-414c-9a12-99bfa57b2c12")
    public NoopMofRepositoryMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        super(fromMetamodel, targetMetamodel);
    }

    @objid ("193d371b-e6be-461c-a68f-e1ee9342839c")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[from "+getSourceMetamodel()+" to "+getTargetMetamodel()+"]";
    }

    /**
     * This migrator does not modify the repository.
     */
    @objid ("d313a3a4-fd28-44bf-adb1-5674275266af")
    @Override
    public boolean doesModifyRepository() {
        return false;
    }

}
