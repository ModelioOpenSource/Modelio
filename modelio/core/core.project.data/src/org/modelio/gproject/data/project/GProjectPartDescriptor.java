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
import java.net.URI;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.version.Version;

/**
 * Project part descriptor.
 * <p>
 * A part is identified by its {@link #getId()} and {@link #getType()}.
 * Both are used in {@link #equals(Object)}, {@link #hashCode()} and {@link #compareIds(GProjectPartDescriptor, GProjectPartDescriptor)}.
 */
@objid ("7be02518-702c-4f8b-95fd-dc313618cbcf")
public class GProjectPartDescriptor implements Serializable {
    /**
     * Class format version
     */
    @objid ("f8ad8b78-e03e-423d-8e2a-8a406d1ccec6")
    public static final long serialVersionUID = 1L;

    @objid ("9fdece05-82bc-4626-b55b-22f901b8358e")
    private String id;

    @objid ("09c3fb87-11c9-4883-84cb-7aece7aa56df")
    private DefinitionScope definitionScope;

    @objid ("4981c5ae-f55e-4164-a0ad-816ccb98de52")
    private String label;

    @objid ("2724bce4-4df4-43d7-bfd9-4ecdd3d36498")
    private GProjectPartType type;

    /**
     * The object returned by {@link #getVersion()} when no version is specified.
     */
    @objid ("c72af831-7bc1-49b5-ad14-6f4e5d8bcf11")
    public static final Version NO_VERSION = new Version(0,0,0);

    @objid ("6ab39c1d-1be6-4e39-b68b-8121fac525a7")
    private URI location;

    @objid ("0a7d0103-3aaf-4d65-8422-03e957c4db16")
    private Version version;

    @objid ("4e6c9bfc-99ac-4963-bb5f-2a6cf21f93f9")
    private AuthDescriptor auth;

    @objid ("b4590e47-04c1-4ef5-9b90-f55a2f943204")
    private GProperties properties;

    @objid ("e85c86e9-bf62-45dd-bc1d-bab8848b8356")
    public  GProjectPartDescriptor(GProjectPartType type, String id, Version version, DefinitionScope definitionScope) {
        this.type = type;
        this.id = id;
        this.version = version;
        this.definitionScope = definitionScope;
        this.properties = new GProperties();
        
    }

    /**
     * @param fromDescriptor the descriptor to copy.
     */
    @objid ("de22d7d1-3c68-4991-a238-537c631e085f")
    public  GProjectPartDescriptor(GProjectPartDescriptor fromDescriptor) {
        this.type = fromDescriptor.type;
        this.id = fromDescriptor.id;
        this.version = fromDescriptor.version;
        this.definitionScope = fromDescriptor.definitionScope;
        this.auth = fromDescriptor.auth != null ? new AuthDescriptor(fromDescriptor.auth.getData(), fromDescriptor.auth.getScope()) : new AuthDescriptor(new NoneAuthData(), DefinitionScope.LOCAL);
        this.label = fromDescriptor.label;
        this.properties = new GProperties(fromDescriptor.properties);
        this.location = fromDescriptor.location;
        
    }

    /**
     * @return the part identifier
     */
    @objid ("990c8e88-f19b-444a-8384-31347578264e")
    public String getId() {
        return this.id;
    }

    /**
     * @param id the part identifier
     */
    @objid ("cb7dd6c5-e754-49ab-bee0-c7ec199b8c4d")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the part type.
     */
    @objid ("28ed172c-1cff-4612-b696-a9223ae832ad")
    public GProjectPartType getType() {
        return this.type;
    }

    /**
     * Get the part user friendly label.
     * @return the part user friendly label.
     */
    @objid ("54438538-177d-497b-ac6a-8b26e7d42e67")
    public String getLabel() {
        return this.label;
    }

    /**
     * @return the part version or {@link #NO_VERSION} if none specified.
     */
    @objid ("4418f879-f0ba-4b7d-a765-2f30c1e2bde5")
    public Version getVersion() {
        return this.version != null ? this.version : NO_VERSION;
    }

    /**
     * Get the part location.
     * <p>
     * The location may be relative to the project directory or a remote URI.
     * @return the part location.
     */
    @objid ("44e02eb4-c9b9-4aa9-86a0-6191d50d0fc6")
    public URI getLocation() {
        return this.location;
    }

    /**
     * Get the part location.
     * <p>
     * The location may be relative to the project directory or a remote URI.
     * @param location the part location.
     */
    @objid ("4b0a5a4e-d994-4276-946d-a51c825be177")
    public void setLocation(URI location) {
        this.location = location;
    }

    /**
     * Get the authentication data descriptor.
     * @return the authentication data descriptor.
     */
    @objid ("9f1ae3dc-b0a9-42c8-b91b-5dbec510fcd4")
    public AuthDescriptor getAuth() {
        return this.auth;
    }

    /**
     * set the authentication descriptor.
     * @param auth the authentication descriptor
     */
    @objid ("588f4eee-1058-4fec-bbbf-3f9d949222e2")
    public void setAuth(AuthDescriptor auth) {
        this.auth = auth;
    }

    /**
     * @return the part configuration properties.
     */
    @objid ("859413f6-b588-4e7a-b3d5-e4a71f25ef86")
    public GProperties getProperties() {
        return this.properties;
    }

    /**
     * Replaces the part properties.
     * @param properties the part properties.
     */
    @objid ("08cc9fe1-69b6-414d-945e-29802c712496")
    public void setProperties(GProperties properties) {
        this.properties = properties;
    }

