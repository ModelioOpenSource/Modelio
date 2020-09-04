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

package org.modelio.app.project.ui.views.infos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.app.project.ui.views.urls.PropertiesUrlAdapter;
import org.modelio.app.project.ui.views.urls.UrlEntry;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.gproject.GProject;

@objid ("70c2a655-56a8-4200-aa54-af4a65a1490f")
class ProjectAdapter {
    @objid ("26e179dd-26dc-4bdf-8581-348ec30c73cb")
    private PropertiesUrlAdapter propertiesUrlAdapter;

    @objid ("8ab915f2-78d9-474e-a878-c9b86d6cede8")
    private final ProjectDescriptor projectDescriptor;

    @objid ("07bcf207-9996-470a-b8f3-4992701c52b0")
    public ProjectAdapter(ProjectDescriptor projectDescriptor) {
        this.projectDescriptor = projectDescriptor;
        
        this.propertiesUrlAdapter = new PropertiesUrlAdapter(projectDescriptor.getProperties());
    }

    @objid ("97aeecbd-8d55-408e-93b4-2abba9274347")
    public boolean isSameAs(GProject project) {
        return project != null && project.getName().equals(getName());
    }

    @objid ("4445d246-094c-4f61-8e5e-db2c2106ad1a")
    public boolean isSameAs(ProjectDescriptor aProjectDescriptor) {
        return aProjectDescriptor != null && aProjectDescriptor.getName().equals(getName());
    }

    @objid ("7e135cf6-28f0-4692-9c82-39dff8d3d2d6")
    public String getName() {
        if (this.projectDescriptor != null) {
            return this.projectDescriptor.getName();
        }
        return "";
    }

    @objid ("ba50c292-fc07-4d41-bdcd-7841218706f4")
    public List<UrlEntry> getUrls() {
        return this.propertiesUrlAdapter.getUrls();
    }

    @objid ("fa0b1568-51f7-4bf4-b4d4-7126243f77ce")
    public ProjectDescriptor getProjectDescriptor() {
        return this.projectDescriptor;
    }

    /**
     * @return the project properties.
     */
    @objid ("00c649de-18f5-4a74-91a7-6082ff7a6280")
    public GProperties getProperties() {
        if (this.projectDescriptor != null) {
            return this.projectDescriptor.getProperties();
        }
        return new GProperties();
    }

    @objid ("db440e3d-86f3-40e5-9ce2-fa8b4ce1f852")
    public ProjectFileStructure getProjectFileStructure() {
        if (this.projectDescriptor != null) {
            return this.projectDescriptor.getProjectFileStructure();
        }
        return null;
    }

    @objid ("6e507b34-4bb2-432d-b973-86009d304bab")
    public Object[] getFragments() {
        if (this.projectDescriptor != null) {
            return this.projectDescriptor.getFragments().toArray();
        }
        return null;
    }

    @objid ("15ab036e-0264-4a61-9b26-a8bb3dc40ca2")
    public Object[] getModules() {
        if (this.projectDescriptor != null) {
            return this.projectDescriptor.getModules().toArray();
        }
        return null;
    }

    @objid ("cfe30a51-4434-404e-a891-8e57a9a7abb6")
    public String getStoragePathString() {
        if (this.projectDescriptor != null) {
            return this.projectDescriptor.getProjectFileStructure().getProjectPath().toString();
        }
        return "";
    }

    @objid ("3081909f-6836-4674-8f29-0be8f048bc97")
    public String getStorageLastModificationTimeString() {
        if (this.projectDescriptor != null) {
            try {
                FileTime lastLastModifiedTime = Files.getLastModifiedTime(this.projectDescriptor.getProjectFileStructure().getProjectConfFile());
                Date date = new Date(lastLastModifiedTime.toMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat(AppProjectUiExt.I18N.getString("ProjectInfoHtmlPage.lastLastModifiedTimeFormat"));
                return dateFormat.format(date);
            } catch (IOException e) {
                AppProjectUi.LOG.error(e);
            }
        }
        return "";
    }

    /**
     * Get the work models fragments
     * FragmentType: EXML_SVN, EXML
     * @return
     */
    @objid ("ece56476-ee22-4f86-bf85-c27ffd265572")
    public List<FragmentDescriptor> getWorkModelsFragments() {
        List<FragmentDescriptor> workModelsFragments = new ArrayList<>();
        for (Object element : getFragments()) {
            if (element != null && element instanceof FragmentDescriptor) {
                switch (((FragmentDescriptor) element).getType()) {
                case EXML_SVN:
                case EXML:
                    workModelsFragments.add((FragmentDescriptor) element);
                    break;
                default:
                    break;
                }
            }
        }
        return workModelsFragments;
    }

    /**
     * Get the libraries fragments
     * FragmentType: EXML_URL, RAMC
     * @return
     */
    @objid ("bd420ad8-0d47-4f8d-bfaf-acad8149ca51")
    public List<FragmentDescriptor> getLibrariesFragments() {
        List<FragmentDescriptor> librariesFragments = new ArrayList<>();
        for (Object element : getFragments()) {
            if (element != null && element instanceof FragmentDescriptor) {
                switch (((FragmentDescriptor) element).getType()) {
                case EXML_URL:
                case RAMC:
                    if (!((FragmentDescriptor) element).getId().equals("PredefinedTypes")) {
                        librariesFragments.add((FragmentDescriptor) element);
                    }
                    break;
                default:
                    break;
                }
            }
        }
        return librariesFragments;
    }

}
