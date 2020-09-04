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

package org.modelio.xmi.generation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.gui.ProgressBarComposite;
import org.modelio.xmi.model.objing.IOElement;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ScopeChecker;

/**
 * This class manages the export sequence of each element.
 * i.e. creation if necessary, attachement, and properties setting
 * @author ebrosse
 */
@objid ("c6e5e52a-e057-4b11-b18c-0adcc4b1c87a")
public class ExportModel {
    @objid ("5040a845-f32c-42a7-9db2-8dc0bed53a81")
    private ProgressBarComposite progressBar = null;

    @objid ("d901cafc-dd97-42ca-a279-7f41589ece9a")
    private ScopeChecker scopeChecker = new ScopeChecker(GenerationProperties
            .getInstance().getRootElements());

    /**
     * A constructor with a given progress bar
     * 
     * @param progressBar : the given progress bar
     */
    @objid ("2a876bbb-84e9-4f01-af2b-707ec30ea781")
    public ExportModel(ProgressBarComposite progressBar) {
        super();
        this.progressBar = progressBar;
        GenerationProperties.getInstance().setScopeChecker(this.scopeChecker);
    }

    /**
     * The default constructor
     */
    @objid ("233df95f-b62d-4197-86b6-1af38502efb3")
    public ExportModel() {
        super();
        this.progressBar = null;
    }

    /**
     * The export method is in charge of the export sequence for a given IOElement
     * 
     * @param ioelem : the IOElement to export
     */
    @objid ("a8f52dfc-a415-46e4-8049-14b04aa5bdb4")
    public void export(IOElement ioelem) {
        try {
            if ( this.scopeChecker.contains(ioelem.getObjingElement())) {
        
                org.eclipse.uml2.uml.Element ecoreElt = TotalExportMap.getInstance().get(ioelem.getObjingID());
        
                if (ecoreElt == null || isSuperCall(ioelem)) {
        
                    if (ecoreElt == null) {
                        ecoreElt =  PartialExportMap.getInstance().remove(ioelem.getObjingID());
                    } else { // Case of the mapping for the super class:
                        PartialExportMap.getInstance().remove(ioelem.getObjingID());
                        TotalExportMap.getInstance().remove(ioelem.getObjingID());
                    }
        
                    if (ecoreElt == null) {
                        // Creation:
                         ecoreElt = createElement(ioelem);
                    }
        
                    if (ecoreElt != null) {
                        PartialExportMap.getInstance().put(ioelem.getObjingID(), ecoreElt);
        
                        // Finish creation:
                        finishElementCreation(ecoreElt, ioelem);
        
                        PartialExportMap.getInstance().remove(ioelem.getObjingID());
                        TotalExportMap.getInstance().put(ioelem.getObjingID(), ecoreElt);
                    } else {
                        throw new NotFoundException("Element of type "
                                + ioelem.getObjingElement().getClass()
                                .getSimpleName()
                                + " has no mapping to EMF-UML2.");
                    }
                }
            }
        } catch (NotFoundException e) {
            Xmi.LOG.warning(Xmi.PLUGIN_ID, e);
        }
    }

    @objid ("9e1f9b7a-0542-4173-b2e3-a0f9cfce1779")
    private org.eclipse.uml2.uml.Element createElement(IOElement ioelem) {
        return ioelem.createEcoreElt();
    }

    @objid ("f621cb1f-b319-4df8-9ec7-db7768b5ba01")
    private void finishElementCreation(org.eclipse.uml2.uml.Element ecoreElt, IOElement ioelem) {
        ioelem.attach(ecoreElt);
        ioelem.setProperties(ecoreElt);
        
        if (this.progressBar != null){
            this.progressBar.addElement();
            if (ioelem.getObjingElement() instanceof NameSpace){
                this. progressBar.addValue();
            }
        }
    }

    @objid ("a98db02c-139d-4088-81ad-e5045eb0fade")
    private boolean isSuperCall(IOElement ioelem) {
        Element objingElt = ioelem.getObjingElement();
        
        String mapperName = ioelem.getClass().getSimpleName();
        String eltClassName = objingElt.getClass().getSimpleName();
        
        mapperName = mapperName.substring(1);
        eltClassName = eltClassName.substring(2);
        return (!(mapperName.equals(eltClassName)));
    }

}
