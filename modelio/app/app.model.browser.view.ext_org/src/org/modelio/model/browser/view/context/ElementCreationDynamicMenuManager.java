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

package org.modelio.model.browser.view.context;

import javax.inject.Singleton;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.modelio.model.browser.view.plugin.ModelBrowserExtOrg;
import org.modelio.platform.model.ui.popupmenu.AbstractPopupProvider;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.osgi.framework.Bundle;

/**
 * Dynamically populates the creation menu with standard creation items.
 */
@objid ("0053e972-7a19-1006-9c1d-001ec947cd2a")
@Creatable
@Singleton
public class ElementCreationDynamicMenuManager extends AbstractPopupProvider {
    @objid ("ab8c9667-ca87-4c26-b536-bac63fd1d67a")
    @Override
    protected Bundle getBundle() {
        return ModelBrowserExtOrg.getContext().getBundle();
    }

    @objid ("0fb2d051-fe36-4f5c-a5ca-2542969c656e")
    @Override
    protected BundledMessages getI18nBundle() {
        return ModelBrowserExtOrg.I18N;
    }

    @objid ("2e072f36-ddfb-4e01-86aa-75f86e731c9b")
    @Override
    protected IPath getXmlPath() {
        return new Path("/res/create-popups.xml");
    }

    @objid ("a8bb0911-9fdd-4734-9593-c07897dd64cc")
    @Override
    protected String getMenuIconPath() {
        return "platform:/plugin/org.modelio.app.model.browser.view/icons/createuml.png";
    }

    @objid ("8d0b1629-62ea-4359-9d22-c5329de0a5ff")
    @Override
    protected String getMenuLabel() {
        return ModelBrowserExtOrg.I18N.getString("CreateElementMenu.label");
    }

}
