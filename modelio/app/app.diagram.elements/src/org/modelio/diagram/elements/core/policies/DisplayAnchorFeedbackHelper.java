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
package org.modelio.diagram.elements.core.policies;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.figures.anchors.AnchorFigureFactory;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.link.RoutingModeGetter;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.handle.AnchorHandle;
import org.modelio.diagram.elements.core.link.anchors.handle.TranslatedAnchorLocator;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

@objid ("c709e541-f4af-4793-8902-57e6ce347adf")
public class DisplayAnchorFeedbackHelper {
    @objid ("6be2cfa8-b89b-483a-b7cd-15d032b89456")
    private IFigure[] feedbacks;

    @objid ("bc5f925a-943a-4675-a739-8b0053a55751")
    private final IFixedNodeAnchorProvider anchorProvider;

    @objid ("dda16344-85f7-4021-9628-ef623fd8c62d")
    private final IFigure feedbackLayer;

    @objid ("6dddbdce-0e12-4ac7-8fd1-b79d26217a21")
    private final GraphicalEditPart host;

    @objid ("909889cb-d52b-418a-a7df-d1dd986f3a4a")
    private final IFigure hostFigure;

    @objid ("c75e6c61-d411-40e7-a055-6a16134dc290")
    public  DisplayAnchorFeedbackHelper(EditPart host, IFigure hostFigure, IFixedNodeAnchorProvider anchorProvider, IFigure feedbackLayer) {
        super();
        this.host = (GraphicalEditPart) host;
        this.hostFigure = hostFigure;
        this.anchorProvider = anchorProvider;
        this.feedbackLayer = feedbackLayer;
        
    }

    @objid ("251dd530-503d-4edb-be8c-7c202d73fed3")
    public void removeAllFeedbacks() {
        if (this.feedbacks != null) {
            for (IFigure f : this.feedbacks) {
                this.feedbackLayer.remove(f);
            }
        
            this.feedbacks = null;
        }
        
    }

    @objid ("a3fc6a34-8636-4a4c-8f27-2eb0266da163")
    public void showTargetFeedback(Request request) {
        ConnectionRouterId routingMode = RoutingModeGetter.fromRequest(request);
        Collection<ConnectionAnchor> allanchors = this.anchorProvider.getAnchorFactoryFor(this.host, this.hostFigure).getAllAnchors( routingMode, null);
        
        if (this.feedbacks != null && this.feedbacks.length != allanchors.size()) {
            removeAllFeedbacks();
        }
        
        if (this.feedbacks == null) {
            this.feedbacks = createFeedbackFigures(allanchors);
            for (IFigure f : this.feedbacks) {
                this.feedbackLayer.add(f);
            }
        }
        
        // compute highlight type
        final FigureUtilities2.HighlightType hightlightType;
        if(request.getType().equals(RequestConstants.REQ_RESIZE)) {
            hightlightType = FigureUtilities2.HighlightType.INFO;
        } else {
            final Command c = this.host.getCommand(request);
            if (c == null) {
                hightlightType = FigureUtilities2.HighlightType.ERROR;
            } else if (c.canExecute()) {
                hightlightType = FigureUtilities2.HighlightType.SUCCESS;
            } else {
                hightlightType = FigureUtilities2.HighlightType.WARNING;
            }
        }
        
        int i=0;
        for (ConnectionAnchor anchor : allanchors) {
            IFigure fb = this.feedbacks[i++];
        
            FigureUtilities2.updateHighlightType(fb, hightlightType);
            if (true) {
                new TranslatedAnchorLocator(anchor).relocate(fb);
            } else {
                Point ref = anchor.getReferencePoint();
                Rectangle bounds = new Rectangle();
                bounds.setLocation(ref);
                bounds.expand(FixedAnchor.ANCHOR_RADIUS, FixedAnchor.ANCHOR_RADIUS);
        
                fb.translateToRelative(bounds);
                fb.setBounds(bounds);
                fb.validate();
            }
        }
        
    }

    /**
     * Create the hover feedback figures.
     * @param allanchors all anchors for which a feedback is wanted
     * @return the hover feedback figure.
     */
    @objid ("da85e144-053b-4ce0-8a19-50f838d0c38e")
    protected IFigure[] createFeedbackFigures(Collection<ConnectionAnchor> allanchors) {
        int size = allanchors.size();
        IFigure[] ret = new IFigure[size];
        int i=0;
        
        for (ConnectionAnchor anchor : allanchors) {
            if (false) {
                AnchorHandle fb;
                fb = new AnchorHandle(this.host, anchor);
                fb.setTransludent();
                ret[i++] = fb;
            } else {
                IFigure fb;
                fb = AnchorFigureFactory.createHandleFigure(anchor);
                fb.setOpaque(false);
                if (fb instanceof Shape) {
                    Shape shape = (Shape) fb;
                    shape.setFill(false);
                    shape.setAlpha(150);
                    shape.setAntialias(1);
                }
                ret[i++] = fb;
            }
        
        }
        return ret;
    }

}
