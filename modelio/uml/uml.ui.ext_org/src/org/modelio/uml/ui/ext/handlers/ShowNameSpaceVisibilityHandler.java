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

package org.modelio.uml.ui.ext.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.panel.ModelBrowserPanelProvider;
import org.modelio.uml.ui.browser.contrib.labelprovider.UmlLabelProvider;

@objid ("9c99874e-99bf-440d-bbe2-9a27b38dcd61")
public class ShowNameSpaceVisibilityHandler {
    @objid ("a1a31083-a070-4a69-9ec9-c001c48e4e19")
    private static final String SHOWNAMESPACEVISIBILITY_MENUITEMID = "org.modelio.model.browser.directmenuitem.namespacevisibility";

    @objid ("a1b3b5b1-27dd-482a-a9e5-d5df21ee4cce")
    @Execute
    public static final void execute(MPart part, EModelService s) {
        assert part.getObject() instanceof BrowserView : "Handler used on a part other than BrowserView!";
        for (final MMenu menu : part.getMenus()) {
            if (BrowserView.VIEWMENU_ID.equals(menu.getElementId())) {
                final MDirectMenuItem button = (MDirectMenuItem) s.find(ShowNameSpaceVisibilityHandler.SHOWNAMESPACEVISIBILITY_MENUITEMID, menu);
                if (button != null) {
                    final ModelBrowserPanelProvider panel = (ModelBrowserPanelProvider) ((BrowserView) part.getObject()).getContributedPanel();
                    ((UmlLabelProvider) panel.getLabelContentProvider(StandardMetamodel.NAME)).setShowNamespaceVisibility(button.isSelected());
                    panel.refresh();
                }
            }
        }
    }

}
