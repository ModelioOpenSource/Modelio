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
package org.modelio.module.propertytab.propertytab;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.commands.ExpressionContext;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.eclipse.e4.ui.model.application.ui.MCoreExpression;
import org.eclipse.e4.ui.model.application.ui.MUiFactory;
import org.eclipse.e4.ui.model.application.ui.advanced.MAdvancedFactory;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.modelio.api.module.command.ActionLocation;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.api.module.propertiesPage.IModulePropertyPanel;
import org.modelio.gproject.gproject.GProject;
import org.modelio.module.propertytab.plugin.ModulePropertyTab;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.mda.infra.service.IModuleService;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.module.commands.ExecuteModuleActionHandler;
import org.modelio.platform.module.commands.IsVisibleExpression;
import org.modelio.platform.module.commands.ModuleCommandsRegistry;
import org.modelio.platform.project.services.IProjectService;
import org.osgi.framework.FrameworkUtil;

/**
 * This e4 processor handles the lifecycle of module property views and their (optionnal) toolbar.
 * <p>
 * Views are created at the e4 level when a module starts, and are removed when a module stops.
 * The {@link MPartStack} owning each view is persisted when the view is closed, to be restored when opening the view again (usually after opening a project).
 * </p>
 */
@objid ("c885dc10-1eba-11e2-9382-bc305ba4815c")
@SuppressWarnings("restriction")
public class ModulePropertyViewHandler {
    @objid ("044f5733-1ebb-11e2-9382-bc305ba4815c")
    private static final String BUNDLE_PREFIX = "bundleclass://";

    /**
     * Tag added on dynamic module views, needed for lookup.
     */
    @objid ("044f7e43-1ebb-11e2-9382-bc305ba4815c")
    private static final String MODULE_MPART_TAG = "View brought by a module";

    /**
     * Tag indicating the default perspective stack owning module view, needed for lookup.
     */
    @objid ("f3c84d5c-b2f3-4250-b083-825756b94633")
    private static final String MODULE_MPARTSTACK_TAG = "MODULE_STACK";

    /**
     * Tag added on dynamic module tool items, needed for lookup.
     */
    @objid ("044fa553-1ebb-11e2-9382-bc305ba4815c")
    private static final String MODULE_MTOOLITEM_TAG = "module property page toolbar contribution";

    /**
     * Id of the e4 perspective stack.
     */
    @objid ("7cd4d026-b6e9-4fb2-b320-fe9979d4d489")
    private static final String MPERSPECTIVESTACK_ID = "org.modelio.app.ui.stack.perspectives";

    @objid ("044f3022-1ebb-11e2-9382-bc305ba4815c")
    private static final String PLATFORM_PREFIX = "platform:/plugin/";

    @objid ("044f0911-1ebb-11e2-9382-bc305ba4815c")
    private static final String URI_SEPARATOR = "/";

    /**
     * Id of the e4 window.
     */
    @objid ("325d5ca8-d28c-4ff0-843a-fa6b1223af58")
    private static final String WINDOW_ID = "org.modelio.app.ui.trimmed";

    @objid ("f0e5eeb7-4854-44e8-966b-1357891385a1")
    private static ModulePropertyViewHandler INSTANCE;

    @objid ("d859579a-ef7c-4880-8fb4-972540589883")
    @Inject
    private MApplication mApplication;

    @objid ("c8862a33-1eba-11e2-9382-bc305ba4815c")
    @Execute
    void execute(final IEclipseContext context) {
        // Create an instance and store it so it can listen to module events.
        ModulePropertyViewHandler.INSTANCE = this;
        
    }

