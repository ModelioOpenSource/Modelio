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

package org.modelio.vcore.utils.metamodel.experts.links;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.utils.metamodel.experts.ILinkExpertHelper;

/**
 * Default creation expert that uses the {@link MetamodelRules} to answer.
 */
@objid ("7312b7c8-062e-452b-8cd6-d8f366a6bcaf")
public class RuleBasedLinkExpertHelper implements ILinkExpertHelper {
    @objid ("73a40fcb-7411-4f9a-bf07-58fe8ee5c8d6")
    private final MetamodelRules RULES;

    @objid ("1f6fe851-53ea-4ec0-9c5a-fdc95a457745")
    private final Map<MClass, AssocData> associations = new HashMap<>();

    @objid ("dd58f258-c305-410f-a937-030c38164c8e")
    private final MMetamodel mm;

    /**
     * @param mm the metamodel
     */
    @objid ("6f84bdc8-8e27-435d-bbf2-d1d559fefa87")
    public RuleBasedLinkExpertHelper(MMetamodel mm) {
        this.mm = mm;
        this.RULES = new MetamodelRules(mm);
    }

    @objid ("e58b5438-ed76-4b04-81ce-32cb3659fe3b")
    @Override
    public boolean canLink(MClass linkMetaclass, MObject from, MObject to) {
        return this.RULES.canLink(linkMetaclass, from.getMClass(), to.getMClass());
    }

    @objid ("88530f1f-5e35-41c9-8f65-121938f05e50")
    @Override
    public boolean canLink(MClass linkMetaclass, MClass from, MClass to) {
        return this.RULES.canLink(linkMetaclass, from, to);
    }

    @objid ("194de277-2928-495d-876e-8751b51365b7")
    @Override
    public boolean canSource(MObject linkElement, MObject from) {
        return this.RULES.canSource(linkElement.getMClass(), from.getMClass());
    }

    @objid ("67a7e3e4-b679-4857-983f-8b8d6e9351a6")
    @Override
    public boolean canSource(MClass linkMetaclass, final MClass fromMetaclass) {
        return this.RULES.canSource(linkMetaclass, fromMetaclass);
    }

    @objid ("86725bb9-3d50-4dfb-b27b-41a9c632ed72")
    @Override
    public boolean canTarget(MClass linkMetaclass, MClass toMetaclass) {
        return this.RULES.canTarget(linkMetaclass, toMetaclass);
    }

    @objid ("4a96a123-a78f-47e8-be49-e556773ce320")
    @Override
    public boolean canTarget(MObject linkElement, MObject to) {
        return this.RULES.canTarget(linkElement.getMClass(), to.getMClass());
    }

    /**
     * Add an allowed rule.
     * 
     * @param mcD link metaclass
     * @param mcX source metaclass
     * @param mcY target metaclass
     */
    @objid ("128432b2-c9a4-440c-82f7-59bbf46ebf67")
    public void addRule(Class<? extends MObject> mcD, Class<? extends MObject> mcX, Class<? extends MObject> mcY) {
        this.RULES.addRule(mcD, mcX, mcY);
    }

    /**
     * Add an allowed rule with subclasses if asked for.
     * 
     * @param mcD link metaclass
     * @param mcX source metaclass
     * @param xRec with subclasses
     * @param mcY target metaclass
     * @param yRec with target subclasses
     */
    @objid ("b0f58e92-adce-4b0b-84dc-088031340288")
    public void addRule(Class<? extends MObject> mcD, Class<? extends MObject> mcX, boolean xRec, Class<? extends MObject> mcY, boolean yRec) {
        this.RULES.addRule(mcD, mcX, xRec, mcY, yRec);
    }

    /**
     * Get a model link source
     * 
     * @param aLink a model link.
     * @return the link source
     */
    @objid ("0495b1cc-51c7-4d88-8e33-201e9c7c062d")
    @Override
    public MObject getSource(MObject aLink) {
        AssocData data = getData(aLink);
        
        for (MDependency dep : data.sources) {
            List<MObject> vals = aLink.mGet(dep);
            if (!vals.isEmpty()) {
                return vals.get(0);
            }
        }
        return null;
    }

    /**
     * Get a model link target.
     * 
     * @param aLink a model link.
     * @return the link target.
     */
    @objid ("1a7a524c-6a01-46fc-a275-442c2a77dbde")
    @Override
    public MObject getTarget(MObject aLink) {
        AssocData data = getData(aLink);
        
        for (MDependency dep : data.targets) {
            List<MObject> vals = aLink.mGet(dep);
            if (!vals.isEmpty()) {
                return vals.get(0);
            }
        }
        return null;
    }

