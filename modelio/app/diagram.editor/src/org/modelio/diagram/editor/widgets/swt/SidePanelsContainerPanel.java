/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Panel containing a main panel and side panels that are resizeable with sashes.
 * <p>
 * A side panel may also be collapsed.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("ce1ef6b8-5e36-4f9d-a871-0d320d615b78")
public class SidePanelsContainerPanel {
    @objid ("29ec8c2e-53e0-4670-9937-7ed9f4e7a148")
    private final Composite container;

    @objid ("9909d946-c83a-4356-97cf-06fb63af2120")
    private final Composite mainPanel;

    @objid ("1152a5d2-c745-4ff3-8b9a-a2f05662de4a")
    private final List<FlyoutHolder> rightPanels;

    @objid ("8d020341-d196-4237-9102-1f9d000ac75b")
    public SidePanelsContainerPanel(Composite parent) {
        this.container = new Composite(parent, SWT.NONE);
        this.container.setLayout(new LLayout());
        
        this.mainPanel = new Composite(this.container, SWT.NONE);
        this.mainPanel.setLayout(new FillLayout());
        
        this.rightPanels = new ArrayList<>(3);
    }

    /**
     * Get the main panel composite.
     * <p>
     * This composite is never collapsed.
     * @return the main panel.
     */
    @objid ("d4efd66f-f8ab-45bf-bd35-99fcc376041c")
    public Composite getMainPanel() {
        return this.mainPanel;
    }

    /**
     * Add a lateral panel.
     * @param panel the panel provider
     * @param title the panel title
     * @param icon an optional icon
     */
    @objid ("43636cd8-450a-4a9f-a581-aca3d55b1fbe")
    public void addFlyout(IPanelProvider panel, String title, Image icon) {
        FlyoutHolder holder = new FlyoutHolder(this.container, title, icon);
        panel.createPanel(holder.getClientComposite());
        
        this.rightPanels.add(holder);
        
        setHolderCollapsed(holder);
        
        Listener listener = ev -> {
            switch (holder.getState()) {
            case collapsed:
                setHolderExpanded(holder);
                holder.setState(FlyoutState.pinned);
                break;
            case expanded:
                setHolderCollapsed(holder);
                break;
            case pinned:
                setHolderCollapsed(holder);
                break;
            default:
                break;
        
            }
        
        };
        holder.getExpandButton().addListener(SWT.Selection, listener);
        
        holder.getTitleBar().addMouseListener(new MouseAdapter() {
            boolean clicked;
        
            @Override
            public void mouseDown(MouseEvent e) {
                this.clicked = true;
            }
        
            @Override
            public void mouseUp(MouseEvent e) {
                if (this.clicked && e.widget == holder.getTitleBar()) {
                    listener.handleEvent(null);
                }
                this.clicked = false;
            }
        });
        
        holder.getSash().addListener(SWT.Selection, ev -> onSashDragged(ev, holder));
    }

    /**
     * Listener method called when dragging {@link Sash}.
     * @param event the drag event
     * @param holder the resized side pane.
     */
    @objid ("2fced5b6-5fcf-4c9c-ba57-8bfb0f5ee483")
    private void onSashDragged(Event event, FlyoutHolder holder) {
        Rectangle sashBounds = holder.getSash().getBounds();
        int shift = sashBounds.x - event.x;
        RowData d = (RowData) holder.getLayoutData();
        d.width += shift;
        this.container.layout(true);
        holder.layout();
    }

    @objid ("b6483945-3d06-46bd-ae3d-d95d8aae8042")
    private void onMouseEnterHolderSash(FlyoutHolder holder) {
        if (holder.getState() == FlyoutState.collapsed) {
            setHolderExpanded(holder);
        }
    }

    @objid ("22eb7cb1-e713-497c-a41b-fa42e848f147")
    private void setHolderCollapsed(FlyoutHolder holder) {
        holder.setState(FlyoutState.collapsed);
        this.container.layout(true);
    }

