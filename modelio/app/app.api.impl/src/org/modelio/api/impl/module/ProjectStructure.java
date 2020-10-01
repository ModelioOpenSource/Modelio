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

package org.modelio.api.impl.module;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.project.IFragmentStructure;
import org.modelio.api.module.context.project.IModuleStructure;
import org.modelio.api.module.context.project.IProjectStructure;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.vbasic.version.Version;

@objid ("bae92554-643b-447e-a194-1dc208fa24b9")
public class ProjectStructure implements IProjectStructure {
    @objid ("74ee0fdd-b97d-4d74-9b88-7026b02d6ad0")
    private String name = "";

    @objid ("8fdcb26c-6f23-42f3-912b-5a48185c2759")
    private String type = "";

    @objid ("8ee85bf7-980b-45ce-a014-3cefa0f31243")
    private String remoteLocation = "";

    @objid ("e426459b-6d99-4a51-b218-a262c3404407")
    private ProjectFileStructure pfs;

    @objid ("89bde735-9929-4305-b8cb-879ddb752c92")
    private List<IModuleStructure> modules = new ArrayList<>();

    @objid ("370e186c-d5ef-4a76-8ca4-eb496c3fdf8f")
    private List<IFragmentStructure> fragments = new ArrayList<>();

    @objid ("8320d3e3-eab6-4349-ba2a-36cf6b0b272e")
    ProjectStructure(GProject project) {
        if (project != null) {
            this.name = project.getName();
            this.type = project.getType().toString();
            this.pfs = project.getProjectFileStructure();
            this.remoteLocation = project.getRemoteLocation();
        
            // Modules
            for (GModule gm : project.getModules()) {
                this.modules.add(new ModuleDescriptorImpl(
                        gm.getName(),
                        gm.getVersion(),
                        String.valueOf(gm.getOriginalArchiveUri())));
            }
        
            // Fragments
            for (IProjectFragment f : project.getFragments()) {
                this.fragments.add(new FragmentStructure(f));
            }
        }
    }

    @objid ("55033493-bb26-4f4e-a9b7-ddd3d1acc5a1")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("2bf221f3-e9e9-4c97-9b20-b362f43d6c69")
    @Override
    public String getType() {
        return this.type;
    }

    @objid ("bf5e56de-d3d0-48f5-8c54-99a8e1e99c93")
    @Override
    public Path getPath() {
        return this.pfs.getProjectPath();
    }

    @objid ("539ae479-1906-4df4-a3b9-6dd787d31718")
    @Override
    public String getRemoteLocation() {
        return this.remoteLocation;
    }

    @objid ("34fd692d-0816-4035-83bd-6b85a5590b2b")
    @Override
    public List<IFragmentStructure> getFragments() {
        return this.fragments;
    }

    @objid ("d8fc28af-69e2-4756-86ec-945d42a6624b")
    @Override
    public List<IModuleStructure> getModules() {
        return this.modules;
    }

    @objid ("736cb74d-6ec2-49c5-9852-34b40bbcaa4c")
    private static class ModuleDescriptorImpl implements IModuleStructure {
        @objid ("917002a5-b017-48df-b154-0e22ea4a8266")
        private final String name;

        @objid ("c3598f52-adab-48bc-b815-000f4388392b")
        private final String archive;

        @objid ("7c7dfe91-0ba8-47ca-b4cf-e033b4d70836")
        private final Version version;

        @objid ("c5619bd4-31c6-42f8-95ee-023eecbe4042")
        public ModuleDescriptorImpl(String name, Version version, String archive) {
            this.name = name;
            this.version = version;
            this.archive = archive;
        }

        @objid ("4c8d1268-413a-4a1a-a1b2-a0a927f4e2a8")
        @Override
        public String getName() {
            return this.name;
        }

        @objid ("bff7840b-2d65-47e9-8095-66c9def44dd7")
        @Override
        public Version getVersion() {
            return this.version;
        }

        @objid ("ada8d9f1-292b-4524-8ed7-4cfe91547895")
        @Override
        public String getArchive() {
            return this.archive;
        }

    }

}
