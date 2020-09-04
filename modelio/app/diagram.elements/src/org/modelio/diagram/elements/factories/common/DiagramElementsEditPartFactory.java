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

package org.modelio.diagram.elements.factories.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.jface.resource.ImageRegistry;
import org.modelio.diagram.elements.common.embeddeddiagram.EmbeddedDiagramRootEditPart;
import org.modelio.diagram.elements.common.embeddeddiagram.GmEmbeddedDiagram;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.group.GroupEditPart;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.image.ImageEditPart;
import org.modelio.diagram.elements.common.image.LabelledImageEditPart;
import org.modelio.diagram.elements.common.image.NonSelectableImageEditPart;
import org.modelio.diagram.elements.common.image.UserDefinedImageProvider;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.common.label.name.GmNameLabel;
import org.modelio.diagram.elements.common.label.name.GmNameSimpleLabel;
import org.modelio.diagram.elements.common.label.name.NameLabelEditPart;
import org.modelio.diagram.elements.common.label.name.NameSimpleLabelEditPart;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.common.simple.NonSelectableSimpleEditPart;
import org.modelio.diagram.elements.common.simple.SimpleEditPart;
import org.modelio.diagram.elements.common.text.ElementTextEditPart;
import org.modelio.diagram.elements.common.text.GmElementText;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.ImageServices;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.umlcommon.dependency.DependencyEditPart;
import org.modelio.diagram.elements.umlcommon.dependency.GmDependency;
import org.modelio.diagram.elements.umlcommon.diagramheader.DiagramHeaderEditPart;
import org.modelio.diagram.elements.umlcommon.diagramheader.GmDiagramHeader;
import org.modelio.diagram.elements.umlcommon.diagramholder.GmDiagramHolderLink;
import org.modelio.diagram.elements.umlcommon.diagramview.DiagramViewEditPart;
import org.modelio.diagram.elements.umlcommon.diagramview.GmDiagramView;
import org.modelio.diagram.elements.umlcommon.externdocument.ExternDocumentEditPart;
import org.modelio.diagram.elements.umlcommon.externdocument.GmExternDocument;
import org.modelio.diagram.elements.umlcommon.externdocument.GmExternDocumentLink;
import org.modelio.diagram.elements.umlcommon.namespaceuse.GmNamespaceUse;
import org.modelio.diagram.elements.umlcommon.namespaceuse.GmNamespaceUseLabel;
import org.modelio.diagram.elements.umlcommon.namespaceuse.NamespaceUseEditPart;
import org.modelio.diagram.elements.umlcommon.note.GmNote;
import org.modelio.diagram.elements.umlcommon.note.GmNoteLink;
import org.modelio.diagram.elements.umlcommon.note.NoteEditPart;
import org.modelio.diagram.elements.umlcommon.usage.GmUsage;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;

/**
 * Implementation of {@link EditPartFactory} in charge of {@link EditPart} elements brought by the {@link DiagramElements} plugin.
 */
@objid ("82e2cea5-5fff-4eeb-8940-fab431b8d63a")
public class DiagramElementsEditPartFactory implements EditPartFactory {
    /**
     * the default factory to use when {@link RepresentationMode#IMAGE} mode is requested.
     */
    @objid ("b86d541d-013a-42a0-8ff5-56806a7e300c")
    private ImageModeEditPartFactory imageModeEditPartFactory = new ImageModeEditPartFactory();

    /**
     * the default factory to use when {@link RepresentationMode#SIMPLE} mode is requested.
     */
    @objid ("08e62501-9fbd-4a44-811c-9821ef38c03e")
    private SimpleModeEditPartFactory simpleModeEditPartFactory = new SimpleModeEditPartFactory();

    /**
     * the default factory to use when {@link RepresentationMode#STRUCTURED} mode is requested.
     */
    @objid ("8b99858a-99e1-4bdf-8b34-8a55ad232b04")
    private StructuredModeEditPartFactory structuredModeEditPartFactory = new StructuredModeEditPartFactory();

    /**
     * the default factory to use when {@link RepresentationMode#USER_IMAGE} mode is requested.
     */
    @objid ("395165f7-5e78-4f42-a9f0-5c857f2dafb0")
    private UserImageModeEditPartFactory userImageModeEditPartFactory = new UserImageModeEditPartFactory();

