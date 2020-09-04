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

package org.modelio.diagram.editor.activity.editor;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.plugin.DiagramEditorActivity;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.utils.i18n.BundledMessages;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for activity diagram.
 */
@objid ("41a3bb53-847b-4014-834f-6fc49ee55246")
public class ActivityCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("200e7109-3406-4040-822b-8373d4809bc9")
    private BundledMessages I18N = new BundledMessages(DiagramEditorActivity.LOG, ResourceBundle.getBundle("diagram-create-popups"));

    @objid ("143344ac-d88d-4ff1-8b75-8e38d9976f15")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorActivity.getContext().getBundle();
    }

    @objid ("955bad2c-7395-4ef3-9b23-beea08f13f26")
    @Override
    protected BundledMessages getI18nBundle() {
        return this.I18N;
    }

}
