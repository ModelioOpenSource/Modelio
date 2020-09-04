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

package org.modelio.mda.infra.service.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.diagram.tools.IDiagramTool;
import org.modelio.api.module.IModule;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.command.ActionLocation;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.api.module.license.ILicenseInfos;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.api.module.propertiesPage.IModulePropertyPanel;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.mda.infra.service.IRTModuleController;
import org.modelio.mda.infra.service.IRTModuleListener;
import org.modelio.mda.infra.service.contributions.WizardContribution;
import org.modelio.mda.infra.service.impl.common.ModuleConfiguration;
import org.modelio.mda.infra.service.impl.common.PeerModuleConfiguration;
import org.modelio.mda.infra.service.impl.controller.RTModuleController;
import org.modelio.mda.infra.service.impl.controller.load.ModuleLoader;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

@objid ("bb7d18a6-71bf-4d80-a7b1-9c20a9e7e37f")
class RTModule implements IRTModuleAccess {
    @objid ("02a8d537-99e0-11e1-b1e0-001ec947c8cc")
    private ModuleRuntimeState runtimeState = ModuleRuntimeState.Loaded;

    @objid ("8d6e9ab4-eaeb-4166-ab5e-98d9cea5b1d6")
    private int priority = Integer.MAX_VALUE;

    @objid ("b78d572d-0e9d-11e0-8636-001ec947cd2a")
    private Map<ActionLocation, List<IModuleAction>> actionsRegistry;

    @objid ("2faae81d-5aea-4757-96f2-640170b13a48")
    private List<DiagramToolDescriptor> diagramTools;

    @objid ("883deeea-dc80-464c-b27e-21304e6c1835")
    private List<DiagramCustomizationDescriptor> diagramCustomizations;

    @objid ("14877ba4-3d14-436f-8deb-29cb8f09957c")
    private List<WizardContribution> wizardContributions;

    @objid ("39a165df-929a-4ac1-b125-3e4c3ea47620")
    private List<IModulePropertyPanel> propertyPanels;

    @objid ("9913e429-1db4-4660-9a29-d1585057b984")
    private IModule iModule;

    @objid ("89a3d21e-4579-46f6-8623-310d0240c0c9")
    private GModule gmodule;

    @objid ("2b4aedc7-25ed-43f7-9de1-0dc1eed4bd06")
    private final List<ISmMetamodelFragment> mmFrags = new ArrayList<>();

    @objid ("775231cc-6136-485f-bf77-de4053c6e46c")
    private List<IRTModule> usedModules;

    @objid ("f776a6ec-76b9-4c02-b37c-fb07bcee27c4")
    private ModuleException downError;

    @objid ("49d21490-9bc1-4230-b442-8c84721db46b")
    private final IRTModuleController controller;

    @objid ("478b0c48-1d25-4d49-bd34-6b7eb6119e43")
    private List<IRTModule> requiredModules;

    @objid ("83b859d5-96df-4b0b-a777-a584a3a10698")
    private ClassLoader classLoader;

    @objid ("be00a325-fc26-4b3e-868a-b9b1108bdf56")
    private List<IRTModuleListener> moduleListeners = new CopyOnWriteArrayList<>();

    @objid ("12bb423b-ac31-456c-9c3b-295fbefa64a0")
    private IModuleUserConfiguration moduleUserConfiguration;

    @objid ("2774eabf-55cb-424e-8b4a-5eebb0a461f7")
    private IModuleAPIConfiguration moduleApiConfiguration;

    @objid ("d4ace222-1ee2-437c-9903-94973a44c7c6")
    private final IModuleRegistryAccess moduleRegistry;

    /**
     * Modules requiring this module.
     */
    @objid ("01abbf23-5509-4df2-bc09-524c4b034825")
    private List<IRTModule> moduleUsers;

    /**
     * Modules optionally using this module.
     */
    @objid ("a8796e36-f9fa-46a9-b02e-1ed34acc40ea")
    private List<IRTModule> moduleOptionalUsers;

