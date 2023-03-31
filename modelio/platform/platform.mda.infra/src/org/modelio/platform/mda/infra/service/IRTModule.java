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
package org.modelio.platform.mda.infra.service;

import java.util.Collection;
import java.util.List;
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
import org.modelio.gproject.parts.module.GModule;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.service.contributions.WizardContribution;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;

/**
 * Represents the runtime module .
 * <p>
 * This is a decorator around the {@link IModule} module main class.
 */
@objid ("0b2b294b-5b6b-4f97-a949-d55206272bc7")
public interface IRTModule {
    /**
     * Returns the collection of {@link IModuleAction} associated with passed location.
     * @param location the location for which actions are to be returned.
     * @return the collection of {@link IModuleAction} associated with passed location.
     */
    @objid ("45f7535b-65cd-11e0-b0ca-001ec947cd2a")
    List<IModuleAction> getActions(ActionLocation location);

    /**
     * Get the configuration associated to this module.
     * @return the module configuration.
     */
    @objid ("287cc5f6-be23-48cf-96f2-bdb2dd36c745")
    IModuleUserConfiguration getConfiguration();

    /**
     * Used to return the module description.
     * @return The module description
     */
    @objid ("b397dcbe-a57c-4095-94d7-2b9b613d8c72")
    String getDescription();

    /**
     * Get all defined diagram custommizations.
     * @return the toolIds.
     * @since 2.2.1
     */
    @objid ("e2451778-d148-4ae8-a844-4b8f1d595e29")
    List<DiagramCustomizationDescriptor> getDiagramCustomizations();

    /**
     * Get all defined diagram tools.
     * @return the toolIds.
     * @since 2.2.1
     */
    @objid ("95bb2bb4-dcc8-440e-97e6-142a6ef06048")
    List<DiagramToolDescriptor> getDiagramTools();

    /**
     * @return the creation wizard contributions.
     */
    @objid ("6311e30d-8d61-45f4-9d7e-5d56b94c942d")
    List<WizardContribution> getWizardContributions();

    /**
     * For a broken or incompatible module, returns the exception that caused the module loading fail.
     * <p>
     * The exception message is expected to be directly displayed to the user. So the message has to be translated and as user friendly as possible
     * @return the break cause or null if the module is not broken.
     */
    @objid ("3f78af6e-4413-4c46-a57c-405019f10862")
    ModuleException getDownError();

    /**
     * Get the low level module.
     * @return the GModule
     */
    @objid ("c95df398-87c6-43b6-afcf-8243bf63092e")
    GModule getGModule();

    /**
     * @return the wrapped module main class instance.
     */
    @objid ("39a44507-1601-4f58-82a4-5e17390511e6")
    IModule getIModule();

    /**
     * Get the module label that is displayed in dialog boxes and other GUIU parts.
     * @return The module label.
     */
    @objid ("8f4d773e-0420-400a-bb63-880b45b3960e")
    String getLabel();

    /**
     * Get the translated label for a label key.
     * @param id a label key
     * @return the translated label
     */
    @objid ("0c2059ab-950c-4748-ac39-1aa22871c0e2")
    String getLabel(String id);

    /**
     * @return the license infos
     */
    @objid ("3761bc04-1bb6-4087-85f1-5455ca11b347")
    ILicenseInfos getLicenseInfos();

    /**
     * @return the module model component
     */
    @objid ("1dc73620-2192-42c8-8bf3-55679e491c72")
    ModuleComponent getModel();

    /**
     * @return the module image
     */
    @objid ("ba1f2e26-3707-47b0-b6e3-684327e132d9")
    Image getModuleImage();

    /**
     * @return the module image path
     */
    @objid ("8317e6d2-2f1e-4556-9614-bd17a40c26c6")
    String getModuleImagePath();

    /**
     * Used to return the module name.
     * <p>
     * <p>
     * The module name corresponds to the name of the module, as defined in the <i>MDA Designer<i> tool.
     * @return The module name
     */
    @objid ("7d59d674-06e9-406c-8f08-77593db693ac")
    String getName();

    /**
     * @return the module API interface
     */
    @objid ("c15b86d3-254b-4b42-a0e0-004911e12ebc")
    IPeerModule getPeerModule();

    /**
     * Return the defined property panels
     * @return The collection of property panels
     */
    @objid ("a2f280ad-c994-4ef4-9234-81df89611597")
    Collection<IModulePropertyPanel> getPropertyPanels();