    @objid ("e2d6a203-7853-46c3-a0d2-622317ace920")
    private void setHolderExpanded(FlyoutHolder holder) {
        holder.setState(FlyoutState.expanded);
        this.container.layout(true);
    }

    @objid ("93afe354-680c-4307-bbc7-a6a4731ce645")
    private void onMouseExitFlyout(FlyoutHolder holder) {
        Display display = getDisplay();
        
        if (holder.getState() == FlyoutState.expanded) {
            display.timerExec(1000, new Runnable() {
                @Override
                public void run() {
                    if (!isDisposed() && holder.getState() == FlyoutState.expanded) {
                        Point absCursor = display.getCursorLocation();
                        Point cursor = display.map(null, holder.getParent(), absCursor);
                        Rectangle holderBounds = holder.getBounds();
                        Rectangle sashBounds = holder.getSash().getBounds();
                        if (!holderBounds.contains(cursor) && !sashBounds.contains(cursor)) {
                            // hide the panel
                            setHolderCollapsed(holder);
                        } else {
                            // check each second
                            display.timerExec(1000, this);
                        }
                    }
                }
            });
        }
    }

    @objid ("a9fe8ad2-fc34-4ba1-aca7-61973821bbf1")
    public boolean isDisposed() {
        return this.container == null || this.container.isDisposed();
    }

    @objid ("3e836285-9cb8-4dcc-a9c2-5bf1ab61763e")
    private Display getDisplay() {
        return this.container.getDisplay();
    }

