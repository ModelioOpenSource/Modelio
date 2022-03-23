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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.api.modelio.model.IElementFilter;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.IModule;
import org.modelio.api.ui.swt.SelectionHelper;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The ModuleMultiVariantPropertyPanel is an implementation of IModulePropertyCustomPanel that supports different GUI depending on the element to display.<br/>
 * For this purpose, the developer is invited to register different {@link IPanelProvider} instances associated to selection conditions.<br/>
 * The conditions for a {@link IPanelProvider} instance to be used for a given element are made of two criteria:
 * <ul>
 * <li>an {@link ElementScope} criterion where the given element must satisfy the {@link ElementScope#isMatching(MObject)} method</li>
 * <li>an optional {@link IElementFilter} where the given element must satisfy the the {@link IElementFilter#accept(MObject)} method</li>
 * </ul>
 * {@link IPanelProvider} instances are registered along with their applicability conditions in a map where they are looked up for when the {@link ModuleMultiVariantPropertyPanel#setInput(Object)} is called. The ModuleMultiVariantPropertyPanel chooses the
 * first condition matching {@link IPanelProvider} which becomes the {@link #activePanelProvider} that is in charge of displaying the element properties.
 * 
 * When no matching {@link IPanelProvider} is found a statically predefined "empty" panel provider is used {@link #EMPTYPANELPROVIDER} that simply display an empty GUI.
 * @since 4.1
 */
@objid ("1ee8619c-ca92-46f5-92a1-b3093a2483ba")
public class ModuleMultiVariantPropertyPanel implements IModulePropertyCustomPanel {
    @objid ("4568515e-3423-4d35-a3b6-1a549f1309f7")
    private String label;

    @objid ("5a416184-839c-4ccb-9cc2-110e4bcefad4")
    private String name;

    /**
     * The EMPTYPANELPROVIDER instance. Provides a fallback provider when no matching entry can be found in {@link #registeredPanels}. The EMPTYPANELPROVIDER displays an empty GUI.
     */
    @objid ("84128088-6e78-447a-9d87-f7c4fbb1feef")
    private static final IPanelProvider EMPTYPANELPROVIDER = new IPanelProvider() {
            private Composite top;
            private Object input;
            @Override
            public void setInput(Object input) {
                // Nothing special to do here, the EMPTYPANELPROVIDER does not display anything dependent on the current input.
                // Just store the current input to provide a coherent implementation of getInput()
                this.input = input;
            }
            @Override
            public boolean isRelevantFor(@SuppressWarnings ("hiding") Object input) {
                // EMPTYPANELPROVIDER is relevant for any element
                return true;
            }
            @Override
            public Object getPanel() {
                return this.top;
            }
            @Override
            public Object getInput() {
                return this.input;
            }
            @Override
            public String getHelpTopic() {
                return null;
            }
            @Override
            public void dispose() {
            }
            @Override
            public Object createPanel(Composite parent) {
                this.top = new Composite(parent, SWT.NONE);
                this.top.setBackground(UIColor.LIGHTGRAY);
                return this.top;
            }
        };

    /**
     * A YES to any element filter used as a fallback when no filter has been set in a VariantEntry.
     */
    @objid ("d6357326-b4f1-4e6a-a320-c566929efef4")
    private static final IElementFilter YES_FILTER = new IElementFilter() {
            @Override
            public boolean accept(MObject element) {
                return true;
            }
        };

    @objid ("69ba0307-c647-497e-9c55-9b7117f4effb")
    private IPanelProvider activePanelProvider;

    @objid ("ae9e0be6-968a-423b-9d1c-9b925928523c")
    private Object input;

    @objid ("2f9fbcc8-4a85-4af0-9af7-316d3ac4b637")
    private IModule module;

    @objid ("66975b21-c8c2-41ea-9d52-e127e2274728")
    private List<VariantEntry> registeredPanels = new ArrayList<>();

    @objid ("88d57af4-24e4-4e26-83cc-f2971d799274")
    private Composite stackComposite;

    @objid ("d240e5ca-b32a-467c-9484-d37a2108b4be")
    private StackLayout stackLayout;

    @objid ("ab193898-b23f-41a7-9b59-d3219beffc5b")
    private Path icon;

    /**
     * Constructors of this property page.
     * @param module module that is associated to the property page
     * @param name the name of the property page.
     * @param label the label of the property page.
     * @param icon a relative path to the image to display for the property page.
     */
    @objid ("666a43ce-2009-41c7-acb3-3f0d828b722d")
    public  ModuleMultiVariantPropertyPanel(IModule module, String name, String label, String icon) {
        this.module = module;
        this.name = name;
        this.label = label;
        this.icon = icon == null || icon.isEmpty() ? null : module.getModuleContext().getConfiguration().getModuleResourcesPath().resolve(icon);
        
    }

    /**
     * From interface IPanelProvider
     * 
     * Create the GUI for the ModuleMultiVariantPropertyPanel. The GUI is mainly a Composite using a stack layout to switch between the different registered panels.
     */
    @objid ("a7a1941c-14d4-4817-817c-1f8cef782222")
    @Override
    public Object createPanel(Composite parent) {
        // stackComposite level container
        this.stackComposite = new Composite(parent, SWT.NONE);
        //this.stackComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        this.stackLayout = new StackLayout();
        
        this.stackComposite.setLayout(this.stackLayout);
        
        // Make the EMPTYPANELPROVIDER the initial provider to start with.
        EMPTYPANELPROVIDER.createPanel(this.stackComposite);
        this.stackLayout.topControl = (Control) EMPTYPANELPROVIDER.getPanel();
        return this.stackComposite;
    }

    /**
     * From IPanelProvider
     */
    @objid ("a3c9bb0a-5327-47cb-aea4-9469931988fa")
    @Override
    public void dispose() {
        this.activePanelProvider = EMPTYPANELPROVIDER;
        for (VariantEntry variantEntry : this.registeredPanels) {
            variantEntry.panelProvider.dispose();
        }
        this.registeredPanels.clear();
        
    }

    /**
     * From interface IPanelProvider. Simply delegate to currently active panel.
     */
    @objid ("30a80c94-d96f-46cd-ba0d-2f1ff99678df")
    @Override
    public String getHelpTopic() {
        return this.activePanelProvider != null ? this.activePanelProvider.getHelpTopic() : null;
    }

    /**
     * From interface IPanelProvider
     */
    @objid ("817aa7af-d978-490d-ba0b-106423459ae5")
    @Override
    public Object getInput() {
        return this.input;
    }

    /**
     * from IModulePropertyPanel
     */
    @objid ("0e7102ff-5cb6-4e8f-9e20-df5322a025a9")
    @Override
    public String getLabel() {
        return this.label;
    }

    /**
     * from IModulePropertyPanel
     */
    @objid ("362da41a-b10a-4ad5-950a-e5583d57ccaf")
    @Override
    public IModule getModule() {
        return this.module;
    }

    /**
     * from IModulePropertyPanel
     */
    @objid ("cf246872-69a1-4959-b153-96e185635c81")
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * From interface IPanelProvider
     */
    @objid ("b6f115ed-eb6b-43dc-9418-b94b62ebc040")
    @Override
    public Object getPanel() {
        return this.stackComposite;
    }

    /**
     * From interface IPanelProvider.
     * 
     * The resolution of isRelevantFor() consists in:
     * <ol>
     * <li>get the proper IPanelProvider for the input</li>
     * <li>call this IPanelProvider isRelevantFor() method</li>
     * </ol>
     * The method does not switch the currently active panel.
     */
    @objid ("fdd0b289-3453-4593-b641-8b2ba2a4e627")
    @Override
    public boolean isRelevantFor(Object proposedInput) {
        MObject obj = SelectionHelper.getFirst((ISelection) proposedInput, MObject.class);
        if (obj != null) {
            IPanelProvider pp = getPanelProviderFor(obj);
            return pp != null ? pp.isRelevantFor(obj) : false;
        }
        return false;
    }

    /**
     * Register an {@link IPanelProvider} for the given scope.
     * @param panelProvider the {@link IPanelProvider} to register. Cannot be null.
     * @param scope the scope for which the panel provider is applicable. Cannot be null.
     */
    @objid ("19b487b5-ad38-4e11-b1ed-98a3871924fd")
    public void register(IPanelProvider panelProvider, ElementScope scope) {
        register(panelProvider, scope, YES_FILTER);
    }

    /**
     * Register an {@link IPanelProvider} for the given scope and under the additional filter condition.
     * @param panelProvider the {@link IPanelProvider} to register. Cannot be null.
     * @param scope the scope for which the panel provider is applicable. Cannot be null.
     * @param filter the additional filter the element must pass once its scope has been approved. Cannot be null.
     */
    @objid ("ec93dea3-62a5-42e6-a329-a8d57259140e")
    public void register(IPanelProvider panelProvider, ElementScope scope, IElementFilter filter) {
        this.registeredPanels.add(new VariantEntry(panelProvider, scope, filter));
    }

    /**
     * From interface IPanelProvider.
     * 
     * Switch the currently activated panel to the proper panel for proposed input and pass the input to this new activated panel.
     */
    @objid ("d14ab560-4631-4194-bd51-5003013606ef")
    @Override
    public void setInput(Object input) {
        MObject mobj = null;
        if (input instanceof ISelection) {
            mobj = SelectionHelper.getFirst((ISelection) input, MObject.class);
        } else if (input instanceof Collection) {
            for (Object o : (Collection<?>) input) {
                if (o instanceof MObject) {
                    mobj = (MObject) o;
                    break;
                } else if (o instanceof IAdaptable) {
                    mobj = ((IAdaptable) o).getAdapter(MObject.class);
                    if (mobj != null) {
                        break;
                    }
                }
            }
        } else if (input instanceof MObject) {
            mobj = (MObject) input;
        }
        this.input = mobj;
        
        switchActivePanelProvider(mobj);
        
        this.activePanelProvider.setInput(mobj);
        ((Composite) this.activePanelProvider.getPanel()).layout(true, true);
        ((Composite) this.activePanelProvider.getPanel()).redraw();
        
    }

    /**
     * from IModulePropertyPanel
     */
    @objid ("63e8efc3-7172-4ebe-9ac5-daf790767284")
    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * from IModulePropertyPanel
     */
    @objid ("9cc91de7-7d71-48b9-8d01-d2513b5b171a")
    @Override
    public void setModule(IModule mdac) {
        this.module = mdac;
    }

    /**
     * from IModulePropertyPanel
     */
    @objid ("01392f5c-07f4-4bc3-aedb-d02e88aea928")
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Switching the active panel provider consists in:
     * <ol>
     * <li>get the proper IPanelProvider for the input
     * <li>Make it the new active panel provider</li>
     * <li>get the panel composite of the new activated panel provider and make it the stackComposite displayed done on the stacklayout</li>
     * <li>if the panel composite has not been created yet (first use of the panel provider) create it and sets its input</li>
     * 
     * </ol>
     */
    @objid ("6d956b61-c1ae-4ce4-ab12-90c7bc14e533")
    private void switchActivePanelProvider(MObject mobj) {
        IPanelProvider pp = getPanelProviderFor(mobj);
        this.activePanelProvider = pp;
        
        if (this.activePanelProvider.getPanel() == null) {
            this.activePanelProvider.createPanel(this.stackComposite);
        }
        this.stackLayout.topControl = (Control) this.activePanelProvider.getPanel();
        this.stackComposite.layout(true, true);
        
    }

    /**
     * If no proper panel can be guessed for the input, use the EMPTYPANELPROVIDER that displays nothing.
     * @return the proper panel provider for mObj or the EMPTYPANELPROVIDER
     */
    @objid ("a6c9cddf-c0f4-4c74-9f32-bdec484e6a53")
    private IPanelProvider getPanelProviderFor(MObject mObj) {
        for (VariantEntry variantEntry : this.registeredPanels) {
            if (variantEntry.scope.isMatching(mObj) && variantEntry.filter.accept(mObj)) {
                IPanelProvider panelProvider = variantEntry.panelProvider;
                if (panelProvider.isRelevantFor(mObj)) {
                    return panelProvider;
                }
            }
        }
        return EMPTYPANELPROVIDER;
    }

    @objid ("d08d252d-6d06-4582-bcf7-eb3d136fd435")
    @Override
    public Path getIcon() {
        return this.icon;
    }

    @objid ("89da09df-4c3a-4cfe-a05e-3aa4f9ce7d6d")
    protected final IPanelProvider getActivePanelProvider() {
        return this.activePanelProvider;
    }

    @objid ("1a091b85-7592-4c50-94a9-6e020f195702")
    private static class VariantEntry {
        @objid ("6f7ac3ad-4178-48f4-907c-abf4026ab985")
        public final ElementScope scope;

        @objid ("0429ac36-214c-4b00-b5d7-4f314944a868")
        public final IElementFilter filter;

        @objid ("9c0e735d-da14-4705-871e-fdf964421187")
        public final IPanelProvider panelProvider;

        @objid ("a19d58f7-a4cf-4bc2-ad88-762d28fef7a4")
        public  VariantEntry(IPanelProvider panelProvider, ElementScope scope, IElementFilter filter) {
            this.panelProvider = panelProvider;
            this.scope = scope;
            this.filter = filter;
            
        }

    }

}
