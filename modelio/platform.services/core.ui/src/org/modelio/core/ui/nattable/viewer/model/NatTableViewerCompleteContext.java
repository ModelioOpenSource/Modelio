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

package org.modelio.core.ui.nattable.viewer.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.app.core.project.ICurrentProjectService;
import org.modelio.mda.infra.service.IModuleService;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Implementation of {@link INatTableViewerContext} based on an {@link IEclipseContext}.
 * 
 * @since Valkyrie 3.8
 */
@objid ("3bc0e612-5139-4578-90c1-ce7e1b2129af")
public class NatTableViewerCompleteContext implements INatTableViewerContext {
    @objid ("bf5f4bf4-7523-45f5-a6d1-be12a1fa8411")
    private IEclipseContext context;

    @objid ("6f870999-e7a8-4b5c-8e63-4d107f91bd77")
    @Override
    public IActivationService getActivationService() {
        return this.context.get(IActivationService.class);
    }

    @objid ("6beda4ae-0d63-44fd-9da8-f9e59aa0a367")
    @Override
    public IMModelServices getModelService() {
        return this.context.get(IMModelServices.class);
    }

    @objid ("40ad6e1d-81f8-4962-8c58-742ba73f0576")
    @Override
    public IModelioNavigationService getNavigationService() {
        return this.context.get(IModelioNavigationService.class);
    }

    @objid ("774b1f86-9d16-43bd-8a66-a198d18272fb")
    @Override
    public IModelioPickingService getPickingService() {
        return this.context.get(IModelioPickingService.class);
    }

    @objid ("3eca918a-b736-4c72-befe-973450855cea")
    @Override
    public IMdaExpert getMdaExpert() {
        return this.context.get(IModuleService.class).getMdaExpert();
    }

    /**
     * Initialize with an eclipse context.
     * @param context an eclipse context to get Modelio services from.
     */
    @objid ("a3696daa-3790-4a00-8ab4-2867afd5473c")
    public NatTableViewerCompleteContext(IEclipseContext context) {
        super();
        this.context = context;
    }

    @objid ("25a62bec-23f7-41a8-8b93-9ec39b932ea7")
    @Override
    public IPreferenceStore getProjectPreferences(String nodeId) {
        ICurrentProjectService projectService = this.context.get(ICurrentProjectService.class);
        return projectService != null ? projectService.getProjectPreferences(nodeId) : null;
    }

    @objid ("2a1d02c5-e990-48f2-a39f-a387fd26eda3")
    @Override
    public ICoreSession getSession() {
        ICurrentProjectService projectService = this.context.get(ICurrentProjectService.class);
        return projectService != null ? projectService.getSession() : null;
    }

}
