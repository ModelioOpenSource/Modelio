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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.version.Version;

/**
 * Descriptor of a GProject, this class holds an in-memory representation of a "project.conf" file.
 * <p>
 * Descriptors can be created either by reading from a file
 * with {@link GProjectDescriptorReader#read(Path, DefinitionScope)},
 * or by creating an empty one with {@link #createEmpty(String, Path, Version)}.
 * 
 * <h2>Warning</h2>
 * 
 * {@link #equals(Object)} and {@link #hashCode()} <b>use all fields</b>.
 * It is not suitable to put a {@link GProjectDescriptor} directly in a Set or as use it as Map key.
 */
@objid ("05c683f3-c680-455e-93a1-2fdcb9714bc3")
public final class GProjectDescriptor implements Serializable {
    /**
     * The last project space directory structure version.
     * <p>
     * It is different from {@link #serialVersionUID} that represents the last descriptor format version.
     * <h3>History:</h3>
     * <ul>
     * <li>0 : initial version, this attribute didn't exist
     * <li>1 : 21/03/2017 : Project space reorganized for Modelio 3.7
     * </ul>
     */
    @objid ("04772157-f1f8-4708-bfdd-7a4056d27756")
    public static final long currentProjectSpaceVersion = 1L;

    /**
     * This descriptor format version.
     * <p>
     * see {@link #serialVersionUID} for the last format version.
     */
    @objid ("f7a45f10-246a-4cf0-ae63-1deba3a9c4aa")
    private long formatVersion = GProjectDescriptor.serialVersionUID;

    @objid ("ef64352a-4f46-4e50-8398-d12b1c515c2e")
    private String name;

    /**
     * This project space directory structure version.
     * <p>
     * It is different from {@link #formatVersion} that represents the descriptor format version.
     * <p>
     * See {@link #currentProjectSpaceVersion} for history.
     */
    @objid ("71335d55-3b3e-48fa-9582-d487644f8a8c")
    private long projectSpaceVersion = GProjectDescriptor.currentProjectSpaceVersion;

    /**
     * Remote path of the project for server projects. <code>null</code> for local projects.
     */
    @objid ("3799193c-2413-446c-a4fa-ba3145894b4f")
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
     * <li>6 : 02/2021 - Modelio 5.2 : added features (ie 'by project activable/de-activable plugins'), generalization of part descriptor
     * <li>7 : 11/2022 - Modelio 5.3 : added features ???
     * </ul>
     */
    @objid ("958fee27-da97-4a7b-9b57-bf4b61379165")
    public static final long serialVersionUID = 7L;

    /**
     * Project type : local, svn, http...
     */
    @objid ("03dbc45d-8639-4c1e-a011-1a7102636510")
    private String type;

    /**
     * Authentication material to access the project.
     */
    @objid ("db1a16b7-0266-4571-b188-598bbaedf52d")
    private AuthDescriptor auth;

    @objid ("49af5a56-ecf7-40ff-8280-e2f207afe2f4")
    private final List<GProjectPartDescriptor> partDescriptors = new ArrayList<>();

    /**
     * Lock informations if the project is locked.
     */
    @objid ("78ba8f99-47d2-4c78-a8e4-ae4ef86d6970")
    private ILockInfo lockInfo;

    /**
     * The version of Modelio used to write this project.
     */
    @objid ("12096f40-b7a8-4791-8c77-69a4d4c5d53e")
    private Version modelioVersion;

    /**
     * The physical path of the project Note : the project space can be either local or delegated to another directory.
     */
    @objid ("6763173d-be9a-46fa-b0fc-0e1e9cfc6645")
    private ProjectFileStructure pfs;

    /**
     * Properties of the project
     */
    @objid ("590a417e-6220-4cf3-9b57-dcb6ab1094fb")
    private GProperties properties = new GProperties();

    /**
     * Instantiate an empty project descriptor.
     */
    @objid ("b2716533-ea5e-49e0-b59e-27df74b6bfc5")
    public  GProjectDescriptor() {
        
    }

    /**
     * Copy constructor.
     * @param orig the descriptor to copy.
     * @param withFragments if true fragment descriptors are copied too. If false the returned project has no fragment at all.
     */
    @objid ("3e7c68e7-de95-42f0-844e-2fe0cb8671ca")
    public  GProjectDescriptor(GProjectDescriptor orig, boolean withFragments) {
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
        
        if (withFragments) {
            for (GProjectPartDescriptor of : orig.getPartDescriptors()) {
                getPartDescriptors().add(new GProjectPartDescriptor(of));
            }
        }
        
    }

