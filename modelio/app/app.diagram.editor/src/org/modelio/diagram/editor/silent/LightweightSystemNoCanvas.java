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

package org.modelio.diagram.editor.silent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

/**
 * Override of LightweightSystem that can work without having a Control.
 * 
 * @author fpoyer
 */
@objid ("6696f9f1-33f7-11e2-95fe-001ec947c8cc")
public final class LightweightSystemNoCanvas extends LightweightSystem {
    /**
     * C'tor.
     */
    @objid ("6696f9f3-33f7-11e2-95fe-001ec947c8cc")
    public LightweightSystemNoCanvas() {
        super();
        setUpdateManager(new SynchronousUpdateManager());
    }

    @objid ("6696f9f6-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void setControl(final Canvas c) {
        // Size the root figure and contents to the current control's size
        Rectangle r = new Rectangle(0, 0, 1000, 1000);
        getRootFigure().setBounds(r);
        getRootFigure().revalidate();
    }

    @objid ("6696f9fb-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void controlResized() {
        return;
    }

    @objid ("6696f9ff-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected org.eclipse.draw2d.LightweightSystem.RootFigure createRootFigure() {
        RootFigure rootFig = new NoControlRootFigure();
        rootFig.addNotify();
        rootFig.setOpaque(true);
        rootFig.setLayoutManager(new StackLayout());
        return rootFig;
    }

    @objid ("6696fa05-33f7-11e2-95fe-001ec947c8cc")
    private final class NoControlRootFigure extends org.eclipse.draw2d.LightweightSystem.RootFigure {
        @objid ("6696fa06-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public boolean isMirrored() {
            return false;
        }

        @objid ("66995c3a-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public Font getFont() {
            Font f = super.getFont();
            if (f != null) {
                return f;
            } else {
                return Display.getDefault().getSystemFont();
            }
        }

        @objid ("66995c3e-33f7-11e2-95fe-001ec947c8cc")
        public NoControlRootFigure() {
            super();
        }

    }

}
