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

package org.modelio.app.project.ui.views.infos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.app.project.ui.views.urls.UrlEntry;
import org.modelio.gproject.data.project.ProjectDescriptor;

/**
 * This view displays information about the project currently selected in the
 * workspace project list as a set of 'sections'.
 * Practically, the view has to distinguish between two cases:
 * <ul>
 * <li>the displayed project is the currently opened project
 * <li>the displayed project is NOT the currently opened project
 * 
 * In the first case, most sections will allow modifying the project
 * configuration while such modifications are forbidden in the second case.
 * 
 * @author phv
 */
@objid ("aff39864-13b5-49d0-9efe-488f95e3247a")
public class ProjectInfosView {
    /**
     * The ID of the view as specified by the extension.
     */
    @objid ("63956d7c-31e3-437d-b6a3-0a182f6ce1fd")
    public static final String ID = "org.modelio.model.workspace.views.ProjectPageView";

    @objid ("9c1682fa-1ad8-4384-925f-f16cba2f4a4b")
    @Inject
    @Optional
    private MApplication application;

    @objid ("a61b4d04-6034-4677-abe4-2780a2ba9996")
    @Inject
    @Optional
    private EModelService modelService;

    @objid ("99e38519-ef08-4f6c-aeb9-ac6ceed36b6d")
    @Inject
    @Optional
    private EPartService partService;

    /**
     * The project adapter to the project that is currently being displayed by
     * the view.
     */
    @objid ("dc1da7b9-b10e-452d-9b3c-312b90f7f56a")
     ProjectAdapter projectAdapter;

    @objid ("f030d7c5-f037-4f24-8bc0-6a4363b3902c")
    private ProjectPageView projectInfoPage;

    /**
     * C'tor
     */
    @objid ("53cee2b3-5dc3-4606-b936-46b03118172d")
    public ProjectInfosView() {
    }

    /**
     * Creates the SWT controls.
     * <p>
     * Called by E4 injection.
     * 
     * @param parent the parent composite.
     */
    @objid ("a2f2df54-85b8-43f2-a193-b8f0e4207630")
    @PostConstruct
    public void createControls(final Composite parent) {
        this.projectInfoPage = new ProjectPageView();
        this.projectInfoPage.createControls(parent);
        
        updateProjectInfoPage();
    }

    /**
     * Workspace tree selection always comes as ProjectDescriptor.
     * 
     * @param selection the project selected in the workspace browser
     */
    @objid ("deac0c58-836a-4407-907c-ec280e6bbbd3")
    @Optional
    @Inject
    public void onSelectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        this.projectAdapter = null;
        if (selection != null) {
            List<ProjectDescriptor> projectDescriptors = getSelectedElements(selection);
            if (projectDescriptors.size() == 1) {
                this.projectAdapter = new ProjectAdapter(projectDescriptors.get(0));
            }
        }
        updateProjectInfoPage();
        updateBrowsers();
    }

    @objid ("a5c926ee-b46c-48b0-8568-f7b5bb7eb4bd")
    @Focus
    void setFocus() {
        // Do nothing here
    }

    @objid ("53585214-5d8b-4ad4-91fb-b53bd5b885e6")
    void updateBrowsers() {
        List<UrlEntry> urls;
        if (this.projectAdapter != null) {
            urls = this.projectAdapter.getUrls();
        } else {
            urls = new ArrayList<>();
        }
        // refresh documentation browsers
        MPartStack stack = (MPartStack) this.modelService.find("org.modelio.app.project.ui.partstack.infos", this.application);
        
        // delete previous browsers
        {
            for (MPart view : this.partService.getParts()) {
                if (ProjectPageView.ID.equals(view.getElementId())) {
                    final ProjectPageView projectPageView = (ProjectPageView) view.getObject();
                    if (projectPageView==null || !isUrlEntryExist(projectPageView.getUrl(),view.getLabel(), urls)) {
                        this.partService.hidePart(view, true /* force removal from model */);
                    }
                }
            }
        }
        
        // create new documentation browsers
        {
            for (UrlEntry entry : urls) {
                if (!isProjectPageViewExist(entry.url, entry.name)) {
                    MPart view = MBasicFactory.INSTANCE.createPart();
                    view.setElementId(ProjectPageView.ID);
                    view.setIconURI("platform:/plugin/org.modelio.app.project.ui/icons/url.png");
                    view.setContributionURI("bundleclass://"+AppProjectUiExt.PLUGIN_ID+"/"+ProjectPageView.class.getName());
                    view.setLabel(entry.name);
                    view.setCloseable(false);
                    // add the tab before the default "Information" tab
                    stack.getChildren().add(stack.getChildren().size() - 1, view);
                    this.partService.showPart(view, PartState.VISIBLE);
                    ((ProjectPageView) view.getObject()).setUrl(entry.url);
                }
            }
            stack.setSelectedElement(stack.getChildren().get(0));
        }
    }

    @objid ("4a4918f3-f579-4891-879e-a28b472175e1")
    private void updateProjectInfoPage() {
        if (this.projectInfoPage != null) {
            if (this.projectAdapter!=null && this.projectAdapter.getProjectDescriptor()!=null) {
                ProjectInfoHtmlGenerator htmlPage = new ProjectInfoHtmlGenerator(this.projectAdapter);
                if (!htmlPage.getPageUrl().isEmpty()) {
                    this.projectInfoPage.setUrl(htmlPage.getPageUrl());
                }
            } else {
                this.projectInfoPage.setUrl(null);
            }
        }
    }

    @objid ("a6c093a9-b0f2-4fbe-91fa-a960331d5af7")
    private boolean isUrlEntryExist(String url, String name, List<UrlEntry> urls) {
        for (UrlEntry entry : urls) {
            if (entry.url.equals(url) && entry.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @objid ("550025dd-e190-440d-be0b-7ad1ea07780d")
    private boolean isProjectPageViewExist(String url, String name) {
        for (MPart view : this.partService.getParts()) {
            if (view.getElementId().equals(ProjectPageView.ID)) {
                if (((ProjectPageView) view.getObject()).getUrl().equals(url) && view.getLabel().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @objid ("546ce08a-08b9-44a2-a3bd-485b5861734c")
    private List<ProjectDescriptor> getSelectedElements(final IStructuredSelection selection) {
        List<ProjectDescriptor> selectedElements = new ArrayList<>();
        if (selection.size() > 0) {
            Object[] elements = selection.toArray();
            for (Object element : elements) {
                if (element instanceof ProjectDescriptor) {
                    selectedElements.add((ProjectDescriptor) element);
                }
            }
        }
        return selectedElements;
    }

}
