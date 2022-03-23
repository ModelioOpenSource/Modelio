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
package org.modelio.vcore.utils.metamodel.experts;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.utils.metamodel.experts.links.RuleBasedLinkExpertHelper;
import org.modelio.vcore.utils.metamodel.experts.meta.RuleBasedMetaExpertHelper;

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
 * 
 * <p>
 * Also provides service methods to work with 'links', e.g. Generalizations, Dependencies... Each link has a generic 'source' and
 * 'target', that can represent several MDependencies. In most cases, the source is also the composition owner.
 * </p>
 */
@objid ("dcd09b3c-6c66-40c2-a4af-11f17f2c21c0")
public class CompositeMetamodelExpert extends org.modelio.vcore.smkernel.meta.DefaultMetaExpert {
    @objid ("59d1b3e8-f3c1-4f8e-9103-0fdb337ffa93")
    private final LinkExpertRegistry LINK_REGISTRY;

    @objid ("1d2ea4f9-3b7e-4f37-8108-0ae4405609f5")
    private final MetaExpertRegistry META_REGISTRY;

    @objid ("f3fd850a-741d-4ae1-ac47-52634fea1e51")
    protected final RuleBasedLinkExpertHelper ruleLinkExpert;

    @objid ("fe6e85fc-5c79-4cf5-81c2-a2faacf4dfaf")
    protected final RuleBasedMetaExpertHelper ruleMetaExpert;

    @objid ("5ae736a7-970b-4418-9b5d-8e142e572a62")
    protected final MMetamodel metamodel;

    /**
     * @param mm the metamodel.
     */
    @objid ("488f1475-3aba-4e93-a3f0-13a8ced3f6b6")
    public  CompositeMetamodelExpert(MMetamodel mm) {
        this.metamodel = mm;
        
        this.ruleMetaExpert = new RuleBasedMetaExpertHelper(mm);
        this.ruleLinkExpert = new RuleBasedLinkExpertHelper(mm);
        
        this.LINK_REGISTRY = new LinkExpertRegistry(this.ruleLinkExpert);
        this.META_REGISTRY = new MetaExpertRegistry(this.ruleMetaExpert);
        
    }

    @objid ("c736fe6c-62fb-499b-b148-9b5e836820d3")
    @Override
    public boolean canLink(MClass linkMetaclass, MClass from, MClass to) {
        return this.LINK_REGISTRY.getExpert(linkMetaclass).canLink(linkMetaclass, from, to);
    }

    @objid ("1f99f811-816c-46eb-8cd3-d2099d713e36")
    @Override
    public boolean canLink(MClass linkMetaclass, MObject from, MObject to) {
        ILinkExpertHelper expert = this.LINK_REGISTRY.getExpert(linkMetaclass);
        return expert.canLink(linkMetaclass, from, to);
    }

    @objid ("8153954a-2255-4d4d-b8df-785756976090")
    @Override
    public boolean canSource(MClass linkMetaclass, MClass from) {
        return this.LINK_REGISTRY.getExpert(linkMetaclass).canSource(linkMetaclass, from);
    }

    @objid ("d0dbb3a3-e307-4399-b6e2-59946d827cda")
    @Override
    public boolean canTarget(MClass linkMetaclass, MClass to) {
        return this.LINK_REGISTRY.getExpert(linkMetaclass).canTarget(linkMetaclass, to);
    }

    @objid ("80a4cd4c-bdd6-4eb6-949d-78ff4b1b232a")
    @Override
    public boolean isLink(MClass metaclass) {
        return metaclass.isLinkMetaclass();
    }

    @objid ("6f12211c-4768-4f34-982c-8c756fe528c6")
    @Override
    public boolean canSource(MObject linkElement, MObject from) {
        ILinkExpertHelper expert = this.LINK_REGISTRY.getExpert(linkElement.getMClass());
        return expert.canSource(linkElement, from);
    }

    @objid ("20cb87e3-63a2-4211-8af4-61a7824650aa")
    @Override
    public boolean canTarget(MObject linkElement, MObject to) {
        ILinkExpertHelper expert = this.LINK_REGISTRY.getExpert(linkElement.getMClass());
        return expert.canTarget(linkElement, to);
    }