    /**
     * Layout manager for {@link SidePanelsContainerPanel}.
     * <p>
     * Layout the main panel, side panels and their {@link Sash}. Each side panels size is stored in {@link RowData} layout data. The main panel takes the remaining space.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("4f9097d7-e669-492c-8039-ed730f3962d3")
    private class LLayout extends Layout {
        @objid ("37567199-dc79-4075-95d6-6373fe7ee000")
        @Override
        protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
            Point mainSize = SidePanelsContainerPanel.this.mainPanel.computeSize(wHint, hHint, flushCache);
            Point size = new Point(mainSize.x, mainSize.y);
            for (FlyoutHolder panel : SidePanelsContainerPanel.this.rightPanels) {
                Point ps = panel.computeSize(-1, hHint, flushCache);
            
                if (panel.isOpen()) {
                    RowData d = (RowData) panel.getLayoutData();
                    if (d != null) {
                        ps.x = d.width;
                    }
                } else {
                    ps.x = panel.getExpandButton().computeSize(wHint, hHint).x;
                }
            
                size.x += ps.x;
                size.y = Math.max(size.y, ps.y);
            
                // int sashWidth = 3;
                Sash panelSash = panel.getSash();
                int sashWidth = panelSash.computeSize(-1, hHint).x;
            
                size.x = +sashWidth;
            }
            size.x += composite.getBorderWidth();
            size.y += composite.getBorderWidth();
            return size;
        }

        @objid ("ad66a29b-7472-41cd-a5ad-4f708f690e30")
        @Override
        protected void layout(Composite composite, boolean flushCache) {
            Rectangle area = composite.getClientArea();
            if (area.isEmpty()) {
                return;
            }
            int x = area.x + area.width;
            for (FlyoutHolder panel : SidePanelsContainerPanel.this.rightPanels) {
                int panelW;
                if (panel.isOpen()) {
                    RowData d = (RowData) panel.getLayoutData();
                    if (d != null) {
                        panelW = d.width;
                    } else {
                        int preferredW = panel.computeSize(-1, area.height, false).x;
                        int maxW = area.width / (SidePanelsContainerPanel.this.rightPanels.size() + 2);
                        panelW = Math.min(preferredW, maxW);
                        panel.setLayoutData(new RowData(panelW, 0));
                    }
                } else {
                    panelW = panel.getExpandButton().computeSize(-1, -1).x;
                }
            
                Rectangle cbounds = new Rectangle(x - panelW, area.y, panelW, area.height);
                panel.setBounds(cbounds);
                x -= panelW;
            
                Sash panelSash = panel.getSash();
                int sashWidth = panelSash.computeSize(-1, area.height).x;
                panelSash.setBounds(x - sashWidth, 0, 3, area.height);
                x -= sashWidth;
            }
            
            SidePanelsContainerPanel.this.mainPanel.setBounds(area.x, area.y, x, area.height - 1);
        }

    }

    @objid ("a01f107d-1afd-422c-80fa-df807dbac6a6")
    private static class FlyoutHolder extends Composite {
        @objid ("be013111-95b6-4fb0-802e-1f52b23dba3f")
        private FlyoutState state;

        @objid ("aff82137-bd2d-436a-ab21-35ce02faa587")
        private String title;

        @objid ("ef7408a2-2af5-4445-adb5-4935bbd475ce")
        private int expandButtonSizeHint;

        @objid ("8c31bdb9-8469-4700-b2af-e4d161c0a410")
        private final Composite titleBar;

        @objid ("ab26b050-3120-4114-8ceb-9a03d44cf63d")
        private final Composite clientPane;

        @objid ("ac0b73dd-b529-4dd2-a8c0-6d371b3d5dc7")
        private Sash sash;

        @objid ("ccbd31b8-d857-4ca4-84c6-0d0889feb56d")
        private ArrowButtonCanvas expandButton2;

        @objid ("5bab66ec-587c-44b8-b825-6692ac2f3e0a")
        public FlyoutHolder(Composite parent, String title, Image icon) {
            super(parent, SWT.NONE);
            this.state = FlyoutState.pinned;
            this.title = title;
            
            GridLayoutFactory layoutFactory = GridLayoutFactory.fillDefaults().spacing(0, 2);
            layoutFactory.applyTo(this);
            
            // sash : created , owned and handled by the PARENT
            this.sash = new Sash(parent, SWT.VERTICAL);
            
            this.titleBar = new Composite(this, SWT.NONE);
            GridDataFactory.swtDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).applyTo(this.titleBar);
            layoutFactory.spacing(3, 0).numColumns(3).applyTo(this.titleBar);
            this.titleBar.addListener(SWT.Paint, ev -> drawTitleBackground(ev.gc, this.titleBar));
            
            Font modifiedFont = CoreFontRegistry.getModifiedFont(parent.getFont(), 0, 0.9f);
            setFont(modifiedFont);
            
            this.expandButton2 = new ArrowButtonCanvas(this.titleBar);
            this.expandButton2.setDirection(PositionConstants.WEST);
            
            this.expandButtonSizeHint = 14;
            GridDataFactory.swtDefaults()
                    .hint(this.expandButtonSizeHint, this.expandButtonSizeHint)
                    .applyTo(this.expandButton2);
            
            // client pane
            this.clientPane = new Composite(this, SWT.NONE);
            GridDataFactory.fillDefaults().grab(true, true).applyTo(this.clientPane);
            this.clientPane.setLayout(new FillLayout());
        }

        @objid ("0bad5a3e-7cd9-4bd9-8ca4-77eb322a8673")
        private void updatePanelLayout(boolean rotated) {
            if (!rotated) {
                GridDataFactory.swtDefaults()
                        .align(SWT.FILL, SWT.CENTER)
                        .grab(true, false)
                        .applyTo(this.titleBar);
            } else {
                GridDataFactory.swtDefaults()
                        .align(SWT.BEGINNING, SWT.FILL)
                        .grab(true, true)
                        .applyTo(this.titleBar);
            }
        }

        @objid ("931422ab-801a-4fbc-a537-7df5e863f5d1")
        private void drawTitleBackground(GC gc, Control control) {
            Rectangle bounds = control.getBounds();
            int x = bounds.x;
            int y = bounds.y;
            int width = bounds.width;
            int height = bounds.height;
            
            Device display = gc.getDevice();
            Color backColor;
            Color backGradientColor;
            Color titleColor;
            if (isOpen()) {
                // backColor = display.getSystemColor (SWT.COLOR_TITLE_BACKGROUND);
                // backGradientColor = display.getSystemColor (SWT.COLOR_TITLE_BACKGROUND_GRADIENT);
                backColor = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);
                backGradientColor = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT);
                titleColor = display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
            } else {
                backColor = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);
                backGradientColor = display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT);
                titleColor = display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
                // backColor = display.getSystemColor (SWT.COLOR_TITLE_BACKGROUND);
                // backGradientColor = display.getSystemColor (SWT.COLOR_TITLE_BACKGROUND_GRADIENT);
                // titleColor = display.getSystemColor (SWT.COLOR_TITLE_INACTIVE_FOREGROUND);
            }
            
            gc.setForeground(backColor);
            gc.setBackground(backGradientColor);
            gc.fillGradientRectangle(x, y, width, height, isOpen());
            
            if (!isOpen()) {
                Point btSize = getExpandButton().getSize();
                Transform t = new Transform(display);
                t.translate(btSize.x, btSize.y + 5);
                t.rotate(90);
                gc.setTransform(t);
                gc.setFont(getFont());
                gc.setForeground(titleColor);
                gc.drawText(this.title, 0, 0, true);
            } else {
                Point btSize = getExpandButton().getSize();
                gc.setForeground(titleColor);
                gc.setFont(getFont());
                gc.drawText(this.title, btSize.x + 5, 0, true);
            }
        }

        @objid ("57ddbacc-524e-4abf-b97a-4d876bc401d8")
        public Sash getSash() {
            return this.sash;
        }

        @objid ("5b700487-4f27-46f9-acc8-b2aceffcbb28")
        public Composite getTitleBar() {
            return this.titleBar;
        }

        @objid ("81b08c88-b819-405d-9b1f-d60adc3f14d4")
        public Control getExpandButton() {
            return this.expandButton2;
        }

        @objid ("985d3309-0b82-453a-9896-06e1b1deb8aa")
        public boolean isOpen() {
            switch (this.state) {
            case collapsed:
                return false;
            case expanded:
            case pinned:
            default:
                return true;
            }
        }

        @objid ("9c964668-9467-473f-90fb-8dcd0638742e")
        public void setState(FlyoutState state) {
            this.state = state;
            switch (this.state) {
            case collapsed:
                this.expandButton2.setDirection(PositionConstants.WEST);
                ((GridData) this.clientPane.getLayoutData()).exclude = true;
                this.clientPane.setVisible(false);
                this.sash.setEnabled(false);
                this.sash.setVisible(false);
                break;
            case expanded:
            case pinned:
            default:
                ((GridData) this.clientPane.getLayoutData()).exclude = false;
                this.expandButton2.setDirection(PositionConstants.EAST);
                this.clientPane.setVisible(true);
                this.sash.setEnabled(true);
                this.sash.setVisible(true);
                break;
            }
            
            updatePanelLayout(!isOpen());
        }

        @objid ("0b92044c-698e-4703-8d6e-b63f67859d00")
        public FlyoutState getState() {
            return this.state;
        }

        /**
         * Return the client area.
         * @return the panel that contain client SWT component.
         */
        @objid ("3aea081e-dd52-4c85-b762-0bbeebfc3e62")
        public Composite getClientComposite() {
            return this.clientPane;
        }

        @objid ("f1aaca58-0d6f-46e7-8bf1-ef123a3ba1ca")
        @Override
        public String toString() {
            return getClass().getSimpleName() + "[state=" + this.state + ", layout=" +
                    getLayoutData() + "]";
        }

    }

    @objid ("1a7a6357-3958-44bf-abb6-0180b31b29af")
    public enum FlyoutState {
        collapsed,
        expanded,
        pinned;
    }

}
