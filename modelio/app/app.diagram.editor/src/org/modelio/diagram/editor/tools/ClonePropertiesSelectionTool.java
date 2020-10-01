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

package org.modelio.diagram.editor.tools;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.platform.ui.gef.SharedCursors2;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;

/**
 * This tool applies style properties of its refStyle on each clicked
 * GraphicalEditPart in a diagram. The ESC or SPACE keys aborts the tool. When
 * the CTRL key is NOT pressed when clicking all 'metakey-matching' properties
 * are set on the cliked editpart, otherwise only 'graphic' properties are
 * applied.
 */
@objid ("66a547fb-33f7-11e2-95fe-001ec947c8cc")
public class ClonePropertiesSelectionTool extends PanSelectionTool {
    @objid ("66a547fe-33f7-11e2-95fe-001ec947c8cc")
    private boolean fullMode = true;

    @objid ("66a547ff-33f7-11e2-95fe-001ec947c8cc")
    private static final MetaKey[] graphicProperties = { MetaKey.FILLCOLOR, MetaKey.FILLMODE, MetaKey.FONT, MetaKey.LINECOLOR,
			MetaKey.LINEPATTERN, MetaKey.LINEWIDTH, MetaKey.LINERADIUS, MetaKey.TEXTCOLOR };

    @objid ("e3aaa21e-8a56-4eb7-acbc-6d3f11a50411")
    private GraphicalEditPart refEditPart = null;

    /**
     * C'tor.
     * 
     * @param refEditPart the edit part used as reference.
     */
    @objid ("66a54802-33f7-11e2-95fe-001ec947c8cc")
    public ClonePropertiesSelectionTool(final GraphicalEditPart refEditPart) {
        this.refEditPart = refEditPart;
    }

    @objid ("66a54807-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected Cursor calculateCursor() {
        // compute the proper cursor
        if (getHovered() == null) {
            return Cursors.NO;
        } else {
            return this.fullMode ? SharedCursors2.CURSOR_CLONE_ALL_OPTIONS : SharedCursors2.CURSOR_CLONE_GRAPHIC_OPTIONS;
        }
    }

    @objid ("66a5480b-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleButtonDown(final int which) {
        super.handleButtonDown(which);
        List<?> selectedEditParts = getCurrentViewer().getSelectedEditParts();
        if (selectedEditParts == null || selectedEditParts.isEmpty()) {
            return true;
        }
        GraphicalEditPart target = (GraphicalEditPart) selectedEditParts.get(0);
        if (target == null) {
            return false;
        }
        if (target.equals(this.refEditPart)) {
            return false;
        }
        
        IGmObject gmObject = (IGmObject) target.getModel();
        if (! gmObject.isUserEditable()) {
            return false;
        }
        
        final IGmDiagram diagram = gmObject.getDiagram();
        final ITransactionSupport session = diagram.getModelManager().getModelingSession().getTransactionSupport();
        
        try (ITransaction transaction = session.createTransaction("Clone "+target+" properties")) {
            // model processing code (may throw an unplanned exception...)
            if (this.fullMode) {
                cloneAllProperties(target);
            } else {
                cloneGraphicProperties(target);
            }
            
            // Save diagram
            diagram.save(false);
            
            // end of processing code , commit the transaction
            transaction.commit();
        }
        return true;
    }

    @objid ("66a54812-33f7-11e2-95fe-001ec947c8cc")
    private void cloneGraphicProperties(final GraphicalEditPart target) {
        final IGmObject refGm = (IGmObject) this.refEditPart.getModel();
        final IStyle refStyle = refGm.getPersistedStyle();
        final IGmObject targetGm = (IGmObject) target.getModel();
        final IStyle targetStyle = targetGm.getPersistedStyle();
        
        // copy 'graphic' StyleKey values from source to target
        for (final MetaKey mk : graphicProperties) {
            if (refGm.getStyleKey(mk) != null && targetGm.getStyleKey(mk) != null) {
                targetStyle.setProperty(targetGm.getStyleKey(mk), refStyle.getProperty(refGm.getStyleKey(mk)));
            }
        }
        targetStyle.normalize();
    }