    /**
     * On module start, create the module's property view(s).
     */
    @objid ("c8865142-1eba-11e2-9382-bc305ba4815c")
    @Inject
    @Optional
    void onModuleStarted(@UIEventTopic(ModelioEventTopics.MODULE_STARTED) final IRTModule module, final IProjectService projectService, final EModelService modelService) {
        for (IModulePropertyPanel propertyPage : module.getPropertyPanels()) {
            MPart mPart = createModulePart(module, propertyPage, modelService);
        
            // Add the shared mPart to the window
            MTrimmedWindow trimmedWindow = modelService.findElements(this.mApplication, ModulePropertyViewHandler.WINDOW_ID, MTrimmedWindow.class, null).get(0);
            trimmedWindow.getSharedElements().add(mPart);
        
            // Add a placeholder where the view needs to be added for each perspective
            List<MPerspectiveStack> perspectiveStacks = modelService.findElements(this.mApplication, ModulePropertyViewHandler.MPERSPECTIVESTACK_ID, MPerspectiveStack.class, null);
            for (MPerspective perspective : modelService.findElements(perspectiveStacks.get(0), null, MPerspective.class, null)) {
                MPartStack mPartStack = findPreviousParentStack(modelService, perspective, StatePersistenceHelper.restoreState(projectService.getStatePreferences(), mPart.getElementId(), perspective.getElementId()));
                if (mPartStack != null) {
                    createPlaceholder(mPart, mPartStack);
                } else {
                    createPlaceholder(mPart, findDefaultModulePartStack(modelService, perspective));
                }
            }
        }
        
    }

    /**
     * On module stop, close the module's property view(s).
     */
    @objid ("c8867855-1eba-11e2-9382-bc305ba4815c")
    @Inject
    @Optional
    void onModuleStopped(@UIEventTopic(ModelioEventTopics.MODULE_STOPPED) final IRTModule module, final IProjectService projectService, final EModelService modelService) {
        // Get the shared mParts from the window
        MTrimmedWindow trimmedWindow = modelService.findElements(this.mApplication, ModulePropertyViewHandler.WINDOW_ID, MTrimmedWindow.class, null).get(0);
        for (MPart mPart : getModuleParts(module, modelService, trimmedWindow)) {
            mPart.setToBeRendered(false);
            trimmedWindow.getSharedElements().remove(mPart);
            mPart.setObject(null);
        
            // Remove placeholder where the view needs to be added from each perspective
            List<MPerspectiveStack> perspectiveStacks = modelService.findElements(this.mApplication, ModulePropertyViewHandler.MPERSPECTIVESTACK_ID, MPerspectiveStack.class, null);
            for (MPerspective perspective : modelService.findElements(perspectiveStacks.get(0), null, MPerspective.class, null)) {
                for (MPlaceholder placeholder : modelService.findElements(perspective, null, MPlaceholder.class, null)) {
                    if (mPart.equals(placeholder.getRef())) {
                        // Store parent in this perspective
                        StatePersistenceHelper.saveState(projectService.getStatePreferences(), mPart.getElementId(), perspective.getElementId(), placeholder.getParent().getElementId());
        
                        // Remove placeholder
                        placeholder.setParent(null);
                        placeholder.setRenderer(false);
                    }
                }
            }
        }
        
    }

    /**
     * When the project is closing, persist the module's property view(s) owners.
     */
    @objid ("ab2f056c-98a7-4597-af3a-e09aed007640")
    @Inject
    @Optional
    void onProjectClosing(@EventTopic(ModelioEventTopics.PROJECT_CLOSING) final GProject project, IProjectService projectService, final EModelService modelService, IModuleService moduleService) {
        for (IRTModule module : moduleService.getStartedModules()) {
            // Get the shared mParts from the window
            MTrimmedWindow trimmedWindow = modelService.findElements(this.mApplication, ModulePropertyViewHandler.WINDOW_ID, MTrimmedWindow.class, null).get(0);
            for (MPart mPart : getModuleParts(module, modelService, trimmedWindow)) {
                // Persist PartStack where the view needs to be added for each perspective
                List<MPerspectiveStack> perspectiveStacks = modelService.findElements(this.mApplication, ModulePropertyViewHandler.MPERSPECTIVESTACK_ID, MPerspectiveStack.class, null);
                for (MPerspective perspective : modelService.findElements(perspectiveStacks.get(0), null, MPerspective.class, null)) {
                    for (MPlaceholder placeholder : modelService.findElements(perspective, null, MPlaceholder.class, null)) {
                        if (mPart.equals(placeholder.getRef())) {
                            // Store parent in this perspective
                            StatePersistenceHelper.saveState(projectService.getStatePreferences(), mPart.getElementId(), perspective.getElementId(), placeholder.getParent().getElementId());
                        }
                    }
                }
            }
        }
        
    }