    @objid ("74c7ec9c-655d-41b0-a336-8b9914479e01")
    @Override
    public MObject getSource(MObject linkElement) {
        ILinkExpertHelper expert = this.LINK_REGISTRY.getExpert(linkElement.getMClass());
        return expert.getSource(linkElement);
    }

    @objid ("d259ca44-daa7-43f2-999a-fbd52062155a")
    @Override
    public MObject getTarget(MObject aLink) {
        ILinkExpertHelper expert = this.LINK_REGISTRY.getExpert(aLink.getMClass());
        return expert.getTarget(aLink);
    }

    @objid ("3618907d-5ed8-44bf-91d9-964d53823fca")
    @Override
    public void setSource(MObject linkElement, final MObject oldSource, MObject newSource) throws IllegalArgumentException {
        ILinkExpertHelper expert = this.LINK_REGISTRY.getExpert(linkElement.getMClass());
        expert.setSource(linkElement, oldSource, newSource);
        
    }

    @objid ("3f566980-7434-4750-b47f-08b6f54d4e42")
    @Override
    public void setTarget(MObject linkElement, final MObject oldTarget, MObject newTarget) throws IllegalArgumentException {
        ILinkExpertHelper expert = this.LINK_REGISTRY.getExpert(linkElement.getMClass());
        expert.setTarget(linkElement, oldTarget, newTarget);
        
    }

    // typical usage: creation tools
    @objid ("202a544b-0dd7-4cf5-9122-75ef94544552")
    @Override
    public boolean canCompose(MClass owner, MClass composed, String dep) {
        return this.META_REGISTRY.getExpert(owner).canCompose(owner, composed, dep);
    }

    // typical usage: creation tools where stereotypes count
    @objid ("23892fc4-da37-4eec-ace3-230ce558a3e2")
    @Override
    public boolean canCompose(MObject owner, MClass composed, String dep) {
        IMetaExpertHelper expert = this.META_REGISTRY.getExpert(owner.getMClass());
        return expert.canCompose(owner, composed, dep);
    }

    // typical usage: drag and drop
    @objid ("619b03c2-54de-493d-9819-1c2264d3df64")
    @Override
    public boolean canCompose(MObject owner, MObject composed, String dep) {
        IMetaExpertHelper expert = this.META_REGISTRY.getExpert(owner.getMClass());
        return expert.canCompose(owner, composed, dep);
    }

    // typical usage : property box for element editor ?
    @objid ("c284d458-94ff-4b08-b644-f3801c3fe5a7")
    @Override
    public boolean canDep(MClass source, MClass target, String dep) {
        return this.META_REGISTRY.getExpert(source).canDep(source, target, dep);
    }

    // typical usage : property box for element editor ?
    @objid ("014bdc0d-d230-478d-aa89-8213ebfc80ba")
    @Override
    public boolean canDep(MObject source, MClass target, String dep) {
        IMetaExpertHelper expert = this.META_REGISTRY.getExpert(source.getMClass());
        return expert.canDep(source, target, dep);
    }

    // typical usage : property box for element editor ?
    @objid ("7bc41f40-ba67-4147-8e33-59e3c3d13e44")
    @Override
    public boolean canDep(MObject source, MObject target, String dep) {
        IMetaExpertHelper expert = this.META_REGISTRY.getExpert(source.getMClass());
        return expert.canDep(source, target, dep);
    }

    /**
     * Register a link expert.
     * @param cls the metaclass
     * @param expert the expert
     */
    @objid ("b4a7090e-c511-4ad4-bc4c-4e0d1d03ee45")
    public void addLinkExpert(MClass cls, ILinkExpertHelper expert) {
        this.LINK_REGISTRY.addExpert(cls, expert);
    }

    /**
     * Register a meta expert.
     * @param cls the metaclass
     * @param expert the expert
     */
    @objid ("62178aa7-9659-4bd4-829e-ca8fbded2e65")
    public void addMetaExpert(MClass cls, IMetaExpertHelper expert) {
        this.META_REGISTRY.addExpert(cls, expert);
    }

