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
package org.modelio.uml.deploymentdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.uml.deploymentdiagram.editor.plugin.DiagramEditorDeployment;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for Deployment diagram.
 */
@objid ("4400b568-6fdd-48b9-afd3-90061ca1629e")
public class DeploymentCreationPopupProvider extends AbstractCreationPopupProvider {
    @objid ("3a314684-0e29-4d43-a332-75b9f6deb350")
    @Override
    protected Bundle getBundle() {
        return DiagramEditorDeployment.getContext().getBundle();
    }

    @objid ("31cf3c9d-f82c-496a-aeb5-d64c6140b3e2")
    @Override
    protected BundledMessages getI18nBundle() {
        return DiagramEditorDeployment.I18N;
    }

}