    /**
     * This method is used to handle visibility of the tool items (which <em>should</em> be handled by the visibleWhen MExpression but this doesn't work in current version of e4).
     */
    @objid ("c8873ba4-1eba-11e2-9382-bc305ba4815c")
    @SuppressWarnings({ "unused" })
    @Optional
    @Inject
    void onSelectionChanged(@Named(IServiceConstants.ACTIVE_SELECTION) final ISelection selection, final EModelService modelService) {
        for (MPart mPart : modelService.findElements(this.mApplication, null, MPart.class, Arrays.asList(ModulePropertyViewHandler.MODULE_MPART_TAG))) {
            final MToolBar toolbar = mPart.getToolbar();
            if (toolbar != null) {
                refreshToolbar(modelService, toolbar);
            }
        }
        
    }

    @objid ("c886c673-1eba-11e2-9382-bc305ba4815c")
    private MHandler createAndActivateHandler(MPart mPart, MCommand mCommand, IRTModule module, IModuleAction action) {
        // Instantiate the actual handler class
        Object handler = new ExecuteModuleActionHandler(module, action);
        // Fit it into a MHandler
        MHandler mHandler = MCommandsFactory.INSTANCE.createHandler();
        mHandler.setObject(handler);
        // Bind it to the corresponding mCommand
        mHandler.setCommand(mCommand);
        // Define scope of this handler as the browser view.
        mPart.getHandlers().add(mHandler);
        
        // If the mPart already have a context, it means the e4 model has already been read.
        // In this case, activate the handler "by hand" since this mPart of the model may not be read again.
        // Otherwise, the activation will be done automatically when the model is read.
        IEclipseContext context = mPart.getContext();
        if (context != null) {
            EHandlerService handlerService = context.get(EHandlerService.class);
            handlerService.activateHandler(mCommand.getElementId(), handler);
        }
        return mHandler;
    }

    /**
     * Create a module mPart, including its toolbar.
     */
    @objid ("4965892f-bc8d-4fd5-a441-e4305c8cfc2e")
    private MPart createModulePart(final IRTModule module, IModulePropertyPanel propertyPage, final EModelService modelService) {
        // First, create the mPart itself
        MPart mPart = MBasicFactory.INSTANCE.createPart();
        mPart.setElementId(module + "_" + propertyPage.getName());
        mPart.setContributorURI(ModulePropertyViewHandler.PLATFORM_PREFIX + FrameworkUtil.getBundle(ModulePropertyViewHandler.class).getSymbolicName());
        mPart.setContributionURI(ModulePropertyViewHandler.BUNDLE_PREFIX + FrameworkUtil.getBundle(ModulePropertyView.class).getSymbolicName() + ModulePropertyViewHandler.URI_SEPARATOR + ModulePropertyView.class.getName());
        
        List<String> tags = mPart.getTags();
        tags.add(ModulePropertyViewHandler.MODULE_MPART_TAG);
        tags.add(module.getName());
        tags.add(propertyPage.getName());
        
        mPart.setLabel(propertyPage.getLabel());
        Path iconPath = propertyPage.getIcon();
        if (iconPath != null) {
            mPart.setIconURI(iconPath.toUri().toString());
        }
        
        // Create a toolbar if any mCommand is to be placed in it
        List<IModuleAction> actions = module.getActions(ActionLocation.property);
        if (actions != null && !actions.isEmpty()) {
            mPart.setToolbar(createToolBar(module, mPart, actions, modelService));
        }
        return mPart;
    }

