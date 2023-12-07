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
import java.util.stream.Collectors;
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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("74a91d07-c373-4351-b703-59db20cfe684")
public class UnmaskOutLinksHandler extends AbstractUnmaskHandler {
    @objid ("31d8cb59-3569-47ea-958d-a3558f2eaefb")
    @Override
    protected void unmask(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> secondarySelection, ICoreSession session) {
        try (ITransaction t = session.getTransactionSupport().createTransaction("UnmaskNonStructuringLinksAndNodes")) {
            GmModel primaryGm = (GmModel) primarySelection.getModel();
        
            List<MObject> existingsLinks  = getExistingsLinks(primaryGm);
            List<LinkedElementData> outputLink = getOutLinks(primaryGm.getRelatedElement(),existingsLinks);
            outputLink.addAll(getNaryLinks(primaryGm.getRelatedElement(),existingsLinks));
        
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
                    unmaskManager.unmaskLinks(primarySelection.getViewer(), primaryGm, links,true);
                }
            }
        
            t.commit();
        } catch (Exception e) {
            DiagramEditor.LOG.error(e);
        }
        
    }

    @objid ("68fe1c8a-b104-4183-9726-ad411eece018")
    private List<LinkedElementData> getNaryLinks(MObject selection, List<MObject> existingsLinks) {
        List<LinkedElementData> result = new ArrayList<>();
        if(selection instanceof Classifier) {
           for(NaryAssociationEnd end : ((Classifier)selection).getOwnedNaryEnd()) {
               if(!existingsLinks.contains(end.getNaryAssociation())) {
                   result.add(new LinkedElementData(end.getNaryAssociation(),end.getNaryAssociation().getNaryEnd().stream().map(elt -> elt.getOwner()).collect(Collectors.toList())));
               }
           }
        } else if(selection instanceof Instance) {
            for(NaryLinkEnd end : ((Instance)selection).getOwnedNaryEnd()) {
                if(!existingsLinks.contains(end.getNaryLink())) {
                    result.add(new LinkedElementData(end.getNaryLink(),end.getNaryLink().getNaryLinkEnd().stream().map(elt -> elt.getCompositionOwner()).collect(Collectors.toList())));
                }
            }
         }
        return result;
    }

    @objid ("28f457ff-0aea-4a3d-8660-22214ff5615c")
    private List<MObject> getExistingsLinks(GmModel primaryGm) {
        List<MObject> result = new ArrayList<MObject>();
        for(IGmLinkObject link : primaryGm.getDiagram().getAllLinks()) {
            if(link instanceof IGmLink) {
                result.add(((IGmLink)link).getRelatedElement());
            }
        }
        
        
        for(GmModel model : primaryGm.getDiagram().getAllModels()) {
            if(model.getRepresentedElement() instanceof NaryLink  || model.getRepresentedElement()  instanceof NaryAssociation) {
                result.add(model.getRepresentedElement());
            }
        }
        return result;
    }

    @objid ("283e1e74-1dad-4764-b7f3-a747f58fb2d9")
    private List<LinkedElementData> getOutLinks(MObject elt, List<MObject> existingsLinks) {
        List<LinkedElementData> outLinks = new ArrayList<>();
        for (MClass mc : initLinkMetaclasses(elt)) {
            // For all the meta model SmDepndencies that can play the role of a source for 'mc typed' links
            for (MDependency dep : mc.getLinkMetaclassSources()) {
                // If the meta class targeted by the SmDep is compatible with 'element', the link type is qualified for further investigation
                if (elt.getMClass().hasBase(dep.getTarget())) {
                    for (MObject link : elt.mGet(dep.getSymetric())) {
                        // If the link instance passes the configuration filter
                        if (link.getMClass().equals(mc) && !existingsLinks.contains(link)) {
                            for (MDependency target :mc.getLinkMetaclassTargets()) {
                                for(MObject targetedElement : link.mGet(target)) {
                                    LinkedElementData linkedElement = new LinkedElementData(link,targetedElement);
                                    outLinks.add(linkedElement);
                                }
                            }
                        }
                    }
                }
            }
        }
        return outLinks;
    }

    @objid ("a8a73368-772b-487c-9c54-948e77447e9d")
    private List<MObject> getInLinks(MObject elt) {
        List<MObject> inLinks = new ArrayList<>();
        for (MClass mc : initLinkMetaclasses(elt)) {
            // For all the meta model SmDepndencies that can play the role of a source for 'mc typed' links
            for (MDependency dep : mc.getLinkMetaclassSources()) {
                // If the meta class targeted by the SmDep is compatible with 'element', the link type is qualified for further investigation
                if (elt.getMClass().hasBase(dep.getTarget())) {
                    for (MObject link : elt.mGet(dep.getSymetric())) {
                        // If the link instance passes the configuration filter
                        if (link.getMClass().equals(mc)) {
                            inLinks.add(link);
                        }
                    }
                }
            }
        }
        return inLinks;
    }

    @objid ("d682e2c6-fc35-43ec-9c01-afb2f149397a")
    private List<MClass> initLinkMetaclasses(MObject element) {
        List<MClass> linkMetaclasses = new ArrayList<>();
        for (MClass mc : element.getMClass().getMetamodel().getRegisteredMClasses()) {
            if (mc.isLinkMetaclass() ||  mc.getName().equals("NaryLinkEnd")) {
                linkMetaclasses.add(mc);
            }
        }
        return linkMetaclasses;
    }

}
