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

package org.modelio.edition.dialogs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyPanel;
import org.modelio.app.core.ModelioEnv;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.edition.dialogs.dialog.EditElementDialog;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.gproject.gproject.GProject;
import org.modelio.mda.infra.service.IModuleService;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.propertytab.propertytab.ModulePanelProvider;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1dbb88c6-8481-48bd-91a1-ba15094db8fa")
@Creatable
public class EditionDialogService {
    @objid ("5df212d3-dece-47ac-bc1d-532de623cac4")
    @Inject
    @Optional
    private ModelioEnv env;

    @objid ("7bf945e4-49f3-4ab9-905f-5f8edb1c8176")
    private PanelContributions panelContributions;

    @objid ("1affc396-635a-4cbd-8177-a6ece84ffa0a")
    @Optional
    @Inject
    void onEditElement(@UIEventTopic(ModelioEventTopics.EDIT_ELEMENT) final MObject mObject, final IEclipseContext eclipseContext, @Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        final IProjectService ps = eclipseContext.get(IProjectService.class);
        
        // For some metaclasses, we want no properties edition dialog on 'edit'
        // event because we have a dedicated editor
        // alternative.
        if (EditionDialogSupport.isSupported(mObject)) {
            final EditElementDialog dlg = new EditElementDialog(shell, getRelevantPanels(mObject), ps.getSession());
            dlg.setEditedElement((Element) mObject);
            dlg.open();
        }
    }

    @objid ("d31c607f-307e-4bec-94a6-43dc02922607")
    @Optional
    @Inject
    void onEditProperties(@UIEventTopic(ModelioEventTopics.EDIT_PROPERTIES) final MObject mObject, final IEclipseContext eclipseContext, @Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        final IProjectService ps = eclipseContext.get(IProjectService.class);
        
        final EditElementDialog dlg = new EditElementDialog(shell, getRelevantPanels(mObject), ps.getSession());
        dlg.setEditedElement((Element) mObject);
        dlg.open();
    }

    @objid ("d147a8c3-5930-4558-a926-c97f33d70dc6")
    private List<PanelDescriptor> getRelevantPanels(final MObject mObject) {
        // Collect and add panels
        return this.panelContributions.getPanels(mObject);
    }

    @objid ("d5842232-0199-467d-b6be-88e24d13716c")
    @PostConstruct
    private void postConstruct() {
        this.panelContributions = new PanelContributions();
    }

    @objid ("300667b4-ffa2-4a94-a84f-83baf244532d")
    @SuppressWarnings("unused")
    @Optional
    @Inject
    void onOpenProject(@UIEventTopic(ModelioEventTopics.PROJECT_OPENED) final GProject project, final IEclipseContext eclipseContext) {
        this.panelContributions.initializeExtensions(eclipseContext);
    }

    @objid ("7e8e4a6c-f5ca-4f69-ba4d-49afc4565c80")
    @SuppressWarnings("unused")
    @Optional
    @Inject
    void onCloseProject(@UIEventTopic(ModelioEventTopics.PROJECT_CLOSED) final GProject project) {
        this.panelContributions.clearExtensions();
    }

    @objid ("3fe3e0e9-21fe-4bca-ad01-0dcf0f3bc8e3")
    private static class EditionDialogSupport {
        @objid ("df169ff3-0a09-4d5d-9b7d-229e4614aa2a")
        private static final String DIRECTORY = "directory";

        @objid ("b1184a73-ffce-46c5-b147-7f25cceb3e51")
        private static final String FILE = "file";

        @objid ("291586e5-4650-4da2-8724-de6f1ad35d54")
        private static final String URL = "url";

        @objid ("94e28878-f2ff-4267-b8b7-1d883241c496")
        private static final String MODULE_NAME = "ModelerModule";

        @objid ("6616c8c6-b345-4946-ac2b-fec97e74b18f")
        private static final String MAIL = "mail";

