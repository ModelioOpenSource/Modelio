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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.modelio.model.scope.StereotypeSpecReader;
import org.modelio.api.module.IModule;
import org.modelio.api.ui.contributor.DefaultWizardPreviewPanel;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Convenience class that provides an abstract implementation of IDiagramWizardContributor that only provides useful fields and their getters/setters.
 * 
 * <p><u>Note to contributor implementors:</u><br/>
 * By inheriting from this abstract class you only have to provide implementations for the two abstract methods:<ul>
 * <li>{@link #checkCanCreateIn(ModelElement)}</li>
 * <li>{@link #actionPerformed(ModelElement, String, String)}</li>
 * <li>{@link #dispose()}</li>
 * </ul>
 * </p>
 * @since 3.4.0
 */
@objid ("f0b3883a-507d-40dd-a4f7-da6fd19ae296")
public abstract class AbstractWizardContributor implements IWizardContributor {
    /**
     * Represents the label used to display the name of the contributor in the GUI.
     * Must be I18n'ed
     */
    @objid ("0b86335f-ebdc-4291-9df4-7ae90a849f90")
    private String label;

    /**
     * Represents the basic information describing the behavior of the contributor (what is created).
     * Must be short (one line).
     * Must be I18n'ed.
     */
    @objid ("b38e7569-a81e-4ccd-91d9-25c7602cbb04")
    private String information;

    /**
     * The URL of the help page for this contributor.
     * This URL is relative to the help system 'root'.
     */
    @objid ("d69fea2a-3c61-4021-8efe-d4c84a612c8f")
    private String helpUrl;

    /**
     * Represents the detailed information describing the behavior of the contributor (what is created and how to use it).
     * Can be several lines long..
     * Must be I18n'ed.
     */
    @objid ("c49cb1d6-d87a-42aa-9983-0f4569cf57c8")
    private String details;

    /**
     * Internal table of named parameters used to store the values entered by the end-user in the GUI of the contributor.
     */
    @objid ("a66cc276-9a6c-4025-a71e-19c228197b8e")
    private Map<String, String> parameters;

    /**
     * A creator id. The creator id designates the particular {@link IModelViewTemplate} used by this contributor.
     * Optional, empty string means no creator defined.
     */
    @objid ("56f62145-be4c-4a49-8842-cbe2231f0bf2")
    private String modelViewTemplateId = "";

    /**
     * The icon for this contributor.
     * Lifecycle: the image is created by the Wizard GUI from the given descriptor and freed when necessary.
     */
    @objid ("aa7da990-6627-4023-98fd-8b3aa5c32d90")
    private ImageDescriptor iconDescriptor;

    /**
     * The valid scopes for this contributor, ie the (metaclass+stereotype) combinations which are accepted as 'context' in  {@link #accept(MObject)} and  {@link #actionPerformed(ModelElement, String, String)}
     */
    @objid ("ca2ba3d0-e79d-4317-8ba4-eb4ffb24e791")
    private List<ElementScope> scopes;

    /**
     * When this contributor is provided by a module, this value is the contributing module. May be null.
     */
    @objid ("30aee579-30cf-46cc-83ff-631d00954773")
    private IModule contributingModule;

    /**
     * An optional descriptor of a preview image of "what" is created by the contributor.
     * Lifecycle: the preview image is created by the Wizard GUI from the given descriptor and freed when necessary.
     */
    @objid ("ccc424dd-3091-4a69-a941-beaa3d573041")
    private ImageDescriptor previewDescriptor;

    /**
     * Default constructor that ensure {@link #getParameters()} and {@link #getScopes()} won't return null.
     */
    @objid ("21f46c96-013a-4b5c-b716-b0afdabb5ed3")
    public AbstractWizardContributor() {
        // Ensure non null containers
        this.parameters = new HashMap<>();
        this.scopes = new ArrayList<>();
    }

    @objid ("63bb2c7d-fef1-4459-9d28-50f539c15de6")
    @Override
    public final String getLabel() {
        return this.label;
    }

    @objid ("497186fc-654d-4608-86fa-a1aa373806f8")
    @Override
    public final void setLabel(String label) {
        this.label = label;
    }

    @objid ("03555118-62fa-4c14-a35f-e84d691d4955")
    @Override
    public final String getInformation() {
        return this.information;
    }

    @objid ("4fa87108-b007-4b99-9a70-bd9ee01f40e4")
    @Override
    public final void setInformation(String information) {
        this.information = information;
    }

    @objid ("542db4e6-7126-4698-a88a-d23eb7b90856")
    @Override
    public final String getHelpUrl() {
        return this.helpUrl;
    }

    @objid ("fd945016-1d97-47e2-a2d0-b0c0677493a0")
    @Override
    public final void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    @objid ("1200ac24-6818-43ab-b8c9-ef014f679e31")
    @Override
    public final String getDetails() {
        return this.details;
    }

    @objid ("3f4e76a6-57be-49b0-8580-e36a915f4c3a")
    @Override
    public final void setDetails(String details) {
        this.details = details;
    }

    @objid ("150104ca-a261-4868-95ae-9639509594d0")
    @Override
    public final ImageDescriptor getIconDescriptor() {
        return this.iconDescriptor;
    }

    @objid ("6b9a16c4-e956-4940-be32-301081884400")
    @Override
    public final void setIconDescriptor(ImageDescriptor iconDescriptor) {
        this.iconDescriptor = iconDescriptor;
    }

    /**
     * @return the wizard contribution parameters.
     */
    @objid ("0de23335-b2ac-47ff-a252-7912028c939f")
    public final Map<String, String> getParameters() {
        return this.parameters;
    }

    @objid ("cbe1b499-9a69-487f-87f4-960b9b12a181")
    @Override
    public final void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @objid ("672c47d8-17cf-4fa5-a665-acf54a5405a6")
    @Override
    public List<ElementScope> getScopes() {
        return this.scopes;
    }

    @objid ("e8ab1471-cb20-42e9-a2dc-69d781559eb8")
    @Override
    public final void setScopes(List<ElementScope> scopes) {
        this.scopes = scopes;
    }

    /**
     * Default implementation that deals with checking the scopes and requires an abstract delegated method to be provided by implementors.
     */
    @objid ("504b5b02-c2f4-46cc-a635-2a08d392368f")
    @Override
    public boolean accept(ModelElement main) {
        // Cannot accept null
        if (main == null) {
            return false;
        }
        
        if (checkCanCreateIn(main)) {
            for (ElementScope scope : getScopes()) {
                if (scope.isMatching(main)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Execute the creation action.
     * <p>
     * Note : a model transaction is already open by the caller.
     */
    @objid ("ecec8d02-cf32-4e0f-8712-0f89e26dc6ab")
    @Override
    public abstract MObject actionPerformed(ModelElement diagramContext, String diagramName, String diagramDescription);

    @objid ("e515eff8-430f-440b-ae96-7b5dc317031a")
    @Override
    public final IModule getModule() {
        return this.contributingModule;
    }

    @objid ("ddf9f7ae-f550-4bfc-be71-1a4646f3f506")
    @Override
    public final void setModule(IModule module) {
        this.contributingModule = module;
    }

    @objid ("2e64fe6a-b71d-433c-9e2f-fc9c85e77e9c")
    @Override
    public final ImageDescriptor getPreviewImage() {
        return this.previewDescriptor;
    }

    /**
     * Find the stereotype from a stereotype specification.
     * <p>
     * The stereotype specification may have one of the following formats:
     * <ul>
     * <li><i>stereotype name</i>
     * <li><i>module name<b>#</b>stereotype name</i>
     * <li><i>module regex<b>#</b>stereotype name</i>
     * <li><i>module regex<b>#</b>stereotype regex</i>
     * </ul>
     * Returns <i>null</i> if the specification is <i>null</i> or the stereotype is not found.
     * 
     * @param metaclass the metaclass to look from
     * @param stereotypeSpec the stereotype specification
     * @return the found stereotype or <i>null</i>.
     */
    @objid ("1dd943e9-ed40-466f-82e6-66b74366bb55")
    public final Stereotype findStereotypeFromSpec(MClass metaclass, String stereotypeSpec) {
        return StereotypeSpecReader.findStereotypeFromSpec(getModule().getModuleContext().getModelingSession(), metaclass, stereotypeSpec);
    }

    @objid ("4c57df17-634c-427d-8422-5657c20cfb2a")
    @Override
    public IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("329d1dd7-7905-41da-b9dc-f927a6a8d4ff")
    @Override
    public final void setPreviewImage(ImageDescriptor previewDescriptor) {
        this.previewDescriptor = previewDescriptor;
    }

    @objid ("ed4f1ff3-cdc9-47b0-ab2d-478f7c347cb2")
    @Override
    public final String getModelViewTemplateId() {
        return this.modelViewTemplateId;
    }

    @objid ("ce7c4188-324f-4d9d-8910-9fd1843633b9")
    @Override
    public final void setModelViewTemplateId(String id) {
        this.modelViewTemplateId = id;
    }

    /**
     * Called by {@link #accept(MObject)} to check whether the given object has sufficient rights to create the wizard new element.
     * 
     * @param owner the new element owner.
     * @return true if the operation is allowed else false.
     */
    @objid ("1ae3d44c-25fe-47a5-ad89-87b25bbffdb7")
    protected abstract boolean checkCanCreateIn(ModelElement owner);

    /**
     * Default implementation returning <code>null</code>.
     */
    @objid ("156b1753-3ccf-490b-9821-aefea6355a45")
    @Override
    public Image getIconImage() {
        return null;
    }

}
