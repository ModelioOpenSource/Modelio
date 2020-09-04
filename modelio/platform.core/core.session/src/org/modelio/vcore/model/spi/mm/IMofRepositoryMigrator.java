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

package org.modelio.vcore.model.spi.mm;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.mapi.services.IMetamodelDependentService;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;

/**
 * Participant in a repository MOF migration.
 * <p>
 * Considerations for the implementers:
 * <p>
 * Projects are usually versioned with SVN.
 * Versioned projects are migrated in 2 steps:<ul>
 * <li> The first user migrating it triggers a server repository migration.
 * <li> The other users migrate only the elements they have locally modified.
 * </ul>
 * Some of the model content may then be locked and the caller may decide
 * they are not modifiable.
 * These elements should then be ignored by the implementation, the caller will then
 * take in charge all consequences.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("c2846e77-0652-4623-80d5-46124f11514f")
public interface IMofRepositoryMigrator extends IMetamodelDependentService {
    /**
     * Modify the metamodel so that it can read the {@link #getSourceMetamodel()} repository.
     * <p>
     * The implementation should make an union of the passed target metamodel and the source one.
     * @param metamodel the metamodel at the {@link #getTargetMetamodel() target} state.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on fatal failure preventing migration
     */
    @objid ("736b29c5-1e21-49e7-9afa-14294a2bc7d7")
    void prepareMetamodel(MofMetamodel metamodel) throws MofMigrationException;

    /**
     * Migrates the given repository using the given session.
     * @param monitor a progress monitor
     * @param migrationSession the migration session
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on fatal failure preventing migration
     */
    @objid ("0e7510a8-d569-4688-9e17-894b9174718d")
    void run(IModelioProgress monitor, IMofSession migrationSession) throws MofMigrationException;

    /**
     * @return the metamodel from which this migration can run.
     */
    @objid ("88adc1b3-9f41-472d-8942-d546e685e93c")
    MetamodelVersionDescriptor getSourceMetamodel();

    /**
     * @return the metamodel to which the implementation will migrate the model.
     */
    @objid ("f08525aa-a69a-4bee-906a-801ce637ca64")
    MetamodelVersionDescriptor getTargetMetamodel();

    /**
     * Get a resume of metamodel changes between {@link #getSourceMetamodel()} and {@link #getTargetMetamodel()}.
     * <p>
     * This resume is important because it is needed by some repository implementations
     * to maintain their organization.
     * @return a resume of metamodel changes between {@link #getSourceMetamodel()} and {@link #getTargetMetamodel()}.
     */
    @objid ("3def37ca-8fcf-400a-bde1-495ce83e66fa")
    MetamodelChangeDescriptor getMetamodelChanges();

    /**
     * Allows the migrator to modify the metamodel descriptor that will be written after the migration is complete.
     * <p>
     * Each migrator will be called with this method in order.
     * @param desc the final metamodel descriptor.
     * @param reporter a place to log things.
     * @throws org.modelio.vcore.model.spi.mm.MofMigrationException on failure.
     */
    @objid ("4f63c04b-0a55-48ad-a5e0-882434bd0a01")
    default void completeFinalMetamodelDescriptor(MetamodelDescriptor desc, IMigrationReporter reporter) throws MofMigrationException {
        // Do nothing by default.
    }

    /**
     * Tells whether this migrator has to modify the repository.
     * <p>
     * Most implementations do modify the model. Only {@link NoopMofRepositoryMigrator} is expected to return false.
     * The caller may do some optimizations if no migrators of the chain modify the model.
     * @return <i>true</i> if this migrator modifies the model else <i>false</i>.
     */
    @objid ("62476483-a603-4f69-a03b-9bcad09fe6de")
    default boolean doesModifyRepository() {
        return true;
    }

}
