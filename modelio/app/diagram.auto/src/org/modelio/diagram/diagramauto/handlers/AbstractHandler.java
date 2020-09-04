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

package org.modelio.diagram.diagramauto.handlers;

import java.net.URL;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.osgi.framework.Bundle;

@objid ("93e073db-aaef-41c6-bbd6-38f28ead203c")
public abstract class AbstractHandler extends AbstractDiagramWizardContributor {
    @objid ("dae3661a-37d3-49d7-95e0-08ae80508540")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("29889dc4-99cb-476c-ac36-ed9e41fca02a")
    private static Image diagramIcon;

    @objid ("08e2d2f2-c3c3-4ef1-849f-a562ac169398")
    protected MMetamodel getMetamodel() {
        return this.mmServices.getMetamodel();
    }

    @objid ("2aa67694-30d0-4d7f-9dfb-8eab38ada236")
    @Override
    public final Image getIcon() {
        if (AbstractHandler.diagramIcon == null) {
            String iconPath = getIconPath();
            if (iconPath != null) {
                Bundle bundle = DiagramAuto.context.getBundle();
                URL imageUrl = FileLocator.find(bundle, new Path(iconPath), null);
                AbstractHandler.diagramIcon = ImageDescriptor.createFromURL(imageUrl).createImage();
            }
        }
        return AbstractHandler.diagramIcon;
    }

    @objid ("bff0033d-67e1-42b9-ae15-df7968e0b679")
    @Override
    public final ImageDescriptor getPreviewImage() {
        String imagePath = getPreviewImagePath();
        if (imagePath != null) {
            Bundle bundle = DiagramAuto.context.getBundle();
            URL imageUrl = FileLocator.find(bundle, new Path(imagePath), null);
            return ImageDescriptor.createFromURL(imageUrl);
        }
        return null;
    }

    @objid ("b8b3434d-11e3-49f6-b7cb-2be80fc2c894")
    protected abstract String getPreviewImagePath();

    @objid ("20972865-920f-481f-8c13-32356f1304e3")
    protected abstract String getIconPath();

    @objid ("3e199e4c-4e81-498a-8134-de0f77da1b0e")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, ClassDiagram.MQNAME);
    }

}
