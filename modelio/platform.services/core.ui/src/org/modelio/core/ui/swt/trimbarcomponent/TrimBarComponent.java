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

package org.modelio.core.ui.swt.trimbarcomponent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.ui.UIColor;
import org.modelio.ui.UIFont;

/**
 * This class should be extended when adding a custom ToolControl to Modelio's main TrimBar in the e4 model.
 * <p>
 * Creates a widget including a title label, and a custom Control to be provided in {@link #createControl(Composite)}.
 * </p>
 * <p>
 * Each {@link TrimBarComponent} is created and disposed through injection, at the abstract class level. Overriding classes must not redefine @PostConstruct and @PreDestroy methods.
 * </p>
 */
@objid ("baef57be-7c18-470c-8519-2ac49d452516")
public abstract class TrimBarComponent {
    @objid ("2911855f-c131-4407-b317-232b966b6379")
    private boolean isVisible = false;

    @objid ("4f1cdaa9-07c0-4144-8ae2-186a10ca8e3b")
    private final String title;

    @objid ("d4829c1b-b177-42fd-a35c-e220abeb5bf8")
    private Control control;

    @objid ("1c91cf32-4548-4387-a56c-0bdd81f78a1a")
    private Composite controlZone;

    @objid ("874466b7-97b3-4144-8018-18eb43471da4")
    private Label titleLabel;

    @objid ("2a33a458-d383-4b20-87f8-e0447c28dc8b")
    public boolean isVisible() {
        return this.isVisible;
    }

    /**
     * Call this method when the component should be hidden/shown.
     */
    @objid ("b46af424-17a8-49d1-98b8-2c0854be5b41")
    public void setVisible(boolean isVisible) {
        if (this.isVisible != isVisible) {
            this.isVisible = isVisible;
            refreshLayout();
        }
    }

    @objid ("60e241e3-cc38-4b5f-ab9b-9a9b2663c592")
    protected TrimBarComponent(String title) {
        this.title = title;
    }

    /**
     * Defines the actual content of the TrimBar component.
     * @param parent a composite control which will be the parent of the new instance (cannot be null).
     * @return the created SWT control. Must not be <code>null</code>.
     */
    @objid ("ec3e5f02-dfb4-4255-8e42-c4562fb8d861")
    protected abstract Control createControl(final Composite parent);

    @objid ("ece7375b-ef3f-4759-b9aa-319b10e594b7")
    @PreDestroy
    protected final void dispose() {
        preDispose();
        
        this.controlZone.dispose();
        this.controlZone = null;
        this.control = null;
        this.titleLabel = null;
    }

    @objid ("89922cc6-22ce-4fdc-be4b-3c9134d77ff3")
    protected Control getControl() {
        return this.control;
    }

    @objid ("0f8f7ad6-6217-434d-8ca3-64ae4d90ef25")
    protected Label getTitleLabel() {
        return this.titleLabel;
    }

    /**
     * Override this method to insert behavior after SWT widgets have been created.
     * <p>
     * Default behavior does nothing.
     * </p>
     */
    @objid ("b84acd69-11b4-440b-8d9e-75fc1ea4471c")
    protected void postCreate() {
        // Default behavior does nothing
    }

    /**
     * Override this method to insert behavior before the disposal of the component
     * <p>
     * Default behavior does nothing.
     * </p>
     */
    @objid ("947a2936-21fb-47d2-b4c7-02b0262d3c0a")
    protected void preDispose() {
        // Default behavior does nothing
    }

    /**
     * Manually refresh the toolbar layout, as e4 is kind of lost with it...
     * <p>
     * When {@link #isHidden()} returns <code>true</code>, the component is hidden.
     * </p>
     */
    @objid ("7b5c46f7-bf8e-4b1c-81ef-73e0b58abcb6")
    protected void refreshLayout() {
        if (this.controlZone == null) {
            return;
        }
        
        boolean isHidden = !this.isVisible;
        for (Control c : this.controlZone.getChildren()) {
            GridData layoutData = (GridData) c.getLayoutData();
            layoutData.exclude = isHidden;
        }
        this.controlZone.setVisible(!isHidden);
        
        if (!isHidden) {
            this.control.pack();
        }
        this.control.getShell().layout(new Control[] { this.controlZone }, SWT.DEFER);
    }

