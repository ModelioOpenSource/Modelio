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

package org.modelio.bpmnxml.nodes.finaliser.subplan;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.BPMNDiagram;
import org.modelio.bpmnxml.model.BPMNEdge;
import org.modelio.bpmnxml.model.BPMNLabel;
import org.modelio.bpmnxml.model.BPMNPlane;
import org.modelio.bpmnxml.model.BPMNShape;
import org.modelio.bpmnxml.model.Bounds;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.metamodel.diagrams.BehaviorDiagram;

@objid ("21bee1d5-f5b7-434b-960e-78638fdfd95e")
public class SubProcessActivityPlan {
    @objid ("78e3ac2b-37cf-4ef9-a8f7-81f6fd770472")
    private BehaviorDiagram modelioDiagram;

    @objid ("6c3b3de1-d35e-4fd2-9ff6-cdc82bbf5086")
    private BPMNDiagram jaxbDiagram;

    @objid ("d45b4fab-17d3-47b7-b74c-aa0a68e699c4")
    private Bounds bounds;

    @objid ("f2ff78d1-21ed-4ce0-9a94-3e83247bc9ee")
    private BPMNPlane jaxPlan;

    @objid ("ca5161bb-057f-40d8-8fdc-34bbafae11ba")
    public SubProcessActivityPlan(BehaviorDiagram modelioDiagram, Bounds bounds) {
        this.modelioDiagram = modelioDiagram;
        this.jaxbDiagram = new BPMNDiagram();
        this.jaxPlan = new BPMNPlane();
        this.jaxbDiagram.setBPMNPlane(this.jaxPlan);
        this.bounds = bounds;
    }

    @objid ("8179e1bd-27a5-45e3-84a6-112242c5cef4")
    public BehaviorDiagram getModelioDiagram() {
        return this.modelioDiagram;
    }

    @objid ("c7f6a0a1-9571-4b0d-a58b-aff38638d776")
    public BPMNDiagram getJaxbDiagram() {
        return this.jaxbDiagram;
    }

    @objid ("8ed253c7-2e21-468f-a298-00d975a0cb3d")
    public void addShape(BPMNShape jaxShape) {
        jaxShape.setBounds(transpose(jaxShape.getBounds()));
        
        BPMNLabel jaxLabel = jaxShape.getBPMNLabel();
        if (jaxLabel != null && jaxLabel.getBounds() != null) {
            jaxLabel.setBounds(transpose(jaxLabel.getBounds()));
        }
        
        ObjectFactory factory = new ObjectFactory();
        this.jaxPlan.getDiagramElement().add(factory.createBPMNShape(jaxShape));
    }

    @objid ("1239c951-becf-4c0d-9afa-fe11d23c61e2")
    public void addEdge(BPMNEdge jaxEdge) {
        List<org.modelio.bpmnxml.model.Point> newPoints = new ArrayList<>();
        for (org.modelio.bpmnxml.model.Point p : jaxEdge.getWaypoint()) {
            newPoints.add(transpose(p));
        }
        
        BPMNLabel jaxLabel = jaxEdge.getBPMNLabel();
        if (jaxLabel != null && jaxLabel.getBounds() != null) {
            jaxLabel.setBounds(transpose(jaxLabel.getBounds()));
        }
        
        jaxEdge.getWaypoint().clear();
        jaxEdge.getWaypoint().addAll(newPoints);
        
        ObjectFactory factory = new ObjectFactory();
        this.jaxPlan.getDiagramElement().add(factory.createBPMNEdge(jaxEdge));
    }

    @objid ("aa115d45-51cb-42aa-9399-4bff8ecdfe03")
    public boolean isInPlan(double x, double y) {
        if (x < this.bounds.getX() || x > this.bounds.getX() + this.bounds.getWidth()) {
            return false;
        }
        
        if (y < this.bounds.getY() || y > this.bounds.getY() + this.bounds.getHeight()) {
            return false;
        }
        return true;
    }

    @objid ("130a5a99-ca1c-4424-9a4d-9e22fa9ed190")
    private Bounds transpose(Bounds rec) {
        rec.setX(rec.getX() - this.bounds.getX());
        rec.setY(rec.getY() - this.bounds.getY());
        return rec;
    }

    @objid ("9d29f5e6-2413-41df-a955-c98001922ca5")
    private org.modelio.bpmnxml.model.Point transpose(org.modelio.bpmnxml.model.Point point) {
        point.setX(point.getX() - this.bounds.getX());
        point.setY(point.getY() - this.bounds.getY());
        return point;
    }

}
