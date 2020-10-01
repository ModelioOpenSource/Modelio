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

package org.modelio.api.module.propertiesPage;

import java.nio.file.Path;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.modelio.api.module.IModule;
import org.modelio.api.ui.form.ElementFormPanel;
import org.modelio.api.ui.form.IFieldFactory;

/**
 * An {@link IModulePropertyCustomPanel} implementation displaying a form for the selected element.
 * <p>
 * SubClasses should redefine {@link #isRelevantFor(Object)} and {@link #initFactory()} only.
 * </p>
 * <p>
 * The IPanelProvider aspect is delegated to an {@link ElementFormPanel} relying on a {@link IFieldFactory}.
 * </p>
 */
@objid ("044b4a28-d3ae-4b16-a889-2378bb8ec328")
public abstract class ElementFormPropertyPanel implements IModulePropertyCustomPanel {
    @objid ("c20b6f1b-1e17-4569-a004-ec8da00f88ea")
    private String label;

    @objid ("cf2c49f2-615d-4d05-a9c5-2bd84c98a8c6")
    private String name;

    @objid ("d1b1aea9-f64f-43c6-abfd-b38570909e0a")
    private Listener formUpdater;

    @objid ("274fdaaa-448c-4766-97d0-df3fb7f5296f")
    private IModule module;

    @objid ("e3b7cc6f-9cef-4cd5-9a51-70858677283a")
    private final ElementFormPanel formPanel;

    @objid ("75091f42-1db1-4ea4-9491-937b73a66831")
    private Path icon;

    /**
     * Constructor invoked by Modelio when building the module's property page.
     * <p/>
     * Signature is <b>mandatory</b> and shouldn't be changed on the module's side.
     * 
     * @param module the module this property page is build for.
     * @param name the internal name of this property page.
     * @param label the label displayed in Modelio for this property page.
     * @param icon a relative path to the image to display for the property page.
     */
    @objid ("221b1605-f921-41d5-a62e-07b7eccd98fa")
    public ElementFormPropertyPanel(IModule module, String name, String label, String icon) {
        this.module = Objects.requireNonNull(module);
        this.name = name;
        this.label = label;
        this.formPanel = new ElementFormPanel(module.getModuleContext(), initFactory());
        this.icon = icon == null || icon.isEmpty() ? null : module.getModuleContext().getConfiguration().getModuleResourcesPath().resolve(icon);
    }

    @objid ("1d855019-44ad-473a-b6c0-ca6635435747")
    @Override
    public final Composite createPanel(Composite parent) {
        return this.formPanel.createPanel(parent);
    }

    @objid ("05305859-2d5d-4131-9d6c-5bc68906cc1a")
    @Override
    public final void dispose() {
        this.formPanel.dispose();
    }

    @objid ("76572b8e-eff7-48bd-afd6-96f03fa65f1b")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("8157abec-8d5e-477a-a8a9-83bde15e5927")
    @Override
    public final Object getInput() {
        return this.formPanel.getInput();
    }

    @objid ("a37d4387-7700-4a7a-b31f-74a55c59f715")
    @Override
    public final String getLabel() {
        return this.label;
    }

    @objid ("77ce27ba-5bbb-42fa-92c7-56c9e3e99bd7")
    @Override
    public final IModule getModule() {
        return this.module;
    }

    @objid ("eed2028a-6492-4a05-b639-9b931426b36c")
    @Override
    public final String getName() {
        return this.name;
    }

    @objid ("c03ca3ff-5dce-455d-8bc5-fe574c3e7c74")
    @Override
    public final Composite getPanel() {
        return this.formPanel.getPanel();
    }

    @objid ("8f2629fa-aa8e-4cf9-959e-2e89f9af4d91")
    @Override
    public final void setInput(Object input) {
        this.formPanel.setInput(input);
    }

    @objid ("d4a902c9-22c3-4d7f-a163-617739175bf7")
    @Override
    public final void setLabel(String label) {
        this.label = label;
    }

    @objid ("2042de6a-cc5c-4521-b333-468e3d9da508")
    @Override
    public final void setModule(IModule module) {
        this.module = module;
    }

    @objid ("095c92be-27df-4d4c-9785-ac9ba28d80ae")
    @Override
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Initialize an instance of {@link IFieldFactory} to build the property page's form.
     * <p/>
     * Called only once in the constructor.
     */
    @objid ("9d07d66d-ec91-43f1-ba21-2ea5d9b6eb39")
    protected abstract IFieldFactory initFactory();

    @objid ("a3ba43cb-642e-492a-b7bf-e7e711f96c4a")
    @Override
    public Path getIcon() {
        return this.icon;
    }

}