    /**
     * Registry to get the expert for a given:
     * <ul>
     * <li>parent element metaclass.</li>
     * <li>link metaclass.</li>
     * <li>stereotype.</li>
     * <li>mobject</li>
     * </ul>
     * Custom experts must implements ILinkExpertHelper and be registered in the <tt>initialize()</tt> method.
     * <p>
     * Stereotype creation experts can be added with {@linkplain #registerExpert(Stereotype, ILinkExpertHelper)} and removed with
     * {@linkplain #unregisterExpert(Stereotype)}.
     */
    @objid ("be9a5788-4942-4897-ad8e-9e82ba564cc8")
    private static class LinkExpertRegistry {
        @objid ("481290eb-5068-436f-9431-ef1cd2ca3fc1")
        private final Map<MClass, ILinkExpertHelper> EXPERTS = new HashMap<>();

        @objid ("3e75df27-b5ca-4e1a-a986-c0e3406f1970")
        private final ILinkExpertHelper DEFAULT_EXPERT;

        /**
         * Get the creation expert for the given element.
         * @param metaclass a metamodel class.
         * @return the matching creation expert (never returns <code>null</code>)
         */
        @objid ("c418b3be-c97d-4aaf-b63c-197ed813db97")
        public ILinkExpertHelper getExpert(final MClass metaclass) {
            ILinkExpertHelper ret = this.EXPERTS.get(metaclass);
            if (ret == null) {
                // No specific expert, return the default one
                ret = this.DEFAULT_EXPERT;
            }
            return ret;
        }

        /**
         * This class has no instances.
         * @param defaultExpert the default expert
         */
        @objid ("a4e96390-88b2-408e-9634-c6ee24cb2e52")
        public  LinkExpertRegistry(ILinkExpertHelper defaultExpert) {
            // Init experts
            this.DEFAULT_EXPERT = defaultExpert;
            
        }

        /**
         * Register an expert
         * @param cls the metaclass
         * @param expert the expert
         */
        @objid ("5e5e5926-aaa5-46bd-bd14-c25dc0c1de53")
        public void addExpert(MClass cls, ILinkExpertHelper expert) {
            this.EXPERTS.put(cls, expert);
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
     * Custom experts must implements IMetaExpertHelper and be registered in the <tt>initialize()</tt> method.
     * <p>
     * Stereotype creation experts can be added with {@linkplain #registerExpert(Stereotype, IMetaExpertHelper)} and removed with
     * {@linkplain #removeStereotypeExpert(Stereotype)}.
     */
    @objid ("d612d042-b299-4f66-b7eb-b95b8aba7ae1")
    private static class MetaExpertRegistry {
        @objid ("ea792f39-5b4b-4509-bf9a-83205c2d5728")
        private final Map<MClass, IMetaExpertHelper> EXPERTS = new HashMap<>();

        @objid ("59df5646-29aa-4dfe-9e16-c8b7d3bc15c7")
        private IMetaExpertHelper DEFAULT_EXPERT;

        /**
         * Get the creation expert for the given element.
         * @param metaclass a metamodel class.
         * @return the matching creation expert (never returns <code>null</code>)
         */
        @objid ("177b6622-eb22-4535-b423-32374a4caff5")
        public IMetaExpertHelper getExpert(final MClass metaclass) {
            IMetaExpertHelper ret = this.EXPERTS.get(metaclass);
            if (ret == null) {
                // No specific expert, return the default one
                ret = this.DEFAULT_EXPERT;
            }
            return ret;
        }

        /**
         * @param defaultExpert the default expert.
         */
        @objid ("98103981-629c-4fb3-a78c-27b90e6ed456")
        public  MetaExpertRegistry(IMetaExpertHelper defaultExpert) {
            // Init experts
            this.DEFAULT_EXPERT = defaultExpert;
            
        }

        /**
         * Register an expert
         * @param cls the metaclass
         * @param expert the expert
         */
        @objid ("e498e252-6e7b-4b6c-b752-bce4421b0c08")
        public void addExpert(MClass cls, IMetaExpertHelper expert) {
            this.EXPERTS.put(cls, expert);
        }

    }

}
