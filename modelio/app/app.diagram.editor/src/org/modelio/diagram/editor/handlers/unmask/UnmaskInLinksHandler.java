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
package org.modelio.diagram.editor.handlers.unmask;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.editor.handlers.unmask.ui.LinkedElementData;
import org.modelio.diagram.editor.handlers.unmask.ui.UnmaskLinkSelectionDialog;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("12d182d4-04b1-456e-89cb-f9ee24b237aa")
public class UnmaskInLinksHandler extends AbstractUnmaskHandler {
    @objid ("6fd5aacf-bf07-4d59-b9c3-8d567c52495e")
    @Override
    protected void unmask(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> secondarySelection, ICoreSession session) {
        try (ITransaction t = session.getTransactionSupport().createTransaction("UnmaskNonStructuringLinksAndNodes")) {
            GmModel primaryGm = (GmModel) primarySelection.getModel();
        
            List<MObject> existingsLinks = getExistingsLinks(primaryGm);
            List<LinkedElementData> outputLink =  getInLinks(primaryGm.getRelatedElement(),existingsLinks);
            if(outputLink.size() != 0) {
                UnmaskLinkSelectionDialog dialog = new UnmaskLinkSelectionDialog(Display.getCurrent().getActiveShell());
                dialog.setInput(outputLink);
                if(dialog.open() == IDialogConstants.OK_ID) {
                    List<MObject> links = new ArrayList<>();
                    for(LinkedElementData data : outputLink) {
                        if(data.getIsSelected()) {
                            links.add(data.getLink());
                        }
        
                    }
                    unmaskManager.unmaskLinks(primarySelection.getViewer(), primaryGm, links,false);
                }
            }
        
        
            t.commit();
        } catch (Exception e) {
            DiagramEditor.LOG.error(e);
        }
        
    }

    @objid ("28a70a3a-ac0f-40a0-ab1d-5469a99b0185")
    private List<MObject> getExistingsLinks(GmModel primaryGm) {
        List<MObject> result = new ArrayList<MObject>();
        for(IGmLinkObject link : primaryGm.getDiagram().getAllLinks()) {
            if(link instanceof IGmLink) {
                result.add(((IGmLink)link).getRelatedElement());
            }
        }
        return result;
    }

    @objid ("01d85f82-aa53-418c-a466-fa11206b4b6f")
    private List<LinkedElementData> getInLinks(MObject elt, List<MObject> existingsLinks) {
        List<LinkedElementData> inLinks = new ArrayList<>();
        for (MClass mc : initLinkMetaclasses(elt)) {
            // For all the meta model SmDepndencies that can play the role of a source for 'mc typed' links
            for (MDependency dep : mc.getLinkMetaclassTargets()) {
                // If the meta class targeted by the SmDep is compatible with 'element', the link type is qualified for further investigation
                if (elt.getMClass().hasBase(dep.getTarget())) {
                    for (MObject link : elt.mGet(dep.getSymetric())) {
                        // If the link instance passes the configuration filter
                        if (link.getMClass().equals(mc) && ! existingsLinks.contains(link)) {
                            for (MDependency target :mc.getLinkMetaclassSources()) {
                                for(MObject targetedElement : link.mGet(target)) {
                                    LinkedElementData linkedElement = new LinkedElementData(link,targetedElement);
                                    inLinks.add(linkedElement);
                                }
                            }
                        }
                    }
                }
            }
        }
        return inLinks;
    }

    @objid ("3a55ab56-3b4f-43af-bddd-2c5cfb8ab430")
    private List<MClass> initLinkMetaclasses(MObject element) {
        List<MClass> linkMetaclasses = new ArrayList<>();
        for (MClass mc : element.getMClass().getMetamodel().getRegisteredMClasses()) {
            if (mc.isLinkMetaclass()) {
                linkMetaclasses.add(mc);
            }
        }
        return linkMetaclasses;
    }

}
