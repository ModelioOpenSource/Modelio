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

package org.modelio.gproject.fragment;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.fragment.exml.ExmlFragment;
import org.modelio.gproject.fragment.ramcfile.RamcFileFragmentFactory;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.version.ModelioVersionGuesser;

/**
 * Metamodel versions conveniences.
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
        return new Version(1,0,mmVersion);
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
            } else if (item.getVersion().isOlderThan(new Version(0,0,10000)) && item.getVersion().isNewerBuildOf(new Version(0,0,1))){
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

    /**
     * Guess the Modelio version of a loaded project from the loaded (but not opened) project and its descriptor.
     * @param projDesc the project descriptor
     * @param loadedProject the project in loaded state. Should not be opened.
     * @return the guessed Modelio version
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("e8b249d4-f96d-4a11-b294-76811dd58279")
    public static Version guessModelioVersion(ProjectDescriptor projDesc, GProject loadedProject) throws IOException {
        Version lastWorkVersion = null;
        Version lastRamcVersion = null;
        
        for (FragmentDescriptor f : projDesc.getFragments()) {
            try {
                FragmentType fragType = f.getType();
                if (fragType == FragmentType.EXML || fragType==FragmentType.EXML_SVN) {
                    // work fragment, look for metamodel
                    ExmlFragment fr = new ExmlFragment(f.getId(), f.getScope(), f.getProperties(), new GAuthConf(f.getAuthDescriptor().getData(), f.getAuthDescriptor().getScope()));
                    fr.setProject(loadedProject);
                    MetamodelVersionDescriptor mmv = fr.getRequiredMetamodelDescriptor();
                    Version v = mmv.getVersion(StandardMetamodel.NAME);
        
                    // Default value for ExmlFragment.getRequiredMetamodelDescriptor() if no version file
                    // assume Modelio 3.1 (9020) metamodel version.
                    if (v != null && ! v.equals(VersionHelper.convert(9020))) {
                        if (lastWorkVersion == null || v.isNewerThan(lastWorkVersion)) {
                            lastWorkVersion = v;
                        }
                    }
                } else if (fragType==FragmentType.RAMC) {
                    IProjectFragment ramcFragment = RamcFileFragmentFactory.getInstance().instantiate(f);
                    ramcFragment.setProject(loadedProject);
                    MetamodelVersionDescriptor mmv = ramcFragment.getRequiredMetamodelDescriptor();
                    Version v = mmv.getVersion(StandardMetamodel.NAME);
                    if (v != null) {
                        if (lastRamcVersion == null || v.isNewerThan(lastRamcVersion)) {
                            lastRamcVersion = v;
                        }
                    }
                }
            } catch (@SuppressWarnings ("unused") IOException e) {
                // ignore
            }
        }
        
        if (lastRamcVersion != null) {
            return ModelioVersionGuesser.guessFromStandardMmVersion(lastRamcVersion);
        } else if (lastWorkVersion != null) {
            return ModelioVersionGuesser.guessFromStandardMmVersion(lastWorkVersion);
        } else {
            return null;
        }
    }

    @objid ("6d743e8f-b92b-48f6-9337-82845fb9a1a2")
    private VersionHelper() {
        // no instance
    }

}
