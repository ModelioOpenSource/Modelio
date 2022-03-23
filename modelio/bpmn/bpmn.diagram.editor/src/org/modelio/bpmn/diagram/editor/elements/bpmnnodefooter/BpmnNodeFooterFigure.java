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
package org.modelio.bpmn.diagram.editor.elements.bpmnnodefooter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.modelio.bpmn.diagram.editor.editor.BpmnSharedImages;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.common.image.ImageFigure;

@objid ("617303db-55b6-11e2-877f-002564c97630")
public class BpmnNodeFooterFigure extends Figure {
    @objid ("71f9d0a9-55c1-11e2-9337-002564c97630")
    private ImageFigure emptySubProcessFigure;

    @objid ("71f9d0aa-55c1-11e2-9337-002564c97630")
    private ImageFigure loopFigure;

    @objid ("71f9d0ab-55c1-11e2-9337-002564c97630")
    private ImageFigure parallelFigure;

    @objid ("71f9d0ac-55c1-11e2-9337-002564c97630")
    private ImageFigure sequentialFigure;

    @objid ("71f9d0ad-55c1-11e2-9337-002564c97630")
    private ImageFigure adhocFigure;

    @objid ("71f9d0ae-55c1-11e2-9337-002564c97630")
    private ImageFigure copensationFigure;

    @objid ("71f9d0af-55c1-11e2-9337-002564c97630")
    private ImageFigure nonemptySubProcessFigure;

    @objid ("617303f1-55b6-11e2-877f-002564c97630")
    public  BpmnNodeFooterFigure() {
        this.emptySubProcessFigure = new ImageFigure();
        this.emptySubProcessFigure.setIcon(DiagramEditorBpmn
                .getImageRegistry()
                .getImage(BpmnSharedImages.SUBPROCESS));
        
        this.nonemptySubProcessFigure = new ImageFigure();
        this.nonemptySubProcessFigure.setIcon(DiagramEditorBpmn
                .getImageRegistry()
                .getImage(BpmnSharedImages.NONEMPTYSUBPROCESS));
        
        this.loopFigure = new ImageFigure();
        this.loopFigure.setIcon(DiagramEditorBpmn
                .getImageRegistry()
                .getImage(BpmnSharedImages.LOOP));
        
        this.parallelFigure = new ImageFigure();
        this.parallelFigure.setIcon(DiagramEditorBpmn
                .getImageRegistry()
                .getImage(BpmnSharedImages.PARALLEL));
        
        this.sequentialFigure = new ImageFigure();
        this.sequentialFigure.setIcon(DiagramEditorBpmn
                .getImageRegistry()
                .getImage(BpmnSharedImages.SEQUENTIAL));
        
        this.adhocFigure = new ImageFigure();
        this.adhocFigure.setIcon(DiagramEditorBpmn
                .getImageRegistry()
                .getImage(BpmnSharedImages.ADHOC));
        
        this.copensationFigure = new ImageFigure();
        this.copensationFigure.setIcon(DiagramEditorBpmn
                .getImageRegistry()
                .getImage(BpmnSharedImages.COMPENSATION));
        
    }

    @objid ("617303f3-55b6-11e2-877f-002564c97630")
    void setEmptySubProcessVisible(boolean visible) {
        if (visible) {
            if (!this.getChildren().contains(this.emptySubProcessFigure)) {
                this.add(this.emptySubProcessFigure);
            }
        } else if (this.getChildren().contains(this.emptySubProcessFigure)) {
            this.remove(this.emptySubProcessFigure);
        }
        
    }

    @objid ("617303f6-55b6-11e2-877f-002564c97630")
    void setParallelVisible(boolean visible) {
        if (visible) {
            if (!this.getChildren().contains(this.parallelFigure)) {
                this.add(this.parallelFigure);
            }
        } else if (this.getChildren().contains(this.parallelFigure)) {
            this.remove(this.parallelFigure);
        }
        
    }

    @objid ("617303f9-55b6-11e2-877f-002564c97630")
    void setSequentialVisible(boolean visible) {
        if (visible) {
            if (!this.getChildren().contains(this.sequentialFigure)) {
                this.add(this.sequentialFigure);
            }
        } else if (this.getChildren().contains(this.sequentialFigure)) {
            this.remove(this.sequentialFigure);
        }
        
    }

    @objid ("617303fc-55b6-11e2-877f-002564c97630")
    void setAdHocVisible(boolean visible) {
        if (visible) {
            if (!this.getChildren().contains(this.adhocFigure)) {
                this.add(this.adhocFigure);
            }
        } else if (this.getChildren().contains(this.adhocFigure)) {
            this.remove(this.adhocFigure);
        }
        
    }

    @objid ("61748a5b-55b6-11e2-877f-002564c97630")
    void setCompensationVisible(boolean visible) {
        if (visible) {
            if (!this.getChildren().contains(this.copensationFigure)) {
                this.add(this.copensationFigure);
            }
        } else if (this.getChildren().contains(this.copensationFigure)) {
            this.remove(this.copensationFigure);
        }
        
    }

    @objid ("61748a5e-55b6-11e2-877f-002564c97630")
    void setLoopVisible(boolean visible) {
        if (visible) {
            if (!this.getChildren().contains(this.loopFigure)) {
                this.add(this.loopFigure);
            }
        } else if (this.getChildren().contains(this.loopFigure)) {
            this.remove(this.loopFigure);
        }
        
    }

    @objid ("61748a61-55b6-11e2-877f-002564c97630")
    public void setNonEmptySubProcessVisible(final boolean visible) {
        if (visible) {
            if (!this.getChildren().contains(this.nonemptySubProcessFigure)) {
                this.add(this.nonemptySubProcessFigure);
            }
        } else if (this.getChildren().contains(this.nonemptySubProcessFigure)) {
            this.remove(this.nonemptySubProcessFigure);
        }
        
    }

}