    /**
     * Create a placeholder for a module mPart into a mPart stack.
     */
    @objid ("07490af5-16ba-47ba-95a0-c35a2cc86aaa")
    private void createPlaceholder(MPart mPart, final MPartStack mPartStack) {
        if (mPartStack != null) {
            final MPlaceholder placeholder = MAdvancedFactory.INSTANCE.createPlaceholder();
            placeholder.setRef(mPart);
        
            mPartStack.getChildren().add(placeholder);
        
            if (!mPartStack.isToBeRendered()) {
                mPartStack.setToBeRendered(true);
            }
        }
        
    }

    /**
     * Create a toolbar for a module mPart, containing a button for each action.
     */
    @objid ("d9b4b90c-041e-4d54-8621-84ff1cef2ef4")
    private MToolBar createToolBar(final IRTModule module, MPart mPart, List<IModuleAction> actions, final EModelService modelService) {
        MToolBar toolbar = MMenuFactory.INSTANCE.createToolBar();
        toolbar.setVisible(true);
        toolbar.setToBeRendered(true);
        for (IModuleAction action : actions) {
            // MCommand
            MCommand mCommand = ModuleCommandsRegistry.getCommand(module, action);
            // MHandler
            final MHandler handler = createAndActivateHandler(mPart, mCommand, module, action);
        
            // MHandledItem
            MHandledToolItem item = createToolItem(action);
            // Bind to mCommand
            item.setCommand(mCommand);
        
            Expression visWhen = new IsVisibleExpression(handler.getObject(), item);
            MCoreExpression isVisibleWhenExpression = MUiFactory.INSTANCE.createCoreExpression();
            isVisibleWhenExpression.setCoreExpressionId("programmatic.value");
            isVisibleWhenExpression.setCoreExpression(visWhen);
        
            item.setVisibleWhen(isVisibleWhenExpression);
        
            // Add to the toolbar
            toolbar.getChildren().add(item);
        }
        
        refreshToolbar(modelService, toolbar);
        return toolbar;
    }

    @objid ("c886ed84-1eba-11e2-9382-bc305ba4815c")
    private MHandledToolItem createToolItem(IModuleAction action) {
        Path bitmapPath = action.getBitmapPath();
        String iconURI = bitmapPath != null ? bitmapPath.toUri().toString() : "";
        String label = action.getLabel();
        String tooltip = action.getTooltip();
        
        // create a new item
        MHandledToolItem item = MMenuFactory.INSTANCE.createHandledToolItem();
        item.setLabel(label);
        item.setTooltip(tooltip);
        item.setIconURI(iconURI);
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(true);
        item.getTags().add(ModulePropertyViewHandler.MODULE_MTOOLITEM_TAG);
        return item;
    }

    /**
     * Look for the first MPartStack in a perspective having the {@value #MODULE_MPARTSTACK_TAG} tag..
     */
    @objid ("b65b3826-3d5b-48fe-9729-fd97827e854a")
    private MPartStack findDefaultModulePartStack(final EModelService modelService, MPerspective perspective) {
        List<MPartStack> mPartStack = modelService.findElements(perspective, null, MPartStack.class, Arrays.asList(ModulePropertyViewHandler.MODULE_MPARTSTACK_TAG));
        return !mPartStack.isEmpty() ? mPartStack.get(0) : null;
    }

    /**
     * Look for a MPartStack by id in a perspective.
     */
    @objid ("0b9af244-c3f6-43ed-bef7-9bb6377f89de")
    private MPartStack findPreviousParentStack(EModelService modelService, MPerspective perspective, String stackId) {
        if (stackId != null) {
            List<MPartStack> mPartStack = modelService.findElements(perspective, stackId, MPartStack.class, null);
            if (!mPartStack.isEmpty()) {
                return mPartStack.get(0);
            }
        }
        return null;
    }

    @objid ("c88789c5-1eba-11e2-9382-bc305ba4815c")
    private String getModuleImagePath(IRTModule module) {
        String relativePath = module.getModuleImagePath();
        if (relativePath != null && !relativePath.isEmpty()) {
            final Path moduleDirectory = module.getConfiguration().getModuleResourcesPath();
            Path imageFile = moduleDirectory.resolve(relativePath.substring(1));
            if (Files.isRegularFile(imageFile)) {
                return imageFile.toUri().toString();
            }
        }
        return null;
    }

