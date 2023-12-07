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
package org.modelio.app.ui.persp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.internal.util.PrefUtil;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.gproject.core.IGProject;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.model.ui.swt.trimbarcomponent.TrimBarComponent;
import org.modelio.platform.project.services.IProjectService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * Class used to fill a dynamic perspective selection toolbar.
 * <p>
 * Is is declared as a workbench listener to refresh its items when workbenches change.
 * </p>
 * <p>
 * Relies on a dynamic ToolControl in the e4 model.
 * </p>
 */
@objid ("4db4fdd8-3a8f-4794-99e6-eea50769bdc6")
@SuppressWarnings ("restriction")
public class PerspectiveSwitcher extends TrimBarComponent {
    @objid ("a6605b37-b873-4011-b716-0a62f2e0699f")
    public static final String PERSPECTIVE_SWITCHER_ID = "org.eclipse.e4.ui.PerspectiveSwitcher"; // // $NON-NLS-1$
    

    @objid ("d72c1e5a-a0e2-49f9-8090-66121b599d35")
    private static final String PERSPECTIVE_SUBDIR = "perspective";

    @objid ("885237b4-aec9-45f5-8819-89c804e63ae9")
    private EventHandler childrenHandler = new EventHandler() {
                @Override
                public void handleEvent(Event event) {
                    if (getControl().isDisposed()) {
                        return;
                    }
                    Object changedObj = event.getProperty(UIEvents.EventTags.ELEMENT);
                    String eventType = (String) event.getProperty(UIEvents.EventTags.TYPE);
                    if (changedObj instanceof MWindow && UIEvents.EventTypes.ADD.equals(eventType)) {
                        MUIElement added = (MUIElement) event.getProperty(UIEvents.EventTags.NEW_VALUE);
                        if (added instanceof MPerspectiveStack) {
                        }
                    }
                    if (PerspectiveSwitcher.this.toolControl == null || !(changedObj instanceof MPerspectiveStack)) {
                        return;
                    }
                    MWindow perspWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor((MUIElement) changedObj);
                    MWindow switcherWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor(PerspectiveSwitcher.this.toolControl);
                    if (perspWin != switcherWin) {
                        return;
                    }
                    if (UIEvents.EventTypes.ADD.equals(eventType)) {
                        MPerspective added = (MPerspective) event.getProperty(UIEvents.EventTags.NEW_VALUE);
                        // Adding invisible elements is a NO-OP
                        if (!added.isToBeRendered()) {
                            return;
                        }
                        addPerspectiveItem(added);
                    } else if (UIEvents.EventTypes.REMOVE.equals(eventType)) {
                        MPerspective removed = (MPerspective) event.getProperty(UIEvents.EventTags.OLD_VALUE);
                        // Removing invisible elements is a NO-OP
                        if (!removed.isToBeRendered()) {
                            return;
                        }
                        removePerspectiveItem(removed);
                    }
                }
            };

    @objid ("9db7febd-c26c-48ae-8baa-ee0be31477a5")
    @Inject
    private IEventBroker eventBroker;

