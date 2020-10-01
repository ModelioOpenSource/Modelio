/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
import org.modelio.api.modelio.picking.IPickingService;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.script.IScriptService;

/**
 * This class a the class that represents the modelio application.
 * <p>
 * It is a singleton. It can be accessed by <code>Modelio.getInstance()</code>
 * method.
 */
@objid ("a1f0c48c-ed20-11dd-a469-0014222a9f79")
@Deprecated
public abstract class Modelio {
    @objid ("c714458f-6bf4-11e0-a371-001ec947cd2a")
    protected static Modelio instance;

    /**
     * Get the audit service.
     * 
     * @return the audit service.
     * 
     * @deprecated Use {@link IModelioServices#getAuditService()}
     */
    @objid ("44bc2a8e-6bf5-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract IAuditService getAuditService();

    /**
     * Get the context of the Modelio application.
     * 
     * @return the Modelio context.
     * @deprecated Use {@link IModuleContext#getModelioContext()}
     */
    @objid ("37df3828-64d9-11e0-b650-001ec947cd2a")
    @Deprecated
    public abstract IModelioContext getContext();

    /**
     * Get the diagram manipulation service.
     * 
     * @return the diagram service.
     * @deprecated Use {@link IModelioServices#getDiagramService()}
     */
    @objid ("d99a86aa-6bf4-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract IDiagramService getDiagramService();

    /**
     * Get the text editor management service.
     * 
     * @return the edition service.
     * @deprecated Use {@link IModelioServices#getEditionService()}
     */
    @objid ("218fdb67-6c25-11e0-b589-002564c97630")
    @Deprecated
    public abstract IEditionService getEditionService();

    /**
     * Get the service to import/export model elements.
     * 
     * @return the exchange service.
     * @since 2.2
     * @deprecated Use @link {@link IModelioServices#getExchangeService()}
     */
    @objid ("1483333b-9516-11e1-a83f-002564c97630")
    @Deprecated
    public abstract IExchangeService getExchangeService();

    /**
     * Get the service to get the image of an element.
     * 
     * @return the image service.
     * @deprecated Use {@link IModelioServices#getImageService()}
     */
    @objid ("76263473-e3dd-11dd-a49b-0014222a9f79")
    @Deprecated
    public abstract IImageService getImageService();

    /**
     * Get the modelio application instance.
     * 
     * @return the singleton instance of Modelio
     * @deprecated the former services of Modelio instance are available on IModule and IModulecontext
     */
    @objid ("af34c0d6-6bf7-11e0-a371-001ec947cd2a")
    @Deprecated
    public static Modelio getInstance() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return instance;
    }

    /**
     * Get the log service for modules.
     * 
     * @return the log service.
     * @deprecated Use {@link IModelioServices#getLogService()}
     */
    @objid ("76ea52de-6bf5-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract ILogService getLogService();

    /**
     * Get the metamodel management service.
     * 
     * @return the metamodel service.
     * @deprecated Use {@link IModelioServices#getMetamodelService()}
     */
    @objid ("f41fc31e-723e-11e0-89ea-002564c97630")
    @Deprecated
    public abstract IMetamodelService getMetamodelService();

    /**
     * Get the model component management service.
     * 
     * @return the model component service.
     * @deprecated Use {@link IModelioServices#getModelComponentService()}
     */
    @objid ("ee8ca0e6-6bf4-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract IModelComponentService getModelComponentService();

    /**
     * Get the model manipulation service.
     * 
     * @return the model manipulation service.
     * @deprecated Use {@link IModelioServices#getModelManipulationService()}
     */
    @objid ("8e654e80-820c-11e0-ae7b-002564c97630")
    @Deprecated
    public abstract IModelManipulationService getModelManipulationService();

    /**
     * Get the project session.
     * 
     * @return the current project session.
     * @deprecated Use {@link IModuleContext#getModelingSession()}
     */
    @objid ("00c8ec8e-6bf5-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract IModelingSession getModelingSession();

    /**
     * Get the module management service.
     * 
     * @return the module management service.
     * @deprecated Use {@link IModelioServices#getModuleService()}
     */
    @objid ("184f7457-6bf5-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract IModuleService getModuleService();

    /**
     * Get the navigation service.
     * <p>
     * The navigation service allow to force selection in all the view/dialog
     * that are registered as NavigationListener.
     * 
     * @return the navigation service.
     * @deprecated Use {@link IModelioServices#getNavigationService()}
     */
    @objid ("37df382c-64d9-11e0-b650-001ec947cd2a")
    @Deprecated
    public abstract INavigationService getNavigationService();

    /**
     * Get the service to export/apply patterns.
     * 
     * @return the pattern service
     * @since 3.4
     * @deprecated Use {@link IModelioServices#getPatternService()}
     */
    @objid ("085fbccf-df54-454d-9324-9b8c6c9310d0")
    @Deprecated
    public abstract IPatternService getPatternService();

    /**
     * Get the element picking service.
     * 
     * @return the element picking service.
     * @deprecated Use {@link IModelioServices#getPickingService()}
     */
    @objid ("3dd313e6-6bf5-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract IPickingService getPickingService();

    /**
     * Get the service to get the image of an element in the explorer.
     * 
     * @return the image service.
     * @deprecated Use {@link IModuleContext#getJythonEngine()}
     */
    @objid ("86358e14-6bf6-11e0-a371-001ec947cd2a")
    @Deprecated
    public abstract IScriptService getScriptService();

    /**
     * Get the service implementing the given interface.
     * <p>
     * Returns <code>null</code> if no such service is registered.
     * 
     * @param serviceInterface the service interface.
     * @return the registered service.
     * @deprecated Use {@link IModelioServices#getService(Class)}
     */
    @objid ("b34e1f8c-370d-45f1-a231-e24be780bf4a")
    @Deprecated
    public abstract <I> I getService(Class<I> serviceInterface);

}