    /**
     * @param metaclass a metamodel class
     * @return true if the metaclass is a relationship metaclasses.
     */
    @objid ("71e61ebf-c4f5-44ce-9676-e844c82ba153")
    public boolean isLink(MClass metaclass) {
        return this.associations.containsKey(metaclass);
    }

    /**
     * Change a model link target.
     * 
     * @param link a model link.
     * @param oldTarget the old target.
     * @param newTarget the new target.
     * @throws java.lang.IllegalArgumentException if the new destination is illegal or the link is not a model link.
     */
    @objid ("5eaa3115-74fc-4212-a295-7d67877d06a2")
    @Override
    public void setTarget(MObject link, MObject oldTarget, MObject newTarget) throws IllegalArgumentException {
        AssocData data = getData(link);
        MDependency found = null;
        
        for (MDependency dep : data.targets) {
            List<MObject> vals = link.mGet(dep);
            vals.retainAll(Collections.singletonList(newTarget));
        
            if (newTarget != null
                    && newTarget.getMClass().hasBase(dep.getTarget())
                    && !vals.contains(newTarget)) {
                if (found != null) {
                    throw new IllegalArgumentException(String.format("%s can be added to %s.%s and %s.%s",
                            newTarget,
                            link.getMClass().getName(),
                            found.getName(),
                            link.getMClass().getName(),
                            dep.getName()));
                } else {
                    vals.add(newTarget);
                    found = dep;
                }
            } else {
                found = dep;
            }
        }
        
        if (found == null && newTarget != null) {
            throw new IllegalArgumentException(newTarget.toString());
        }
    }

    /**
     * Change a model link source.
     * 
     * @param link a model link.
     * @param oldSource the old source.
     * @param newSource the new source.
     * @throws java.lang.IllegalArgumentException if the new destination is illegal or the link is not a model link.
     */
    @objid ("cc871ff1-06e7-4b1b-815f-3920b57bc735")
    @Override
    public void setSource(MObject link, MObject oldSource, MObject newSource) throws IllegalArgumentException {
        AssocData data = getData(link);
        MDependency found = null;
        
        for (MDependency dep : data.sources) {
            List<MObject> vals = link.mGet(dep);
            vals.retainAll(Collections.singletonList(newSource));
        
            if (newSource != null
                    && newSource.getMClass().hasBase(dep.getTarget())
                    && !vals.contains(newSource)) {
                if (found != null) {
                    throw new IllegalArgumentException(String.format("%s can be added to %s.%s and %s.%s",
                            newSource,
                            link.getMClass().getName(),
                            found.getName(),
                            link.getMClass().getName(),
                            dep.getName()));
                } else {
                    vals.add(newSource);
                    found = dep;
                }
            } else {
                found = dep;
            }
        }
        
        if (found == null && newSource != null) {
            throw new IllegalArgumentException(newSource.toString());
        }
    }

    /**
     * Register a source dependency
     * 
     * @param depClass the link metaclass interface
     * @param srcDepName the dependency name
     * @throws java.lang.IllegalArgumentException on input mistake.
     */
    @objid ("6d98708e-9ede-4e7d-a7e2-0836eb86e871")
    public void addSourceDep(Class<? extends MObject> depClass, String srcDepName) throws IllegalArgumentException {
        MClass mClass = getMClass(depClass);
        
        AssocData data = this.associations.get(mClass);
        if (data == null) {
            data = new AssocData();
            this.associations.put(mClass, data);
        }
        
        MDependency dep = mClass.getDependency(srcDepName);
        if (dep == null) {
            throw new IllegalArgumentException(String.format("'%s' metaclass has no '%s' dependency.", depClass, srcDepName));
        }
        
        data.sources.add(dep);
    }

    /**
     * Register a link metaclass.
     * 
     * @param linkMetaclassInterface a link metaclass name.
     */
    @objid ("ee7c9185-dfcf-4ee1-b642-f7fe4e1f9613")
    public void addLinkMetaclass(Class<? extends MObject> linkMetaclassInterface) {
        MClass mClass = getMClass(linkMetaclassInterface);
        
        AssocData data = this.associations.get(mClass);
        if (data == null) {
            data = new AssocData();
            this.associations.put(mClass, data);
        }
    }

    @objid ("8be4ba63-dfbe-4ceb-b4e2-6e67386b134f")
    private MClass getMClass(Class<? extends MObject> depClass) {
        MClass mClass = this.mm.getMClass(depClass);
        if (mClass == null) {
            throw new IllegalArgumentException(String.format("'%s' metaclass does not exist in '%s' metamodel.", depClass, this.mm.getFragments()));
        }
        return mClass;
    }