    @objid ("c1f8615f-e7cf-440c-bd31-0846bee3aeb2")
    private EventHandler labelHandler = new EventHandler() {
                @Override
                public void handleEvent(Event event) {
                    if (getControl().isDisposed()) {
                        return;
                    }
                    MUIElement changedElement = (MUIElement) event.getProperty(UIEvents.EventTags.ELEMENT);
                    if (PerspectiveSwitcher.this.toolControl == null || !(changedElement instanceof MPerspective)) {
                        return;
                    }
                    String attName = (String) event.getProperty(UIEvents.EventTags.ATTNAME);
                    Object newValue = event.getProperty(UIEvents.EventTags.NEW_VALUE);
                    MWindow perspWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor(changedElement);
                    MWindow switcherWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor(PerspectiveSwitcher.this.toolControl);
                    if (perspWin != switcherWin) {
                        return;
                    }
                    MPerspective perspective = (MPerspective) changedElement;
                    if (!perspective.isToBeRendered()) {
                        return;
                    }
                    for (ToolItem ti : getControl().getItems()) {
                        if (ti.getData() == perspective) {
                            updateToolItem(ti, attName, newValue);
                        }
                    }
                    // update the layout
                    refreshLayout();
                }
    
                private void updateToolItem(ToolItem ti, String attName, Object newValue) {
                    boolean showText = PrefUtil.getAPIPreferenceStore().getBoolean(
                            IWorkbenchPreferenceConstants.SHOW_TEXT_ON_PERSPECTIVE_BAR);
                    if (showText && UIEvents.UILabel.LABEL.equals(attName)) {
                        String newName = (String) newValue;
                        ti.setText(newName);
                    } else if (UIEvents.UILabel.TOOLTIP.equals(attName)) {
                        String newTTip = (String) newValue;
                        ti.setToolTipText(newTTip);
                    }
                }
            };

    @objid ("69b1f81a-0271-4613-8437-92b870a925a6")
    @Inject
    private EModelService modelService;

    @objid ("f31bf22c-2d52-49ba-a51a-7d628d6e6bf3")
    private EventHandler selectionHandler = new EventHandler() {
                @Override
                public void handleEvent(Event event) {
                    if (getControl().isDisposed()) {
                        return;
                    }
    
                    MUIElement changedElement = (MUIElement) event.getProperty(UIEvents.EventTags.ELEMENT);
                    if (PerspectiveSwitcher.this.toolControl == null || !(changedElement instanceof MPerspectiveStack)) {
                        return;
                    }
                    MWindow perspWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor(changedElement);
                    MWindow switcherWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor(PerspectiveSwitcher.this.toolControl);
                    if (perspWin != switcherWin) {
                        return;
                    }
                    MPerspectiveStack perspStack = (MPerspectiveStack) changedElement;
                    if (!perspStack.isToBeRendered()) {
                        return;
                    }
                    MPerspective selElement = perspStack.getSelectedElement();
                    for (ToolItem ti : getControl().getItems()) {
                        ti.setSelection(ti.getData() == selElement);
                    }
                }
            };

    @objid ("23c05a6b-0fb7-4963-8712-ecb2b42bc3a0")
    private EventHandler toBeRenderedHandler = new EventHandler() {
                @Override
                public void handleEvent(Event event) {
                    if (getControl().isDisposed()) {
                        return;
                    }
                    MUIElement changedElement = (MUIElement) event.getProperty(UIEvents.EventTags.ELEMENT);
                    if (PerspectiveSwitcher.this.toolControl == null || !(changedElement instanceof MPerspective)) {
                        return;
                    }
                    MWindow perspWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor(changedElement);
                    MWindow switcherWin = PerspectiveSwitcher.this.modelService.getTopLevelWindowFor(PerspectiveSwitcher.this.toolControl);
                    if (perspWin != switcherWin) {
                        return;
                    }
                    MPerspective persp = (MPerspective) changedElement;
                    if (!persp.getParent().isToBeRendered()) {
                        return;
                    }
                    if (changedElement.isToBeRendered()) {
                        addPerspectiveItem(persp);
                    } else {
                        removePerspectiveItem(persp);
                    }
                }
            };

    @objid ("659c81be-3abf-4cba-bf60-629042c6a679")
    @Inject
    private MToolControl toolControl;

    @objid ("5dcd64b8-73ce-4d3a-adfa-e1bef97fbb62")
    @Inject
    private MWindow window;

    @objid ("f83784a4-7238-4973-8b32-72c5c5bb4662")
    private Image perspectiveImage;

    @objid ("f957c856-3f6d-4535-bc36-0a6b13616cfa")
    public  PerspectiveSwitcher() {
        super(AppUi.I18N.getString("PerspectiveSwitcher.PerspectiveZone.label"));
    }

