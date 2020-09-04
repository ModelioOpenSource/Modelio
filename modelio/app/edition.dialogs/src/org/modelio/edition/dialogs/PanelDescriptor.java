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

package org.modelio.edition.dialogs;

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Panel descriptor.
 */
@objid ("8aac90e3-72e4-4617-a306-2e09864e19a3")
public class PanelDescriptor {
    @mdl.prop
    @objid ("09bf0ac8-4602-45d4-a8dd-0d0ffb75f479")
    private String relevance;

    @mdl.propgetter
    public String getRelevance() {
        // Automatically generated method. Please do not modify this code.
        return this.relevance;
    }

    @mdl.prop
    @objid ("7e08d4ac-67d4-488a-83f8-98dbe0921f58")
    private String id;

    @mdl.propgetter
    public String getId() {
        // Automatically generated method. Please do not modify this code.
        return this.id;
    }

    @mdl.prop
    @objid ("6a545d63-476b-4a68-a329-a1b4dcd7e792")
    private String panelLabel;

    @mdl.propgetter
    public String getPanelLabel() {
        // Automatically generated method. Please do not modify this code.
        return this.panelLabel;
    }

    @mdl.prop
    @objid ("32291d82-84e6-4118-8828-23346b3f26a1")
    private IPanelProvider panel;

    @mdl.propgetter
    public IPanelProvider getPanel() {
        // Automatically generated method. Please do not modify this code.
        return this.panel;
    }

    /**
     * @param origin the origin of the panel. for a module panel it is the module itself, for a plugin contribution this value is either a particular formalism (UML, Analysis,...) or null for secondary non-specific panels
     * @param isPrimary whether it is a primary panel: to be displayed first
     * 
     * @param id the panel id
     * @param label the panel label
     * @param panel the panel implementation
     */
    @objid ("1c3f0ae7-9b27-4536-a58b-d95c80d3fe77")
    public PanelDescriptor(String relevance, String id, String label, IPanelProvider panel) {
        this.relevance = relevance; 
        this.id = id;
        this.panelLabel = label;
        this.panel = panel;
    }

}
