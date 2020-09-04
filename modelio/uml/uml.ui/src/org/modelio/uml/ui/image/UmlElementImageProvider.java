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

package org.modelio.uml.ui.image;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.spi.IElementImageProvider;
import org.modelio.core.ui.swt.images.spi.IMetamodelImageProvider;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.ui.swt.QualifiedImage;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default image provider with UML elements for the model browser.
 */
@objid ("5b3b66b1-00b1-44e6-8857-4282b2e234f4")
public class UmlElementImageProvider implements IElementImageProvider, IMetamodelImageProvider {
    @objid ("b6c75d94-48ba-45fa-a5f8-db920d330bfc")
    private static final String IMAGES_PATH = "mmimages/";

    @objid ("3d2600b8-2421-41c1-a66a-20a6464e4b0c")
    private static final String IMAGES_EXTENSION = ".png";

    @objid ("8858991f-33d4-4c96-88c2-09ff24ec3e0b")
    private static final ImageRegistry REGISTRY = new ImageRegistry();

    @objid ("3eb625d6-54cb-4e9d-ba0f-09e2542a2670")
    @Override
    public QualifiedImage getIcon(MObject element) {
        return getIcon(element.getMClass(), getFlavor(element));
    }

    /**
     * Get the icon for a metaclass and a flavor
     * 
     * @param metaclass a metaclass
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the found icon or a default unknown icon.
     */
    @objid ("499e2b3f-0685-4ec5-87b4-7b20026cc3b4")
    @Override
    public QualifiedImage getIcon(MClass metaclass, String flavor) {
        // since 3.8.1 ALWAYS get metaclass icons by a qualified name
        return getImageFromKey(getIconKey(metaclass, flavor));
    }

    @objid ("9e8d022a-fbd4-4b9a-8a2a-051dbc513725")
    @Override
    public QualifiedImage getImage(MObject element) {
        return getImage(element.getMClass(), getFlavor(element));
    }

    /**
     * Get the diagram big image for a metaclass and a flavor.
     * 
     * @param metaclass a metaclass
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the found image or null.
     */
    @objid ("ae1a496b-7e69-486f-b0b7-de0cac43e534")
    @Override
    public QualifiedImage getImage(MClass metaclass, String flavor) {
        final String key = getImageKey(metaclass, flavor, metaclass.getOrigin().isExtension());
        return getImageFromKey(key);
    }

    /**
     * @return a flavor to concatenate to the lookup key. Might be <code>null</code>.
     */
    @objid ("e2a879bd-93b6-414a-9be8-9f7759531ec3")
    private String getFlavor(MObject element) {
        return (String) element.accept(new ImageFlavorBuilder());
    }

    @objid ("6e26d999-ae11-4c44-9880-6238cb1c7557")
    private String getImageKey(MClass metaclass, String flavor, boolean qualified) {
        return getIconKey(metaclass, flavor) + ".image";
    }

    @objid ("e6ec5264-f1f9-4ffa-be4d-89513ec67181")
    private String getIconKey(MClass metaclass, String flavor) {
        final String className = metaclass.getQualifiedName();
        if (flavor == null || flavor.isEmpty()) {
            return className;
        } else {
            final StringBuilder keyBuffer = new StringBuilder(className.length() + flavor.length() + 1);
            keyBuffer.append(className);
            keyBuffer.append(".");
            keyBuffer.append(flavor);
            return keyBuffer.toString();
        }
    }

    @objid ("2b705211-be00-433d-98c1-07060a3a67c5")
    private Image loadImage(String key) {
        final IPath imagePath = new Path(getIconPath(key));
        URL url = FileLocator.find(UmlUi.getContext().getBundle(), imagePath, null);
        if (url != null) {
            ImageDescriptor desc = ImageDescriptor.createFromURL(url);
            return desc.createImage();
        }
        return null;
    }

    /**
     * Get the icon for a metaclass.
     * 
     * @param metaclassName a metaclass name.
     * @return the metaclass icon.
     */
    @objid ("e0dbd9e3-5b3d-4da6-b978-2d766434b222")
    @Override
    public QualifiedImage getIcon(final String metaclassName) {
        return getImageFromKey(metaclassName);
    }

    /**
     * Get the image for a given key.
     * <p>
     * The image is loaded into the registry if not yet done.
     * @return
     */
    @objid ("e8ad69b4-cd30-4060-b0ca-fffb42ff1721")
    private QualifiedImage getImageFromKey(final String key) {
        Image image = UmlElementImageProvider.REGISTRY.get(key);
        
        if (image == null) {
            image = loadImage(key);
            if (image != null) {
                UmlElementImageProvider.REGISTRY.put(key, image);
            } else {
                return null;
            }
        }
        // Image is not null
        return new QualifiedImage(image, key);
    }