    /**
     * Register a target dependency.
     * 
     * @param depClass the link metaclass interface
     * @param depName the dependency name
     * @throws java.lang.IllegalArgumentException on input mistake.
     */
    @objid ("ff79561a-ded9-4bf8-bd90-6295e769e823")
    public void addTargetDep(Class<? extends MObject> depClass, String depName) throws IllegalArgumentException {
        MClass mClass = getMClass(depClass);
        
        AssocData data = this.associations.get(mClass);
        if (data == null) {
            data = new AssocData();
            this.associations.put(mClass, data);
        }
        
        MDependency dep = mClass.getDependency(depName);
        if (dep == null) {
            throw new IllegalArgumentException(String.format("'%s' metaclass has no '%s' dependency.", depClass, depName));
        }
        
        data.targets.add(dep);
    }

    @objid ("807c87b3-feec-4a29-9819-5d3d780963b0")
    private AssocData getData(MObject aLink) {
        AssocData assocData = this.associations.get(aLink.getMClass());
        if (assocData == null) {
            throw new IllegalArgumentException(aLink + " is not a link object");
        }
        return assocData;
    }

    /**
     * This class contains the metamodel knowledge of the {@link DefaultCreationExpert}.
     * <p>
     * A CreationExpert tool can answer the following questions:
     * <ol>
     * <li>can I create an object X under an Y object ?</li>
     * <li>can I create a link D from an X to an Y</li>
     * <li>can I start a link D from an X</li>
     * </ol>
     * where X, Y and D are metaclass names.
     * </p>
     * <p>
     * Example:<br/>
     * <tt>CreationExpert.canLink(IAssociation.class, IClass.class, IClass.class)</tt> => returns true
     * </p>
     */
    @objid ("e40417eb-f9d3-42ab-b39b-933078f430de")
    protected static class MetamodelRules {
        @objid ("95099f0c-7a7b-43e3-9a57-547d312c82be")
        protected final MMetamodel mm;

        @objid ("cf778a1c-fbcd-4e8e-b6ee-4a84368420d7")
        private final Set<RuleKey> directRules = new HashSet<>();

        @objid ("71e1f587-b102-43da-8f1b-b1223bc36c77")
        private final Set<RuleKey> canSourceRules = new HashSet<>();

        @objid ("ad59e286-130d-4dd5-bdd3-48aee6aa98a3")
        private final Set<RuleKey> canTargetRules = new HashSet<>();

        @objid ("761727f5-3a09-4810-96bd-80ef81651c1a")
        public MetamodelRules(MMetamodel mm) {
            this.mm = mm;
        }

        /**
         * Tells whether a link of the given metaclass can have another metaclass as source.
         * 
         * @param linkMetaclass The link metaclass
         * @param fromMetaclass The source metaclass
         * @return true if the creation is possible, false otherwise.
         */
        @objid ("a2df2d47-9a2f-46d6-a808-014374b6f56d")
        public boolean canSource(final MClass linkMetaclass, MClass fromMetaclass) {
            boolean ret = this.canSourceRules.contains(new RuleKey(linkMetaclass, fromMetaclass, null))
                    || this.canSourceRules.contains(new RuleKey(linkMetaclass, null, null));
            return ret;
        }

        /**
         * Tells whether a link of the given metaclass can be created between the 2 other metaclasses.
         * 
         * @param linkMetaclass The link metaclass
         * @param fromMetaclass The Source metaclass
         * @param toMetaclass The destination metaclass
         * @return true if the creation is possible, false otherwise.
         */
        @objid ("3128e055-b371-4e6a-a2a4-4e5477316194")
        public boolean canLink(final MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
            boolean ret = this.directRules.contains(new RuleKey(linkMetaclass, fromMetaclass, toMetaclass))
                    || this.directRules.contains(new RuleKey(linkMetaclass, fromMetaclass, null))
                    || this.directRules.contains(new RuleKey(linkMetaclass, null, null));
            return ret;
        }

        /**
         * Returns whether this metaclass is known or not.
         * 
         * @param linkMetaclass The link metaclass
         * @param toMetaclass The target metaclass
         * @return true if the creation is possible, false otherwise.
         */
        @objid ("16c6b124-7ac7-41f5-b0c4-0a4e0123eb22")
        public boolean canTarget(final MClass linkMetaclass, MClass toMetaclass) {
            boolean ret = this.canTargetRules.contains(new RuleKey(linkMetaclass, null, toMetaclass))
                    || this.canTargetRules.contains(new RuleKey(linkMetaclass, null, null));
            return ret;
        }

