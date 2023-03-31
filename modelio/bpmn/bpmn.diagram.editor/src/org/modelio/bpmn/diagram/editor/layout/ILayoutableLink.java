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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.api.modelio.diagram.IDiagramLink;

@objid ("b7a8714e-2c40-4094-abbd-2121c940c298")
public interface ILayoutableLink {
    @objid ("59ab027e-17c0-4891-aa51-0851eae90766")
    ILayoutableNode getFrom();

    @objid ("0a565b35-f1c5-4cb5-b5a5-ca318bfca939")
    ILayoutableNode getTo();

    @objid ("9ca3b52c-fbb7-475b-9130-06461bfad9ee")
    Shape getShape();

    @objid ("da26dd77-445f-45bc-9fb0-9db20c5708a7")
    AnchorDirection getSourceAnchor();

    @objid ("9abb3c4a-4c57-489c-86fe-cefd81639cb6")
    AnchorDirection getTargetAnchor();

    @objid ("1eb622fd-4bbc-47f9-97b9-916c3ee0f3d9")
    void setSourceAnchor(AnchorDirection sourceAnchor);

    @objid ("985926ba-b534-4551-9b01-e0e3b2b0e1e3")
    void setTargetAnchor(AnchorDirection targetAnchor);

    @objid ("57ac3cb1-12de-4fbf-9b75-511e4ea80662")
    void setShape(Shape shape);

    @objid ("aac75202-00d6-47cc-ba0c-bfe76d2c8bfe")
    boolean isSelected();

    @objid ("17c66370-28b2-471b-b7af-4ab759af0cdc")
    IDiagramLink getLinkDG();

    @objid ("ce78fd06-e2a7-4920-ab70-d52626e60268")
    void setSourcePoint(Point pt);

    @objid ("fc49fcd3-7d77-4b3a-8cd6-2682df806374")
    void setTargetPoint(Point pt);

    @objid ("ca740d62-3097-46b7-8cfb-a7a2580b0ec9")
    Point getSourcePoint();

    @objid ("cb2e055b-c9a9-44a6-9831-156016dfd252")
    Point getTargetPoint();

    @objid ("5c12113b-4ec2-45dd-8f76-e246ea30c264")
    double getSlope();

    @objid ("89d9a76c-6205-4dd8-8140-ca0f85487ced")
    enum Shape {
        @objid ("cda9827c-c2eb-44c8-9679-2395fcfd68f5")
        HorizontalDirect,
        @objid ("0da54419-e424-4f94-b0b1-b6b66aea97cf")
        VerticalDirect,
        @objid ("fc28c05e-dc68-498f-a528-07d8f89b9898")
        HorizontalBridge,
        @objid ("1e46ab29-a9e9-4661-869f-d21225c15979")
        VerticalBridge,
        @objid ("a1668701-7c85-4206-afd5-af18dec6cc65")
        VerticalZ,
        @objid ("2b2c6241-4ba1-47da-923f-c4d71c26f8d1")
        Direct,
        @objid ("70af7df5-9a88-48e4-bbab-5e276d84db8d")
        Corner;

    }

    @objid ("75bebaac-9029-4568-a8c7-c055907cfb9b")
    enum AnchorDirection {
        @objid ("34f74dec-5d7e-48ff-a155-e72e3ae7681b")
        North,
        @objid ("bd24de54-2780-439f-b64d-f388d8f66263")
        South,
        @objid ("c1d53687-7721-4220-924a-874f7f27d7c1")
        West,
        @objid ("030c2dcd-2609-4076-bd87-32e564e8820c")
        East;

    }
}

