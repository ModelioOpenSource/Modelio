/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.api.impl.services;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.api.impl.app.ScriptService;
import org.modelio.api.impl.app.navigation.NavigationService;
import org.modelio.api.impl.app.picking.PickingService;
import org.modelio.api.impl.audit.AuditService;
import org.modelio.api.impl.diagrams.DiagramService;
import org.modelio.api.impl.editor.EditionService;
import org.modelio.api.impl.exchange.ExchangeService;
import org.modelio.api.impl.log.LogService;
import org.modelio.api.impl.mc.ModelComponentService;
import org.modelio.api.impl.meta.MetamodelService;
import org.modelio.api.impl.model.ImageService;
import org.modelio.api.impl.model.ModelManipulationService;
import org.modelio.api.impl.model.SharedModelingSession;
import org.modelio.api.impl.module.ModuleContextFactoryImpl;
import org.modelio.api.impl.module.ModuleService;
import org.modelio.api.impl.pattern.PatternService;
import org.modelio.api.impl.swt.UiToolkit;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.IModelioServicesRegistry;
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
import org.modelio.api.module.context.log.ILogService;
import org.modelio.api.module.script.IScriptService;
import org.modelio.api.ui.swt.IUiToolkit;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vaudit.modelshield.ModelShield;

/**
 * This class is an E4 processor. This class establishes the link between the
 * api.impl service or API proxies and their corresponding Modelio
 * implementations.
 * The ModelioServices also initializes the ModuleContextFactory singleton
 * instance declared in Modelio mda.infra plugin.
 * The mda.infra module loader uses this ModuleContextFactoryImpl factory to
 * create IModuleContext instances for modules.
 * 
 * @author phv
 */
@objid ("93a2d68f-02f0-4698-978d-413380676c25")
public class ModelioServices implements IModelioServices, IModelioServicesRegistry {
    @objid ("0317acfe-1583-43d9-a91d-bb009f036055")
    @Inject
    private IEclipseContext eclipseContext;

    @objid ("5434f26b-ad43-4616-bae9-66d859eb36de")
    private boolean servicesInitialized;

    @objid ("12127771-76b4-4e10-ad5e-3caffff01928")
    private static ModelioServices instance;

    /**
     * Service map cache.
     * <ul>
     * <li>Lazily loaded when a service is needed.</li>
     * <li>Cleared on project close.</li>
     * </ul>
     */
    @objid ("cf1521c9-4fa1-4608-9f94-c3dfc450f3ea")
    private Map<Class<?>, Object> serviceMap = new HashMap<>();

    @objid ("6c88c71d-0dd4-45b6-9c42-ca7da663f4bd")
    private GProject gProject;

    /**
     * Called once at creation by injection mechanism (E4 processor execute)
     */
    @objid ("7cf066e0-a273-402b-8bca-8b8aec26bb60")
    @Execute
    void initialize(final IEclipseContext context) {
        this.eclipseContext = context;
        
        // This constructor must be called only once, this is ok here
        new ModuleContextFactoryImpl(this.eclipseContext).register();
        
        // Singleton instance
        ModelioServices.instance = this;
        
        // Register singleton into the context
        this.eclipseContext.set(IModelioServicesRegistry.class, this);
    }

    /**
     * @return the singleton instance.
     */
    @objid ("16f688ee-bc5c-492c-ad5d-85e5e1ebdeb7")
    public static ModelioServices getInstance() {
        return ModelioServices.instance;
    }

    @objid ("c6e5e56b-c0b3-401d-b112-77cbb7a979e9")
    @Override
    public IAuditService getAuditService() {
        return getService(IAuditService.class);
    }

    @objid ("46b834a3-107c-4436-8237-7f8e712aa0aa")
    @Override
    public IDiagramService getDiagramService() {
        return getService(IDiagramService.class);
    }

    @objid ("376b8ad2-8c4f-44bb-90c8-4d7c480c93b0")
    @Override
    public IEditionService getEditionService() {
        return getService(IEditionService.class);
    }

    @objid ("32af7449-8d47-4a9b-9f1b-0140f1e88ae6")
    @Override
    public IImageService getImageService() {
        return getService(IImageService.class);
    }

    @objid ("f96e0e1c-2590-44c4-a7d7-094a09eb0bc5")
    @Override
    public ILogService getLogService() {
        return getService(ILogService.class);
    }

    @objid ("a075d0b2-733d-4124-9cfc-9e7e3ac44345")
    @Override
    public IModuleService getModuleService() {
        return getService(IModuleService.class);
    }

    @objid ("280794cc-e8ae-4421-8c73-39009847a494")
    @Override
    public INavigationService getNavigationService() {
        return getService(INavigationService.class);
    }

    @objid ("e244ac16-daeb-4b3e-bcff-76c024cc8274")
    @Override
    public IPickingService getPickingService() {
        return getService(IPickingService.class);
    }

    @objid ("85383daf-24e1-4450-b65f-61e7f46bd76f")
    @Override
    public IModelComponentService getModelComponentService() {
        return getService(IModelComponentService.class);
    }

    @objid ("4b4016dd-703d-429d-93e9-39d2918a2577")
    @Override
    public IMetamodelService getMetamodelService() {
        return getService(IMetamodelService.class);
    }

    @objid ("5e427c93-5e87-43bd-8de7-92f9d799635e")
    @Override
    public IModelManipulationService getModelManipulationService() {
        return getService(IModelManipulationService.class);
    }