    @objid ("dc105066-4c14-404d-b3e3-2b2bdb5b25e0")
    private final Label createTitleLabel(final Composite parent) {
        Label label = new Label(parent, SWT.NONE);
        label.setAlignment(SWT.CENTER);
        label.setText(this.title);
        // label.setBackground(UIColor.TABLE_HEADER_BG);
        // label.setForeground(UIColor.TABLE_HEADER_FG);
        
        label.setBackground(UIColor.SWT_WIDGET_BACKGROUND);
        label.setForeground(UIColor.SWT_WIDGET_FOREGROUND); //UIColor.SWT_WIDGET_NORMAL_SHADOW
        label.setFont(CoreFontRegistry.getModifiedFont(label.getFont(), SWT.NONE, UIFont.XSMALL_SIZE));
        
        label.addPaintListener(new PaintListener() {
        
            @Override
            public void paintControl(PaintEvent e) {
                Label l = (Label) e.getSource();
        
                Rectangle r = l.getParent().getClientArea();
        
        //                e.gc.setBackgroundPattern(
        //                        new Pattern(e.gc.getDevice(),
        //                                r.x, r.y, r.x, r.y + r.height, UIColor.SWT_WIDGET_BACKGROUND, UIColor.SWT_WIDGET_NORMAL_SHADOW));
        //                e.gc.fillRectangle(r);
        
        //                e.gc.setForeground(UIColor.SWT_WIDGET_NORMAL_SHADOW);
        //                e.gc.setLineDash(new int[] { 1, 1 });
        //                e.gc.drawLine(r.x + 2, r.y, r.x + r.width - 2, r.y);
        
        //                e.gc.setForeground(UIColor.SWT_WIDGET_NORMAL_SHADOW);
        //        
        //                Point ts = e.gc.textExtent(l.getText());
        
                //e.gc.drawText(l.getText(), r.x + r.width / 2 - ts.x / 2, r.y, true);
        
            }
        });
        return label;
    }

    /**
     * Initialize the SWT toolbar.
     * @param parent a widget which will be the parent of the new SWT components.
     */
    @objid ("18b624ec-9978-4af3-b378-8c1823088cf4")
    @PostConstruct
    private final void createWidget(final Composite parent) {
        this.controlZone = new Composite(parent, SWT.NONE);
        
        GridLayout zoneLayout = new GridLayout(1, false);
        zoneLayout.marginHeight = 1;
        zoneLayout.marginWidth = 2;
        zoneLayout.verticalSpacing = 0;
        zoneLayout.horizontalSpacing = 0;
        zoneLayout.marginBottom = 0;
        zoneLayout.marginLeft = 0;
        zoneLayout.marginRight = 0;
        zoneLayout.marginTop = 0;
        this.controlZone.setLayout(zoneLayout);
        
        // Create a toolbar.
        this.control = createControl(this.controlZone);
        this.control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        this.controlZone.addPaintListener(new PaintListener() {
        
            @Override
            public void paintControl(PaintEvent e) {
                Rectangle r = ((Composite) e.getSource()).getClientArea();
                
                e.gc.setForeground(UIColor.SWT_WIDGET_NORMAL_SHADOW);
                e.gc.setLineDash(new int[] { 1, 1 });
                
                // Top
                //e.gc.drawLine(r.x, r.y, r.x+r.width-1 , r.y );
                
                // Left side
                e.gc.drawLine(r.x, r.y+2, r.x, r.y + r.height - 2);
                
                // Right side
                e.gc.drawLine(r.x+r.width-1, r.y+2, r.x + r.width-1, r.y + r.height - 2);
                
                //Bottom
                //e.gc.drawLine(r.x, r.y+r.height-1, r.x+r.width-1 , r.y + r.height-1);
            }
        });
        
        this.titleLabel = createTitleLabel(this.controlZone);
        this.titleLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        postCreate();
        
        refreshLayout();
    }

}
