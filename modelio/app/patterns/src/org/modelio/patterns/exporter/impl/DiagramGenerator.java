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

package org.modelio.patterns.exporter.impl;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode.Role;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.plugin.Patterns;

@objid ("1b653dfa-6954-493a-94d6-1b3531714bb3")
class DiagramGenerator {
    @objid ("8f2a5d8d-73da-4687-92e5-fbd71c2b941e")
    private List<AbstractDiagram> diagrams = new ArrayList<>();

    @objid ("4d932b04-8e1b-4579-bd5b-dda0c9cdd231")
    public void addDiagram(AbstractDiagram diagram) {
        this.diagrams.add(diagram);
    }

    @objid ("8fd75268-5320-4075-90cf-0c0c0baeac37")
    public void generate(final FileWriterUtil filewriter) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                for (AbstractDiagram diagram : DiagramGenerator.this.diagrams) {
                    generateIDiagramGraphic(filewriter, diagram);
                }
            }
        });
    }

    @objid ("db2638ec-4cf7-40ed-93cc-30e68e4cbdc1")
    private void generateIDiagramGraphic(FileWriterUtil filewriter, AbstractDiagram diagram) {
        try (IDiagramHandle rep = Modelio.getInstance().getDiagramService().getDiagramHandle(diagram)) {
            filewriter.write("rep = Modelio.getInstance().getDiagramService().getDiagramHandle(" + "(AbstractDiagram) this.elements.get(" + IdGenerator.getInstance().getId(diagram) + "));");
        
            IDiagramDG diagramNode = rep.getDiagramNode();
            filewriter.write("rep.getDiagramNode().setStyle(getIStyleHandleByName(\"" + diagramNode.getStyle().getName() + "\"));");
        
            for (String property : diagramNode.getLocalPropertyNames()) {
                String value = diagramNode.getProperty(property);
                filewriter.write("rep.getDiagramNode().setProperty(\"" + property + "\",\"" + value + "\");");
            }
        
            for (IDiagramNode graphic : diagramNode.getNodes()) {
                generateGraphicNode(filewriter, graphic);
            }
            for (IDiagramLink graphic : diagramNode.getLinks()) {
                generateGraphicLink(filewriter, graphic);
            }
        
            filewriter.write("rep.save();");
            filewriter.write("rep.close();");
        
            rep.close();
        }
    }

    @objid ("fd579edc-3662-4241-b756-0fb27772d18c")
    private void generateGraphicNode(FileWriterUtil filewriter, IDiagramNode graphic) {
        String nodeId = IdGenerator.getInstance().computeNextId();
        if (graphic.getElement() != null) {
            filewriter.write("try{");
            Rectangle bounds = graphic.getBounds();
            if (IdGenerator.getInstance().exists(graphic.getElement())) {
        
                String id = "this.elements.get(" + IdGenerator.getInstance().getId(graphic.getElement()) + ")";
        
                filewriter.write("rep.unmask(" + id + ", " + bounds.x + "," + bounds.y + ");");
        
                filewriter.write("links = rep.getDiagramGraphics(" + id + ");");
                filewriter.write("IDiagramNode " + nodeId + " = (IDiagramNode) links.get(0);");
                filewriter.write("rec = new Rectangle(" + bounds.x + "," + bounds.y + ","
                        + bounds.width + "," + bounds.height + ");");
                filewriter.write(nodeId + ".setBounds(rec);");
            } else if (graphic.getElement().getStatus().isRamc()) {
                filewriter.write("rep.unmask(session.findElementById(Metamodel.getJavaInterface(Metamodel.getMClass("
                        + graphic.getElement().getMClass().getJavaInterface().getName() + ".class)),\"" + graphic.getElement().getUuid().toString()
                        + "\")," + bounds.x + "," + bounds.y + ");");
                filewriter.write("links = rep.getDiagramGraphics(session.findElementById(Metamodel.getJavaInterface(Metamodel.getMClass("
                        + graphic.getElement().getMClass().getJavaInterface().getName()
                        + ".class)),\""
                        + graphic.getElement().getUuid().toString() + "\"));");
                filewriter.write("IDiagramNode " + nodeId + " = (IDiagramNode) links.get(0);");
                filewriter.write("rec = new Rectangle(" + bounds.x + "," + bounds.y + ","
                        + bounds.width + "," + bounds.height + ");");
                filewriter.write(nodeId + ".setBounds(rec);");
            } else {
                String name = ((ModelElement) graphic.getElement()).getName();
                filewriter.write("rep.unmask((MObject) this.parameters.get(\"" + name + "\")," + bounds.x + ","
                        + bounds.y + ");");
                filewriter.write("links = rep.getDiagramGraphics((MObject) this.parameters.get(\"" + name + "\"));");
                filewriter.write("IDiagramNode " + nodeId + " = (IDiagramNode) links.get(0);");
                filewriter.write("rec = new Rectangle(" + bounds.x + "," + bounds.y + ","
                        + bounds.width + "," + bounds.height + ");");
                filewriter.write(nodeId + ".setBounds(rec);");
            }
        
            for (String property : graphic.getLocalPropertyNames()) {
                String value = graphic.getProperty(property);
                filewriter.write(nodeId + ".setProperty(\"" + property + "\",\"" + value + "\");");
            }
        
            int index = 0;
            for (IDiagramNode child : graphic.getNodes(Role.SATELLITE)) {
                Rectangle childBounds = child.getBounds();
                filewriter.write(nodeId + ".getNodes().get(" + index++ + ").setBounds(new Rectangle(" + childBounds.x + "," + childBounds.y + "," + childBounds.width + "," + childBounds.height + "));");
            }
        
            filewriter.write("}catch (Exception e) {   Patterns.LOG.debug(e); }");
            filewriter.countWrite("");
        
            for (IDiagramNode child : graphic.getNodes(Role.INNER)) {
                generateGraphicNode(filewriter, child);
            }
        
            for (IDiagramNode child : graphic.getNodes(Role.PORT)) {
                generateGraphicNode(filewriter, child);
            }
        
            for (IDiagramLink child : graphic.getFromLinks()) {
                generateGraphicLink(filewriter, child);
            }
        
            for (IDiagramLink child : graphic.getToLinks()) {
                generateGraphicLink(filewriter, child);
            }
        }
    }

    @objid ("fd999cd2-08b1-43ba-956f-aa4ab1cf0ae2")
    private void generateGraphicLink(FileWriterUtil filewriter, IDiagramLink graphic) {
        if (IdGenerator.getInstance().exists(graphic.getElement())) {
            filewriter.write("try{");
            String id = "this.elements.get(" + IdGenerator.getInstance().getId(graphic.getElement()) + ")";
            String nodeId = IdGenerator.getInstance().computeNextId();
        
            filewriter.write("links = rep.unmask(" + id + ", 0, 0);");
            filewriter.write("links = rep.getDiagramGraphics(" + id + ");");
            filewriter.write("if(links.size() >0 && links.get(0) instanceof IDiagramLink){");
            filewriter.write("IDiagramLink " + nodeId + " = (IDiagramLink)links.get(0);");
        
            for (String property : graphic.getLocalPropertyNames()) {
                String value = graphic.getProperty(property);
                filewriter.write(nodeId + ".setProperty(\"" + property + "\",\"" + value + "\");");
                try {
                    filewriter.write(nodeId + ".setStyle(" + graphic.getStyle() + ");");
                } catch (Exception e) {
                    // Ignore error
                }
            }
        
            filewriter.write("points = new ArrayList<>();");
        
            try {
                ILinkPath path = graphic.getPath();
                if (path != null) {
        
                    for (Point point : path.getPoints()) {
                        filewriter.write("point = new Point(" + point.x + "," + point.y + ");");
                        filewriter.write("points.add(point);");
                    }
                    filewriter.write("try{");
                    filewriter.write(nodeId + ".setPath(points);");
                    filewriter.write("} catch (Exception e) {");
                    filewriter.write("calculate(" + nodeId + ");");
                    filewriter.write("}");
                }
        
            } catch (Exception e) {
                Patterns.LOG.debug(e);
            }
            filewriter.write("}");
            filewriter.write("}catch (Exception e) {   Patterns.LOG.debug(e); }");
            filewriter.countWrite("");
        }
    }

}