    @objid ("75af9bb5-76c3-4fd0-90ef-d5a4b72b6259")
    @Override
    public IExchangeService getExchangeService() {
        return getService(IExchangeService.class);
    }

    @objid ("f8417e88-31af-4ff0-a31e-33fa71612dc0")
    @Override
    public IPatternService getPatternService() {
        return getService(IPatternService.class);
    }

    @objid ("e8affc4c-f612-498e-8736-8cb9ea6dd3a0")
    @SuppressWarnings ("unchecked")
    @Override
    public synchronized <I> I getService(final Class<I> serviceInterface) {
        if (!this.servicesInitialized) {
            initializeServices();
        }
        return (I) this.serviceMap.get(serviceInterface);
    }

    /**
     * On project closing , clear the service cache
     * 
     * @param closedProject the project being closed.
     */
    @objid ("6b190cd8-644a-4e6b-8d3b-3e938d0a282b")
    @Inject
    @Optional
    public synchronized void onProjectClosed(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) final GProject closedProject) {
        this.serviceMap = new HashMap<>();
        this.servicesInitialized = false;
        this.gProject = null;
    }

    /**
     * On project opening clear the service cache
     * 
     * @param newProject the project being opened.
     */
    @objid ("9c59d5ce-bfdd-4e4c-a938-e6123da56fb4")
    @Inject
    @Optional
    void onProjectOpening(@EventTopic(ModelioEventTopics.PROJECT_OPENING) final GProject newProject) {
        this.servicesInitialized = false;
        this.gProject = newProject;
    }

    @objid ("c647d7c7-edeb-492a-abd8-d881be8fcbee")
    @Override
    public synchronized <I> void registerService(final Class<I> serviceInterface, final I service) {
        this.serviceMap.put(serviceInterface, service);
    }

    /**
     * C'tor to forbid external instantiation
     */
    @objid ("312c325d-fe5a-4eba-abbc-6c42585b898b")
    ModelioServices() {
    }

    @objid ("611abdc6-6a92-48c4-879b-5203115f3efa")
    private void initializeServices() {
        final IProjectService projectService = this.eclipseContext.get(IProjectService.class);
        final org.modelio.mda.infra.service.IModuleManagementService coreModuleService = this.eclipseContext.get(org.modelio.mda.infra.service.IModuleManagementService.class);
        
        IModelingSession modelingSession = new SharedModelingSession(this.gProject, this.eclipseContext.get(IMModelServices.class));
        this.serviceMap.put(IModelingSession.class, modelingSession);
        
        IAuditService auditService = new AuditService(this.eclipseContext.get(ModelShield.class), this.eclipseContext.get(org.modelio.audit.service.IAuditService.class));
        this.serviceMap.put(IAuditService.class, auditService);
        
        IDiagramService diagramService = new DiagramService(this.eclipseContext);
        this.serviceMap.put(IDiagramService.class, diagramService);
        
        IEditionService editionService = new EditionService(this.eclipseContext.get(IModelioEventService.class), this.eclipseContext);
        this.serviceMap.put(IEditionService.class, editionService);
        
        IExchangeService exchangeService = new ExchangeService(this.eclipseContext, this.gProject.getSession().getMetamodel());
        this.serviceMap.put(IExchangeService.class, exchangeService);
        
        IImageService imageService = new ImageService(this.gProject.getSession().getMetamodel());
        this.serviceMap.put(IImageService.class, imageService);
        
        ILogService logService = new LogService(null);
        this.serviceMap.put(ILogService.class, logService);
        
        IModelManipulationService modelManipulationService = new ModelManipulationService();
        this.serviceMap.put(IModelManipulationService.class, modelManipulationService);
        
        INavigationService navigationService = ContextInjectionFactory.make(NavigationService.class, this.eclipseContext);
        this.serviceMap.put(INavigationService.class, navigationService);
        
        IPickingService pickingService = new PickingService(this.eclipseContext.get(IModelioPickingService.class));
        this.serviceMap.put(IPickingService.class, pickingService);
        
        IModelComponentService modelComponentService = new ModelComponentService(projectService, this.gProject, this.eclipseContext.get(org.modelio.mda.infra.service.IModuleService.class));
        this.serviceMap.put(IModelComponentService.class, modelComponentService);
        
        IScriptService scriptService = new ScriptService(coreModuleService, projectService);
        this.serviceMap.put(IScriptService.class, scriptService);
        
        IModuleService moduleService = new ModuleService(coreModuleService);
        this.serviceMap.put(IModuleService.class, moduleService);
        
        IPatternService patternService = new PatternService(this.eclipseContext);
        this.serviceMap.put(IPatternService.class, patternService);
        
        IMetamodelService metamodelService = new MetamodelService(this.gProject.getSession().getMetamodel());
        this.serviceMap.put(IMetamodelService.class, metamodelService);
        
        IUiToolkit uiToolkit = new UiToolkit(this, this.gProject, this.eclipseContext );
        this.serviceMap.put(IUiToolkit.class, uiToolkit);
        
        this.servicesInitialized = true;
    }

    @objid ("a8c35b3b-7e0b-4a66-b629-ff31278242c8")
    @Override
    public IUiToolkit getUiToolkit() {
        return getService(IUiToolkit.class);
    }

    @objid ("7d91c409-024f-434d-b981-effdd0c12d29")
    @Override
    public synchronized <I> void unregisterService(final Class<I> serviceInterface) {
        this.serviceMap.remove(serviceInterface);
    }

}
