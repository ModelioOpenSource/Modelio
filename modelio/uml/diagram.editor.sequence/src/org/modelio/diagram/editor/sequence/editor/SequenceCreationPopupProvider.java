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

package org.modelio.diagram.editor.sequence.editor;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.diagram.editor.sequence.plugin.DiagramEditorSequence;
import org.modelio.utils.i18n.BundledMessages;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for Sequence diagram.
 */
@objid ("12cad973-e2a0-4581-9e0b-cb0b0b9d81b6")
public class SequenceCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("358c8d06-d9aa-491a-a16e-78ebef43951a")
    private BundledMessages I18N = new BundledMessages(DiagramEditorSequence.LOG, ResourceBundle.getBundle("diagram-create-popups"));

    @objid ("ca458d11-c62e-454b-9f66-07de255c0842")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorSequence.getContext().getBundle();
    }

    @objid ("84b96ba5-8cfe-4745-8ca6-f77015a32242")
    @Override
    protected BundledMessages getI18nBundle() {
        return this.I18N;
    }

}
