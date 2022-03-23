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

@objid ("57414ae2-7baa-448d-bacd-146492600793")
public class ShowFeatureVisibilityHandler {
    @objid ("6bb85fc3-2ac5-423d-87e9-9dc5ddb65548")
    private static final String SHOWFEATUREVISIBILITY_MENUITEMID = "org.modelio.model.browser.directmenuitem.featurevisibility";

    @objid ("4fdb8c9e-78ca-46ed-9d76-67a5a0f2bea8")
    @Execute
    public static final void execute(MPart part, EModelService s) {
        assert part.getObject() instanceof BrowserView : "Handler used on a part other than BrowserView!";
        for (final MMenu menu : part.getMenus()) {
            if (BrowserView.VIEWMENU_ID.equals(menu.getElementId())) {
                final MDirectMenuItem button = (MDirectMenuItem) s.find(ShowFeatureVisibilityHandler.SHOWFEATUREVISIBILITY_MENUITEMID, menu);
                if (button != null) {
                    final ModelBrowserPanelProvider panel = (ModelBrowserPanelProvider) ((BrowserView) part.getObject()).getContributedPanel();
                    ((UmlLabelProvider) panel.getLabelContentProvider(StandardMetamodel.NAME)).setShowFeatureVisibility(button.isSelected());
                    panel.refresh();
                }
            }
        }
        
    }

}