    @objid ("47144ec1-f7fd-43fd-8820-79d7db3da0d9")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        if (model instanceof GmNodeModel) {
            // For node models, delegates according the representation mode.
            GmNodeModel node = (GmNodeModel) model;
            switch (node.getRepresentationMode()) {
            case USER_IMAGE:
                editPart = this.userImageModeEditPartFactory.createEditPart(context, model);
                break;
            case IMAGE:
                editPart = this.imageModeEditPartFactory.createEditPart(context, model);
                break;
            case SIMPLE:
                editPart = this.simpleModeEditPartFactory.createEditPart(context, model);
                break;
            case STRUCTURED:
                editPart = this.structuredModeEditPartFactory.createEditPart(context, model);
                break;
            default:
                editPart = null;
                break;
            }
        } else {
            // Link models are always in structured mode.
            editPart = this.structuredModeEditPartFactory.createEditPart(context, model);
        }
        return editPart;
    }

    /**
     * EditPart factory for node models in stereotype image mode.
     * 
     * @author cmarin
     */
    @objid ("73e83b4a-a0fe-4be8-a6de-7b95bb45aeb8")
    private static final class ImageModeEditPartFactory implements EditPartFactory {
        @objid ("caae0f87-90d3-4ae1-920a-2e088e943f03")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            // Port containers stay a port container in image mode
            if (model instanceof GmPortContainer) {
                new IllegalStateException("Ports containers should never be in image mode.").printStackTrace();
            
                final EditPart editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model instanceof IImageableNode && model instanceof GmNodeModel) {
                if (((GmNodeModel) model).getParent() instanceof GmPortContainer) {
                    final EditPart editPart = new NonSelectableImageEditPart();
                    editPart.setModel(model);
                    return editPart;
                } else {
                    final EditPart editPart = new LabelledImageEditPart();
                    editPart.setModel(model);
                    return editPart;
                }
            }
            return null;
        }

    }

    /**
     * EditPart factory for node models in simple mode.
     * 
     * @author cmarin
     */
    @objid ("d4edd670-adc2-4624-a6b7-e650bd126142")
    private static final class SimpleModeEditPartFactory implements EditPartFactory {
        @objid ("b583b355-a743-406e-9ccd-d3f4c3e834d3")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            // Port containers stay a port container in simple mode
            if (model instanceof GmPortContainer) {
                final EditPart editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Fall back
            
            if (model instanceof GmModelElementHeader) {
                EditPart editPart;
                if (((GmModelElementHeader) model).isFlat()) {
                    editPart = new ModelElementLabelEditPart();
                } else {
                    editPart = new ModelElementHeaderEditPart();
                }
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmNodeModel) {
                if (((GmNodeModel) model).getParent() instanceof GmPortContainer) {
                    final SimpleEditPart editPart = new NonSelectableSimpleEditPart();
                    editPart.setModel(model);
                    return editPart;
                } else {
                    final SimpleEditPart editPart = new SimpleEditPart();
                    editPart.setModel(model);
                    return editPart;
                }
            }
            
            // Not handled
            return null;
        }

    }

    /**
     * EditPart factory for node models in standard structured mode.
     * <p>
     * This is the default mode so the default factory.
     * 
     * @author cmarin
     */
    @objid ("de3fb6bd-9371-436f-9007-caa26ba09742")
    private static final class StructuredModeEditPartFactory implements EditPartFactory {
        @objid ("17706f3e-2781-40d0-b315-0645ea3554ac")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            // "standard" elements
            if (model.getClass() == GmDependency.class) {
                editPart = new DependencyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmUsage.class) {
                editPart = new DependencyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmElementLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNameSimpleLabel.class) {
                editPart = new NameSimpleLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNameLabel.class) {
                editPart = new NameLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmBodyFreeZone.class) {
                editPart = new FreeZoneEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNote.class) {
                editPart = new NoteEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNoteLink.class) {
                editPart = new LinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExternDocument.class) {
                editPart = new ExternDocumentEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExternDocumentLink.class) {
                editPart = new LinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNamespaceUse.class) {
                editPart = new NamespaceUseEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmNamespaceUseLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEmbeddedDiagram.class) {
                // editPart = new EmbeddedDiagramEditPart();
                editPart = new EmbeddedDiagramRootEditPart(context, model); // TODO embedded diagrams here
                editPart.setModel(model);
                return editPart;
            }
            
            // Related Diagram links
            if (model instanceof GmDiagramHeader) {
                editPart = new DiagramHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmDiagramHolderLink) {
                editPart = new LinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Last chance: Generic fall backs
            // -------------------------------
            if (model instanceof GmElementText) {
                editPart = new ElementTextEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmGroup) {
                editPart = new GroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmFreeZone) {
                editPart = new FreeZoneEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmModelElementLabel) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmModelElementHeader) {
                if (((GmModelElementHeader) model).isFlat()) {
                    editPart = new ModelElementLabelEditPart();
                } else {
                    editPart = new ModelElementHeaderEditPart();
                }
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmDiagramView) {
                editPart = new DiagramViewEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // End of last chance generic fall backs
            return null;
        }

    }

    /**
     * EditPart factory for node models in user image mode.
     * 
     * @author cmarin
     */
    @objid ("eec2a400-4873-41f0-83d7-7e1e75025818")
    private static final class UserImageModeEditPartFactory implements EditPartFactory {
        @objid ("deefe4ca-9314-4ca5-bdeb-c36d32c720a0")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            if (model instanceof GmPortContainer) {
                // Port containers stay a port container in image mode
                new IllegalStateException("Ports containers should never be in image mode.").printStackTrace();
            
                final EditPart editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            UserDefinedImageProvider imProv = createImageProvider(context, model);
            return createUserImageEditPart(model, imProv);
        }

        @objid ("9557c374-3186-4e70-81d3-831a12c7ae2a")
        protected UserDefinedImageProvider createImageProvider(EditPart context, Object model) {
            ImageRegistry imageRegistry = ImageServices.getImageRegistry(context);
            UserDefinedImageProvider imProv = new UserDefinedImageProvider((GmModel) model, imageRegistry);
            return imProv;
        }

        @objid ("030fadfa-f2d6-4945-8fd9-add9693eabf6")
        protected EditPart createUserImageEditPart(Object model, UserDefinedImageProvider imProv) {
            if (((GmNodeModel) model).getParent() instanceof GmPortContainer) {
                final ImageEditPart editPart = new NonSelectableImageEditPart();
                editPart.setModel(model);
                editPart.setImageProvider(imProv);
                return editPart;
            } else {
                final ImageEditPart editPart = new LabelledImageEditPart();
                editPart.setModel(model);
                editPart.setImageProvider(imProv);
                return editPart;
            }
        }

    }

}
