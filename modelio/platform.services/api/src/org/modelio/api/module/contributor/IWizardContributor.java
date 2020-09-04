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

package org.modelio.api.module.contributor;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This is the contract to be honored by a contributor to the Creation Wizard.
 * <ul>
 * <li>Getters are used by the wizard to display information about the element to create</li>
 * <li>Setters are used to initialize the data structure when loading a module providing the contributor</li>
 * <li>The {@link IWizardContributor#actionPerformed(ModelElement, String, String)} method is in charge of creating the
 * element from the provided data.
 * </ul>
 * 
 * @since 3.4.0
 */
@objid ("0a397039-5d53-4777-aacd-facbd326bf12")
public interface IWizardContributor {
    /**
     * Execute the creation action.
     * 
     * @param context the model element on which the wizard is run
     * @param name the name of the element to create.
     * @param description the description of the element to create.
     * @return the created model object.
     */
    @objid ("7b08eb8e-33dc-42a1-ad4e-0a01a5869c57")
    MObject actionPerformed(final ModelElement context, final String name, final String description);

    /**
     * Get a complete textual explanation of the contribution's role or function.
     * 
     * @return the contribution's role or function.
     */
    @objid ("d27e968a-f065-4968-ab50-4bad191ceab0")
    String getDetails();

    /**
     * Get a URL to some 'help' or 'documentation' page about the contribution.
     * 
     * @return the help URL.
     */
    @objid ("9ab0cfb2-8b4c-4d2b-a15c-c47e9eb65d57")
    String getHelpUrl();

    /**
     * @return the wizard small icon
     */
    @objid ("0490d12d-2a1b-4765-9c57-8626e358a7cb")
    Image getIcon();

    /**
     * Get a short textual explanation of the contribution's role or function.
     * <p>
     * This text is displayed in the panel header.
     * 
     * @return the short explanation.
     */
    @objid ("4ceed4d6-4e26-429b-ab1d-300e2830dbea")
    String getInformation();

    /**
     * Get the label to be displayed for the contribution by the Creation Wizard in the "type" browser.
     * 
     * @return the label to be displayed
     */
    @objid ("a6e56c11-967d-4a5a-b9ad-55994175d762")
    String getLabel();

    /**
     * @return the module that contributed this contribution.
     */
    @objid ("20abf057-f79a-4e5e-8c67-98fb9a034502")
    IModule getModule();

    /**
     * @return a preview image to display in the wizard.
     */
    @objid ("587a10b0-209e-4a0b-9f73-f166d7eba51b")
    ImageDescriptor getPreviewImage();

    /**
     * @return the scopes this wizard applies to
     */
    @objid ("e27f9a3d-fbdd-4adc-8720-3f75aef1478d")
    List<ElementScope> getScopes();

    /**
     * Set a textual explanation of the contribution's role or function.
     * 
     * @param details a textual explanation
     */
    @objid ("ab067d70-abe7-4997-a9dc-9a23488e53ad")
    void setDetails(String details);

    /**
     * @param helpUrl the help URL
     */
    @objid ("c60d26bf-d49e-4365-a819-c34b3bbeec52")
    void setHelpUrl(String helpUrl);

    /**
     * @param icon this wizard contribution icon
     */
    @objid ("2f250b9e-c4c4-465a-94b8-57e2d6c67dd1")
    void setIcon(Image icon);

    /**
     * Get a short textual explanation of the contribution's role or function.
     * <p>
     * This text is displayed in the panel header.
     * 
     * @param information the short explanation.
     */
    @objid ("f15104d6-e8d6-4ce5-8f82-4e7138deb6ff")
    void setInformation(String information);

    /**
     * @param label this contribution label.
     */
    @objid ("5f4c80f9-b2e5-4adf-a2e3-cfaf927a1ce3")
    void setLabel(String label);

    /**
     * @param module the module that provides this wizard contribution.
     */
    @objid ("baf58ea6-0f73-40f5-8d99-8308ebd4c63f")
    void setModule(IModule module);

    /**
     * @param parameters the wizard parameters
     */
    @objid ("49299fb8-621e-41df-b683-7910ccef2b5f")
    void setParameters(Map<String, String> parameters);

    /**
     * @param scopes the wizard scope
     */
    @objid ("e38a09c0-a0e8-4327-9a21-92f194e813aa")
    void setScopes(List<ElementScope> scopes);

    /**
     * This method checks if an element is accepted.
     * 
     * @param element the model element to check.
     * @return <code>true</code> if the element is accepted.
     */
    @objid ("85ec1d2a-3071-4802-b613-10c984f29e25")
    boolean accept(final MObject element);

    /**
     * Get the GUI panel to display in the wizard.
     * 
     * @return the wizard panel to display.
     * @since 3.4
     */
    @objid ("25cc1fbb-44ea-40b3-a575-5412d52a1cf0")
    IPanelProvider getWizardPanel();

    /**
     * Release resources allocated by this contributor.
     * <p>
     * Should for example dispose the icon allocated by {@link #getIcon()} if any.
     */
    @objid ("4700bb10-f2e8-4369-a11f-f613b1ed5e0e")
    void dispose();

    /**
     * @param descriptor a descriptor for the wizard preview image
     */
    @objid ("09689bc2-3587-4c10-bee3-5467d1253bd9")
    void setPreviewImage(ImageDescriptor descriptor);

    /**
     * @return a descriptor indicating which element type is created by the contribution.
     */
    @objid ("9e6a023d-c481-44df-aaa6-73ea6cd09290")
    ElementDescriptor getCreatedElementType();

}
