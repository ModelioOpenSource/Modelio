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

package org.modelio.metamodel.impl.expert;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.creation.BindingCreationExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.creation.BpmnDataAssociationCreationExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.creation.BpmnMessageFlowCreationExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.creation.BpmnSequenceFlowCreationExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.creation.ClassAssociationCreationExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.creation.DefaultLinkExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.creation.TemplateBindingCreationExpert;
import org.modelio.metamodel.impl.expert.standard.links.impl.ends.ChangeSourceVisitor;
import org.modelio.metamodel.impl.expert.standard.links.impl.ends.ChangeTargetVisitor;
import org.modelio.metamodel.impl.expert.standard.links.impl.ends.GetSourceVisitor;
import org.modelio.metamodel.impl.expert.standard.links.impl.ends.GetTargetVisitor;
import org.modelio.metamodel.impl.expert.standard.meta.IMetaExpert;
import org.modelio.metamodel.impl.expert.standard.meta.impl.BpmnLaneCreationExpert;
import org.modelio.metamodel.impl.expert.standard.meta.impl.DefaultMetaExpert;
import org.modelio.metamodel.impl.expert.standard.meta.impl.PartDecompositionCreationExpert;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * <p>
 * The Standard metamodel expert tool can answer questions about dependencies between metaclass or elements.
 * <ol>
 * <li>is the model X <>-----dep-> Y possible?</li>
 * <li>get the default composition dependency between X and Y.</li>
 * <li>is the model X -------dep-> Y possible?</li>
 * <li>get possibles dependencies between X and Y.</li>
 * </ol>
 * where X, Y are either elements or metaclasses and dep a SmDependency name.
 * </p>
 * <p>
 * Also provides service methods to work with 'links', e.g. Generalizations, Dependencies... Each link has a generic 'source' and
 * 'target', that can represent several MDependencies. In most cases, the source is also the composition owner.
 * </p>
 */
@objid ("eea525b6-9048-4cb8-ba0d-109148c04a6e")
public class StandardMetamodelExpert extends org.modelio.vcore.smkernel.meta.DefaultMetaExpert {
    @objid ("005875aa-d07d-1098-bcec-001ec947cd2a")
    private final LinkExpertRegistry LINK_REGISTRY;

    @objid ("a95b9e47-9a0e-4a85-bb41-03c5d9a7f9d8")
    private final GetTargetVisitor getTargetVisitor = new GetTargetVisitor();

    @objid ("5aea8470-8d61-45a9-a0ef-27b6173f57aa")
    private final GetSourceVisitor getSourceVisitor = new GetSourceVisitor();

    @objid ("5a4f6064-ee7a-48e3-99a3-9611a409d7f7")
    private final ChangeTargetVisitor changeTargetVisitor = new ChangeTargetVisitor();

    @objid ("45da8282-da2a-492f-8003-e3f7ff60ab58")
    private final ChangeSourceVisitor changeSourceVisitor = new ChangeSourceVisitor();

    @objid ("00928326-de01-1097-bcec-001ec947cd2a")
    private final MetaExpertRegistry META_REGISTRY;

    /**
     * @param mm the metamodel.
     */
    @objid ("ea0fbaa9-47e9-4e3b-8907-142d54d6e6c4")
    public StandardMetamodelExpert(MMetamodel mm) {
        this.LINK_REGISTRY = new LinkExpertRegistry(mm);
        this.META_REGISTRY = new MetaExpertRegistry(mm);
    }

    @objid ("000103ce-de02-1097-bcec-001ec947cd2a")
    @Override
    public boolean canLink(MClass linkMetaclass, MClass from, MClass to) {
        return this.LINK_REGISTRY.getExpert(linkMetaclass).canLink(linkMetaclass, from, to);
    }

    @objid ("0001bee0-de02-1097-bcec-001ec947cd2a")
    @Override
    public boolean canLink(MClass linkMetaclass, MObject from, MObject to) {
        ILinkExpert expert = this.LINK_REGISTRY.getExpert(linkMetaclass);
        return expert.canLink(linkMetaclass, from, to);
    }

    @objid ("0001f356-de02-1097-bcec-001ec947cd2a")
    @Override
    public boolean canSource(MClass linkMetaclass, MClass from) {
        return this.LINK_REGISTRY.getExpert(linkMetaclass).canSource(linkMetaclass, from);
    }

