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

package org.modelio.diagram.elements.common.simple;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.CellEditorLocator;
import org.modelio.diagram.elements.common.edition.EditorLocatorForLabelFigure;
import org.modelio.diagram.elements.common.edition.TextDirectEditManager;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDirectEditPolicy;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Universal editpart for "simple" mode of potentially any ModelElement It provides a simple rounded rectangle figure with a centered label
 */
@objid ("7f1f4061-1dec-11e2-8cad-001ec947c8cc")
public class RoundedSimpleEditPart extends AbstractNodeEditPart {
    /**
     * @see AbstractNodeEditPart#propertyChange(java.beans.PropertyChangeEvent)
     */
    @objid ("7f1f4063-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        /*
         * | The model change listener method. | This method is called when the model fires a property change event | Typical code consists in testing the PropertyChangeEvent type and | process the proper code. | For IGmObject.PROPERTY_LINK,
         * IGmObject.PROPERTY_CHILDREN, | IGmObject.PROPERTY_LAYOUTDATA and IGmObject.PROPERTY_STYLE event types the super class behaviour | should suffice. | However, if the "GmModel" managed by the edit part fires other types of events, | theses types
         * should be processed here. | Typical code fragment: | if (evt.getPropertyName().equals( "My Special Event Type") { | ...my special processing code... | } else super.propertyChange(evt); |
         */
        super.propertyChange(evt);
    }

    /**
     * Creates the Figure to be used as this part's visuals
     */
    @objid ("7f21a2b3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        /*
         * | Create (new allocated object) the figure to be used as this part's visuals. | A fake implementation, a RectangleFigure, is coded below. | You are supposed to follow the proposed pattern which clearly distinguishes | between the graphic
         * properties that are controlled by the style and those which are | hard-coded
         */
        
        // create the figure
        final RoundedSimpleFigure aFigure = new RoundedSimpleFigure();
        
        // set style independent properties
        aFigure.setSize(100, 50); // TODO: remove/change fake code
        
        // set style dependent properties
        refreshFromStyle(aFigure, getModelStyle());
        
        // return the figure
        return aFigure;
    }

    /**
     * Refresh the figure from the given style.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * @param figure The figure to update, should be {@link #getFigure()}.
     * 
     * @param style The style to update from, usually {
     * @link #getModelStyle()}
     */
    @objid ("7f21a2bb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            /*
             * | In this method you have to fetch relevant property values from the style | and apply them to the managed figure. Both the figure and the style to use | are passed as parameters, so you are not expected to get them directly from 'this' |
             * This is especially important as the refreshFromStyle method is called in createFigure() | possibly during EditPart initialisation, assuming nothing from 'this' is probably safer | | Typical code: | | MyFigure figure = (MyFigure) aFigure; |
             * figure.setForegroundColor(style.getColor(StyleKey.????.TEXTCOLOR)); | figure.setFont(style.getFont(StyleKey.????.FONT));
             */
            super.refreshFromStyle(aFigure, style);
            // SimpleFigure figure = (SimpleFigure)aFigure;
        }
    }

    @objid ("7f21a2c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DefaultElementDirectEditPolicy());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("7f21a2c7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        final GmNodeModel model = getModel();
        final RoundedSimpleFigure aFigure = (RoundedSimpleFigure) getFigure();
        
        aFigure.getParent().setConstraint(aFigure, model.getLayoutData());
        
        // Ugly we have to go to the Ob level ..
        final MObject e = model.getRelatedElement();
        if (e instanceof ModelElement) {
            aFigure.setLabel(((ModelElement) e).getName());
        } else {
            aFigure.setLabel(e.toString());
        }
    }

    /**
     * SimpleEditPart has no model children.
     */
    @objid ("7f21a2cb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<Object> getModelChildren() {
        // Could also return only the GmModelElementHeader.
        return Collections.emptyList();
    }

    @objid ("7f21a2d3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
            final SimpleFigure aFigure = (SimpleFigure) getFigure();
            final Label label = aFigure.getLabelFigure();
            final IEditableText editableText = ((GmModel) getModel()).getEditableText();
            if (editableText != null) {
                final CellEditorLocator cellEditorLocator = new EditorLocatorForLabelFigure(
                        label,
                        (String s) -> label.setText(s));
        
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

}