        /**
         * Add an allowed rule.
         * 
         * @param mcD link metaclass
         * @param mcX source metaclass
         * @param mcY target metaclass
         */
        @objid ("6a7eb2a8-9a5b-4f77-84a9-2cc59992e1c0")
        protected void addRule(Class<? extends MObject> mcD, Class<? extends MObject> mcX, Class<? extends MObject> mcY) {
            addRule(mcD, mcX, false, mcY, false);
        }

        /**
         * Add an allowed rule.
         * 
         * @param mcD link metaclass
         * @param mcX source metaclass
         * @param xRec with subclasses
         * @param mcY target metaclass
         * @param yRec with target subclasses
         */
        @objid ("86cf37ac-1ea4-4f0c-8f8f-42d8d2891f60")
        protected void addRule(Class<? extends MObject> mcD, Class<? extends MObject> mcX, boolean xRec, Class<? extends MObject> mcY, boolean yRec) {
            // Add 'canSource' rules
            this.canSourceRules.add(key(mcD, mcX, null));
            
            // Add 'canLink' rules
            this.directRules.add(key(mcD, mcX, mcY));
            
            // Add 'canTarget' rules
            this.canTargetRules.add(key(mcD, null, mcY));
            
            if (xRec) {
                MClass mx = this.mm.getMClass(mcX);
                for (MClass xsub : mx.getSub(false)) {
                    addRule(mcD, xsub.getJavaInterface(), true, mcY, yRec);
                }
            }
            
            if (yRec) {
                MClass my = this.mm.getMClass(mcY);
                for (MClass ysub : my.getSub(false)) {
                    addRule(mcD, mcX, xRec, ysub.getJavaInterface(), true);
                }
            }
        }

        /**
         * Create a rule key from metaclass java interfaces.
         * 
         * @param link link metaclass
         * @param source source metaclass
         * @param target target metaclass
         * @return the built RuleKey
         */
        @objid ("b003168f-382f-41e5-a6b9-fbf482960448")
        protected RuleKey key(Class<? extends MObject> link, Class<? extends MObject> source, Class<? extends MObject> target) {
            return new RuleKey(this.mm.getMClass(link),
                                            source != null ? this.mm.getMClass(source) : null,
                                            target != null ? this.mm.getMClass(target) : null);
        }

        @objid ("fc2d907e-5b1a-4188-82c7-3187aff9a446")
        private static class RuleKey {
            @objid ("0e21eec4-c4d8-4f8e-9edc-e32b99087a85")
            public final MClass link;

            @objid ("8e8d4a70-fb5d-4407-85a3-49649fa849f9")
            public final MClass source;

            @objid ("a4625c61-b1d8-454f-a8dc-6011af4ab501")
            public final MClass target;

            @objid ("6d7acb60-e63a-4646-8b15-843da45a2676")
            public RuleKey(MClass link, MClass source, MClass target) {
                super();
                this.link = link;
                this.source = source;
                this.target = target;
            }

            @objid ("1ade9953-0405-44b8-9f89-be764cfcb3e5")
            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((this.link == null) ? 0 : this.link.hashCode());
                result = prime * result + ((this.source == null) ? 0 : this.source.hashCode());
                result = prime * result + ((this.target == null) ? 0 : this.target.hashCode());
                return result;
            }

            @objid ("beb1c32e-f696-4548-bcbd-dd38dfa83580")
            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                RuleKey other = (RuleKey) obj;
                if (this.link == null) {
                    if (other.link != null) {
                        return false;
                    }
                } else if (!this.link.equals(other.link)) {
                    return false;
                }
                if (this.source == null) {
                    if (other.source != null) {
                        return false;
                    }
                } else if (!this.source.equals(other.source)) {
                    return false;
                }
                if (this.target == null) {
                    if (other.target != null) {
                        return false;
                    }
                } else if (!this.target.equals(other.target)) {
                    return false;
                }
                return true;
            }

            @objid ("49d902c2-5d98-4feb-8157-8ca6236efe3a")
            @Override
            public String toString() {
                return "RuleKey [link=" + this.link + ", source=" + this.source + ", target=" + this.target + "]";
            }

        }

    }

    @objid ("8d667dfc-111f-4209-a084-cb340da29f7d")
    protected static class AssocData {
        @objid ("5d3589e4-ed20-44b1-be6e-7e254d250639")
        public final List<MDependency> sources = new ArrayList<>();

        @objid ("ba026447-5bac-4215-a635-f025decc0046")
        public final List<MDependency> targets = new ArrayList<>();

    }

}
