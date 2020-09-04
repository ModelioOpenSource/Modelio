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

package org.modelio.core.rcp.system;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.help.IContext;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.help.IWorkbenchHelpSystem;

@objid ("2d347207-2937-4fda-a95d-c9c90802861c")
public class ModelioHelpSystem implements IWorkbenchHelpSystem {
    @objid ("f619e639-d990-47ee-93c1-7e760e104b40")
    private static ModelioHelpSystem instance;

    @objid ("491b84cd-3d4d-4264-a5b4-c22dffd7b47e")
    private ModelioHelpUi ui = null;

    @objid ("12ac8dc1-77f3-4ecb-9971-7bf904450124")
    private ModelioHelpSystem() {
    }

    /**
     * Return the singleton instance of this class.
     * 
     * @return the singleton instance
     */
    @objid ("22b609c9-a02c-4d7d-a0fe-3f32d4678232")
    public static ModelioHelpSystem getInstance() {
        if (ModelioHelpSystem.instance == null) {
            ModelioHelpSystem.instance = new ModelioHelpSystem();
        }
        return ModelioHelpSystem.instance;
    }

    @objid ("308f25d6-cac6-4aa1-b342-e5f303ee3be6")
    private ModelioHelpUi getUi() {
        if (this.ui == null) {
            this.ui = new ModelioHelpUi();
        }
        return this.ui;
    }

    @objid ("ada614c3-e7b8-476d-8c08-30e87f292040")
    @Override
    public boolean hasHelpUI() {
        return getUi() != null;
    }

    /**
     * Displays the entire help bookshelf.
     * <p>
     * Ignored if no help UI is available.
     * </p>
     * @see org.eclipse.ui.help.IWorkbenchHelpSystem#displayHelp()
     */
    @objid ("035b1a76-45e8-4562-8216-bb966572f3c6")
    @Override
    public void displayHelp() {
        if (getUi() != null) {
            this.ui.displayHelp();
        }
    }

    @objid ("1ca488c7-d1dc-44e5-8e28-c89137a5323d")
    @Override
    public void displaySearch() {
        throw new UnsupportedOperationException();
    }

    @objid ("c7ad5886-5966-420e-9c20-0272a22e3b90")
    @Override
    public void displayDynamicHelp() {
        throw new UnsupportedOperationException();
    }

    @objid ("c97e0707-8cc6-4ca7-ad67-4cf6127da4ca")
    @Override
    public void search(String expression) {
        throw new UnsupportedOperationException();
    }

    @objid ("207af100-c77f-44cb-b7a7-0bd7fbb08269")
    @Override
    public void displayContext(IContext context, int x, int y) {
        throw new UnsupportedOperationException();
    }

    @objid ("bd65675c-1722-4978-849c-faa7315acfc5")
    @Override
    public void displayHelpResource(String href) {
        if (href == null) {
            throw new IllegalArgumentException();
        }
        ModelioHelpUi helpUi = getUi();
        if (helpUi != null) {
            helpUi.displayHelpResource(href);
        }
    }

    @objid ("a84cad00-00ef-4e7e-91b7-3d5f5394c68d")
    @Override
    public void displayHelp(String contextId) {
        throw new UnsupportedOperationException();
    }

    @objid ("be92c829-942c-4fe5-8a30-daa794b2c9eb")
    @Override
    public void displayHelp(IContext context) {
        throw new UnsupportedOperationException();
    }

    @objid ("8ab7e333-4866-40a2-8f21-706b12dbb1aa")
    @Override
    public boolean isContextHelpDisplayed() {
        throw new UnsupportedOperationException();
    }

    @objid ("651bd167-3dd8-48ce-8a00-a1ad0838075a")
    @Override
    public void setHelp(IAction action, String contextId) {
        throw new UnsupportedOperationException();
    }

    @objid ("273659f6-097f-4ffa-95bd-c9ef4d0e4883")
    @Override
    public void setHelp(Control control, String contextId) {
        throw new UnsupportedOperationException();
    }

    @objid ("eaa6714e-9f79-4c98-a9a3-55660da4fcbe")
    @Override
    public void setHelp(Menu menu, String contextId) {
        throw new UnsupportedOperationException();
    }

    @objid ("cebb53f7-f3a7-4e7c-b415-ac20248e2cdd")
    @Override
    public void setHelp(MenuItem item, String contextId) {
        throw new UnsupportedOperationException();
    }

    @objid ("e624f065-0576-464d-b8ef-65afcf50b6b3")
    @Override
    public URL resolve(String href, boolean documentOnly) {
        return getUi().resolve(href, documentOnly);
    }

}
