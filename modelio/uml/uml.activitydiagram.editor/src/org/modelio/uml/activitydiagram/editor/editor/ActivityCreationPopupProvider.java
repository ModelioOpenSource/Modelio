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
package org.modelio.uml.activitydiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.uml.activitydiagram.editor.plugin.DiagramEditorActivity;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for activity diagram.
 */
@objid ("41a3bb53-847b-4014-834f-6fc49ee55246")
public class ActivityCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("143344ac-d88d-4ff1-8b75-8e38d9976f15")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorActivity.getContext().getBundle();
    }

    @objid ("955bad2c-7395-4ef3-9b23-beea08f13f26")
    @Override
    protected BundledMessages getI18nBundle() {
        return DiagramEditorActivity.I18N;
    }

}
