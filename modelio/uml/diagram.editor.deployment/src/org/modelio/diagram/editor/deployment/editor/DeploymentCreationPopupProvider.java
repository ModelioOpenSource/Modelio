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

package org.modelio.diagram.editor.deployment.editor;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.diagram.editor.deployment.plugin.DiagramEditorDeployment;
import org.modelio.utils.i18n.BundledMessages;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for Deployment diagram.
 */
@objid ("4400b568-6fdd-48b9-afd3-90061ca1629e")
public class DeploymentCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("048b0085-38f2-4f26-957c-e0dcdcf2e101")
    private BundledMessages I18N = new BundledMessages(DiagramEditorDeployment.LOG, ResourceBundle.getBundle("diagram-create-popups"));

    @objid ("3a314684-0e29-4d43-a332-75b9f6deb350")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorDeployment.getContext().getBundle();
    }

    @objid ("31cf3c9d-f82c-496a-aeb5-d64c6140b3e2")
    @Override
    protected BundledMessages getI18nBundle() {
        return this.I18N;
    }

}
