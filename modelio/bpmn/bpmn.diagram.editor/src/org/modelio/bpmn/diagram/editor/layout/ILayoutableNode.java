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

package org.modelio.bpmn.diagram.editor.layout;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.bpmn.diagram.editor.layout.ILayoutableLink.AnchorDirection;

@objid ("ef586cc5-3be9-4a07-861f-2736e1ddb2f9")
public interface ILayoutableNode {
    /**
     * The 'Null' Node.
     * <ul>
     * <li>has (0,0,0,0) bounds</li>
     * <li>has an empty unmodifiable list of predecessors</li>
     * <li>has an empty unmodifiable list of successors</li>
     * <li>has an empty unmodifiable list of 'from' links</li>
     * <li>is located at col=0, row=0</li>
     * <li>is not modifiable</li>
     * </ul>
     */
    @objid ("e34474f8-86b7-4ef6-a5e8-19c6f7e038ac")
    public static final ILayoutableNode NullNode = new ILayoutableNode() {
        @Override
        public void setRow(int row) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void setLocation(int x, int y) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void setCol(int col) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void setRightDepth(int computeRightDepth) {
            throw new UnsupportedOperationException();
        }
        @Override
        public List<ILayoutableNode> getSuccessors() {
            return java.util.Collections.EMPTY_LIST;
        }
        @Override
        public int getRow() {
            return 0;
        }
        @Override
        public List<ILayoutableNode> getPredecessors() {
            return java.util.Collections.EMPTY_LIST;
        }
        @Override
        public List<IDiagramLink> getFromLinks() {
            return java.util.Collections.EMPTY_LIST;
        }
        @Override
        public int getCol() {
            return 0;
        }
        @Override
        public Rectangle getBounds() {
            return new Rectangle(0, 0, 0, 0);
        }
        @Override
        public String getName() {
            return "NullNode";
        }
        @Override
        public int getRightDepth() {
            return 0;
        }
        @Override
        public List<IDiagramLink> getToLinks() {
            return java.util.Collections.EMPTY_LIST;
        }
        @Override
        public List<ILayoutableLink> getInLinks(AnchorDirection anchorDirection) {
            return java.util.Collections.EMPTY_LIST;
        }
        @Override
        public List<ILayoutableLink> getOutLinks(AnchorDirection anchorDirection) {
            return java.util.Collections.EMPTY_LIST;
        }
    };

// @objid ("497ca9e9-8085-4345-be57-92c253d41df1")
// IDiagramNode getDiagramNode();
    @objid ("3e7bd7dd-fa05-4733-b3cc-3cb6d8618ff5")
    List<ILayoutableNode> getPredecessors();

    @objid ("afdeda38-c995-4bb3-bda0-9c4ecda23cd3")
    List<ILayoutableNode> getSuccessors();

    @objid ("2c1d7846-51e6-492b-9bf3-4b43b125a321")
    int getCol();

    @objid ("ffae4c43-6fbd-420d-9302-11565668bdb1")
    void setCol(int col);

    @objid ("21c1d06f-d3a1-4820-9b8b-1a5287c87a7a")
    int getRow();

    @objid ("a69fcc63-b29b-41af-9f15-e4e36abb32c8")
    void setRow(int row);

    @objid ("33be5cb3-9723-4aac-a75c-bf7d929289ea")
    List<IDiagramLink> getFromLinks();

    @objid ("c01883fe-93a4-410e-b420-853cf5f8ab25")
    void setLocation(int x, int y);

    @objid ("b57b8b69-5fb3-4592-a128-5d1293e38b68")
    Rectangle getBounds();

    @objid ("b58c8d81-571f-4561-91d7-408babc2b6f0")
    String getName();

    @objid ("ae031456-7ecc-40cb-83e5-4a1f79e1a708")
    void setRightDepth(int computeRightDepth);

    @objid ("c9bcb05a-2c57-4891-abcf-b638b0b984e7")
    int getRightDepth();

    @objid ("fae0ebb1-617d-4f4b-842d-0e64559e1087")
    List<IDiagramLink> getToLinks();

    @objid ("098fafa7-28e1-42b6-a075-c564fcb2fece")
    List<ILayoutableLink> getInLinks(AnchorDirection anchorDirection);

    @objid ("c22cf0a1-6373-4455-b3c2-60fa5bf61117")
    List<ILayoutableLink> getOutLinks(AnchorDirection anchorDirection);

}
