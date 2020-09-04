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

package org.modelio.diagram.editor.bpmn.editor;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.utils.i18n.BundledMessages;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for Bpmn diagram.
 */
@objid ("dae0be76-2bdc-479a-b67e-225a612892dc")
public class BpmnCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("9df1118e-a3be-4b90-9f59-0f4cf59b299f")
    private BundledMessages I18N = new BundledMessages(DiagramEditorBpmn.LOG, ResourceBundle.getBundle("diagram-create-popups"));

    @objid ("1d3950e0-854a-42c7-9b1a-2595359b55ef")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorBpmn.getContext().getBundle();
    }

    @objid ("4bad6f98-fad5-4276-90db-71dabf68accc")
    @Override
    protected BundledMessages getI18nBundle() {
        return this.I18N;
    }

}