        @objid ("e1a6aaf6-e852-4036-9b2d-2ada3ec4c1b6")
        public static boolean isSupported(final MObject obj) {
            if (obj != null) {
                final String qName = obj.getMClass().getQualifiedName();
                if (obj instanceof AbstractDiagram) {
                    return false;
                } else if (obj instanceof MatrixDefinition) {
                    return false;
                } else if (qName.equals("Analyst.Dictionary")) {
                    return false;
                } else if (qName.startsWith("Analyst") && qName.endsWith("Container")) {
                    // TODO this is kind of ugly...
                    return false;
                } else if (obj instanceof AbstractResource) {
                    return false;
                } else if (obj instanceof Artifact) {
                    final Artifact artifact = (Artifact) obj;
                    if (artifact.isStereotyped(EditionDialogSupport.MODULE_NAME, EditionDialogSupport.FILE) || artifact.isStereotyped(EditionDialogSupport.MODULE_NAME, EditionDialogSupport.DIRECTORY)) {
                        if (artifact.getFileName().length() > 0) {
                            return false;
                        }
                    }
                    if (artifact.isStereotyped(EditionDialogSupport.MODULE_NAME, EditionDialogSupport.URL)) {
                        if (artifact.getFileName().length() > 0) {
                            return false;
                        }
                    }
                    if (artifact.isStereotyped(EditionDialogSupport.MODULE_NAME, EditionDialogSupport.MAIL)) {
                        if (artifact.getFileName().length() > 0) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     * This class is responsible for collecting the applicable panels for a
     * given element to be shown in the element edition dialog.
     * <p>
     * Applicable panels come from:
     * <ul>
     * <li>contributions to the extension point</li>
     * <li>{@link IRTModule#getPropertyPanels()}</li>
     * </ul>
     */
    @objid ("015e02b9-a4ef-45b3-9e89-51ad6bce0757")
    private static class PanelContributions {
        @objid ("35b4fd20-cffa-4603-978c-44c231e06b25")
        private IEclipseContext eclipseContext;

        @objid ("22f7ba91-97ec-439f-8e01-79ef2d197463")
        private List<PanelDescriptor> extensionStaticPanels;

        /**
         * Get the edition panels for a model object
         * 
         * @param obj the element to edit
         * @return the panels to display
         */
        @objid ("fff4eb4b-86c1-4b56-ad8d-0f042487b8f3")
        public List<PanelDescriptor> getPanels(final MObject obj) {
            final List<PanelDescriptor> results = new ArrayList<>();
            
            // Primary extension panels
            final Object input = new StructuredSelection(obj);
            for (final PanelDescriptor ext : this.extensionStaticPanels) {
                if (ext.getPanel().isRelevantFor(input)) {
                    results.add(ext);
                }
            }
            
            // Modules panels
            // Build the contributing module panels
            // Things are getting tricky here. Modules can contribute two kinds
            // of panel: IModulePropertyPage or IPanelProvider instances.
            //
            // For IModulePropertyPage, things are straightforward as these
            // objects does not deal with SWT stuff at all (they are merely
            // a generic data model).
            // It is therefore safe to instantiate and dispose these objects.
            //
            // IPanelProvider on the contrary are strongly linked to SWT stuff,
            // especially the parent of their SWT widget tree.
            // Modules only instantiate their property panel once and keep its instance
            // forever. If we just use this instance in our edition,
            // they can be disposed when the dialog closes (because their SWT
            // widget tree parent is disposed by the dialog). The
            // solution is to create a new IPanelProvider instance and to avoid
            // using the one returned by the module. These new
            // instances can later be disposed safely when the dialog closes.
            //
            final IModuleService moduleService = this.eclipseContext.get(IModuleService.class);
            for (final IRTModule module : moduleService.getStartedModules()) {
                for (final IModulePropertyPanel modulePanel : module.getPropertyPanels()) {
                    if (modulePanel instanceof IPanelProvider) {
                        // IPanelProvider contribution
                        if (((IPanelProvider) modulePanel).isRelevantFor(input)) {
                            final IPanelProvider pp = createPanelProviderClone(modulePanel);
                            if (pp != null) {
                                results.add(new PanelDescriptor(modulePanel.getRelevance(), modulePanel.getName(), modulePanel.getLabel(), pp));
                            }
                        }
                    } else if (modulePanel instanceof IModulePropertyPage) {
                        // IModulePropertyPage contribution
                        final IPanelProvider pp = new ModulePanelProvider((IModulePropertyPage) modulePanel);
                        ContextInjectionFactory.inject(pp, this.eclipseContext);
                        if (pp.isRelevantFor(input)) {
                            results.add(new PanelDescriptor(modulePanel.getRelevance(), modulePanel.getName(), modulePanel.getLabel(), pp));
                        }
                    }
                }
            }
            
            results.sort(new PanelSorter(obj));
            return results;
        }

        @objid ("1fbe8d0b-107f-48e6-b172-72533bf1d135")
        public PanelContributions() {
        }

        @objid ("b4de7b39-9ff1-454e-8976-1950511cfe28")
        public void initializeExtensions(final IEclipseContext context) {
            this.eclipseContext = context;
            this.extensionStaticPanels = new ArrayList<>();
            for (final IConfigurationElement entry : new ExtensionPointContributionManager("org.modelio.edition.dialogs.panels").getExtensions("panel")) {
                try {
                    final IPanelProvider panel = (IPanelProvider) entry.createExecutableExtension("class");
                    ContextInjectionFactory.inject(panel, this.eclipseContext);
                    this.extensionStaticPanels.add(new PanelDescriptor(entry.getAttribute("relevance"), entry.getAttribute("id"), entry.getAttribute("label"), panel));
                } catch (ClassCastException | InvalidRegistryObjectException | CoreException e) {
                    EditionDialogs.LOG.error(e);
                }
            }
        }

        @objid ("bbf2c04b-fc52-44d7-98fa-c7b5e431358f")
        private IPanelProvider createPanelProviderClone(final IModulePropertyPanel modulePanel) {
            try {
                final Constructor<?> ctor = modulePanel.getClass().getConstructor(IModule.class, String.class, String.class,
                        String.class);
                final IPanelProvider ppp = (IPanelProvider) ctor.newInstance(modulePanel.getModule(), modulePanel.getName(),
                        modulePanel.getLabel(), null);
            
                ContextInjectionFactory.inject(ppp, this.eclipseContext);
                return ppp;
            
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                EditionDialogs.LOG.error(e);
            }
            return null;
        }

        @objid ("37cf61fb-2c10-4fe8-becb-e39cfd0caf69")
        public void clearExtensions() {
            if (this.extensionStaticPanels != null) {
                this.extensionStaticPanels.clear();
            }
            this.eclipseContext = null;
        }

    }

    @objid ("c2709d1e-4d7e-498a-b9a8-644dceb20b63")
    private static class PanelSorter implements Comparator<PanelDescriptor> {
        @objid ("9674c446-d024-4417-828a-4d239dcf9436")
        private final MObject referencedObject;

        @objid ("710185c4-c6c9-48b0-9bc3-73449847adf3")
        public PanelSorter(final MObject input) {
            this.referencedObject = input;
        }

        @objid ("8e48800b-e7c3-49ad-97b4-5c3ccbed24d8")
        @Override
        public int compare(final PanelDescriptor d1, final PanelDescriptor d2) {
            return Integer.compare(getRelevanceNote(d2), getRelevanceNote(d1));
        }

        @objid ("6e409e61-afbc-4ec8-9718-78eb3b0d3574")
        private int getRelevanceNote(final PanelDescriptor p) {
            // Panel has no origin information => zero point
            if (p.getRelevance() != null) {
            
                // If relevance is '*' => any element matches => one point
                if (p.getRelevance().equals("*")) {
                    return 1;
                }
            
                // Formalism matching => two points
                if (this.referencedObject.getMClass().getOrigin().getName().equals(p.getRelevance())) {
                    return 2;
                }
            
                // Exact metaclass matching => three points
                if (this.referencedObject.getMClass().getQualifiedName().equals(p.getRelevance())) {
                    return 3;
                }
            
                // Module matching => four points
                if (this.referencedObject instanceof ModelElement) {
                    for (final Stereotype s : ((ModelElement) this.referencedObject).getExtension()) {
                        if (s.getModule() != null) {
                            if (s.getModule().getName().equals(p.getRelevance())) {
                                return 4;
                            }
                        }
                    }
                }
            }
            return 0;
        }

    }

}
