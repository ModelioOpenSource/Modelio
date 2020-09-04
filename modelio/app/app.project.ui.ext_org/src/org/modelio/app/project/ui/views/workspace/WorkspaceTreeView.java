/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.app.project.ui.views.workspace;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.ui.closeproject.CloseProjectHandler;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.ui.progress.IModelioProgressService;

/**
 * Workspace tree viewer
 */
@objid ("186caf88-5c0d-4840-961b-328e56ec8877")
@SuppressWarnings ("restriction")
public class WorkspaceTreeView {
    /**
     * The popup menu ID as specified in the .e4xmi file.
     */
    @objid ("b68cdad5-6cfb-4e6b-9a41-47807a9353b6")
    private static final String POPUP_MENU_ID = "org.modelio.app.workspace.popupmenu";

    /**
     * The ID of the view as specified by the extension.
     */
    @objid ("d83720b7-c5f7-47ad-9736-7f802d483301")
    private static final String VIEW_ID = "org.modelio.model.workspace.views.BrowserView";

    @objid ("a47db1a4-d225-4ab9-b4a9-0a1244e5a314")
    @Inject
    @Optional
    private ESelectionService selectionService;

    @objid ("8353f115-7d41-4528-a04e-73d45113a987")
    @Inject
    @Optional
    private EMenuService service;

    @objid ("5633dec3-c84b-45b2-8f9e-4ac1e99decf0")
    @Inject
    private ECommandService commandService;

    @objid ("4e27fb23-a47e-4944-a2a1-5d1aa9ba4648")
    @Inject
    private EHandlerService handlerService;

    @objid ("52e3df53-e1d7-482c-bb75-3a09071233aa")
    private static final String OPENPROJECT_COMMAND_ID = "org.modelio.app.ui.command.openproject";

    @objid ("dfdd98f1-616b-4d8a-8740-1bf1c82ce56d")
    private Path workspacePath;

    @objid ("81ca3bc5-29fc-47eb-a4cf-8ee5594d64c3")
    private final ProjectCache cache = new ProjectCache();

    @objid ("eff65e7e-5368-4011-a9df-7392de4aec1e")
    @Inject
     IProjectService projectService;

    @objid ("71590ca6-3913-425c-9100-b102ee22b28a")
    private WatchService watchSvc;

    @objid ("6c9c1760-25e0-4ce8-b1d6-863a4ef44c0d")
    private TreeViewer viewer;

    /**
     * Constructor
     */
    @objid ("d62e4ef8-68a5-4a73-b3e7-d87820268fa0")
    public WorkspaceTreeView() {
    }

