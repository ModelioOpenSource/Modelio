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
package org.modelio.uml.objectdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.uml.objectdiagram.editor.plugin.DiagramEditorObject;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for Object diagram.
 */
@objid ("53c00849-f38d-473b-b628-4cb15a40c9b5")
public class ObjectCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("54ac3189-e4c9-467d-86b0-42e2eccd7471")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorObject.getContext().getBundle();
    }

    @objid ("43c432e1-c5fe-47ef-bb04-13a06a48cbc3")
    @Override
    protected BundledMessages getI18nBundle() {
        return DiagramEditorObject.I18N;
    }

}
