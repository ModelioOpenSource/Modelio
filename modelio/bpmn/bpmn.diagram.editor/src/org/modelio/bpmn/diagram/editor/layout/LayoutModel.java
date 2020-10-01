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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;

/**
 * Data structure representing the layout during its computation by the Layouter and as final result for rendering.
 */
@objid ("84baf77c-c37d-4798-81b7-d701ff94d9ac")
public class LayoutModel {
    @objid ("e43bc524-b798-4e1e-a2f8-cd9f17ef5490")
    public int xSpacing = 60;

    @objid ("11ca62e4-8927-4ded-a552-5123080285af")
    public int ySpacing = 60;

    @objid ("c6f2cad5-4063-43f1-9058-e17202f4d2fd")
    private Map<IDiagramNode, ILayoutableNode> nodesLayout;

    @objid ("7877ff38-7b01-4f1b-84a5-aa07eb42093d")
    private Map<IDiagramLink, ILayoutableLink> linksLayout;

    @objid ("4fd4dd2c-753f-49e9-b070-ed59f0afd79a")
    private NodeTable nodeTable;

    @objid ("07d84efd-8540-43d5-a788-38fb15197c10")
    public LayoutModel() {
        this.nodesLayout = new HashMap<>();
        this.linksLayout = new HashMap<>();
        this.nodeTable = new NodeTable();
    }

    @objid ("6e302ece-0308-407a-82e9-95405bde1942")
    public int getNodeNumber() {
        return this.nodesLayout.size();
    }

    @objid ("8e82ba8d-0236-4833-b688-86c732b376b2")
    public void setNode(int row, int col, ILayoutableNode diagramNode) {
        this.nodeTable.setNode(row, col, diagramNode);
    }

    @objid ("6cdcef6d-dca4-4244-932c-3a69f1c2bc1a")
    public ILayoutableNode get(IDiagramNode n) {
        return this.nodesLayout.get(n);
    }

    @objid ("fbaa9c49-b655-445b-8d52-f6c7a32914d6")
    public List<ILayoutableNode> getNodes() {
        return new ArrayList<>(this.nodesLayout.values());
    }

    @objid ("50d8da9d-b5d0-41c6-a222-e73f9910f6d9")
    public void add(IDiagramLink link, ILayoutableLink layoutableLink) {
        this.linksLayout.put(link, layoutableLink);
    }

    @objid ("b5576af1-2c12-47ee-9f69-4a1164ea6c9d")
    public void add(IDiagramNode n, ILayoutableNode layoutableNode) {
        this.nodesLayout.put(n, layoutableNode);
    }

    @objid ("09949176-1232-4541-97a7-9fe1f45ed49e")
    public List<ILayoutableLink> getLinks() {
        return new ArrayList<>(this.linksLayout.values());
    }

    @objid ("261e6dbb-7264-4864-a06f-c607bb9616d3")
    public boolean isFreeCell(int row, int col) {
        return this.nodeTable.isFree(row, col);
    }

    @objid ("f60b9ffd-a5f4-4b14-923e-09d5639d19a6")
    public int getRowNumber() {
        return this.nodeTable.getRowNumber();
    }

    @objid ("59cbf638-fbf2-4f61-b32a-dd0a2f96f1bb")
    public int getColNumber() {
        return this.nodeTable.getColNumber();
    }

    @objid ("9b13cf54-d2b4-4bfe-9bf5-59082c459518")
    public int getColNumber(int row) {
        return this.nodeTable.getColNumber(row);
    }

    @objid ("c1019ef5-b9e6-4fc5-9acf-750a60e4096f")
    public ILayoutableNode getNode(int row, int col) {
        return this.nodeTable.getNode(row, col);
    }

    @objid ("ba9fa99c-dad6-4321-ac96-f763a78263d0")
    public ILayoutableLink get(IDiagramLink linkDg) {
        return this.linksLayout.get(linkDg);
    }

    @objid ("a10a34bc-b0ee-4f59-8d04-da2a62118738")
    private static class NodeTable {
        @objid ("8f61a658-6dd2-4ac8-8051-5a0d9e2432d1")
        private List<List<ILayoutableNode>> nodes = new ArrayList<>();

        @objid ("de77bf9f-3cde-436a-a953-09a9dc443765")
        public ILayoutableNode getNode(int row, int col) {
            try {
                return this.nodes.get(row).get(col);
            } catch (IndexOutOfBoundsException e) {
                return ILayoutableNode.NullNode;
            }
        }

        /**
         * Get the number of rows of the table
         * @return
         */
        @objid ("2b5b9d40-2d2f-4d2c-8c76-0ac9bc98a96b")
        public int getRowNumber() {
            return this.nodes.size();
        }

        /**
         * Get the number of defined columns in this row
         * @return
         */
        @objid ("572d5c6b-07c3-41ce-837a-f606f5bd7355")
        public int getColNumber(int row) {
            try {
                return this.nodes.get(row).size();
            } catch (IndexOutOfBoundsException e) {
                return 0;
            }
        }

        /**
         * Set node at (row,col). Resize the table if needed.
         */
        @objid ("aeb18340-358c-4866-b1df-8df1ce14a435")
        public void setNode(int row, int col, ILayoutableNode node) {
            if (row >= this.nodes.size()) {
                for (int i = this.nodes.size() - 1; i < row; i++) {
                    this.nodes.add(new ArrayList<>());
                }
            }
            List<ILayoutableNode> colValues = this.nodes.get(row);
            if (col >= colValues.size()) {
                for (int j = colValues.size() - 1; j < col; j++) {
                    colValues.add(ILayoutableNode.NullNode);
                }
            }
            colValues.set(col, node);
        }

        /**
         * Get the greatest number of columns in the table
         * @return
         */
        @objid ("05ca3f91-6689-4958-946c-84bdf4fae561")
        public int getColNumber() {
            int nbCols = 0;
            for (int row = 0; row < getRowNumber(); row++) {
                nbCols = Math.max(nbCols, this.nodes.get(row).size());
            }
            return nbCols;
        }

        @objid ("2b40cdbd-eb18-4891-b970-c93827553a93")
        public boolean isFree(int row, int col) {
            return getNode(row, col) == ILayoutableNode.NullNode;
        }

    }

}