    @objid ("000287bc-de02-1097-bcec-001ec947cd2a")
    @Override
    public boolean canTarget(MClass linkMetaclass, MClass to) {
        return this.LINK_REGISTRY.getExpert(linkMetaclass).canTarget(linkMetaclass, to);
    }

    @objid ("0002b732-de02-1097-bcec-001ec947cd2a")
    @Override
    public boolean isLink(MClass metaclass) {
        /*
         * switch (metaclass.getName()) {
         * case Abstraction.MNAME:
         * case ActivityEdge.MNAME:
         * case AssociationEnd.MNAME:
         * case Binding.MNAME:
         * case BpmnDataAssociation.MNAME:
         * case BpmnMessageFlow.MNAME:
         * case BpmnSequenceFlow.MNAME:
         * case ComponentRealization.MNAME:
         * case CommunicationChannel.MNAME:
         * case ConnectorEnd.MNAME:
         * case ControlFlow.MNAME:
         * case DataFlow.MNAME:
         * case ElementImport.MNAME:
         * case ElementRealization.MNAME:
         * case ExceptionHandler.MNAME:
         * case Generalization.MNAME:
         * case InformationFlow.MNAME:
         * case InterfaceRealization.MNAME:
         * case LinkEnd.MNAME:
         * case Manifestation.MNAME:
         * case Message.MNAME:
         * case PackageImport.MNAME:
         * case PackageMerge.MNAME:
         * case RaisedException.MNAME:
         * case Substitution.MNAME:
         * case TemplateBinding.MNAME:
         * case Transition.MNAME:
         * case UseCaseDependency.MNAME:
         * return true;
         * default:
         * return false;
         * }
         */
        return metaclass.isLinkMetaclass();
    }

    @objid ("fc3c8cde-ad5c-4f5e-98ad-83240629655c")
    @Override
    public boolean canSource(MObject linkElement, MObject from) {
        ILinkExpert expert = this.LINK_REGISTRY.getExpert(linkElement.getMClass());
        return expert.canSource(linkElement, from);
    }

    @objid ("be7fadb4-281c-4720-a752-3f0421411165")
    @Override
    public boolean canTarget(MObject linkElement, MObject to) {
        ILinkExpert expert = this.LINK_REGISTRY.getExpert(linkElement.getMClass());
        return expert.canTarget(linkElement, to);
    }

    @objid ("11bfc22f-1502-4ace-bbca-7b9d4c57764d")
    @Override
    public MObject getSource(MObject aLink) {
        return (MObject) aLink.accept(this.getSourceVisitor);
    }

    @objid ("fe54925f-39b3-4c74-a153-39dee2720cc1")
    @Override
    public MObject getTarget(MObject aLink) {
        return (MObject) aLink.accept(this.getTargetVisitor);
    }

    @objid ("42355f12-6c43-4fb0-98ac-308d83f2c9a9")
    @Override
    public void setSource(MObject linkElement, final MObject oldSource, MObject newSource) throws IllegalArgumentException {
        this.changeSourceVisitor.oldSource = oldSource;
        this.changeSourceVisitor.newSource = newSource;
        try {
            linkElement.accept(this.changeSourceVisitor);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException(newSource + " is not a legal source for " + linkElement, e);
        }
    }

