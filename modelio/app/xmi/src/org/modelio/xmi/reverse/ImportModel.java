/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.xmi.reverse;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.xmi.gui.ProgressBarComposite;
import org.modelio.xmi.model.ecore.IEElement;
import org.modelio.xmi.plugin.Xmi;

@objid ("84a8a797-3c11-4e05-b8c1-66fe64583225")
public class ImportModel {
    @objid ("0680e182-25dc-4a8f-a394-ef84c20a2b88")
    private boolean external = false;

    @objid ("9d4d273b-e8a0-4e55-8b01-56e5381c72ff")
    private ProgressBarComposite progressBar = null;

    @objid ("22f33c3d-09db-49cc-964f-9e1b1dbfcc63")
    public void importXMI(IEElement ieelem) {
        PartialImportMap partialCreationMap = PartialImportMap.getInstance();
        TotalImportMap totalCreationMap = TotalImportMap.getInstance();
        boolean isNamespace = false;
        
        org.eclipse.uml2.uml.Element ecoreElt = ieelem.getEcoreElement();
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.Namespace) {
            isNamespace = true;
        }
        
        Object objingElt = totalCreationMap.get(ecoreElt);
        
        if (objingElt == null) {
            objingElt = partialCreationMap.get(ecoreElt);
        
            if (objingElt == null &&
                    isMapperOfCurrentElt(getCurrentClassName(ieelem), ieelem.getEcoreElement())) {
                // Creation:
                objingElt = ieelem.createObjingElt();
        
                if (objingElt != null) {
                    partialCreationMap.put(ecoreElt, objingElt);
                }
        
                if (isNamespace && this.progressBar != null) {
                    this.progressBar.addValue();
                    this.progressBar.addElement();
                }
            }
        
            if (objingElt != null) {
        
                // Finish creation:
                setMapping(objingElt, ieelem);
        
                partialCreationMap.remove(ecoreElt);
                totalCreationMap.put(ecoreElt, objingElt);
                if (this.progressBar != null) {
                    this.progressBar.addElement();
                    if (isNamespace) {
                        this.progressBar.addValue();
                    }
                }
        
            }
        }
    }

    @objid ("f0dee57d-50d6-4ed4-82ec-92fa258bd8ec")
    public ImportModel(ProgressBarComposite progressBar) {
        this.progressBar = progressBar;
    }

    @objid ("1b89b0f9-ed18-4fe5-870a-f67296819766")
    private void setMapping(Object objingElt, IEElement ieelem) {
        try {
            if (objingElt instanceof Element) {
                setEltMapping((Element)objingElt, ieelem);
            } else if (objingElt instanceof List<?>) {
                for (Object elt : (List<?>) objingElt) {
                    if (elt instanceof Element) {
                        setEltMapping((Element) elt, ieelem);
                    }
                }
            }
        } catch (RuntimeException e) {
            Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
        }
    }

    @objid ("639792cb-a7ba-406c-9bd5-f04b88af727b")
    private String getCurrentClassName(IEElement ieelem) {
        String name = ieelem.getClass().getSimpleName();
        return name.substring(1, name.length());
    }

    @objid ("03dacf2f-a1dd-45f8-a565-122237e57f35")
    public ImportModel() {
    }

    @objid ("2ba13eb0-048f-4946-912f-d6526f401aa5")
    private boolean isMapperOfCurrentElt(String mapperName, org.eclipse.uml2.uml.Element ecoreElement) {
        return (ecoreElement.getClass().getSimpleName().contains(mapperName));
    }

    @objid ("0426c7f5-aea8-48a2-a12d-e930c65761d0")
    private void setEltMapping(Element objingElt, IEElement ieelem) {
        if  (objingElt.getStatus().isModifiable()) {
        
            if (this.external) {
                if (objingElt instanceof ModelTree) {
                    ((ModelTree) objingElt).setOwner(ReverseProperties.getInstance()
                            .getExternalPackage());
                    this.external = false;
                }
            } else {
                ieelem.attach(objingElt);
            }
        
            ieelem.setProperties(objingElt);
            ieelem.setStereotypes();
        }
    }

}
