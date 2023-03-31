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
package org.modelio.diagram.elements.drawings.note;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.swt.widgets.Control;
import org.modelio.diagram.elements.common.edition.DirectEditManager2;
import org.modelio.diagram.elements.common.edition.EditorLocatorForLabelFigure;
import org.modelio.diagram.elements.common.edition.HtmlTextCellEditor;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.node.GmNodeDrawing;
import org.modelio.diagram.elements.drawings.core.node.NodeDrawingEditPart;

/**
 * Edit part for {@link GmNoteDrawing}.
 */
@objid ("f9343265-338c-4bc3-a0ca-eb6f38965a0f")
public class NoteDrawingEditPart extends NodeDrawingEditPart {
    @objid ("de0e8c80-3fae-47f6-8031-9bc097e7806e")
    static final Dimension HTML_EDITOR_MIN_SIZE = new Dimension(400, 300);

    @objid ("2b5ea5c7-6b4f-4583-9b48-71048d59cc98")
    @Override
    public void performRequest(Request req) {
        if (RequestConstants.REQ_OPEN.equals(req.getType()) || RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            final NoteDrawingFigure NoteDrawingFigure = getNoteDrawingFigure();
            final Figure label = NoteDrawingFigure.getContentFigure();
            EditorLocatorForLabelFigure cellEditorLocator = new EditorLocatorForLabelFigure(
                    label,
                    (String s) -> NoteDrawingFigure.setContents(s)).setMinSize(NoteDrawingEditPart.HTML_EDITOR_MIN_SIZE);
            HtmlTextEditManager manager = new HtmlTextEditManager(this, cellEditorLocator);
            manager.show();
        } else {
            super.performRequest(req);
        }
        
    }

    @objid ("609f9894-27d2-4807-b709-72b66c54cb79")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        } else if (evt.getPropertyName().equals(IGmObject.PROPERTY_LINK_TARGET)) {
            super.propertyChange(evt);
        } else {
            super.propertyChange(evt);
        }
        
    }

    @objid ("d9170c96-0032-4c04-9e28-fade5e26eccb")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NoteDrawingDirectEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new NoteDrawingDirectEditPolicy());
        
    }

    @objid ("98c88758-1b70-4e19-a70b-8b194ed8f0e0")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        NoteDrawingFigure figure1 = new NoteDrawingFigure();
        
        // Set style independent properties
        // figure1.setSize(100, 50);
        figure1.setOpaque(true);
        
        // Set style dependent properties
        refreshFromStyle(figure1, getModelStyle());
        // Return the created figure
        return figure1;
    }

    /**
     * Get the note figure.
     * @return The note figure.
     */
    @objid ("2daaa177-1fdb-4bb5-a9cb-650571c96bea")
    protected final NoteDrawingFigure getNoteDrawingFigure() {
        return (NoteDrawingFigure) getFigure();
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("d7bf602e-6d21-45a4-bb36-974fcb7af3ca")
    @Override
    protected void refreshVisuals() {
        final NoteDrawingFigure NoteDrawingFigure = (NoteDrawingFigure) getFigure();
        final GmNoteDrawing noteModel = (GmNoteDrawing) getModel();
        
        // Set the layout constraint
        NoteDrawingFigure.getParent().setConstraint(NoteDrawingFigure, noteModel.getLayoutData());
        
        // Set the note contents
        String label = noteModel.getLabel();
        NoteDrawingFigure.setContents(label);
        
    }

    @objid ("85956e2c-e32a-40de-8923-de88c18fa5e6")
    @Override
    public GmNoteDrawing getModel() {
        return (GmNoteDrawing) super.getModel();
    }

    @objid ("e94c0ad6-4811-42e1-b510-a34d3a4cf721")
    private static final class HtmlTextEditManager extends DirectEditManager2 {
        @objid ("3bf7044d-84bf-4c8c-9c18-f0ece7d55330")
         HtmlTextEditManager(GraphicalEditPart source, CellEditorLocator locator) {
            super(source, HtmlTextCellEditor.class, locator);
        }

        @objid ("8654b043-21eb-45fe-a23b-38b985f3da3c")
        @Override
        protected void initCellEditor() {
            final HtmlTextCellEditor textEdit = (HtmlTextCellEditor) getCellEditor();
            textEdit.setValue(((GmNodeDrawing) getEditPart().getModel()).getLabel());
            
            final Control textControl = textEdit.getControl();
            textEdit.performSelectAll();
            
            textControl.setBackground(ColorConstants.white);
            textControl.setForeground(ColorConstants.blue);
            textControl.setFont(((NoteDrawingFigure) getEditPart().getFigure()).getTextFont());
            
            super.initCellEditor();
            
        }

    }

    /**
     * This FigureListener tracks the "moves" of the ConnectionFigure.
     * <p>
     * When such moves occur a new "anchoring position" of the note link must be computed and the note figure has to adapt its representation accordingly (gradient direction, 'bracket-like' border shape).
     * </p>
     * <p>
     * Unfortunately, whenever the diagram is "dragged" (middle click + move) extra figure move are fired without the connection being really modified. Therefore, as an optimization, the class implements a cache of the last connection figure bounds which
     * is compared to the figure bounds when the move occurs, only when these values differ, the anchoring is re-computed.
     * </p>
     */
    @objid ("ada1aefb-5f64-4a61-acb1-011d15c664c9")
    private static class NoteDrawingFigureMoveListener implements FigureListener {
        @objid ("7eb586c2-481e-463b-a2f3-acda6ee1cbf1")
        private NoteDrawingEditPart NoteDrawingEditPart;

        @objid ("33af401b-f41b-4e9e-b825-93ebe746d230")
        private Rectangle lastBounds = new Rectangle();

        @objid ("3d7fd8f0-e42d-4c9c-8683-8a92bf2d5cb0")
        public  NoteDrawingFigureMoveListener(NoteDrawingEditPart NoteDrawingEditPart) {
            this.NoteDrawingEditPart = NoteDrawingEditPart;
        }

        @objid ("98eef465-61c6-4b7c-8f5e-93930254c43d")
        @Override
        public void figureMoved(IFigure source) {
            Connection noteLinkconnectionFigure = (Connection) source;
            Rectangle connectionFigureBounds = source.getBounds();
            if (!this.lastBounds.equals(connectionFigureBounds)) {
                // Fix the link anchoring orientation
                NoteDrawingFigure NoteDrawingFigure = (NoteDrawingFigure) this.NoteDrawingEditPart.getFigure();
                Direction direction = GeomUtils.getDirection(noteLinkconnectionFigure.getPoints().getFirstPoint(), NoteDrawingFigure.getBounds());
                NoteDrawingFigure.setAnchoringBorderPosition(direction);
                this.lastBounds.setBounds(connectionFigureBounds);
            }
            
        }

    }

}