    @objid ("4a959dc3-cf59-48aa-ba80-344a28195da2")
    @Override
    public void setTarget(MObject linkElement, final MObject oldTarget, MObject newTarget) throws IllegalArgumentException {
        this.changeTargetVisitor.oldTarget = oldTarget;
        this.changeTargetVisitor.newTarget = newTarget;
        try {
            linkElement.accept(this.changeTargetVisitor);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException(newTarget + " is not a legal target for " + linkElement, e);
        }
    }

// typical usage: creation tools
    @objid ("0092974e-de01-1097-bcec-001ec947cd2a")
    @Override
    public boolean canCompose(MClass owner, MClass composed, String dep) {
        return this.META_REGISTRY.getExpert(owner).canCompose(owner, composed, dep);
    }

// typical usage: creation tools where stereotypes count
    @objid ("0092d830-de01-1097-bcec-001ec947cd2a")
    @Override
    public boolean canCompose(MObject owner, MClass composed, String dep) {
        IMetaExpert expert = this.META_REGISTRY.getExpert(owner.getMClass());
        return expert.canCompose(owner, composed, dep);
    }

// typical usage: drag and drop
    @objid ("00935c10-de01-1097-bcec-001ec947cd2a")
    @Override
    public boolean canCompose(MObject owner, MObject composed, String dep) {
        IMetaExpert expert = this.META_REGISTRY.getExpert(owner.getMClass());
        return expert.canCompose(owner, composed, dep);
    }

// typical usage: move objects, import
    @objid ("0094215e-de01-1097-bcec-001ec947cd2a")
    @Override
    public MDependency getDefaultCompositionDep(MObject owner, MObject composed) {
        CompositionDepVisitor v = new CompositionDepVisitor(composed);
        MDependency dep = (MDependency) owner.accept(v);
        return dep != null ? dep : super.getDefaultCompositionDep(owner, composed);
    }

// typical usage : property box for element editor ?
    @objid ("0094af34-de01-1097-bcec-001ec947cd2a")
    @Override
    public boolean canDep(MClass source, MClass target, String dep) {
        return this.META_REGISTRY.getExpert(source).canDep(source, target, dep);
    }

// typical usage : property box for element editor ?
    @objid ("0094f14c-de01-1097-bcec-001ec947cd2a")
    @Override
    public boolean canDep(MObject source, MClass target, String dep) {
        IMetaExpert expert = this.META_REGISTRY.getExpert(source.getMClass());
        return expert.canDep(source, target, dep);
    }

// typical usage : property box for element editor ?
    @objid ("00953418-de01-1097-bcec-001ec947cd2a")
    @Override
    public boolean canDep(MObject source, MObject target, String dep) {
        IMetaExpert expert = this.META_REGISTRY.getExpert(source.getMClass());
        return expert.canDep(source, target, dep);
    }

    /**
     * Registry to get the expert for a given:
     * <ul>
     * <li>parent element metaclass.</li>
     * <li>link metaclass.</li>
     * <li>stereotype.</li>
     * <li>mobject</li>
     * </ul>
     * Custom experts must implements ILinkExpert and be registered in the <tt>initialize()</tt> method.
     * <p>
     * Stereotype creation experts can be added with {@linkplain #registerExpert(Stereotype, ILinkExpert)} and removed with
     * {@linkplain #unregisterExpert(Stereotype)}.
     */
    @objid ("0002d58c-de02-1097-bcec-001ec947cd2a")
    private static class LinkExpertRegistry {
        @objid ("0002e7e8-de02-1097-bcec-001ec947cd2a")
        private final Map<MClass, ILinkExpert> EXPERTS = new HashMap<>();

        @objid ("0059ee76-d07d-1098-bcec-001ec947cd2a")
        private final ILinkExpert DEFAULT_EXPERT;

        /**
         * Get the creation expert for the given element.
         * @param metaclass a metamodel class.
         * @return the matching creation expert (never returns <code>null</code>)
         */
        @objid ("00033572-de02-1097-bcec-001ec947cd2a")
        public ILinkExpert getExpert(final MClass metaclass) {
            ILinkExpert ret = this.EXPERTS.get(metaclass);
            if (ret == null) {
                // No specific expert, return the default one
                ret = this.DEFAULT_EXPERT;
            }
            return ret;
        }

        /**
         * This class has no instances.
         * @param mm the metamodel.
         */
        @objid ("00043486-de02-1097-bcec-001ec947cd2a")
        public LinkExpertRegistry(MMetamodel mm) {
            // Init experts
            this.DEFAULT_EXPERT = new DefaultLinkExpert(mm);
            
            this.EXPERTS.put(mm.getMClass(Binding.class), new BindingCreationExpert(this.DEFAULT_EXPERT));
            this.EXPERTS.put(mm.getMClass(BpmnDataAssociation.class), new BpmnDataAssociationCreationExpert(this.DEFAULT_EXPERT));
            this.EXPERTS.put(mm.getMClass(BpmnMessageFlow.class), new BpmnMessageFlowCreationExpert(this.DEFAULT_EXPERT));
            this.EXPERTS.put(mm.getMClass(BpmnSequenceFlow.class), new BpmnSequenceFlowCreationExpert(this.DEFAULT_EXPERT));
            this.EXPERTS.put(mm.getMClass(TemplateBinding.class), new TemplateBindingCreationExpert(this.DEFAULT_EXPERT));
            this.EXPERTS.put(mm.getMClass(ClassAssociation.class), new ClassAssociationCreationExpert(this.DEFAULT_EXPERT));
        }

    }

