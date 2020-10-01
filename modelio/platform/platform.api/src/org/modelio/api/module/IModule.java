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

package org.modelio.api.module;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.license.ILicenseInfos;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.api.module.parameter.IParameterEditionModel;
import org.modelio.gproject.ramc.core.model.IModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vbasic.version.Version;

/**
 * All Modelio module classes must implement this interface.
 * <p>
 * In practice, the Modelio modules implement the {@link IModule} interface .
 * </p>
 * <p>
 * The {@link IModule} interface is implemented thanks to the
 * {@code AbstractJavaModule} class.<br/>
 * The {@link IModule} can never be used by another module developer.<br/>
 * Only the peer module ({@link IPeerModule}) can be accessed, as described
 * below:
 * </p>
 * <p>
 * <code>
 * IPeerModule module = Modelio.getInstance().getModelingSession().getModuleService().getPeerModule (ModelerModulePeerModule.class);
 * </code>
 * </p>
 */
@objid ("01f40414-0000-32a9-0000-000000000000")
public interface IModule {
    /**
     * Used to return the module description.
     * 
     * @return The module description
     */
    @objid ("01f40414-0000-32b2-0000-000000000000")
    String getDescription();

    /**
     * Get the image provided by the module for a given stereotype. The module
     * should return an image if the stereotype is provided by itself, null in
     * the other case. The image life cycle must be handled by the module.
     * 
     * @param stereotype a stereotype
     * @param type the image type
     * @return the stereotype image, or null if the module provides none.
     */
    @objid ("e3b2b60f-de71-11dd-afed-0014222a9f79")
    Image getImage(Stereotype stereotype, ImageType type);

    /**
     * Get the image provided by the module for a given profile. The module
     * should return an image if the profile is provided by itself, null in
     * the other case. The image life cycle must be handled by the module.
     * @param type the image type
     * 
     * @param profile a profile
     * @return the profile image, or null if the module provides none.
     * @since Modelio Valkyrie 3.8
     */
    @objid ("20e146f7-c5dc-487e-ac00-0c4f6339f795")
    Image getImage(Profile profile, ImageType imageType);

    /**
     * Get the module's i18n label.
     */
    @objid ("4f710aac-819b-48e2-9cac-ec4e804e5ab9")
    String getLabel();

    @objid ("d0f20abf-b0ac-404e-ae01-2c75875c3643")
    default ILicenseInfos getLicenseInfos() {
        return null;
    }

    /**
     * Return the life cycle handler of a module.
     * @since 3.5
     */
    @objid ("04ee760e-04d7-4fb7-8262-8fb871294feb")
    IModuleLifeCycleHandler getLifeCycleHandler();

    /**
     * Get an expert for a stereotype belonging to this module. <br/>
     * Might return <code>null</code>.
     * 
     * @param st a stereotype owned by the current module.
     * @since 3.4
     */
    @objid ("b02ff947-1701-4d18-931d-4c71b50790b1")
    default IMdaExpert getMdaExpert(Stereotype st) {
        return null;
    }

    /**
     * Get the ModelComponent contributor associated to this module.
     * @see IModelComponentContributor
     * 
     * @param mc the Model component being built.
     * @return the module configuration.
     */
    @objid ("1f42ee4a-65e6-11e0-9853-001ec947cd2a")
    default IModelComponentContributor getModelComponentContributor(IModelComponent mc) {
        return null;
    }

    /**
     * Return the context of a module, to access Modelio services.
     * @since 3.5
     */
    @objid ("699a64b6-f12a-4f40-9355-6bbfec7b7d1f")
    IModuleContext getModuleContext();

    /**
     * Returns an Image for this module.
     * 
     * @return an Image for this module. Might be <code>null</code>.
     */
    @objid ("9567ce16-8bc1-11dd-ad20-0014222a9f79")
    Image getModuleImage();