    @objid ("66a54816-33f7-11e2-95fe-001ec947c8cc")
    private void cloneAllProperties(final GraphicalEditPart target) {
        final IGmObject refGm = (IGmObject) this.refEditPart.getModel();
        final IStyle refStyle = refGm.getPersistedStyle();
        final IGmObject targetGm = (IGmObject) target.getModel();
        final IStyle targetStyle = targetGm.getPersistedStyle();
        
        for (final StyleKey sk : refGm.getStyleKeys()) {
        
            if (targetGm.getStyleKeys().contains(sk)) {
                // direct match
                targetStyle.setProperty(sk, refStyle.getProperty(sk));
            } else {
                // try a metakey-matching
                final MetaKey metaKey = sk.getMetakey();
                if (metaKey != null && targetGm.getStyleKey(metaKey) != null) {
                    targetStyle.setProperty(targetGm.getStyleKey(metaKey), refStyle.getProperty(sk));
                }
            }
        
        }
        targetStyle.normalize();
    }

    /**
     * Handle KeyDown events for some particular keycodes:
     * <ul>
     * <li>ESC or SPACE : abort the tool</li>
     * <li>CTRL : switch the tool in 'full' mode</li>
     * </ul>
     * @see org.eclipse.gef.tools.SelectionTool#handleKeyDown(org.eclipse.swt.events.KeyEvent)
     */
    @objid ("66a5481a-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleKeyDown(final KeyEvent e) {
        if (e.keyCode == SWT.CTRL) {
            this.fullMode = false;
            refreshCursor();
        }
        
        if (e.keyCode == SWT.ESC || e.keyCode == ' ') {
            getDomain().setActiveTool(getDomain().getDefaultTool());
        }
        return super.handleKeyDown(e);
    }

    /**
     * Handle KeyUp events for some particular keycodes:
     * <ul>
     * <li>ESC or SPACE : abort the tool</li>
     * <li>CTRL : switch the tool in 'graphic' mode</li>
     * </ul>
     * @see org.eclipse.gef.tools.SelectionTool#handleKeyUp(org.eclipse.swt.events.KeyEvent)
     */
    @objid ("66a54822-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleKeyUp(final KeyEvent e) {
        if (e.keyCode == SWT.CTRL) {
            this.fullMode = true;
            refreshCursor();
        }
        
        if (e.keyCode == SWT.ESC || e.keyCode == ' ') {
            abort();
        }
        return super.handleKeyDown(e);
    }

    @objid ("66a7aa59-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleMove() {
        setCursor(calculateCursor());
        return super.handleMove();
    }

    /**
     * Returns the GraphicalEditPart being hovered by the mouse however
     * excluding the root edit part.
     * 
     * @return the hovered GraphicalEditPart or null
     */
    @objid ("66a7aa5e-33f7-11e2-95fe-001ec947c8cc")
    private GraphicalEditPart getHovered() {
        final GraphicalEditPart ep = (GraphicalEditPart) getTargetEditPart();
        
        
        if (ep == null || ep instanceof RootEditPart) {
            return null;
        } else if (((IGmObject) ep.getModel()).isUserEditable()) {
            return ep;
        } else {
            return null;
        }
    }

    @objid ("66a7aa63-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleFocusLost() {
        abort();
        return super.handleFocusLost();
    }

    @objid ("66a7aa68-33f7-11e2-95fe-001ec947c8cc")
    private void abort() {
        getDomain().setActiveTool(getDomain().getDefaultTool());
    }

    @objid ("66a7aa6a-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleViewerExited() {
        abort();
        return super.handleViewerExited();
    }

    @objid ("66a7aa6f-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void mouseDown(final MouseEvent e, final EditPartViewer viewer) {
        super.mouseDown(e, viewer);
        
        // Set state 'in progress' to avoid edition
        int oldState = getState();
        setState(STATE_DRAG_IN_PROGRESS);
        
        super.mouseUp(e, viewer);
        
        // Reset state
        setState(oldState);
        
        super.mouseDown(e, viewer);
    }

}
