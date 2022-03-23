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
package org.modelio.diagram.editor.widgets.gef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

@objid ("fd14a2eb-1f61-48f3-ad43-f34b1e5847ca")
public class OutlinePage extends org.eclipse.gef.ui.parts.ContentOutlinePage {
    @objid ("df24871c-f606-4aab-a0f7-87927f754550")
    ScrollableThumbnail thumbnail;

    @objid ("c331a7d6-4acd-4a58-b9bd-6991c8e6ad63")
    private Composite panel;

    @objid ("28f0419e-95cc-46bb-a5fb-3ed8d1a8aaa8")
    private SelectionSynchronizer synchronizer;

    @objid ("423c1190-6c9d-40b2-8fce-658b6a07170b")
    private DisposeListener disposeListener;

    @objid ("4e36160c-0303-42f1-8196-e4fbe03d9065")
    private GraphicalViewer graphicalViewer;

    @objid ("61b300a5-b7b9-4158-8d9e-29a0f1d49cc9")
    public  OutlinePage(GraphicalViewer graphicalViewer, SelectionSynchronizer synchronizer) {
        super(new ScrollingGraphicalViewer());
        this.synchronizer = synchronizer;
        this.graphicalViewer = graphicalViewer;
        
    }

    @objid ("8c6c1bfe-d97e-4115-a208-4c757bc267ff")
    @Override
    public void createControl(Composite parent) {
        this.panel = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout(1, true);
        gl.horizontalSpacing = 0;
        gl.verticalSpacing = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        this.panel.setLayout(gl);
        
        this.synchronizer.addViewer(this.getViewer());
        
        // Create the thumbnail view
        Canvas canvas = new Canvas(this.panel, SWT.BORDER);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        canvas.setLayoutData(gd);
        LightweightSystem lws = new LightweightSystem(canvas);
        final GraphicalViewer viewer = this.graphicalViewer;
        if (viewer != null) {
            this.thumbnail = new ScrollableThumbnail(
                    (Viewport) ((ScalableFreeformRootEditPart) viewer.getRootEditPart()).getFigure());
        
            this.thumbnail.setSource(((ScalableFreeformRootEditPart) viewer.getRootEditPart())
                    .getLayer(LayerConstants.PRINTABLE_LAYERS));
            lws.setContents(this.thumbnail);
        
            // add a dispose listener for cleaning
            this.disposeListener = e -> {
                if (OutlinePage.this.thumbnail != null) {
                    OutlinePage.this.thumbnail.deactivate();
                    OutlinePage.this.thumbnail = null;
                }
                OutlinePage.this.dispose(); // dispose the outline page to
                // avoid a graphical refresh
                // problem
            };
            viewer.getControl().addDisposeListener(this.disposeListener);
        }
        
    }

    @objid ("1f2b0b41-d4fe-4d45-b3ee-18e8f5d2c58b")
    @Override
    public void dispose() {
        this.synchronizer.removeViewer(this.getViewer());
        if (this.graphicalViewer != null && this.graphicalViewer.getControl() != null && !this.graphicalViewer.getControl().isDisposed()) {
            this.graphicalViewer.getControl().removeDisposeListener(this.disposeListener);
        }
        super.dispose();
        
    }

    @objid ("87653ec5-1358-4403-83d9-ea3050ec366a")
    @Override
    public Control getControl() {
        return this.panel;
    }

}