    /**
     * Returns the collection of {@link IModuleAction} associated with passed location.
     * 
     * @param location the location for which actions are to be returned.
     * @return the collection of {@link IModuleAction} associated with passed location.
     */
    @objid ("b7921bd8-0e9d-11e0-8636-001ec947cd2a")
    @Override
    public List<IModuleAction> getActions(ActionLocation location) {
        if (this.actionsRegistry.containsKey(location)) {
            return this.actionsRegistry.get(location);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Register a module action for the contextual popupmenu(s) of the application.
     * 
     * @param location The action insertion point in the popupmenu (see {@link ActionLocation})
     * @param action Action to store
     */
    @objid ("a047d2c5-479d-11df-a533-001ec947ccaf")
    @Override
    public void registerAction(ActionLocation location, IModuleAction action) {
        List<IModuleAction> actionsList = this.actionsRegistry.get(location);
        // Register the command in the moduleActions registry
        actionsList.add(action);
    }

    @objid ("aa1fa07b-3f01-49f9-9138-33d937183699")
    @Override
    public final void registerCustomizedTool(String id, IDiagramTool diagramCommand) {
        this.diagramTools.add(new DiagramToolDescriptor(id, diagramCommand));
    }

    @objid ("f3dfe929-371b-4cce-bcd1-9d42ae8d4b4a")
    @Override
    public final void registerDiagramCustomization(Stereotype stereotype, Class<? extends AbstractDiagram> baseDiagramClass, IDiagramCustomizer customizer) {
        this.diagramCustomizations.add(new DiagramCustomizationDescriptor(stereotype, baseDiagramClass, customizer));
    }

    @objid ("3291a949-c2d8-43af-9e8e-1f99b12d1240")
    @Override
    @SuppressWarnings ("unchecked")
    public <I> I instanciateExternProcessor(String className, Class<I> clazz, Object... initargs) {
        try {
            // Look for a standard class
            Class<I> cls = (Class<I>) Class.forName(className, true, getIModule().getClass().getClassLoader());
        
            Class<?>[] initargsTypes = new Class[initargs.length];
            for (int i = 0; i < initargs.length; i++) {
                initargsTypes[i] = initargs[i].getClass();
            }
        
            Constructor<?> constr = cls.getConstructors()[0];
            return (I) constr.newInstance(initargs);
        } catch (ClassNotFoundException | ClassCastException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            MdaInfra.LOG.warning(e.toString());
        }
        return null;
    }

    @objid ("b300704c-a26a-4940-b32f-ff9dae0dd308")
    @Override
    public void registerWizardContribution(ContributorCategory category, IWizardContributor contributor) {
        this.wizardContributions.add(new WizardContribution(category, contributor));
    }

    @objid ("6132acde-d1ed-4257-8b90-b77e8eb16a73")
    @Override
    public void unregisterWizardContribution(ContributorCategory category, IWizardContributor contributor) {
        this.wizardContributions.remove(new WizardContribution(category, contributor));
    }

    @objid ("6656d131-48a5-4cc6-ad9e-c5fb969aaaf0")
    @Override
    public List<WizardContribution> getWizardContributions() {
        return this.wizardContributions;
    }

    /**
     * Return the defined property panels
     * 
     * @return The collection of property panels
     */
    @objid ("c0e4c841-da08-40ff-9f85-5ca1dad1423c")
    @Override
    public List<IModulePropertyPanel> getPropertyPanels() {
        return this.propertyPanels;
    }

    /**
     * Get ids of all defined diagram tools.
     * 
     * @return the toolIds.
     */
    @objid ("762d4fa9-9dc4-42bd-93cd-a16d6c7562c6")
    @Override
    public final List<DiagramToolDescriptor> getDiagramTools() {
        return this.diagramTools;
    }

    @objid ("b84b3e8b-6e3f-4f65-8494-8ee3c15ca3ae")
    @Override
    public final List<DiagramCustomizationDescriptor> getDiagramCustomizations() {
        return this.diagramCustomizations;
    }

    @objid ("0236648a-99e0-11e1-b1e0-001ec947c8cc")
    @Override
    public ModuleRuntimeState getState() {
        return this.runtimeState;
    }

    @objid ("02366491-99e0-11e1-b1e0-001ec947c8cc")
    @Override
    public void setState(ModuleRuntimeState newState) {
        this.runtimeState = newState;
    }

    @objid ("6a8d552f-e55f-4b45-9ee4-c3eaa092f085")
    public RTModule(GModule gModule, IModuleRegistryAccess moduleRegistry) {
        this.gmodule = gModule;
        this.moduleRegistry = moduleRegistry;
        this.controller = new RTModuleController(this, moduleRegistry);
        
        setGModule(gModule);
    }

    @objid ("bb8632e0-0621-4ea9-b388-18b29a8fcb86")
    @Override
    public String getName() {
        return this.iModule.getName();
    }

    @objid ("e41220e2-e736-4a10-9c7c-78182bd2dfc9")
    @Override
    public IModuleUserConfiguration getConfiguration() {
        return this.moduleUserConfiguration;
    }

    @objid ("23189bec-c7f1-43a3-860d-0e8e1b1f7ac4")
    @Override
    public String getDescription() {
        return this.iModule.getDescription();
    }

    @objid ("b3d5ba33-36b5-4bd2-8263-d237ef5cb9c1")
    @Override
    public String getLabel() {
        return this.iModule.getLabel();
    }

    @objid ("48233090-ef38-45dd-860d-763dedeb3b59")
    @Override
    public Version getVersion() {
        return this.iModule.getVersion();
    }

    @objid ("1b5d19d7-6649-49ee-9295-e532458505d3")
    @Override
    public ILicenseInfos getLicenseInfos() {
        return this.iModule.getLicenseInfos();
    }

    @objid ("0054f7dd-81cb-4c95-84c5-d413f8d270e4")
    @Override
    public IModule getIModule() {
        return this.iModule;
    }

    @objid ("67b2d2bb-fe9e-4a26-82f4-7119072cbe90")
    @Override
    public String getLabel(String key) {
        return this.iModule.getModuleContext().getI18nSupport().getString(key);
    }

    @objid ("a17f0429-d185-40ca-82a6-e4a20f054aef")
    @Override
    public Image getModuleImage() {
        return this.iModule.getModuleImage();
    }

    @objid ("24d5834f-5e92-488a-8bcd-0a5fd6e68093")
    @Override
    public ModuleComponent getModel() {
        return this.iModule.getModuleContext().getModel();
    }

    @objid ("1ca9b27c-05d2-4af0-a3af-26771b437445")
    @Override
    public IPeerModule getPeerModule() {
        return this.iModule.getPeerModule();
    }

    @objid ("ec9c27ad-5be0-4b52-9482-3ce6cfe93026")
    @Override
    public String getModuleImagePath() {
        return this.iModule.getModuleImagePath();
    }

    @objid ("5c3d05be-599c-4cf6-a41b-84939c8ef4e9")
    @Override
    public ModuleException getDownError() {
        return this.downError;
    }

    @objid ("f5ddea42-fc9d-46a5-b4cc-bdf77844d736")
    @Override
    public GModule getGModule() {
        return this.gmodule;
    }

    /**
     * The module configuration for API use.
     * 
     * @return The module configuration for API use.
     */
    @objid ("5c33aaf2-b361-4869-a6e5-3ab20788237b")
    @Override
    public IModuleAPIConfiguration getModuleApiConfiguration() {
        return this.moduleApiConfiguration;
    }

    @objid ("5036a6b9-163d-40c7-80a8-f2af0dbd499a")
    @Override
    public List<IRTModule> getRequiredDependencies() {
        if (this.requiredModules == null) {
            computeDependencies();
        }
        return this.requiredModules;
    }

    @objid ("e2c7549c-3570-4c31-a36e-9e97ffc6b64c")
    @Override
    public List<IRTModule> getOptionalDependencies() {
        if (this.usedModules == null) {
            computeDependencies();
        }
        return this.usedModules;
    }

    @objid ("fa8ca156-d95f-4103-a393-3a59a0a649dd")
    @Override
    public IRTModuleController getController() {
        return this.controller;
    }

    @objid ("99663ae2-079a-46f8-bcfc-3e880b076c43")
    @Override
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    @objid ("ae0d15aa-f2ae-4cd1-8249-0787447b6116")
    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @objid ("dc290614-9ff9-42c3-9519-e777d5ce4e72")
    @Override
    public List<ISmMetamodelFragment> getMetamodelFragments() {
        return this.mmFrags;
    }

    @objid ("1147cc47-32c1-4f16-9100-24e9eae62152")
    @Override
    public void setDownError(ModuleException downError) {
        this.downError = downError;
    }

    @objid ("832dff21-5d0f-4943-9f20-02e9e7ef777a")
    @Override
    public List<IRTModuleListener> getListeners() {
        return this.moduleListeners;
    }

    @objid ("ecaad02a-ffda-40c1-9f82-dab73f3282f5")
    @Override
    public void setIModule(IModule imodule) {
        this.iModule = imodule;
    }

    @objid ("38509f4a-96b9-4aac-b571-1189ee3822d2")
    @Override
    public final void setGModule(GModule gModule) {
        // Record the new module
        this.gmodule = gModule;
        this.runtimeState = ModuleRuntimeState.Loaded;
        
        // Things to reset
        this.classLoader = null;
        this.downError = null;
        resetDynamicModel();
        
        // Prepare the ModuleConfiguration objects for the module.
        IModuleHandle moduleHandle = gModule.getModuleHandle();
        this.moduleUserConfiguration = new ModuleConfiguration(gModule, moduleHandle.getResourcePath(), moduleHandle.getDocPaths(), moduleHandle.getStylePaths());
        this.moduleApiConfiguration = new PeerModuleConfiguration(gModule, moduleHandle.getResourcePath(), moduleHandle.getDocPaths());
        
        resetDependencies();
        
        // Instantiate temporary fake IModule
        this.iModule = new ModuleLoader(this).createFakeModule();
    }

    @objid ("e2895d64-8d49-4da7-b826-33b6f2280a21")
    @Override
    public void resetDynamicModel() {
        this.diagramTools = new ArrayList<>();
        this.diagramCustomizations = new ArrayList<>();
        this.wizardContributions = new ArrayList<>();
        this.propertyPanels = new ArrayList<>();
        
        // Initialize the moduleActions collections.
        this.actionsRegistry = new HashMap<>();
        for (ActionLocation actionLocation : ActionLocation.values()) {
            this.actionsRegistry.put(actionLocation, new ArrayList<IModuleAction>());
        }
    }

    @objid ("17a4a161-3f8e-404b-9420-3b478e4c38da")
    @Override
    public String toString() {
        if (this.gmodule != null) {
            return this.gmodule.toString() + " runtime module";
        } else {
            return super.toString();
        }
    }

    /**
     * Trigger refresh of required & optional dependencies from the GModule underneath.
     */
    @objid ("aed31490-c7bb-493f-9de1-feeedd813aac")
    @Override
    public final void resetDependencies() {
        this.requiredModules = null;
        this.usedModules = null;
    }

    @objid ("3a545164-16ab-48cc-a666-420f7d1c2ff8")
    @Override
    public void resetModuleUsers() {
        this.moduleUsers = null;
        this.moduleOptionalUsers = null;
    }

    @objid ("dc35235c-a2bf-4cb1-bd1b-2ca318c7ae5b")
    private void initModuleUsers() {
        ArrayList<IRTModule> newUsers = new ArrayList<>();
        ArrayList<IRTModule> newOptionalUsers = new ArrayList<>();
        
        if (this.gmodule != null) {
            for (IRTModule m : new ArrayList<>(this.moduleRegistry.getModules())) {
                // Note: calling m.getRequiredDependencies() may result of this.resetModuleUsers() being called
        
                if (m.getRequiredDependencies().contains(this)) {
                    newUsers.add(m);
                }
        
                if (m.getOptionalDependencies().contains(this)) {
                    newOptionalUsers.add(m);
                }
            }
        }
        
        // Set fields last to avoid concurrent field reset by resetModuleUsers()
        this.moduleUsers = newUsers;
        this.moduleOptionalUsers = newOptionalUsers;
    }

    @objid ("44c30332-0ec2-4d31-b365-555641ab7c4f")
    @Override
    public List<IRTModule> getModuleUsers() {
        if (this.moduleUsers == null) {
            initModuleUsers();
        }
        return this.moduleUsers;
    }

    @objid ("9d854358-bed7-4281-88f7-10c6ad326814")
    @Override
    public List<IRTModule> getModuleOptionalUsers() {
        if (this.moduleOptionalUsers == null) {
            initModuleUsers();
        }
        return this.moduleOptionalUsers;
    }

    @objid ("e7c8df63-4e2c-4cff-a3e2-871e27172add")
    @Override
    public int getPriority() {
        return this.priority;
    }

    @objid ("2f1e6bf8-9f0c-4546-b308-dd200d382f2f")
    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Compute required & optional dependencies from the GModule underneath.
     */
    @objid ("9d505c0d-6149-48ad-8306-74cd00600290")
    private void computeDependencies() {
        if (this.gmodule != null) {
            // Recompute required and used modules
            List<GModule> requiredGModules = ModuleResolutionHelper.getRequiredGModules(this.gmodule, this.gmodule.getProject());
            List<GModule> optionalGModules = ModuleResolutionHelper.getWeakDependenciesGModules(this.gmodule, this.gmodule.getProject());
        
            List<IRTModule> newRequired = new ArrayList<>(requiredGModules.size());
            List<IRTModule> newOptional = new ArrayList<>(optionalGModules.size());
        
            for (GModule strongDependency : requiredGModules) {
                IRTModule rtDep = this.moduleRegistry.loadRTModule(strongDependency);
                newRequired.add(rtDep);
                rtDep.resetModuleUsers();
            }
        
            for (GModule weakDependency : optionalGModules) {
                IRTModule rtDep = this.moduleRegistry.loadRTModule(weakDependency);
                newOptional.add(rtDep);
                rtDep.resetModuleUsers();
            }
        
            this.requiredModules = newRequired;
            this.usedModules = newOptional;
        } else {
            this.requiredModules = Collections.emptyList();
            this.usedModules = Collections.emptyList();
        }
    }

}
