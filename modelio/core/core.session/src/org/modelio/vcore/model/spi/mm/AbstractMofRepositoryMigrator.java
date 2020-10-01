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
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;

/**
 * Default implementation of {@link IMofRepositoryMigrator} that does nothing.
 * @author cma
 */
@objid ("34bad46f-90f1-460f-bbb6-1c71e877b2ff")
public abstract class AbstractMofRepositoryMigrator implements IMofRepositoryMigrator {
    @objid ("93ecc77e-9b66-417d-9dd6-03b3dffae92f")
    private MetamodelVersionDescriptor fromMetamodel;

    @objid ("6f46d606-7f75-487f-9319-1149374e1e70")
    private MetamodelVersionDescriptor targetMetamodel;

    @objid ("39616f93-325a-4f3f-a61e-cf8b23e271e8")
    private MetamodelChangeDescriptor metamodelChangeDescriptor;

    /**
     * @param fromMetamodel the source metamodel
     * @param targetMetamodel the target metamodel
     */
    @objid ("dab9b2d8-6853-4bbb-8621-61e32d111ac2")
    public AbstractMofRepositoryMigrator(MetamodelVersionDescriptor fromMetamodel, MetamodelVersionDescriptor targetMetamodel) {
        this.fromMetamodel = fromMetamodel;
        this.targetMetamodel = targetMetamodel;
        this.metamodelChangeDescriptor = new MetamodelChangeDescriptor();
    }

    /**
     * @return a resume of metamodel changes between {@link #getSourceMetamodel()} and {@link #getTargetMetamodel()}.
     */
    @objid ("99bb457b-cbea-4bbe-b2e9-e24a96246e20")
    @Override
    public MetamodelChangeDescriptor getMetamodelChanges() {
        return this.metamodelChangeDescriptor;
    }

    /**
     * @return the metamodel from which this migration can run.
     */
    @objid ("b5cf5ec8-98c5-4e4d-b73f-399139ff777b")
    @Override
    public MetamodelVersionDescriptor getSourceMetamodel() {
        return this.fromMetamodel;
    }

    /**
     * @return the metamodel to which the implementation will migrate the model.
     */
    @objid ("8be7763c-21ea-4fe6-8da0-f8d6e181336d")
    @Override
    public MetamodelVersionDescriptor getTargetMetamodel() {
        return this.targetMetamodel;
    }

    /**
     * Modify the metamodel so that it can read the {@link #getSourceMetamodel()} repository.
     * 
     * @param metamodel the metamodel at the {@link #getTargetMetamodel() target} state.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on fatal failure preventing migration
     */
    @objid ("1ace3f1f-715c-4ba6-8d40-b818959c8e21")
    @Override
    public void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException {
        // nothing
    }

    /**
     * Migrates the given repository using the given session.
     * 
     * @param monitor a progress monitor
     * @param session the migration session
     */
    @objid ("544f2651-6490-43c5-973a-e77c2daf7cb4")
    @Override
    public void run(IModelioProgress monitor, IMofSession session) throws MofMigrationException {
        // nothing
    }

    /**
     * Set the metamodel changes descriptor.
     * 
     * @param changes the metamodel changes descriptor.
     * @return this instance
     */
    @objid ("51fb6e5e-e0dc-4081-832c-bb26c442b750")
    public IMofRepositoryMigrator setMetamodelChanges(MetamodelChangeDescriptor changes) {
        this.metamodelChangeDescriptor = changes;
        return this;
    }

    @objid ("a0f58c5b-e84e-46a0-a291-8b5972bf4438")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[from "+getSourceMetamodel()+" to "+getTargetMetamodel()+"]";
    }

}