    /**
     * Get module parts from the window.
     */
    @objid ("1431b2fc-c375-486b-87de-8a7d2fc0eaf3")
    private List<MPart> getModuleParts(final IRTModule module, final EModelService modelService, final MTrimmedWindow trimmedWindow) {
        return modelService.findElements(trimmedWindow, null, MPart.class, Arrays.asList(ModulePropertyViewHandler.MODULE_MPART_TAG, module.getName()));
    }

    /**
     * A very nice and elegant ugly hack to force a layout of this f...g CTabFolder, a very well-known failure in Eclipse/SWT,
     * which does not layout toolbar properly unless you force it to believe it has been resized !
     */
    @objid ("c9f4f230-5549-4cbd-b3f3-ea6c1fe07044")
    private void refreshLayoutHack(final MToolBar toolbar) {
        Control c = (Control) toolbar.getWidget();
        while (c != null && !(c instanceof CTabFolder)) {
            c = c.getParent();
        }
        if (c != null) {
            final CTabFolder tabFolder = (CTabFolder) c;
            c.getDisplay().asyncExec(() -> {
                if (!tabFolder.isDisposed()) {
                    Rectangle r = tabFolder.getBounds();
                    r.height += -1;
                    tabFolder.setBounds(r);
                }
            });
        }
        
    }

    @objid ("71222a89-ccd7-43fb-a95a-0552a97c6eb1")
    private void refreshToolbar(final EModelService modelService, final MToolBar toolbar) {
        List<MHandledToolItem> toolItems = modelService.findElements(toolbar, null, MHandledToolItem.class, Arrays.asList(ModulePropertyViewHandler.MODULE_MTOOLITEM_TAG));
        
        final ExpressionContext expressionContext = new ExpressionContext(this.mApplication.getContext());
        
        for (MHandledToolItem toolItem : toolItems) {
            IsVisibleExpression expression = (IsVisibleExpression) (((MCoreExpression) toolItem.getVisibleWhen()).getCoreExpression());
            if (expression != null) {
                // Creates dependency on a predefined value that can be "poked" by the evaluation service
                ExpressionInfo info = expression.computeExpressionInfo();
                String[] names = info.getAccessedPropertyNames();
                for (String name : names) {
                    expressionContext.getVariable(name + ".evaluationServiceLink"); //$NON-NLS-1$
                }
                boolean visible = (expression.evaluate(expressionContext) != EvaluationResult.FALSE);
                if (visible != toolItem.isVisible()) {
                    toolItem.setVisible(visible);
                }
            }
        }
        
        refreshLayoutHack(toolbar);
        
    }

    /**
     * Each module view persists its current parent in every perspective.
     */
    @objid ("e811ae8a-8bde-4241-862d-6e01fdaba4af")
    private static class StatePersistenceHelper {
        @objid ("0e196d8c-584a-424e-92fd-935c2ec579c8")
        public static String restoreState(IPreferenceStore savedPrefs, String partId, String perspectiveId) {
            String value = savedPrefs.getString(StatePersistenceHelper.computeKey(partId, perspectiveId));
            if (value != null && !value.isEmpty()) {
                return value;
            } else {
                return null;
            }
            
        }

        @objid ("e56c0714-9245-4367-99e6-3d262f76564f")
        public static void saveState(IPreferenceStore prefs, String partId, String perspectiveId, String parentId) {
            if (prefs != null) {
                prefs.setValue(StatePersistenceHelper.computeKey(partId, perspectiveId), parentId);
            }
            
        }

        @objid ("b69739f5-b1f5-4fd0-a19c-f6f42c9f8861")
        private static String computeKey(String partId, String perspectiveId) {
            return ModulePropertyTab.PLUGIN_ID + "." + partId + "." + perspectiveId + ".parentId";
        }

    }

}
