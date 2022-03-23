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
package org.modelio.platform.project.creation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;

/**
 * {@link ProjectCreationDialog} dialog data model.
 */
@objid ("0044a3e0-cc35-1ff2-a7f4-001ec947cd2a")
public class ProjectCreationDataModel implements IProjectCreationData {
    /**
     * The name of the project to create. This name must be usable as a directory name. The project path will be
     * workspace/projectName unless a redirected projectPath is provided.
     */
    @objid ("004791b8-cc35-1ff2-a7f4-001ec947cd2a")
    private String projectName = "";

    /**
     * The description of the project
     */
    @objid ("004792bc-cc35-1ff2-a7f4-001ec947cd2a")
    private String projectDescription = "";

    /**
     * The project creation author
     */
    @objid ("004793b6-cc35-1ff2-a7f4-001ec947cd2a")
    private String projectAuthor = "";

    /**
     * The project creation date
     */
    @objid ("004796ae-cc35-1ff2-a7f4-001ec947cd2a")
    private Date projectDate = null;

    /**
     * If true, open the project configurator after project creation and opening.
     */
    @objid ("796eedc9-3c7b-4c75-a46e-6711086b5dc7")
    public boolean openConfigurator;

    /**
     * The modules to be installed in the created project
     */
    @objid ("00487218-cc35-1ff2-a7f4-001ec947cd2a")
    private final List<IModuleHandle> moduleHandles = new ArrayList<>();

    /**
     * This directory will be used to hold the project data instead of the default value workspace/projectName UNSUPPORTED (on Nov
     * 27th 2012)
     */
    @objid ("0044bd1c-cc35-1ff2-a7f4-001ec947cd2a")
    private Path projectRedirectionPath = null;

    /**
     * The path of the workspace in which the project has to be created
     */
    @objid ("0044d2c0-cc35-1ff2-a7f4-001ec947cd2a")
    public Path workspace;

    @objid ("0072d486-7310-10b7-9941-001ec947cd2a")
    private Path template;

    /**
     * Constructor.
     */
    @objid ("0046e11e-cc35-1ff2-a7f4-001ec947cd2a")
    public  ProjectCreationDataModel(final Path workspace) {
        this.workspace = workspace;
    }

    @objid ("0047b724-cc35-1ff2-a7f4-001ec947cd2a")
    public List<IModuleHandle> getModuleHandles() {
        return this.moduleHandles;
    }

    @objid ("0048a396-cc35-1ff2-a7f4-001ec947cd2a")
    public String getProjectAuthor() {
        return this.projectAuthor;
    }

    @objid ("0048a42c-cc35-1ff2-a7f4-001ec947cd2a")
    public Date getProjectDate() {
        return this.projectDate;
    }

    @objid ("0045916a-cc35-1ff2-a7f4-001ec947cd2a")
    public String getProjectDescription() {
        return this.projectDescription;
    }

    @objid ("0045920a-cc35-1ff2-a7f4-001ec947cd2a")
    public Path getProjectRedirectionPath() {
        return this.projectRedirectionPath;
    }

    @objid ("0045a89e-cc35-1ff2-a7f4-001ec947cd2a")
    public String getProjectName() {
        return this.projectName;
    }

    @objid ("0045a1e6-cc35-1ff2-a7f4-001ec947cd2a")
    public void setProjectAuthor(final String projectAuthor) {
        this.projectAuthor = projectAuthor;
    }

    @objid ("0045a286-cc35-1ff2-a7f4-001ec947cd2a")
    public void setProjectDate(final Date projectDate) {
        this.projectDate = projectDate;
    }

    @objid ("00484ec8-cc35-1ff2-a7f4-001ec947cd2a")
    public void setProjectDescription(final String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @objid ("00484f5e-cc35-1ff2-a7f4-001ec947cd2a")
    public void setProjectRedirectionPath(final Path projectRedirectionPath) {
        this.projectRedirectionPath = projectRedirectionPath;
    }

    @objid ("0046e448-cc35-1ff2-a7f4-001ec947cd2a")
    public void setProjectName(final String projectName) {
        this.projectName = projectName.trim();
    }

    @objid ("0073f758-7310-10b7-9941-001ec947cd2a")
    public void setTemplate(Path template) {
        this.template = template;
    }

    @objid ("00740cf2-7310-10b7-9941-001ec947cd2a")
    public Path getTemplate() {
        return this.template;
    }

}
