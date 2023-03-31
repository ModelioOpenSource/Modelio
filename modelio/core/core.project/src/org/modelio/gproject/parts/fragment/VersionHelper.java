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
package org.modelio.gproject.parts.fragment;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.parts.GPartFactory;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.version.ModelioVersionGuesser;

/**
 * Metamodel versions conveniences.
 * 
 * @author cmarin
 */
@objid ("3b8ea3f2-bc2b-4a6d-8209-ca6e136e4fb5")
public class VersionHelper {
    /**
     * Convert an old metamodel version to the new format
     * @param mmVersion the old metamodel version
     * @return the metamodel version with the new format.
     */
    @objid ("6ba51d3e-e825-4f9c-a60c-eed3c95461cb")
    public static Version convert(int mmVersion) {
        return new Version(1, 0, mmVersion);
    }

    /**
     * Convert a potential pre Modelio 3.4 metamodel descriptor.
     * @param input the source descriptor
     * @return another converted one.
     */
    @objid ("d1feb0ff-3fca-4f51-9f38-0d054cf039ae")
    public static MetamodelVersionDescriptor convert(MetamodelVersionDescriptor input) {
        MetamodelVersionDescriptor out = new MetamodelVersionDescriptor();
        
        for (VersionedItem<?> item : input) {
            // Pre Modelio 3.4 compatibility
            if (item.getName().equals("Modelio")) {
                VersionedItem<?> n = new VersionedItem<>(
                        StandardMetamodel.NAME,
                        convert(item.getVersion().getMajorVersion()),
                        item.getObject());
                out.addDescriptor(n);
            } else if (item.getVersion().isOlderThan(new Version(0, 0, 10000)) && item.getVersion().isNewerBuildOf(new Version(0, 0, 1))) {
                // It is a 0.0.9xxx version, should be very rare
                out.addDescriptor(new VersionedItem<>(
                        StandardMetamodel.NAME,
                        convert(item.getVersion().getBuildVersion()),
                        item.getObject()));
            } else {
                out.addDescriptor(item);
            }
        }
        return out;
    }

    /**
     * Build a {@link MetamodelVersionDescriptor} from an old metamodel version.
     * @param oldMmVersion an old Modelio metamodel version.
     * @return the matching VersionDescriptors
     */
    @objid ("4a6fc675-fbc4-46e8-a679-a65b9c3e00c6")
    public static MetamodelVersionDescriptor getDescriptors(int oldMmVersion) {
        VersionedItem<Void> it = new VersionedItem<>(StandardMetamodel.NAME, convert(oldMmVersion));
        return new MetamodelVersionDescriptor(it);
    }

    /**
     * Build a {@link MetamodelVersionDescriptor} from a metamodel .
     * @param mm a metamodel
     * @return its descriptor
     */
    @objid ("747b6fce-bc43-4797-918b-46db5b00b4d9")
    public static MetamodelVersionDescriptor getDescriptors(MMetamodel mm) {
        MetamodelVersionDescriptor desc = new MetamodelVersionDescriptor();
        
        for (MMetamodelFragment mmFragment : mm.getFragments()) {
            VersionedItem<Void> v = new VersionedItem<>(mmFragment.getName(), mmFragment.getVersion());
            desc.addDescriptor(v);
        }
        return desc;
    }

    @objid ("6d743e8f-b92b-48f6-9337-82845fb9a1a2")
    private  VersionHelper() {
        // no instance
    }

    /**
     * Guess the Modelio version of a loaded project from the loaded (but not opened) project.
     * The guess consists in analysing the parts to determine the Modelio version.
     * @param projDesc the project descriptor
     * @return the guessed Modelio version
     */
    @objid ("e8b249d4-f96d-4a11-b294-76811dd58279")
    public static Version guessModelioVersion(IGProject loadedProject) {
        Version lastWorkVersion = null;
        Version lastRamcVersion = null;
        try {
            for (GProjectPartDescriptor d : loadedProject.getDescriptor().getPartDescriptors()) {
                GProjectPartType type = d.getType();
                IGModelFragment f;
                MetamodelVersionDescriptor mmv;
                Version v;
                switch (type) {
                case EXMLFRAGMENT:
                case SVNFRAGMENT:
                case HTTPFRAGMENT:
                    // For model frgaments the trick consists in examining the metamodel version which is relevant of the Modelio version for old projects.
                    f = (IGModelFragment) GPartFactory.getInstance().instantiate(d);
                    f.install(loadedProject, null);
                    mmv = f.getRequiredMetamodelDescriptor();
                    v = mmv.getVersion(StandardMetamodel.NAME);
                    // Default value for ExmlFragment.getRequiredMetamodelDescriptor() if no version file
                    // assume Modelio 3.1 (9020) metamodel version.
                    if (v != null && !v.equals(VersionHelper.convert(9020))) {
                        if (lastWorkVersion == null || v.isNewerThan(lastWorkVersion)) {
                            lastWorkVersion = v;
                        }
                    }
                    break;
                case RAMC:
                    f = (IGModelFragment) GPartFactory.getInstance().instantiate(d);
                    f.install(loadedProject, null);
                    mmv = f.getRequiredMetamodelDescriptor();
                    v = mmv.getVersion(StandardMetamodel.NAME);
                    if (v != null) {
                        if (lastRamcVersion == null || v.isNewerThan(lastRamcVersion)) {
                            lastRamcVersion = v;
                        }
                    }
                    break;
                case MODULE:
                case FEATURE:
                case RESOURCE:
                default:
                    break;
                }
            }
        
            if (lastRamcVersion != null) {
                return ModelioVersionGuesser.guessFromStandardMmVersion(lastRamcVersion);
            } else if (lastWorkVersion != null) {
                return ModelioVersionGuesser.guessFromStandardMmVersion(lastWorkVersion);
            } else {
                return null;
            }
        
        } catch (IOException|GPartException e) {
            // A part version could not be read => the overall guess is considered as failed.
            return null;
        }
        
    }

}