    /**
     * Returns current runtime state of this module.
     * @return the current runtime state of this module.
     */
    @objid ("0cf32e9f-99de-11e1-b1e0-001ec947c8cc")
    ModuleRuntimeState getState();

    /**
     * Used to return the module version.
     * @return The module version
     */
    @objid ("702796b6-c0a8-48da-922a-4ae0f2eddf24")
    Version getVersion();

    /**
     * Instantiate a matrix external processor.
     * @param <I> the required type
     * @param className the full class name
     * @param clazz the required type ?
     * @param initargs the constructor arguments
     * @return the processor instance
     */
    @objid ("4b8ff81b-0d93-4388-87e9-1a8cbf5fbf9c")
    <I> I instanciateExternProcessor(String className, Class<I> clazz, Object... initargs);

    /**
     * @return the module controller.
     */
    @objid ("21e43549-83d9-4883-b84c-ce20a1c230c4")
    IRTModuleController getController();

    /**
     * @return the metamodel fragments provided by the module.
     */
    @objid ("d7d4a9e3-8e55-4cd4-a548-07ea7113bc47")
    List<ISmMetamodelFragment> getMetamodelFragments();

    /**
     * @return the module class loader
     */
    @objid ("f31d6726-05c0-4c86-9806-6a0583d4863e")
    ClassLoader getClassLoader();

    /**
     * @return the required modules
     */
    @objid ("fc90994e-5a75-423d-9950-8dbb5f5b86f1")
    List<IRTModule> getMandatoryRequiredModules();

    /**
     * @return the optionally used modules
     */
    @objid ("e8da79e5-bf62-405f-981c-5545e56ec071")
    List<IRTModule> getOptionalRequiredModules();

    /**
     * Get the module life cycle listeners.
     * <p>
     * Returns a direct reference to the actual list. The list may be modified to add or remove listeners.
     * @return the module listeners.
     */
    @objid ("dda8c406-3b44-4267-aa9a-1418d3c0b10c")
    List<IRTModuleListener> getListeners();

    /**
     * The module configuration for API use.
     * @return The module configuration for API use.
     */
    @objid ("4b5b2e2b-1031-4dc0-975e-1c870d3d1c25")
    IModuleAPIConfiguration getModuleApiConfiguration();

    @objid ("d1892ff0-878e-4ada-b884-0be4fe40e91a")
    void registerWizardContribution(ContributorCategory category, IWizardContributor contributor);

    @objid ("37ef4b06-0cae-44aa-b95c-e7bc292f95ba")
    void unregisterWizardContribution(ContributorCategory category, IWizardContributor contributor);

    /**
     * Refresh the required & used module lists from the low level {@link GModule}.
     */
    @objid ("d4f10bd0-d385-484b-857d-3f9eb302b89a")
    void resetDependencies();

    /**
     * @return the modules that require this module.
     */
    @objid ("c74181ea-0a64-4cf4-8ff0-b2a6b2aaba34")
    List<IRTModule> getModuleMandatoryUses();

    /**
     * @return the modules that use optionally this module.
     */
    @objid ("db889b30-af82-4063-86c6-f876e846fd86")
    List<IRTModule> getModuleOptionalUses();

    /**
     * Invalidates the module users list so that next call to {@link #getModuleMandatoryUses()} or {@link #getModuleOptionalUses()} recomputes it.
     */
    @objid ("f6f2754f-9d9a-4cd6-8db8-ea7d2965f5e9")
    void resetModuleUsers();

    /**
     * Get the functional priority of the module.
     * 
     * The priority values define an ordering of the modules that allows Modelio to give more importance to some of them, for example to propose their commands or extensions in first positions in the GUI...
     * 
     * Values: 0 is understood as the highest priority
     * @return the current functional priority level of the module
     */
    @objid ("32cf887b-2160-4e62-ab2c-1ae7a28bcb01")
    int getPriority();

    /**
     * Set the functional priority of the module.
     */
    @objid ("fca0094d-b0e6-48c3-b6e7-9c2df3baef84")
    void setPriority(int priority);

    /**
     * Data structure representing a diagram customizer.
     * 
     * @since 2.2.1
     */
    @objid ("dc8f2fa1-8c0a-4a86-a7ed-433a83ff72cc")
    class DiagramCustomizationDescriptor {
        @objid ("e6db5ef7-58f9-434c-a6a1-c33f2eef55ef")
        private Stereotype stereotype;

        @objid ("5426600e-2b0d-4cfa-bdcf-e0ca3742ce51")
        private Class<? extends AbstractDiagram> baseDiagramClass;

