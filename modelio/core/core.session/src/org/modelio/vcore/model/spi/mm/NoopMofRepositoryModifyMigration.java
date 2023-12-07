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

@objid ("421b2c6f-a70a-4866-8e83-030142dae56b")
public class NoopMofRepositoryModifyMigration extends AbstractMofRepositoryMigrator {
    /**
     * @param fromMetamodel the source metamodel
     * @param targetMetamodel the target metamodel
     */
    @objid ("888d8932-5cd9-4264-9dba-00082ac64f6c")
    public  NoopMofRepositoryModifyMigration(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        super(fromMetamodel, targetMetamodel);
    }

    @objid ("d26ecc54-e87b-4a02-b983-e2f334f8cde4")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[from "+getSourceMetamodel()+" to "+getTargetMetamodel()+"]";
    }

    /**
     * This migrator does  modify the repository.
     */
    @objid ("53c98bbd-a57f-4dd6-a9c6-3bec96489880")
    @Override
    public boolean doesModifyRepository() {
        return true;
    }

}
