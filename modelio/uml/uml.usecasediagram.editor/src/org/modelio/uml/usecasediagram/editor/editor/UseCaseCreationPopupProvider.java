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

package org.modelio.uml.usecasediagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.uml.usecasediagram.editor.plugin.DiagramEditorUseCase;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for use case diagram.
 */
@objid ("ede4b758-33d6-490a-98f0-10d3366c8832")
public class UseCaseCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("0d8d37ea-f22a-4d9d-80a4-7c0c680ddf87")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorUseCase.getContext().getBundle();
    }

    @objid ("72c3fe99-7751-46b2-88af-27de12462961")
    @Override
    protected BundledMessages getI18nBundle() {
        return DiagramEditorUseCase.I18N;
    }

}
