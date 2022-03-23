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
package org.modelio.creation.wizard.dialog.treeview;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.api.module.contributor.diagramcreation.IDiagramWizardContributor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ViewerSorter for the creation contributors list. The valid contributors will be displayed first and then the invalid
 * ones. And they are also sorted by names.
 */
@objid ("ab3402e9-c2e1-46ca-8428-fbe161dc6bb2")
public class ContributorSorter extends ViewerComparator {
    @objid ("301e3c46-3449-442d-8d4d-5a137126ff1b")
    private ModelElement context;

    @objid ("bb6a521e-570f-47bb-84b1-93cb916f75d9")
    public  ContributorSorter(ModelElement context) {
        this.context = context;
    }

    @objid ("95037c9b-170a-4830-b0de-18c5ba5eea95")
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        // Handle category first
        int cat1 = category(e1);
        int cat2 = category(e2);
        
        if (cat1 != cat2) {
            return cat1 - cat2;
        }
        
        // Category UML is always display as the first
        if (e1 instanceof Category && e2 instanceof Category) {
            return ((Category) e1).getLabel().compareToIgnoreCase(((Category) e2).getLabel());
        } else if (e1 instanceof IWizardContributor && e2 instanceof IWizardContributor) {
            IWizardContributor contributor1 = (IWizardContributor) e1;
            IWizardContributor contributor2 = (IWizardContributor) e2;
            // Valid contributors are always the first
            if (this.context != null) {
                if (contributor1.accept(this.context) && !contributor2.accept(this.context)) {
                    return -1;
                } else if (!contributor1.accept(this.context) && contributor2.accept(this.context)) {
                    return 1;
                }
            }
            boolean isStandardE1 = contributor1.getClass().getPackage().getName().equals("org.modelio.uml.statikdiagram.editor.contributor");
            boolean isStandardE2 = contributor2.getClass().getPackage().getName().equals("org.modelio.uml.statikdiagram.editor.contributor");
            if (isStandardE1 != isStandardE2) {
                // Contributors from the creation.wizard plugin should always be first
                return isStandardE1 ? -1 : 1;
            } else {
                // Sort by contributor names
                return contributor1.getLabel().compareToIgnoreCase(contributor2.getLabel());
            }
        }
        return 0;
    }

    @objid ("16fbdf77-5abe-4a13-88bf-20d333ffa27b")
    @Override
    public int category(Object element) {
        // Category UML is always display as the first
        if (element instanceof Category) {
            if (((Category) element).getLabel().startsWith("UML")) {
                return 0;
            } else {
                return 1;
            }
        } else if (element instanceof IWizardContributor) {
            IWizardContributor contributor = (IWizardContributor) element;
            if (contributor instanceof IDiagramWizardContributor) {
                return 2;
            } else {
                return 3;
            }
        }
        return 4;
    }

}
