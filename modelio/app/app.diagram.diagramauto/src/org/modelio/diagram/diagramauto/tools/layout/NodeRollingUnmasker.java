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
package org.modelio.diagram.diagramauto.tools.layout;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d38e4fcc-6241-4320-a60d-4b2a49c42da1")
public class NodeRollingUnmasker {
    /**
     * The unmask point is shifted for each unmasked node to to avoid unmasking nodes on each other (which could lead to unexecped re-parenting !)
     */
    @objid ("9c8eb3d7-c0ca-4abf-b1f6-2e2d411c2207")
    protected Point _unmaskPoint;

    @objid ("4b8dbf1b-06bc-4720-9895-1981639f156c")
    public  NodeRollingUnmasker() {
        this._unmaskPoint = new Point(0, 0);
    }

    @objid ("e790bc43-6209-4263-8ee4-5fd10d9b968e")
    public IDiagramNode unmask(final IDiagramHandle dh, final MObject element) {
        List<IDiagramGraphic> nodes = dh.unmask(element, this._unmaskPoint.x, this._unmaskPoint.y);
        if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
            IDiagramNode node = (IDiagramNode) nodes.get(0);
            shiftUnmaskPoint(node);
            return node;
        }
        return null;
    }

    @objid ("8fd1ecdd-ce66-427a-9331-a91b3332bf83")
    public IDiagramNode unmask(final IDiagramHandle dh, final MObject element, int width, int height) {
        List<IDiagramGraphic> nodes = dh.unmask(element, this._unmaskPoint.x, this._unmaskPoint.y);
        if ((nodes != null) && (nodes.size() > 0) && nodes.get(0) instanceof IDiagramNode) {
            IDiagramNode node = (IDiagramNode) nodes.get(0);
            node.setSize(width, height);
            shiftUnmaskPoint(node);
            return node;
        }
        return null;
    }

    @objid ("27e2a186-7fc6-45ba-8ae3-a9aabe64eb4a")
    private void shiftUnmaskPoint(IDiagramNode node) {
        this._unmaskPoint.translate(node.getBounds().getSize().expand(10, 10));
    }

}
