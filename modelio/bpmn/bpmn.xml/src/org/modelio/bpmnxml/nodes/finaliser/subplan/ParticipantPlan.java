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

package org.modelio.bpmnxml.nodes.finaliser.subplan;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.modelio.bpmnxml.model.BPMNEdge;
import org.modelio.bpmnxml.model.BPMNLabel;
import org.modelio.bpmnxml.model.BPMNPlane;
import org.modelio.bpmnxml.model.BPMNShape;
import org.modelio.bpmnxml.model.Bounds;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;

@objid ("569f8b00-84bb-44ba-a0bd-0c1c1776a5dc")
public class ParticipantPlan {
    @objid ("1ae2eee9-afa7-4415-b373-e056336c3e16")
    private BpmnProcessCollaborationDiagram diagram;

    @objid ("6c98b61a-5960-4443-9777-37b349fc8aaa")
    private Bounds bounds;

    @objid ("acbe6a5f-2701-40d2-b5bd-865e0a5f9605")
    private Point transposition;

    @objid ("d6e5156c-cc92-4b8b-8bff-c480b0d104d6")
    private BPMNPlane jaxPlan;

    @objid ("8693efae-f233-4188-b4ad-0264259c591a")
    public ParticipantPlan(BpmnProcessDesignDiagram diagram, Bounds bounds) {
        this.diagram = diagram;
        this.jaxPlan = new BPMNPlane();
        this.bounds = bounds;
        this.transposition = new PrecisionPoint(bounds.getX(), bounds.getY());
    }

    @objid ("2752e3ff-581f-421f-a7d5-dd3c8a56ae64")
    public BpmnProcessCollaborationDiagram getDiagram() {
        return this.diagram;
    }

    @objid ("86c3cbe7-9f31-4185-a092-26496f3add1d")
    public boolean isInPlan(double x, double y) {
        if (x < this.bounds.getX() || x > this.bounds.getX() + this.bounds.getWidth()) {
            return false;
        }
        
        if (y < this.bounds.getY() || y > this.bounds.getY() + this.bounds.getHeight()) {
            return false;
        }
        return true;
    }

    @objid ("a7c69182-9310-49af-8bac-7dbb418c12f3")
    private Bounds transpose(Bounds rec) {
        rec.setX(rec.getX() - this.transposition.x);
        rec.setY(rec.getY() - this.transposition.y);
        return rec;
    }

    @objid ("732f3449-d09a-478b-8f29-63f31942e19f")
    private org.modelio.bpmnxml.model.Point transpose(org.modelio.bpmnxml.model.Point point) {
        point.setX(point.getX() - this.transposition.x);
        point.setY(point.getY() - this.transposition.y);
        return point;
    }

    @objid ("754e31b6-d868-40c9-b636-30de4418890f")
    public void addShape(BPMNShape jaxShape) {
        jaxShape.setBounds(transpose(jaxShape.getBounds()));
        
        BPMNLabel jaxLabel = jaxShape.getBPMNLabel();
        if (jaxLabel != null && jaxLabel.getBounds() != null) {
            jaxLabel.setBounds(transpose(jaxLabel.getBounds()));
        }
        
        ObjectFactory factory = new ObjectFactory();
        this.jaxPlan.getDiagramElement().add(factory.createBPMNShape(jaxShape));
    }

    @objid ("db665e07-c476-4026-a7a1-e350fe34447d")
    public void addEdge(BPMNEdge jaxEdge) {
        List<org.modelio.bpmnxml.model.Point> newPoints = new ArrayList<>();
        for (org.modelio.bpmnxml.model.Point p : jaxEdge.getWaypoint()) {
            newPoints.add(transpose(p));
        }
        jaxEdge.getWaypoint().clear();
        jaxEdge.getWaypoint().addAll(newPoints);
        
        BPMNLabel jaxLabel = jaxEdge.getBPMNLabel();
        if (jaxLabel != null && jaxLabel.getBounds() != null) {
            jaxLabel.setBounds(transpose(jaxLabel.getBounds()));
        }
        
        ObjectFactory factory = new ObjectFactory();
        this.jaxPlan.getDiagramElement().add(factory.createBPMNEdge(jaxEdge));
    }

    @objid ("eb127d1f-9eb1-4431-9426-826849af5ab2")
    public BPMNPlane getSubPlan() {
        return this.jaxPlan;
    }

}
