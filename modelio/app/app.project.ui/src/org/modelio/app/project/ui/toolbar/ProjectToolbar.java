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

package org.modelio.app.project.ui.toolbar;

import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.renderers.swt.TrimBarLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.model.ui.swt.trimbarcomponent.TrimBarComponent;
import org.modelio.platform.ui.UIColor;

/**
 * Class used to fill a project-level toolbar.
 * <p>
 * Relies on a dynamic ToolControl in the e4 model.
 * </p>
 */
@objid ("cd272ae5-d4de-418c-8b75-eb0e09e6dc36")
@SuppressWarnings ("restriction")
public class ProjectToolbar extends TrimBarComponent {
    @objid ("8603d4b0-d6bc-40ac-9d08-f7fc51f6c58c")
    private static final String OPENCONFIGURATOR_COMMAND_ID = "org.modelio.app.ui.command.openprojectconfigurator";

    @objid ("4772aaf2-d8a8-4ebb-9060-396aab0e12e2")
    private static final String REDO_COMMAND_ID = "org.eclipse.ui.edit.redo";

    @objid ("bc7462b7-21a5-4357-b2f7-8cd7ac571e97")
    private static final String SAVE_COMMAND_ID = "org.eclipse.ui.file.save";

    @objid ("0f44b9e8-6d46-42c8-9680-0a878a8f91c0")
    private static final String UNDO_COMMAND_ID = "org.eclipse.ui.edit.undo";

    @objid ("d39b2334-c448-4145-8a36-1ebf9b7385c3")
    @Inject
    private ECommandService commandService;

    @objid ("561edcd5-0fef-401e-86bc-f94fecfd649b")
    @Inject
    private IEclipseContext context;

    /**
     * Unique instance of {@link SelectionListener} launching the execution of a e4 handler.
     */
    @objid ("c33f4e63-228e-4d6b-8289-b66c0f421f7c")
    private final SelectionListener executionListener = new SelectionListener() {
        @Override
        public void widgetSelected(final SelectionEvent e) {
            final Object data = e.widget.getData();
            if (data instanceof ParameterizedCommand) {
                ProjectToolbar.this.handlerService.executeHandler((ParameterizedCommand) data);
            }
        }
        @Override
        public void widgetDefaultSelected(final SelectionEvent e) {
            // Nothing to do
        }
    };

    @objid ("ae27e308-232e-4f41-8f82-4f0d626831a5")
    @Inject
    private EHandlerService handlerService;

    @objid ("41b49dab-d7df-43a4-afbf-c5a2e30201a7")
    @Inject
    @Optional
    private MPart part;

    @objid ("fc250347-e968-462c-802d-787a96512e2d")
    private static final Image OPENCONFIGURATOR;

    @objid ("8ca4a62a-c87a-400c-8229-7259349022ea")
    private static final Image REDO;

    @objid ("440c955b-7228-4762-ad13-75d1dc25753c")
    private static final Image SAVE;

    @objid ("9e4c6ede-3e5f-4f24-bd90-a47b05d58967")
    private static final Image UNDO;

    @objid ("ad441661-44c8-407d-bf87-74987941eca1")
    public ProjectToolbar() {
        super(AppProjectUi.I18N.getString("ProjectToolbar.ProjectZone.label"));
    }

    /**
     * Initialize the SWT toolbar.
     * 
     * @param parent a widget which will be the parent of the new SWT components.
     */
    @objid ("afed38c0-2950-46c1-9796-395a69755d55")
    @Override
    protected Control createControl(final Composite parent) {
        final TrimBarLayout layout = (TrimBarLayout) parent.getParent().getLayout();
        
        layout.marginTop = 1;
        layout.marginBottom = 1;
        
        parent.getParent().addPaintListener(new PaintListener() {
        
            @Override
            public void paintControl(final PaintEvent e) {
                final Rectangle r = ((Composite) e.getSource()).getClientArea();
                e.gc.setForeground(UIColor.SWT_WIDGET_NORMAL_SHADOW);
                e.gc.setLineDash(new int[] { 1, 1 });
                e.gc.drawLine(r.x, r.y, r.x + r.width, r.y);
                e.gc.drawLine(r.x, r.y + r.height - 1, r.x + r.width, r.y + r.height - 1);
            }
        });
        
        // Create a toolbar.
        final ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
        
        // Add tool items
        createSaveToolItem(toolBar);
        createSeparator(toolBar);
        createUndoToolItem(toolBar);
        createRedoToolItem(toolBar);
        createSeparator(toolBar);
        createOpenConfiguratorToolItem(toolBar);
        return toolBar;
    }

    @objid ("01901549-3ea4-4caf-8833-f449c96e4e03")
    @Override
    protected ToolBar getControl() {
        return (ToolBar) super.getControl();
    }

    /**
     * Create a tool item saving the project.
     * 
     * @return a new toolbar item.
     */
    @objid ("98867986-e184-41e3-b9a4-25f4f27896c9")
    private ToolItem createOpenConfiguratorToolItem(final ToolBar toolBar) {
        // Create a new handled item
        final ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        
        // Set tooltip and icon
        item.setToolTipText(AppProjectUi.I18N.getString("ProjectToolbar.OpenConfigurator.tooltip"));
        item.setImage(ProjectToolbar.OPENCONFIGURATOR);
        
        // Get the e4 command
        final ParameterizedCommand command = ProjectToolbar.this.commandService.createCommand(ProjectToolbar.OPENCONFIGURATOR_COMMAND_ID, new HashMap<>());
        item.setData(command);
        
        item.addSelectionListener(this.executionListener);
        return item;
    }

