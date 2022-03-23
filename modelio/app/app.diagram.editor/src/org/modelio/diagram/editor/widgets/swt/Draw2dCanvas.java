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
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * SWT Canvas that contains a draw2d container.
 * 
 * 
 * @author cma, from GEF {@link org.eclipse.gef.ui.palette.FlyoutPaletteComposite}.
 */
@objid ("e84e58e9-6fde-4b9a-8b4d-85c152a2b01d")
public class Draw2dCanvas extends Canvas {
    @objid ("c46b9864-9731-46c4-895d-868c4055a42a")
    private LightweightSystem lws;

    @objid ("af4cbe36-c70f-48fb-8131-222828b00203")
    public  Draw2dCanvas(Composite parent) {
        super(parent, SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
        init();
        
    }

    @objid ("a8b39e35-2093-48ca-92aa-412224967cb2")
    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        Dimension size = this.lws.getRootFigure().getPreferredSize(wHint, hHint);
        size.union(new Dimension(wHint, hHint));
        return new org.eclipse.swt.graphics.Point(size.width, size.height);
    }

    @objid ("4000ab43-2b6a-417e-b283-fbcab702a198")
    private void init() {
        setCursor(Cursors.ARROW);
        this.lws = new LightweightSystem();
        this.lws.setControl(this);
        
    }

    @objid ("8647feb1-561e-4a6d-92b8-413018100a62")
    public void setContent(IFigure f) {
        this.lws.setContents(f);
    }

    @objid ("af21a3d2-f800-4f32-99f0-674f73d5a95d")
    public IFigure getContent() {
        return (IFigure) this.lws.getRootFigure().getChildren().get(0);
    }

}