    /**
     * Get the relative file name from the current plugin.
     */
    @objid ("50605b4f-1f92-4ada-8173-1e63944984a4")
    private String getIconPath(String key) {
        final StringBuilder path = new StringBuilder(UmlElementImageProvider.IMAGES_PATH);
        path.append(key.toLowerCase());
        path.append(UmlElementImageProvider.IMAGES_EXTENSION);
        return path.toString();
    }

    @objid ("905fde52-d6d8-4697-9fd5-e449c7665525")
    @Override
    public String getIconCompletePath(String metaclassName) {
        String iconPath = getIconPath(metaclassName);
        final IPath imagePath = new Path(iconPath);
        URL url = FileLocator.find(UmlUi.getContext().getBundle(), imagePath, null);
        if (url != null) {
            StringBuilder ret = new StringBuilder();
            ret.append("platform:/plugin/");
            ret.append(UmlUi.PLUGIN_ID);
            ret.append("/");
            ret.append(iconPath);
            return ret.toString();
        } else {
            return null;
        }
    }

    @objid ("6f641c17-7a12-4566-bd06-cbe9a092581b")
    @Override
    public String getIconCompletePath(MClass metaclass) {
        final String key = getIconKey(metaclass, null);
        return getIconCompletePath(key);
    }

    /**
     * Visitor that computes a flavor to concatenate to the lookup key for a given {@link UmlModelElement}.
     */
    @objid ("8bfed586-1770-11e2-aa0d-002564c97630")
    protected static class ImageFlavorBuilder extends DefaultModelVisitor {
        @objid ("8c00d169-1770-11e2-aa0d-002564c97630")
        @Override
        public Object visitParameter(Parameter theParameter) {
            if (theParameter.getReturned() != null) {
                return "return";
            } else if (theParameter.getComposed() != null) {
                return "io";
            } else {
                return null;
            }
        }

        @objid ("8c00f877-1770-11e2-aa0d-002564c97630")
        @Override
        public Object visitBehaviorParameter(BehaviorParameter theBehaviorParameter) {
            final Parameter theParameter = theBehaviorParameter.getMapped();
            if (theParameter != null) {
                return visitParameter(theParameter);
            } else {
                return super.visitUmlModelElement(theBehaviorParameter);
            }
        }

        @objid ("8c011f86-1770-11e2-aa0d-002564c97630")
        @Override
        public Object visitStateMachine(StateMachine theStateMachine) {
            if (theStateMachine.getKind() == KindOfStateMachine.PROTOCOL) {
                return "protocol";
            } else {
                return visitBehavior(theStateMachine);
            }
        }

        @objid ("8c011f8b-1770-11e2-aa0d-002564c97630")
        @Override
        public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
            switch (theAssociationEnd.getAggregation()) {
            case KINDISAGGREGATION:
                return "aggregation";
            case KINDISCOMPOSITION:
                return "composition";
            case KINDISASSOCIATION:
            default:
                return null;
            
            }
        }

        @objid ("8c01469a-1770-11e2-aa0d-002564c97630")
        @Override
        public Object visitExpansionNode(ExpansionNode theExpansionNode) {
            if (theExpansionNode.getRegionAsOutput() == null) {
                // Input expansion node
                return "inputelement";
            } else {
                // Output expansion node
                return "outputelement";
            }
        }

        @objid ("8c016da8-1770-11e2-aa0d-002564c97630")
        @Override
        public Object visitInformationFlow(InformationFlow theInformationFlow) {
            if ((theInformationFlow.getRealizingActivityEdge().size() == 1) || (theInformationFlow.getRealizingFeature().size() == 1) || (theInformationFlow.getRealizingCommunicationMessage().size() == 1)
                    || (theInformationFlow.getRealizingLink().size() == 1) || (theInformationFlow.getRealizingMessage().size() == 1)) {
                // Input expansion node
                return "realizedinformationflow";
            } else {
                // Output expansion node
                return null;
            }
        }

        @objid ("8c016dad-1770-11e2-aa0d-002564c97630")
        @Override
        public Object visitConnectionPointReference(final ConnectionPointReference theConnectionPointReference) {
            if (theConnectionPointReference.getEntry() != null) {
                return "entry";
            } else if (theConnectionPointReference.getExit() != null) {
                return "exit";
            }
            return super.visitConnectionPointReference(theConnectionPointReference);
        }

        @objid ("c2a79bc0-bc2d-4a71-9839-9fa84e8b41b3")
        @Override
        public Object visitBpmnParticipant(BpmnParticipant theParticipant) {
            if (theParticipant.getProcess() == null) {
                // A participant that is not bound to a process is representing an external process, which is the default case
                return null;
            } else if (theParticipant.isLocal()) {
                // otherwise it is a 'simple' participant which can be either 'local'
                return "local";
            } else {
                // or 'referenced'
                return "referenced";
            }
        }

    }

}
