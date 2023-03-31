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
package org.modelio.gproject.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.monitor.GProjectEvent;
import org.modelio.gproject.parts.AbstractGPart;
import org.modelio.gproject.parts.GPartState;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;

/**
 * This interface indicates a specific feature in a Modelio project, with its own lifecycle.
 * 
 * This interface should not be directly implemented. Extends {@link AbstractGPart} instead.
 * 
 * @since 5.2
 */
@objid ("02d95c7e-d779-44e9-9399-f2eb27773bf7")
public interface IGPart {
    /**
     * @return the non null id for the project part. This id must be unique.
     */
    @objid ("755e31f8-08bb-41f2-a0e4-d9434259d30e")
    String getId();

    /**
     * The 'Modelio' version of the part. Used to manage migration of the part when necessary.
     * @return The 'Modelio' version of the part.
     */
    @objid ("4bbd8068-083b-4591-b4e6-5c749b683500")
    Version getVersion();

    /**
     * @return The type of the part.
     */
    @objid ("1e548efd-925d-437c-96fb-98f6331bea47")
    GProjectPartType getType();

    /**
     * @return the descriptor of the part, descriptor fields are set to the part current values.
     */
    @objid ("dde71f4d-f9b9-4574-8a47-d7b6c1f619ec")
    GProjectPartDescriptor getDescriptor();

    /**
     * Get the state manager for this part.
     * 
     * The effective part state value can be obtained from {@link IGPartState#getValue()}
     * @return The part runtime state manager in this project.
     */
    @objid ("2b53491b-5ecd-40ed-bc74-5df75d7cba3a")
    IGPartState getState();

    /**
     * Mount the part in the project making it 'active' in the project.<br/>
     * The mount lasts only for the current project session.<br/>
     * For the part to be automatically mounted at next project opening it has to be installed.
     * 
     * Implementors have to:
     * <ul>
     * <li>update the part state according to the mount() operation results (mounting, success, failed see {@link IGPartState}).</li>
     * <li>report the down error in the state in case of failure</li>
     * <li>post GFailure to the project to log the mount failure cause</li>
     * </ul>
     * 
     * Please note that the {@link IGPartState} will automatically fire {@link GProjectEvent} to the project event support when state changes.
     * @param monitor a progress monitor. Can be <code>null</code>.
     * @throws GPartException if the mount fails.
     */
    @objid ("a82e4958-5699-4881-bbc7-b712421f9de9")
    void mount(IModelioProgress monitor) throws GPartException;

    /**
     * Unmount the part in the project making it 'inactive' in the project.<br/>
     * The umount applies immediately for the current project session.<br/>
     * However, for installed parts, the 'umounted' state of the part will be persisted in the project.conf file and the part will not be mounted at next project opening.
     * 
     * Implementors have to:
     * <ul>
     * <li>update the part state according to the umount() operation results (umounting, success, failed see {@link GPartState}).</li>
     * <li>report the down error in the state in case of failure</li>
     * <li>post GFailure to the project to log the umount failure cause</li>
     * </ul>
     * 
     * Please note that the {@link GPartState} will automatically fire {@link GProjectEvent} to the project event support when state changes.
     * @param monitor a progress monitor. Can be <code>null</code>.
     * @throws GPartException if the unmount fails.
     */
    @objid ("d125ba1c-41cc-4b1a-934f-09b10a0868a0")
    void unmount(IModelioProgress monitor) throws GPartException;

    /**
     * @return The definition scope of this part.
     */
    @objid ("0f8fb674-840b-4311-8fa6-c57950ebe92d")
    DefinitionScope getDefinitionScope();

    /**
     * The properties of this part.<br/>
     * Key values depends on the part type.<br/>
     * Properties are persisted in the project.conf file.
     * @return the part properties
     */
    @objid ("3b7a6092-1cb4-4075-ab62-973138a5c462")
    GProperties getProperties();

    /**
     * @return the authentication mode and configuration for this part.
     */
    @objid ("9e98ad15-c18e-451f-be26-13547f189025")
    AuthDescriptor getAuth();

    /**
     * Whether or not this part is 'active'. what 'active' means exactly depends on the part type and left to the part implementor.
     * @return <code>true</code> if the part is active. <code>false</code> if not.
     */
    @objid ("3e612c09-4626-4db4-97b3-dc43c53b5db6")
    boolean isActive();

    /**
     * Sets whether or not this part is active.
     * @see IGPart#isActive()
     * @param b <code>true</code> if the part is active. <code>false</code> if not.
     * @throws GPartException if the configuration change fails.
     */
    @objid ("64ba6eee-fe2e-4bcd-846d-abe0fd0e5687")
    void setActive(boolean b) throws GPartException;

    /**
     * The project this part is installed in.
     * @return the project this part is installed in or <code>null</code> if the part is not mounted.
     */
    @objid ("158f9d77-7e15-4144-b5d0-7b24c9e8eba5")
    IGProject getProject();

    /**
     * Install the part in the project.
     * @param project the project to install the part into.
     * @param monitor a progress monitor. Can be <code>null</code>.
     * @throws GPartException if the install fails.
     */
    @objid ("8aacd152-8725-4bfe-85ed-c2887f7f430b")
    void install(IGProject project, IModelioProgress monitor) throws GPartException;

    /**
     * Uninstall the part from the project.
     * @param project the project to uninstall the part from.
     * @param monitor a progress monitor. Can be <code>null</code>.
     * @throws GPartException if the uninstall fails.
     */
    @objid ("ce53b119-0f4a-4088-91c2-90bc440ea81f")
    void uninstall(IGProject project, IModelioProgress monitor) throws GPartException;

    /**
     * This exception is thrown when modifying a part in a project.
     * 
     * @since 5.2
     */
    @objid ("acf3047a-fd39-46bc-83a8-42cf76b61b1d")
    static class GPartException extends Exception {
        @objid ("52f0f571-92a6-4f4d-a16e-e263251fcf18")
        private static final long serialVersionUID = -1720970669102024935L;

        /**
         * Default c'tor.
         */
        @objid ("c4479a98-cc67-4234-a3eb-8af3206f6909")
        public  GPartException() {
            super();
        }

        /**
         * Constructs a new exception with the specified detail message.
         * @param message the detail message.
         */
        @objid ("47d2d3cc-6c9c-4c85-99d4-37bd657daaf4")
        public  GPartException(String message) {
            super(message);
        }

        /**
         * Constructs a new exception with a cause.
         * @param cause the cause. Can be <code>null</code> if the cause is nonexistent or unknown.
         */
        @objid ("cea60cc4-0a68-42e2-b78b-862e073e416b")
        public  GPartException(Throwable cause) {
            super(cause);
        }

        /**
         * Constructs a new exception with the specified detail message and a cause.
         * @param message the detail message.
         * @param cause the cause. Can be <code>null</code> if the cause is nonexistent or unknown.
         */
        @objid ("879350d2-1880-4299-84f5-37d457942e8a")
        public  GPartException(String message, Throwable cause) {
            super(message, cause);
        }

    }
}

