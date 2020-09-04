/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.layout;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.diagram.elements.core.figures.geometry.LineSeg;

@objid ("edbe09a7-d0f9-4c9c-a768-dc199aabcb2a")
class LayoutableLink implements ILayoutableLink {
    @objid ("009ba9da-b504-4830-b4b5-7509e723b321")
    private AnchorDirection sourceAnchor;

    @objid ("2a1264a7-3517-4fa6-a103-0f897cbf61bd")
    private AnchorDirection targetAnchor;

    @objid ("313d3947-15f8-4336-b1d8-9f3444d7508a")
    private Shape shape;

    @objid ("bf526d9c-d5ac-4018-b889-f4fdc5cf2c04")
    private IDiagramLink linkDG;

    @objid ("da9cfe01-6693-423d-abe2-96cbcac6ee48")
    private ILayoutableNode from;

    @objid ("a91ed5ce-b590-4f59-8378-c6ed5314b9c2")
    private ILayoutableNode to;

    @objid ("47a8dd01-b339-47da-bf43-8842d7fdf556")
    private Point sourcePoint;

    @objid ("520ee89d-e7b5-475c-bde2-69bbed6e4b15")
    private Point targetPoint;

    @objid ("66cb4f21-268b-4e5f-82d5-d92b34156a2e")
    public LayoutableLink(IDiagramLink linkDG, ILayoutableNode from, ILayoutableNode to) {
        this.linkDG = linkDG;
        this.from = from;
        this.to = to;
    }

    @objid ("64c901fe-f1e8-4936-85c0-78f838e09983")
    @Override
    public ILayoutableNode getFrom() {
        return this.from;
    }

    @objid ("a381479a-4ce2-460e-b601-aeec737f64bc")
    @Override
    public ILayoutableNode getTo() {
        return this.to;
    }

    @objid ("187bf90e-73ac-4093-ab94-6a70e70ca6a0")
    @Override
    public Shape getShape() {
        return this.shape;
    }

    @objid ("e54b7ba4-fa7b-4c90-93ab-aba74431f0f9")
    @Override
    public AnchorDirection getSourceAnchor() {
        return this.sourceAnchor;
    }

    @objid ("2cf86608-2ee7-41cd-920a-15fa64c770ed")
    @Override
    public AnchorDirection getTargetAnchor() {
        return this.targetAnchor;
    }

    @objid ("b9fcbc51-7334-43f2-ba3c-e72f3b8341f0")
    @Override
    public void setSourceAnchor(AnchorDirection sourceAnchor) {
        this.sourceAnchor = sourceAnchor;
    }

    @objid ("37a35fea-5347-478c-b630-c691e94d0cb9")
    @Override
    public void setTargetAnchor(AnchorDirection targetAnchor) {
        this.targetAnchor = targetAnchor;
    }

    @objid ("2eb257b3-a8ad-484e-b3ed-b71810e29caf")
    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @objid ("8e04dfb5-f1a7-4f93-ae6d-49cb4829fac1")
    @Override
    public String toString() {
        return String.format("%s -> %s \t(%s, %s, %s)", this.from.getName(), this.to.getName(), this.shape, this.sourceAnchor, this.targetAnchor);
    }

    @objid ("64033d93-64bb-4c50-b6fc-d93edfa14cd4")
    @Override
    public boolean isSelected() {
        return this.linkDG.isSelected();
    }

    @objid ("d108b8f9-6e88-4a7d-8f06-42d7d115fb78")
    @Override
    public IDiagramLink getLinkDG() {
        return this.linkDG;
    }

    @objid ("d48ba0e1-088f-4c5c-95cf-645fd720b466")
    @Override
    public void setSourcePoint(Point pt) {
        this.sourcePoint = pt;
    }

    @objid ("89ceb49b-d415-4541-9b25-3826488b1f88")
    @Override
    public Point getSourcePoint() {
        return this.sourcePoint;
    }

    @objid ("848ac38e-c1b6-4a0d-a1dc-785646e3e534")
    @Override
    public Point getTargetPoint() {
        return this.targetPoint;
    }

    @objid ("44d5d587-c3c9-4de5-abc5-3fcdf192a7f6")
    @Override
    public void setTargetPoint(Point pt) {
        this.targetPoint = pt;
    }

    @objid ("4192d964-3a22-4404-b1c0-c30f80b6ce67")
    @Override
    public double getSlope() {
        return LineSeg.getLineEquation(this.from.getRow(), this.from.getCol(), this.to.getRow(), this.to.getCol())[0];
    }

}
