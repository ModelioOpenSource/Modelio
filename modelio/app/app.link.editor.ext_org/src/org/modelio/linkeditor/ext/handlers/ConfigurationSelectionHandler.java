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
package org.modelio.linkeditor.ext.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectToolItem;
import org.modelio.linkeditor.ext.LinkEditorConfigurationParameters;
import org.modelio.linkeditor.ext.plugin.LinkEditorOrg;
import org.modelio.linkeditor.ext.view.LinkEditorView;
import org.modelio.linkeditor.panel.ILinkEditor;
import org.modelio.platform.project.services.IProjectService;

@objid ("e0744d3f-bb0f-499f-86d4-81a6b69d609e")
public class ConfigurationSelectionHandler {
    @objid ("2aca22cf-634c-45bf-96e3-37b18583cea5")
    public static final String INHERITANCE_TOOLID = "org.modelio.link.editor.ext_org.directtoolitem.inheritance";

    @objid ("0cde53e6-af32-48f2-bb61-3b1708192810")
    public static final String ASSOCIATIONS_TOOLID = "org.modelio.link.editor.ext_org.directtoolitem.associations";

    @objid ("3f116a87-db5f-4de0-aacd-01b8b445847e")
    public static final String DEPENDENCIES_TOOLID = "org.modelio.link.editor.ext_org.directtoolitem.dependencies";

    @objid ("aa8a13f9-b844-4b43-ae4e-0adf42dfa3e6")
    public static final String TRACES_TOOLID = "org.modelio.link.editor.ext_org.directtoolitem.traces";

    @objid ("51fc6f61-2e5c-4ff3-84c2-c67ee676d18d")
    public static final String FILTEREDDEPS_TOOLID = "org.modelio.link.editor.ext_org.directtoolitem.filtereddeps";

    @objid ("9c62a129-26e2-4800-89db-1f5826d6c13a")
    @Execute
    public void execute(MDirectToolItem item, MPart mPart) {
        LinkEditorView view = (LinkEditorView) mPart.getObject();
        ILinkEditor editor = view.getLinkEditor();
        
        LinkEditorConfigurationParameters parameters = LinkEditorConfigurationParameters.getInstance();
        
        switch (item.getElementId()) {
        case ASSOCIATIONS_TOOLID:
            parameters.setShowAssociations(item.isSelected());
            break;
        case DEPENDENCIES_TOOLID:
            parameters.setShowDeps(item.isSelected());
            break;
        case TRACES_TOOLID:
            parameters.setShowTraces(item.isSelected());
            break;
        case INHERITANCE_TOOLID:
            parameters.setShowInheritance(item.isSelected());
            break;
            case FILTEREDDEPS_TOOLID:
                parameters.setShowFiltereDeps(item.isSelected());
                break;
        default:
            LinkEditorOrg.LOG.error("Link Editor mode %s not supported", item.getElementId());
        }
        
        editor.getConfigurator().apply(parameters);
        
    }

    @objid ("d52dcede-7699-4a9c-a529-1e37f68d8af4")
    @CanExecute
    public boolean canExecute(IProjectService projectService) {
        return projectService.getOpenedProject() != null;
    }

}
