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
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Convenience class that provides an abstract implementation of
 * IDiagramWizardContributor that only provides useful fields and their
 * getters/setters.
 * 
 * @since 3.4.0
 */
@objid ("f0b3883a-507d-40dd-a4f7-da6fd19ae296")
public abstract class AbstractWizardContributor implements IWizardContributor {
    @objid ("0b86335f-ebdc-4291-9df4-7ae90a849f90")
    private String label;

    @objid ("b38e7569-a81e-4ccd-91d9-25c7602cbb04")
    private String information;

    @objid ("d69fea2a-3c61-4021-8efe-d4c84a612c8f")
    private String helpUrl;

    @objid ("c49cb1d6-d87a-42aa-9983-0f4569cf57c8")
    private String details;

    @objid ("a66cc276-9a6c-4025-a71e-19c228197b8e")
    private Map<String, String> parameters;

    @objid ("aa7da990-6627-4023-98fd-8b3aa5c32d90")
    private Image icon;

    @objid ("ca2ba3d0-e79d-4317-8ba4-eb4ffb24e791")
    private List<ElementScope> scopes;

    @objid ("30aee579-30cf-46cc-83ff-631d00954773")
    private IModule contributingModule;

    @objid ("ccc424dd-3091-4a69-a941-beaa3d573041")
    private ImageDescriptor previewDescriptor;

    /**
     * Default constructor that ensure {@link #getParameters()} and
     * {@link #getScopes()} won't return null.
     */
    @objid ("21f46c96-013a-4b5c-b716-b0afdabb5ed3")
    public AbstractWizardContributor() {
        // Ensure non null containers
        this.parameters = new HashMap<>();
        this.scopes = new ArrayList<>();
    }

    @objid ("63bb2c7d-fef1-4459-9d28-50f539c15de6")
    @Override
    public String getLabel() {
        return this.label;
    }

    @objid ("497186fc-654d-4608-86fa-a1aa373806f8")
    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @objid ("03555118-62fa-4c14-a35f-e84d691d4955")
    @Override
    public String getInformation() {
        return this.information;
    }

    @objid ("4fa87108-b007-4b99-9a70-bd9ee01f40e4")
    @Override
    public void setInformation(String information) {
        this.information = information;
    }

    @objid ("542db4e6-7126-4698-a88a-d23eb7b90856")
    @Override
    public String getHelpUrl() {
        return this.helpUrl;
    }

    @objid ("fd945016-1d97-47e2-a2d0-b0c0677493a0")
    @Override
    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    @objid ("1200ac24-6818-43ab-b8c9-ef014f679e31")
    @Override
    public String getDetails() {
        return this.details;
    }

    @objid ("3f4e76a6-57be-49b0-8580-e36a915f4c3a")
    @Override
    public void setDetails(String details) {
        this.details = details;
    }

    @objid ("150104ca-a261-4868-95ae-9639509594d0")
    @Override
    public Image getIcon() {
        return this.icon;
    }

    @objid ("6b9a16c4-e956-4940-be32-301081884400")
    @Override
    public void setIcon(Image icon) {
        this.icon = icon;
    }

    /**
     * @return the wizard contribution parameters.
     */
    @objid ("0de23335-b2ac-47ff-a252-7912028c939f")
    public Map<String, String> getParameters() {
        return this.parameters;
    }

    @objid ("cbe1b499-9a69-487f-87f4-960b9b12a181")
    @Override
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @objid ("672c47d8-17cf-4fa5-a665-acf54a5405a6")
    @Override
    public List<ElementScope> getScopes() {
        return this.scopes;
    }

    @objid ("e8ab1471-cb20-42e9-a2dc-69d781559eb8")
    @Override
    public void setScopes(List<ElementScope> scopes) {
        this.scopes = scopes;
    }

    @objid ("504b5b02-c2f4-46cc-a635-2a08d392368f")
    @Override
    public abstract boolean accept(MObject element);

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
    public IModule getModule() {
        return this.contributingModule;
    }

    @objid ("ddf9f7ae-f550-4bfc-be71-1a4646f3f506")
    @Override
    public void setModule(IModule module) {
        this.contributingModule = module;
    }

    @objid ("2e64fe6a-b71d-433c-9e2f-fc9c85e77e9c")
    @Override
    public ImageDescriptor getPreviewImage() {
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
     * Returns <i>null</i> if the specification is <i>null</i> or the stereotype
     * is not found.
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

    /**
     * Disposes the icon set by {@link #setIcon(Image)}.
     */
    @objid ("bb05b688-c092-4f8f-9680-080e46eaee93")
    @Override
    public void dispose() {
        if (this.icon != null) {
            this.icon.dispose();
            this.icon = null;
        }
    }

    @objid ("329d1dd7-7905-41da-b9dc-f927a6a8d4ff")
    @Override
    public void setPreviewImage(ImageDescriptor previewDescriptor) {
        this.previewDescriptor = previewDescriptor;
    }

}