    @objid ("fb0a4250-aafb-40e5-8407-6a540b5c100e")
    @Override
    protected Control createControl(Composite parent) {
        ToolBar toolbar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
        
        toolbar.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                disposeTBImages();
            }
        
        });
        
        toolbar.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            @Override
            public void getName(AccessibleEvent e) {
                if (0 <= e.childID && e.childID < toolbar.getItemCount()) {
                    ToolItem item = toolbar.getItem(e.childID);
                    if (item != null) {
                        e.result = item.getToolTipText();
                    }
                }
            }
        });
        return toolbar;
    }

    @objid ("cf770f75-d6fa-43bc-9bdc-636e7258c258")
    protected ToolItem getItemFor(final MPerspective persp) {
        if (getControl() == null) {
            return null;
        }
        
        for (ToolItem ti : getControl().getItems()) {
            if (ti.getData() == persp) {
                return ti;
            }
        }
        return null;
    }

    @objid ("6eedef75-3a57-4c19-8e7c-418b7edc7ffd")
    @Override
    protected ToolBar getControl() {
        return (ToolBar) super.getControl();
    }

    @objid ("66397526-795c-4564-aa34-7b81edae5c40")
    @Override
    protected void postCreate() {
        MPerspectiveStack stack = getPerspectiveStack();
        if (stack != null) {
            // Create an item for each perspective that should show up
            for (MPerspective persp : stack.getChildren()) {
                if (persp.isToBeRendered()) {
                    if (persp.getTags().contains("user")) {
                        addPerspectiveItem(persp);
                    }
                }
            }
        }
        
    }

    @objid ("12303834-b2a9-4edc-85c7-b9f3c0ff7ecd")
    @Override
    protected void preDispose() {
        cleanUp();
    }

    @objid ("05d545b5-e3bc-41d9-9899-1ca90572f119")
    private ToolItem addPerspectiveItem(final MPerspective persp) {
        final ToolItem psItem = new ToolItem(getControl(), SWT.RADIO);
        psItem.setData(persp);
        
        String iconUri = persp.getIconURI();
        ImageDescriptor imageDescriptor = null;
        try {
            imageDescriptor = ImageDescriptor.createFromURL(new URL(iconUri));
        } catch (MalformedURLException e) {
            AppUi.LOG.error(e);
        }
        
        boolean foundImage = false;
        if (imageDescriptor != null) {
            final Image image = imageDescriptor.createImage(false);
            if (image != null) {
                psItem.setImage(image);
                psItem.addListener(SWT.Dispose, new Listener() {
                    @Override
                    public void handleEvent(org.eclipse.swt.widgets.Event event) {
                        image.dispose();
                    }
                });
                foundImage = true;
                psItem.setToolTipText(persp.getLocalizedLabel());
            }
        }
        
        if (!foundImage) {
            psItem.setText(persp.getLocalizedLabel());
            psItem.setToolTipText(persp.getLocalizedTooltip());
        }
        
        psItem.setSelection(persp == persp.getParent().getSelectedElement());
        
        /*
         * The perspective button supports a little trick here. - when simply pressed it selects the perspective. - when CTRL+ALT pressed it resets the perspective and restart Modelio.
         */
        psItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean reset = (e.stateMask & SWT.ALT) != 0 && (e.stateMask & SWT.CTRL) != 0;
                MPerspective p = (MPerspective) e.widget.getData();
                if (reset) {
                    // Switch to the perspective
                    p.getParent().setSelectedElement(p);
        
                    // Create the reset flag file and restart modelio
                    final IEclipseContext eclipseContext = E4Workbench.getServiceContext().getActiveChild();
        
                    IProjectService projectService = eclipseContext.get(IProjectService.class);
                    IGProject openedProject = projectService.getOpenedProject();
                    Path persistenceDir = openedProject.getPfs().getProjectRuntimePath().resolve(PERSPECTIVE_SUBDIR);
                    Path persistenceResetFlagFile = persistenceDir.resolve(p.getElementId() + ".reset");
                    try {
                        Files.createFile(persistenceResetFlagFile);
                        final IWorkbench workbench = eclipseContext.get(IWorkbench.class);
                        if (workbench != null) {
                            Display.getDefault().asyncExec(() -> workbench.restart());
                        }
                    } catch (IOException e1) {
        
                    }
                } else {
                    // Just switch to the perspective
                    p.getParent().setSelectedElement(p);
                }
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                MPerspective p = (MPerspective) e.widget.getData();
                p.getParent().setSelectedElement(p);
            }
        });
        
        // update the layout
        refreshLayout();
        return psItem;
    }

    @objid ("de015fef-0e01-41c6-9cd8-a5f9da432e09")
    @PreDestroy
    private void cleanUp() {
        if (this.perspectiveImage != null) {
            this.perspectiveImage.dispose();
            this.perspectiveImage = null;
        }
        
        this.eventBroker.unsubscribe(this.toBeRenderedHandler);
        this.eventBroker.unsubscribe(this.childrenHandler);
        this.eventBroker.unsubscribe(this.selectionHandler);
        this.eventBroker.unsubscribe(this.labelHandler);
        
    }

    /**
     * Initialize the SWT toolbar.
     * @param parent a widget which will be the parent of the new SWT components.
     */
    @objid ("a3491422-474c-4ebb-9b21-87e5982edded")
    @PostConstruct
    private void createWidget(final Composite parent, final MToolControl tc) {
        this.toolControl = tc;
    }

    @objid ("b11766e4-aead-42cb-a0e6-d3da0ec7cabd")
    private void disposeTBImages() {
        ToolItem[] items = getControl().getItems();
        for (ToolItem item : items) {
            Image image = item.getImage();
            if (image != null) {
                item.setImage(null);
                image.dispose();
            }
        }
        
    }

    @objid ("d6d98beb-5d47-41f3-bfd4-584f34127d17")
    private MPerspectiveStack getPerspectiveStack() {
        List<MPerspectiveStack> psList = this.modelService.findElements(this.window, null, MPerspectiveStack.class, null);
        if (psList.size() > 0) {
            return psList.get(0);
        }
        return null;
    }

    @objid ("bf5a465f-d409-4d19-92d7-36f34a61a34c")
    @PostConstruct
    private void init() {
        this.eventBroker.subscribe(UIEvents.ElementContainer.TOPIC_CHILDREN, this.childrenHandler);
        this.eventBroker.subscribe(UIEvents.UIElement.TOPIC_TOBERENDERED, this.toBeRenderedHandler);
        this.eventBroker.subscribe(UIEvents.ElementContainer.TOPIC_SELECTEDELEMENT, this.selectionHandler);
        this.eventBroker.subscribe(UIEvents.UILabel.TOPIC_ALL, this.labelHandler);
        
    }

    @objid ("3b16c889-4ecc-4cfd-85ac-ccf01216a8de")
    private void removePerspectiveItem(final MPerspective toRemove) {
        ToolItem psItem = getItemFor(toRemove);
        if (psItem != null) {
            psItem.dispose();
        }
        
        // update the layout
        refreshLayout();
        
    }

    @objid ("d252865a-a816-4196-8ba7-4b63f94cae56")
    @Inject
    @Optional
    private void onProjectClosed(@SuppressWarnings ("unused")
    @EventTopic (ModelioEventTopics.PROJECT_CLOSED) IGProject project) {
        getControl().getDisplay().asyncExec(() -> setVisible(false));
    }

    @objid ("c27c7634-0183-4e80-848e-37ac21da739c")
    @Inject
    @Optional
    private void onProjectOpened(@SuppressWarnings ("unused")
    @EventTopic (ModelioEventTopics.PROJECT_OPENED) final IGProject openedProject) {
        getControl().getDisplay().asyncExec(() -> setVisible(true));
    }

}
