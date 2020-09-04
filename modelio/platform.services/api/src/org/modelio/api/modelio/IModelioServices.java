/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.api.modelio.module.IModuleService;
import org.modelio.api.modelio.navigation.INavigationService;
import org.modelio.api.modelio.pattern.IPatternService;
import org.modelio.api.modelio.picking.IPickingService;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.ui.swt.IUiToolkit;

/**
 * Set of application-level services for modules.
 * An IModelioServices instance can be obtained by {@link IModuleContext#getModelioServices()}
 */
@objid ("e0ebf56e-7261-4bdd-9d12-acc443670b8e")
public interface IModelioServices {
    /**
     * Get the audit service.
     * 
     * @return the audit service.
     * @since 3.5
     */
    @objid ("9ecbb033-90da-49d9-9434-6d68550d3953")
    IAuditService getAuditService();

    /**
     * Get the context of the Modelio application.
     * 
     * @return the Modelio context.
     * @since 3.5
     */
    @objid ("84748384-22bf-4f31-9cfd-dad7fbc6ef06")
    IDiagramService getDiagramService();

    /**
     * Get the text editor management service.
     * 
     * @return the edition service.
     * @since 3.5
     */
    @objid ("1518102b-8368-488d-96bd-fa0de0d3048e")
    IEditionService getEditionService();

    /**
     * Get the service to import/export model elements.
     * 
     * @return the exchange service.
     * @since 3.5
     */
    @objid ("5dd2d097-f4e8-425f-8731-de14aca9960b")
    IExchangeService getExchangeService();

    /**
     * Get the service to get the image of an element.
     * 
     * @return the image service.
     * @since 3.5
     */
    @objid ("bb31d399-9ed8-4c3d-8962-379025b0f349")
    IImageService getImageService();

    /**
     * Get the log service for modules.
     * 
     * @return the log service.
     * @since 3.5
     */
    @objid ("a954c78e-d9bf-4788-b71f-a7376290cdfd")
    ILogService getLogService();

    /**
     * Get the metamodel management service.
     * 
     * @return the metamodel service.
     * @since 3.5
     */
    @objid ("5d2d1033-a893-42ee-8ff7-e3ccf656e9bf")
    IMetamodelService getMetamodelService();

    /**
     * Get the model component management service.
     * 
     * @return the model component service.
     * @since 3.5
     */
    @objid ("86adcb76-7fe0-46bb-9e87-0b3bb5ed5308")
    IModelComponentService getModelComponentService();

    /**
     * Get the model manipulation service.
     * 
     * @return the model manipulation service.
     * @since 3.5
     */
    @objid ("40229d34-6bce-45e4-b69a-ee95837fcc47")
    IModelManipulationService getModelManipulationService();

    /**
     * Get the module management service.
     * 
     * @return the module management service.
     * @since 3.5
     */
    @objid ("6f06e42f-2bef-4810-ae29-f923f909b8f0")
    IModuleService getModuleService();

    /**
     * Get the navigation service.
     * <p>
     * The navigation service allow to force selection in all the view/dialog
     * that are registered as NavigationListener.
     * 
     * @return the navigation service.
     * @since 3.5
     */
    @objid ("413fdcdf-e0b0-4c76-ad1d-082c22aebe2d")
    INavigationService getNavigationService();

    /**
     * Get the service to export/apply patterns.
     * 
     * @return the pattern service
     * @since 3.5
     */
    @objid ("5185f0ad-083f-42e4-bb87-20c9edb28842")
    IPatternService getPatternService();

    /**
     * Get the element picking service.
     * 
     * @return the element picking service.
     * @since 3.5
     */
    @objid ("2b1a4496-e7b8-4f94-bddd-d515048f325e")
    IPickingService getPickingService();

    @objid ("f619081c-7dff-4c64-9355-97317af811bf")
    <I> I getService(Class<I> serviceInterface);

    /**
     * Get the GUI toolkit modules can use to create model or metamodel related widgets.
     * <p>
     * 
     * @return the GUI toolkit.
     * @since 3.7.1
     */
    @objid ("31eb3f27-145f-4dee-9deb-ac42ce8cb3e6")
    IUiToolkit getUiToolkit();

}
