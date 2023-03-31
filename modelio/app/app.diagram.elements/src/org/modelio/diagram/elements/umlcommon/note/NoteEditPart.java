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
package org.modelio.diagram.elements.umlcommon.note;

import java.beans.PropertyChangeEvent;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.swt.widgets.Control;
import org.modelio.diagram.elements.common.edition.DirectEditManager2;
import org.modelio.diagram.elements.common.edition.EditorLocatorForLabelFigure;
import org.modelio.diagram.elements.common.edition.HtmlTextCellEditor;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeEndReconnectEditPolicy;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultElementDirectEditPolicy;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Edit part for {@link GmNote}.
 */
@objid ("818b23fe-1dec-11e2-8cad-001ec947c8cc")
public class NoteEditPart extends AbstractNodeEditPart {
    @objid ("d4c65ef5-6cab-426f-8af6-c68fc8cd99d0")
    static final Dimension HTML_EDITOR_MIN_SIZE = new Dimension(400, 300);

    @objid ("bb115440-1bea-4048-b107-64077e91ab91")
    private NoteFigureMoveListener connectionFigureListener;

    @objid ("818b2400-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performRequest(Request req) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            GmNote gmNote = (GmNote) getModel();
            IEditableText editableText = gmNote.getEditableText();
            if (editableText == null) {
                return;
            }
            final NoteFigure noteFigure = getNoteFigure();
            final Figure label = noteFigure.getContentFigure();
        
            if (gmNote.isHtml()) {
                EditorLocatorForLabelFigure cellEditorLocator = new EditorLocatorForLabelFigure(
                        label,
                        (String s) -> noteFigure.setContents(s, true)).setMinSize(NoteEditPart.HTML_EDITOR_MIN_SIZE);
        
                HtmlTextEditManager manager = new HtmlTextEditManager(this, cellEditorLocator);
                manager.show();
            } else {
                final CellEditorLocator cellEditorLocator = new EditorLocatorForLabelFigure(
                        label,
                        (String s) -> noteFigure.setContents(s, false));
        
                TextDirectEditManager manager = new TextDirectEditManager(
                        this,
                        cellEditorLocator,
                        HAlign.Left,
                        editableText.getText())
                                .setMultiline(true)
                                .setWrap(true);
        
                manager.show();
            }
        
        } else {
            super.performRequest(req);
        }
        
    }

    @objid ("818d861e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        } else if (evt.getPropertyName().equals(IGmObject.PROPERTY_LINK_TARGET)) {
            super.propertyChange(evt);
        
            // This property change event may be used to signal that the link to the annoted element is missing.
            Object newValue = evt.getNewValue();
            if (newValue instanceof ModelElement) {
                createMissingLink((ModelElement) newValue);
            }
        } else {
            super.propertyChange(evt);
        }
        
    }

    @objid ("818d8622-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DefaultElementDirectEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy("notelink", new LinkedNodeEndReconnectEditPolicy());
        
    }

    @objid ("818d8625-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        NoteFigure figure1 = new NoteFigure(!getModelStyle().getBoolean(GmNoteStyleKeys.UMLREPR));
        
        // Set style independent properties
        // figure1.setSize(100, 50);
        figure1.setOpaque(true);
        
        // Set style dependent properties
        refreshFromStyle(figure1, getModelStyle());
        
        // Install connection figure listener (if possible, for new notes links may not bet available yet)
        List<IGmLink> endingLinks = getModel().getEndingLinks();
        if (!endingLinks.isEmpty()) {
            installConnectionFigureListener(endingLinks.get(0));
        }
        
        // Return the created figure
        return figure1;
    }

    @objid ("dab2c3f9-778d-4683-b238-7664df4ad647")
    private void installConnectionFigureListener(IGmLink gmNoteLink) {
        if (this.connectionFigureListener == null) {
            LinkEditPart noteLinkEditPart = (LinkEditPart) NoteEditPart.this.getViewer().getEditPartRegistry().get(gmNoteLink);
            if (noteLinkEditPart != null) {
                Connection noteLinkconnectionFigure = noteLinkEditPart.getConnectionFigure();
                this.connectionFigureListener = new NoteFigureMoveListener(this);
                noteLinkconnectionFigure.addFigureListener(this.connectionFigureListener);
            }
        }
        
    }

    /**
     * Get the note figure.
     * @return The note figure.
     */
    @objid ("818d862c-1dec-11e2-8cad-001ec947c8cc")
    protected final NoteFigure getNoteFigure() {
        return (NoteFigure) getFigure();
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("818d8631-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final NoteFigure noteFigure = (NoteFigure) getFigure();
        final GmNote noteModel = (GmNote) getModel();
        
        // Install the connection figure lmove listener if not already done
        List<IGmLink> endingLinks = getModel().getEndingLinks();
        if (this.connectionFigureListener == null && !endingLinks.isEmpty()) {
            installConnectionFigureListener(endingLinks.get(0));
        }
        
        // Set the layout constraint
        noteFigure.getParent().setConstraint(noteFigure, noteModel.getLayoutData());
        
        // Set the note contents
        String contents = noteModel.getContents();
        noteFigure.setContents(contents, noteModel.isHtml());
        noteFigure.setType(noteModel.getType());
        
    }

    /**
     * Recreate the link between the {@link Note} and its annoted element through a reconnect request.
     * @param source the annoted element.
     */
    @objid ("c2e9147a-5562-4179-a9de-1c46e889df02")
    private void createMissingLink(final ModelElement source) {
        IGmLinkable gmTarget = getModel();
        GmNoteLink gmLink = new GmNoteLink(gmTarget.getDiagram(), new MRef(gmTarget.getRelatedElement()));
        gmTarget.addEndingLink(gmLink);
        
        // Build a reconnect request
        CreateConnectionRequest request = new CreateConnectionRequest();
        request.setType(RequestConstants.REQ_CONNECTION_END);
        request.setSourceEditPart(this);
        request.setLocation(new Point(0, 0));
        
        ModelioLinkCreationContext context = new ModelioLinkCreationContext(gmTarget.getRelatedElement());
        request.setFactory(context);
        
        DefaultCreateLinkCommand startCommand = new DefaultCreateLinkCommand(context);
        startCommand.setTarget(gmTarget);
        request.setStartCommand(startCommand);
        
        // Search all gm representing the new source
        for (GmModel gmSource : gmTarget.getDiagram().getAllGMRelatedTo(new MRef(source))) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) this.getViewer().getEditPartRegistry().get(gmSource);
            if (editPart != null) {
                // Make sure this edit part accepts the reconnect request
                EditPart sourceEditPart = editPart.getTargetEditPart(request);
                if (sourceEditPart != null) {
                    // found a valid target: add the link to it!
                    IGmLinkable sourceModel = (IGmLinkable) sourceEditPart.getModel();
                    sourceModel.addStartingLink(gmLink);
                    return;
                }
            }
        }
        
        // Unable to find a valid source, delete link
        gmLink.delete();
        
    }

    @objid ("3c357d27-8cb5-430b-8a1a-016b8d3ada2b")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        NoteFigure noteFigure = (NoteFigure) aFigure;
        
        if (style.getBoolean(GmNoteStyleKeys.UMLREPR)) {
            if (noteFigure.isLightRepresentation()) {
                noteFigure.setLightRepresentation(false);
            }
        } else {
            if (!noteFigure.isLightRepresentation()) {
                noteFigure.setLightRepresentation(true);
            }
        }
        super.refreshFromStyle(aFigure, style);
        
    }

    @objid ("4e087a9c-3234-43e5-ac5b-92e071bdb8b1")
    private static final class HtmlTextEditManager extends DirectEditManager2 {
        @objid ("04c5a293-ae68-43aa-ac4d-fe9e24c9b12b")
         HtmlTextEditManager(GraphicalEditPart source, CellEditorLocator locator) {
            super(source, HtmlTextCellEditor.class, locator);
        }

        @objid ("06ea1ec0-23fe-41d8-951d-c4da048e1f27")
        @Override
        protected void initCellEditor() {
            final HtmlTextCellEditor textEdit = (HtmlTextCellEditor) getCellEditor();
            textEdit.setValue(((GmModel) getEditPart().getModel()).getEditableText().getText());
            
            final Control textControl = textEdit.getControl();
            textEdit.performSelectAll();
            
            textControl.setBackground(ColorConstants.white);
            textControl.setForeground(ColorConstants.blue);
            textControl.setFont(((NoteFigure) getEditPart().getFigure()).getTextFont());
            
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
    @objid ("3644a01c-ce1e-43b2-9998-c4f0d7b44595")
    private static class NoteFigureMoveListener implements FigureListener {
        @objid ("240cfc44-ce55-4336-831b-7d4f01d583c3")
        private NoteEditPart noteEditPart;

        @objid ("31df964a-755e-4b36-95ff-441e99950b32")
        private Rectangle lastBounds = new Rectangle();

        @objid ("78dd4458-b30e-460a-945e-20469226b2e8")
        public  NoteFigureMoveListener(NoteEditPart noteEditPart) {
            this.noteEditPart = noteEditPart;
        }

        @objid ("32529145-afec-4649-b5a9-b2678b3ce3d4")
        @Override
        public void figureMoved(IFigure source) {
            Connection noteLinkconnectionFigure = (Connection) source;
            Rectangle connectionFigureBounds = source.getBounds();
            if (!this.lastBounds.equals(connectionFigureBounds)) {
                // Fix the link anchoring orientation
                NoteFigure noteFigure = (NoteFigure) this.noteEditPart.getFigure();
                Direction direction = GeomUtils.getDirection(noteLinkconnectionFigure.getPoints().getFirstPoint(), noteFigure.getBounds());
                noteFigure.setAnchoringBorderPosition(direction);
                this.lastBounds.setBounds(connectionFigureBounds);
            }
            
        }

    }

}
