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

package org.modelio.gproject.data.project;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.todo.TodoActionDescriptor;
import org.modelio.gproject.data.project.todo.TodoDescriptor;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.version.Version;

/**
 * Descriptor of a GProject, this class holds an in-memory representation of a "project.conf" file.
 * <p>
 * Descriptors can be created either by reading from a file with {@link ProjectDescriptorReader#read(Path, DefinitionScope)},
 * or by creating an empty one with {@link #createEmpty(String, Path, Version)}.
 */
@objid ("50c8e5d0-985a-11e1-ac83-001ec947ccaf")
public final class ProjectDescriptor implements Serializable {
    /**
     * Project space directory structure version.
     * <p>
     * <h3>History:</h3>
     * <ul>
     * <li>0 : initial version, this attribute didn't exist
     * <li>1 : 21/03/2017 : Project space reorganized for Modelio 3.7
     * </ul>
     */
    @objid ("d557faf1-df87-41aa-a5dd-d7cbd237c3d4")
    public static final long currentProjectSpaceVersion = 1L;

    /**
     * see {@link #serialVersionUID}.
     */
    @objid ("91b25210-e892-466a-b045-9ec7a258f3b8")
    private long formatVersion = ProjectDescriptor.serialVersionUID;

    @objid ("eed9bf98-9a71-11e1-ac83-001ec947ccaf")
    private String name;

    /**
     * Project space directory structure version.
     * <p>
     * See {@link #currentProjectSpaceVersion} for history.
     */
    @objid ("4049222b-9927-480a-a116-40040cf8f0bb")
    private long projectSpaceVersion = ProjectDescriptor.currentProjectSpaceVersion;

    /**
     * Remote path of the project for shared projects.
     */
    @objid ("40dec621-0c6e-11e2-bed6-001ec947ccaf")
    private String remoteLocation;

    /**
     * Project descriptor format version.
     * <p>
     * History:
     * <ul>
     * <li>1 : initial version
     * <li>2 : fragments and module paths are now URI relative to the project or absolute.
     * <li>3 : 15/10/2015 - Modelio 3.4.1 : added modelioVersion attribute.<br>
     * 20/10/2015 - added formatVersion attribute to write ascendent compatible descriptors.
     * <li>4 : 21/03/2017 - Modelio 3.7 : added synchronized resources, reorganized project space directory structure.
     * <li>5 : 1/03/2019 - Modelio 3.8.1 : auth properties may be multiline and serialized in a TEXT node.
     * </ul>
     */
    @objid ("eed75d53-9a71-11e1-ac83-001ec947ccaf")
    public static final long serialVersionUID = 5L;

    /**
     * Project type : local, svn, http...
     */
    @objid ("40dec619-0c6e-11e2-bed6-001ec947ccaf")
    private String type;

    @objid ("e285be30-4cd0-40a3-b8dc-09d11f13e19e")
    private AuthDescriptor auth;

    @objid ("b63bb0bf-99bb-11e1-ac83-001ec947ccaf")
    private final List<FragmentDescriptor> fragments = new ArrayList<>();

    /**
     * Lock informations if the project is locked.
     */
    @objid ("e3f2e4eb-b9f5-48ad-b8a7-8d2edf2b98ee")
    private ILockInfo lockInfo;

    /**
     * The version of Modelio used to write this project.
     */
    @objid ("5a0b8a4b-f025-4b1d-81b6-a47c63cce52b")
    private Version modelioVersion;

    @objid ("aa848eec-ec75-11e1-912e-001ec947ccaf")
    private final List<ModuleDescriptor> modules = new ArrayList<>();

    /**
     * The physical path of the project
     * Note : the project space can be either local or delegated to another directory.
     */
    @objid ("001f69c2-34d4-1fc7-b42e-001ec947cd2a")
    private ProjectFileStructure pfs;

    @objid ("f478e90d-aa5a-11e1-8392-001ec947ccaf")
    private GProperties properties = new GProperties();

    @objid ("2ad17274-862d-46ba-8b4a-e93641982c7f")
    private final List<ResourceDescriptor> sharedResources = new ArrayList<>();

    @objid ("1c63426b-464a-43d5-b603-dbc1550981ba")
    private TodoDescriptor todo = new TodoDescriptor();

