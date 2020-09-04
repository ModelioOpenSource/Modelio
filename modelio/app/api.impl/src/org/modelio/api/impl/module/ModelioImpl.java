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

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.impl.app.ModelioContext;
import org.modelio.api.impl.app.picking.PickingService;
import org.modelio.api.impl.app.picking.PickingSessionProxy;
import org.modelio.api.impl.services.ModelioServices;
import org.modelio.api.modelio.IModelioContext;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.audit.IAuditService;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.editor.IEditionService;
import org.modelio.api.modelio.exchange.IExchangeService;
import org.modelio.api.modelio.mc.IModelComponentService;
import org.modelio.api.modelio.meta.IMetamodelService;
import org.modelio.api.modelio.model.IImageService;
import org.modelio.api.modelio.model.IModelManipulationService;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.module.IModuleService;
import org.modelio.api.modelio.navigation.INavigationService;
import org.modelio.api.modelio.pattern.IPatternService;
import org.modelio.api.modelio.picking.IPickingProvider;
import org.modelio.api.modelio.picking.IPickingService;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.script.IScriptService;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.picking.IPickingSession;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;

/**
 * This class a the class that represents the modelio application.
 * <p>
 * It is a singleton. It can be accessed by <code>Modelio.getInstance()</code>
 * method.
 */
@objid ("d8736b08-26ab-4300-976b-ef3ec3a9e3d1")
@Deprecated
public class ModelioImpl extends Modelio {
    @objid ("473953da-ef7d-4563-89d4-02d1f24e33c4")
    @Inject
    private IEclipseContext eclipseContext;

    @objid ("71bc1743-3b13-438c-9350-7d42c87bcf76")
    private GProject openedProject;

    @objid ("3b9c361a-b9ee-49af-84f3-61729a901869")
    @Override
    @Deprecated
    public IAuditService getAuditService() {
        return getService(IAuditService.class);
    }

    @objid ("85361f17-253e-4885-a54e-3f3a0a31fc11")
    @Override
    @Deprecated
    public IModelioContext getContext() {
        return new ModelioContext(this.eclipseContext.get(IProjectService.class));
    }

    @objid ("894e1fb1-599e-46f1-8817-fac43a8731c4")
    @Override
    @Deprecated
    public IDiagramService getDiagramService() {
        return getService(IDiagramService.class);
    }

    @objid ("fc671a0e-3363-4702-b375-888700cffeb6")
    @Override
    @Deprecated
    public IEditionService getEditionService() {
        return getService(IEditionService.class);
    }

    @objid ("018a4b0d-bf37-4507-99fa-3d10358c8415")
    @Override
    @Deprecated
    public IImageService getImageService() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return getService(IImageService.class);
    }

    @objid ("5a77c6f0-4a08-40cf-b15e-feca2a9d8161")
    @Override
    @Deprecated
    public ILogService getLogService() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return getService(ILogService.class);
    }

    @objid ("9c6913f6-c96d-41c7-b228-f436c5c687c2")
    @Override
    @Deprecated
    public IModelingSession getModelingSession() {
        return getService(IModelingSession.class);
    }

    @objid ("3a392484-7f01-42ac-9540-3e15de613045")
    @Override
    @Deprecated
    public IModuleService getModuleService() {
        return getService(IModuleService.class);
    }

    /**
     * Get the navigation service.
     * <p>
     * The navigation service allow to force selection in all the view/dialog
     * that are registered has NavigationListener.
     * 
     * @return the navigation service.
     */
    @objid ("ee682731-1eaf-4ed3-9a1f-25cc0c15f615")
    @Override
    @Deprecated
    public INavigationService getNavigationService() {
        return getService(INavigationService.class);
    }

    @objid ("987def87-b52b-4509-926e-edd031a6434b")
    @Override
    @Deprecated
    public IPickingService getPickingService() {
        return getService(IPickingService.class);
    }

    @objid ("0b1d2672-4f47-466f-a219-6f00ba442910")
    @Override
    @Deprecated
    public IModelComponentService getModelComponentService() {
        return getService(IModelComponentService.class);
    }

    @objid ("f9f80c5d-00aa-45a3-8f0e-d0ee7433d7b0")
    @Override
    @Deprecated
    public IScriptService getScriptService() {
        return getService(IScriptService.class);
    }

    @objid ("3650f193-8394-4332-8af5-53759737e409")
    @Override
    @Deprecated
    public IMetamodelService getMetamodelService() {
        return getService(IMetamodelService.class);
    }

    @objid ("9222d44e-dd97-4c04-a3e6-1c61e89a658e")
    @Override
    @Deprecated
    public IModelManipulationService getModelManipulationService() {
        return getService(IModelManipulationService.class);
    }

    @objid ("a618e022-c10d-4055-9c88-895f8203c4c8")
    @Override
    @Deprecated
    public IExchangeService getExchangeService() {
        return getService(IExchangeService.class);
    }

    @objid ("f7729365-15d1-4302-aa41-19b8637a5507")
    @Override
    @Deprecated
    public IPatternService getPatternService() {
        return getService(IPatternService.class);
    }

    @objid ("f820a10f-612e-4dcf-b3ea-b601a6f8202c")
    @Override
    @Deprecated
    public synchronized <I> I getService(final Class<I> serviceInterface) {
        return ModelioServices.getInstance().getService(serviceInterface);
    }

    /**
     * Called by E4 injection to initialize the instance
     * 
     * @param context the eclipse context.
     */
    @objid ("42b90af5-1701-49d6-bb27-315df2f68a70")
    @Execute
    void initialize(final IEclipseContext context) {
        this.eclipseContext = context;
        Modelio.instance = this;
    }

    @objid ("64d34c74-89d2-4563-999e-2889f7f3e111")
    @Inject
    @Optional
    void onProjectOpening(@EventTopic(ModelioEventTopics.PROJECT_OPENING) final GProject newProject) {
        this.openedProject = newProject;
    }

    /**
     * Package private constructor.
     */
    @objid ("e290b87e-70bd-4f42-9e3d-2466409ecdbb")
    ModelioImpl() {
    }

    @objid ("2b27227a-0b07-4d78-a8aa-d7d70e871539")
    @Inject
    @Optional
    void onPickingStart(@EventTopic(ModelioEventTopics.PICKING_START) final IPickingSession session) {
        // @UIEventTopic doesn't seems to be working here...
        Display.getDefault().asyncExec(new Runnable() {
        
            @Override
            public void run() {
                for (IPickingProvider provider : ((PickingService) getPickingService())
                        .getPickingProvider()) {
                    provider.enterPickingMode(new PickingSessionProxy(session));
                }
            }
        });
    }

    @objid ("9cd03e62-28d2-462c-ae55-ead92df7ab6a")
    @Inject
    @Optional
    void onPickingStop(@EventTopic(ModelioEventTopics.PICKING_STOP) final IPickingSession session) {
        // @UIEventTopic doesn't seems to be working here...
        Display.getDefault().asyncExec(new Runnable() {
        
            @Override
            public void run() {
                for (IPickingProvider provider : ((PickingService) getPickingService())
                        .getPickingProvider()) {
                    provider.enterPickingMode(new PickingSessionProxy(session));
                }
            }
        });
    }

}