    /**
     * Create a tool item saving the project.
     * 
     * @return a new toolbar item.
     */
    @objid ("57309b01-8e75-47b6-8cca-1debffb8e230")
    private ToolItem createRedoToolItem(final ToolBar toolBar) {
        // Create a new handled item
        final ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        
        // Set tooltip and icon
        item.setToolTipText(AppProjectUi.I18N.getString("ProjectToolbar.Redo.tooltip"));
        item.setImage(ProjectToolbar.REDO);
        
        // Get the e4 command
        final ParameterizedCommand command = ProjectToolbar.this.commandService.createCommand(ProjectToolbar.REDO_COMMAND_ID, new HashMap<>());
        item.setData(command);
        
        item.addSelectionListener(this.executionListener);
        return item;
    }

    /**
     * Create a tool item saving the project.
     * 
     * @return a new toolbar item.
     */
    @objid ("c68cda7c-38a0-48c2-b8d0-0057c44e25a7")
    private ToolItem createSaveToolItem(final ToolBar toolBar) {
        // Create a new handled item
        final ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        
        // Set tooltip and icon
        item.setToolTipText(AppProjectUi.I18N.getString("ProjectToolbar.Save.tooltip"));
        item.setImage(ProjectToolbar.SAVE);
        
        // Get the e4 command
        final ParameterizedCommand command = ProjectToolbar.this.commandService.createCommand(ProjectToolbar.SAVE_COMMAND_ID, new HashMap<>());
        item.setData(command);
        
        item.addSelectionListener(this.executionListener);
        return item;
    }

    @objid ("979522cd-7d76-436f-8b07-1f57c5e400dd")
    @SuppressWarnings ("unused")
    private void createSeparator(final ToolBar toolBar) {
        final ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);
    }

    /**
     * Create a tool item saving the project.
     * 
     * @return a new toolbar item.
     */
    @objid ("34273821-1e18-4f14-a360-16cde16e3438")
    private ToolItem createUndoToolItem(final ToolBar toolBar) {
        // Create a new handled item
        final ToolItem item = new ToolItem(toolBar, SWT.PUSH);
        
        // Set tooltip and icon
        item.setToolTipText(AppProjectUi.I18N.getString("ProjectToolbar.Undo.tooltip"));
        item.setImage(ProjectToolbar.UNDO);
        
        // Get the e4 command
        final ParameterizedCommand command = ProjectToolbar.this.commandService.createCommand(ProjectToolbar.UNDO_COMMAND_ID, new HashMap<>());
        item.setData(command);
        
        item.addSelectionListener(this.executionListener);
        return item;
    }

    @objid ("dc87601d-d919-4819-a000-4d0171c6960d")
    @Inject
    @Optional
    private void onSelectionChanged(@SuppressWarnings ("unused") @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        refreshStatus();
    }

    @objid ("0c3a32d0-a104-4333-b3d6-5b62f3d77bbc")
    @Override
    protected void postCreate() {
        super.postCreate();
    }

    @objid ("1ec4fcce-1aac-414d-941a-73ee51841be1")
    @Inject
    @Optional
    private void onProjectClosed(@SuppressWarnings ("unused") @EventTopic (ModelioEventTopics.PROJECT_CLOSED) final GProject project) {
        getControl().getDisplay().asyncExec(() -> setVisible(false));
    }

    @objid ("b13b2f3e-ec69-4652-8cf6-cce977ac9c25")
    @Inject
    @Optional
    private void onProjectOpened(@EventTopic (ModelioEventTopics.PROJECT_OPENED) final GProject openedProject) {
        getControl().getDisplay().asyncExec(() -> setVisible(true));
        
        // Add a model change listener to refresh button status
        openedProject.getSession().getModelChangeSupport().addModelChangeListener(event -> {
            refreshStatus();
        });
    }

    /**
     * Refresh 'enabled' status of each tool item
     */
    @objid ("66412ea2-bae3-41bd-878b-e68f322c63dd")
    private void refreshStatus() {
        final ToolBar toolbar = getControl();
        if (toolbar != null) {
            toolbar.getDisplay().asyncExec(() -> {
                if (!toolbar.isDisposed()) {
                    for (final ToolItem child : toolbar.getItems()) {
                        final Object data = child.getData();
                        if (data instanceof ParameterizedCommand) {
                            child.setEnabled(this.handlerService.canExecute((ParameterizedCommand) data, this.context));
                        }
                    }
                }
            });
        }
    }

    @objid ("4e261219-73c6-45b3-aae7-1236708e29a8")
    @Inject
    @Optional
    private void onProjectSaved(@SuppressWarnings ("unused") @EventTopic (ModelioEventTopics.PROJECT_SAVED) final GProject project) {
        refreshStatus();
    }


static {
        final ImageDescriptor image1 = AbstractUIPlugin.imageDescriptorFromPlugin(AppUi.PLUGIN_ID, "icons/save.png");
        if (image1 != null) {
            SAVE = image1.createImage();
        } else {
            SAVE = null;
        }

        final ImageDescriptor image2 = AbstractUIPlugin.imageDescriptorFromPlugin(AppUi.PLUGIN_ID, "icons/undo.png");
        if (image2 != null) {
            UNDO = image2.createImage();
        } else {
            UNDO = null;
        }

        final ImageDescriptor image3 = AbstractUIPlugin.imageDescriptorFromPlugin(AppUi.PLUGIN_ID, "icons/redo.png");
        if (image3 != null) {
            REDO = image3.createImage();
        } else {
            REDO = null;
        }

        final ImageDescriptor image4 = AbstractUIPlugin.imageDescriptorFromPlugin(AppUi.PLUGIN_ID, "icons/config.png");
        if (image4 != null) {
            OPENCONFIGURATOR = image4.createImage();
        } else {
            OPENCONFIGURATOR = null;
        }

    }
}
