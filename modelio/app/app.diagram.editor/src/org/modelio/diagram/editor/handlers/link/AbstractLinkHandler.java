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
package org.modelio.diagram.editor.handlers.link;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Base abstract class for link-related command handlers.
 */
@objid ("483adf51-fb5d-4199-9e3c-04934a26d94c")
public abstract class AbstractLinkHandler {
    /**
     * Makes sure the selection contains only non-nary editable links
     * @param selection the current diagram selection.
     * @return <code>true</code> if the handler can be executed.
     */
    @objid ("65c52af1-33f7-11e2-95fe-001ec947c8cc")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        final List<LinkEditPart> selectedLinks = SelectionHelper.toList(selection, LinkEditPart.class);
        // At least one link must be selected
        if (selectedLinks.isEmpty()) {
            return false;
        }
        
        for (final LinkEditPart linkEditpart : selectedLinks) {
            final GmLink link = linkEditpart.getModel();
            // Deactivate on read only diagram
            if (!link.isUserEditable()) {
                return false;
            }
        
            // Deactivate command on Naries...
            MObject relatedEl = link.getRelatedElement();
            if (relatedEl instanceof NaryAssociationEnd
                    || relatedEl instanceof NaryLinkEnd
                    || relatedEl instanceof ImpactLink
                    || relatedEl instanceof Constraint) {
                return false;
            }
        
            // Link must have a configurable router
            final StyleKey styleKey = link.getStyleKey(MetaKey.CONNECTIONROUTER);
            if (styleKey == null) {
                return false;
            }
        }
        return true;
    }

}