    /**
     * Registry to get the expert for a given:
     * <ul>
     * <li>parent element metaclass.</li>
     * <li>link metaclass.</li>
     * <li>stereotype.</li>
     * <li>mobject</li>
     * </ul>
     * Custom experts must implements IMetaExpert and be registered in the <tt>initialize()</tt> method.
     * <p>
     * Stereotype creation experts can be added with {@linkplain #registerExpert(Stereotype, IMetaExpert)} and removed with
     * {@linkplain #removeStereotypeExpert(Stereotype)}.
     */
    @objid ("00962f58-de01-1097-bcec-001ec947cd2a")
    private static class MetaExpertRegistry {
        @objid ("00964bf0-de01-1097-bcec-001ec947cd2a")
        private final Map<MClass, IMetaExpert> EXPERTS = new HashMap<>();

        @objid ("004ed842-d07d-1098-bcec-001ec947cd2a")
        private IMetaExpert DEFAULT_EXPERT;

        /**
         * Get the creation expert for the given element.
         * @param metaclass a metamodel class.
         * @return the matching creation expert (never returns <code>null</code>)
         */
        @objid ("0096f2c6-de01-1097-bcec-001ec947cd2a")
        public IMetaExpert getExpert(final MClass metaclass) {
            IMetaExpert ret = this.EXPERTS.get(metaclass);
            if (ret == null) {
                // No specific expert, return the default one
                ret = this.DEFAULT_EXPERT;
            }
            return ret;
        }

        /**
         * This class has no instances.
         * @param mm the metamodel.
         */
        @objid ("0097fbc6-de01-1097-bcec-001ec947cd2a")
        public MetaExpertRegistry(MMetamodel mm) {
            // Init experts
            this.DEFAULT_EXPERT = new DefaultMetaExpert();
            this.EXPERTS.put(mm.getMClass(Lifeline.class), new PartDecompositionCreationExpert((SmClass) mm.getMClass(PartDecomposition.class)));
            this.EXPERTS.put(mm.getMClass(BpmnLane.class), new BpmnLaneCreationExpert());
        }

    }

    @objid ("80e7d037-272c-11e2-a9d1-002564c97630")
    private static class CompositionDepVisitor extends DefaultModelVisitor {
        @objid ("80e7d039-272c-11e2-a9d1-002564c97630")
        private final MObject objDest;

        @objid ("80ea318e-272c-11e2-a9d1-002564c97630")
        public CompositionDepVisitor(MObject to) {
            this.objDest = to;
        }

        @objid ("80ea3197-272c-11e2-a9d1-002564c97630")
        @Override
        public Object visitClassifier(Classifier theElement) {
            if (this.objDest instanceof BindableInstance) {
                return theElement.getMClass().getDependency("InternalStructure");
            }
            // the rest of the cases seem correctly handled by the generic algorithm
            return visitUmlModelElement(theElement);
        }

        @objid ("80ea31a3-272c-11e2-a9d1-002564c97630")
        @Override
        public Object visitOperation(Operation theElement) {
            if (this.objDest instanceof Parameter) {
                Parameter param = (Parameter) this.objDest;
                if (param.getReturned() != null) {
                    return theElement.getMClass().getDependency("Return");
                } else {
                    return theElement.getMClass().getDependency("IO");
                }
            }
            // the rest of the cases seem correctly handled by the generic algorithm
            return visitUmlModelElement(theElement);
        }

        @objid ("80ea31a9-272c-11e2-a9d1-002564c97630")
        @Override
        public Object visitProject(Project theElement) {
            if (this.objDest instanceof Package) {
                return theElement.getMClass().getDependency("Model");
            }
            // the rest of the cases seem correctly handled by the generic algorithm
            return null;
        }

        @objid ("80ea31af-272c-11e2-a9d1-002564c97630")
        @Override
        public Object visitState(State theState) {
            if (this.objDest instanceof InternalTransition) {
                return theState.getMClass().getDependency("Internal");
            }
            // the rest of the cases seem correctly handled by the generic algorithm
            return visitUmlModelElement(theState);
        }

    }

}