    /**
     * Remove incomplete module and fragment descriptors.
     * @return A report of all incomplete descriptors that were deleted.
     */
    @objid ("a872e58c-d35f-4cec-aea2-e4061c742e8f")
    public String cleanup() {
        StringBuilder sb = new StringBuilder();
        
        Iterator<GProjectPartDescriptor> it = this.partDescriptors.iterator();
        while (it.hasNext()) {
            GProjectPartDescriptor d = it.next();
            if (!d.isValid()) {
                sb.append("Removed incomplete '" + d + "' part descriptor.\n");
                it.remove();
            }
        }
        return sb.toString();
    }

    /**
     * Create an empty local project descriptor.
     * <p>
     * Authorization data is set to none.
     * @param projectName the project name.
     * @param projectPath the project path.
     * @param modelioVersion the Modelio version
     * @return the new project descriptor.
     */
    @objid ("79909b3f-ce2e-4673-a370-40ea3b20597d")
    public static GProjectDescriptor createEmpty(String projectName, Path projectPath, Version modelioVersion) {
        GProjectDescriptor descriptor = new GProjectDescriptor();
        descriptor.setName(projectName);
        descriptor.setPath(projectPath);
        descriptor.setType(ProjectType.LOCAL.name());
        descriptor.setRemoteLocation("");
        descriptor.setAuthDescriptor(new AuthDescriptor(new NoneAuthData(), DefinitionScope.LOCAL));
        descriptor.setModelioVersion(modelioVersion);
        return descriptor;
    }

    /**
     * Get the authentication data descriptor.
     * @return the authentication data descriptor.
     */
    @objid ("933f2b9d-7e63-437c-9654-c27824411ce4")
    public AuthDescriptor getAuthDescriptor() {
        return this.auth;
    }

    /**
     * Get the descriptor format version.
     * @see #serialVersionUID
     * @return the descriptor format version.
     */
    @objid ("2cec15a5-5258-40a2-b8df-f0914cbd39e3")
    public long getFormatVersion() {
        return this.formatVersion;
    }

