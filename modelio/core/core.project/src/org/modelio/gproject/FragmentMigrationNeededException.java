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
package org.modelio.gproject;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.model.spi.mm.MmVersionComparator;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Indicates that a fragment needs to be migrated because the metamodel is outdated.
 * <p>
 * Throw when {@link IGModelFragment#mount(IGProject, IModelioProgress) mounting} fragment and set as {@link IGModelFragment#getDownError() fragment down cause}.
 */
@objid ("f33dc38b-c7d4-421a-98b5-0d4c4d4849f1")
public class FragmentMigrationNeededException extends Exception {
    @objid ("5715f25b-2201-4fef-985f-ab8ca2a925db")
    private String fragmentId;

    @objid ("056119ed-b724-4f34-91c7-7759a4d10f3e")
    private static final long serialVersionUID = 1L;

    @objid ("cdfc7aea-ec9f-44b4-83e3-47ca5e30bc16")
    private String detailMessage;

    @objid ("3f0d79e1-6e08-42b7-860f-c3ff906d22f5")
    private boolean remoteMigrationNeeded;

    @objid ("97cef184-03b6-4141-bc62-4c51fde68436")
    private MetamodelVersionDescriptor fragmentVersion;

    @objid ("1885cd02-c4d4-4cae-8330-1c22f99e2ae2")
    private MetamodelVersionDescriptor targetVersion;

    /**
     * Constructor that builds a default summary.
     * @param fragment the fragment that needs migration.
     * @param targetVersion the needed metamodel version
     */
    @objid ("e757dced-f50b-4622-9110-a78f3ceccb73")
    public  FragmentMigrationNeededException(IGModelFragment fragment, MetamodelVersionDescriptor targetVersion) {
        super(computeMessage(fragment, targetVersion));
        
        init(fragment, targetVersion);
        
    }

    /**
     * Constructor with a custom summary.
     * @param fragment the fragment that needs migration.
     * @param targetVersion the needed metamodel version
     * @param summary summary about the needed migration.
     */
    @objid ("5b35eb86-1a46-42be-a2a2-de90ced5e036")
    public  FragmentMigrationNeededException(IGModelFragment fragment, MetamodelVersionDescriptor targetVersion, String summary) {
        super(summary);
        init(fragment, targetVersion);
        
    }

    @objid ("c1db3301-480e-4fd9-ab7f-7a4dfb5e2acb")
    private void init(IGModelFragment fragment, MetamodelVersionDescriptor target) {
        this.targetVersion = target;
        this.fragmentId = fragment.getId();
        try {
            this.fragmentVersion = fragment.getRequiredMetamodelDescriptor();
        } catch (IOException e) {
            // Record the exception as a dummy version
            VersionedItem<?> d = new VersionedItem<Object>(FileUtils.getLocalizedMessage(e), null, e);
        
            this.fragmentVersion = new MetamodelVersionDescriptor(d);
        }
        
    }

    /**
     * Get the identifier of the fragment to migrate.
     * @return the fragment identifier.
     */
    @objid ("8b32a0cd-360e-47fa-8cd7-a8615d2e8108")
    public String getFragmentId() {
        return this.fragmentId;
    }

    /**
     * Get the metamodel version of the fragment to migrate.
     * @return the fragment metamodel version.
     */
    @objid ("82f680f3-5d9c-4a85-9b82-70c5167a6b61")
    public MetamodelVersionDescriptor getFragmentVersion() {
        return this.fragmentVersion;
    }

    /**
     * Get the metamodel version the fragment must be migrated to.
     * @return the target metamodel version.
     */
    @objid ("8022e424-f360-4941-908f-955b80da8bb4")
    public MetamodelVersionDescriptor getTargetVersion() {
        return this.targetVersion;
    }

    /**
     * Compute the exception message.
     * @param fragment the fragment to migrate
     * @param targetVersion the target metamodel versions
     * @return a message.
     */
    @objid ("e09a831c-ad17-4b7a-99c4-b61232140e2e")
    private static String computeMessage(IGModelFragment fragment, MetamodelVersionDescriptor targetVersion) {
        try {
            MetamodelVersionDescriptor fragmentVersion = fragment.getRequiredMetamodelDescriptor();
            MmVersionComparator comp = MmVersionComparator
                    .withSource(fragmentVersion)
                    .withTarget(targetVersion)
                    .withCommonRemoved();
        
            return CoreProject.I18N.getMessage(
                    "FragmentMigrationNeededException.message",
                    fragment.getId(),
                    comp.getSource(),
                    comp.getTarget());
        } catch (IOException e) {
            return FileUtils.getLocalizedMessage(e);
        }
        
    }

    /**
     * Tells for a remote/versioned fragment whether the remote repository needs to be migrated too.
     * @return true if the remote repository needs to be migrated, false if only the local model needs migration.
     * @since 3.7.01
     */
    @objid ("e69c8474-cf6a-4d26-8ff0-1d8a6163871c")
    public boolean isRemoteMigrationNeeded() {
        return this.remoteMigrationNeeded;
    }

    /**
     * Set the remote repository needs to be migrated too.
     * @return this instance to chain calls.
     * @since 3.7.01
     */
    @objid ("3081dc9c-277c-4a1c-a958-ce86cb5fbd51")
    public FragmentMigrationNeededException setRemoteMigrationNeeded() {
        this.remoteMigrationNeeded = true;
        return this;
    }

}
