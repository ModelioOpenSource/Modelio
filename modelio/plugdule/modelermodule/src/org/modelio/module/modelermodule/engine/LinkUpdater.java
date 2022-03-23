/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.module.modelermodule.engine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.i18n.I18nMessageService;

/**
 * Service class managing model transformations and synchronizations between Link and Associations.
 */
@objid ("bba2e30b-3e06-46e8-8cc6-835dd1df7732")
public class LinkUpdater {
    /**
     * Update a link from its base association.
     * @param current the link to update.
     * @return <code>true</code> if the link has been modified.
     * @throws ModelerModuleException when an error occurs during the update.
     */
    @objid ("8373b99f-4318-43a0-8bae-55b9c7aa7d2a")
    public boolean updateLinkFromAssociation(final Link current) throws ModelerModuleException {
        Association model = current.getModel();
        if (model != null) {
            updateLinkFromAssociation(current, model);
        } else {
            throw new ModelerModuleException(I18nMessageService.getString("module.error.updateLinkFromAssociation.model", current.getName()));
        }
        return true;
    }

    @objid ("3c3939c9-f438-4458-9b50-abb6840c808f")
    private void updateLinkFromAssociation(Link current, Association model) throws ModelerModuleException {
        current.setName(model.getName());
        
        for (final LinkEnd le : current.getLinkEnd()) {
            final AssociationEnd endModel = le.getModel();
            if (endModel != null) {
                if (model.equals(endModel.getAssociation())) {
                    // Matching ends
                    updateLinkEndFromAssociationEnd(le, endModel);
                    continue;
                }
            }
        
            // Invalid model, try finding the right one
            for (final AssociationEnd ae : model.getEnd()) {
                if (le.getName().equals(ae.getName())) {
                    updateLinkEndFromAssociationEnd(le, ae);
                    continue;
                }
            }
        
            // No matching end found
            throw new ModelerModuleException(I18nMessageService.getString("module.error.updateLinkFromAssociation.end", le.getName()));
        }
        
    }

    /**
     * Update link end properties.
     */
    @objid ("8bc5cfd5-33af-4342-b36d-e9edfb8c565d")
    private void updateLinkEndFromAssociationEnd(LinkEnd current, AssociationEnd model) {
        current.setModel(model);
        current.setName(model.getName());
        current.setIsOrdered(model.isIsOrdered());
        current.setIsUnique(model.isIsUnique());
        current.setNavigable(model.isNavigable());
        
    }

}
