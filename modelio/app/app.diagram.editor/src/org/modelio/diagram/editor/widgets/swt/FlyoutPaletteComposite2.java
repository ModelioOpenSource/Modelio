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

package org.modelio.diagram.editor.widgets.swt;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ButtonBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.FocusEvent;
import org.eclipse.draw2d.FocusListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Triangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.internal.ui.palette.PaletteColorUtil;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteMessages;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tracker;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.internal.DragCursors;

/**
 * The FlyoutPaletteComposite is used to show a flyout palette alongside another control. The flyout palette auto-hides (thus maximizing space) when not in use, but can also be pinned open if so desired. It will only be visible when the PaletteView is not.
 * 
 * Modified Phv: to reduce TitleLabel size and look (no more label and icon)
 * 
 * @author Pratik Shah
 * @since 3.0
 */
@objid ("65957c11-33f7-11e2-95fe-001ec947c8cc")
public class FlyoutPaletteComposite2 extends Composite {
    @objid ("65957c1e-33f7-11e2-95fe-001ec947c8cc")
    public static final int DEFAULT_PALETTE_SIZE = 130;

    @objid ("65957c20-33f7-11e2-95fe-001ec947c8cc")
    public static final int MIN_PALETTE_SIZE = 20;

    @objid ("65957c22-33f7-11e2-95fe-001ec947c8cc")
    public static final int MAX_PALETTE_SIZE = 500;

    @objid ("65957c24-33f7-11e2-95fe-001ec947c8cc")
    public static final int STATE_HIDDEN = 8;

    @objid ("65957c26-33f7-11e2-95fe-001ec947c8cc")
    public static final int STATE_EXPANDED = 1;

    @objid ("6597de43-33f7-11e2-95fe-001ec947c8cc")
    public static final int SASH_BUTTON_WIDTH = 11;

    /**
     * One of the two possible initial states of the flyout palette. This is the default one. When in this state, only the flyout palette's sash is visible.
     */
    @objid ("6597de45-33f7-11e2-95fe-001ec947c8cc")
    public static final int STATE_COLLAPSED = 2;

    /**
     * One of the two possible initial states of the flyout palette. When in this state, the flyout palette is completely visible and pinned open so that it doesn't disappear when the user wanders away from the flyout.
     */
    @objid ("6597de48-33f7-11e2-95fe-001ec947c8cc")
    public static final int STATE_PINNED_OPEN = 4;

    @objid ("659a409d-33f7-11e2-95fe-001ec947c8cc")
     boolean transferFocus = false;

    @objid ("659a409e-33f7-11e2-95fe-001ec947c8cc")
     int dock = PositionConstants.EAST;

    @objid ("659a409f-33f7-11e2-95fe-001ec947c8cc")
     int paletteState = FlyoutPaletteComposite2.STATE_HIDDEN;

    @objid ("659a40a0-33f7-11e2-95fe-001ec947c8cc")
     int paletteWidth = FlyoutPaletteComposite2.DEFAULT_PALETTE_SIZE;

    @objid ("659a40a1-33f7-11e2-95fe-001ec947c8cc")
     int minWidth = FlyoutPaletteComposite2.MIN_PALETTE_SIZE;

    @objid ("659a40a2-33f7-11e2-95fe-001ec947c8cc")
    private int cachedSize = -1;

    @objid ("659a40a3-33f7-11e2-95fe-001ec947c8cc")
    private int cachedState = -1;

    @objid ("659a40a4-33f7-11e2-95fe-001ec947c8cc")
    private int cachedLocation = -1;

    @objid ("659ca2f4-33f7-11e2-95fe-001ec947c8cc")
     int cachedTitleHeight = 24; // give it a default value

    @objid ("185b7bdd-3897-11e2-95fe-001ec947c8cc")
    private static final String PROPERTY_PALETTE_WIDTH = "org.eclipse.gef.ui.palette.fpa.paletteWidth"; // $NON-NLS-1$

    @objid ("18604090-3897-11e2-95fe-001ec947c8cc")
    private static final String PROPERTY_STATE = "org.eclipse.gef.ui.palette.fpa.state"; // $NON-NLS-1$

    @objid ("18650545-3897-11e2-95fe-001ec947c8cc")
    private static final String PROPERTY_DOCK_LOCATION = "org.eclipse.gef.ui.palette.fpa.dock"; // $NON-NLS-1$

    @objid ("65957c13-33f7-11e2-95fe-001ec947c8cc")
     static final FontManager FONT_MGR = new FontManager();

    @objid ("6597de41-33f7-11e2-95fe-001ec947c8cc")
     static final Dimension ARROW_SIZE = new Dimension(6, 11);

    @objid ("6597de4b-33f7-11e2-95fe-001ec947c8cc")
     PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    @objid ("6597de4c-33f7-11e2-95fe-001ec947c8cc")
     Composite paletteContainer;

    @objid ("6597de4d-33f7-11e2-95fe-001ec947c8cc")
    private PaletteViewer pViewer;

    @objid ("6597de4e-33f7-11e2-95fe-001ec947c8cc")
    private PaletteViewer externalViewer;

    @objid ("6597de4f-33f7-11e2-95fe-001ec947c8cc")
    private IMemento capturedPaletteState;

    @objid ("6597de50-33f7-11e2-95fe-001ec947c8cc")
     Control graphicalControl;

    @objid ("6597de51-33f7-11e2-95fe-001ec947c8cc")
     Composite sash;

    @objid ("659a409a-33f7-11e2-95fe-001ec947c8cc")
    private PaletteViewerProvider provider;

    @objid ("659a409b-33f7-11e2-95fe-001ec947c8cc")
     FlyoutPreferences prefs;

    @objid ("659a409c-33f7-11e2-95fe-001ec947c8cc")
    private Point cachedBounds = new Point(0, 0);

