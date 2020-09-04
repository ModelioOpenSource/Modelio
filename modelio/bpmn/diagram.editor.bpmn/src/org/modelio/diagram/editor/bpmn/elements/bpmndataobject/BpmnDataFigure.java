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

package org.modelio.diagram.editor.bpmn.elements.bpmndataobject;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.elements.umlcommon.note.NoteFigure;

/**
 * A BpmnDataFigure figure is is made of: <br>
 * <ul>
 * <li>a top area containing iconS</li>
 * <li>a center icon</li>
 * </ul>
 */
@objid ("60aca68a-55b6-11e2-877f-002564c97630")
public class BpmnDataFigure extends ShapedFigure {
    @objid ("966cb4e4-c631-4c7b-9bdc-b43b5dd28ae0")
    protected Figure topContainer;

    @objid ("6c8eee0d-e5e3-49f9-aa55-cb013b1e78be")
    private ShapedBorder shapedBorder;

    @objid ("cd1af7f7-645a-456c-b008-0c0d6872d593")
    private ImageFigure centerIcon = null;

    @objid ("60ae2d23-55b6-11e2-877f-002564c97630")
    public BpmnDataFigure() {
        super();
        setShaper(new NoteFigure.NoteShaper());
        this.setLayoutManager(new BorderLayout());
        
        this.topContainer = new Figure();
        this.topContainer.setLayoutManager(new FlowLayout());
        this.topContainer.setOpaque(false);
        
        this.add(this.topContainer, BorderLayout.TOP);
        this.shapedBorder = new ShapedBorder(this.getLineColor(), this.getLineWidth(), new NoteFigure.NoteShaper());
        this.setBorder(this.shapedBorder);
    }

    @objid ("60ae2d25-55b6-11e2-877f-002564c97630")
    public void setCenterIcon(Image icon) {
        if (this.centerIcon != null) {
            this.remove(this.centerIcon);
            this.centerIcon  = null;
        }
        
        // add new image figure
        if (icon != null) {
            this.centerIcon = new ImageFigure(icon);
            this.add(this.centerIcon, BorderLayout.CENTER);
        }
    }

    @objid ("60ae2d2b-55b6-11e2-877f-002564c97630")
    public void setTopIcons(final List<Image> icons) {
        // remove existing labels
        this.topContainer.removeAll();
        // add new image figures
        for (Image img : icons) {
            ImageFigure imgFigure = new ImageFigure(img);
            imgFigure.setBorder(new MarginBorder(5, 5, 0, 0));
            this.topContainer.add(imgFigure, BorderLayout.LEFT);
        }
    }

    @objid ("b3026e0f-853e-4425-8cc6-d8235c19e2bf")
    @Override
    public void setLineColor(Color lineColor) {
        this.shapedBorder.setColor(lineColor);
        super.setLineColor(lineColor);
    }

    @objid ("c454782a-233c-4e7a-ba7e-0f882677b537")
    @Override
    public void setLineWidth(int lineWidth) {
        this.shapedBorder.setWidth(lineWidth);
        super.setLineWidth(lineWidth);
    }

}
