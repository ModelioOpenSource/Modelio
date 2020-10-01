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

package org.modelio.diagram.editor.handlers.unmask;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

@objid ("665dc1b5-33f7-11e2-95fe-001ec947c8cc")
public class UnmaskNonStructuringLinksHandler extends AbstractUnmaskHandler {
    @objid ("665dc1b6-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void unmask(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> secondarySelection, ICoreSession session) {
        try (ITransaction t = session.getTransactionSupport().createTransaction("UnmaskNonStructuringLinksAndNodes")) {
            GmModel primaryGm = (GmModel) primarySelection.getModel();
            unmaskManager.unmaskNonStructuringLinks(primarySelection.getViewer(), primaryGm, false);
        
            for (GraphicalEditPart secondaryEditPart : secondarySelection) {
                GmModel secondaryGm = (GmModel) secondaryEditPart.getModel();
                unmaskManager.unmaskNonStructuringLinks(secondaryEditPart.getViewer(), secondaryGm, false);
            }
        
            t.commit();
        } catch (Exception e) {
            DiagramEditor.LOG.error(e);
        }
    }

}