    /**
     * Constructor
     * 
     * @param parent The parent Composite
     * @param style The style of the widget to construct; only SWT.BORDER is allowed
     * @param pvProvider The provider that is to be used to create the flyout palette
     * @param flyoutPreferences To save/retrieve the preferences for the flyout
     */
    @objid ("659ca2f6-33f7-11e2-95fe-001ec947c8cc")
    public FlyoutPaletteComposite2(Composite parent, int style, PaletteViewerProvider pvProvider, FlyoutPreferences flyoutPreferences) {
        super(parent, style & SWT.BORDER);
        this.provider = pvProvider;
        this.prefs = flyoutPreferences;
        this.sash = createSash();
        this.paletteContainer = createPaletteContainer();
        
        // Initialize the state properly
        if (this.prefs.getPaletteWidth() <= 0) {
            this.prefs.setPaletteWidth(FlyoutPaletteComposite2.DEFAULT_PALETTE_SIZE);
        }
        setPaletteWidth(this.prefs.getPaletteWidth());
        setDockLocation(this.prefs.getDockLocation());
        setState(this.prefs.getPaletteState());
        
        addListener(SWT.Resize, new Listener() {
            @Override
            public void handleEvent(Event event) {
                Rectangle area = getClientArea();
                /*
                 * @TODO:Pratik Sometimes, the editor is resized to 1,1 or 0,0 (depending on the platform) when the editor is closed or maximized. We have to ignore such resizes. See Bug# 62748
                 */
                if (area.width > FlyoutPaletteComposite2.this.minWidth) {
                    layout(true);
                }
            }
        });
        
        this.listeners.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String property = evt.getPropertyName();
                if (property.equals(FlyoutPaletteComposite2.PROPERTY_PALETTE_WIDTH)) {
                    FlyoutPaletteComposite2.this.prefs.setPaletteWidth(FlyoutPaletteComposite2.this.paletteWidth);
                } else if (property.equals(FlyoutPaletteComposite2.PROPERTY_DOCK_LOCATION)) {
                    FlyoutPaletteComposite2.this.prefs.setDockLocation(FlyoutPaletteComposite2.this.dock);
                } else if (property.equals(FlyoutPaletteComposite2.PROPERTY_STATE)) {
                    if (FlyoutPaletteComposite2.this.paletteState == FlyoutPaletteComposite2.STATE_COLLAPSED
                            || FlyoutPaletteComposite2.this.paletteState == FlyoutPaletteComposite2.STATE_PINNED_OPEN) {
                        FlyoutPaletteComposite2.this.prefs.setPaletteState(FlyoutPaletteComposite2.this.paletteState);
                    }
                }
            }
        });
    }

    @objid ("659ca2fd-33f7-11e2-95fe-001ec947c8cc")
    private void addListenerToCtrlHierarchy(Control parent, int eventType, Listener listener) {
        parent.addListener(eventType, listener);
        if (!(parent instanceof Composite)) {
            return;
        }
        Control[] children = ((Composite) parent).getChildren();
        for (int i = 0; i < children.length; i++) {
            addListenerToCtrlHierarchy(children[i], eventType, listener);
        }
    }

    @objid ("659ca302-33f7-11e2-95fe-001ec947c8cc")
    private static IMemento capturePaletteState(PaletteViewer viewer) {
        IMemento memento = XMLMemento.createWriteRoot("paletteState"); //$NON-NLS-1$
        try {
            viewer.saveState(memento);
        } catch (@SuppressWarnings ("unused") RuntimeException re) {
            // Bug 74843 -- See comment #1
            // If there's a problem with saving the palette's state, it simply won't be
            // transferred to the new palette
            memento = null;
        }
        return memento;
    }

    @objid ("659ca307-33f7-11e2-95fe-001ec947c8cc")
    Control createFlyoutControlButton(Composite parent) {
        return new ButtonCanvas(parent);
    }

    @objid ("659ca30c-33f7-11e2-95fe-001ec947c8cc")
    private Composite createPaletteContainer() {
        return new PaletteComposite(this, SWT.NONE);
    }

    @objid ("659ca310-33f7-11e2-95fe-001ec947c8cc")
    private Composite createSash() {
        return new Sash(this, SWT.NONE);
    }

    @objid ("659ca314-33f7-11e2-95fe-001ec947c8cc")
    Control createTitle(Composite parent) {
        return new TitleCanvas(parent);
    }

    @objid ("659ca319-33f7-11e2-95fe-001ec947c8cc")
    Control getPaletteViewerControl() {
        Control result = null;
        if (this.pViewer != null) {
            result = this.pViewer.getControl();
        }
        // Fix for bug 101703 -- pViewer.getControl().getParent() might be parented
        // by paletteContainer
        if (result != null && !result.isDisposed() && result.getParent() != this.paletteContainer) {
            result = result.getParent();
        }
        return result;
    }

    /**
     * Will return false if the ancestor or descendant is <code>null</code>.
     */
    @objid ("659ca31d-33f7-11e2-95fe-001ec947c8cc")
    static boolean isDescendantOf(Control ancestor, Control descendant) {
        if (ancestor == null || descendant == null) {
            return false;
        }
        Control parent = descendant;
        while (parent != null) {
            if (ancestor == parent) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    @objid ("659ca324-33f7-11e2-95fe-001ec947c8cc")
    boolean isInState(int state) {
        return (this.paletteState & state) != 0;
    }

    @objid ("659ca329-33f7-11e2-95fe-001ec947c8cc")
    boolean isMirrored() {
        return (getStyle() & SWT.MIRRORED) != 0;
    }

    /**
     * @see Composite#layout(boolean)
     */
    @objid ("659ca32d-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void layout(boolean changed) {
        if (this.graphicalControl == null || this.graphicalControl.isDisposed()) {
            return;
        }
        
        Rectangle area = getClientArea();
        if (area.width == 0 || area.height == 0) {
            return;
        }
        
        int sashWidth = this.sash.computeSize(-1, -1).x;
        int pWidth = this.paletteWidth;
        int maxWidth = Math.min(area.width / 2, FlyoutPaletteComposite2.MAX_PALETTE_SIZE);
        maxWidth = Math.max(maxWidth, this.minWidth);
        pWidth = Math.max(pWidth, this.minWidth);
        pWidth = Math.min(pWidth, maxWidth);
        
        /*
         * Fix for Bug# 65892 Laying out only when necessary helps reduce flicker on GTK in the case where the flyout palette is being resized past its maximum size.
         */
        if (this.paletteState == this.cachedState && pWidth == this.cachedSize && this.cachedLocation == this.dock
                && this.cachedBounds == getSize()) {
            return;
        }
        this.cachedState = this.paletteState;
        this.cachedSize = pWidth;
        this.cachedLocation = this.dock;
        this.cachedBounds = getSize();
        
        setRedraw(false);
        if (isInState(FlyoutPaletteComposite2.STATE_HIDDEN)) {
            this.sash.setVisible(false);
            this.paletteContainer.setVisible(false);
            this.graphicalControl.setBounds(area);
        } else if (this.dock == PositionConstants.EAST) {
            layoutComponentsEast(area, sashWidth, pWidth);
        } else {
            layoutComponentsWest(area, sashWidth, pWidth);
        }
        this.sash.layout();
        setRedraw(true);
        update();
    }

    @objid ("659ca332-33f7-11e2-95fe-001ec947c8cc")
    private void layoutComponentsEast(Rectangle area, int sashWidth, int pWidth) {
        if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
            this.paletteContainer.setVisible(false);
            this.sash.setBounds(area.x + area.width - sashWidth, area.y, sashWidth, area.height);
            this.graphicalControl.setBounds(area.x, area.y, area.width - sashWidth, area.height);
            this.sash.setVisible(true);
        } else if (isInState(FlyoutPaletteComposite2.STATE_EXPANDED)) {
            this.paletteContainer.moveAbove(this.graphicalControl);
            this.sash.moveAbove(this.paletteContainer);
            this.paletteContainer.setBounds(area.x + area.width - pWidth, area.y, pWidth, area.height);
            this.sash.setBounds(area.x + area.width - pWidth - sashWidth, area.y, sashWidth, area.height);
            this.graphicalControl.setBounds(area.x, area.y, area.width - sashWidth, area.height);
            this.sash.setVisible(true);
            this.paletteContainer.setVisible(true);
        } else if (isInState(FlyoutPaletteComposite2.STATE_PINNED_OPEN)) {
            this.paletteContainer.setBounds(area.x + area.width - pWidth, area.y, pWidth, area.height);
            this.sash.setBounds(area.x + area.width - pWidth - sashWidth, area.y, sashWidth, area.height);
            this.graphicalControl.setBounds(area.x, area.y, area.width - sashWidth - pWidth, area.height);
            this.sash.setVisible(true);
            this.paletteContainer.setVisible(true);
        }
    }

    @objid ("659ca337-33f7-11e2-95fe-001ec947c8cc")
    private void layoutComponentsWest(Rectangle area, int sashWidth, int pWidth) {
        if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
            this.sash.setVisible(true);
            this.paletteContainer.setVisible(false);
            this.sash.setBounds(area.x, area.y, sashWidth, area.height);
            this.graphicalControl.setBounds(area.x + sashWidth, area.y, area.width - sashWidth, area.height);
        } else if (isInState(FlyoutPaletteComposite2.STATE_EXPANDED)) {
            this.sash.setVisible(true);
            this.paletteContainer.setVisible(true);
            this.paletteContainer.moveAbove(this.graphicalControl);
            this.sash.moveAbove(this.paletteContainer);
            this.paletteContainer.setBounds(area.x, area.y, pWidth, area.height);
            this.sash.setBounds(area.x + pWidth, area.y, sashWidth, area.height);
            this.graphicalControl.setBounds(area.x + sashWidth, area.y, area.width - sashWidth, area.height);
        } else if (isInState(FlyoutPaletteComposite2.STATE_PINNED_OPEN)) {
            this.sash.setVisible(true);
            this.paletteContainer.setVisible(true);
            this.paletteContainer.setBounds(area.x, area.y, pWidth, area.height);
            this.sash.setBounds(area.x + pWidth, area.y, sashWidth, area.height);
            this.graphicalControl.setBounds(area.x + pWidth + sashWidth, area.y, area.width - sashWidth - pWidth, area.height);
        }
    }

    @objid ("659ca33c-33f7-11e2-95fe-001ec947c8cc")
    private static boolean restorePaletteState(PaletteViewer newPalette, IMemento state) {
        if (state != null) {
            try {
                return newPalette.restoreState(state);
            } catch (@SuppressWarnings ("unused") RuntimeException re) {
                // Ignore error, palette should go as it is
            }
        }
        return false;
    }

    /**
     * If an external palette viewer is provided, palette state (that is captured in {@link PaletteViewer#saveState(IMemento)} -- active tool, drawer expansion state, drawer pin state, etc.) will be maintained when switching between the two viewers.
     * Providing an external viewer, although recommended, is optional.
     * 
     * @param viewer The palette viewer used in the PaletteView
     */
    @objid ("659ca342-33f7-11e2-95fe-001ec947c8cc")
    public void setExternalViewer(PaletteViewer viewer) {
        if (viewer == null && this.externalViewer != null) {
            this.capturedPaletteState = capturePaletteState(this.externalViewer);
        }
        this.externalViewer = viewer;
        if (this.externalViewer != null && this.pViewer != null) {
            transferState(this.pViewer, this.externalViewer);
        }
    }

    @objid ("659ca346-33f7-11e2-95fe-001ec947c8cc")
    void setDockLocation(int position) {
        if (position != PositionConstants.EAST && position != PositionConstants.WEST) {
            return;
        }
        if (position != this.dock) {
            int oldPosition = this.dock;
            this.dock = position;
            this.listeners.firePropertyChange(FlyoutPaletteComposite2.PROPERTY_DOCK_LOCATION, oldPosition, this.dock);
            if (this.pViewer != null) {
                layout(true);
            }
        }
    }

    @objid ("659ca349-33f7-11e2-95fe-001ec947c8cc")
    final void setPaletteWidth(int newSize) {
        if (this.paletteWidth != newSize) {
            int oldValue = this.paletteWidth;
            this.paletteWidth = newSize;
            this.listeners.firePropertyChange(FlyoutPaletteComposite2.PROPERTY_PALETTE_WIDTH, oldValue, this.paletteWidth);
            if (this.pViewer != null) {
                layout(true);
            }
        }
    }

    /**
     * Sets the control along the side of which the palette is to be displayed. The given Control should be a child of this Composite. This method should only be invoked once.
     * 
     * @param graphicalViewer the control of the graphical viewer; cannot be <code>null</code>
     */
    @objid ("659ca34c-33f7-11e2-95fe-001ec947c8cc")
    public void setGraphicalControl(Control graphicalViewer) {
        if (graphicalViewer != null) {
            Assert.isTrue(graphicalViewer.getParent() == this);
            Assert.isTrue(this.graphicalControl == null);
            this.graphicalControl = graphicalViewer;
            addListenerToCtrlHierarchy(this.graphicalControl, SWT.MouseEnter, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    if (!isInState(FlyoutPaletteComposite2.STATE_EXPANDED)) {
                        return;
                    }
                    Display.getCurrent().timerExec(250, new Runnable() {
                        @Override
                        public void run() {
                            if (isDescendantOf(FlyoutPaletteComposite2.this.graphicalControl, Display.getCurrent()
                                    .getCursorControl())
                                    && isInState(FlyoutPaletteComposite2.STATE_EXPANDED)) {
                                setState(FlyoutPaletteComposite2.STATE_COLLAPSED);
                            }
                        }
                    });
                }
            });
        }
    }

    @objid ("659f0551-33f7-11e2-95fe-001ec947c8cc")
    public void hookDropTargetListener(GraphicalViewer viewer) {
        viewer.addDropTargetListener(new TransferDropTargetListener() {
            @Override
            public void dragEnter(DropTargetEvent event) {
                // Nothing to do.
            }
        
            @Override
            public void dragLeave(DropTargetEvent event) {
                // Nothing to do.
            }
        
            @Override
            public void dragOperationChanged(DropTargetEvent event) {
                // Nothing to do.
            }
        
            @Override
            public void dragOver(DropTargetEvent event) {
                // Nothing to do.
            }
        
            @Override
            public void drop(DropTargetEvent event) {
                // Nothing to do.
            }
        
            @Override
            public void dropAccept(DropTargetEvent event) {
                // Nothing to do.
            }
        
            @Override
            public Transfer getTransfer() {
                return TemplateTransfer.getInstance();
            }
        
            @Override
            public boolean isEnabled(DropTargetEvent event) {
                if (isInState(FlyoutPaletteComposite2.STATE_EXPANDED)) {
                    setState(FlyoutPaletteComposite2.STATE_COLLAPSED);
                }
                return false;
            }
        });
    }

    @objid ("659f0554-33f7-11e2-95fe-001ec947c8cc")
    public void setState(int initialNewState) {
        // Fix for Bug# 69617 and Bug# 81248 FlyoutPreferences.getPaletteState() could return an invalid state if none is stored. In that case, we use the default state: STATE_COLLAPSED.
        int newState;
        if (initialNewState != FlyoutPaletteComposite2.STATE_HIDDEN && initialNewState != FlyoutPaletteComposite2.STATE_PINNED_OPEN && initialNewState != FlyoutPaletteComposite2.STATE_EXPANDED) {
            newState = FlyoutPaletteComposite2.STATE_COLLAPSED;
        } else {
            newState = initialNewState;
        }
        
        if (this.paletteState == newState) {
            return;
        }
        
        int oldState = this.paletteState;
        this.paletteState = newState;
        switch (this.paletteState) {
        case STATE_EXPANDED:
        case STATE_COLLAPSED:
        case STATE_PINNED_OPEN:
            if (this.pViewer == null) {
                this.pViewer = this.provider.createPaletteViewer(this.paletteContainer);
                if (this.externalViewer != null) {
                    transferState(this.externalViewer, this.pViewer);
                } else {
                    restorePaletteState(this.pViewer, this.capturedPaletteState);
                }
                this.capturedPaletteState = null;
                this.minWidth = Math.max(this.pViewer.getControl().computeSize(0, 0).x, FlyoutPaletteComposite2.MIN_PALETTE_SIZE);
            }
            break;
        case STATE_HIDDEN:
            if (this.pViewer == null) {
                break;
            }
            if (this.externalViewer != null) {
                this.provider.getEditDomain().setPaletteViewer(this.externalViewer);
                transferState(this.pViewer, this.externalViewer);
            }
            if (this.provider.getEditDomain().getPaletteViewer() == this.pViewer) {
                this.provider.getEditDomain().setPaletteViewer(null);
            }
            Control pViewerCtrl = getPaletteViewerControl();
            if (pViewerCtrl != null && !pViewerCtrl.isDisposed()) {
                pViewerCtrl.dispose();
            }
            this.pViewer = null;
            break;
        default:
            break;
        }
        // Fix for Bug# 63901 When the flyout collapses, if the palette has focus, throw focus to the graphical control. That way, hitting ESC will still deactivate the current tool and load the default one. Note that focus is being set on RulerComposite
        // and not GraphicalViewer's control. But this is okay since RulerComposite passes the focus on to its first child, which is the graphical viewer's control.
        if (this.paletteState == FlyoutPaletteComposite2.STATE_COLLAPSED && this.pViewer.getControl().isFocusControl()) {
            this.graphicalControl.setFocus();
        }
        layout(true);
        this.listeners.firePropertyChange(FlyoutPaletteComposite2.PROPERTY_STATE, oldState, newState);
    }

    @objid ("659f0557-33f7-11e2-95fe-001ec947c8cc")
    private static void transferState(PaletteViewer src, PaletteViewer dest) {
        restorePaletteState(dest, capturePaletteState(src));
    }

    /**
     * FlyoutPreferences is used to save/load the preferences for the flyout palette.
     * 
     * @author Pratik Shah
     * @since 3.0
     */
    @objid ("659f055b-33f7-11e2-95fe-001ec947c8cc")
    public interface FlyoutPreferences {
        /**
         * Should return {@link PositionConstants#EAST} or {@link PositionConstants#WEST}. Any other int will be ignored and the default dock location (EAST) will be used instead.
         * 
         * @return the saved dock location of the Palette
         */
        @objid ("659f055d-33f7-11e2-95fe-001ec947c8cc")
        int getDockLocation();

        /**
         * When there is no saved state, this method can return any non-positive int (which will result in the palette using the default state -- collapsed), or {@link FlyoutPaletteComposite#STATE_COLLAPSED}, or
         * {@link FlyoutPaletteComposite#STATE_PINNED_OPEN}
         * 
         * @return the saved state of the palette
         */
        @objid ("659f0560-33f7-11e2-95fe-001ec947c8cc")
        int getPaletteState();

        /**
         * When there is no saved width, this method can return any int (preferrably a non-positive int). Returning a non-positive int will cause the palette to be sized to the default size, whereas returning a postive int will find the closest match in
         * the valid range (>= minimum and <= maximum)
         * 
         * @return the saved width of the flyout palette
         */
        @objid ("659f0563-33f7-11e2-95fe-001ec947c8cc")
        int getPaletteWidth();

        /**
         * This method is invoked when the flyout palette's dock location is changed. The provided dock location should be persisted and returned in {@link #getDockLocation()}.
         * 
         * @param location {@link PositionConstants#EAST} or {@link PositionConstants#WEST}
         */
        @objid ("659f0566-33f7-11e2-95fe-001ec947c8cc")
        void setDockLocation(int location);

        /**
         * This method is invoked when the flyout palette's state is changed (the new state becomes the default). The provided state should be persisted and returned in {@link #getPaletteState()}.
         * 
         * @param state {@link FlyoutPaletteComposite#STATE_COLLAPSED} or {@link FlyoutPaletteComposite#STATE_PINNED_OPEN}
         */
        @objid ("659f0569-33f7-11e2-95fe-001ec947c8cc")
        void setPaletteState(int state);

        /**
         * This method is invoked when the flyout palette is resized. The provided width should be persisted and returned in {@link #getPaletteWidth()}.
         * 
         * @param width the new size of the flyout palette
         */
        @objid ("659f056c-33f7-11e2-95fe-001ec947c8cc")
        void setPaletteWidth(int width);

    }

    @objid ("659f056f-33f7-11e2-95fe-001ec947c8cc")
    private class Sash extends Composite {
        @objid ("659f0570-33f7-11e2-95fe-001ec947c8cc")
        private Control button;

        @objid ("659f0571-33f7-11e2-95fe-001ec947c8cc")
        public Sash(Composite parent, int style) {
            super(parent, style);
            this.button = createFlyoutControlButton(this);
            SashDragManager sashDragManager = new SashDragManager();
            addMouseMoveListener(sashDragManager);
            addMouseListener(sashDragManager);
            
            addMouseTrackListener(new MouseTrackAdapter() {
                @Override
                public void mouseHover(MouseEvent e) {
                    if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
                        setState(FlyoutPaletteComposite2.STATE_EXPANDED);
                    }
                }
            });
            
            addListener(SWT.Paint, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    paintSash(event.gc);
                }
            });
            
            addListener(SWT.Resize, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    layout(true);
                }
            });
            
            FlyoutPaletteComposite2.this.listeners.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals(FlyoutPaletteComposite2.PROPERTY_STATE)) {
                        updateState();
                    }
                }
            });
        }

        @objid ("659f0575-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Point computeSize(int wHint, int hHint, boolean changed) {
            if (isInState(FlyoutPaletteComposite2.STATE_PINNED_OPEN)) {
                return new Point(3, 3);
            }
            
            // button size plus two pixels for the two lines to be drawn
            return new Point(FlyoutPaletteComposite2.SASH_BUTTON_WIDTH + 2, FlyoutPaletteComposite2.this.cachedTitleHeight);
        }

        @objid ("659f057d-33f7-11e2-95fe-001ec947c8cc")
        void handleSashDragged(int shiftAmount) {
            int newSize = FlyoutPaletteComposite2.this.paletteContainer.getBounds().width
                    + (FlyoutPaletteComposite2.this.dock == PositionConstants.EAST ? -shiftAmount : shiftAmount);
            setPaletteWidth(newSize);
        }

        @objid ("659f0580-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void layout(boolean changed) {
            if (this.button == null) {
                return;
            }
            
            if (isInState(FlyoutPaletteComposite2.STATE_PINNED_OPEN)) {
                this.button.setVisible(false);
                return;
            }
            
            this.button.setVisible(true);
            Rectangle area = getClientArea();
            this.button.setBounds(area.x + 1, area.y + 1, FlyoutPaletteComposite2.SASH_BUTTON_WIDTH, FlyoutPaletteComposite2.this.cachedTitleHeight - 1);
            
            if (FlyoutPaletteComposite2.this.transferFocus) {
                FlyoutPaletteComposite2.this.transferFocus = false;
                this.button.setFocus();
            }
        }

        @objid ("659f0584-33f7-11e2-95fe-001ec947c8cc")
        void paintSash(GC gc) {
            Rectangle bounds = getBounds();
            if (isInState(FlyoutPaletteComposite2.STATE_PINNED_OPEN)) {
                gc.setBackground(PaletteColorUtil.WIDGET_BACKGROUND);
                gc.fillRectangle(0, 0, bounds.width, bounds.height);
            
                gc.setForeground(PaletteColorUtil.WIDGET_LIST_BACKGROUND);
                gc.drawLine(0, 0, bounds.width, 0);
                gc.setForeground(PaletteColorUtil.WIDGET_NORMAL_SHADOW);
                gc.drawLine(0, bounds.height - 1, bounds.width - 1, bounds.height - 1);
                gc.setForeground(PaletteColorUtil.WIDGET_LIST_BACKGROUND);
                gc.drawLine(0, 0, 0, bounds.height);
                gc.setForeground(PaletteColorUtil.WIDGET_NORMAL_SHADOW);
                gc.drawLine(bounds.width - 1, 0, bounds.width - 1, bounds.height - 1);
            } else {
                gc.setForeground(PaletteColorUtil.WIDGET_NORMAL_SHADOW);
                gc.drawLine(0, 0, 0, bounds.height);
                gc.drawLine(bounds.width - 1, 0, bounds.width - 1, bounds.height);
            
                gc.setForeground(PaletteColorUtil.WIDGET_LIST_BACKGROUND);
                gc.drawLine(1, 0, 1, bounds.height);
            
                gc.setForeground(PaletteColorUtil.WIDGET_BACKGROUND_LIST_BACKGROUND_85);
                gc.drawLine(2, 0, 2, bounds.height);
            }
        }

        @objid ("659f0587-33f7-11e2-95fe-001ec947c8cc")
        void updateState() {
            setCursor(isInState(FlyoutPaletteComposite2.STATE_EXPANDED | FlyoutPaletteComposite2.STATE_PINNED_OPEN) ? Cursors.SIZEWE : null);
        }

        @objid ("659f0589-33f7-11e2-95fe-001ec947c8cc")
        private class SashDragManager extends MouseAdapter implements MouseMoveListener {
            @objid ("659f058a-33f7-11e2-95fe-001ec947c8cc")
            protected boolean dragging = false;

            @objid ("659f058b-33f7-11e2-95fe-001ec947c8cc")
            protected boolean correctState = false;

            @objid ("65a167a8-33f7-11e2-95fe-001ec947c8cc")
            protected boolean mouseDown = false;

            @objid ("65a167a9-33f7-11e2-95fe-001ec947c8cc")
            protected int origX;

            @objid ("65a167aa-33f7-11e2-95fe-001ec947c8cc")
            protected Listener keyListener = new Listener() {
                @Override
                public void handleEvent(Event event) {
                    if (event.keyCode == SWT.ALT || event.keyCode == SWT.ESC) {
                        SashDragManager.this.dragging = false;
                        Display.getCurrent().removeFilter(SWT.KeyDown, this);
                    }
                    event.doit = false;
                    event.type = SWT.None;
                }
            };

            @objid ("65a167ab-33f7-11e2-95fe-001ec947c8cc")
            public SashDragManager() {
                super();
            }

            @objid ("65a167ad-33f7-11e2-95fe-001ec947c8cc")
            @Override
            public void mouseDown(MouseEvent me) {
                if (me.button != 1) {
                    return;
                }
                this.mouseDown = true;
                this.correctState = isInState(FlyoutPaletteComposite2.STATE_EXPANDED | FlyoutPaletteComposite2.STATE_PINNED_OPEN);
                this.origX = me.x;
                Display.getCurrent().addFilter(SWT.KeyDown, this.keyListener);
            }

            @objid ("65a167b1-33f7-11e2-95fe-001ec947c8cc")
            @Override
            public void mouseMove(MouseEvent me) {
                if (this.mouseDown) {
                    this.dragging = true;
                }
                if (this.dragging && this.correctState) {
                    handleSashDragged(me.x - this.origX);
                }
            }

            @objid ("65a167b5-33f7-11e2-95fe-001ec947c8cc")
            @Override
            public void mouseUp(MouseEvent me) {
                Display.getCurrent().removeFilter(SWT.KeyDown, this.keyListener);
                if (!this.dragging && me.button == 1) {
                    if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
                        setState(FlyoutPaletteComposite2.STATE_EXPANDED);
                    } else if (isInState(FlyoutPaletteComposite2.STATE_EXPANDED)) {
                        setState(FlyoutPaletteComposite2.STATE_COLLAPSED);
                    }
                }
                this.dragging = false;
                this.correctState = false;
                this.mouseDown = false;
            }

        }

    }

    @objid ("65a167b9-33f7-11e2-95fe-001ec947c8cc")
    private class ResizeAction extends Action {
        @objid ("65a167ba-33f7-11e2-95fe-001ec947c8cc")
        public ResizeAction() {
            super(PaletteMessages.RESIZE_LABEL);
        }

        @objid ("65a167bc-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public boolean isEnabled() {
            return !isInState(FlyoutPaletteComposite2.STATE_COLLAPSED);
        }

        @objid ("65a167c1-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void run() {
            final Tracker tracker = new Tracker(FlyoutPaletteComposite2.this, SWT.RIGHT | SWT.LEFT);
            Rectangle[] rects = new Rectangle[1];
            rects[0] = FlyoutPaletteComposite2.this.sash.getBounds();
            tracker.setCursor(Cursors.SIZEE);
            tracker.setRectangles(rects);
            if (tracker.open()) {
                int deltaX = FlyoutPaletteComposite2.this.sash.getBounds().x - tracker.getRectangles()[0].x;
                if (FlyoutPaletteComposite2.this.dock == PositionConstants.WEST) {
                    deltaX = -deltaX;
                }
                setPaletteWidth(FlyoutPaletteComposite2.this.paletteContainer.getBounds().width + deltaX);
            }
            tracker.dispose();
        }

    }

    @objid ("65a167c4-33f7-11e2-95fe-001ec947c8cc")
    private class TitleDragManager extends MouseAdapter implements Listener, MouseTrackListener {
        @objid ("65a167c5-33f7-11e2-95fe-001ec947c8cc")
        protected boolean switchDock = false;

        @objid ("65a167c6-33f7-11e2-95fe-001ec947c8cc")
        protected boolean dragging = false;

        @objid ("65a167c7-33f7-11e2-95fe-001ec947c8cc")
        protected int threshold;

        @objid ("65a167c8-33f7-11e2-95fe-001ec947c8cc")
        public TitleDragManager() {
            super();
        }

        @objid ("65a167ca-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void handleEvent(Event event) {
            this.dragging = true;
            this.switchDock = false;
            this.threshold = FlyoutPaletteComposite2.this.dock == PositionConstants.EAST ? Integer.MAX_VALUE / 2 : -1;
            final Composite flyout = FlyoutPaletteComposite2.this;
            final Rectangle flyoutBounds = flyout.getBounds();
            final int switchThreshold = flyoutBounds.x + (flyoutBounds.width / 2);
            Rectangle bounds = FlyoutPaletteComposite2.this.sash.getBounds();
            if (FlyoutPaletteComposite2.this.paletteContainer.getVisible()) {
                bounds = bounds.union(FlyoutPaletteComposite2.this.paletteContainer.getBounds());
            }
            final Rectangle origBounds = Display.getCurrent().map(flyout, null, bounds);
            final Tracker tracker = new Tracker(Display.getDefault(), SWT.NULL);
            tracker.setRectangles(new Rectangle[] { origBounds });
            tracker.setStippled(true);
            tracker.addListener(SWT.Move, new Listener() {
                @Override
                public void handleEvent(final Event evt) {
                    Display.getCurrent().syncExec(new Runnable() {
                        @Override
                        public void run() {
                            Control ctrl = Display.getCurrent().getCursorControl();
                            Point pt = flyout.toControl(evt.x, evt.y);
                            TitleDragManager.this.switchDock = isDescendantOf(FlyoutPaletteComposite2.this.graphicalControl, ctrl)
                                    && ((FlyoutPaletteComposite2.this.dock == PositionConstants.WEST && pt.x > TitleDragManager.this.threshold - 10)
                                            || (FlyoutPaletteComposite2.this.dock == PositionConstants.EAST && pt.x < TitleDragManager.this.threshold + 10));
                            boolean invalid = false;
                            if (!TitleDragManager.this.switchDock) {
                                invalid = !isDescendantOf(FlyoutPaletteComposite2.this, ctrl);
                            }
                            if (TitleDragManager.this.switchDock) {
                                if (FlyoutPaletteComposite2.this.dock == PositionConstants.WEST) {
                                    TitleDragManager.this.threshold = Math.max(TitleDragManager.this.threshold, pt.x);
                                    TitleDragManager.this.threshold = Math.min(TitleDragManager.this.threshold, switchThreshold);
                                } else {
                                    TitleDragManager.this.threshold = Math.min(TitleDragManager.this.threshold, pt.x);
                                    TitleDragManager.this.threshold = Math.max(TitleDragManager.this.threshold, switchThreshold);
                                }
                            }
                            Rectangle placeHolder = origBounds;
                            if (TitleDragManager.this.switchDock) {
                                if (FlyoutPaletteComposite2.this.dock == PositionConstants.EAST) {
                                    placeHolder = new Rectangle(0, 0, origBounds.width, origBounds.height);
                                } else {
                                    placeHolder = new Rectangle(flyoutBounds.width - origBounds.width, 0, origBounds.width,
                                            origBounds.height);
                                }
                                placeHolder = Display.getCurrent().map(flyout, null, placeHolder);
                            }
                            // update the cursor
                            int cursor;
                            if (invalid) {
                                cursor = DragCursors.INVALID;
                            } else if ((!TitleDragManager.this.switchDock && FlyoutPaletteComposite2.this.dock == PositionConstants.EAST)
                                    || (TitleDragManager.this.switchDock && FlyoutPaletteComposite2.this.dock == PositionConstants.WEST)) {
                                cursor = DragCursors.RIGHT;
                            } else {
                                cursor = DragCursors.LEFT;
                            }
                            if (isMirrored()) {
                                if (cursor == DragCursors.RIGHT) {
                                    cursor = DragCursors.LEFT;
                                } else if (cursor == DragCursors.LEFT) {
                                    cursor = DragCursors.RIGHT;
                                }
                            }
                            tracker.setCursor(DragCursors.getCursor(cursor));
                            // update the rectangle only if it has changed
                            if (!tracker.getRectangles()[0].equals(placeHolder)) {
                                tracker.setRectangles(new Rectangle[] { placeHolder });
                            }
                        }
                    });
                }
            });
            if (tracker.open()) {
                if (this.switchDock) {
                    setDockLocation(PositionConstants.EAST_WEST & ~FlyoutPaletteComposite2.this.dock);
                }
                // mouse up is received by the tracker and by this listener, so we set dragging
                // to be false
                this.dragging = false;
            }
            tracker.dispose();
        }

        @objid ("65a167ce-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void mouseEnter(MouseEvent e) {
            // Nothing to do.
        }

        @objid ("65a167d2-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void mouseExit(MouseEvent e) {
            // Nothing to do.
        }

        @objid ("65a167d6-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void mouseHover(MouseEvent e) {
            /*
             * @TODO:Pratik Mouse hover events are received if the hover occurs just before you finish or cancel the drag. Open a bugzilla about it?
             */
            if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
                setState(FlyoutPaletteComposite2.STATE_EXPANDED);
            }
        }

        @objid ("65a167da-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void mouseUp(MouseEvent me) {
            if (me.button != 1) {
                return;
            }
            if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
                setState(FlyoutPaletteComposite2.STATE_EXPANDED);
            } else if (isInState(FlyoutPaletteComposite2.STATE_EXPANDED)) {
                setState(FlyoutPaletteComposite2.STATE_COLLAPSED);
            }
        }

    }

    @objid ("65a167de-33f7-11e2-95fe-001ec947c8cc")
    private class PaletteComposite extends Composite {
        @objid ("65a167df-33f7-11e2-95fe-001ec947c8cc")
        protected Control button;

        @objid ("65a167e0-33f7-11e2-95fe-001ec947c8cc")
        protected Control title;

        @objid ("65a3ca02-33f7-11e2-95fe-001ec947c8cc")
        public PaletteComposite(Composite parent, int style) {
            super(parent, style);
            createComponents();
            
            FlyoutPaletteComposite2.this.listeners.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals(FlyoutPaletteComposite2.PROPERTY_STATE)) {
                        updateState();
                    } else if (evt.getPropertyName().equals(FlyoutPaletteComposite2.PROPERTY_DOCK_LOCATION)) {
                        if (getVisible()) {
                            layout(true);
                        }
                    }
                }
            });
            
            addListener(SWT.Resize, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    layout(true);
                }
            });
            
            updateState();
        }

        @objid ("65a3ca06-33f7-11e2-95fe-001ec947c8cc")
        protected final void createComponents() {
            this.title = createTitle(this);
            this.button = createFlyoutControlButton(this);
        }

        @objid ("65a3ca08-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void layout(boolean changed) {
            Control pCtrl = getPaletteViewerControl();
            if (pCtrl == null || pCtrl.isDisposed()) {
                return;
            }
            
            Rectangle area = getClientArea();
            boolean buttonVisible = this.button.getVisible();
            Point titleSize = this.title.computeSize(-1, -1);
            Point buttonSize = buttonVisible ? this.button.computeSize(-1, -1) : new Point(0, 0);
            FlyoutPaletteComposite2.this.cachedTitleHeight = Math.max(titleSize.y, buttonSize.y);
            if (buttonVisible) {
                buttonSize.x = Math.max(FlyoutPaletteComposite2.this.cachedTitleHeight, buttonSize.x);
            }
            if (FlyoutPaletteComposite2.this.dock == PositionConstants.EAST) {
                int buttonX = area.width - buttonSize.x;
                this.button.setBounds(buttonX, 0, buttonSize.x, FlyoutPaletteComposite2.this.cachedTitleHeight);
                this.title.setBounds(0, 0, buttonX, FlyoutPaletteComposite2.this.cachedTitleHeight);
            } else {
                int titleX = buttonSize.x;
                this.button.setBounds(0, 0, buttonSize.x, FlyoutPaletteComposite2.this.cachedTitleHeight);
                this.title.setBounds(titleX, 0, area.width - titleX, FlyoutPaletteComposite2.this.cachedTitleHeight);
            }
            area.y += FlyoutPaletteComposite2.this.cachedTitleHeight;
            area.height -= FlyoutPaletteComposite2.this.cachedTitleHeight;
            pCtrl.setBounds(area);
        }

        @objid ("65a3ca0c-33f7-11e2-95fe-001ec947c8cc")
        protected void updateState() {
            this.button.setVisible(isInState(FlyoutPaletteComposite2.STATE_PINNED_OPEN));
            if (FlyoutPaletteComposite2.this.transferFocus && this.button.getVisible()) {
                FlyoutPaletteComposite2.this.transferFocus = false;
                this.button.setFocus();
            }
            layout(true);
        }

    }

    @objid ("65a3ca0e-33f7-11e2-95fe-001ec947c8cc")
    private static class TitleLabel extends Label {
        @objid ("65a3ca10-33f7-11e2-95fe-001ec947c8cc")
        protected static final Border BORDER = new MarginBorder(0, 3, 0, 3);

        @objid ("65a3ca12-33f7-11e2-95fe-001ec947c8cc")
        protected static final Border TOOL_TIP_BORDER = new MarginBorder(0, 2, 0, 2);

        @objid ("65a3ca14-33f7-11e2-95fe-001ec947c8cc")
        public TitleLabel() {
            // super(GEFMessages.Palette_Label, InternalImages.get(InternalImages.IMG_PALETTE));
            super();
            setLabelAlignment(PositionConstants.LEFT);
            setBorder(TitleLabel.BORDER);
            // Label tooltip = new Label(getText());
            // tooltip.setBorder(TOOL_TIP_BORDER);
            // setToolTip(tooltip);
            setForegroundColor(ColorConstants.listForeground);
        }

        @objid ("65a3ca16-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public IFigure getToolTip() {
            if (isTextTruncated()) {
                return super.getToolTip();
            }
            return null;
        }

        @objid ("65a3ca1b-33f7-11e2-95fe-001ec947c8cc")
        @Override
        protected void paintFigure(Graphics graphics) {
            // paint the gradient
            graphics.pushState();
            org.eclipse.draw2d.geometry.Rectangle r = org.eclipse.draw2d.geometry.Rectangle.SINGLETON;
            r.setBounds(getBounds());
            graphics.setForegroundColor(PaletteColorUtil.WIDGET_LIST_BACKGROUND);
            graphics.setBackgroundColor(PaletteColorUtil.WIDGET_BACKGROUND);
            graphics.fillGradient(r, true);
            
            // draw bottom border
            graphics.setForegroundColor(PaletteColorUtil.WIDGET_NORMAL_SHADOW);
            graphics.drawLine(r.getBottomLeft().getTranslated(0, -1), r.getBottomRight().getTranslated(0, -1));
            
            graphics.popState();
            
            // paint the text and icon
            super.paintFigure(graphics);
            
            // paint the focus rectangle around the text
            if (hasFocus()) {
                org.eclipse.draw2d.geometry.Rectangle textBounds = getTextBounds();
                // We reduce the width by 1 because FigureUtilities grows it by 1 unnecessarily
                textBounds.width--;
                graphics.drawFocus(this.bounds.getResized(-1, -1).intersect(textBounds.getExpanded(getInsets())));
            }
        }

    }

    @objid ("65a3ca1f-33f7-11e2-95fe-001ec947c8cc")
    private class ButtonCanvas extends Canvas {
        @objid ("65a3ca20-33f7-11e2-95fe-001ec947c8cc")
        private LightweightSystem lws;

        @objid ("65a3ca21-33f7-11e2-95fe-001ec947c8cc")
        public ButtonCanvas(Composite parent) {
            super(parent, SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
            init();
            provideAccSupport();
        }

        @objid ("65a3ca24-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Point computeSize(int wHint, int hHint, boolean changed) {
            Dimension size = this.lws.getRootFigure().getPreferredSize(wHint, hHint);
            size.union(new Dimension(wHint, hHint));
            return new org.eclipse.swt.graphics.Point(size.width, size.height);
        }

        @objid ("65a3ca2c-33f7-11e2-95fe-001ec947c8cc")
        int getArrowDirection() {
            int direction = PositionConstants.EAST;
            if (isInState(FlyoutPaletteComposite2.STATE_EXPANDED | FlyoutPaletteComposite2.STATE_PINNED_OPEN)) {
                direction = FlyoutPaletteComposite2.this.dock == PositionConstants.WEST ? PositionConstants.WEST
                        : PositionConstants.EAST;
            } else {
                direction = FlyoutPaletteComposite2.this.dock == PositionConstants.WEST ? PositionConstants.EAST
                        : PositionConstants.WEST;
            }
            if (isMirrored()) {
                if (direction == PositionConstants.WEST) {
                    direction = PositionConstants.EAST;
                } else {
                    direction = PositionConstants.WEST;
                }
            }
            return direction;
        }

        @objid ("65a3ca30-33f7-11e2-95fe-001ec947c8cc")
        String getButtonTooltipText() {
            if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
                return PaletteMessages.PALETTE_SHOW;
            }
            return PaletteMessages.PALETTE_HIDE;
        }

        @objid ("65a3ca34-33f7-11e2-95fe-001ec947c8cc")
        private void init() {
            setCursor(Cursors.ARROW);
            this.lws = new LightweightSystem();
            this.lws.setControl(this);
            final ArrowButton b = new ArrowButton(getArrowDirection());
            b.setRolloverEnabled(true);
            b.setBorder(new ButtonBorder(ButtonBorder.SCHEMES.TOOLBAR));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    FlyoutPaletteComposite2.this.transferFocus = true;
                    if (isInState(FlyoutPaletteComposite2.STATE_COLLAPSED)) {
                        setState(FlyoutPaletteComposite2.STATE_PINNED_OPEN);
                    } else {
                        setState(FlyoutPaletteComposite2.STATE_COLLAPSED);
                    }
                }
            });
            FlyoutPaletteComposite2.this.listeners.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals(FlyoutPaletteComposite2.PROPERTY_STATE)) {
                        b.setDirection(getArrowDirection());
                        setToolTipText(getButtonTooltipText());
                    } else if (evt.getPropertyName().equals(FlyoutPaletteComposite2.PROPERTY_DOCK_LOCATION)) {
                        b.setDirection(getArrowDirection());
                    }
                }
            });
            this.lws.setContents(b);
        }

        @objid ("65a3ca36-33f7-11e2-95fe-001ec947c8cc")
        private void provideAccSupport() {
            getAccessible().addAccessibleListener(new AccessibleAdapter() {
                @Override
                public void getDescription(AccessibleEvent e) {
                    e.result = PaletteMessages.ACC_DESC_PALETTE_BUTTON;
                }
            
                @Override
                public void getHelp(AccessibleEvent e) {
                    getDescription(e);
                }
            
                @Override
                public void getName(AccessibleEvent e) {
                    e.result = getToolTipText();
                }
            });
            getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
                @Override
                public void getRole(AccessibleControlEvent e) {
                    e.detail = ACC.ROLE_PUSHBUTTON;
                }
            });
        }

        @objid ("65a3ca38-33f7-11e2-95fe-001ec947c8cc")
        private class ArrowButton extends Button {
            @objid ("65a3ca39-33f7-11e2-95fe-001ec947c8cc")
            private Triangle triangle;

            /**
             * Creates a new instance
             * 
             * @param direction the direction the arrow should face (PositionConstants.RIGHT or PositionConstants.LEFT)
             */
            @objid ("65a62c5c-33f7-11e2-95fe-001ec947c8cc")
            public ArrowButton(int direction) {
                super();
                setDirection(direction);
                
                this.triangle = new Triangle();
                this.triangle.setOutline(true);
                this.triangle.setBackgroundColor(PaletteColorUtil.WIDGET_LIST_BACKGROUND);
                this.triangle.setForegroundColor(PaletteColorUtil.WIDGET_DARK_SHADOW);
                setContents(this.triangle);
            }

            @objid ("65a62c60-33f7-11e2-95fe-001ec947c8cc")
            public final void setDirection(int direction) {
                if (this.triangle != null) {
                    this.triangle.setDirection(direction);
                }
            }

            @objid ("65a62c63-33f7-11e2-95fe-001ec947c8cc")
            @Override
            protected void layout() {
                org.eclipse.draw2d.geometry.Rectangle clientArea = getBounds();
                
                this.triangle.setBounds(new org.eclipse.draw2d.geometry.Rectangle(clientArea.getCenter().getTranslated(
                        -FlyoutPaletteComposite2.ARROW_SIZE.width / 2, -FlyoutPaletteComposite2.ARROW_SIZE.height / 2), FlyoutPaletteComposite2.ARROW_SIZE));
            }

            @objid ("65a62c66-33f7-11e2-95fe-001ec947c8cc")
            @Override
            protected void paintFigure(Graphics graphics) {
                super.paintFigure(graphics);
                
                // paint the gradient
                graphics.pushState();
                org.eclipse.draw2d.geometry.Rectangle r = org.eclipse.draw2d.geometry.Rectangle.SINGLETON;
                r.setBounds(getBounds());
                graphics.setForegroundColor(PaletteColorUtil.WIDGET_LIST_BACKGROUND);
                graphics.setBackgroundColor(PaletteColorUtil.WIDGET_BACKGROUND);
                graphics.fillGradient(r, true);
                graphics.popState();
                
                // draw bottom border
                graphics.setForegroundColor(PaletteColorUtil.WIDGET_NORMAL_SHADOW);
                graphics.drawLine(r.getBottomLeft().getTranslated(0, -1), r.getBottomRight().getTranslated(0, -1));
            }

        }

    }

    @objid ("65a62c6a-33f7-11e2-95fe-001ec947c8cc")
    private class TitleCanvas extends Canvas {
        @objid ("65a62c6b-33f7-11e2-95fe-001ec947c8cc")
        private LightweightSystem lws;

        @objid ("65a62c6c-33f7-11e2-95fe-001ec947c8cc")
        public TitleCanvas(Composite parent) {
            super(parent, SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
            init();
            provideAccSupport();
        }

        /**
         * @see org.eclipse.swt.widgets.Control#computeSize(int, int, boolean)
         */
        @objid ("65a62c6f-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Point computeSize(int wHint, int hHint, boolean changed) {
            Dimension size = this.lws.getRootFigure().getPreferredSize(wHint, hHint);
            size.union(new Dimension(wHint, hHint));
            return new org.eclipse.swt.graphics.Point(size.width, size.height);
        }

        @objid ("65a62c78-33f7-11e2-95fe-001ec947c8cc")
        private void init() {
            final IFigure contents = new TitleLabel();
            contents.setRequestFocusEnabled(true);
            contents.setFocusTraversable(true);
            contents.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {
                    fe.gainer.repaint();
                }
            
                @Override
                public void focusLost(FocusEvent fe) {
                    fe.loser.repaint();
                }
            });
            
            this.lws = new LightweightSystem();
            this.lws.setControl(this);
            this.lws.setContents(contents);
            setCursor(Cursors.SIZEALL);
            FlyoutPaletteComposite2.FONT_MGR.register(this);
            TitleDragManager titleDragManager = new TitleDragManager();
            addListener(SWT.DragDetect, titleDragManager);
            addMouseListener(titleDragManager);
            addMouseTrackListener(titleDragManager);
            
            final MenuManager manager = new MenuManager();
            MenuManager mgr = new MenuManager(PaletteMessages.DOCK_LABEL);
            mgr.add(new ChangeDockAction(PaletteMessages.LEFT_LABEL, PositionConstants.WEST));
            mgr.add(new ChangeDockAction(PaletteMessages.RIGHT_LABEL, PositionConstants.EAST));
            manager.add(new ResizeAction());
            manager.add(mgr);
            setMenu(manager.createContextMenu(this));
            mgr.addMenuListener(new IMenuListener() {
                @Override
                public void menuAboutToShow(IMenuManager menuMgr) {
                    IContributionItem[] items = menuMgr.getItems();
                    for (int i = 0; i < items.length; i++) {
                        ((ActionContributionItem) items[i]).update();
                    }
                }
            });
            
            addDisposeListener(new DisposeListener() {
                @Override
                public void widgetDisposed(DisposeEvent e) {
                    FlyoutPaletteComposite2.FONT_MGR.unregister(TitleCanvas.this);
                    manager.dispose();
                }
            });
        }

        @objid ("65a62c7a-33f7-11e2-95fe-001ec947c8cc")
        private void provideAccSupport() {
            getAccessible().addAccessibleListener(new AccessibleAdapter() {
                @Override
                public void getDescription(AccessibleEvent e) {
                    e.result = PaletteMessages.ACC_DESC_PALETTE_TITLE;
                }
            
                @Override
                public void getHelp(AccessibleEvent e) {
                    getDescription(e);
                }
            
                @Override
                public void getName(AccessibleEvent e) {
                    e.result = GEFMessages.Palette_Label;
                }
            });
            getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
                @Override
                public void getRole(AccessibleControlEvent e) {
                    e.detail = ACC.ROLE_SLIDER;
                }
            });
        }

        @objid ("65a62c7c-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void setFont(Font font) {
            ((IFigure) this.lws.getRootFigure().getChildren().get(0)).setFont(font);
            if (isVisible()) {
                /*
                 * If this canvas is in the sash, we want the FlyoutPaletteComposite to layout (which will cause the sash to be resized and laid out). However, if this canvas is in the paletteContainer, the paletteContainer's bounds won't change, and hence
                 * it won't layout. Thus, we also invoke getParent().layout().
                 */
                FlyoutPaletteComposite2.this.layout(true);
                getParent().layout(true);
            }
        }

    }

    @objid ("65a88eb6-33f7-11e2-95fe-001ec947c8cc")
    private class ChangeDockAction extends Action {
        @objid ("65a88eb7-33f7-11e2-95fe-001ec947c8cc")
        private int position;

        /**
         * Constructor
         * 
         * @param text this action's text
         * @param position the dock side that this action represents: PositionConstants.EAST or PositionConstants.WEST
         */
        @objid ("65a88eb8-33f7-11e2-95fe-001ec947c8cc")
        public ChangeDockAction(String text, int position) {
            super(text, IAction.AS_RADIO_BUTTON);
            this.position = position;
        }

        /**
         * This Action is checked when the palette is docked on the side this action represents
         * @see org.eclipse.jface.action.IAction#isChecked()
         */
        @objid ("65a88ebd-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public boolean isChecked() {
            return FlyoutPaletteComposite2.this.dock == this.position;
        }

        /**
         * Changes the palette's dock location to the side this action represents
         * @see org.eclipse.jface.action.IAction#run()
         */
        @objid ("65a88ec3-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void run() {
            setDockLocation(this.position);
        }

    }

    @objid ("65a88ec7-33f7-11e2-95fe-001ec947c8cc")
    private static class FontManager {
        @objid ("1b64ba7c-3897-11e2-95fe-001ec947c8cc")
         final String fontName = getFontType();

        @objid ("65a88ecb-33f7-11e2-95fe-001ec947c8cc")
        private Font titleFont;

        @objid ("65a88ecc-33f7-11e2-95fe-001ec947c8cc")
        private final IPropertyChangeListener fontListener = new IPropertyChangeListener() {
            @Override
            public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
                if (FontManager.this.fontName.equals(event.getProperty())) {
                    handleFontChanged();
                }
            }
        };

        @objid ("65a88ece-33f7-11e2-95fe-001ec947c8cc")
        private List<Control> registrants = new ArrayList<>();

        @objid ("65a88ed1-33f7-11e2-95fe-001ec947c8cc")
        FontManager() {
        }

        @objid ("65a88ed3-33f7-11e2-95fe-001ec947c8cc")
        protected final Font createTitleFont() {
            return JFaceResources.getFont(this.fontName);
        }

        @objid ("65a88ed7-33f7-11e2-95fe-001ec947c8cc")
        protected void dispose() {
            this.titleFont = null;
            JFaceResources.getFontRegistry().removeListener(this.fontListener);
        }

        @objid ("65a88ed9-33f7-11e2-95fe-001ec947c8cc")
        protected static String getFontType() {
            return JFaceResources.DIALOG_FONT;
        }

        @objid ("65a88edd-33f7-11e2-95fe-001ec947c8cc")
        protected void handleFontChanged() {
            if (this.titleFont == null) {
                return;
            }
            Font oldFont = this.titleFont;
            this.titleFont = createTitleFont();
            for (Iterator<Control> iter = this.registrants.iterator(); iter.hasNext();) {
                iter.next().setFont(this.titleFont);
            }
            oldFont.dispose();
        }

        @objid ("65a88edf-33f7-11e2-95fe-001ec947c8cc")
        protected void init() {
            this.titleFont = createTitleFont();
            JFaceResources.getFontRegistry().addListener(this.fontListener);
        }

        @objid ("65a88ee1-33f7-11e2-95fe-001ec947c8cc")
        public void register(Control ctrl) {
            if (this.titleFont == null) {
                init();
            }
            ctrl.setFont(this.titleFont);
            this.registrants.add(ctrl);
        }

        @objid ("65a88ee4-33f7-11e2-95fe-001ec947c8cc")
        public void unregister(Control ctrl) {
            this.registrants.remove(ctrl);
            if (this.registrants.isEmpty()) {
                dispose();
            }
        }

    }

}
