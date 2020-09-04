/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.ui.handlers;

import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.ui.di.AboutToHide;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.modelio.app.ui.plugin.AppUi;
import org.osgi.framework.Bundle;

@objid ("d0d26955-0b26-4904-b81f-6979c1fc9525")
public class ThemeMenuHandler {
    @objid ("f9e5e08d-2a79-4fa8-94e8-bc7a85a0eaa4")
    private static final String THEME_EXTENSION_ID = "org.eclipse.e4.ui.css.swt.theme";

    @objid ("d4ce0b32-a007-466f-b2b3-601f4e4cc0c0")
    private static final String SWITCH_THEME_COMMAND_ID = "org.modelio.app.ui.command.switchtheme";

    @objid ("082ef29b-e75b-4d1f-aa97-12bd8f026949")
    private static final String SWITCH_THEME_PARAMETER_ID = "org.modelio.app.ui.command.switchtheme.parameter.themeId";

    @objid ("e0b47e2c-aeaf-4ad4-a11f-53d9f6427768")
    @Inject
     MApplication application;

    @objid ("b92962d3-79b2-4c2f-aba5-1f0511415afb")
    private MCommand switchCommand;

    @objid ("679a1b75-08bb-41e9-a3c8-c04222982a41")
    @AboutToShow
    public void aboutToShow(List<MMenuElement> items) {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] config = registry.getConfigurationElementsFor(THEME_EXTENSION_ID);
        
        for (IConfigurationElement e : config) {
            if (e.getName().equals("theme")) {
                processTheme(items, e);
            }
        }
    }

    @objid ("e5287bf1-dbbd-46c3-9b49-f6ee6b41e516")
    private void processTheme(List<MMenuElement> menuItems, IConfigurationElement elt) {
        String themeLabel = elt.getAttribute("label");
        String themeId = elt.getAttribute("id");
        String themeOs = elt.getAttribute("os");
        
        AppUi.LOG.debug("ThemeMenuHandler: found theme = '%s' %s (os=%s)", themeLabel, themeId, themeOs);
        
        if (themeOs == null || Platform.getOS().equals(themeOs)) {
        
            // create a new handled item
            MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
            item.setCommand(getSwitchThemeCommand());
        
            // make the item visible
            item.setEnabled(true);
            item.setToBeRendered(true);
            item.setVisible(true);
        
            // compute and set the element id
            final String itemId = themeId + ".item";
            item.setElementId(itemId);
        
            item.setLabel(themeLabel);
        
            // add the theme id parameter
            MParameter p = MCommandsFactory.INSTANCE.createParameter();
            p.setName(SWITCH_THEME_PARAMETER_ID);
            p.setValue(themeId);
            item.getParameters().add(p);
        
            // bind the item to the contributing plugin
            item.setContributorURI(getContributorId(AppUi.getContext().getBundle()));
        
            // add the item to menu
            menuItems.add(item);
        }
    }

    @objid ("ce438965-0ec2-4ceb-a6ed-109aa2b30f60")
    private String getContributorId(Bundle bundle) {
        return "platform:/plugin/" + bundle.getSymbolicName();
    }

    @objid ("3aa86b81-1689-4200-8c23-d51817225c2c")
    private MCommand getSwitchThemeCommand() {
        // Try the cache first...
        if (this.switchCommand == null) {
            // Not in the cache, look into the application commands
            for (MCommand c : this.application.getCommands()) {
                if (SWITCH_THEME_COMMAND_ID.equals(c.getElementId())) {
                    // Match, keep it in the cache...
                    this.switchCommand = c;
                    break;
                }
            }
        }
        return this.switchCommand;
    }

    @objid ("28a02c2c-14ad-4a05-858d-c6006185cecc")
    @AboutToHide
    public void aboutToHide(List<MMenuElement> items) {
        // TODO Your code goes here
    }

}