    @objid ("af66ff8b-a586-4edf-b112-165865aca1c4")
    @PostConstruct
    void createControls(final Composite parent) {
        this.viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        this.viewer.setContentProvider(new WksContentProvider());
        this.viewer.setLabelProvider(new WksLabelProvider(this.projectService, this.viewer.getTree().getFont()));
        this.viewer.setComparator(new WksNameSorter());
        this.viewer.setUseHashlookup(true);
        
        ColumnViewerToolTipSupport.enableFor(this.viewer);
        
        this.viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @SuppressWarnings ("synthetic-access")
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (WorkspaceTreeView.this.selectionService != null) {
                    WorkspaceTreeView.this.selectionService.setSelection((event.getSelection()));
                }
            }
        });
        
        this.viewer.addDoubleClickListener(new IDoubleClickListener() {
            @SuppressWarnings ("synthetic-access")
            @Override
            public void doubleClick(DoubleClickEvent event) {
                ParameterizedCommand openCommand = WorkspaceTreeView.this.commandService.createCommand(WorkspaceTreeView.OPENPROJECT_COMMAND_ID,
                        new HashMap<String, Object>());
                WorkspaceTreeView.this.handlerService.executeHandler(openCommand);
            }
        
        });
        
        Path path = this.projectService.getWorkspace();
        setWorkspacePath(path);
        
        this.service.registerContextMenu(this.viewer.getTree(), WorkspaceTreeView.POPUP_MENU_ID);
    }

    /**
     * Modify the workspace path.
     * 
     * @param path the workspace path.
     */
    @objid ("79544b5e-3ba2-4dec-babe-ddbfe62695a2")
    public void setWorkspacePath(final Path path) {
        AppProjectUi.LOG.info("Changing workspace to: %s", path.toString());
        this.workspacePath = path;
        File file = path.toFile();
        if ((file != null) && file.exists() && file.isDirectory()) {
            if (Files.isWritable(path)) {
                this.cache.load(path);
        
                final ProjectCache aCache = this.cache;
                this.viewer.getTree().getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        getViewer().setInput(aCache);
                    }
                });
        
                watchDirectory(path);
            } else {
                MessageDialog.openError(this.viewer.getTree().getDisplay().getActiveShell(),
                        AppProjectUiExt.I18N.getString("AccessWorkspaceWrite.failed.title"),
                        AppProjectUiExt.I18N.getMessage("AccessWorkspaceWrite.failed.message", path.toString()));
            }
        
        } else {
            AppProjectUi.LOG.error("Invalid workspace path: %s", path.toString());
        }
    }

    /**
     * @return the workspace path
     */
    @objid ("70137841-c589-42d5-8c7f-0cd422bf69a9")
    public Path getWorkspacePath() {
        return this.workspacePath;
    }

    /**
     * Refresh the workspace view.
     */
    @objid ("d320df36-2605-4e4b-883e-6865d9f1200a")
    private void refreshContents() {
        this.cache.refresh();
        asyncRefreshTree();
    }

    @objid ("30d503ec-3a46-4020-ab41-17856bc11fda")
    private void asyncRefreshTree() {
        this.viewer.getTree().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (isValid()) {
                    getViewer().refresh(true);
                }
            }
        });
    }

    /**
     * Select the project with the given name.
     * 
     * @param projectName the project to select
     */
    @objid ("40dec47b-3078-44bf-85ed-29e312c815d2")
    private void selectProject(final String projectName) {
        this.viewer.getTree().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                final ProjectDescriptor project = WorkspaceTreeView.this.cache.findProjectByName(projectName);
                if (project != null && !WorkspaceTreeView.this.viewer.getControl().isDisposed()) {
                    setFocus();
                    getViewer().setSelection(new StructuredSelection(project), true);
                }
            }
        });
    }

    @objid ("fc130275-e00f-40a3-84d1-8cd997329b93")
    @Focus
    void setFocus() {
        this.viewer.getTree().setFocus();
    }

    /**
     * Called when the contents of the workspace are known to have changed (add/removing project).
     * 
     * @param wkspace the changed workspace
     */
    @objid ("922aa40f-b39e-434a-b243-86f5cce232ef")
    @Inject
    @Optional
    void onWorkspaceContentChange(@UIEventTopic (ModelioEventTopics.WORKSPACE_CONTENTS) final Path wkspace) {
        AppProjectUi.LOG.debug("onWorkspaceContentChange('%s')", wkspace.toString());
        if (isValid()) {
            refreshContents();
        }
    }

    @objid ("f32e571b-0a36-4e5d-99ba-9959aed05cd2")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic (ModelioEventTopics.PROJECT_OPENED) final GProject project, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell, MWindow window, final IModelioProgressService progressService, final StatusReporter statusReporter) {
        AppProjectUi.LOG.debug("onProjectOpened() %s", project.getName());
        
        // FIXME Misplaced
        IWindowCloseHandler handler = new IWindowCloseHandler() {
            @Override
            public boolean close(MWindow windoww) {
                GProject openedProject = WorkspaceTreeView.this.projectService.getOpenedProject();
                if (CloseProjectHandler.saveBeforeClose(shell, WorkspaceTreeView.this.projectService, openedProject, progressService, statusReporter)) {
                    if (openedProject != null) {
                        WorkspaceTreeView.this.projectService.closeProject(openedProject, true);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        };
        
        window.getContext().set(IWindowCloseHandler.class, handler);
        
        asyncRefreshTree();
    }

    /**
     * Called when the current opened project has been closed
     * 
     * @param project the closed project
     */
    @objid ("12a4a585-6877-4f9f-8ffd-a2e2225d5cc9")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic (ModelioEventTopics.PROJECT_CLOSED) final GProject project) {
        AppProjectUi.LOG.debug("onProjectClosed() %s", project);
        // fire a whole workspace contents refresh (reloading the cache) to
        // ensure that project descriptors are "re-read" from disc
        if (isValid()) {
            refreshContents();
        }
    }

    /**
     * Called when the currently opened project has been saved.
     * 
     * @param project the saved project
     */
    @objid ("2812c2d1-fd94-448c-b8fe-47f4d64de83a")
    @Inject
    @Optional
    void onProjectSaved(@UIEventTopic (ModelioEventTopics.PROJECT_SAVED) final GProject project) {
        if (isValid()) {
            refreshContents();
            selectProject(project.getName());
        }
    }

    /**
     * Called when current workspace has been changed (another workspace was chosen)
     * 
     * @param wkspace the new workspace
     */
    @objid ("1205faf1-03d1-4c56-92d2-b9a96474f31b")
    @Inject
    @Optional
    void onWorkspaceSwitch(@EventTopic (ModelioEventTopics.WORKSPACE_SWITCH) final Path wkspace) {
        AppProjectUi.LOG.debug("onWorkspaceSwitch() ", wkspace.toString());
        setWorkspacePath(wkspace);
    }

    @objid ("adb3ef1a-05ca-4894-a0c1-44aef5f664a9")
    TreeViewer getViewer() {
        return this.viewer;
    }

    /**
     * Tells whether the tree view is initialized and not disposed.
     * 
     * @return <code>true</code> if the viewer is usable else <code>false</code> .
     */
    @objid ("b5bd7783-1bd7-4bc8-89b4-2689d96b18e8")
    boolean isValid() {
        return (this.viewer != null) && (this.viewer.getTree() != null) && !this.viewer.getTree().isDisposed();
    }

    /**
     * Watch the given workspace directory for modifications and refresh the tree viewer in this case.
     * 
     * @param path the workspace directory to watch
     */
    @objid ("181a93a1-e02f-4abf-a686-81b259b36176")
    private void watchDirectory(final Path path) {
        try {
            if (this.watchSvc != null) {
                this.watchSvc.close();
            }
        
            final int POLL_TIME = 1000; // wait time in milliseconds between 2
            // polls
        
            final WatchService newWatchSvc = path.getFileSystem().newWatchService();
            this.watchSvc = newWatchSvc;
        
            path.register(newWatchSvc, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        
            Display display = getViewer().getControl().getDisplay();
            display.asyncExec(new Runnable() {
                @Override
                public void run() {
                    try {
                        WatchKey wk = newWatchSvc.poll();
                        if (wk != null) {
                            wk.pollEvents();
                            onWorkspaceContentChange(path);
                            wk.reset();
                        }
        
                        // schedule next poll
                        display.timerExec(POLL_TIME, this);
                    } catch (@SuppressWarnings ("unused") ClosedWatchServiceException e) {
                        // it's time to stop
                    }
                }
            });
        
        } catch (IOException e) {
            AppProjectUi.LOG.error(e);
        }
    }

    /**
     * E4 destructor
     */
    @objid ("9b2285ca-b99b-42d3-9389-4caec6927085")
    @PreDestroy
    void onDestroy() {
        if (this.watchSvc != null) {
            try {
                this.watchSvc.close();
            } catch (IOException e) {
                AppProjectUi.LOG.debug(e);
            }
        }
    }

    @objid ("8209e2b8-e792-419f-8168-b95776058425")
    @Inject
    @Optional
    void onWorkspaceNavigate(@UIEventTopic (ModelioEventTopics.WORKSPACE_NAVIGATE) final String projectName) {
        AppProjectUi.LOG.debug("onWorkspaceNavigate('%s')", projectName);
        if (isValid()) {
            selectProject(projectName);
        }
    }

    /**
     * Cache of all project descriptors found in the workspace.
     */
    @objid ("57355b14-8b1d-47ec-83e2-d54318c8d06b")
    static class ProjectCache {
        @objid ("74afc7ff-4e7f-4615-9fc2-b3babe40508f")
        public Path cachePath;

        @objid ("47a54307-d0d6-45d1-90ad-06f8be86ce35")
         ArrayList<ProjectDescriptor> cachedProjects = new ArrayList<>();

        @objid ("8fcfc271-9cd7-41ac-a846-a9b65247e1df")
        public ProjectCache() {
        }

        @objid ("da4708e5-6cd8-4954-8428-d1eb3c5d3c3e")
        public ProjectDescriptor[] getProjects() {
            return this.cachedProjects.toArray(new ProjectDescriptor[this.cachedProjects.size()]);
        }

        @objid ("fad5f01b-f984-49af-9e72-d22dbf312588")
        public void load(final Path aWorkspacePath) {
            this.cachePath = aWorkspacePath;
            rescan(this.cachePath);
        }

        @objid ("7be5fe49-88ed-4e8d-9b6e-14c92ec6bad8")
        public void refresh() {
            rescan(this.cachePath);
        }

        @objid ("3ce93b62-146c-429d-aa54-4daa87f9b38f")
        public ProjectDescriptor findProjectByName(final String projectName) {
            for (ProjectDescriptor projectDescriptor : this.cachedProjects) {
                if (projectDescriptor.getName().equals(projectName)) {
                    return projectDescriptor;
                }
            }
            return null;
        }

        @objid ("594ef091-309d-4145-aa74-85e916c68722")
        private void rescan(final Path aWorkspacePath) {
            this.cachedProjects.clear();
            
            // scan workspace
            if (Files.isDirectory(aWorkspacePath)) {
                try (DirectoryStream<Path> dirList = Files.newDirectoryStream(aWorkspacePath);) {
                    for (Path entry : dirList) {
                        if (GProjectFactory.isProjectSpace(entry)) {
                            try {
                                ProjectDescriptor projectDescriptor = GProjectFactory.readProjectDirectory(entry);
                                this.cachedProjects.add(projectDescriptor);
                            } catch (IOException e) {
                                AppProjectUi.LOG.error("Invalid project found : " + entry);
                                AppProjectUi.LOG.debug(e);
                            }
                        }
                    }
                } catch (Exception e) {
                    AppProjectUi.LOG.error(e);
                }
            }
        }

    }

}