    /**
     * Get the fragment descriptors.
     * <p>
     * Returns the live list, <b>do not mess with it!</b>.
     * @return the fragments.
     */
    @objid ("dc91bad9-a971-49d4-95bc-8f8cea35af07")
    public List<GProjectPartDescriptor> getPartDescriptors() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.partDescriptors;
    }

    /**
     * Return the projects parts matching given type.
     * @param partType the searched type
     * @return the projects parts matching given type.
     */
    @objid ("2f56e5e4-fab9-461d-9d9c-2b2064a8f7e4")
    public List<GProjectPartDescriptor> getPartDescriptors(final GProjectPartType partType) {
        return getPartDescriptors().stream().filter(d -> {
            return d.getType() == partType;
        }).collect(Collectors.toList());
        
    }

    /**
     * Get lock informations if the project is locked.
     * @return the lock informations or <i>null</i>.
     */
    @objid ("7c5ac14f-f313-4005-83f5-31ddd98ee919")
    public ILockInfo getLockInfo() {
        return this.lockInfo;
    }

    /**
     * Get the version of Modelio used to write this project.
     * @return the Modelio version.
     */
    @objid ("956d5529-7502-45ab-b07a-885dec712590")
    public Version getModelioVersion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.modelioVersion;
    }

    /**
     * @return the project name.
     */
    @objid ("067acf04-1ac7-441d-bc6f-13c5c370a95e")
    public String getName() {
        return this.name;
    }

    /**
     * Get the project space structure.
     * @return the project space structure. Might be <code>null</code> if this descriptor is not fully initialized.
     */
    @objid ("0e2631e8-048d-4a4d-8980-0ac9d5453454")
    public ProjectFileStructure getProjectFileStructure() {
        return this.pfs;
    }

    @objid ("4e8881de-2970-48da-bbd0-bc30fd9ab220")
    public long getProjectSpaceVersion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.projectSpaceVersion;
    }

    /**
     * Get the project properties.
     * @return the project properties.
     */
    @objid ("60b9a99c-22ba-4cfa-b19f-428647583354")
    public GProperties getProperties() {
        return this.properties;
    }

    /**
     * Get the project remote location.
     * <p>
     * Returns <code>null</code> for local projects.
     * @return the project remote location.
     */
    @objid ("f693a692-ec04-45f2-9530-2bbf7562dcbc")
    public String getRemoteLocation() {
        return this.remoteLocation;
    }

    /**
     * Find a part descriptor from its id.
     * @param id the part descriptor id
     * @return the found resource or <i>null</i>.
     */
    @objid ("794ffe23-a5d8-49c1-bcb7-fad72f8f95ef")
    public GProjectPartDescriptor getPartDescriptor(String id) {
        for (GProjectPartDescriptor rd : this.partDescriptors) {
            if (rd.getId().equals(id)) {
                return rd;
            }
        }
        return null;
    }

    /**
     * Get the project type as a string.
     * <p>
     * The string value should match one of the {@link ProjectType} enumeration values.
     * @return the project type.
     */
    @objid ("ed8fbadb-b87b-48fd-8f3b-1638c20f8697")
    public String getType() {
        return this.type;
    }

    /**
     * set the authentication descriptor.
     * @param auth the authentication descriptor
     */
    @objid ("41936659-2deb-495d-8da5-370bcd22929c")
    public void setAuthDescriptor(AuthDescriptor auth) {
        this.auth = auth;
    }

    /**
     * Set the descriptor format version.
     * @see #serialVersionUID
     * @param formatVersion the descriptor format version.
     */
    @objid ("89457b78-b545-4c71-84f1-b12a2c356134")
    public void setFormatVersion(long formatVersion) {
        this.formatVersion = formatVersion;
    }

    /**
     * Set lock informations if the project is locked.
     * @param lockInfo lock informations
     */
    @objid ("7d091e98-feee-4b45-b50b-6f821b73d959")
    public void setLockInfo(ILockInfo lockInfo) {
        this.lockInfo = lockInfo;
    }

    /**
     * Set the version of Modelio used to write this project.
     * @param value the Modelio version.
     */
    @objid ("1447e9f1-f091-4637-865b-643d2d3f4a20")
    public void setModelioVersion(Version value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.modelioVersion = value;
        
    }

    /**
     * Set the project name.
     * @param name the project name.
     */
    @objid ("9e830b99-92c5-4366-a2fe-0eefda2ce589")
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the project path to initialize the project file structure.
     * @param projectPath the project path.
     */
    @objid ("1f4f4cb6-bf40-4230-a5d0-973fbafd7710")
    public void setPath(final Path projectPath) {
        this.pfs = new ProjectFileStructure(projectPath);
    }

    @objid ("2c272eae-d21a-497b-8cb3-01387f6c16d3")
    public void setProjectSpaceVersion(long value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.projectSpaceVersion = value;
        
    }

    /**
     * Set the project properties.
     * <p>
     * Since Modelio 3.2 properties are initialized to an empty GProperties, you don't need to set one.
     * @param gProperties the project properties.
     */
    @objid ("c0b5e30d-0f41-42db-978b-417e2ba8788a")
    public void setProperties(final GProperties gProperties) {
        this.properties = gProperties;
    }

    /**
     * Set the project remote location.
     * @param remoteLocation the project remote location.
     */
    @objid ("7c023531-3553-4653-9ca2-fd11ccb5eddd")
    public void setRemoteLocation(String remoteLocation) {
        this.remoteLocation = remoteLocation;
    }

    /**
     * Set the project type.
     * <p>
     * The string value should match one of the {@link ProjectType} enumeration values.
     * @param type the project type.
     */
    @objid ("b3176431-d0f2-47ee-a751-66e481682f1f")
    public void setType(String type) {
        this.type = type;
    }

    @objid ("d7ff015e-c52b-400b-a5c2-7921f7a614a8")
    @Override
    public int hashCode() {
        return Objects.hash(this.auth, this.formatVersion, this.modelioVersion, this.name, this.partDescriptors,
                this.projectSpaceVersion, this.properties, this.remoteLocation, this.type);
        
    }

    /**
     * Makes a deep comparison with the other descriptor.
     */
    @objid ("bfc96343-1855-47ae-aa1b-6f76b75676fd")
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
        GProjectDescriptor other = (GProjectDescriptor) obj;
        return deepEquals(other);
    }

    /**
     * Makes a deep comparison with the other project descriptor.
     * @param other the other project descriptor.
     * @return true only if all projects fields and parts are exactly the same.
     */
    @objid ("488685fc-c42f-4113-b279-8a2f12cef873")
    public boolean deepEquals(GProjectDescriptor other) {
        // Make comparable versions of part lists.
        ArrayList<GProjectPartDescriptor> myParts = new ArrayList<>(this.partDescriptors);
        ArrayList<GProjectPartDescriptor> otherParts = new ArrayList<>(other.partDescriptors);
        Comparator<GProjectPartDescriptor> comparator = GProjectPartDescriptor::compareIds;
        myParts.sort(comparator);
        otherParts.sort(comparator);
        return true
                && this.formatVersion == other.formatVersion
                && Objects.equals(this.type, other.type)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.auth, other.auth)
                && Objects.equals(this.modelioVersion, other.modelioVersion)
                && Objects.equals(this.properties, other.properties)
                && Objects.equals(this.remoteLocation, other.remoteLocation)
                && this.projectSpaceVersion == other.projectSpaceVersion
                && Objects.equals(myParts, otherParts);
        
    }

}
