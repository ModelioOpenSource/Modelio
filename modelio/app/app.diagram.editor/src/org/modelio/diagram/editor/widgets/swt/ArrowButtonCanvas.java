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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.ButtonBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.modelio.diagram.editor.widgets.draw2d.ArrowButton;

/**
 * SWT Canvas containing a Draw2d {@link ArrowButton}.
 * 
 * @author cma, from GEF {@link org.eclipse.gef.ui.palette.FlyoutPaletteComposite}.
 */
@objid ("8a66a3d1-5ccf-4f60-9c10-f182376f5968")
public class ArrowButtonCanvas extends Draw2dCanvas {
    @objid ("df18ce34-9537-430a-a06a-26437223d429")
    public  ArrowButtonCanvas(Composite parent) {
        super(parent);
        init();
        provideAccSupport();
        
    }

    @objid ("873dbb30-e65b-4e1d-aab1-cf621f59ca0a")
    private void init() {
        setCursor(Cursors.ARROW);
        
        final ArrowButton b = new ArrowButton(PositionConstants.LEFT);
        b.setRolloverEnabled(true);
        b.setBorder(new ButtonBorder(ButtonBorder.SCHEMES.TOOLBAR));
        b.setBackgroundColor(ColorConstants.listBackground);
        b.setForegroundColor(ColorConstants.buttonDarkest);
        setContent(b);
        
        // Convert GEF event to SWT event
        b.addActionListener(ev -> {
            Event swtEvent = new Event();
            swtEvent.display = getDisplay();
            swtEvent.type = SWT.Selection;
            swtEvent.widget = this;
            
            notifyListeners(SWT.Selection, swtEvent);
        });
        
    }

    @objid ("285b76d8-0e83-4757-a08f-d33924f59738")
    private void provideAccSupport() {
        getAccessible().addAccessibleListener(new AccessibleAdapter() {
            @Override
            public void getDescription(AccessibleEvent e) {
                e.result = getToolTipText();
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

    /**
     * Get the draw2d button.
     * @return
     */
    @objid ("fa088904-5012-4a9a-9b3a-dc240de9f955")
    public ArrowButton getButton() {
        return (ArrowButton) getContent();
    }

    /**
     * Sets the direction the arrow will face.
     * <p>
     * Possible values are
     * {@link PositionConstants#NORTH}, {@link PositionConstants#SOUTH},
     * {@link PositionConstants#EAST} and {@link PositionConstants#WEST}.
     * @param direction The direction
     */
    @objid ("60305305-ed96-4ea8-baff-450c150bb6ec")
    public final void setDirection(int direction) {
        getButton().setDirection(direction);
    }

    /**
     * Add an {@link ActionListener} to react to button click.
     * @param l the listener
     */
    @objid ("197bc09e-bd70-4595-bb57-8b3c28ec8ede")
    public final void addActionListener(ActionListener l) {
        getButton().addActionListener(l);
    }

}