    /**
     * Get the path to the image representing the module.
     * 
     * @return a path relative to the module's resource path.
     */
    @objid ("0bf45a9b-8afe-42fd-b7d0-06cd1a0421d0")
    String getModuleImagePath();

    /**
     * Used to return the module name.
     * <p>
     * <p>
     * The module name corresponds to the name of the module, as defined in the
     * <i>MDA Designer<i> tool.
     * 
     * @return The module name
     */
    @objid ("01f40414-0000-32ae-0000-000000000000")
    String getName();

    /**
     * Get the parameters model as it must be shown in the module parameters
     * edition dialog.
     * 
     * @return The parameters edition model.
     */
    @objid ("f8cddd33-8f94-11dd-bbe0-001ec947ccaf")
    default IParameterEditionModel getParametersEditionModel() {
        return null;
    }

    /**
     * Define a custom panel for module parameter edition. Input will be an set
     * as an {@link IParameterEditionModel}.
     * 
     * @return a {@link IPanelProvider}. Might be <code>null</code> to use the
     * standard Modelio implementation.
     */
    @objid ("6b937266-fdc7-474b-9245-c3ae085664fd")
    default IPanelProvider getParametersEditionPanel() {
        return null;
    }

    /**
     * Returns the peer module, connected to this module.
     * <p>
     * The peer module represents the public services of this current module.
     * </p>
     * 
     * @return The associated peer module
     */
    @objid ("01f40414-0000-32c3-0000-000000000000")
    IPeerModule getPeerModule();

    /**
     * Returns the minimum Modelio version that authorize the Module to be
     * activated.
     * 
     * @return The minimum Modelio version
     */
    @objid ("45f75358-65cd-11e0-b0ca-001ec947cd2a")
    Version getRequiredModelioVersion();

    /**
     * Used to return the module version.
     * 
     * @return The module version
     */
    @objid ("01f40414-0000-32b6-0000-000000000000")
    Version getVersion();

    /**
     * Method automatically called just after the creation of the module.
     * <p>
     * The module is automatically instantiated at the beginning of the mda
     * lifecycle and the constructor implementation is not accessible to the
     * module developer.
     * </p>
     * <p>
     * The <code>init</code> method allows the developer to execute the desired
     * initialization code at this step. For example, this is the perfect place
     * to register any IExpertise this module provides.
     * </p>
     * <p>
     * This method should never be called by the developer because it is already
     * invoked by the tool.
     * </p>
     */
    @objid ("01f40414-0000-320a-0000-000000000000")
    default void init() {
        // Empty default implementation
    }

    /**
     * Reserved for compatibility M3.4 M3.5. Not intended for use.
     * 
     * @param moduleContext the module's context.
     */
    @objid ("81a06395-bf2b-4ccc-9d9d-aeae3946cf1b")
    void initModulecontext(IModuleContext moduleContext);

    /**
     * Initialize the parameters model from the <b>module.xml</b> file at module
     * install.
     * <p>
     * It might be manually modified later.
     * </p>
     * 
     * @param model The parameters edition model.
     */
    @objid ("8e427dd6-3cd8-4ce1-aacd-439bfa71bbbe")
    default void initParametersEditionModel(IParameterEditionModel model) {
        // Empty default implementation
    }

    /**
     * Method automatically called just before the disposal of the module.
     * <p>
     * The <code>uninit</code> method allows the developer to execute the
     * desired un-initialization code at this step. For example, if an IExpertise
     * have been registered in the {@link #init()} method, this method is the
     * perfect place to remove them.
     * </p>
     * <p>
     * This method should never be called by the developer because it is already
     * invoked by the tool.
     * </p>
     */
    @objid ("0a40ab62-a354-11e1-abf7-001ec947c8cc")
    default void uninit() {
        // Empty default implementation
    }

    @objid ("8e52ef63-72a1-11dd-a1d1-001ec947cd2a")
    enum ImageType {
        ICON,
        IMAGE;
    }

}