    /**
     * Instantiate an empty project descriptor.
     */
    @objid ("001113fe-92ee-1060-84ef-001ec947cd2a")
    public ProjectDescriptor() {
    }

    /**
     * Copy constructor.
     * 
     * @param orig the descriptor to copy.
     */
    @objid ("7db50d7f-a86a-4334-927c-7a2babc38e8e")
    public ProjectDescriptor(ProjectDescriptor orig) {
        for (FragmentDescriptor of : orig.getFragments()) {
            getFragments().add(new FragmentDescriptor(of));
        }
        
        for (ModuleDescriptor md : orig.getModules()) {
            getModules().add(new ModuleDescriptor(md));
        }
        
        getTodo().getActions().addAll(orig.getTodo().getActions());
        
        setAuthDescriptor(new AuthDescriptor(orig.getAuthDescriptor().getData(), orig.getAuthDescriptor().getScope()));
        setFormatVersion(orig.getFormatVersion());
        setLockInfo(orig.getLockInfo());
        setModelioVersion(orig.getModelioVersion());
        setName(orig.getName());
        setProjectSpaceVersion(orig.getProjectSpaceVersion());
        setProperties(new GProperties(orig.getProperties()));
        setRemoteLocation(orig.getRemoteLocation());
        setType(orig.getType());
        
        ProjectFileStructure projectFileStructure = orig.getProjectFileStructure();
        if (projectFileStructure != null) {
            setPath(projectFileStructure.getProjectPath());
        }
    }

    /**
     * Remove incomplete module and fragment descriptors.
     * 
     * @return A report of all incomplete descriptors that were deleted.
     */
    @objid ("0448f7d4-3019-11e2-8f81-001ec947ccaf")
    public String cleanup() {
        StringBuilder sb = new StringBuilder();
        
        Iterator<FragmentDescriptor> it = this.fragments.iterator();
        while (it.hasNext()) {
            FragmentDescriptor d = it.next();
            if (!d.isValid()) {
                sb.append("Removed incomplete '" + d + "' fragment descriptor.\n");
                it.remove();
            }
        }
        
        Iterator<ModuleDescriptor> mit = this.modules.iterator();
        while (mit.hasNext()) {
            ModuleDescriptor d = mit.next();
            if (!d.isValid()) {
                sb.append("Removed incomplete '" + d + "' module descriptor.\n");
                mit.remove();
            }
        }
        
        Iterator<TodoActionDescriptor> todoIt = this.todo.getActions().iterator();
        while (todoIt.hasNext()) {
            TodoActionDescriptor d = todoIt.next();
            if (!d.isValid() || this.todo.getActions().stream().anyMatch(d2 -> d2 != d && d2.equals(d))) {
                sb.append("Removed duplicate '" + d + "' TODO descriptor.\n");
                todoIt.remove();
            }
        }
        return sb.toString();
    }

    /**
     * Create an empty local project descriptor.
     * <p>
     * Authorization data is set to none.
     * 
     * @param projectName the project name.
     * @param projectPath the project path.
     * @param modelioVersion the Modelio version
     * @return the new project descriptor.
     */
    @objid ("0010f5ae-92ee-1060-84ef-001ec947cd2a")
    public static ProjectDescriptor createEmpty(String projectName, Path projectPath, Version modelioVersion) {
        ProjectDescriptor descriptor = new ProjectDescriptor();
        descriptor.setName(projectName);
        descriptor.setPath(projectPath);
        descriptor.setType(ProjectType.LOCAL.name());
        descriptor.setRemoteLocation("");
        descriptor.setAuthDescriptor(new AuthDescriptor(new NoneAuthData(), DefinitionScope.LOCAL));
        descriptor.setModelioVersion(modelioVersion);
        return descriptor;
    }

    @objid ("db30f14c-f60c-4d41-bfa1-b62f741d92c8")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        ProjectDescriptor other = (ProjectDescriptor) obj;
        
        if (this.formatVersion != other.formatVersion) {
            return false;
        }
        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        
        if (!Objects.equals(this.modelioVersion, other.modelioVersion)) {
            return false;
        }
        
        if (!Objects.equals(this.remoteLocation, other.remoteLocation)) {
            return false;
        }
        
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        
        if (!sameContent(this.fragments, other.fragments)) {
            return false;
        }
        
        if (!Objects.equals(this.auth, other.auth)) {
            return false;
        }
        
