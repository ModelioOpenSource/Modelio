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
package org.modelio.platform.project.services.syncproject;

import java.io.IOException;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.GProblem;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Defines the {@link IGProjectConfAction} reconfiguration plan that is necessary to re-align an opened project with its reference project.conf definition.
 * @since 5.2
 */
@objid ("bfa07359-314a-40ac-82b7-9e26e8eb6495")
public interface IGProjectConfUpdater {
    /**
     * Compute the reconfiguration plan
     * @param project the project to reconfigure.
     * @param accessDeniedHandler callback that will be called if authentication fails. The implementation is expected to prompt the user for new authentication data.
     * @param progress the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done() on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot be
     * cancelled.
     * @return A project configuration action plan {@link GProjectConfPlan}
     */
    @objid ("697480f1-d4f3-46c9-9b66-9a1227d7db20")
    GProjectConfPlan computeReconfigurationPlan(IGProject project, IAccessDeniedHandler accessDeniedHandler, IGModuleUpdatePolicy moduleUpdater, IModelioProgress progress) throws IOException;

    /**
     * Callback called when authentication fails on a fragment or a module.
     * <p>
     * Implementations should prompt the user for new authentication data.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("c770b2c6-e961-4b97-af5d-8889e1954891")
    interface IAccessDeniedHandler {
        /**
         * Called when authentication fails on a fragment or a module.
         * <p>
         * Implementations should prompt the user for new authentication data.
         * @param name the name of the module/fragment that cannot be accessed
         * @param uri the location of the element
         * @param data the failed authentication data
         * @param e the failure
         * @return the new authentication data to try authentication again or null to abort.
         */
        @objid ("d66f5c31-76f3-432d-b27e-0d8ea6b1b723")
        IAuthData handleAccessDeniedException(String name, URI uri, IAuthData data, AccessDeniedException e);
}
    

    @objid ("0e30d63c-1475-4ffa-b4e7-e8635809aeac")
    interface IGModuleUpdatePolicy {
        @objid ("0a4e602a-3327-4e75-94e7-b6235136fd3f")
        Collection<GProblem> installModule(IModelioProgress progress, IGProject project, IModuleHandle handle, GProjectPartDescriptor md);

        @objid ("aaef572c-f14b-4e8e-af4e-806d2f58a408")
        Collection<GProblem> updateModule(IModelioProgress progress, IGProject project, IModuleHandle handle, GProjectPartDescriptor md);

        @objid ("468b759e-02c6-4abc-93dd-dc67262bb620")
        Collection<GProblem> removeModule(IModelioProgress progress, IGProject project, GModule md);
}
    
}