        @objid ("b13e7d51-f64a-4335-969f-9ab3a5014a66")
        private IDiagramCustomizer customizer;

        /**
         * Default constructor initializing fields.
         * @param stereotype a stereotype.
         * @param baseDiagramClass a diagram metaclass.
         * @param customizer a diagram customizer.
         */
        @objid ("7d6b76e0-3a4c-4c40-b534-22b4275b2a9a")
        public  DiagramCustomizationDescriptor(Stereotype stereotype, Class<? extends AbstractDiagram> baseDiagramClass, IDiagramCustomizer customizer) {
            this.stereotype = stereotype;
            this.baseDiagramClass = baseDiagramClass;
            this.customizer = customizer;
            
        }

        /**
         * @return the stereotype
         */
        @objid ("c8df26d7-2198-4ce1-918c-30b18ac3515b")
        public Stereotype getStereotype() {
            return this.stereotype;
        }

        /**
         * @return the baseDiagramClass
         */
        @objid ("2c8016c6-68da-4d07-b62f-922cd9d9c7dc")
        public Class<? extends AbstractDiagram> getBaseDiagramClass() {
            return this.baseDiagramClass;
        }

        /**
         * @return the customizer
         */
        @objid ("2b5da504-0ace-457c-902c-90f14500d8ac")
        public IDiagramCustomizer getCustomizer() {
            return this.customizer;
        }

    }

    /**
     * Data structure representing a diagram tool.
     * 
     * @since 2.2.1
     */
    @objid ("df460e76-0480-42c5-b69e-115ca9e1bfa5")
    class DiagramToolDescriptor {
        @objid ("44d65cf5-4745-4072-a4a3-437a4c4c7112")
        private String id;

        @objid ("9afe1b82-32b3-4c1c-9f68-36fdd2f75164")
        private IDiagramTool handler;

        /**
         * @return the id
         */
        @objid ("5ab06903-40b3-4898-b54b-de2fe4c74f1e")
        public String getId() {
            return this.id;
        }

        /**
         * @return the java interface of the metaclass
         */
        @objid ("bcb56348-9989-4011-90d7-6d1174340eeb")
        public Class<? extends MObject> getMetaclass() {
            MMetamodel mm = this.handler.getModule().getModuleContext().getModel().getMClass().getMetamodel();
            
            String mc = this.handler.getParameters().get("metaclass");
            MClass mClass = mc != null ? mm.getMClass(mc) : null;
            return mClass != null ? mClass.getJavaInterface() : null;
        }

        /**
         * @return the stereotype
         */
        @objid ("79866c0d-8ab4-4dc7-ae06-6495f9cda393")
        public Stereotype getStereotype() {
            MMetamodel mm = this.handler.getModule().getModuleContext().getModel().getMClass().getMetamodel();
            
            String mc = this.handler.getParameters().get("metaclass");
            MClass mClass = mc != null ? mm.getMClass(mc) : null;
            
            String st = this.handler.getParameters().get("stereotype");
            return st != null && !st.isEmpty() ? this.handler.getModule().getModuleContext().getModelingSession().getMetamodelExtensions()
                    .getStereotype(st, mClass) : null;
            
        }

        /**
         * @return the dependency name.
         */
        @objid ("fa5007cb-8103-4177-9bba-a6de6774601c")
        public String getDep() {
            return this.handler.getParameters().get("relation");
        }

        /**
         * @return the handler
         */
        @objid ("94af2a2e-58c5-4bde-856e-83f2b76edcec")
        public IDiagramTool getHandler() {
            return this.handler;
        }

        /**
         * @param id the tool id
         * @param handler the tool handler
         */
        @objid ("8d117a34-d8d5-49e3-bc7f-9129bc81dfe1")
        public  DiagramToolDescriptor(String id, IDiagramTool handler) {
            this.id = id;
            this.handler = handler;
            
        }

    }

    /**
     * Enumeration of the states a module can have at runtime.
     */
    @objid ("4d148035-99dd-11e1-b1e0-001ec947c8cc")
    enum ModuleRuntimeState {
        /**
         * This state indicates the module is loaded but not started.
         */
        @objid ("5953af60-99dd-11e1-b1e0-001ec947c8cc")
        Loaded,
        /**
         * This states indicates the module is loaded and started.
         */
        @objid ("559ec902-99dd-11e1-b1e0-001ec947c8cc")
        Started,
        /**
         * This state indicates the module could not be loaded at all.
         */
        @objid ("5c3dee31-99dd-11e1-b1e0-001ec947c8cc")
        Incompatible;

    }
}