        if (!sameContent(this.modules, other.modules)) {
            return false;
        }
        
        if (!sameContent(this.sharedResources, other.sharedResources)) {
            return false;
        }
        
        if (!Objects.equals(this.properties, other.properties)) {
            return false;
        }
        return true;
    }

    /**
     * Get the authentication data descriptor.
     * 
     * @return the authentication data descriptor.
     */
    @objid ("e56af036-c4ad-476c-9bea-80bd4aff34e5")
    public AuthDescriptor getAuthDescriptor() {
        return this.auth;
    }

    /**
     * Get the descriptor format version.
     * @see #serialVersionUID
     * 
     * @return the descriptor format version.
     */
    @objid ("1ea700b4-0fed-41b2-a401-a457c6445199")
    public long getFormatVersion() {
        return this.formatVersion;
    }

    /**
     * @return the fragments.
     */
    @objid ("005fb70c-3590-1fc6-b42e-001ec947cd2a")
    public List<FragmentDescriptor> getFragments() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.fragments;
    }

    /**
     * Get lock informations if the project is locked.
     * 
     * @return the lock informations or <i>null</i>.
     */
    @objid ("0a6603c2-92d9-4d31-8a54-139bf7b43b26")
    public ILockInfo getLockInfo() {
        return this.lockInfo;
    }

    /**
     * Get the version of Modelio used to write this project.
     * 
     * @return the Modelio version.
     */
    @objid ("f0dd8e6b-0be9-49f8-b3c5-89c552d20945")
    public Version getModelioVersion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.modelioVersion;
    }

    /**
     * @return the module descriptors.
     */
    @objid ("aa86f144-ec75-11e1-912e-001ec947ccaf")
    public List<ModuleDescriptor> getModules() {
        return this.modules;
    }

    /**
     * @return the project name.
     */
    @objid ("eed9bf9b-9a71-11e1-ac83-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Get the project space structure.
     * 
     * @return the project space structure. Might be <code>null</code> if this descriptor is not fully initialized.
     */
    @objid ("49be8d30-ab3f-11e1-8392-001ec947ccaf")
    public ProjectFileStructure getProjectFileStructure() {
        return this.pfs;
    }

    @objid ("0a42ec25-9699-495c-8c99-a7e19222bda0")
    public long getProjectSpaceVersion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.projectSpaceVersion;
    }

    /**
     * Get the project properties.
     * 
     * @return the project properties.
     */
    @objid ("f478e911-aa5a-11e1-8392-001ec947ccaf")
    public GProperties getProperties() {
        return this.properties;
    }

    /**
     * Get the project remote location.
     * <p>
     * Returns <code>null</code> for local projects.
     * 
     * @return the project remote location.
     */
    @objid ("380d01f6-0c6e-11e2-bed6-001ec947ccaf")
    public String getRemoteLocation() {
        return this.remoteLocation;
    }

    /**
     * Find a shared resource from its id.
     * 
     * @param id the resource id
     * @return the found resource or <i>null</i>.
     */
    @objid ("df96cd26-97e1-43a1-9f84-cfdeacda50a3")
    public ResourceDescriptor getSharedResource(String id) {
        for (ResourceDescriptor rd : this.sharedResources) {
            if (rd.getId().equals(id)) {
                return rd;
            }
        }
        return null;
    }

    @objid ("029b0124-001f-433d-aff8-76396db9c9b2")
    public List<ResourceDescriptor> getSharedResources() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sharedResources;
    }

    /**
     * Get the actions to do on project opening.
     * 
     * @return the to-do descriptor.
     * @since Modelio 3.4
     */
    @objid ("a112bd0f-bbf5-4037-8a6a-2bc75f330897")
    public TodoDescriptor getTodo() {
        return this.todo;
    }

    /**
     * Get the project type as a string.
     * <p>
     * The string value should match one of the {@link ProjectType} enumeration values.
     * 
     * @return the project type.
     */
    @objid ("b473b153-0baa-11e2-bed6-001ec947ccaf")
    public String getType() {
        return this.type;
    }

    @objid ("0f8e359d-782f-43a4-b80e-5c42fd8b0c46")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.fragments == null ? 0 : this.fragments.hashCode());
        result = prime * result + (this.modules == null ? 0 : this.modules.hashCode());
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        result = prime * result + (this.auth == null ? 0 : this.auth.hashCode());
        result = prime * result + (this.properties == null ? 0 : this.properties.hashCode());
        result = prime * result + (this.remoteLocation == null ? 0 : this.remoteLocation.hashCode());
        result = prime * result + (this.type == null ? 0 : this.type.hashCode());
        result = prime * result + (this.sharedResources == null ? 0 : this.sharedResources.hashCode());
        return result;
    }

    /**
     * set the authentication descriptor.
     * 
     * @param auth the authentication descriptor
     */
    @objid ("7a1b901b-1130-43df-a23c-b63c13b1b567")
    public void setAuthDescriptor(AuthDescriptor auth) {
        this.auth = auth;
    }

    /**
     * Set the descriptor format version.
     * @see #serialVersionUID
     * 
     * @param formatVersion the descriptor format version.
     */
    @objid ("e8b67ce0-1342-4bbe-96d7-5dfac922cbbb")
    public void setFormatVersion(long formatVersion) {
        this.formatVersion = formatVersion;
    }

    /**
     * Set lock informations if the project is locked.
     * 
     * @param lockInfo lock informations
     */
    @objid ("a8fe0c95-328b-474e-b986-1968dd2a39a1")
    public void setLockInfo(ILockInfo lockInfo) {
        this.lockInfo = lockInfo;
    }

    /**
     * Set the version of Modelio used to write this project.
     * 
     * @param value the Modelio version.
     */
    @objid ("a26ffff2-e558-422f-8eb6-478c0431f639")
    public void setModelioVersion(Version value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.modelioVersion = value;
    }

    /**
     * Set the project name.
     * 
     * @param name the project name.
     */
    @objid ("eed9bf9f-9a71-11e1-ac83-001ec947ccaf")
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the project path to initialize the project file structure.
     * 
     * @param projectPath the project path.
     */
    @objid ("001ff676-34d4-1fc7-b42e-001ec947cd2a")
    public void setPath(final Path projectPath) {
        this.pfs = new ProjectFileStructure(projectPath);
    }

    @objid ("365ec166-1211-45e5-b539-bcfeecd14a19")
    public void setProjectSpaceVersion(long value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.projectSpaceVersion = value;
    }

    /**
     * Set the project properties.
     * <p>
     * Since Modelio 3.2 properties are initialized to an empty GProperties, you don't need to set one.
     * 
     * @param gProperties the project properties.
     */
    @objid ("f478e915-aa5a-11e1-8392-001ec947ccaf")
    public void setProperties(final GProperties gProperties) {
        this.properties = gProperties;
    }

    /**
     * Set the project remote location.
     * 
     * @param remoteLocation the project remote location.
     */
    @objid ("380d01fb-0c6e-11e2-bed6-001ec947ccaf")
    public void setRemoteLocation(String remoteLocation) {
        this.remoteLocation = remoteLocation;
    }

    /**
     * Get the actions to do on project opening.
     * 
     * @param newTodo the to-do descriptor.
     * @since Modelio 3.4
     */
    @objid ("95e43b8f-4092-4da1-8b5e-ab140a616357")
    public void setTodo(TodoDescriptor newTodo) {
        this.todo = newTodo;
    }

    /**
     * Set the project type.
     * <p>
     * The string value should match one of the {@link ProjectType} enumeration values.
     * 
     * @param type the project type.
     */
    @objid ("b473b157-0baa-11e2-bed6-001ec947ccaf")
    public void setType(String type) {
        this.type = type;
    }

    @objid ("0448f7cf-3019-11e2-8f81-001ec947ccaf")
    FragmentDescriptor getFragment(String id) {
        for (FragmentDescriptor f : getFragments()) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    @objid ("0448f7ca-3019-11e2-8f81-001ec947ccaf")
    ModuleDescriptor getModule(String moduleName) {
        for (ModuleDescriptor m : getModules()) {
            if (m.getName().equals(moduleName)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Compare 2 lists regardless of the order.
     * 
     * @param c1 a list
     * @param c2 another list
     * @return <code>true</code> if they contain the same elements, whatever the order.
     */
    @objid ("dc7f7c43-cd40-44bf-b252-f6df372fdbe2")
    private boolean sameContent(List<?> c1, List<?> c2) {
        return c1.size() == c2.size() && c1.containsAll(c2);
    }

}
