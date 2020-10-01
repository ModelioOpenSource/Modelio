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

package org.modelio.diagram.editor.corexp;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.umlcommon.diagramview.DiagramViewEditPart;
import org.modelio.diagram.elements.umlcommon.namespaceuse.GmNamespaceUse;

/**
 * Core Expression property tester for diagram nodes and links.
 * Operates on selection.
 * <p/>
 * Supported properties are:
 * <ul>
 * <li>'is-gmnsu' - true is all selected elements are ImpactLinks</li>
 * <li>'is-gmlink' - true is all selected element are GmLinks</li>
 * <li>'is-gmnode' - true is all selected element are GmNodes</li>
 * <lI>'is-gmdiagramview' - true is all selected element are GmDiagramHolders</li>
 * </ul>
 * @author phv
 */
@objid ("b97ed541-218b-43cd-be96-e23d6e03e2ad")
public class GmTester extends PropertyTester {
    @objid ("adcab4aa-fbed-460f-83bf-67a2bd8f5b44")
    private static final String IS_GMNSU = "is-gmnsu";

    @objid ("da41871e-effd-4040-9087-8ebc0181d91b")
    private static final String IS_GMNODE = "is-gmnode";

    @objid ("0a2d728f-7642-4ae2-9ba0-ad9652708aac")
    private static final String IS_GMLINK = "is-gmlink";

    @objid ("71eb1e10-e0a3-480d-8816-1e8882f5c586")
    private static final String IS_GMDIAGRAMVIEW = "is-gmdiagramview";

    @objid ("8247ab73-74c2-458d-a9a9-843e5ca2cf49")
    public GmTester() {
        // nothing
    }

    @objid ("ac960e48-45f4-4b56-8f72-58cb7fc13194")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof IStructuredSelection)) {
            return false;
        }
        
        final IStructuredSelection selection = (IStructuredSelection) receiver;
        Object[] elements = selection.toArray();
        switch (property) {
        case IS_GMLINK:
            // true is all selected element are GmLinks
            for (Object element : elements) {
                if (!(element instanceof LinkEditPart)) {
                    return false;
                }
            }
            return true;
        case IS_GMNODE:
            // true is all selected element are GmNodes
            for (Object element : elements) {
                if (!(element instanceof AbstractNodeEditPart) || (element instanceof AbstractDiagramEditPart)) {
                    return false;
                }
            }
            return true;
        case IS_GMNSU:
            // true is all selected elements are ImpactLinks
            for (Object element : elements) {
                if (!isNsu(element)) {
                    return false;
                }
            }
            return true;
        case IS_GMDIAGRAMVIEW:
            // true is all selected element are GmDiagramViews
            for (Object element : elements) {
                if (!(element instanceof DiagramViewEditPart)) {
                    return false;
                }
            }
            return true;
        default:
            throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
    }

    @objid ("823cdaaf-cdb0-4bfb-a9ae-6d4efc923db1")
    private boolean isNsu(Object element) {
        return (element instanceof LinkEditPart) && (((LinkEditPart) element).getModel() instanceof GmNamespaceUse);
    }

}