    /**
     * Get the definition scope.
     * <p>
     * The fragment may be defined locally or on a server.
     * @return the definition scope.
     */
    @objid ("9715a0c5-ac3d-4b72-a498-484472d23d23")
    public DefinitionScope getDefinitionScope() {
        return this.definitionScope;
    }

    /**
     * Set the part user friendly label.
     * @param label the part user friendly label.
     */
    @objid ("e6d80c18-aec3-4688-9fac-9e477c381dd5")
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Tells whether this descriptor is a complete fragment descriptor or only
     * a reference to a fragment with local level properties.
     * @return <code>true</code> if the descriptor is complete enough to instantiate a fragment.
     */
    @objid ("098b6c59-a1fe-44c4-bc6f-c61c3266d77d")
    public boolean isValid() {
        return this.id != null &&
                ! this.id.isEmpty() &&
                this.definitionScope != null &&
                this.type != null;
        
    }

    /**
     * Set the definition scope.
     * @param definitionScope the definition scope.
     */
    @objid ("60ca6330-9181-4568-8971-18a94d43849f")
    public void setDefinitionScope(DefinitionScope definitionScope) {
        this.definitionScope = definitionScope;
    }

    /**
     * @param version the part version, may be null.
     */
    @objid ("20ed4543-5822-40e3-abc0-9d02899c1b5a")
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * Visibility package : not to be used public
     */
    @objid ("0b77186b-cf33-4805-90cc-743f04b6bdb7")
    void setType(GProjectPartType type) {
        this.type = type;
    }

    @objid ("ecac0eae-1487-415b-b6a3-802690e6454f")
    @Override
    public String toString() {
        return "GProjectPartDescriptor [id=" + this.id + ", version=" + this.version + ", type=" + this.type + ", definitionScope=" + this.definitionScope + "]";
    }

    @objid ("e06d23d7-02c6-4f7c-b50a-d24595f35351")
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type);
    }

    /**
     * Makes a deep comparison with the other descriptor.
     */
    @objid ("7ef5d58d-9524-49cf-a978-c85009fc3034")
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
        GProjectPartDescriptor other = (GProjectPartDescriptor) obj;
        return matches(other);
    }

    /**
     * Compares the identifier and type of both descriptors.
     * @param other another part descriptor
     * @return true only if both {@link #getId()} and {@link #getType()} are equal.
     */
    @objid ("0fdf08b4-48ce-4511-84c4-e92073373efc")
    public boolean matches(GProjectPartDescriptor other) {
        return this.type == other.type && Objects.equals(this.id, other.id);
    }

    /**
     * Makes a deep comparison with the other descriptor.
     * @param other another part descriptor
     * @return true only if all fields are equal.
     */
    @objid ("20661fb3-9be4-46e4-a17c-ddd059115a0a")
    public boolean deepEquals(GProjectPartDescriptor other) {
        return other != null
                && this.type == other.type
                && this.definitionScope == other.definitionScope
                && Objects.equals(this.auth, other.auth)
                && Objects.equals(this.id, other.id)
                && Objects.equals(this.label, other.label)
                && Objects.equals(this.location, other.location)
                && Objects.equals(this.properties, other.properties)
                && Objects.equals(this.version, other.version);
        
    }

    /**
     * Compare 2 part descriptor identifiers : {@link #getId()} and {@link #getType()}.
     * <p>
     * To be used as method reference to make a Comparator.
     * @param a a part descriptor, not null
     * @param b a part descriptor, not null
     * @return an order
     */
    @objid ("c10f2631-f674-4faf-ae6d-6625a3079228")
    public static int compareIds(GProjectPartDescriptor a, GProjectPartDescriptor b) {
        String ida = a.getId();
        String idb = b.getId();
        if (ida == null) ida = "";
        if (idb == null) idb = "";
        
        int ret = ida.compareTo(idb);
        if (ret != 0)
            return ret;
        
        GProjectPartType typea = a.getType();
        GProjectPartType typeb = b.getType();
        
        if (typea !=null && typeb != null) {
            // usual case
            return typea.ordinal() - typeb.ordinal();
        } else if (typeb != null) {
            // only type a is null
            return -1;
        } else if (typea != null) {
            // only typeb is null
            return 1;
        } else {
            // both are null
            return 0;
        }
        
    }

    /**
     * Part types.
     */
    @objid ("211ccff2-df54-44db-ab3e-d823e6f62e27")
    public enum GProjectPartType {
        /**
         * Local EXML model fragment
         */
        @objid ("9eb819bd-776c-4797-9631-ba062dc0551e")
        EXMLFRAGMENT,
        /**
         * SVN shared model fragment
         */
        @objid ("8ff61f56-5157-4855-896d-260963ca3630")
        SVNFRAGMENT,
        /**
         * EXML fragment located on a HTTP server.
         */
        @objid ("6bf6df7a-ddd1-4ea2-88ba-668efbe05d2c")
        HTTPFRAGMENT,
        /**
         * RAMC archive fragment.
         */
        @objid ("bcc140a9-6113-4acb-80ff-ba8a9da95444")
        RAMC,
        /**
         * Module.
         */
        @objid ("6333a9d0-c091-4605-9bb4-3fdf7427a118")
        MODULE,
        /**
         * A feature that can be enabled or disabled.
         */
        @objid ("199078f5-da76-4932-9746-b2a65bed669f")
        FEATURE,
        /**
         * A physical resource in the project space.
         */
        @objid ("2f5c184c-197c-4219-aa12-3b7c627558ce")
        RESOURCE,
        /**
         * Shared real time Model server JSON fragment.
         * @since > 5.4.0
         */
        @objid ("c10e9ffe-591a-42a9-83d4-472b15c3e3f7")
        CONFERENCEFRAGMENT;

    }

}
