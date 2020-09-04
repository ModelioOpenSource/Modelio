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

package org.modelio.diagram.editor.statik.editor;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.diagram.editor.statik.elements.namespacinglink.CompositionLinkEditPart;
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for Static diagram.
 */
@objid ("6419d6ce-ebaf-408c-8e5d-9ff76ed48d24")
public class StaticCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("a603bece-cb43-442f-931f-006205c7e8eb")
    private BundledMessages I18N = new BundledMessages(DiagramEditorStatik.LOG, ResourceBundle.getBundle("diagram-create-popups"));

    @objid ("0a0cf943-594b-4382-9e1a-2c58fadee616")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorStatik.getContext().getBundle();
    }

    @objid ("84a2b4b5-112b-4cd6-aab6-cfedc91737ba")
    @Override
    protected BundledMessages getI18nBundle() {
        return this.I18N;
    }

    @objid ("4ff24341-2616-4ca1-8cb6-f8c93637b94d")
    @Override
    protected MObject getSelectedElement() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several times...
        IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
        if (selection == null || selection.size() != 1) {
            return null;
        }
        
        final Object obj = selection.getFirstElement();
        if (obj instanceof CompositionLinkEditPart) {
            return null;
        }
        return super.getSelectedElement();
    }

}
